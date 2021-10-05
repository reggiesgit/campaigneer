package com.ufpr.campaigneer.enums;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

public enum CampaignType {

    GIFT(0, "GIFT"),
    CASHBACK(1, "CASHBACK"),
    WARRANTY(2, "WARRANTY");

    private int chave;
    private String valor;

    CampaignType(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }
}
