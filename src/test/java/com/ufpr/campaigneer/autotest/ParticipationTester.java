package com.ufpr.campaigneer.autotest;

import com.ufpr.campaigneer.component.ParticipationComponent;
import com.ufpr.campaigneer.dao.AddressDAO;
import com.ufpr.campaigneer.dao.ParticipationDAO;
import com.ufpr.campaigneer.dao.ProductDAO;
import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.Participation;
import javassist.NotFoundException;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 12/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParticipationTester {

    private ParticipationDAO dao = new ParticipationDAO();
    private ParticipationComponent component = new ParticipationComponent();
    private AddressTester addressHelper = new AddressTester();
    private ProductTester productHelper = new ProductTester();
    private AddressDAO addressDAO = new AddressDAO();

    private Address setUpAddress() {
        addressHelper.defaultCity();

        Address address = new Address();
        address.setAddressType(AddressType.BILLING_ADDRESS);
        address.setPostalCode("80630280");
        address.setStreetName("Assis Figueiredo");
        address.setStreetNumber(1315);
        address.setComplement("Marumbi");
        address.setCity(addressDAO.findByCityNameAndStateCode("Curitiba", "PR"));
        return addressDAO.createAddress(address);
    }

    public Participation defaultParticipation() {
        Participation part = component.findByEmail("regisandre@ufpr.br").orElse(new Participation());
        if (part.getId() != null) {
            return part;
        }
        part.setName("Regis");
        part.setLastName("Gaboardi");
        part.setEmail("regisandre@ufpr.br");
        part.setContact("88231331");
        return component.create(part).orElse(null);
    }

    @Test
    @Order(1)
    public void createParticipation() {
        assertNotNull(defaultParticipation());
    }

    @Test
    @Order(2)
    public void updateWithAddress() {
        Participation original = component.findByEmail(defaultParticipation().getEmail()).orElse(null);
        original.setAddresses(new HashSet<>());
        original.getAddresses().add(setUpAddress());
        Participation updated = component.update(original).orElse(null);
        assertNotNull(updated);
        assertTrue(!updated.getAddresses().isEmpty());
    }

    @Test
    @Order(3)
    public void updateWithProduct() {
        Participation original = component.findByEmail(defaultParticipation().getEmail()).orElse(null);
        original.setProducts(new HashSet<>());
        original.getProducts().add(productHelper.defaultProduct());
        Participation updated = component.update(original).orElse(null);
        assertNotNull(updated);
        assertTrue(!updated.getProducts().isEmpty());
    }

}