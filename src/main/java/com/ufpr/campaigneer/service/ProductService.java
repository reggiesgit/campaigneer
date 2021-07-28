package com.ufpr.campaigneer.service;

import com.ufpr.campaigneer.model.Product;
import org.jvnet.hk2.annotations.Service;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 03/07/2021
 */

@Service
public interface ProductService {

    Optional<Product> create(Product product);
    Optional<Product> update(Product product);
    void delete(Product product);

    Optional<Product> findByEAN(String ean);
}
