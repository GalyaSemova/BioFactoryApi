package com.dev.biostoreapi.model.entity;

import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private MainCategoryNameEnum name;
    @Column
    private String description;
    @Column(name = "img_url")
    private String imgUrl;
//    @OneToMany
//    private Set<SubcategoryEntity> subcategories;

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

//    public Set<SubcategoryEntity> getSubcategories() {
//        return subcategories;
//    }

//    public void setSubcategories(Set<SubcategoryEntity> subcategories) {
//        this.subcategories = subcategories;
//    }
}
