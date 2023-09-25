package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.CategoryDto;
import com.example.productserviceapis.dtos.ProductDto;
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
            productDto.setCategory(product.getCategory());
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
    public CategoryDto postCategory(CategoryDto categoryDto) {
        Category category = new Category();
        List<ProductDto> productDtoList = categoryDto.getProductList();
        category.setName(categoryDto.getName());
        for(ProductDto productDto : productDtoList) {
            Product product = new Product();
            product.setTitle(productDto.getTitle());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setImage(productDto.getImage());
            product.setPrice(productDto.getPrice());
            category.getProductList().add(product);
        }
        categoryRepository.save(category);
        return convertToDto(category);
    }

    @Override
    public List<ProductDto> getProductsInCategory(String categoryName) {
       Category category = categoryRepository.findCategoryByName(categoryName);
        List<ProductDto> productDtoList = new ArrayList<>();
         if(category != null) {
             for (Product product : category.getProductList()) {
                 ProductDto productDto = new ProductDto();
                 productDto.setTitle(product.getTitle());
                 productDto.setDescription(product.getDescription());
                 productDto.setCategory(product.getCategory());
                 productDto.setImage(product.getImage());
                 productDto.setPrice(product.getPrice());
                 productDtoList.add(productDto);
             }

         }
        return productDtoList;
    }
}
