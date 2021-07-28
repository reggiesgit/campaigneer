package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.AddressCity;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.AddressState;
import javassist.NotFoundException;
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

    private final AddressDAO dao = new AddressDAO();

    @Test
    @Order(1)
    public void createCountry() throws SQLException {
        AddressCountry country = new AddressCountry("Brasil", "BR");
        AddressCountry created = dao.createCountry(country);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(2)
    public void createState() {
        AddressCountry country = dao.findByCountryCode("BR");
        AddressState state = new AddressState("Parana", "PR", country);
        AddressState created = dao.createState(state);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(3)
    public void createCity() throws SQLException {
        AddressState state = dao.findByStateCodeAndCountryCode("PR", "BR");
        assertTrue(state.getId() > 0);

        AddressCity city = new AddressCity("Curitiba", state);
        AddressCity created = dao.createCity(city);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(4)
    public void createAnotherCountry() throws SQLException {
        AddressCountry country = new AddressCountry("Argentina", "AR");
        AddressCountry created = dao.createCountry(country);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(5)
    public void createAnotherState() throws SQLException {
        AddressCountry country = dao.findByCountryCode("AR");
        AddressState state = new AddressState("Ayacucho", "AY", country);
        AddressState created = dao.createState(state);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(6)
    public void createAnotherCity() throws SQLException {
        AddressState state = dao.findByStateCodeAndCountryCode("AY", "AR");
        assertTrue(state.getId() > 0);

        AddressCity city = new AddressCity("Buenos Aires", state);
        AddressCity created = dao.createCity(city);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(7)
    public void breakCountryForeignKeyConstraint() throws NotFoundException {
        AddressCountry bound = dao.findByCountryCode("BR");
        assertThrows(PersistenceException.class, () -> {
            dao.removeCountry(bound);
        });
    }

    @Test
    @Order(8)
    public void breakCountryUniqueCodeConstraint() {
        AddressCountry country = new AddressCountry("Brasil", "BR");
        assertThrows(ConstraintViolationException.class, () -> {
            AddressCountry created = dao.createCountry(country);
        });
    }

    @Test
    @Order(9)
    public void breakStateForeignKeyConstraint() {
        AddressState bound = dao.findByStateCodeAndCountryCode("PR", "BR");
        assertThrows(PersistenceException.class, () -> {
            dao.removeState(bound);
        });
    }

    @Test
    @Order(10)
    public void updateCountry() throws NotFoundException {
        AddressCountry original = dao.findByCountryCode("BR");
        assertNotNull(original.getName());

        AddressCountry pirate = new AddressCountry();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Republica do Brasil");
        pirate.setCode(original.getCode());

        AddressCountry current = new AddressCountry();
        current = dao.updateCountry(pirate);

        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(11)
    public void updateState() {
        AddressState original = dao.findByStateCodeAndCountryCode("PR", "BR");
        assertNotNull(original.getName());

        AddressState pirate = new AddressState();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Parana do Sul");
        pirate.setCode(original.getCode());
        pirate.setCountry(original.getCountry());

        AddressState current = dao.updateState(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(12)
    public void updateCity() {
        AddressCity original = dao.findByCityNameAndStateCode("Curitiba", "PR");
        assertNotNull(original.getName());

        AddressCity pirate = new AddressCity();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Curitiba Capital");
        pirate.setState(original.getState());

        AddressCity current = dao.updateCity(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(13)
    public void createAddress() {
        Address address = new Address();
        AddressCity city = dao.findByCityNameAndStateCode("Curitiba Capital", "PR");
        assertNotNull(city.getName());

        address.setCity(city);
        address.setAddressType(AddressType.BRAND_MAIN);
        address.setComplement("SEPT");
        address.setStreetName("Dr. Alcides Arcoverde");
        address.setStreetNumber(1225);
        address.setPostalCode("88088-888");

        Address created = dao.createAddress(address);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(14)
    public void createAnotherAddress() {
        Address address = new Address();
        AddressCity city = dao.findByCityNameAndStateCode("Buenos Aires", "AY");
        assertNotNull(city.getName());

        address.setCity(city);
        address.setAddressType(AddressType.BRAND_BRANCH);
        address.setComplement("UBA");
        address.setStreetName("Arenales");
        address.setStreetNumber(819);
        address.setPostalCode("88088-999");

        Address created = dao.createAddress(address);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(15)
    public void findAddress() {
        Address address = dao.findByPostalCodeAndNumber("88088-888", 1225);
        assertNotNull(address.getComplement());
    }

    @Test
    @Order(16)
    public void deleteAddress() {
        Address toRemove = dao.findByPostalCodeAndNumber("88088-888", 1225);
        assertNotNull(toRemove);

        dao.deleteAddress(toRemove);
        Address deleted = dao.findByDeletedPostalCodeAndNumber("88088-888", 1225);

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(17)
    public void deleteCity() {
        AddressCity toRemove = dao.findByCityNameAndStateCode("Curitiba Capital", "PR");
        assertNotNull(toRemove);

        dao.deleteCity(toRemove);
        AddressCity deleted = dao.findByDeletedCityNameAndStateCode("Curitiba Capital", "PR");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(18)
    public void deleteState() {
        AddressState toRemove = dao.findByStateCodeAndCountryCode("PR", "BR");
        assertNotNull(toRemove);

        dao.deleteState(toRemove);
        AddressState deleted = dao.findByDeletedStateCodeAndCountryCode("PR", "BR");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(19)
    public void deleteCountry() {
        AddressCountry toRemove = dao.findByCountryCode("BR");
        assertNotNull(toRemove);

        dao.deleteCountry(toRemove);
        AddressCountry deleted = dao.findByDeletedCountryCode("BR");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(20)
    public void removeAddress() {
        Address toRemove = dao.findByDeletedPostalCodeAndNumber("88088-888", 1225);
        assertNotNull(toRemove);

        dao.removeAddress(toRemove);
        assertNull(dao.findByDeletedPostalCodeAndNumber("88088-888", 1225));
    }

    @Test
    @Order(21)
    public void removeCity() {
        AddressCity toRemove = dao.findByDeletedCityNameAndStateCode("Curitiba Capital", "PR");
        assertNotNull(toRemove);

        dao.removeCity(toRemove);
        assertNull(dao.findByDeletedCityNameAndStateCode("Curitiba Capital", "PR"));
    }

    @Test
    @Order(22)
    public void removeState() {
        AddressState toRemove = dao.findByDeletedStateCodeAndCountryCode("PR", "BR");
        assertNotNull(toRemove);

        dao.removeState(toRemove);
        assertNull(dao.findByDeletedStateCodeAndCountryCode("PR", "BR"));
    }

    @Test
    @Order(23)
    public void removeCountry() {
        AddressCountry toRemove = dao.findByDeletedCountryCode("BR");
        assertNotNull(toRemove);

        dao.removeCountry(toRemove);
        assertNull(dao.findByDeletedCountryCode("BR"));
    }

    @Test
    @Order(24)
    public void removeAnotherAddress() {
        Address address = dao.findByPostalCodeAndNumber("88088-999", 819);
        assertNotNull(address);
        dao.removeAddress(address);
        assertNull(dao.findByPostalCodeAndNumber("88088-999", 819));

        AddressCity city = dao.findByCityNameAndStateCode("Buenos Aires", "AY");
        assertNotNull(city);
        dao.removeCity(city);
        assertNull(dao.findByCityNameAndStateCode("Buenos Aires", "AY"));

        AddressState state = dao.findByStateCodeAndCountryCode("AY", "AR");
        assertNotNull(state);
        dao.removeState(state);
        assertNull(dao.findByStateCodeAndCountryCode("AY", "AR"));

        AddressCountry country = dao.findByCountryCode("AR");
        assertNotNull(country);
        dao.removeCountry(country);
        assertNull(dao.findByCountryCode("AR"));
    }

    @Test
    @Order(25)
    public void findNoCity() {
        assertNull(dao.findByCityNameAndStateCode("Curitiba Capital", "PR"));
    }

    @Test
    @Order(26)
    public void findNoState() {
        assertNull(dao.findByStateCodeAndCountryCode("PR", "BR"));
    }

    @Test
    @Order(27)
    public void findNoCountry() {
        assertNull(dao.findByCountryCode("BR"));
    }
}
