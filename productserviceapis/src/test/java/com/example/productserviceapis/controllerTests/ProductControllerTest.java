package com.example.productserviceapis.controllerTests;

import com.example.productserviceapis.controllers.ProductController;
import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.services.ProductService;
import com.example.productserviceapis.dtos.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        createProduct.setId(UUID.randomUUID());

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setImage("Test Image");
        expectedProduct.setPrice(100.00);
        expectedProduct.setCategory("Test Category");
        expectedProduct.setTitle("Test Product");
        expectedProduct.setDescription("Test Description");
        expectedProduct.setId(createProduct.getId());

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

    @Test
    void getAllProductsReturnsEmptyListWhenNoProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(content().string("[]"));
    }

    @Test
    void getAllProductsReturnsListOfProducts() throws Exception {
       ArrayList<GenericProductDto> products = new ArrayList<>();
       products.add(new GenericProductDto());
        products.add(new GenericProductDto());
        products.add(new GenericProductDto());

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(products)));

    }

    @Test
    void getProductByIdReturnsMessageWhenNoProductWithIdFound() throws Exception {
           UUID id = UUID.randomUUID();
        when(productService.getProductById(id))
                .thenThrow(new NotFoundException("Product with id " + id + " not found"));

        mockMvc.perform(get("/products/" + id))
                .andExpect(status().is(404));
    }

    @Test
    void getProductByIdReturnsProductWhenProductFound() throws Exception {
        UUID id = UUID.randomUUID();
        GenericProductDto product = new GenericProductDto();
        product.setId(id);
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setCategory("Test Category");
        product.setPrice(100.00);
        product.setImage("Test Image");

        when(productService.getProductById(id)).thenReturn(product);

        mockMvc.perform(get("/products/" + id))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(product)));
    }

    @Test
    void updateProductById() throws Exception {
        UUID id = UUID.randomUUID();
        GenericProductDto product = new GenericProductDto();
        product.setId(id);
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setImage("Test Image");
        product.setPrice(100.00);
        product.setCategory("Test Category");

        GenericProductDto updatedProduct = new GenericProductDto();
        updatedProduct.setId(product.getId());
        updatedProduct.setTitle("Updated Test Product");
        updatedProduct.setDescription("Updated Test Description");
        updatedProduct.setPrice(200.00);
        updatedProduct.setImage("Updated Test Image");
        updatedProduct.setCategory("Updated Test Category");

        when(productService.updateProduct(id, product))
                .thenReturn(updatedProduct);

        mockMvc.perform(put("/products/" + id)
                        .content(objectMapper.writeValueAsString(product)));

    }

    @Test
    void deleteProductShouldRemoveProduct() throws Exception {
        UUID id = UUID.randomUUID();
        GenericProductDto product = new GenericProductDto();
        product.setId(id);
        product.setTitle("Test Product");
        product.setCategory("Test Category");
        product.setDescription("Test Description");
        product.setTitle("Test Title");
        product.setImage("Test Image");

        when(productService.deleteProduct(id)).thenReturn(product);

        mockMvc.perform(delete("/products/" + id))
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(product)));

    }
}
