package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.dto.SubcategoryDTO;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryDTO> getAllSubcategories();
}
