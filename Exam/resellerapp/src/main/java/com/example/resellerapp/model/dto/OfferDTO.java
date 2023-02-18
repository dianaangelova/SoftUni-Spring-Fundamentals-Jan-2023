package com.example.resellerapp.model.dto;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.entity.OfferEntity;
import com.example.resellerapp.model.entity.UserEntity;
import com.example.resellerapp.model.enums.ConditionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private Long id;
    private String description;
    private BigDecimal price;
    private ConditionEntity condition;

    private UserEntity createdBy;

    public OfferDTO(OfferEntity offerEntity) {
        this.id = offerEntity.getId();
        this.description = offerEntity.getDescription();
        this.price = offerEntity.getPrice();
        this.condition=offerEntity.getCondition();
    }
}
