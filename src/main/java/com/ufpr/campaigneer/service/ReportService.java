package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.json.CampaignReportJSON;
import org.springframework.stereotype.Service;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 25/01/2022
 */

@Service
public interface ReportService {

    CampaignReportJSON participationByCampaign(Long campaignId);
}
