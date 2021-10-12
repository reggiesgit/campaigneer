package com.ufpr.campaigneer.component;

import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.model.Product;
import com.ufpr.campaigneer.service.ProductService;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 03/07/2021
 */

@Component
public class ProductComponent implements ProductService {

    ProductDAO dao = new ProductDAO();
    BrandComponent brandComponent = new BrandComponent();

    @Override
    public Optional<Product> create(Product product) {
        product.setManufacturer(brandComponent.findByName(product.getManufacturer().getName())
                .orElseThrow(() -> new NotFoundException("Failed to map Product. Manufacturer is missing.")));
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

    public Set<Product> getProductEntities(List<Product> products) {
        Set<Product> found = new HashSet<>();
        products.forEach(each -> {
            found.add(findByEAN(each.getEan())
                    .orElseThrow(() -> new NotFoundException("Failed to add Product to Campaign. Couldn't find product with ean: " + each.getEan())));
        });
        return found;
    }
}
