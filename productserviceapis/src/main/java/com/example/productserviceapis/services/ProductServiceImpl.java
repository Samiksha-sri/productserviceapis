package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.models.Product;
import com.example.productserviceapis.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    private  ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
    @Override
    public List<ProductDto> getAllProducts() {

      List<Product> productList = productRepository.findAll();
      List<ProductDto> productDtoList = new ArrayList<>();

      for(Product product : productList) {
          ProductDto productDto = convertToDto(product);
          productDtoList.add(productDto);
      }

        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) throws NotFoundException {
      Optional<Product> product =  productRepository.findById(UUID.fromString(String.valueOf(id)));

        if(!product.isEmpty()) {
            throw new NotFoundException("Product not found");

        }

        return convertToDto(product.get());
    }
}
