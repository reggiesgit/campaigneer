package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.ClassOfGood;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
@Table(name = "product")
public class Product extends BasicModel {

    private String name;
    private String ean;
    @Enumerated
    private ClassOfGood classOfGood;
    @NotNull
    @ManyToOne
    private Brand manufacturer;

    public Product(String name, String ean, ClassOfGood classOfGood, Brand manufacturer) {
        this.name = name;
        this.ean = ean;
        this.classOfGood = classOfGood;
        this.manufacturer = manufacturer;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public ClassOfGood getClassOfGood() {
        return classOfGood;
    }

    public void setClassOfGood(ClassOfGood classOfGood) {
        this.classOfGood = classOfGood;
    }

    public Brand getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Brand manufacturer) {
        this.manufacturer = manufacturer;
    }
}
