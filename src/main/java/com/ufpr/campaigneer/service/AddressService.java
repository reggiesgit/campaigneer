package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.AddressCountry;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 26/02/2021
 */

@Service
public interface AddressService {

    AddressCountry createCountry(AddressCountry country);
    AddressCountry updateCountry(AddressCountry country);
    void deleteCountry(AddressCountry toRemove);
    AddressCountry findCountryByCode(String code);

}
