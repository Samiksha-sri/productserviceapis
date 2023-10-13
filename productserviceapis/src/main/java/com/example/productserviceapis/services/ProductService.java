package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<GenericProductDto> getAllProducts();
    GenericProductDto getProductById(UUID id) throws NotFoundException;

    GenericProductDto postProduct(GenericProductDto productDto);

    GenericProductDto updateProduct(UUID id, GenericProductDto productDto) throws NotFoundException;

    GenericProductDto deleteProduct(UUID id) throws NotFoundException;
}
