package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.ClassOfGood;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.model.Product;

import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 03/07/2021
 */

public class ProductJSON {

    private int id;
    private String name;
    private String ean;
    private ClassOfGood classOfGood;
    private Brand manufacturer;

    public ProductJSON(int id, String name, String ean, ClassOfGood classOfGood, Brand manufacturer) {
        this.id = id;
        this.name = name;
        this.ean = ean;
        this.classOfGood = classOfGood;
        this.manufacturer = manufacturer;
    }

    public ProductJSON() {
    }

    public static Product mapJson(ProductJSON json) {
        Product result = new Product();
        result.setId(json.getId());
        result.setName(json.getName());
        result.setManufacturer(json.getManufacturer());
        result.setClassOfGood(json.getClassOfGood());
        result.setEan(json.getEan());
        return result;
    }

    public static ProductJSON map(Product product) {
        ProductJSON result = new ProductJSON();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setManufacturer(product.getManufacturer());
        result.setClassOfGood(product.getClassOfGood());
        result.setEan(product.getEan());
        return result;
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
