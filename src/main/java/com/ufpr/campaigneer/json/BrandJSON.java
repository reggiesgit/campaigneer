package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.Brand;

import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

public class BrandJSON {

    private String name;
    private Set<AddressJSON> addresses;

    public BrandJSON(String name, Set<AddressJSON> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public BrandJSON() {
    }

    public static Brand map(BrandJSON json) {
        Brand result = new Brand();
        result.setName(json.getName());
        result.setAddresses(AddressJSON.map(json.getAddresses()));
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AddressJSON> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressJSON> addresses) {
        this.addresses = addresses;
    }
}
