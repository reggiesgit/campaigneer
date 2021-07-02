package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.BrandJSON;
import com.ufpr.campaigneer.service.BrandService;
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
 * 02/07/2021
 */

@RestController
@RequestMapping("/brand")
public class BrandController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    @Qualifier("brandComponent")
    private BrandService service;

    @PostMapping("/createCountry")
    public void createBrand(@RequestBody BrandJSON json) throws SQLException {
        try {
            logger.debug("Received request to create Brand with name: " + json.getName());
            service.create(BrandJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/update")
    public void update(@RequestBody BrandJSON json) throws SQLException {
        try {
            logger.debug("Received request to update Brand with name: " + json.getName());
            service.update(BrandJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody BrandJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete Brand with name: " + json.getName());
            service.delete(BrandJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }
}
