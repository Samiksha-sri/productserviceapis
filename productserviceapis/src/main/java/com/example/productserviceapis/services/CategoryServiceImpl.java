package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.CategoryDto;
import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Product;
import com.example.productserviceapis.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : category.getProductList()) {
            ProductDto productDto = new ProductDto();
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory().getName());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            productDtoList.add(productDto);
        }
        categoryDto.setProductList(productDtoList);
        return categoryDto;
    }

    @Override
    public List<String> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        List<String> categoryList = new ArrayList<>();

        for(Category category : categories) {
           categoryList.add(category.getName());
        }
        return categoryList;
    }



    @Override
    public List<GenericProductDto> getProductsInCategory(String categoryName) throws NotFoundException {
    Category category = categoryRepository.findCategoryByName(categoryName);
    List<GenericProductDto> genericProductDtoList = new ArrayList<>();

    for(Product product : category.getProductList()){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getAmount());
        genericProductDto.setId(product.getId());
        genericProductDtoList.add(genericProductDto);
    }
        return genericProductDtoList;
    }
}
