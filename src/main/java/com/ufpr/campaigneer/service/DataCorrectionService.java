package com.ufpr.campaigneer.service;


import org.springframework.stereotype.Service;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 11/10/2021
 */

@Service
public interface DataCorrectionService {

    Long findByValidationCode(String validationCode);
}
