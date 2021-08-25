package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.CampaignDAO;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.service.CampaignService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@Component
public class CampaignComponent implements CampaignService {

    CampaignDAO dao = new CampaignDAO();

    @Override
    public Optional<Campaign> create(Campaign campaign) {
        return Optional.ofNullable(dao.create(campaign));
    }

    @Override
    public Optional<Campaign> update(Campaign campaign) {
        return Optional.ofNullable(dao.update(campaign));
    }

    @Override
    public void delete(Campaign campaign) {
        dao.delete(campaign);
    }

    @Override
    public Optional<Campaign> findByid(Long id) {
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public Optional<Campaign> findByCode(String code) {
        return Optional.ofNullable(dao.findByCode(code));
    }
}
