package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.dto.SubcategoryDTO;
import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.model.views.SubcategoryStoreView;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.repository.SubcategoryRepository;
import com.dev.biostoreapi.service.SubcategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository, ModelMapper modelMapper, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<SubcategoryDTO> getAllSubcategories() {
        return subcategoryRepository.findAll()
                .stream()
                .map(subcategoryEntity -> {
                    SubcategoryDTO subcategoryDTO = modelMapper.map(subcategoryEntity, SubcategoryDTO.class);
                    CategoryEntity category = categoryRepository.findByName(subcategoryEntity.getCategory().getName()).orElse(null);
                    subcategoryDTO.setCategory(category.getName());
//                    Set<ProductEntity> products = productRepository.findAllBySubcategory_Name(subcategoryEntity.getName());
//                    subcategoryDTO.setProducts(products);
                    return subcategoryDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoryEntity findByName(SubCategoryNameEnum subcategory) {
        return this.subcategoryRepository.findByName(subcategory);
    }

    @Override
    public List<SubcategoryStoreView> getAllSubcategoriesByCategoryName(MainCategoryNameEnum categoryName) {
        return subcategoryRepository.findAllByCategory_Name(categoryName).stream()
                .map(subcategoryEntity -> modelMapper.map(subcategoryEntity, SubcategoryStoreView.class))
                .collect(Collectors.toList());
    }
}
