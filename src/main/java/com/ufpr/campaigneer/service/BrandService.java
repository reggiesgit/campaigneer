package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Brand;
import org.jvnet.hk2.annotations.Service;

import java.sql.SQLException;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

@Service
public interface BrandService {

    Brand create(Brand brand);
    Brand update(Brand brand);
    void delete(Brand brand);

    Brand findByName(String name);
    Brand findByNameAndCountryName(String name, String country);
}
