package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.dao.CampaignDAO;
import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.enums.CampaignType;
import com.ufpr.campaigneer.model.*;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 04/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CampaignTester {

    private CampaignDAO dao = new CampaignDAO();
    private ProductDAO productDAO = new ProductDAO();
    private AddressDAO addressDAO = new AddressDAO();
    private AddressTester addressHelper = new AddressTester();
    private ProductTester productHelper = new ProductTester();
    private BrandTester brandHelper = new BrandTester();

    @Test
    @Order(1)
    public void setUpBrand() throws SQLException, NotFoundException {
        productHelper.setUpBrand();
        brandHelper.createBranch();
    }

    @Test
    @Order(2)
    public void setUpProduct() {
        productHelper.createProduct();
        productHelper.updateProduct();
        productHelper.createAnotherProduct();
    }

    @Test
    @Order(3)
    public void createCampaign() {
        Product product = productDAO.findByEAN("TADSSEPTUFPR");
        assertNotNull(product.getManufacturer().getAddresses());
        Address local = product.getManufacturer().findMainAddress();
        assertNotNull(local);

        Campaign campaign = new Campaign();
        campaign.setName("Engenharia de Software");
        campaign.setCode("2020_UFPR_ENG_SOFTWARE");
        campaign.setCampaignType(CampaignType.WARRANTY);
        campaign.setValidFrom(LocalDate.of(2020, 03, 06));
        campaign.setValidUntil(LocalDate.of(2021, 10, 30));
        campaign.setPurchaseFrom(LocalDate.of(2020, 03, 06));
        campaign.setPurchaseUntil(LocalDate.of(2021, 10, 30));
        Set<AddressCountry> locations = new HashSet<>();
        locations.add(local.getCity().getState().getCountry());
        campaign.setValidLocations(locations);
        campaign.setPromoter(product.getManufacturer());
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);
        campaign.setParticipatingProducts(productSet);

        Campaign created = dao.create(campaign);
        assertNotNull(created);
        assertNotNull(created.getPromoter());
        assertNotNull(created.getValidLocations());
        assertNotNull(created.getParticipatingProducts());
    }

    @Test
    @Order(4)
    public void breakCampaignCodeForeignKey() {
        Campaign repeatedCode = new Campaign();
        Campaign campaign = new Campaign();
        campaign.setName("Engenharia de Software");
        campaign.setCode("2020_UFPR_ENG_SOFTWARE");
        campaign.setCampaignType(CampaignType.WARRANTY);

        assertThrows(ConstraintViolationException.class, () -> {
            Campaign created = dao.create(repeatedCode);
        });
    }

    @Test
    @Order(5)
    public void updateCampaign() {
        Campaign original = dao.findByCode("2020_UFPR_ENG_SOFTWARE");
        assertNotNull(original);
        Campaign pirate = Campaign.copy(original);

        pirate.setName("Eng. Software 2020");
        Campaign current = dao.update(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(6)
    public void addCampaignProduct() {
        Campaign original = dao.findByCode("2020_UFPR_ENG_SOFTWARE");
        assertNotNull(original);
        int originalSize = original.getParticipatingProducts().size();
        original.getParticipatingProducts().add(productDAO.findByEAN("UFPRSEPTTACS"));

        Campaign current = dao.update(original);
        assertTrue(current.getParticipatingProducts().size() > originalSize);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(7)
    public void addCampaignLocation() throws NotFoundException {
        Campaign original = dao.findByCode("2020_UFPR_ENG_SOFTWARE");
        assertNotNull(original);
        int originalSize = original.getValidLocations().size();
        original.getValidLocations().add(addressDAO.findByCountryCode("AR"));

        Campaign current = dao.update(original);
        assertTrue(current.getValidLocations().size() > originalSize);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(8)
    public void removeCampaignAttributes() {
        Campaign original = dao.findByCode("2020_UFPR_ENG_SOFTWARE");
        Campaign pirate = Campaign.copy(original);
        pirate.setValidLocations(null);
        pirate.setParticipatingProducts(null);

        Campaign current = dao.update(pirate);
        assertNull(current.getParticipatingProducts());
        assertNull(current.getValidLocations());

        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(9)
    public void deleteCampaign() {
        Campaign original = dao.findByCode("2020_UFPR_ENG_SOFTWARE");
        assertNotNull(original);

        dao.delete(original);
        assertNull(dao.findByCode("2020_UFPR_ENG_SOFTWARE"));
    }

    @Test
    @Order(10)
    public void setDownProducts() {
        productHelper.deleteProduct();
        productHelper.deleteAnotherProduct();
        productHelper.removeProduct();
        productHelper.removeAnotherProduct();
    }

    @Test
    @Order(11)
    public void removeCampaign() {
        Campaign original = dao.findDeletedByCode("2020_UFPR_ENG_SOFTWARE");
        assertNotNull(original);

        dao.remove(original);
        assertNull(dao.findDeletedByCode("2020_UFPR_ENG_SOFTWARE"));
    }

    @Test
    @Order(12)
    public void setDownBrandAndBranch() throws SQLException {
        brandHelper.deleteBrand();
        brandHelper.removeBrandAddress();
        addressHelper.removeAnotherAddress();
        brandHelper.setDownAddresses();
        brandHelper.removeBrand();
    }
}
