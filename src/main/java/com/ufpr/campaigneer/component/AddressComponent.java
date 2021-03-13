package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import com.ufpr.campaigneer.service.AddressService;
import org.springframework.stereotype.Component;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 27/02/2021
 */

@Component
public class AddressComponent implements AddressService {

    private AddressDAO dao = new AddressDAO();

    @Override
    public AddressCountry createCountry(AddressCountry country) {
        return dao.createCountry(country);
    }

    @Override
    public AddressCountry updateCountry(AddressCountry country) {
        return dao.updateCountry(country);
    }

    @Override
    public void deleteCountry(AddressCountry country) {
        dao.deleteCountry(country);
    }

    @Override
    public AddressCountry findByCountryCode(String code) {
        return dao.findByCountryCode(code);
    }

    @Override
    public AddressState createState(AddressState state) {
        return dao.createState(state);
    }

    @Override
    public AddressState updateState(AddressState state) {
        return dao.updateState(state);
    }

    @Override
    public void deleteState(AddressState state) {
        dao.deleteState(state);
    }

    @Override
    public AddressState findByStateCodeAndCountryCode(String stateCode, String countryCode) {
        return dao.findByStateCodeAndCountryCode(stateCode, countryCode);
    }

    @Override
    public AddressCity createCity(AddressCity city) {
        return dao.createCity(city);
    }

    @Override
    public AddressCity updateCity(AddressCity city) {
        return dao.updateCity(city);
    }

    @Override
    public void deleteCity(AddressCity city) {
        dao.deleteCity(city);
    }

    @Override
    public AddressCity findByCityNameAndStateCode(String cityName, String stateCode) {
        return dao.findByCityNameAndStateCode(cityName, stateCode);
    }

    @Override
    public Address createAddress(Address address) {
        return dao.createAddress(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return dao.updateAddress(address);
    }

    @Override
    public void deleteAddress(Address address) {
        dao.deleteAddress(address);
    }

}
