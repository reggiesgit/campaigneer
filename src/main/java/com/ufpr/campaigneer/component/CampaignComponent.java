package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.CampaignDAO;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.service.CampaignService;
import org.springframework.stereotype.Component;

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
    public void delete(Campaign campaign) {
        dao.delete(campaign);
    }

    @Override
    public Campaign findByCode(String code) {
        return dao.findByCode(code);
    }
}
