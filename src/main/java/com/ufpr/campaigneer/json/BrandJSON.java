package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.Brand;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 02/07/2021
 */

public class BrandJSON {

    private Long id;
    private String name;
    private Set<AddressJSON> addresses;

    public BrandJSON(Long id, String name, Set<AddressJSON> addresses) {
        this.id = id;
        this.name = name;
        this.addresses = addresses;
    }

    public BrandJSON() {
    }

    public static Brand mapJson(BrandJSON json) {
        Brand result = new Brand();
        result.setId(json.getId());
        result.setName(json.getName());
        result.setAddresses(AddressJSON.mapJson(json.getAddresses()));
        return result;
    }

    public static BrandJSON map(Brand brand) {
        BrandJSON result = new BrandJSON();
        result.setId(brand.getId());
        result.setName(brand.getName());
        result.setAddresses(AddressJSON.map(brand.getAddresses()));
        return result;
    }

    public static Set<BrandJSON> map(Set<Brand> brandSet) {
        Set<BrandJSON> result = new HashSet<>();
        brandSet.forEach(each -> {
            result.add(BrandJSON.map(each));
        });
        return result;
    }

    public static Set<Brand> mapJSon(Set<BrandJSON> jsonSet) {
        Set<Brand> result = new HashSet<>();
        jsonSet.forEach(each -> {
            result.add(BrandJSON.mapJson(each));
        });
        return result;
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

    public Set<AddressJSON> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AddressJSON> addresses) {
        this.addresses = addresses;
    }
}
