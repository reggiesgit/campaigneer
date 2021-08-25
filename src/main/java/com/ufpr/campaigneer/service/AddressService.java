package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 26/02/2021
 */

@Service
public interface AddressService {

    Optional<AddressCountry> createCountry(AddressCountry country);
    Optional<AddressCountry> updateCountry(AddressCountry country);
    void deleteCountry(AddressCountry county);
    Optional<AddressCountry> findByCountryCode(String code);

    Optional<AddressState> createState(AddressState state);
    Optional<AddressState> updateState(AddressState state);
    void deleteState(AddressState state);
    Optional<AddressState> findByStateCodeAndCountryCode(String stateCode, String countryCode);

    Optional<AddressCity> createCity(AddressCity city);
    Optional<AddressCity> updateCity(AddressCity city);
    void deleteCity(AddressCity city);
    Optional<AddressCity> findByCityNameAndStateCode(String cityName, String stateCode);

    Optional<Address> createAddress(Address address);
    Optional<Address> updateAddress(Address address);
    void deleteAddress(Address address);

    Optional<Address> findById(Long id);
    Optional<Address> findByPostalCodeAndNumber(String postalCode, int number);
}
