package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.CampaignType;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(unique = true, nullable = false)
    private String code;
    @OneToOne
    @JoinColumn(name = "promoter_id", foreignKey = @ForeignKey(name ="promoter_fk"))
    private Brand promoter;
    private LocalDate purchaseFrom;
    private LocalDate purchaseUntil;
    private LocalDate validFrom;
    private LocalDate validUntil;
    @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    @JoinTable(name = "campaign_participating_products",
            joinColumns = { @JoinColumn(name = "campaign_id", foreignKey = @ForeignKey(name ="product_campaign_fk"))},
            inverseJoinColumns = { @JoinColumn(name = "participating_products_id", foreignKey = @ForeignKey(name ="campaign_product_fk")) })
    private Set<Product> participatingProducts;
//    TODO: When traders are added to the system, participatingTraders must be enabled.
//    @ManyToMany
//    ManyToMany private String participatingTraders;
    @OneToMany(targetEntity = AddressCountry.class,
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "campaign_valid_locations",
            joinColumns = {@JoinColumn(name = "campaign_id", foreignKey = @ForeignKey(name = "valid_location_fk"))},
            inverseJoinColumns = {@JoinColumn(name = "valid_locations_id", foreignKey = @ForeignKey(name = "campaign_locations_fk"))})
    private Set<AddressCountry> validLocations;
    @Enumerated(EnumType.ORDINAL)
    private CampaignType campaignType;

    public Campaign(String name, Brand promoter, LocalDate purchaseFrom, LocalDate purchaseUntil, LocalDate validFrom, LocalDate validUntil, Set<Product> participatingProducts, Set<AddressCountry> validLocations, CampaignType campaignType) {
        this.name = name;
        this.promoter = promoter;
        this.purchaseFrom = purchaseFrom;
        this.purchaseUntil = purchaseUntil;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.participatingProducts = participatingProducts;
        this.validLocations = validLocations;
        this.campaignType = campaignType;
    }

    public Campaign() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Brand getPromoter() {
        return promoter;
    }

    public void setPromoter(Brand promoter) {
        this.promoter = promoter;
    }

    public LocalDate getPurchaseFrom() {
        return purchaseFrom;
    }

    public void setPurchaseFrom(LocalDate purchaseFrom) {
        this.purchaseFrom = purchaseFrom;
    }

    public LocalDate getPurchaseUntil() {
        return purchaseUntil;
    }

    public void setPurchaseUntil(LocalDate purchaseUntil) {
        this.purchaseUntil = purchaseUntil;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public Set<Product> getParticipatingProducts() {
        return participatingProducts;
    }

    public void setParticipatingProducts(Set<Product> participatingProducts) {
        this.participatingProducts = participatingProducts;
    }

    public Set<AddressCountry> getValidLocations() {
        return validLocations;
    }

    public void setValidLocations(Set<AddressCountry> validLocations) {
        this.validLocations = validLocations;
    }

    public CampaignType getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
    }

    public static Campaign copy(Campaign original) {
        Campaign copy = new Campaign();
        copy.setId(original.getId());
        copy.setName(original.getName());
        copy.setCode(original.getCode());
        copy.setValidLocations(original.getValidLocations());
        copy.setCampaignType(original.getCampaignType());
        copy.setPromoter(original.getPromoter());
        copy.setParticipatingProducts(original.getParticipatingProducts());
        copy.setValidFrom(original.getValidFrom());
        copy.setValidUntil(original.getValidUntil());
        copy.setPurchaseFrom(original.getPurchaseFrom());
        copy.setPurchaseUntil(original.getPurchaseUntil());
        copy.setCreated(original.getCreated());
        copy.setUpdated(original.getUpdated());
        copy.setDeleted(original.getDeleted());
        return copy;
    }
}
