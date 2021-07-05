package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Campaign;
import org.jvnet.hk2.annotations.Service;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@Service
public interface CampaignService {

    Campaign create(Campaign campaign);
    Campaign update(Campaign campaign);
    void delete(Campaign campaign);

    Campaign findByCode(String code);
}
