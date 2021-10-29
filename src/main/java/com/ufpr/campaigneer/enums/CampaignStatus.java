package com.ufpr.campaigneer.enums;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 19/09/2021
 */

public enum CampaignStatus {

    NOT_PROCESSED(0, "NOT_PROCESSED"),
    NO_CAMPAIGN(1, "NO CAMPAIGN"),
    DUPLICATED(2, "DUPLICATED"),
    BAD_PURCHASE_DATE(3, "BAD_PURCHASE_DATE"),
    BAD_REGISTRATION_DATE(4, "BAD_REGISTRATION_DATE"),
    BAD_LOCALE(5, "BAD_LOCALE"),
    BAD_TRADER(6, "BAD_TRADER"),
    BAD_INVOICE(7, "BAD_INVOICE"),
    INVALID(8, "INVALID"),
    VALIDATION_QUEUE(9, "VALIDATION_QUEUE"),
    CORRECTION_QUEUE(10, "CORRECTION_QUEUE"),
    FINAL_INVALID(11, "FINAL_INVALID"),
    VALID(12, "VALID"),
    PAID(13, "PAID"),
    CLOSED(14, "CLOSED");

    private final int chave;
    private final String valor;

    CampaignStatus(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }
}
