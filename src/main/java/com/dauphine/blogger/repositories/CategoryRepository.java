package com.dauphine.blogger.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dauphine.blogger.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
