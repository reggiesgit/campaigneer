package com.ufpr.campaigneer.enums;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

public enum ClassOfGood {

    TELEVISION(0, "ELECTRONIC"),
    SMARTPHONE(1, "SMARTPHONE"),
    LAPTOP(2, "LAPTOP"),
    SOUNDBAR(3, "SOUNDBAR");

    private int chave;
    private String valor;

    ClassOfGood(int chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }
}
