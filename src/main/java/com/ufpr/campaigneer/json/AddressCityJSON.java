package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.AddressCity;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 13/03/2021
 */

public class AddressCityJSON {

    private int id;
    private String name;
    private AddressStateJSON stateJSON;

    public AddressCityJSON(String name, AddressStateJSON stateJSON) {
        this.name = name;
        this.stateJSON = stateJSON;
    }

    public AddressCityJSON() {
    }

    public static AddressCity map(AddressCityJSON json) {
        AddressCity result = new AddressCity();
        result.setId(json.getId());
        result.setName(json.getName());
        result.setState(AddressStateJSON.map(json.getStateJSON()));
        return result;
    }

    public static AddressCityJSON map(AddressCity result) {
        AddressCityJSON json = new AddressCityJSON();
        json.setId(result.getId());
        json.setName(result.getName());
        json.setStateJSON(AddressStateJSON.map(result.getState()));
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

    public AddressStateJSON getStateJSON() {
        return stateJSON;
    }

    public void setStateJSON(AddressStateJSON stateJSON) {
        this.stateJSON = stateJSON;
    }
}