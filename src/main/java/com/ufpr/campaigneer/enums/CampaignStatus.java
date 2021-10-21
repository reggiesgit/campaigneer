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
    VALIDATION_QUEUE(7, "VALIDATION_QUEUE"),
    CORRECTION_QUEUE(8, "CORRECTION_QUEUE"),
    INVALID(9, "INVALID"),
    FINAL_INVALID(10, "FINAL_INVALID"),
    VALID(11, "VALID"),
    PAID(12, "PAID"),
    CLOSED(13, "CLOSED");

    private final int chave;
    private final String valor;

    CampaignStatus(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }
}
