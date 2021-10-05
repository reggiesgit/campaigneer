package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.AddressCity;

import javax.ws.rs.NotFoundException;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 13/03/2021
 */

public class AddressCityJSON {

    private Long id;
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
        if (json.getStateJSON() != null) {
            result.setState(AddressStateJSON.map(json.getStateJSON()));
        }
        return result;
    }

    public static AddressCityJSON map(AddressCity result) {
        AddressCityJSON json = new AddressCityJSON();
        json.setId(result.getId());
        json.setName(result.getName());
        json.setStateJSON(AddressStateJSON.map(result.getState()));
        return json;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
