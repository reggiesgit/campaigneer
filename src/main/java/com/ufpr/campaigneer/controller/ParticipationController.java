package com.ufpr.campaigneer.controller;

import com.ufpr.campaigneer.json.ParticipationJSON;
import com.ufpr.campaigneer.json.VerificationJSON;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.service.DataCorrectionService;
import com.ufpr.campaigneer.service.InvoiceService;
import com.ufpr.campaigneer.service.ParticipationService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.io.IOException;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 23/08/2021
 */

@RestController
@RequestMapping("/participation")
public class ParticipationController {

    private static final String UNDERSCORE = "_";
    Logger logger = LoggerFactory.getLogger(ParticipationController.class);

    @Autowired
    @Qualifier("participationComponent")
    private ParticipationService service;

    @Autowired
    @Qualifier("dataCorrectionComponent")
    private DataCorrectionService correctionService;

    @Autowired
    @Qualifier("invoiceComponent")
    private InvoiceService invoiceService;

    @PostMapping("/")
    public ResponseEntity<ParticipationJSON> create(@RequestBody ParticipationJSON json) {
        logger.debug("Received request to create Participation for client: " + json.getEmail());
        Participation result = service.create(ParticipationJSON.mapJson(json)).orElse(null);
        Participation processed = service.reprocess(result.getId());
        return ResponseEntity.ok(ParticipationJSON.map(processed));
    }

    @PutMapping("/{id}/reprocess")
    public ResponseEntity<ParticipationJSON> reprocess(@PathVariable(value = "id") Long id) throws NotFoundException {
        logger.debug("Received request to reprocess Participation for client: " + id);
        Participation result = service.reprocess(id);
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ParticipationJSON> update(@PathVariable(value = "id") Long id, @RequestBody ParticipationJSON json) throws NotFoundException {
        logger.debug("Received request to update Participation for client: " + id);
        Participation result = service.update(ParticipationJSON.mapJson(json))
                .orElseThrow(() -> new NotFoundException("No Participation found for client: " + id));
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

    @PutMapping("/{id}/invoice")
    @Consumes("multipart/form-data")
    public ResponseEntity<ParticipationJSON> uploadInvoice(@PathVariable(value = "id") Long id, @RequestParam MultipartFile invoice) throws NotFoundException, IOException {
        logger.debug("Received request to update Participation with id: " + id);
        Participation part = service.findById(id).orElseThrow();
        part.setInvoicePath(invoiceService.resolveAndSave(part, invoice));
        Participation result = service.update(part).orElseThrow();
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

    @GetMapping("/validationQueue/{id}")
    public ResponseEntity<ParticipationJSON> getFromValidationQueue(@PathVariable(value = "id") Long campaignId) {
        logger.debug("Retrieving the next Participation on Validation Queue for Campaign with id:" + campaignId);
        Participation next = service.retrieveFromCorrectionQueue(campaignId).orElseThrow();
        return ResponseEntity.ok(ParticipationJSON.map(next));
    }

    @PutMapping("/{id}/evaluate")
    public ResponseEntity<ParticipationJSON> verify(@PathVariable(value = "id") Long id, @RequestBody VerificationJSON json) {
        logger.debug("Received evaluation for Participation with id: " + id);
        service.uptadeVerification(id, VerificationJSON.map(json));
        Participation result = service.reprocess(id);
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

    @PutMapping("/{validationCode}")
    public ResponseEntity<ParticipationJSON> dataCorrection(@PathVariable(value = "validationCode") String uuid, @RequestBody ParticipationJSON json) {
        logger.debug("Received correction for Participation with anonymous code: " + uuid);
        Long flagged = correctionService.findByValidationCode(uuid);
        service.correctData(ParticipationJSON.mapJson(json)).orElseThrow();
        Participation result = service.reprocess(flagged);
        return ResponseEntity.ok(ParticipationJSON.map(result));
    }

}