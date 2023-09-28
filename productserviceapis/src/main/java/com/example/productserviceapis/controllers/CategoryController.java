package com.example.productserviceapis.controllers;

import com.example.productserviceapis.dtos.CategoryDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.services.CategoryService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CategoryDto postCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.postCategory(categoryDto);
    }

    @GetMapping ("/{categoryName}")
    public List<ProductDto> getProductsInCategory(@PathVariable String categoryName) {
        return categoryService.getProductsInCategory(categoryName);
    }
}
