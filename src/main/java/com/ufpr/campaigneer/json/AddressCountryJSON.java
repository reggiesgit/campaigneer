package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.AddressCountry;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/03/2021
 */

public class AddressCountryJSON {

    private Long id;
    private String name;
    private String code;

    public AddressCountryJSON() {
    }

    public AddressCountryJSON(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static AddressCountry map(AddressCountryJSON json) {
        AddressCountry result = new AddressCountry();
        result.setId(json.getId());
        result.setCode(json.getCode());
        result.setName(json.getName());
        return result;
    }

    public static AddressCountryJSON map(AddressCountry country) {
        AddressCountryJSON result = new AddressCountryJSON();
        result.setId(country.getId());
        result.setCode(country.getCode());
        result.setName(country.getName());
        return result;
    }

    public static AddressCountry replace(AddressCountryJSON json) {
        AddressCountry result = new AddressCountry();
        if (json.getCode() != null) result.setCode(json.getCode());
        if (json.getName() != null) result.setName(json.getName());
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
