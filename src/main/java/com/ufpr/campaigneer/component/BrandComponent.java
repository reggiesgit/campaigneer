package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.service.BrandService;
import org.springframework.stereotype.Component;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

@Component
public class BrandComponent implements BrandService {

    BrandDAO dao = new BrandDAO();

    @Override
    public Brand create(Brand brand) {
        return dao.create(brand);
    }

    @Override
    public Brand update(Brand brand) {
        return dao.update(brand);
    }

    @Override
    public void delete(Brand brand) {
        dao.delete(brand);
    }

    @Override
    public Brand findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public Brand findByNameAndCountryName(String name, String country) {
        return dao.findByNameAndCountryName(name, country);
    }
}
