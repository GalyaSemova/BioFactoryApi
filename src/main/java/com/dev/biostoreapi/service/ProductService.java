package com.dev.biostoreapi.service;


import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.model.views.ProductView;
import com.dev.biostoreapi.model.views.ProductViewProductPage;

import java.util.List;
import java.util.Set;

public interface ProductService {

    List<ProductDTO> getAllProducts();

//    ProductEntity save(ProductEntity productEntity);

//    ProductEntity addProduct(ProductDTO productDTO, UserEntity user);
    ProductDTO addProduct(ProductDTO productDTO, UserEntity author);

    List<ProductDTO> getAllProductsByUser(Long userId);

    void deleteProduct(Long id);

    ProductView  editProduct(Long id, ProductDTO productDTO);

    Set<ProductViewProductPage> getAllProductsBySubcategory(SubCategoryNameEnum subCategoryNameEnum);

    ProductViewProductPage getProductById(Long id);

//    List<ProductEntity> findAllByUser_id(Long userId);
}
