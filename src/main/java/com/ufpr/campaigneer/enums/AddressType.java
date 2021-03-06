package com.ufpr.campaigneer.enums;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

public enum AddressType {

    BILLING_ADDRESS(1, "BILLING_ADDRESS"),
    SHIPPING_ADDRESS(2, "SHIPPING_ADDRESS"),
    BRAND_MAIN(3, "BRAND_MAIN"),
    BRAND_BRANCH(4, "BRAND_BRANCH"),
    BRAND_STORE(5, "BRAND_STORE"),
    TRADER_MAIN(6, "TRADER_MAIN"),
    TRADER_STORE(7, "TRADER_MAIN");

    private int chave;
    private String valor;

    AddressType(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }
}
