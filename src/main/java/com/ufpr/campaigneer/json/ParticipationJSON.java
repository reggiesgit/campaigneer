package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.CampaignStatus;
import com.ufpr.campaigneer.model.Address;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Participation;
import com.ufpr.campaigneer.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
    private CampaignStatus campaignStatus;
    private LocalDate invoiceDate;
    private String invoice;

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
        result.setCampaignStatus(json.getCampaignStatus());
        result.setInvoiceDate(json.getInvoiceDate());
        result.setInvoicePath(json.getInvoice());
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
        json.setCampaignStatus(participation.getCampaignStatus());
        json.setInvoiceDate(participation.getInvoiceDate());
        json.setInvoice(participation.getInvoicePath());
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

    public CampaignStatus getCampaignStatus() {
        return campaignStatus;
    }

    public void setCampaignStatus(CampaignStatus campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    public void setTriggeredCampaign(Campaign triggeredCampaign) {
        this.triggeredCampaign = triggeredCampaign;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
