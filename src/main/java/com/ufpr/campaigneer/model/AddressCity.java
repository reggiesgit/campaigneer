package com.ufpr.campaigneer.model;

import javax.persistence.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "address_city")
public class AddressCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private AddressState state;

    public AddressCity(String name, AddressState state) {
        this.name = name;
        this.state = state;
    }

    public AddressCity() {
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

    public AddressState getState() {
        return state;
    }

    public void setState(AddressState state) {
        this.state = state;
    }
}
