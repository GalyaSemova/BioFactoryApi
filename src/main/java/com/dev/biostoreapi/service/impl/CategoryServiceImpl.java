package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.CategoryDTO;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> getAllMainCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDTO.class))
                .collect(Collectors.toList());
    }
}
