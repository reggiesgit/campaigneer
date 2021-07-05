package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.CampaignType;
import com.ufpr.campaigneer.model.AddressCountry;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.model.Campaign;
import com.ufpr.campaigneer.model.Product;

import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/07/2021
 */

public class CampaignJSON {

    private String name;
    private String code;
    private Brand promoter;
    private LocalDate purchaseFrom;
    private LocalDate purchaseUntil;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private Set<Product> participatingProducts;
    //    TODO: When traders are added to the system, participatingTraders must be enabled.
    private String participatingTraders;
    private Set<AddressCountry> validLocations;
    private CampaignType campaignType;

    public CampaignJSON(String name, String code, Brand promoter, LocalDate purchaseFrom, LocalDate purchaseUntil, LocalDate validFrom, LocalDate validUntil, Set<Product> participatingProducts, String participatingTraders, Set<AddressCountry> validLocations, CampaignType campaignType) {
        this.name = name;
        this.code = code;
        this.promoter = promoter;
        this.purchaseFrom = purchaseFrom;
        this.purchaseUntil = purchaseUntil;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.participatingProducts = participatingProducts;
        this.participatingTraders = participatingTraders;
        this.validLocations = validLocations;
        this.campaignType = campaignType;
    }

    public CampaignJSON() {
    }

    public static Campaign map(CampaignJSON json) {
        Campaign result = new Campaign();
        result.setName(json.getName());
        result.setCode(json.getCode());
        result.setValidLocations(json.getValidLocations());
        result.setCampaignType(json.getCampaignType());
        result.setPromoter(json.getPromoter());
        result.setParticipatingProducts(json.getParticipatingProducts());
        result.setValidFrom(json.getValidFrom());
        result.setValidUntil(json.getValidUntil());
        result.setPurchaseFrom(json.getPurchaseFrom());
        result.setPurchaseUntil(json.getPurchaseUntil());
        return result;
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

    public String getParticipatingTraders() {
        return participatingTraders;
    }

    public void setParticipatingTraders(String participatingTraders) {
        this.participatingTraders = participatingTraders;
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
}
