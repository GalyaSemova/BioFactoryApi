package com.dev.biostoreapi.web;

import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.testUtils.TestDataUtil;
import com.dev.biostoreapi.testUtils.UserTestDataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = com.dev.biostoreapi.BioStoreApiApplication.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTestIT {

    private static final String TEST_USER_EMAIL = "user@example.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserTestDataUtil userTestDataUtil;

    @Autowired
    private TestDataUtil testDataUtil;

    @BeforeEach
    void setUp() {
        testDataUtil.cleanAllTestData();
        userTestDataUtil.cleanUp();
    }

    @AfterEach
    void tearDown() {
        testDataUtil.cleanAllTestData();
        userTestDataUtil.cleanUp();
    }


    @Test
    @WithMockUser(username = "testUser", roles = "USER")
    public void testGetAllProducts() throws Exception {

        UserEntity owner = userTestDataUtil.createTestUser("test@example.com");
        ProductEntity productEntity = testDataUtil.createTestProduct(owner);
        // Mock the service method to return a specific list of products
        // when(productService.getAllProducts()).thenReturn(someListOfProducts);
        System.out.println(productEntity);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/all")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(productEntity.getId()))
                .andExpect(jsonPath("$[0].name").value(productEntity.getName()));

    }

//    @Test
//
//    public void testGetAllProducts() throws Exception {
//
//        UserEntity owner = userTestDataUtil.createTestUser("test@example.com");
//        ProductEntity productEntity = testDataUtil.createTestProduct(owner);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
////                .andExpect(jsonPath("$").isArray())
////                .andExpect(jsonPath("$", hasSize(4)));
//    }


//    @Test
//    public void testAnonymousDeletionFails() throws Exception {
//
//        UserEntity owner = userTestDataUtil.createTestUser("test@example.com");
//        ProductEntity productEntity = testDataUtil.createTestProduct(owner);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/api/v1/products/{id}", productEntity.getId())
//                        .with(csrf())
//        )
//                .andExpect(status().isNoContent());
//    }

//    @Test
//    void

//     @Test
//    @WithMockUser(username = "user@example.com", roles = {"ADMIN"})
//    public void testDeleteContact_Success() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/{id}", "4")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//
//    }




}
