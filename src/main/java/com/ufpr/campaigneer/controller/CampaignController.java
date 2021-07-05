package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.CampaignJSON;
import com.ufpr.campaigneer.json.ProductJSON;
import com.ufpr.campaigneer.service.CampaignService;
import com.ufpr.campaigneer.service.ProductService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    @Qualifier("campaignComponent")
    private CampaignService service;

    @PostMapping("/create")
    public void create(@RequestBody CampaignJSON json) throws SQLException {
        try {
            logger.debug("Received request to create Campaign with name: " + json.getName());
            service.create(CampaignJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/update")
    public void update(@RequestBody CampaignJSON json) throws SQLException {
        try {
            logger.debug("Received request to update Campaign with name: " + json.getName());
            service.update(CampaignJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody CampaignJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete Campaign with name: " + json.getName());
            service.delete(CampaignJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }
}
