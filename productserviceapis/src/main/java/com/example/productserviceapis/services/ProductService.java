package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto postProduct(GenericProductDto productDto);

    ProductDto updateProduct(String id, ProductDto productDto);

    void deleteProduct(String id) throws NotFoundException;
}
