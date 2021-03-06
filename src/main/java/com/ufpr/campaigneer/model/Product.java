package com.ufpr.campaigneer.model;

import com.ufpr.campaigneer.enums.ClassOfGood;

import javax.persistence.*;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 06/02/2021
 */

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String ean;
    @Enumerated
    private ClassOfGood classOfGood;
    @ManyToOne
    private Brand manufacturer;

    public Product(int id, String name, String ean, ClassOfGood classOfGood, Brand manufacturer) {
        this.id = id;
        this.name = name;
        this.ean = ean;
        this.classOfGood = classOfGood;
        this.manufacturer = manufacturer;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
