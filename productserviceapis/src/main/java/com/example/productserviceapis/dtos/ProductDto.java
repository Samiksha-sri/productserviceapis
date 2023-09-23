package com.example.productserviceapis.dtos;

import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private String description;

    private Category category;
    private String image;

    private Price price;
}
