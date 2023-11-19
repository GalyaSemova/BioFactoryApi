package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.model.views.ProductView;
import com.dev.biostoreapi.service.ProductService;
import com.dev.biostoreapi.service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @GetMapping("/{subcategory}")
    public ResponseEntity<Set<ProductView>> getAllProductsBySubcategoryName(@PathVariable("subcategory")SubCategoryNameEnum subCategoryNameEnum) {
        return ResponseEntity.ok(productService.getAllProductsBySubcategory(subCategoryNameEnum));
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<List<ProductDTO>> getAllProductsByUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(productService.getAllProductsByUser(userId));
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(
            @RequestBody @Valid ProductDTO productDTO,
            BindingResult bindingResult,
            @AuthenticationPrincipal UserDetails user) {

        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validationErrors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }

        UserEntity author = userService.findByUsername(user.getUsername());
        return
                ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(productService.addProduct(productDTO, author));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetails user) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@RequestBody @Valid ProductDTO productDTO,
                                                   BindingResult bindingResult,
                                                   @PathVariable Long id,
                                                   @AuthenticationPrincipal UserDetails user) {


        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                validationErrors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }

        return ResponseEntity.ok()
                .body(productService.editProduct(id, productDTO));
    }




}

