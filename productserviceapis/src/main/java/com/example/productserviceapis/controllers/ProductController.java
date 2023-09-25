package com.example.productserviceapis.controllers;

import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.exceptions.NotFoundException;
import com.example.productserviceapis.services.ProductService;
import org.springframework.web.bind.annotation.*;

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
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductDto postProduct(@RequestBody ProductDto productDto) {
        return productService.postProduct(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable("id") String id, @RequestBody ProductDto productDto) throws NotFoundException {
        return productService.updateProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") String id) throws NotFoundException {
        productService.deleteProduct(id);
    }


}
