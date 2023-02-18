package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.OfferEntity;
import com.example.resellerapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    List<OfferEntity> findAllByBoughtBy(UserEntity boughtBy);
    List<OfferEntity> findAllByCreatedByNotAndBoughtByIsNull(UserEntity NotBoughtBy);
    List<OfferEntity> findAllByCreatedBy (UserEntity createdBy);
}
