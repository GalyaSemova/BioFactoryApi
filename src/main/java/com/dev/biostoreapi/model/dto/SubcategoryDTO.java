package com.dev.biostoreapi.model.dto;

import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

public class SubcategoryDTO {

    private SubCategoryNameEnum name;
    private String description;

    private String imgUrl;
//    private List<ProductEntity> products;

    private MainCategoryNameEnum category;

    public MainCategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(MainCategoryNameEnum category) {
        this.category = category;
    }

    public SubCategoryNameEnum getName() {
        return name;
    }

    public void setName(SubCategoryNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
