package com.dev.biostoreapi.service;


import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

//    ProductEntity save(ProductEntity productEntity);

//    ProductEntity addProduct(ProductDTO productDTO, UserEntity user);
    ProductDTO addProduct(ProductDTO productDTO, UserEntity author);

    List<ProductDTO> getAllProductsByUser(Long userId);

    void deleteProduct(Long id);

//    List<ProductEntity> findAllByUser_id(Long userId);
}
