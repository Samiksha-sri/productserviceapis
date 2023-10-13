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

    private GenericProductDto convertToDto(Product product) {
        GenericProductDto productDto = new GenericProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice().getAmount());
        productDto.setId(product.getId());
        return productDto;
    }
    @Override
    public List<GenericProductDto> getAllProducts() {

      List<Product> productList = productRepository.findAll();
      List<GenericProductDto> productDtoList = new ArrayList<>();

      for(Product product : productList) {
          GenericProductDto productDto = convertToDto(product);
          productDtoList.add(productDto);
      }

        return productDtoList;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
    Product product = productRepository.findById(UUID.fromString(String.valueOf(id)))
            .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));


    return convertToDto(product);
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
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setCategory(category1);
        product.setImage(productDto.getImage());
        product.setPrice(price);

        productRepository.save(product);

        return productDto;
    }

    @Override
    public GenericProductDto updateProduct(UUID id, GenericProductDto productDto) throws NotFoundException {
        Optional<Product> product = productRepository.findById(UUID.fromString(String.valueOf(id)));
        if(product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        Product product1 = product.get();
        if(!id.equals(product1.getId())){
            throw new NotFoundException("Product id doesn't match");
        }
       Optional<Category> category = categoryRepository.findByNameIgnoreCase(productDto.getCategory());
        Category category1;
        if(category.isPresent()) {
            category1 = category.get();
        }
        else{
            category1 = new Category();
            category1.setName(productDto.getCategory());
            categoryRepository.save(category1);
        }

        Price price = new Price();
        price.setAmount(productDto.getPrice());
        price.setCurrency("INR");

        product1.setId(productDto.getId());
        product1.setCategory(category1);
        product1.setTitle(productDto.getTitle());
        product1.setPrice(price);
        product1.setImage(productDto.getImage());
        product1.setDescription(productDto.getDescription());

        Product savedproduct = productRepository.save(product1);
        return convertToDto(savedproduct);
    }

    @Override
    public GenericProductDto deleteProduct(UUID id) throws NotFoundException {
       Optional<Product> optionalProduct =  productRepository.findById(UUID.fromString(String.valueOf(id)));
       if(optionalProduct.isEmpty()){
           throw new NotFoundException("Product not found");
       }

       Product product = optionalProduct.get();
       GenericProductDto productDto = new GenericProductDto();
      productDto.setImage(product.getImage());
      productDto.setPrice(product.getPrice().getAmount());
      productDto.setDescription(product.getDescription());
      productDto.setCategory(product.getCategory().getName());
      productDto.setTitle(product.getTitle());

      productRepository.delete(product);
      return productDto;


    }
}
