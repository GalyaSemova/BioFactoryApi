package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.dto.SubcategoryDTO;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryDTO> getAllSubcategories();

    SubcategoryEntity findByName(SubCategoryNameEnum subcategory);
}
