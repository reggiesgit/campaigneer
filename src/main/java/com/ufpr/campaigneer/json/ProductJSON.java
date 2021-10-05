package com.ufpr.campaigneer.json;

import com.ufpr.campaigneer.enums.ClassOfGood;
import com.ufpr.campaigneer.model.Brand;
import com.ufpr.campaigneer.model.Product;

import java.util.ArrayList;
import java.util.List;

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
    private Brand manufacturer;

    public ProductJSON(Long id, String name, String ean, ClassOfGood classOfGood, Brand manufacturer) {
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
        result.setManufacturer(json.getManufacturer());
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

    public static List<Product> mapJson(List<ProductJSON> json) {
        List<Product> result = new ArrayList<>();
        json.forEach(each -> {
            result.add(mapJson(each));
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

    public Brand getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Brand manufacturer) {
        this.manufacturer = manufacturer;
    }
}
