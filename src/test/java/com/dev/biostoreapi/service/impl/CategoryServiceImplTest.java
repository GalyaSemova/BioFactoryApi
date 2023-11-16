package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.CategoryDTO;
import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.repository.SubcategoryRepository;
import com.dev.biostoreapi.service.CategoryService;
import com.dev.biostoreapi.service.SubcategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @ExtendWith(MockitoExtension.class)
    private CategoryServiceImpl serviceToTest;

    @Mock
    private CategoryRepository mockCategoryRepository;
    @Mock
    private SubcategoryRepository mockSubcategoryRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @BeforeEach
    void setUp() {
        serviceToTest = new CategoryServiceImpl(
                mockCategoryRepository,
                mockSubcategoryRepository,
                mockModelMapper
        );
    }

    @Test
    public void testGetAllMainCategories() {
        // Arrange
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        SubcategoryRepository subcategoryRepositoryMock = mock(SubcategoryRepository.class);
        ModelMapper modelMapperMock = mock(ModelMapper.class);

        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepositoryMock, subcategoryRepositoryMock, modelMapperMock);

        // Mock data
        CategoryEntity categoryEntity1 = createCategoryEntity(1L, MainCategoryNameEnum.BIO);
        CategoryEntity categoryEntity2 = createCategoryEntity(2L, MainCategoryNameEnum.HANDMADE);
        SubcategoryEntity subcategoryEntity1 = createSubcategoryEntity(1L, SubCategoryNameEnum.COSMETICS, categoryEntity1);
        SubcategoryEntity subcategoryEntity2 = createSubcategoryEntity(2L, SubCategoryNameEnum.ACCESSORIES, categoryEntity2);


        when(mockCategoryRepository.findAll()).thenReturn(Arrays.asList(categoryEntity1, categoryEntity2));
//        when(mockSubcategoryRepository.findAllByCategory_Name(categoryEntity1.getName())).thenReturn(S);
//        when(mockSubcategoryRepository.findAllByCategory_Name(categoryEntity2.getName())).thenReturn(Collections.singleton(subcategoryEntity2));

        when(mockModelMapper.map(categoryEntity1, CategoryDTO.class)).thenReturn(new CategoryDTO());
        when(mockModelMapper.map(categoryEntity2, CategoryDTO.class)).thenReturn(new CategoryDTO());

        // Act
        List<CategoryDTO> result = serviceToTest.getAllMainCategories();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.get(0));


        Mockito.verify(mockModelMapper).map(categoryEntity1, CategoryDTO.class);
    }

    private static CategoryEntity createCategoryEntity(Long id, MainCategoryNameEnum name) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(id);
        categoryEntity.setName(name);
        return categoryEntity;
    }

    private static SubcategoryEntity createSubcategoryEntity(Long id, SubCategoryNameEnum name, CategoryEntity category) {
        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
        subcategoryEntity.setId(id);
        subcategoryEntity.setName(name);
        subcategoryEntity.setCategory(category);
        return subcategoryEntity;
    }

}
