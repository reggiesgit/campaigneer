package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import org.springframework.stereotype.Service;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 26/02/2021
 */

@Service
public interface AddressService {

    AddressCountry createCountry(AddressCountry country);
    AddressCountry updateCountry(AddressCountry country);
    void deleteCountry(AddressCountry county);
    AddressCountry findByCountryCode(String code);

    AddressState createState(AddressState state);
    AddressState updateState(AddressState state);
    void deleteState(AddressState state);
    AddressState findByStateCodeAndCountryCode(String stateCode, String countryCode);

    AddressCity createCity(AddressCity city);
    AddressCity updateCity(AddressCity city);
    void deleteCity(AddressCity city);
    AddressCity findByCityNameAndStateCode(String cityName, String stateCode);

    Address createAddress(Address address);
    Address updateAddress(Address address);
    void deleteAddress(Address address);

}
