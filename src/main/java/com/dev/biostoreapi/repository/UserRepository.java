package com.dev.biostoreapi.repository;

import com.dev.biostoreapi.model.entity.UserEntity;
import com.dev.biostoreapi.service.impl.BioFactoryUserDetailsService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);
}
