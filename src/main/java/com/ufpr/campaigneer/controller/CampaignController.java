package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.CampaignJSON;
import com.ufpr.campaigneer.json.ProductJSON;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.service.CampaignService;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

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

    @PostMapping("/")
    public ResponseEntity<CampaignJSON> create(@RequestBody CampaignJSON json) throws SQLException {
        logger.debug("Received request to create Campaign with name: " + json.getName());
        Campaign created = service.create(CampaignJSON.mapJson(json)).orElse(null);
        return ResponseEntity.ok(CampaignJSON.map(created));
    }

    @PutMapping("/{id}/")
    public ResponseEntity<CampaignJSON> update(@PathVariable(value = "id") Long id, @RequestBody CampaignJSON json) throws SQLException, NotFoundException {
        logger.debug("Received request to update Campaign with id: " + id);
        Campaign result = service.update(CampaignJSON.mapJson(json))
                .orElseThrow(() -> new NotFoundException("No Campaign found with name: " + json.getCode()));
        return ResponseEntity.ok(CampaignJSON.map(result));
    }

    @PutMapping("/{id}/products/")
    public ResponseEntity<CampaignJSON> addProducts(@PathVariable(value = "id") Long id, @RequestBody Set<ProductJSON> products) throws NotFoundException {
        logger.debug("Received request to add Products to Campaign with id: " + id);
        Campaign result = service.updateProducts(id, ProductJSON.mapJson(products))
                .orElseThrow(() -> new NotFoundException("No Campaign found with id: " + id));
        return ResponseEntity.ok(CampaignJSON.map(result));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id, @RequestBody CampaignJSON json) throws SQLException {
        logger.debug("Received request to delete Campaign with id: " + id);
        try {
            service.delete(CampaignJSON.mapJson(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok(json.getId());
    }
}
