package com.example.qlsp_orm.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", nullable = false, length = 100)
    private String name;
    @Column(nullable = false, precision = 15,scale = 2)
    private BigDecimal product_price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false, length = 100)
    private String manufacturer;

    public Product() {
    }
    public Product(String product_name, BigDecimal product_price, String description, String manufacturer) {
        this.name = product_name;
        this.product_price = product_price;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return name;
    }

    public void setProduct_name(String product_name) {
        this.name = product_name;
    }

    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
