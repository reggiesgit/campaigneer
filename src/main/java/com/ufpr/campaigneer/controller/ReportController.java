package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.CampaignReportJSON;
import com.ufpr.campaigneer.service.ParticipationService;
import com.ufpr.campaigneer.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 25/01/2022
 */

@RestController
@RequestMapping("/report")
public class ReportController {

    private static final String UNDERSCORE = "_";
    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    @Qualifier("reportComponent")
    private ReportService service;

    @GetMapping("/campaign/{id}")
    public ResponseEntity<CampaignReportJSON> simpleByCampaign(@PathVariable(value = "id") Long id) {
        logger.debug("Extracting simple report for Campaign with ID = " + id);
        CampaignReportJSON result = service.participationByCampaign(id);
        return ResponseEntity.ok(result);
    }
}
