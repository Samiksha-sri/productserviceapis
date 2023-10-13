package com.example.productserviceapis.dtos;

import com.example.productserviceapis.models.Category;
import com.example.productserviceapis.models.Price;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private UUID id;
    private String title;
    private String description;

    private String category;
    private String image;

    private Price price;

}
