package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.AddressCityJSON;
import com.ufpr.campaigneer.json.AddressCountryJSON;
import com.ufpr.campaigneer.json.AddressJSON;
import com.ufpr.campaigneer.json.AddressStateJSON;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import com.ufpr.campaigneer.service.AddressService;
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
    public ResponseEntity<AddressCountryJSON> createCountry(@RequestBody AddressCountryJSON json) {
        logger.debug("Received request to create country with code: " + json.getCode());
        AddressCountry result = service.createCountry(AddressCountryJSON.map(json)).orElse(null);
        return ResponseEntity.ok(AddressCountryJSON.map(result));
    }

    @PutMapping("/updateCountry/{id}")
    public ResponseEntity<AddressCountryJSON> updateCountry(@PathVariable(value = "id") Long id, @RequestBody AddressCountryJSON json) throws NotFoundException {
        logger.debug("Received request to update country with code: " + json.getCode());
        AddressCountry result = service.updateCountry(AddressCountryJSON.map(json))
                .orElseThrow(() -> new NotFoundException("No country found with code: " + json.getCode()));
        return  ResponseEntity.ok(AddressCountryJSON.map(result));
    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<Long> deleteCountry(@PathVariable(value = "id") Long id, @RequestBody AddressCountryJSON json) throws SQLException {
        logger.debug("Received request to delete country with code: " + json.getCode());
        try {
            service.deleteCountry(AddressCountryJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok().body(json.getId());
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<AddressCountryJSON> findByCountryCode(@PathVariable(value="code") String code) throws NotFoundException {
        logger.debug("Received request to retrieve country with code: " + code);
        AddressCountry result = service.findByCountryCode(code)
                .orElseThrow(() -> new NotFoundException("No Country found with code: " + code));
        return ResponseEntity.ok(AddressCountryJSON.map(result));
    }

    @PostMapping("/createState")
    public ResponseEntity<AddressStateJSON> createState(@RequestBody AddressStateJSON json) {
        logger.debug("Received request to create state with code: " + json.getCode());
        AddressState result = service.createState(AddressStateJSON.map(json)).orElse(null);
        return ResponseEntity.ok(AddressStateJSON.map(result));
    }

    @PutMapping("/updateState/{id}")
    public ResponseEntity<AddressStateJSON> updateState(@PathVariable(value = "id") Long id, @RequestBody AddressStateJSON json) throws NotFoundException {
        logger.debug("Received request to update state with code: " + json.getCode());
        AddressState result = service.updateState(AddressStateJSON.map(json))
                .orElseThrow(() -> new NotFoundException("No state found with code: " + json.getCode()));
        return ResponseEntity.ok(AddressStateJSON.map(result));
    }

    @DeleteMapping("/deleteState/{id}")
    public ResponseEntity<Long> deleteState(@PathVariable(value = "id") Long id, @RequestBody AddressStateJSON json) throws SQLException {
        logger.debug("Received request to delete state with code: " + json.getCode());
        try {
            service.deleteState(AddressStateJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok().body(json.getId());
    }

    @GetMapping("/state/{country}/{state}")
    public ResponseEntity<AddressStateJSON> findByStateAndCountryCode(@PathVariable(value="state") String state, @PathVariable(value="country") String country) throws NotFoundException {
        logger.debug("Received request to retrieve state with code: " + state + " and country: " + country);
        AddressState result = service.findByStateCodeAndCountryCode(state, country)
                .orElseThrow(() -> new NotFoundException("No state found with code: " + state + " and country: " + country));;
        return ResponseEntity.ok(AddressStateJSON.map(result));
    }

    @PostMapping("/createCity")
    public ResponseEntity<AddressCityJSON> createCity(@RequestBody AddressCityJSON json) throws NotFoundException {
        logger.debug("Received request to create city with name: " + json.getName());
        AddressCity result = service.createCity(AddressCityJSON.map(json)).orElse(null);
        return ResponseEntity.ok(AddressCityJSON.map(result));
    }

    @PutMapping("/updateCity/{id}")
    public ResponseEntity<AddressCityJSON> updateCity(@PathVariable(value = "id") Long id, @RequestBody AddressCityJSON json) throws NotFoundException {
        logger.debug("Received request to update City with code: " + json.getName());
        AddressCity result = service.updateCity(AddressCityJSON.map(json))
                .orElseThrow(() -> new NotFoundException("No city found with code: " + json.getName()));
        return ResponseEntity.ok(AddressCityJSON.map(result));
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<Long> deleteCity(@PathVariable(value = "id") Long id, @RequestBody AddressCityJSON json) throws SQLException {
        logger.debug("Received request to delete state with name: " + json.getName());
        try {
            service.deleteCity(AddressCityJSON.map(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok(json.getId());
    }

    @GetMapping("/city/{state}/{city}")
    public ResponseEntity<AddressCityJSON> findByCityAndStateCode(@PathVariable(value="state") String state, @PathVariable(value="name") String city) throws NotFoundException {
        logger.debug("Received request to retrieve city with name: " + city + " and state: " + state);
        AddressCity result = null;
        result = service.findByCityNameAndStateCode(city, state)
                .orElseThrow(() -> new NotFoundException("No City found with name: " + city + " and state: " + state));
        return ResponseEntity.ok(AddressCityJSON.map(result));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressJSON> createAddress(@RequestBody AddressJSON json) throws NotFoundException {
        logger.debug("Received request to create address with postalcode: " + json.getPostalCode());
        Address result = service.createAddress(AddressJSON.mapJson(json)).orElse(null);
        return ResponseEntity.ok(AddressJSON.map(result));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<AddressJSON> updateAddress(@PathVariable(value = "id") Long id, @RequestBody AddressJSON json) throws NotFoundException {
        logger.debug("Received request to update address with postalcode: " + json.getPostalCode());
        Address  result = service.updateAddress(AddressJSON.mapJson(json))
                .orElseThrow(() -> new NotFoundException("No City found with address with postalcode: " + json.getPostalCode()));
        return ResponseEntity.ok(AddressJSON.map(result));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Long> deleteAddress(@PathVariable(value = "id") Long id, @RequestBody AddressJSON json) throws SQLException {
        try {
            logger.debug("Received request to delete address with postalcode: " + json.getPostalCode());
            service.deleteAddress(AddressJSON.mapJson(json));
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new SQLException(e);
            }
        }
        return ResponseEntity.ok(json.getId());
    }
}