package com.dev.biostoreapi.model.dto;

import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.model.jwt.response.JwtResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @NotNull(message = "Subcategory cannot be null")
    private SubCategoryNameEnum subcategory;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private BigDecimal price;
    @PositiveOrZero(message = "Quantity available must be a positive number or zero")
    private int quantityAvailable;
    private String imageUrl;

//    private String authorEmail;


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

//    public String getAuthorEmail() {
//        return authorEmail;
//    }
//
//    public void setAuthorEmail(String authorEmail) {
//        this.authorEmail = authorEmail;
//    }
}

