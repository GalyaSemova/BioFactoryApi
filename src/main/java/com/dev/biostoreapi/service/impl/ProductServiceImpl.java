package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return this.productRepository.findAll();
    }
}
