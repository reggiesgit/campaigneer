package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.service.BrandService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

@Component
public class BrandComponent implements BrandService {

    BrandDAO dao = new BrandDAO();

    @Override
    public Optional<Brand> create(Brand brand) {
        return Optional.ofNullable(dao.create(brand));
    }

    @Override
    public Optional<Brand> update(Brand brand) {
        return Optional.ofNullable(dao.update(brand));
    }

    @Override
    public void delete(Brand brand) {
        dao.delete(brand);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(dao.findById(id));
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return Optional.ofNullable(dao.findByName(name));
    }

    @Override
    public Optional<Brand> findByNameAndCountryCode(String name, String country) {
        return Optional.ofNullable(dao.findByNameAndCountryCode(name, country));
    }
}
