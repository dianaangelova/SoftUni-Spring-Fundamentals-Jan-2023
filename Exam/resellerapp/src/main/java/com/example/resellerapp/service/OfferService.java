package com.example.resellerapp.service;

import com.example.resellerapp.model.dto.CreateOfferDTO;
import com.example.resellerapp.model.dto.OfferDTO;
import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.entity.OfferEntity;
import com.example.resellerapp.model.entity.UserEntity;
import com.example.resellerapp.model.enums.ConditionTypeEnum;
import com.example.resellerapp.repository.ConditionRepository;
import com.example.resellerapp.repository.OfferRepository;
import com.example.resellerapp.repository.UserRepository;
import com.example.resellerapp.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ConditionRepository conditionRepository;

    public OfferService(OfferRepository songRepository, CurrentUser currentUser, UserRepository userRepository, ConditionRepository conditionRepository) {
        this.offerRepository = songRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
    }


    public boolean create(CreateOfferDTO createOfferDTO) {

        ConditionTypeEnum conditionType = ConditionTypeEnum.valueOf(createOfferDTO.getCondition());
        ConditionEntity condition = this.conditionRepository.findByName(conditionType);

        UserEntity user = this.userRepository.findById(currentUser.getId()).orElse(null);

        OfferEntity offer = new OfferEntity();

        offer.setDescription(createOfferDTO.getDescription());
        offer.setPrice(createOfferDTO.getPrice());
        offer.setCondition(condition);
        offer.setCreatedBy(user);

        this.offerRepository.save(offer);
        return true;
    }

    public List<OfferDTO> getAllOffersOwner(long userId) {

        UserEntity user = this.userRepository.findById(userId).orElse(null);

        return this.offerRepository.findAllByBoughtBy(user)
                .stream()
                .map(OfferDTO::new)
                .collect(Collectors.toList());

    }

    public List<OfferDTO> getAllOffersNotOwner(long userId) {

        UserEntity user = this.userRepository.findById(userId).orElse(null);

        return this.offerRepository.findAllByCreatedByNotAndBoughtByIsNull(user)
                .stream()
                .map(OfferDTO::new)
                .collect(Collectors.toList());

    }

    public List<OfferDTO> getAllOffersCreated(long userId) {

        UserEntity user = this.userRepository.findById(userId).orElse(null);

        return this.offerRepository.findAllByCreatedBy(user)
                .stream()
                .map(OfferDTO::new)
                .collect(Collectors.toList());
    }

    public void removeOffer(Long id) {

        OfferEntity offerToRemove = this.offerRepository.findById(id).orElse(null);

        this.offerRepository.delete(offerToRemove);

        return;
    }

    public void buyOffer(Long id) {
        OfferEntity offerToBuy = this.offerRepository.findById(id).orElse(null);
        UserEntity buyer = this.userRepository.findById(currentUser.getId()).orElse(null);

        offerToBuy.setBoughtBy(buyer);
        this.offerRepository.save(offerToBuy);

        //Тук забравям да го махна от списъка с общите
    }

}
