package com.dev.biostoreapi.repository;

import com.dev.biostoreapi.model.entity.CategoryEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(MainCategoryNameEnum categoryName);
}
