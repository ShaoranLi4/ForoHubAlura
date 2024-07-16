package com.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.forohub.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
