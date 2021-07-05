package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.enums.ClassOfGood;
import com.ufpr.campaigneer.model.Product;
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
    private BrandDAO brandDAO = new BrandDAO();
    private BrandTester brandHelper = new BrandTester();

    @Test
    @Order(1)
    public void setUpBrand() throws SQLException {
        brandHelper.setUpAddresses();
        brandHelper.createAddress();
        brandHelper.createBrand();
        brandHelper.updateBrand();
    }

    @Test
    @Order(2)
    public void createProduct() {
        Product product = new Product();
        product.setName("TADS");
        product.setEan("UFPRSEPTTADS");
        product.setClassOfGood(ClassOfGood.LAPTOP);
        product.setManufacturer(brandDAO.findByName("Universidade Federal PR"));
        assertTrue(dao.create(product).getId() > 0);
    }

    @Test
    @Order(3)
    public void updateProduct() {
        Product original = dao.findByEAN("UFPRSEPTTADS");
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
    }

    @Test
    @Order(4)
    public void createAnotherProduct() {
        Product product = new Product();
        product.setName("TACS");
        product.setEan("UFPRSEPTTACS");
        product.setClassOfGood(ClassOfGood.LAPTOP);
        product.setManufacturer(brandDAO.findByName("Universidade Federal PR"));
        assertTrue(dao.create(product).getId() > 0);
    }

    @Test
    @Order(4)
    public void findByEAN() {
        Product toRemove = dao.findByEAN("TADSSEPTUFPR");
        assertNotNull(toRemove);
    }

    @Test
    @Order(5)
    public void deleteProduct() {
        Product toRemove = dao.findByEAN("TADSSEPTUFPR");
        assertNotNull(toRemove);

        dao.delete(toRemove);
        assertNotNull(dao.findDeletedByEAN("TADSSEPTUFPR"));
    }

    @Test
    @Order(6)
    public void deleteAnotherProduct() {
        Product toRemove = dao.findByEAN("UFPRSEPTTACS");
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
    public void setDownBrand() throws SQLException {
        brandHelper.deleteBrand();
        brandHelper.removeBrandAddress();
        brandHelper.removeBrand();
        brandHelper.setDownAddresses();
    }
}
