package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.ProductJSON;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.service.ProductService;
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
 * 03/07/2021
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    @Qualifier("productComponent")
    private ProductService service;

    @PostMapping("/")
    public ResponseEntity<ProductJSON> create(@RequestBody ProductJSON json) throws SQLException, NotFoundException {
        logger.debug("Received request to create Product with ean: " + json.getEan());
        Product result = service.create(ProductJSON.mapJson(json)).orElse(null);
        return ResponseEntity.ok(ProductJSON.map(result));
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ProductJSON> update(@PathVariable(value = "id") Long id, @RequestBody ProductJSON json) throws NotFoundException {
        logger.debug("Received request to update Product with ean: " + json.getEan());
        Product result = service.update(ProductJSON.mapJson(json))
                .orElseThrow(() -> new NotFoundException("No Product found with ean: " + json.getEan()));
        return ResponseEntity.ok(ProductJSON.map(result));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) throws SQLException {
        try {
            logger.debug("Received request to delete Product with id: " + id);
            service.delete(id);
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok(id);
    }
}
