package com.example.resellerapp.init;

import com.example.resellerapp.model.entity.ConditionEntity;
import com.example.resellerapp.model.enums.ConditionTypeEnum;
import com.example.resellerapp.repository.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConditionSeeder implements CommandLineRunner {

    private ConditionRepository conditionRepository;

    @Autowired
    public ConditionSeeder(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (conditionRepository.count() == 0) {
            List<ConditionEntity> categories = Arrays.stream(ConditionTypeEnum.values())
                    .map(name -> new ConditionEntity(name))
                    .collect(Collectors.toList());

            this.conditionRepository.saveAll(categories);

        }
    }
}

