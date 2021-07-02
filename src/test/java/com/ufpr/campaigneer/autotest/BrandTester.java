package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.*;
import org.junit.jupiter.api.*;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        main = addressDAO.findByPostalCodeAndNumber("88088-888", 1225);
        assertTrue(main.getId() > 0);
        Set<Address> brandAddresses = new HashSet<>();
        brandAddresses.add(main);
        Brand brand = new Brand();
        brand.setName("UFPR");
        brand.setAddresses(brandAddresses);
        Brand created = (Brand) dao.create(brand);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(4)
    public void createAnotherBrand() throws SQLException {
        Address main = new Address();
        main = addressDAO.findByPostalCodeAndNumber("88088-888", 1225);
        assertTrue(main.getId() > 0);
        Set<Address> brandAddresses = new HashSet<>();
        brandAddresses.add(main);
        Brand brand = new Brand();
        brand.setName("UFPR - SEPT");
        brand.setAddresses(brandAddresses);
        Brand created = (Brand) dao.create(brand);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(5)
    public void updateBrand() throws SQLException {
        Brand original = dao.findByNameAndCountryName("UFPR", "BR");
        assertTrue(original.getId() > 0);

        Brand pirate = new Brand();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Universidade Federal PR");
        pirate.setAddresses(original.getAddresses());

        Brand current = dao.update(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(6)
    public void breakAddressForeignKey() throws SQLException {
        Brand original = dao.findByNameAndCountryName("Universidade Federal PR", "BR");
        assertTrue(original.getId() > 0);

        assertThrows(PersistenceException.class, () -> {
            dao.remove(original);
        });
    }

    @Test
    @Order(7)
    public void deleteBrand() throws SQLException {
        Brand original = dao.findByNameAndCountryName("Universidade Federal PR", "BR");
        assertTrue(original.getId() > 0);

        dao.delete(original);
        assertNotNull(original.getDeleted());
    }

    @Test
    @Order(8)
    public void findNoBrand() throws SQLException {
        assertNull(dao.findByNameAndCountryName("Universidade Federal PR", "BR"));
    }

    @Test
    @Order(9)
    public void breakRemoveBrandWithAddress() throws SQLException {
        Brand original = dao.findDeletedByNameAndCountryName("Universidade Federal PR", "BR");
        assertNotNull(original);

        assertThrows(PersistenceException.class, () -> {
            dao.remove(original);
        });
    }

    @Test
    @Order(10)
    public void removeBrandAddress() throws SQLException {
        Brand original = dao.findDeletedByNameAndCountryName("Universidade Federal PR", "BR");
        original.getAddresses().forEach(each -> {
            original.getAddresses().remove(each);
        });

        dao.update(original);
        Brand noAddress = dao.findDeletedByName("Universidade Federal PR");
        assertTrue(noAddress.getAddresses().isEmpty());
    }

    @Test
    @Order(11)
    public void removeBrand() throws SQLException {
        Brand toRemove = dao.findDeletedByName("Universidade Federal PR");
        assertNotNull(toRemove);
        assertTrue(toRemove.getAddresses().isEmpty());

        dao.remove(toRemove);
        assertNull(dao.findDeletedByName("Universidade Federal PR"));
    }

    @Test
    @Order(12)
    public void completelyRemoveAnotherBrand() throws SQLException {
        Brand toRemove = dao.findByNameAndCountryName("UFPR - SEPT", "BR");
        assertNotNull(toRemove);
        toRemove.getAddresses().forEach(each -> {
            toRemove.getAddresses().remove(each);
        });

        dao.update(toRemove);
        Brand noAddress = dao.findByName("UFPR - SEPT");
        assertTrue(noAddress.getAddresses().isEmpty());

        dao.delete(noAddress);
        assertNotNull(dao.findDeletedByName("UFPR - SEPT"));

        dao.remove(noAddress);
        assertNull(dao.findDeletedByName("UFPR - SEPT"));
    }

    @Test
    @Order(13)
    public void setDownAddresses() throws SQLException {
        addressHelper.deleteAddress();
        addressHelper.removeAddress();
        addressHelper.updateCity();
        addressHelper.deleteCity();
        addressHelper.removeCity();
        addressHelper.updateState();
        addressHelper.deleteState();
        addressHelper.removeState();
        addressHelper.deleteCountry();
        addressHelper.removeCountry();
    }
}
