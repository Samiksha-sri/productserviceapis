package com.example.productserviceapis.controllers;

import com.example.productserviceapis.dtos.CategoryDto;
import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Product;
import com.example.productserviceapis.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping ("/{categoryName}")
    public List<GenericProductDto> getProductsInCategory(@PathVariable String categoryName) throws NotFoundException {

        return categoryService.getProductsInCategory(categoryName);
    }
}
