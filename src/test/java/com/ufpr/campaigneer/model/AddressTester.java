package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.dao.AddressDAO;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

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
        AddressCountry country = new AddressCountry("Seventyland", "70");

        AddressCountry created = (AddressCountry) dao.createCountry(country);

        assertTrue(created.getId() > 0);
    }

    @Test
    @Order(2)
    public void breakCountryUniqueCodeConstraint() {
        AddressCountry country = new AddressCountry("Seventyland", "70");

        assertThrows(ConstraintViolationException.class, () -> {
            AddressCountry created = dao.createCountry(country);
        });
    }

    @Test
    @Order(3)
    public void updateCountry() {
        AddressCountry original = new AddressCountry();
        original = dao.findCountryByCode("70");
        assertNotNull(original.getName());

        AddressCountry pirate = new AddressCountry();
        pirate.setId(original.getId());
        pirate.setName("Seventeensburg");
        pirate.setCode(original.getCode());

        AddressCountry current = new AddressCountry();
        current = dao.updateCountry(pirate);

        assertNotEquals(original, current);
    }

    @Test
    @Order(4)
    public void deleteCountry() {
        AddressCountry toRemove = new AddressCountry();
        toRemove = dao.findCountryByCode("70");

        assertNotNull(toRemove);

        dao.deleteCountry(toRemove);
        AddressCountry empty = new AddressCountry();
        empty = dao.findCountryByCode("70");

        assertNull(empty);
    }

    @Test
    @Order(5)
    public void findNoCountry() {
        AddressCountry empty = dao.findCountryByCode("70");

        assertNull(empty);
    }

}
