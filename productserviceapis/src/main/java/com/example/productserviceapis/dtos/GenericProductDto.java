package com.example.productserviceapis.dtos;

import com.example.productserviceapis.models.Price;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name="id", columnDefinition = "binary(16)", updatable = false, nullable = false)
    private UUID id;
    private String title;
    private String description;

    private String category;
    private String image;

    private double price;
}
