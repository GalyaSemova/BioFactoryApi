package com.dev.biostoreapi.repository;

import com.dev.biostoreapi.model.dto.ProductDTO;
import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findAll();

    Set<ProductEntity> findAllBySubcategory_Name(SubCategoryNameEnum name);

    List<ProductEntity> findAllByUser_Id(Long userId);
}
