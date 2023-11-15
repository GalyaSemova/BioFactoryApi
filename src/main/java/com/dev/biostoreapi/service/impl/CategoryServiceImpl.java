package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.CategoryDTO;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.repository.SubcategoryRepository;
import com.dev.biostoreapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final SubcategoryRepository subcategoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> getAllMainCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryEntity -> {
                   CategoryDTO categoryDTO =  modelMapper.map(categoryEntity, CategoryDTO.class);
//                   Set<SubcategoryEntity> subcategories = subcategoryRepository.findAllByCategory_Name(categoryEntity.getName());
//                   categoryDTO.setSubcategories(subcategories);
                   return categoryDTO;
                })
                .collect(Collectors.toList());
    }
}
