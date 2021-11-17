package com.ufpr.campaigneer.enums;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 28/09/2021
 */

public enum ViolationType {

    PRODUCT_VIOLATION(0, "PRODUCT VIOLATION"),
    PURCHASE_VIOLATION(1, "PURCHASE DATE VIOLATION"),
    TRADER_VIOLATION(2, "TRADER VIOLATION"),
    LOCALE_VIOLATION(3, "LOCALE VIOLATION"),
    INVOICE_VIOLATION(4, "INVOICE VIOLATION"),
    REGISTRATION_VIOLATION(5, "REGISTRATION_VIOLATION");

    private int chave;
    private String valor;

    ViolationType(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

}
