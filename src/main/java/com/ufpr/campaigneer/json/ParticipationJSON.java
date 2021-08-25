package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 23/08/2021
 */

public class ParticipationJSON {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String contact;
    private Set<Address> addresses;
    private Set<Product> products;
    private Campaign triggeredCampaign;
    private String invoice64;

    public ParticipationJSON(Long id, String name, String lastName, String email, String contact, Set<Address> addresses, Set<Product> products, Campaign triggeredCampaign) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.addresses = addresses;
        this.products = products;
        this.triggeredCampaign = triggeredCampaign;
    }

    public ParticipationJSON() {
    }

    public static Participation mapJson(ParticipationJSON json) {
        Participation result = new Participation();
        result.setId(json.getId());
        result.setName(json.getName());
        result.setLastName(json.getLastName());
        result.setEmail(json.getEmail());
        result.setContact(json.getContact());
        result.setAddresses(json.getAddresses());
        result.setProducts(json.getProducts());
        result.setTriggeredCampaign(json.getTriggeredCampaign());
        return result;
    }

    public static ParticipationJSON map(Participation participation) {
        ParticipationJSON json = new ParticipationJSON();
        json.setId(participation.getId());
        json.setName(participation.getName());
        json.setLastName(participation.getLastName());
        json.setEmail(participation.getEmail());
        json.setContact(participation.getContact());
        json.setAddresses(participation.getAddresses());
        json.setProducts(participation.getProducts());
        json.setTriggeredCampaign(participation.getTriggeredCampaign());
        return json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Campaign getTriggeredCampaign() {
        return triggeredCampaign;
    }

    public void setTriggeredCampaign(Campaign triggeredCampaign) {
        this.triggeredCampaign = triggeredCampaign;
    }
}
