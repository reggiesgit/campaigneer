package com.ufpr.campaigneer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "address_state")
public class AddressState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String code;
    @NotNull
    @ManyToOne
    private AddressCountry country;

    public AddressState(String name, String code, AddressCountry country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }

    public AddressState() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AddressCountry getCountry() {
        return country;
    }

    public void setCountry(AddressCountry country) {
        this.country = country;
    }
}
