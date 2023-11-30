package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.config.LocalDateProvider;
import com.dev.biostoreapi.exceptions.ProductNotFoundException;
import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.model.views.ProductView;
import com.dev.biostoreapi.model.views.ProductViewProductPage;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.service.ProductService;
import com.dev.biostoreapi.service.SubcategoryService;
import com.dev.biostoreapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserService userService;
    private final SubcategoryService subcategoryService;
    private final ModelMapper modelMapper;
//    @Autowired
//    private UserDetailsImpl userDetails;

    private final LocalDateProvider localDateProvider;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, SubcategoryService subcategoryService, ModelMapper modelMapper,
                              LocalDateProvider localDateProvider) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.subcategoryService = subcategoryService;
        this.modelMapper = modelMapper;
        this.localDateProvider = localDateProvider;


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

//        newProduct.setDateAdded(LocalDate.now());
        newProduct.setDateAdded(localDateProvider.now());


        newProduct = productRepository.save(newProduct);
        ProductDTO addedProduct = modelMapper.map(newProduct, ProductDTO.class);
        addedProduct.setSubcategory(subcategory.getName());

        return addedProduct;
    }

    @Override
    public List<ProductDTO> getAllProductsByUser(Long userId) {

        return productRepository.findAllByUser_Id(userId)
                .stream()
                .map(productEntity -> {
                    SubcategoryEntity subcategory = subcategoryService.findByName(productEntity.getSubcategory().getName());
                    ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
                    productDTO.setSubcategory(subcategory.getName());

                    return productDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        if(productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public ProductView editProduct(Long id, ProductDTO productDTO) {
        ProductEntity productToEdit = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        UserEntity user = productToEdit.getUser();
        SubcategoryEntity subcategory = subcategoryService.findByName(productDTO.getSubcategory());


        ProductEntity productReference = modelMapper.map(productDTO, ProductEntity.class);
        productReference.setUser(user);
        productReference.setSubcategory(subcategory);


        if (!productReference.equals(productToEdit)) {
            productToEdit.setSubcategory(productReference.getSubcategory());
            productToEdit.setName(productReference.getName());
            productToEdit.setDescription(productReference.getDescription());
            productToEdit.setImageUrl(productReference.getImageUrl());
            productToEdit.setQuantityAvailable(productReference.getQuantityAvailable());
            productToEdit.setPrice(productReference.getPrice());

            ProductEntity editedProduct = productRepository.save(productToEdit);
            ProductView productView = modelMapper.map(editedProduct, ProductView.class);
            productView.setLastEdited(LocalDateTime.now());
            productView.setSubcategory(editedProduct.getSubcategory().getName());
            return productView;
        }


        return modelMapper.map(productToEdit, ProductView.class);
    }

    @Override
    public Set<ProductViewProductPage> getAllProductsBySubcategory(SubCategoryNameEnum subCategoryNameEnum) {

        return productRepository.findAllBySubcategory_Name(subCategoryNameEnum)
                .stream()
                .map(productEntity -> {
                    SubcategoryEntity subcategory = subcategoryService.findByName(subCategoryNameEnum);
                    ProductViewProductPage productView = modelMapper.map(productEntity, ProductViewProductPage.class);
                    productView.setSubcategory(subcategory.getName());

                    return productView;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public ProductViewProductPage getProductById(Long id) {
       ProductEntity productEntity =  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        SubcategoryEntity subcategory = subcategoryService.findByName(productEntity.getSubcategory().getName());
        ProductViewProductPage product = modelMapper.map(productEntity, ProductViewProductPage.class);
        product.setSubcategory(subcategory.getName());

        return product;
    }
}
