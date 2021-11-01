package com.ufpr.campaigneer.enums;

import com.ufpr.campaigneer.model.Participation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    BAD_PRODUCT(7, "BAD_PRODUCT"),
    BAD_INVOICE(8, "BAD_INVOICE"),
    INVALID(9, "INVALID"),
    VALIDATION_QUEUE(10, "VALIDATION_QUEUE"),
    CORRECTION_QUEUE(11, "CORRECTION_QUEUE"),
    FINAL_INVALID(12, "FINAL_INVALID"),
    VALID(13, "VALID"),
    PAID(14, "PAID"),
    CLOSED(15, "CLOSED");

    private final int chave;
    private final String valor;

    CampaignStatus(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public static List<CampaignStatus> fromViolations(Participation part, Set<ViolationType> violations) {
        List<CampaignStatus> problems = new ArrayList<>();
        if (!violations.isEmpty()) {
            if (violations.contains(CampaignStatus.BAD_PURCHASE_DATE)) {
                part.setCampaignStatus(CampaignStatus.BAD_PURCHASE_DATE);
                problems.add(CampaignStatus.BAD_PURCHASE_DATE);
            }
            if (violations.contains(ViolationType.REGISTRATION_VIOLATION)) {
                part.setCampaignStatus(CampaignStatus.BAD_REGISTRATION_DATE);
                problems.add(CampaignStatus.BAD_REGISTRATION_DATE);
            }
            if (violations.contains(ViolationType.LOCALE_VIOLATION)) {
                part.setCampaignStatus(CampaignStatus.BAD_LOCALE);
                problems.add(CampaignStatus.BAD_LOCALE);
            }
            if (violations.contains(ViolationType.TRADER_VIOLATION)) {
                part.setCampaignStatus(CampaignStatus.BAD_TRADER);
                problems.add(CampaignStatus.BAD_TRADER);
            }
            if (violations.contains(ViolationType.PRODUCT_VIOLATION)) {
                part.setCampaignStatus(CampaignStatus.BAD_PRODUCT);
                problems.add(CampaignStatus.BAD_PRODUCT);
            }
            if (violations.contains(ViolationType.INVOICE_VIOLATION)) {
                part.setCampaignStatus(CampaignStatus.BAD_INVOICE);
                problems.add(CampaignStatus.BAD_INVOICE);
            }
        }
        if (violations.size() > 1) {
            part.setCampaignStatus(CampaignStatus.INVALID);
        }
        return problems;
    }
}
