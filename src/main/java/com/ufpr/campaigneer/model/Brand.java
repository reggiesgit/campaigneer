package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.AddressType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "brand")
public class Brand extends BasicModel {

    private String name;
    @ManyToMany(targetEntity = Address.class, cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    @JoinTable(name="brand_address",
            joinColumns = { @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name ="brand_fk")) },
            inverseJoinColumns = { @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name ="address_fk")) })
    private Set<Address> addresses;

    public Brand(String name, Set<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Address findMainAddress() {
        return this.getAddresses().stream()
                .filter(each -> each.getAddressType().equals(AddressType.BRAND_MAIN))
                .findAny()
                .orElse(null);
    }

}
