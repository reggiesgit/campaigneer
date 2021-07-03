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
public class AddressState extends BasicModel {

    private String name;
    @Column(unique = true)
    private String code;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name ="country_fk"))
    private AddressCountry country;

    public AddressState(String name, String code, AddressCountry country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }

    public AddressState() {
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
