package com.ufpr.campaigneer.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 04/10/2021
 */

@Entity
@Table(name = "data_correction")
public class DataCorrection extends BasicModel {

    private String code;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participation_id", foreignKey = @ForeignKey(name ="participation_fk"))
    private Participation participation;
    private boolean isValid;

    public DataCorrection(String code, Participation participation, boolean isValid) {
        this.code = code;
        this.participation = participation;
        this.isValid = isValid;
    }

    public DataCorrection() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Participation getParticipation() {
        return participation;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
