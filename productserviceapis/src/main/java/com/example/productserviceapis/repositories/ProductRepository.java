package com.example.productserviceapis.repositories;

import com.example.productserviceapis.dtos.ProductDto;
import com.example.productserviceapis.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
