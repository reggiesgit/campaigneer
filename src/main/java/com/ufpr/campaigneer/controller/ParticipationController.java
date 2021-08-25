package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.ParticipationJSON;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.ParticipationService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 23/08/2021
 */

@RestController
@RequestMapping("/participation")
public class ParticipationController {

    Logger logger = LoggerFactory.getLogger(ParticipationController.class);

    @Autowired
    @Qualifier("participationComponent")
    private ParticipationService service;

    @PostMapping("/")
    public ResponseEntity<ParticipationJSON> create(@RequestBody ParticipationJSON json) {
        logger.debug("Received request to create Participation for client: " + json.getEmail());
        Participation result = service.create(ParticipationJSON.mapJson(json)).orElse(null);
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

    @PutMapping("/{id}/")
    public ResponseEntity<ParticipationJSON> update(@PathVariable(value = "id") Long id, @RequestBody ParticipationJSON json) throws NotFoundException {
        logger.debug("Received request to update Participation for client: " + id);
        Participation result = service.update(ParticipationJSON.mapJson(json))
                .orElseThrow(() -> new NotFoundException("No Participation found for client: " + json.getEmail()));
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

    @PutMapping("/{id}/invoice")
    public ResponseEntity<ParticipationJSON> uploadInvoice(@PathVariable(value = "id") Long id, @RequestBody MultipartFile invoice) {
        logger.debug("Received request to update Participation for client: " + id);
        return null;
    }

}