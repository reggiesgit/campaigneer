package com.ufpr.campaigneer.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "brand")
public class Brand extends BasicModel {


    private String name;
    @OneToMany
    private Set<Address> address;

    public Brand(String name, Set<Address> address) {
        this.name = name;
        this.address = address;
    }

    public Brand() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }
}
