package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.CategoryDto;
import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Product;

import java.util.List;

public interface CategoryService {
    List<String> getAllCategories();

    List<GenericProductDto> getProductsInCategory(String categoryName) throws NotFoundException;
}
