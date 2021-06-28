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

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    private BrandDAO dao = new BrandDAO();
    private AddressDAO addressDAO = new AddressDAO();
    private AddressTester addressHelper = new AddressTester();

    @Test
    @Order(1)
    public void setUpAddresses() throws SQLException {
        addressHelper.createCountry();
        addressHelper.createState();
        addressHelper.createCity();
    }

    @Test
    @Order(2)
    public void createAddress() throws  SQLException {
        Address address = new Address();
        AddressCity city = addressDAO.findByCityNameAndStateCode("Curitiba", "PR");
        assertNotNull(city.getName());

        address.setCity(city);
        address.setAddressType(AddressType.BRAND_MAIN);
        address.setComplement("SEPT");
        address.setStreetName("Dr. Alcides Arcoverde");
        address.setStreetNumber(1225);
        address.setPostalCode("88088-888");

        Address created = (Address) addressDAO.createAddress(address);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(3)
    public void createBrand() throws SQLException {
        Address main = new Address();
        main = addressDAO.findByPostalCodeAndNumber("88008-888", 1225);
        assertTrue(main.getId() > 0);
        List<Address> brandAddresses = new ArrayList<>();
        brandAddresses.add(main);
        Brand brand = new Brand();
        brand.setName("UFPR");
        Brand created = (Brand) dao.createBrand(brand);
        assertTrue(created.getId() > 0);
    }
}
