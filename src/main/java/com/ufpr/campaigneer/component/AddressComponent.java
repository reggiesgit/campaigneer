package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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
    public AddressCountry findCountryByCode(String code) {
        return dao.findCountryByCode(code);
    }

}
