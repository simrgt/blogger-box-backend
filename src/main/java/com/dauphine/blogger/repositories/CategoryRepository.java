package com.dauphine.blogger.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dauphine.blogger.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("""
        SELECT category
        FROM Category category
        WHERE UPPER(category.name) LIKE CONCAT('%', UPPER(:name), '%')
    """)
    List<Category> findAllLikeName(@Param("name") String name);
}
