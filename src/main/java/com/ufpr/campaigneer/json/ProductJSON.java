package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.ClassOfGood;
import com.ufpr.campaigneer.model.Product;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 03/07/2021
 */

public class ProductJSON {

    private Long id;
    private String name;
    private String ean;
    private ClassOfGood classOfGood;
    private BrandJSON manufacturer;

    public ProductJSON(Long id, String name, String ean, ClassOfGood classOfGood, BrandJSON manufacturer) {
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
        result.setClassOfGood(json.getClassOfGood());
        result.setEan(json.getEan());
        if (json.getManufacturer() != null) {
            result.setManufacturer(BrandJSON.mapJson(json.getManufacturer()));
        }
        return result;
    }

    public static ProductJSON map(Product product) {
        ProductJSON result = new ProductJSON();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setManufacturer(BrandJSON.map(product.getManufacturer()));
        result.setClassOfGood(product.getClassOfGood());
        result.setEan(product.getEan());
        return result;
    }

    public static Set<Product> mapJson(Set<ProductJSON> json) {
        Set<Product> result = new HashSet<>();
        json.forEach(each -> {
            result.add(mapJson(each));
        });
        return result;
    }

    public static Set<ProductJSON> map(Set<Product> products) {
        Set<ProductJSON> result = new HashSet<>();
        products.forEach(each -> {
            result.add(map(each));
        });
        return result;
    }

    public static Set<Product> replace(Set<ProductJSON> products) {
        Set<Product> result = new HashSet<>();
        products.forEach(each -> {
            Product replaced = new Product();
            if (each.getName() != null)  replaced.setName(each.getName());
            if (each.getEan() != null) replaced.setEan(each.getEan());
            if (each.getClassOfGood() != null) replaced.setClassOfGood(each.getClassOfGood());
            if (each.getManufacturer() != null) replaced.setManufacturer(BrandJSON.mapJson(each.getManufacturer()));
            result.add(replaced);
        });
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BrandJSON getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(BrandJSON manufacturer) {
        this.manufacturer = manufacturer;
    }
}
