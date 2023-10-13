package com.example.productserviceapis.controllers;

import com.example.productserviceapis.dtos.GenericProductDto;
import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HexFormat;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<GenericProductDto> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") UUID id) throws NotFoundException {

        return productService.getProductById(id);
    }

    @PostMapping
    public GenericProductDto postProduct(@RequestBody GenericProductDto productDto) {
        return productService.postProduct(productDto);
    }

    @PutMapping("/{id}")
    public GenericProductDto updateProduct(@PathVariable("id") UUID id, @RequestBody GenericProductDto productDto) throws NotFoundException {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("id") UUID id) throws NotFoundException {
       return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
    }


}
