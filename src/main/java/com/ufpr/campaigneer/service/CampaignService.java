package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Product;
import org.jvnet.hk2.annotations.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@Service
public interface CampaignService {

    Optional<Campaign> create(Campaign campaign);
    Optional<Campaign> update(Campaign campaign);
    void delete(Campaign campaign);

    Optional<Campaign> findByid(Long id);
    Optional<Campaign> findByCode(String code);

    Optional<Campaign> updateProducts(Long id, List<Product> products);
    Optional<Campaign> updateLocations(List<Address> locations);
}
