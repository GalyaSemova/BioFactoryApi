package com.dev.biostoreapi.service;

import com.dev.biostoreapi.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllMainCategories();
}
