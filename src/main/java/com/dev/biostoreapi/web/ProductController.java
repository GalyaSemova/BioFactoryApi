package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.service.ProductService;
import com.dev.biostoreapi.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final UserService userService;
    private final ProductService productService;


    public ProductController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductEntity> getAllProducts() {
        return this.productService.getAllProducts();
    }


}
