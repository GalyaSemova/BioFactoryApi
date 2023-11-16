package com.dev.biostoreapi.service.impl;

import com.dev.biostoreapi.config.LocalDateProvider;
import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.*;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import com.dev.biostoreapi.model.enums.UserRoleEnum;
import com.dev.biostoreapi.repository.ProductRepository;
import com.dev.biostoreapi.service.SubcategoryService;
import com.dev.biostoreapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Role;
import org.springframework.context.support.BeanDefinitionDsl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {

    @ExtendWith(MockitoExtension.class)
    private ProductServiceImpl serviceToTest;

    @Mock
    private ProductRepository mockProductRepository;
    @Mock
    private UserService mockUserService;
    @Mock
    private ProductDTO testProductDTO;
    @Mock
    private ProductEntity testProductEntity;

//    private SubcategoryEntity testSubcategoryEntity;
//    private UserEntity testUserEntity;
    @Mock
    private SubcategoryService mockSubcategoryService;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private LocalDateProvider mockLocalDateProvider;

    @BeforeEach
    void setUp() {
        serviceToTest = new ProductServiceImpl(
                mockProductRepository,
                mockUserService,
                mockSubcategoryService,
                mockModelMapper,
                mockLocalDateProvider
        );


        testProductDTO = new ProductDTO() {
            {
                setId(1L);
              setName("Test Prooduct");
              setDescription("Description");
              setPrice(BigDecimal.TEN);
              setQuantityAvailable(5);
              setImageUrl("test-image-url");
              setSubcategory(createSubcategoryEntity().getName());
            }
        };
        testProductEntity = new ProductEntity() {
            {   setId(1L);
                setName("Test Prooduct");
                setDescription("Description");
                setPrice(BigDecimal.TEN);
                setQuantityAvailable(5);
                setImageUrl("test-image-url");
                setSubcategory(createSubcategoryEntity());
                setDateAdded(mockLocalDateProvider.now());
                setUser(createUserEntity());
            }
        };
        // Mock the necessary repository and service behavior
//        when(mockSubcategoryService.findByName(productDTO.getSubcategory())).thenReturn(subcategoryEntity);
        when(mockModelMapper.map(testProductDTO, ProductEntity.class)).thenReturn(testProductEntity);
        when(mockLocalDateProvider.now()).thenReturn(LocalDate.now());
        when(mockProductRepository.save(Mockito.any(ProductEntity.class))).thenReturn(testProductEntity);

    }
//  TODO testAdd product. It does not work due to subcategory setup
    @Test
    public void testAddProduct() {
        UserEntity user = createUserEntity();
        serviceToTest.addProduct(testProductDTO, user);
        when(mockProductRepository.save(Mockito.any(ProductEntity.class))).thenReturn(testProductEntity);



    }

    private static SubcategoryEntity createSubcategoryEntity() {
        SubcategoryEntity subcategoryEntity = new SubcategoryEntity();
        subcategoryEntity.setId(1L);
        subcategoryEntity.setName(SubCategoryNameEnum.HANDBAGS);

        CategoryEntity category1 = new CategoryEntity();
        category1.setId(1L);
        category1.setImgUrl("test");
        category1.setName(MainCategoryNameEnum.HANDMADE);
        category1.setDescription("category 1");
        category1.setSubcategories(new HashSet<>());
        category1.getSubcategories().add(subcategoryEntity);

        subcategoryEntity.setCategory(category1);
        subcategoryEntity.setImgUrl("Test img");
        subcategoryEntity.setProducts(new HashSet<>());
        subcategoryEntity.setDescription("test");

        return subcategoryEntity;
    }


   public static UserEntity createUserEntity() {

        UserEntity user = new UserEntity();
           user.setFirstName("Pesho");
           user.setLastName("Pesho");
           user.setActive(true);
           user.setUsername("pesho123");
           user.setEmail("pesho@example.com");
           user.setPassword("password");
           user.setAddress("Some Address");
           user.setPhoneNumber("1234567890");

           // Creating dummy roles
           List<UserRoleEntity> roles = createDummyRoles();
           user.setRoles(roles);

           return user;


   }

    public static List<UserRoleEntity> createDummyRoles() {
        // Assuming UserRoleEntity has a constructor that takes a role name
        UserRoleEntity userRoleUser = new UserRoleEntity();
        userRoleUser.setName(UserRoleEnum.ROLE_USER);
        UserRoleEntity userRoleAdmin = new UserRoleEntity();
        userRoleAdmin.setName(UserRoleEnum.ROLE_ADMIN);

        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(userRoleUser);
        roles.add(userRoleAdmin);

        return roles;
    }




}
