package com.example.productserviceapis.repositories;

import com.example.productserviceapis.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findCategoryByName(String categoryName);
}
