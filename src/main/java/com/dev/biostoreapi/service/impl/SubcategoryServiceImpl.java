package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.SubcategoryDTO;
import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.repository.SubcategoryRepository;
import com.dev.biostoreapi.service.SubcategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<SubcategoryDTO> getAllSubcategories() {
        return subcategoryRepository.findAll()
                .stream()
                .map(subcategoryEntity -> {
                    SubcategoryDTO subcategoryDTO = modelMapper.map(subcategoryEntity, SubcategoryDTO.class);
                    CategoryEntity category = categoryRepository.findByName(subcategoryEntity.getCategory().getName()).orElse(null);
                    subcategoryDTO.setCategory(category.getName());

                    return subcategoryDTO;
                })
                .collect(Collectors.toList());
    }
}
