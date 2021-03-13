package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.AddressCityJSON;
import com.ufpr.campaigneer.json.AddressCountryJSON;
import com.ufpr.campaigneer.json.AddressJSON;
import com.ufpr.campaigneer.json.AddressStateJSON;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
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

    @PutMapping("/updateCountry")
    public void updateCountry(@RequestBody AddressCountryJSON json) throws SQLException {
        try {
            logger.debug("Received request to update country with code: " + json.getCode());
            service.updateCountry(AddressCountryJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/deleteCountry")
    public void deleteCountry(@RequestBody AddressCountryJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete country with code: " + json.getCode());
            service.deleteCountry(AddressCountryJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @GetMapping("/country/{code}")
    public AddressCountryJSON findByCountryCode(@PathVariable(value="code") String code) {
        logger.debug("Received request to retrieve country with code: " + code);
        AddressCountry result = null;
        result = service.findByCountryCode(code);
        return AddressCountryJSON.map(result);
    }

    @PostMapping("/createState")
    public void createCountry(@RequestBody AddressStateJSON json) throws SQLException {
        try {
            logger.debug("Received request to create state with code: " + json.getCode());
            service.createState(AddressStateJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/updateState")
    public void updateState(@RequestBody AddressStateJSON json) throws SQLException {
        try {
            logger.debug("Received request to update state with code: " + json.getCode());
            service.updateState(AddressStateJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/deleteState")
    public void deleteState(@RequestBody AddressStateJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete state with code: " + json.getCode());
            service.deleteState(AddressStateJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @GetMapping("/state/{country}/{state}")
    public AddressStateJSON findByStateAndCountryCode(@PathVariable(value="state") String state, @PathVariable(value="country") String country) {
        logger.debug("Received request to retrieve state with code: " + state + " and country: " + country);
        AddressState result = null;
        result = service.findByStateCodeAndCountryCode(state, country);
        return AddressStateJSON.map(result);
    }

    @PostMapping("/createCity")
    public void createCity(@RequestBody AddressCityJSON json) throws SQLException {
        try {
            logger.debug("Received request to create city with name: " + json.getName());
            service.createCity(AddressCityJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/updateCity")
    public void updateCity(@RequestBody AddressCityJSON json) throws SQLException {
        try {
            logger.debug("Received request to update City with code: " + json.getName());
            service.updateCity(AddressCityJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/deleteCity")
    public void deleteCity(@RequestBody AddressCityJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete state with name: " + json.getName());
            service.deleteCity(AddressCityJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @GetMapping("/city/{state}/{city}")
    public AddressCityJSON findByCityAndStateCode(@PathVariable(value="state") String state, @PathVariable(value="name") String city) {
        logger.debug("Received request to retrieve city with name: " + city + " and state: " + state);
        AddressCity result = null;
        result = service.findByCityNameAndStateCode(city, state);
        return AddressCityJSON.map(result);
    }

    @PostMapping("/create")
    public void createAddress(@RequestBody AddressJSON json) throws SQLException {
        try {
            logger.debug("Received request to create address with postalcode: " + json.getPostalCode());
            service.createAddress(AddressJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @PutMapping("/update")
    public void updateAddress(@RequestBody AddressJSON json) throws SQLException {
        try {
            logger.debug("Received request to update address with postalcode: " + json.getPostalCode());
            service.updateAddress(AddressJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

    @DeleteMapping("/delete")
    public void deleteAddress(@RequestBody AddressJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete address with postalcode: " + json.getPostalCode());
            service.deleteAddress(AddressJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
    }

}