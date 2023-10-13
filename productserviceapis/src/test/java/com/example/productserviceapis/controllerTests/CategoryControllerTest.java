package com.example.productserviceapis.controllerTests;

import com.example.productserviceapis.controllers.CategoryController;
import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Price;
import com.example.productserviceapis.models.Product;
import com.example.productserviceapis.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void returnEmptyListWhenNoCategory() throws Exception {
        List<String> expectedList = new ArrayList<>();
        when(categoryService.getAllCategories()).thenReturn(expectedList);

        mockMvc.perform(get("/categories"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void returnAllCategoryList() throws Exception {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Test Category");
        when(categoryService.getAllCategories()).thenReturn(expectedList);

        mockMvc.perform(get("/categories"))
                .andExpect(MockMvcResultMatchers.content().string("[\"Test Category\"]"));
    }

    @Test
    void returnEmptyListWhenNoProducts() throws Exception {

        when(categoryService.getProductsInCategory("Test Category"))
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/categories/Test Category"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void returnListOfProductsInCategory() throws Exception {
        ArrayList<GenericProductDto> expectedList = new ArrayList<>();
        Category category = new Category();
        category.setName("Test Category");
        Price price = new Price();
        price.setAmount(100.99);

        GenericProductDto productDto = new GenericProductDto();
        productDto.setTitle("Test Product");
        productDto.setDescription("Test Description");
        productDto.setCategory("Test Category");
        productDto.setPrice(100.99);
        productDto.setImage("Test Image");
        productDto.setId(UUID.randomUUID());
        expectedList.add(productDto);


        when(categoryService.getProductsInCategory("Test Category"))
                .thenReturn(expectedList);

        mockMvc.perform(get("/categories/Test Category"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(expectedList)));
    }
}
