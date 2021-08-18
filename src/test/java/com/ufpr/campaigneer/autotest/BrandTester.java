package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.component.BrandComponent;
import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.dao.BrandDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.*;
import javassist.NotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.PersistenceException;
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
    private BrandComponent component = new BrandComponent();
    private AddressDAO addressDAO = new AddressDAO();
    private AddressTester addressHelper = new AddressTester();

    public Brand defaultBrand() {
        Brand brand = component.findByName("UFPR").orElse(new Brand());
        if (brand.getId() > 0) {
            return brand;
        }
        AddressCity city = addressHelper.defaultCity();
        Address address = new Address();
        address.setCity(city);
        address.setAddressType(AddressType.BRAND_MAIN);
        address.setComplement("SEPT");
        address.setStreetName("Dr. Alcides Arcoverde");
        address.setStreetNumber(1225);
        address.setPostalCode("88088-888");

        brand.setName("UFPR");
        brand.setAddresses(new HashSet<>());
        brand.getAddresses().add(address);
        return component.create(brand).orElse(null);
    }

    @Test
    @Order(1)
    public void createBrand() {
        assertTrue(defaultBrand().getId() > 0);
    }

    @Test
    @Order(2)
    public void createAnotherBrand() {
        Address main = new Address();
        main = addressDAO.findByPostalCodeAndNumber("88088-888", 1225);
        assertTrue(main.getId() > 0);
        Set<Address> brandAddresses = new HashSet<>();
        brandAddresses.add(main);
        Brand brand = new Brand();
        brand.setName("UTFPR");
        brand.setAddresses(brandAddresses);
        Brand created = component.create(brand).orElse(null);
        assertNotNull(created);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(3)
    public void updateBrand() {
        Brand original = component.findByNameAndCountryCode("UFPR", "BR").orElse(null);
        assertNotNull(original);
        assertTrue(original.getId() > 0);

        Brand pirate = new Brand();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Universidade Federal PR");
        pirate.setAddresses(original.getAddresses());

        Brand current = component.update(pirate).orElse(null);
        assertNotNull(current);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());

        component.update(original);
    }

    @Test
    @Order(4)
    public void breakAddressForeignKey() {
        Brand original = component.findByNameAndCountryCode("UFPR", "BR").orElse(null);
        assertNotNull(original);
        assertTrue(original.getId() > 0);

        assertThrows(PersistenceException.class, () -> {
            dao.remove(original);
        });
    }

    @Test
    @Order(5)
    public void breakRemoveBrandWithAddress() {
        Brand original = component.findByNameAndCountryCode("UFPR", "BR").orElse(null);
        assertNotNull(original);

        assertThrows(PersistenceException.class, () -> {
            dao.remove(original);
        });
    }

    @Test
    @Order(6)
    public void addAnotherBrandAddress() {
        Brand original = component.findByNameAndCountryCode("UFPR", "BR").orElse(null);
        Address another = addressDAO.findByPostalCodeAndNumber("88088-999", 819);
        original.getAddresses().add(another);

        Brand current = component.update(original).orElse(null);
        assertNotNull(current);
        assertTrue(current.getAddresses().size() > 1);
    }

    @Test
    @Order(7)
    public void removeBrandAddress() {
        Brand original = component.findByNameAndCountryCode("UFPR", "BR").orElse(null);
        assertNotNull(original);
        original.setAddresses(new HashSet<>());

        Brand noAddress = component.update(original).orElse(null);
        assertNotNull(noAddress);
        assertTrue(noAddress.getAddresses().isEmpty());
    }

    @Test
    @Order(8)
    public void deleteBrand() {
        Brand original = dao.findByName("UFPR");
        assertNotNull(original);
        assertTrue(original.getId() > 0);

        component.delete(original);
        Brand deleted = dao.findDeletedByName("UFPR");
        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(9)
    public void findNoBrand()  {
        assertNull(component.findByNameAndCountryCode("UFPR", "BR").orElse(null));
    }


    @Test
    @Order(10)
    public void removeBrand() {
        Brand toRemove = dao.findDeletedByName("UFPR");
        assertNotNull(toRemove);
        assertTrue(toRemove.getAddresses().isEmpty());

        dao.remove(toRemove);
        assertNull(dao.findDeletedByName("UFPR"));
    }

    @Test
    @Order(11)
    public void completelyRemoveAnotherBrand() {
        Brand toRemove = dao.findByNameAndCountryCode("UTFPR", "BR");
        assertNotNull(toRemove);
        toRemove.setAddresses(new HashSet<>());

        dao.update(toRemove);
        Brand noAddress = dao.findByName("UTFPR");
        assertTrue(noAddress.getAddresses().isEmpty());

        dao.delete(noAddress);
        assertNotNull(dao.findDeletedByName("UTFPR"));

        dao.remove(noAddress);
        assertNull(dao.findDeletedByName("UTFPR"));
    }

    @Test
    @Order(12)
    public void setDownAddresses() {
        addressHelper.deleteAddress();
        addressHelper.removeAddress();
        addressHelper.deleteCity();
        addressHelper.deleteState();
        addressHelper.deleteCountry();
        addressHelper.removeCity();
        addressHelper.removeState();
        addressHelper.removeCountry();
    }
}
