package com.ufpr.campaigneer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "address_country")
public class AddressCountry extends BasicModel {

    private String name;
    @Column(unique = true)
    private String code;

    public AddressCountry(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public AddressCountry() {

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
}
