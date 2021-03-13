package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.AddressType;
import com.ufpr.campaigneer.model.Address;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 13/03/2021
 */

public class AddressJSON {

    private int id;
    private AddressType addressType;
    private String postalCode;
    private String streetName;
    private int streetNumber;
    private String complement;
    private AddressCityJSON addressCityJSON;

    public AddressJSON(AddressType addressType, String postalCode, String streetName, int streetNumber, String complement, AddressCityJSON addressCityJSON) {
        this.addressType = addressType;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.complement = complement;
        this.addressCityJSON = addressCityJSON;
    }

    public AddressJSON() {
    }

    public static Address map(AddressJSON json) {
        Address result = new Address();
        result.setId(json.getId());
        result.setAddressType(json.getAddressType());
        result.setPostalCode(json.getPostalCode());
        result.setStreetName(json.getStreetName());
        result.setStreetNumber(json.getStreetNumber());
        result.setComplement(json.getComplement());
        result.setCity(AddressCityJSON.map(json.getAddressCityJSON()));
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public AddressCityJSON getAddressCityJSON() {
        return addressCityJSON;
    }

    public void setAddressCityJSON(AddressCityJSON addressCityJSON) {
        this.addressCityJSON = addressCityJSON;
    }
}
