package com.ufpr.campaigneer.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 12/07/2021
 */

@Entity
@Table(name = "participation")
public class Participation extends BasicModel {

    private String name;
    private String lastName;
    private String email;
    private String contact;
    @ManyToMany(targetEntity = Address.class, cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name="participation_address",
            joinColumns = { @JoinColumn(name = "participation_id", foreignKey = @ForeignKey(name ="participation_fk")) },
            inverseJoinColumns = { @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name ="address_fk")) })
    private Set<Address> addresses;
    @OneToMany(targetEntity = Product.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "participation_products",
            joinColumns = {@JoinColumn(name = "participation_id", foreignKey = @ForeignKey(name = "participation_product_fk"))},
            inverseJoinColumns = {@JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_fk"))})
    private Set<Product> products;
    @OneToOne
    @JoinColumn(name = "campaign_id", foreignKey = @ForeignKey(name ="campaign_fk"))
    private Campaign triggeredCampaign;

    public Participation(String name, String lastName, String email, String contact, Set<Address> addresses, Set<Product> products, Campaign triggeredCampaign) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.addresses = addresses;
        this.products = products;
        this.triggeredCampaign = triggeredCampaign;
    }

    public Participation() {
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