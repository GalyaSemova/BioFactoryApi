package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.dto.CategoryDTO;
import com.dev.biostoreapi.model.dto.SubcategoryDTO;
import com.dev.biostoreapi.service.CategoryService;
import com.dev.biostoreapi.service.SubcategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    
    public CategoryController(CategoryService categoryService, SubcategoryService subcategoryService) {
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllMainCategories() {
        return ResponseEntity.ok(categoryService.getAllMainCategories());
    }

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubcategoryDTO>> getAllSubcategories() {
        return ResponseEntity.ok(subcategoryService.getAllSubcategories());
    }


}
