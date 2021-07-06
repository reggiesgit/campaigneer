package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Product;
import org.jvnet.hk2.annotations.Service;

import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@Service
public interface CampaignService {

    Campaign create(Campaign campaign);
    Campaign update(Campaign campaign);
    void delete(int id);

    Campaign findByCode(String code);
    Set<Campaign> findByBrand(int id);
    Set<Campaign> findByEan(String ean);
    Campaign addCampaignProducts(int id, Set<Product> products);
}
