package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.BrandJSON;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.service.BrandService;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    public ResponseEntity<BrandJSON> createBrand(@RequestBody BrandJSON json) throws NotFoundException {
        logger.debug("Received request to create Brand with name: " + json.getName());
        if (json.getAddresses() == null) {
            throw new NotFoundException("Failed to map Brand. Address is missing.");
        }
        Brand result = service.create(BrandJSON.mapJson(json)).orElse(null);
        return ResponseEntity.ok(BrandJSON.map(result));
    }

    @PutMapping("/{id}/")
    public ResponseEntity<BrandJSON> update(@PathVariable(value = "id") Long id, @RequestBody BrandJSON json) throws SQLException, NotFoundException {
        logger.debug("Received request to update Brand with name: " + json.getName());
        Brand result = service.update(BrandJSON.mapJson(json))
                .orElseThrow(() -> new NotFoundException("No Brand found with name: " + json.getName()));
        return ResponseEntity.ok(BrandJSON.map(result));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id, @RequestBody BrandJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete Brand with name: " + json.getName());
            service.delete(BrandJSON.mapJson(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok(json.getId());
    }
}
