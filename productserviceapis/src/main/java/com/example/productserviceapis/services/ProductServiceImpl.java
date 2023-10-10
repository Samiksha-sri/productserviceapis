package com.example.productserviceapis.services;

import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Price;
import com.example.productserviceapis.models.Product;
import com.example.productserviceapis.repositories.CategoryRepository;
import com.example.productserviceapis.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    private  ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    @Override
    public GenericProductDto postProduct(GenericProductDto productDto) {
     Optional<Category> category =  categoryRepository.findByNameIgnoreCase(productDto.getCategory());

     Category category1 ;
        if(category.isPresent()){
            category1 = category.get();
        }
        else {
            category1 = new Category();
            category1.setName(productDto.getCategory());
            categoryRepository.save(category1);
        }

        Price price = new Price();
        price.setAmount(productDto.getPrice());
        price.setCurrency("INR");

        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCategory(category1);
        product.setImage(productDto.getImage());
        product.setPrice(price);

        productRepository.save(product);

        return productDto;
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
      Optional<Product> product =  productRepository.findById(UUID.fromString(id));
        return convertToDto(product.get());
    }

    @Override
    public void deleteProduct(String id) throws NotFoundException {
        Optional<Product> product =  productRepository.findById(UUID.fromString(id));
        if(product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        productRepository.delete(product.get());
    }
}
