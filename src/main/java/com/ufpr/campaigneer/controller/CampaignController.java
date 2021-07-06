package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.CampaignJSON;
import com.ufpr.campaigneer.json.ProductJSON;
import com.ufpr.campaigneer.service.CampaignService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
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

    @GetMapping("/")
    public CampaignJSON findById(@PathVariable(value="code") String code) throws Exception {
        try {
            logger.debug("Received request to find Campaign by code: " + code);
            return CampaignJSON.map(service.findByCode(code));
        } catch(Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @GetMapping("/{brand}")
    public List<CampaignJSON> findByBrand(@PathVariable(value="brand") int brand) throws Exception {
        try {
            logger.debug("Received request to find Campaign by brand: " + brand);
            return CampaignJSON.map(service.findByBrand(brand));

        } catch(Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @GetMapping("/{ean}")
    public List<CampaignJSON> findByProduct(@PathVariable(value="ean") String ean) throws Exception {
        try {
            logger.debug("Received request to find Campaign by ean: " + ean);
            return CampaignJSON.map(service.findByEan(ean));
        } catch(Exception e) {
            throw new Exception(e.getCause());
        }
    }



    @PutMapping("/{id}/update")
    public void update(@PathVariable(value="id") int id, @RequestBody CampaignJSON json) throws SQLException {
        try {
            logger.debug("Received request to update Campaign with name: " + json.getName());
            service.update(CampaignJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/{id}/addProducts")
    public void addCampaignProducts(@PathVariable(value="id") int id, @RequestBody Set<ProductJSON> products) throws Exception {
        try {
            logger.debug("Received request to add Products to Campaign with id: " + id);
            service.addCampaignProducts(id, ProductJSON.map(products));
        } catch(Exception e) {
            throw new Exception(e.getCause());
        }
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable(value="id") int id) throws SQLException {
        try {
            logger.debug("Received request to delete Campaign with id: " + id);
            service.delete(id);
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }
}
