package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.service.ProductService;
import org.springframework.stereotype.Component;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 03/07/2021
 */

@Component
public class ProductComponent implements ProductService {

    ProductDAO dao = new ProductDAO();

    @Override
    public Product create(Product product) {
        return dao.create(product);
    }

    @Override
    public Product update(Product product) {
        return dao.update(product);
    }

    @Override
    public void delete(Product product) {
        dao.delete(product);
    }

    @Override
    public Product findByEAN(String ean) {
        return dao.findByEAN(ean);
    }
}
