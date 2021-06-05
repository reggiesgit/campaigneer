package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.*;
import org.junit.jupiter.api.*;
import org.springframework.test.context.jdbc.Sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/06/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql({"/static/createAddress.sql"})
public class BrandTester {

    BrandDAO dao = new BrandDAO();
    AddressDAO addressDAO = new AddressDAO();

    @Test
    @Order(1)
    public void createBrand() throws SQLException {
        Address main = new Address();
        main = addressDAO.findByPostalCodeAndNumber("88008-888", 1225);
        List<Address> brandAddresses = new ArrayList<>();
        brandAddresses.add(main);
        Brand brand = new Brand();
        brand.setName("TMBG");
        Brand created = (Brand) dao.createBrand(brand);
        assertTrue(created.getId() > 0);
    }
}
