package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.CategoryDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.models.Category;

import java.util.List;

public interface CategoryService {
    List<String> getAllCategories();

    CategoryDto postCategory(CategoryDto categoryDto);
    List<ProductDto> getProductsInCategory(String categoryName);
}
