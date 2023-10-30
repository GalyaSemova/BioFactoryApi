package com.dev.biostoreapi.model.entity;

import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "subcategories")
public class SubcategoryEntity extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private SubCategoryNameEnum name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "img_url")
    private String imgUrl;
    @OneToMany
    private Set<ProductEntity> products;
    @ManyToOne
    private CategoryEntity category;

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

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
