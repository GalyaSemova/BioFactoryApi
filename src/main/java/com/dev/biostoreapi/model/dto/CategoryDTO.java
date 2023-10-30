package com.dev.biostoreapi.model.dto;

import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;


public class CategoryDTO {

    private MainCategoryNameEnum name;

    private String description;

    private String imgUrl;

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
