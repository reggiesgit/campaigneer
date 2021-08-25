package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Brand;
import org.jvnet.hk2.annotations.Service;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

@Service
public interface BrandService {

    Optional<Brand> create(Brand brand);
    Optional<Brand> update(Brand brand);
    void delete(Brand brand);

    Optional<Brand> findById(Long id);
    Optional<Brand> findByName(String name);
    Optional<Brand> findByNameAndCountryCode(String name, String countryCode);
}
