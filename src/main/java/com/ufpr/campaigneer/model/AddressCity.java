package com.ufpr.campaigneer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "address_city")
public class AddressCity extends BasicModel {

    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name ="state_fk"))
    private AddressState state;

    public AddressCity(String name, AddressState state) {
        this.name = name;
        this.state = state;
    }

    public AddressCity() {
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
