package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.AddressType;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "address")
public class Address extends BasicModel {

    @Enumerated(EnumType.ORDINAL)
    private AddressType addressType;
    @NotNull
    @ColumnDefault("00000000")
    private String postalCode;
    private String streetName;
    @NotNull
    @ColumnDefault("0")
    private int streetNumber;
    private String complement;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name ="city_fk") )
    private AddressCity city;

    public Address(AddressType addressType, String postalCode, String streetName, int streetNumber, String complement, AddressCity city) {
        this.addressType = addressType;
        this.postalCode = postalCode;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.complement = complement;
        this.city = city;
    }

    public Address() {
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

    public AddressCity getCity() {
        return city;
    }

    public void setCity(AddressCity city) {
        this.city = city;
    }
}
