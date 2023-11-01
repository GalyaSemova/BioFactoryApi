package com.dev.biostoreapi.model.dto;

import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;

import java.util.List;


public class CategoryDTO {

    private MainCategoryNameEnum name;

    private String description;

    private String imgUrl;

    private List<SubcategoryEntity> subcategories;

    public List<SubcategoryEntity> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryEntity> subcategories) {
        this.subcategories = subcategories;
    }

    public MainCategoryNameEnum getName() {
        return name;
    }

    public void setName(MainCategoryNameEnum name) {
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
