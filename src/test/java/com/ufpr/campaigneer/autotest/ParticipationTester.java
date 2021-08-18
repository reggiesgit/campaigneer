package com.ufpr.campaigneer.autotest;

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

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 12/07/2021
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParticipationTester {


    private AddressTester addressHelper = new AddressTester();
    private ParticipationDAO dao = new ParticipationDAO();
    private ParticipationComponent component = new ParticipationComponent();
    private AddressDAO addressDAO = new AddressDAO();
    private ProductDAO productDAO = new ProductDAO();

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
        Participation part = new Participation();
        part.setAddresses(new HashSet<>());
        part.setProducts(new HashSet<>());
        Address address = setUpAddress();
        part.setName("Regis");
        part.setLastName("Gaboardi");
        part.setEmail("regisandre@ufpr.br");
        part.setContact("88231331");
//        part.getAddresses().add(address);
//        part.getProducts().add(productDAO.findByEAN("UFPRSEPTTADS"));
        return component.create(part).orElse(null);
    }

    @Test
    @Order(1)
    public void createParticipation() {
        assertNotNull(defaultParticipation());
    }


}