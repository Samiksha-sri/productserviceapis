package com.example.productserviceapis.controllerTests;

import com.example.productserviceapis.controllers.ProductController;
import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.services.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void postProductShouldAddNewProduct() throws Exception {
        GenericProductDto createProduct = new GenericProductDto();
        createProduct.setTitle("Test Product");
        createProduct.setDescription("Test Description");
        createProduct.setCategory("Test Category");
        createProduct.setPrice(100.00);
        createProduct.setImage("Test Image");

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setImage("Test Image");
        expectedProduct.setPrice(100.00);
        expectedProduct.setCategory("Test Category");
        expectedProduct.setTitle("Test Product");
        expectedProduct.setDescription("Test Description");
        expectedProduct.setId(1L);

        when(
                productService.postProduct(any())
        ).thenReturn(expectedProduct);


        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProduct))
        ).andExpect(
             content().string(objectMapper.writeValueAsString(expectedProduct))
        ).andExpect(
                status().is(200)
        );

    }
}
