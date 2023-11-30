package com.dev.biostoreapi.testUtils;

import com.dev.biostoreapi.config.LocalDateProvider;
import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.repository.CategoryRepository;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class TestDataUtil {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LocalDateProvider mockLocalDateProvider;

    public ProductEntity createTestProduct(UserEntity owner) {
//        SubcategoryEntity subcategory1 = new SubcategoryEntity();
//        subcategory1.setName(SubCategoryNameEnum.HANDBAGS);
//        subcategory1.setDescription("subcategory1");
//        subcategory1.setImgUrl("imgUrl");
//        SubcategoryEntity subcategory2 = new SubcategoryEntity();
//        subcategory1.setName(SubCategoryNameEnum.COSMETICS);
//
////        create test Category
//        CategoryEntity category = new CategoryEntity();
//        category.setName(MainCategoryNameEnum.HANDMADE);
//        category.setDescription("Test description");
//        category.setImgUrl("TestImg");
//        category.setSubcategories(Set.of(subcategory1, subcategory2));
//        categoryRepository.save(category);

        CategoryEntity categoryEntity1 = createCategoryEntity(1L, MainCategoryNameEnum.BIO);
        CategoryEntity categoryEntity2 = createCategoryEntity(2L, MainCategoryNameEnum.HANDMADE);
        SubcategoryEntity subcategoryEntity1 = createSubcategoryEntity(1L, SubCategoryNameEnum.COSMETICS, categoryEntity1);
        SubcategoryEntity subcategoryEntity2 = createSubcategoryEntity(2L, SubCategoryNameEnum.ACCESSORIES, categoryEntity2);


        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setDescription("product description");
        product.setPrice(BigDecimal.valueOf(10.0));
        product.setQuantityAvailable(5);
        product.setImageUrl("image url");
//        product.setSubcategory(category.getSubcategories().iterator().next());
        product.setSubcategory(subcategoryEntity1);
        product.setUser(owner);
        product.setDateAdded(mockLocalDateProvider.now());

        return productRepository.save(product);
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

    public void cleanAllTestData() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }
}
