package com.example.resellerapp.repository;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.enums.ConditionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity, Long> {
    ConditionEntity findByName(ConditionTypeEnum productType);
}
