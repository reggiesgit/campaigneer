package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.CampaignType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToOne
    private Brand promoter;
    private Date purchaseFrom;
    private Date purchaseUntil;
    private Date validFrom;
    private Date validUntil;
    @ManyToMany
    private List<Product> participatingProducts;
//    TODO: When traders are added to the system, participatingTraders must be enabled.
//    @ManyToMany
//    ManyToMany private String participatingTraders;
    @OneToMany
    private List<AddressCountry> validLocations;
    @Enumerated
    private CampaignType campaignType;

}
