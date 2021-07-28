package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import com.ufpr.campaigneer.service.AddressService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 27/02/2021
 */

@Component
public class AddressComponent implements AddressService {

    private AddressDAO dao = new AddressDAO();

    @Override
    public Optional<AddressCountry> createCountry(AddressCountry country) {
        return Optional.ofNullable(dao.createCountry(country));
    }

    @Override
    public Optional<AddressCountry> updateCountry(AddressCountry country) {
        return Optional.ofNullable(dao.updateCountry(country));
    }

    @Override
    public void deleteCountry(AddressCountry country) {
        dao.deleteCountry(country);
    }

    @Override
    public Optional<AddressCountry> findByCountryCode(String code) {
        return Optional.ofNullable(dao.findByCountryCode(code));
    }

    @Override
    public Optional<AddressState> createState(AddressState state) {
        return Optional.ofNullable(dao.createState(state));
    }

    @Override
    public Optional<AddressState> updateState(AddressState state) {
        return Optional.ofNullable(dao.updateState(state));
    }

    @Override
    public void deleteState(AddressState state) {
        dao.deleteState(state);
    }

    @Override
    public Optional<AddressState> findByStateCodeAndCountryCode(String stateCode, String countryCode) {
        return Optional.ofNullable(dao.findByStateCodeAndCountryCode(stateCode, countryCode));
    }

    @Override
    public Optional<AddressCity> createCity(AddressCity city) {
        return Optional.ofNullable(dao.createCity(city));
    }

    @Override
    public Optional<AddressCity> updateCity(AddressCity city) {
        return Optional.ofNullable(dao.updateCity(city));
    }

    @Override
    public void deleteCity(AddressCity city) {
        dao.deleteCity(city);
    }

    @Override
    public Optional<AddressCity> findByCityNameAndStateCode(String cityName, String stateCode) {
        return Optional.ofNullable(dao.findByCityNameAndStateCode(cityName, stateCode));
    }

    @Override
    public Optional<Address> createAddress(Address address) {
        return Optional.ofNullable(dao.createAddress(address));
    }

    @Override
    public Optional<Address> updateAddress(Address address) {
        return Optional.ofNullable(dao.updateAddress(address));
    }

    @Override
    public void deleteAddress(Address address) {
        dao.deleteAddress(address);
    }

}
