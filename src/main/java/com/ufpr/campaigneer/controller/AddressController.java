package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.AddressCountryJSON;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.service.AddressService;
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
 * 06/03/2021
 */

@RestController
@RequestMapping("/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    @Qualifier("addressComponent")
    private AddressService service;

    @PostMapping("/createCountry")
    public void createCountry(@RequestBody AddressCountryJSON json) throws SQLException {
        try {
            logger.debug("Received request to create country with code: " + json.getCode());
            service.createCountry(AddressCountryJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @GetMapping("/{code}")
    public AddressCountryJSON findCountryByCode(@PathVariable(value="code") String code) {
        logger.debug("Received request to retrieve country with code: " + code);
        AddressCountry result = null;
        result = service.findCountryByCode(code);
        return AddressCountryJSON.map(result);
    }
}