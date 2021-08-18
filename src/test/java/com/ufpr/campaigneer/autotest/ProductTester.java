package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.component.ProductComponent;
import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.enums.ClassOfGood;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.model.Product;
import javassist.NotFoundException;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTester {

    private ProductDAO dao = new ProductDAO();
    private ProductComponent component = new ProductComponent();
    private BrandDAO brandDAO = new BrandDAO();
    private BrandTester brandHelper = new BrandTester();

    public Product defaultProduct() {
        Product product = component.findByEAN("UFPRSEPTTADS")
                .orElse(new Product("TADS", "UFPRSEPTTADS", ClassOfGood.LAPTOP, brandHelper.defaultBrand()));
        if (product.getId() > 0) {
            return product;
        }
        return component.create(product).orElse(null);
    }

    @Test
    @Order(1)
    public void createProduct() {
        assertTrue(defaultProduct().getId() > 0);
    }

    @Test
    @Order(2)
    public void updateProduct() {
        Product original = component.findByEAN("UFPRSEPTTADS").orElse(null);
        assertNotNull(original);
        assertTrue(original.getId() > 0);

        Product pirate = new Product();
        pirate.setId(original.getId());
        pirate.setName(original.getName());
        pirate.setCreated(original.getCreated());
        pirate.setManufacturer(original.getManufacturer());
        pirate.setClassOfGood(original.getClassOfGood());
        pirate.setEan("TADSSEPTUFPR");

        Product current = dao.update(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());

        dao.update(original);
    }

    @Test
    @Order(3)
    public void createAnotherProduct() {
        Product product = new Product();
        product.setName("TACS");
        product.setEan("UFPRSEPTTACS");
        product.setClassOfGood(ClassOfGood.LAPTOP);
        product.setManufacturer(brandDAO.findByName("UFPR"));
        assertTrue(component.create(product).orElse(null).getId() > 0);
    }

    @Test
    @Order(4)
    public void findByEAN() {
        Product toRemove = component.findByEAN("TADSSEPTUFPR").orElse(null);
        assertNotNull(toRemove);
    }

    @Test
    @Order(5)
    public void deleteProduct() {
        Product toRemove = component.findByEAN("TADSSEPTUFPR").orElse(null);
        assertNotNull(toRemove);

        component.delete(toRemove);
        assertNotNull(dao.findDeletedByEAN("TADSSEPTUFPR"));
    }

    @Test
    @Order(6)
    public void deleteAnotherProduct() {
        Product toRemove = component.findByEAN("UFPRSEPTTACS").orElse(null);
        assertNotNull(toRemove);

        dao.delete(toRemove);
        assertNotNull(dao.findDeletedByEAN("UFPRSEPTTACS"));
    }

    @Test
    @Order(7)
    public void removeProduct() {
        Product toRemove = dao.findDeletedByEAN("TADSSEPTUFPR");
        assertNotNull(toRemove);

        dao.remove(toRemove);
        assertNull(dao.findDeletedByEAN("TADSSEPTUFPR"));
    }

    @Test
    @Order(8)
    public void removeAnotherProduct() {
        Product toRemove = dao.findDeletedByEAN("UFPRSEPTTACS");
        assertNotNull(toRemove);

        dao.remove(toRemove);
        assertNull(dao.findDeletedByEAN("UFPRSEPTTACS"));
    }

    @Test
    @Order(9)
    public void setDownBrand() {
        brandHelper.deleteBrand();
        brandHelper.removeBrandAddress();
        brandHelper.removeBrand();
        brandHelper.setDownAddresses();
    }
}
