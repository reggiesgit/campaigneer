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
    private boolean registrationDateViolation;
    private boolean traderViolation;
    private boolean localeViolation;
    private boolean invoiceViolation;

    public VerificationJSON(boolean productViolation, boolean purchaseDateViolation, boolean registrationDateViolation, boolean traderViolation, boolean localeViolation, boolean invoiceViolation) {
        this.productViolation = productViolation;
        this.purchaseDateViolation = purchaseDateViolation;
        this.registrationDateViolation = registrationDateViolation;
        this.traderViolation = traderViolation;
        this.localeViolation = localeViolation;
        this.invoiceViolation = invoiceViolation;
    }

    public VerificationJSON() {
    }

    public static CampaignViolations map(VerificationJSON json) {
        CampaignViolations errors = new CampaignViolations();
        errors.setLocaleViolation(json.isLocaleViolation());
        errors.setProductViolation(json.isProductViolation());
        errors.setPurchaseDateViolation(json.isPurchaseDateViolation());
        errors.setRegistrationDateViolation(json.isRegistrationDateViolation());
        errors.setTraderViolation(json.isTraderViolation());
        errors.setInvoiceViolation(json.isInvoiceViolation());
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

    public boolean isRegistrationDateViolation() {
        return registrationDateViolation;
    }

    public void setRegistrationDateViolation(boolean registrationDateViolation) {
        this.registrationDateViolation = registrationDateViolation;
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

    public boolean isInvoiceViolation() {
        return invoiceViolation;
    }

    public void setInvoiceViolation(boolean invoiceViolation) {
        this.invoiceViolation = invoiceViolation;
    }
}
