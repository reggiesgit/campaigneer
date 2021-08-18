package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.component.AddressComponent;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 27/02/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressTester {

    private final AddressComponent component = new AddressComponent();
    AddressDAO dao = new AddressDAO();

    public AddressCountry defaultCountry() {
        AddressCountry country = component.findByCountryCode("BR")
                .orElse(new AddressCountry("Brasil","BR"));
        if (country.getId() > 0) {
            return country;
        }
        return component.createCountry(country).orElseThrow();
    }

    public AddressState defaultState() {
        AddressState state = component.findByStateCodeAndCountryCode("PR", "BR")
                .orElse(new AddressState("Parana", "PR", defaultCountry()));
        if (state.getId() > 0) {
            return state;
        }
        return component.createState(state).orElse(null);
    }

    public AddressCity defaultCity() {
        AddressCity city = component.findByCityNameAndStateCode("Curitiba", "PR")
                .orElse(new AddressCity("Curitiba", defaultState()));
        if (city.getId() > 0) {
            return city;
        }
        return dao.createCity(city);
    }

    @Test
    @Order(1)
    public void createCountry() {
        assertTrue(defaultCountry().getId() > 0);
    }

    @Test
    @Order(2)
    public void createState() {
        assertTrue(defaultState().getId() > 0);
    }

    @Test
    @Order(3)
    public void createCity() {
        assertTrue(defaultCity().getId() > 0);
    }

    @Test
    @Order(4)
    public void createAnotherCountry() {
        AddressCountry country = new AddressCountry("Argentina", "AR");
        AddressCountry created = dao.createCountry(country);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(5)
    public void createAnotherState() {
        AddressCountry country = component.findByCountryCode("AR").orElse(null);
        assertNotNull(country);
        AddressState state = new AddressState("Ayacucho", "AY", country);
        AddressState created = dao.createState(state);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(6)
    public void createAnotherCity() {
        AddressState state = component.findByStateCodeAndCountryCode("AY", "AR").orElse(null);
        assertNotNull(state);

        AddressCity city = new AddressCity("Buenos Aires", state);
        AddressCity created = dao.createCity(city);
        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(7)
    public void breakCountryForeignKeyConstraint() {
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
        AddressState bound = component.findByStateCodeAndCountryCode("PR", "BR").orElse(null);
        assertNotNull(bound);
        assertThrows(PersistenceException.class, () -> {
            dao.removeState(bound);
        });
    }

    @Test
    @Order(10)
    public void updateCountry() {
        AddressCountry original = defaultCountry();

        AddressCountry pirate = new AddressCountry();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Republica do Brasil");
        pirate.setCode(original.getCode());

        AddressCountry current = dao.updateCountry(pirate);

        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());
    }

    @Test
    @Order(11)
    public void updateState() {
        AddressState original = defaultState();

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
        AddressCity original = defaultCity();

        AddressCity pirate = new AddressCity();
        pirate.setId(original.getId());
        pirate.setCreated(original.getCreated());
        pirate.setName("Curitiba Capital");
        pirate.setState(original.getState());

        AddressCity current = dao.updateCity(pirate);
        assertNotEquals(original, current);
        assertNotNull(current.getUpdated());

        dao.updateCity(original);
    }

    @Test
    @Order(13)
    public void createAddress() {
        Address address = new Address();
        AddressCity city = defaultCity();

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
        AddressCity city = defaultCity();

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
        Address address = component.findByPostalCodeAndNumber("88088-888", 1225).orElse(null);
        assertNotNull(address.getComplement());
    }

    @Test
    @Order(16)
    public void deleteAddress() {
        Address toRemove = component.findByPostalCodeAndNumber("88088-888", 1225).orElse(null);
        assertNotNull(toRemove);

        dao.deleteAddress(toRemove);
        Address deleted = dao.findByDeletedPostalCodeAndNumber("88088-888", 1225);

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(17)
    public void deleteCity() {
        AddressCity toRemove = component.findByCityNameAndStateCode("Curitiba", "PR").orElse(null);
        assertNotNull(toRemove);

        dao.deleteCity(toRemove);
        AddressCity deleted = dao.findByDeletedCityNameAndStateCode("Curitiba", "PR");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(18)
    public void deleteState() {
        AddressState toRemove = component.findByStateCodeAndCountryCode("PR", "BR").orElse(null);
        assertNotNull(toRemove);

        dao.deleteState(toRemove);
        AddressState deleted = dao.findByDeletedStateCodeAndCountryCode("PR", "BR");

        assertNotNull(deleted.getDeleted());
    }

    @Test
    @Order(19)
    public void deleteCountry() {
        AddressCountry toRemove = component.findByCountryCode("BR").orElse(null);
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
        AddressCity toRemove = dao.findByDeletedCityNameAndStateCode("Curitiba", "PR");
        assertNotNull(toRemove);

        dao.removeCity(toRemove);
        assertNull(dao.findByDeletedCityNameAndStateCode("Curitiba", "PR"));
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
        Address address = component.findByPostalCodeAndNumber("88088-999", 819).orElse(null);
        assertNotNull(address);
        dao.removeAddress(address);
        assertNull(dao.findByPostalCodeAndNumber("88088-999", 819));

        AddressCity city = component.findByCityNameAndStateCode("Buenos Aires", "AY").orElse(null);
        assertNotNull(city);
        dao.removeCity(city);
        assertNull(dao.findByCityNameAndStateCode("Buenos Aires", "AY"));

        AddressState state = component.findByStateCodeAndCountryCode("AY", "AR").orElse(null);
        assertNotNull(state);
        dao.removeState(state);
        assertNull(dao.findByStateCodeAndCountryCode("AY", "AR"));

        AddressCountry country = component.findByCountryCode("AR").orElse(null);
        assertNotNull(country);
        dao.removeCountry(country);
        assertNull(dao.findByCountryCode("AR"));
    }

    @Test
    @Order(25)
    public void findNoCity() {
        assertNull(component.findByCityNameAndStateCode("Curitiba", "PR").orElse(null));
    }

    @Test
    @Order(26)
    public void findNoState() {
        assertNull(component.findByStateCodeAndCountryCode("PR", "BR").orElse(null));
    }

    @Test
    @Order(27)
    public void findNoCountry() {
        assertNull(component.findByCountryCode("BR").orElse(null));
    }
}
