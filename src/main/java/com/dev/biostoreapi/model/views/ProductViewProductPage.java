package com.dev.biostoreapi.model.views;

import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductViewProductPage {
    private String name;
    private String description;
    private SubCategoryNameEnum subcategory;
    private BigDecimal price;
    private int quantityAvailable;
    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SubCategoryNameEnum getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategoryNameEnum subcategory) {
        this.subcategory = subcategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
