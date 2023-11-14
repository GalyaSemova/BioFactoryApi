package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.service.ProductService;
import com.dev.biostoreapi.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
//@CrossOrigin("*")
public class ProductController {

    private final UserService userService;
    private final ProductService productService;


    public ProductController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> addProduct(
            @RequestBody @Valid ProductDTO productDTO,
            @AuthenticationPrincipal UserDetails user) {

        UserEntity author = userService.findByUsername(user.getUsername());




        return
                ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(productService.addProduct(productDTO, author));




//        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        ProductEntity newProduct = productService.addProduct(productDTO, currentUser);
//        productService.addProduct(productDTO);
//        return  new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

}

