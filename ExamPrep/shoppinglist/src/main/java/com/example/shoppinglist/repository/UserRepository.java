package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findAllByEmail(String email);
    Optional<UserEntity> findAllByUsername(String username);

}
