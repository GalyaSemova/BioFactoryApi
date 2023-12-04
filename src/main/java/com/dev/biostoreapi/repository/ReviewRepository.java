package com.dev.biostoreapi.repository;

import com.dev.biostoreapi.model.entity.ProductEntity;
import com.dev.biostoreapi.model.entity.Review;
import com.dev.biostoreapi.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByWriterAndProduct(UserEntity writer, ProductEntity product);

    Optional<Review> findAllByProduct_Id(Long productId);
}
