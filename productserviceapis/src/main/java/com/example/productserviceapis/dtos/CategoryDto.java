package com.example.productserviceapis.dtos;

import com.example.productserviceapis.models.Product;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long id;
    private String name;
    private List<ProductDto> productList;


}
