package com.ufpr.campaigneer.json;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 25/01/2022
 */

public class CampaignReportJSON {

    private String campaignName;
    private String promoterName;
    private int totalParticipations;
    private int notValidated;
    private int correctionQueue;
    private int valid;
    private int paid;
    private int closed;

    public CampaignReportJSON(String campaignName, String promoterName, int totalParticipations, int notValidated, int correctionQueue, int valid, int paid, int closed) {
        this.campaignName = campaignName;
        this.promoterName = promoterName;
        this.totalParticipations = totalParticipations;
        this.notValidated = notValidated;
        this.correctionQueue = correctionQueue;
        this.valid = valid;
        this.paid = paid;
        this.closed = closed;
    }

    public CampaignReportJSON() {
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }

    public int getTotalParticipations() {
        return totalParticipations;
    }

    public void setTotalParticipations(int totalParticipations) {
        this.totalParticipations = totalParticipations;
    }

    public int getNotValidated() {
        return notValidated;
    }

    public void setNotValidated(int notValidated) {
        this.notValidated = notValidated;
    }

    public int getCorrectionQueue() {
        return correctionQueue;
    }

    public void setCorrectionQueue(int correctionQueue) {
        this.correctionQueue = correctionQueue;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }
}
