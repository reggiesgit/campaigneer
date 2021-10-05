package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.model.CampaignViolations;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 28/09/2021
 */

public class VerificationJSON {

    private boolean productViolation;
    private boolean purchaseDateViolation;
    private boolean traderViolation;
    private boolean localeViolation;

    public VerificationJSON(boolean productViolation, boolean purchaseDateViolation, boolean traderViolation, boolean localeViolation) {
        this.productViolation = productViolation;
        this.purchaseDateViolation = purchaseDateViolation;
        this.traderViolation = traderViolation;
        this.localeViolation = localeViolation;
    }

    public VerificationJSON() {
    }

    public static CampaignViolations map(VerificationJSON json) {
        CampaignViolations errors = new CampaignViolations();
        errors.setLocaleViolation(json.isLocaleViolation());
        errors.setProductViolation(json.isProductViolation());
        errors.setPurchaseDateViolation(json.isPurchaseDateViolation());
        errors.setTraderViolation(json.isTraderViolation());
        return errors;
    }

    public boolean isProductViolation() {
        return productViolation;
    }

    public void setProductViolation(boolean productViolation) {
        this.productViolation = productViolation;
    }

    public boolean isPurchaseDateViolation() {
        return purchaseDateViolation;
    }

    public void setPurchaseDateViolation(boolean purchaseDateViolation) {
        this.purchaseDateViolation = purchaseDateViolation;
    }

    public boolean isTraderViolation() {
        return traderViolation;
    }

    public void setTraderViolation(boolean traderViolation) {
        this.traderViolation = traderViolation;
    }

    public boolean isLocaleViolation() {
        return localeViolation;
    }

    public void setLocaleViolation(boolean localeViolation) {
        this.localeViolation = localeViolation;
    }
}
