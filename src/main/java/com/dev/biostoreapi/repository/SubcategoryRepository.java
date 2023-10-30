package com.dev.biostoreapi.repository;

import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Long> {
}
