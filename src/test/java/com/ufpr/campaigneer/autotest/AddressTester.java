package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

import javax.persistence.PersistenceException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 27/02/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressTester {

    AddressDAO dao = new AddressDAO();

    @Test
    @Order(1)
    public void createCountry() throws SQLException {
        AddressCountry country = new AddressCountry("Republic of Testing", "RT");
        AddressCountry created = (AddressCountry) dao.createCountry(country);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(2)
    public void createState() throws SQLException {
        AddressCountry country = dao.findByCountryCode("RT");
        AddressState state = new AddressState("South Testland", "ST", country);
        AddressState created = (AddressState) dao.createState(state);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(3)
    public void createCity() throws SQLException {
        AddressState state = dao.findByStateCodeAndCountryCode("ST", "RT");
        assertTrue(state.getId() > 0);

        AddressCity city = new AddressCity("Testtingtown", state);
        AddressCity created = (AddressCity) dao.createCity(city);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(4)
    public void breakCountryForeignKeyConstraint() {
        AddressCountry bound = dao.findByCountryCode("RT");
        assertThrows(PersistenceException.class, () -> {
            dao.removeCountry(bound);
        });
    }

    @Test
    @Order(5)
    public void breakCountryUniqueCodeConstraint() {
        AddressCountry country = new AddressCountry("Republic of Testing", "RT");
        assertThrows(ConstraintViolationException.class, () -> {
            AddressCountry created = dao.createCountry(country);
        });
    }

    @Test
    @Order(6)
    public void breakStateForeignKeyConstraint() {
        AddressState bound = dao.findByStateCodeAndCountryCode("ST", "RT");
        assertThrows(PersistenceException.class, () -> {
            dao.removeState(bound);
        });
    }

    @Test
    @Order(7)
    public void updateCountry() {
        AddressCountry original = dao.findByCountryCode("RT");
        assertNotNull(original.getName());

        AddressCountry pirate = new AddressCountry();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Independent Republic of Testing");
        pirate.setCode(original.getCode());

        AddressCountry current = new AddressCountry();
        current = dao.updateCountry(pirate);

        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(8)
    public void updateState() {
        AddressState original = dao.findByStateCodeAndCountryCode("ST", "RT");
        assertNotNull(original.getName());

        AddressState pirate = new AddressState();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("North Testland");
        pirate.setCode(original.getCode());
        pirate.setCountry(original.getCountry());

        AddressState current = dao.updateState(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(9)
    public void updateCity() {
        AddressCity original = dao.findByCityNameAndStateCode("Testtingtown", "ST");
        assertNotNull(original.getName());

        AddressCity pirate = new AddressCity();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("TesttingBurg");
        pirate.setState(original.getState());

        AddressCity current = dao.updateCity(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(10)
    public void createAddress() {
        Address address = new Address();
        AddressCity city = dao.findByCityNameAndStateCode("TesttingBurg", "ST");
        assertNotNull(city.getName());

        address.setCity(city);
        address.setAddressType(AddressType.BRAND_MAIN);
        address.setComplement("SEPT");
        address.setStreetName("Dr. Alcides Arcoverde");
        address.setStreetNumber(1225);
        address.setPostalCode("88088-888");

        Address created = (Address) dao.createAddress(address);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(11)
    public void findAddress() {
        Address address = dao.findByPostalCodeAndNumber("88088-888", 1225);
        assertNotNull(address.getComplement());
    }

    @Test
    @Order(12)
    public void deleteAddress() {
        Address toRemove = dao.findByPostalCodeAndNumber("88088-888", 1225);
        assertNotNull(toRemove);

        dao.deleteAddress(toRemove);
        Address deleted = dao.findByDeletedPostalCodeAndNumber("88088-888", 1225);

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(13)
    public void deleteCity() {
        AddressCity toRemove = dao.findByCityNameAndStateCode("TesttingBurg", "ST");
        assertNotNull(toRemove);

        dao.deleteCity(toRemove);
        AddressCity deleted = dao.findByDeletedCityNameAndStateCode("TesttingBurg", "ST");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(14)
    public void deleteState() {
        AddressState toRemove = dao.findByStateCodeAndCountryCode("ST", "RT");
        assertNotNull(toRemove);

        dao.deleteState(toRemove);
        AddressState deleted = dao.findByDeletedStateCodeAndCountryCode("ST", "RT");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(15)
    public void deleteCountry() {
        AddressCountry toRemove = dao.findByCountryCode("RT");
        assertNotNull(toRemove);

        dao.deleteCountry(toRemove);
        AddressCountry deleted = dao.findByDeletedCountryCode("RT");

        assertNotNull(deleted.getDeleted());
    }

   @Test
   @Order(16)
   public void removeAddress() {
       Address toRemove = dao.findByDeletedPostalCodeAndNumber("88088-888", 1225);
       assertNotNull(toRemove);

       dao.removeAddress(toRemove);
       assertNull(dao.findByDeletedPostalCodeAndNumber("88088-888", 1225));
   }

   @Test
   @Order(17)
   public void removeCity() {
       AddressCity toRemove = dao.findByDeletedCityNameAndStateCode("TesttingBurg", "ST");
       assertNotNull(toRemove);

       dao.removeCity(toRemove);
       assertNull(dao.findByDeletedCityNameAndStateCode("TesttingBurg", "ST"));
   }

   @Test
   @Order(18)
   public void removeState() {
       AddressState toRemove = dao.findByDeletedStateCodeAndCountryCode("ST", "RT");
       assertNotNull(toRemove);

       dao.removeState(toRemove);
       assertNull(dao.findByDeletedStateCodeAndCountryCode("ST", "RT"));
   }

   @Test
   @Order(19)
   public void removeCountry() {
       AddressCountry toRemove = dao.findByDeletedCountryCode("RT");
       assertNotNull(toRemove);

       dao.removeCountry(toRemove);
       assertNull(dao.findByDeletedCountryCode("RT"));
   }

    @Test
    @Order(20)
    public void findNoCity() {
        assertNull(dao.findByCityNameAndStateCode("TesttingBurg", "ST"));
    }

    @Test
    @Order(21)
    public void findNoState() {
        assertNull(dao.findByStateCodeAndCountryCode("ST", "RT"));
    }

    @Test
    @Order(22)
    public void findNoCountry() {
        assertNull(dao.findByCountryCode("RT"));
    }
}
