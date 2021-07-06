package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.CampaignDAO;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.service.CampaignService;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@Component
public class CampaignComponent implements CampaignService {

    CampaignDAO dao = new CampaignDAO();

    @Override
    public Campaign create(Campaign campaign) {
        return dao.create(campaign);
    }

    @Override
    public Campaign update(Campaign campaign) {
        return dao.update(campaign);
    }

    @Override
    public void delete(int id) {
        Campaign toDelete = dao.findById(id);
        dao.delete(toDelete);
    }

    @Override
    public Campaign findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public Set<Campaign> findByBrand(int id) {
        return dao.findByBrand(id);
    }

    @Override
    public Set<Campaign> findByEan(String ean) {
        return dao.findByEan(ean);
    }

    @Override
    public Campaign addCampaignProducts(int id, Set<Product> products) {
        Campaign campaign = dao.findById(id);
        campaign.getParticipatingProducts().addAll(products);
        dao.update(campaign);
        return campaign;
    }
}
