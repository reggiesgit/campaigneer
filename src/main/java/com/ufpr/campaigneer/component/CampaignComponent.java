package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.CampaignDAO;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.service.CampaignService;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@Component
public class CampaignComponent implements CampaignService {

    CampaignDAO dao = new CampaignDAO();
    BrandComponent brandComponent = new BrandComponent();
    ProductComponent productComponent = new ProductComponent();

    @Override
    public Optional<Campaign> create(Campaign campaign) {
        campaign.setPromoter(brandComponent.findByName(campaign.getPromoter().getName())
                .orElseThrow(() -> new NotFoundException("Failed to map Campaign. Promoter is missing.")));
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

    @Override
    public Optional<Campaign> updateProducts(Long id, Set<Product> products) {
        Campaign campaign = findByid(id).orElseThrow(() -> new NotFoundException("Failed to update Products. Couldn't find Campaign with id: " + id));
        campaign.getParticipatingProducts().addAll(productComponent.getProductEntities(products));
        return Optional.ofNullable(dao.update(campaign));
    }

    @Override
    public Optional<Campaign> updateLocations(List<Address> locations) {
        return Optional.empty();
    }

    public Optional<Campaign> trigger(Participation part) {
        return Optional.ofNullable(dao.findForParticipation(part));
    }
}
