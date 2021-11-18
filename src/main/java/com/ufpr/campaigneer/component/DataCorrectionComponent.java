package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.DataCorrectionDAO;
import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.model.DataCorrection;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.DataCorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 04/10/2021
 */

@Component
public class DataCorrectionComponent implements DataCorrectionService {

    DataCorrectionDAO dao = new DataCorrectionDAO();
    @Autowired
    ParticipationComponent participationComponent; //= new ParticipationComponent();

    public String setupCorrection(Participation part) {
        if (hasAttempts(part.getId())) {
            DataCorrection correction = new DataCorrection();
            correction.setCode(UUID.randomUUID().toString());
            correction.setParticipation(part);
            correction.setValid(true);
            DataCorrection created = dao.create(correction);
            return created.getCode();
        } else {
            part.setCampaignStatus(CampaignStatus.FINAL_INVALID);
            participationComponent.update(part);
            throw new ForbiddenException("This Participation already attempt the maximum number of corrections.");
        }
    }

    private boolean hasAttempts(Long id) {
        List<DataCorrection> attempts = dao.findCorrectionAttempts(id);
        return (3 >= attempts.size());
    }

    public boolean validateCode(String uuid) {
        DataCorrection correction = dao.findByCode(uuid);
        return correction.isValid();
    }

    @Override
    public Participation findByValidationCode(String uuid) {
        DataCorrection correction = dao.findByCode(uuid);
        if (correction.isValid()) {
            Participation valid = participationComponent.findById(correction.getParticipation().getId())
                    .orElseThrow(() -> new NotFoundException("No Participation eligible for correction found."));
            if (CampaignStatus.CORRECTION_QUEUE.equals(valid.getCampaignStatus())) {
                return valid;
            } else {
                throw new ForbiddenException("This Participation is not eligible for correction because it isn't in the Correction Queue.");
            }
        } else {
            throw new ForbiddenException("This Participation is not eligible for correction");
        }
    }

    @Override
    public void makeInvalid(String uuid) {
        DataCorrection correction = dao.findByCode(uuid);
        correction.setValid(false);
        dao.update(correction);
    }
}
