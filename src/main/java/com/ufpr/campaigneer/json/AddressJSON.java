package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.Address;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 13/03/2021
 */

public class AddressJSON {

    private Long id;
    private AddressType addressType;
    private String postalCode;
    private String streetName;
    private int streetNumber;
    private String complement;
    private AddressCityJSON cityJSON;

    public AddressJSON(AddressType addressType, String postalCode, String streetName, int streetNumber, String complement, AddressCityJSON cityJSON) {
        this.addressType = addressType;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.complement = complement;
        this.cityJSON = cityJSON;
    }

    public AddressJSON() {
    }

    public static Address mapJson(AddressJSON json) {
        Address result = new Address();
        result.setId(json.getId());
        result.setAddressType(json.getAddressType());
        result.setPostalCode(json.getPostalCode());
        result.setStreetName(json.getStreetName());
        result.setStreetNumber(json.getStreetNumber());
        result.setComplement(json.getComplement());
        result.setCity(AddressCityJSON.map(json.getCityJSON()));
        return result;
    }

    public static Set<Address> mapJson(Set<AddressJSON> json) {
        Set<Address> result = new HashSet<>();
        json.forEach(each -> {
            result.add(AddressJSON.mapJson(each));
        });
        return result;
    }

    public static AddressJSON map(Address address) {
        AddressJSON result = new AddressJSON();
        result.setId(address.getId());
        result.setAddressType(address.getAddressType());
        result.setPostalCode(address.getPostalCode());
        result.setStreetName(address.getStreetName());
        result.setStreetNumber(address.getStreetNumber());
        result.setComplement(address.getComplement());
        result.setCityJSON(AddressCityJSON.map(address.getCity()));
        return result;
    }

    public static Set<AddressJSON> map(Set<Address> addressSet) {
        Set<AddressJSON> result = new HashSet<>();
        addressSet.forEach(each -> {
            result.add(map(each));
        });
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public AddressCityJSON getCityJSON() {
        return cityJSON;
    }

    public void setCityJSON(AddressCityJSON cityJSON) {
        this.cityJSON = cityJSON;
    }
}
