package com.ufpr.campaigneer.enums;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

public enum CampaignType {

    GIFT(1, "GIFT"),
    CASHBACK(2, "CASHBACK"),
    WARRANTY(3, "WARRANTY");

    private int chave;
    private String valor;

    CampaignType(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }
}
