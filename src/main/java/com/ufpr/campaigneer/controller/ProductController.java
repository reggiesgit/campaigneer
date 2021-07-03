package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.BrandJSON;
import com.ufpr.campaigneer.json.ProductJSON;
import com.ufpr.campaigneer.service.BrandService;
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
 * 03/07/2021
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    @Qualifier("productComponent")
    private ProductService service;

    @PostMapping("/createCountry")
    public void createBrand(@RequestBody ProductJSON json) throws SQLException {
        try {
            logger.debug("Received request to create Product with name: " + json.getName());
            service.create(ProductJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/update")
    public void update(@RequestBody ProductJSON json) throws SQLException {
        try {
            logger.debug("Received request to update Product with name: " + json.getName());
            service.update(ProductJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody ProductJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete Product with name: " + json.getName());
            service.delete(ProductJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }


}
