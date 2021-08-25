package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 03/07/2021
 */

@Component
public class ProductComponent implements ProductService {

    ProductDAO dao = new ProductDAO();

    @Override
    public Optional<Product> create(Product product) {
        return Optional.ofNullable(dao.create(product));
    }

    @Override
    public Optional<Product> update(Product product) {
        return Optional.ofNullable(dao.update(product));
    }

    @Override
    public void delete(Long id) {
        Product product =  dao.findById(id);
        dao.delete(product);
    }

    @Override
    public void delete(Product product) {
        dao.delete(product);
    }

    @Override
    public Optional<Product> findByEAN(String ean) {
        return Optional.ofNullable(dao.findByEAN(ean));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(dao.findById(id));
    }
}
