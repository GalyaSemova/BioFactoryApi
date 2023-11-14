package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.service.ProductService;
import com.dev.biostoreapi.service.SubcategoryService;
import com.dev.biostoreapi.service.UserService;
import com.dev.biostoreapi.service.impl.securityImpl.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserService userService;
    private final SubcategoryService subcategoryService;
    private final ModelMapper modelMapper;
//    @Autowired
//    private UserDetailsImpl userDetails;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, SubcategoryService subcategoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.subcategoryService = subcategoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        return this.productRepository.findAll()
                .stream()
                .map(productEntity -> {
                    ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
//                        UserEntity user = userService.findByEmail(productEntity.getUser().getEmail());
                    SubcategoryEntity subcategory = subcategoryService.findByName(productEntity.getSubcategory().getName());
                    productDTO.setSubcategory(subcategory.getName());
                    return productDTO;

                })
                .collect(Collectors.toList());

    }

    @Override
//    public ProductEntity addProduct(ProductDTO productDTO, UserEntity user) {
    public ProductDTO addProduct(ProductDTO productDTO, UserEntity author) {

//        UserEntity author = userService.findByUsername(userDetails.getUsername());
        ProductEntity newProduct = modelMapper.map(productDTO, ProductEntity.class);
        SubcategoryEntity subcategory = subcategoryService.findByName(productDTO.getSubcategory());
        newProduct.setUser(author);


        newProduct.setSubcategory(subcategory);
        newProduct.setDateAdded(LocalDate.now());


        newProduct = productRepository.save(newProduct);

        return modelMapper.map(newProduct, ProductDTO.class);
    }
}
