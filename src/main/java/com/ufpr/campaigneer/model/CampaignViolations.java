package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.ViolationType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 28/09/2021
 */

public class CampaignViolations {

    private boolean productViolation;
    private boolean purchaseDateViolation;
    private boolean traderViolation;
    private boolean localeViolation;
    private boolean invoiceViolation;

    public CampaignViolations(boolean productViolation, boolean purchaseDateViolation, boolean traderViolation, boolean localeViolation, boolean invoiceViolation) {
        this.productViolation = productViolation;
        this.purchaseDateViolation = purchaseDateViolation;
        this.traderViolation = traderViolation;
        this.localeViolation = localeViolation;
        this.invoiceViolation = invoiceViolation;
    }

    public CampaignViolations() {
    }

    public static Set<ViolationType> fromAttributes(CampaignViolations campaignViolations) {
        Set<ViolationType> violations = new HashSet<>();
        if (campaignViolations.isProductViolation()) {
            violations.add(ViolationType.PRODUCT_VIOLATION);
        }
        if (campaignViolations.isPurchaseDateViolation()) {
            violations.add(ViolationType.PURCHASE_VIOLATION);
        }
        if (campaignViolations.isTraderViolation()) {
            violations.add(ViolationType.TRADER_VIOLATION);
        }
        if (campaignViolations.isLocaleViolation()) {
            violations.add(ViolationType.LOCALE_VIOLATION);
        }
        if (campaignViolations.isInvoiceViolation()) {
            violations.add(ViolationType.INVOICE_VIOLATION);
        }
        return violations;
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

    public boolean isInvoiceViolation() {
        return invoiceViolation;
    }

    public void setInvoiceViolation(boolean invoiceViolation) {
        this.invoiceViolation = invoiceViolation;
    }
}
