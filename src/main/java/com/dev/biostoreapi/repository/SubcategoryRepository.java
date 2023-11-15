package com.dev.biostoreapi.repository;


import com.dev.biostoreapi.model.entity.SubcategoryEntity;
import com.dev.biostoreapi.model.enums.MainCategoryNameEnum;
import com.dev.biostoreapi.model.enums.SubCategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Long> {

    List<SubcategoryEntity> findAll();

    Set<SubcategoryEntity> findAllByCategory_Name(MainCategoryNameEnum name);

    SubcategoryEntity findByName(SubCategoryNameEnum subcategory);
}
