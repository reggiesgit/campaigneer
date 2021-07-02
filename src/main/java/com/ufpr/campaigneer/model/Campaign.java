package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.CampaignType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "campaign")
public class Campaign extends BasicModel {

    private String name;
    @NotNull
    @OneToOne
    private Brand promoter;
    private Date purchaseFrom;
    private Date purchaseUntil;
    private Date validFrom;
    private Date validUntil;
    @ManyToMany
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name ="product_fk"))
    private Set<Product> participatingProducts;
//    TODO: When traders are added to the system, participatingTraders must be enabled.
//    @ManyToMany
//    ManyToMany private String participatingTraders;
    @OneToMany
    private Set<AddressCountry> validLocations;
    @Enumerated
    private CampaignType campaignType;

}
