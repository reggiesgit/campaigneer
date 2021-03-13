package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.AddressState;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 13/03/2021
 */

public class AddressStateJSON {

    private int id;
    private String name;
    private String code;
    private AddressCountryJSON countryJSON;


    public AddressStateJSON(String name, String code, AddressCountryJSON countryJSON) {
        this.name = name;
        this.code = code;
        this.countryJSON = countryJSON;
    }

    public AddressStateJSON() {
    }

    public static AddressState map(AddressStateJSON json) {
        AddressState result = new AddressState();
        result.setId(json.getId());
        result.setCode(json.getCode());
        result.setName(json.getName());
        result.setCountry(AddressCountryJSON.map(json.getCountryJSON()));
        return result;
    }

    public static AddressStateJSON map(AddressState result) {
        AddressStateJSON json = new AddressStateJSON();
        json.setId(result.getId());
        json.setCode(result.getCode());
        json.setName(result.getName());
        json.setCountryJSON(AddressCountryJSON.map(result.getCountry()));
        return json;
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

    public AddressCountryJSON getCountryJSON() {
        return countryJSON;
    }

    public void setCountryJSON(AddressCountryJSON countryJSON) {
        this.countryJSON = countryJSON;
    }
}