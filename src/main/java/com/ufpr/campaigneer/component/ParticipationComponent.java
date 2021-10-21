package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.ParticipationDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.enums.ViolationType;
import com.ufpr.campaigneer.model.*;
import com.ufpr.campaigneer.service.ParticipationService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 21/08/2021
 */

@Component
public class ParticipationComponent implements ParticipationService {

    ParticipationDAO dao = new ParticipationDAO();
    CampaignComponent campaignComponent = new CampaignComponent();
    ProductComponent productComponent = new ProductComponent();
    AddressComponent addressComponent = new AddressComponent();
    DataCorrectionComponent correctionComponent = new DataCorrectionComponent();
    EmailComponent emailComponent = new EmailComponent();

    private static final String INVOICE_FOLDER = "/invoices/";

    @Override
    public Optional<Participation> create(Participation participation) {
        participation.setCampaignStatus(CampaignStatus.NOT_PROCESSED);
        Product product = participation.getProducts().stream().findFirst().orElse(new Product());
        Product productEntity = productComponent.findByEAN(product.getEan())
                .orElseThrow(() -> new NotFoundException("Failed to create Participation. Product not found."));
        participation.getProducts().clear();
        participation.getProducts().add(productEntity);
        Address address = addressComponent.findBillingAddress(participation.getAddresses());

        Address addressEntity = addressComponent.findByPostalCodeAndNumber(address.getPostalCode(), address.getStreetNumber())
                .orElseThrow(() -> new NotFoundException("Failed to create Address for Participation. Insuficient data."));
        participation.getAddresses().clear();
        if (!AddressType.BILLING_ADDRESS.equals(addressEntity.getAddressType())) {
            addressEntity.setAddressType(AddressType.BILLING_ADDRESS);
            participation.getAddresses().add(addressComponent.createParticipationAddress(addressEntity));
        } else {
            participation.getAddresses().add(addressEntity);
        }
        return Optional.ofNullable(dao.create(participation));
    }

    @Override
    public Optional<Participation> update(Participation participation) {
        return Optional.ofNullable(dao.update(participation));
    }

    @Override
    public void delete(Participation participation) {
        dao.delete(participation);
    }

    @Override
    public Optional<Participation> findById(Long id) {
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public Optional<Participation> findByEmail(String email) {
        return Optional.ofNullable(dao.findByEmail(email));
    }

    @Override
    public Optional<Participation> uploadInvoice(Long id, MultipartFile invoice) throws IOException {
        Participation part = dao.findById(id);

        byte[] imgBytes = invoice.getBytes();
        Path path = Paths.get(INVOICE_FOLDER + "invoice_" + part.getId());
        Files.write(path, imgBytes);

//        part.setInvoice64(encode(invoice));
        return Optional.ofNullable(dao.update(part));
    }

    @Override
    public Participation reprocess(Long id) {
        Participation part = findById(id).orElseThrow(() -> new NotFoundException("Failed to reprocess Participation. Couldn't find Participation with id: " + id));
        if (CampaignStatus.BAD_TRADER.ordinal() > part.getCampaignStatus().ordinal()) {
            if (part.getTriggeredCampaign() == null) {
                part = resolveCampaing(part);
                if (part.getCampaignStatus().equals(CampaignStatus.NO_CAMPAIGN)) {
                    return part;
                }
            }
            part = resolveDuplicity(part);
            if (part.getCampaignStatus().equals(CampaignStatus.DUPLICATED)) {
                return part;
            }
            part = resolveParticipationDates(part);
            if (part.getCampaignStatus().equals(CampaignStatus.BAD_REGISTRATION_DATE)
                    || part.getCampaignStatus().equals(CampaignStatus.BAD_PURCHASE_DATE)) {
                return part;
            }
            //TODO: Add mechanic when locale and trader policies are defined.
            // part = resolveLocale(part);
            // part = resolveTrader(part);

            emailComponent.sendQueueStatusMail(part);
            return addToValidationQueue(part);
        }
        if (CampaignStatus.VALID.equals(part.getCampaignStatus())) {
            emailComponent.sendValidStatusMail(part);
        }

        return part;
    }

    @Override
    public Optional<Participation> retrieveFromCorrectionQueue(Long campaignId) {
        Participation next = dao.findFromQueueByCampaign(campaignId);
        return Optional.ofNullable(next);
    }


    @Override
    public Participation uptadeVerification(Long id, CampaignViolations campaignViolations) {
        Participation part = findById(id).orElseThrow(() -> new NotFoundException("Failed to reprocess Participation. Couldn't find Participation with id: " + id));
        Set<ViolationType> violations = CampaignViolations.fromAttributes(campaignViolations);
        if (violations.isEmpty()) {
            part.setCampaignStatus(CampaignStatus.VALID);
            return update(part).orElseThrow();
        } else {
            correctionComponent.setupCorrection(part, violations);
            return part;
        }
    }

    @Override
    public Optional<Participation> correctData(Participation participation) {
        Participation result = update(participation).orElseThrow();
        correctionComponent.makeInvalid(result.getId());
        return Optional.ofNullable(result);
    }


    public Participation resolveCampaing(Participation part) {
        Campaign triggered = campaignComponent.trigger(part).orElse(null);
        if(triggered == null) {
            part.setCampaignStatus(CampaignStatus.NO_CAMPAIGN);
        } else {
            part.setTriggeredCampaign(triggered);
        }
        return dao.update(part);
    }

    public Participation resolveDuplicity(Participation part) {
        List<Participation> single = dao.findTwinParticipations(part);
        if (single.size()> 1) {
            part.setCampaignStatus(CampaignStatus.DUPLICATED);
            return dao.update(part);
        }
        return part;
    }

    private Participation resolveParticipationDates(Participation part) {
        boolean changedStatus = false;
        Campaign triggered = part.getTriggeredCampaign();
        boolean afterStart = triggered.getValidFrom().isBefore(part.getCreated().toLocalDateTime().toLocalDate());
        boolean beforeEnd = triggered.getValidUntil().isAfter(part.getCreated().toLocalDateTime().toLocalDate());
        boolean afterPurchaseStart = triggered.getPurchaseFrom().isBefore(part.getInvoiceDate());
        boolean beforePurchaseEnd = triggered.getPurchaseFrom().isAfter(part.getInvoiceDate());
        if (!afterStart || !beforeEnd) {
            part.setCampaignStatus(CampaignStatus.BAD_REGISTRATION_DATE);
            changedStatus = true;
        }
        if (!afterPurchaseStart || beforePurchaseEnd) {
            part.setCampaignStatus(CampaignStatus.BAD_PURCHASE_DATE);
            changedStatus = true;
        }
        if (changedStatus) {
            part = dao.update(part);
        }
        return part;

    }
    private Participation addToValidationQueue(Participation part) {
        part.setCampaignStatus(CampaignStatus.VALIDATION_QUEUE);
        return dao.update(part);
    }

//    private String encode(MultipartFile invoice) throws IOException {
//        byte[] fileContent = invoice.getBytes();
//        return Base64.getEncoder().encodeToString(fileContent);
//    }
//
//    private MultipartFile decode(String invoice64) throws IOException {
//        byte[] data = Base64.getDecoder().decode(invoice64);
//        try (OutputStream stream = new FileOutputStream("img/invoice.png")) {
//            stream.write(data);
//
//        }
//        return new CommonsMultipartFile(tempFile);
//    }
}
