package com.example.productserviceapis.repositories;

import com.example.productserviceapis.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findCategoryByName(String categoryName);

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) = LOWER(:categoryName)")
    Optional<Category> findByNameIgnoreCase(String categoryName);
}
