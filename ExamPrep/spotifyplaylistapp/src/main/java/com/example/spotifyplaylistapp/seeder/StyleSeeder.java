package com.example.spotifyplaylistapp.seeder;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StyleSeeder implements CommandLineRunner {

    private StyleRepository styleRepository;

    @Autowired
    public StyleSeeder(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (styleRepository.count() == 0) {
            List<StyleEntity> categories = Arrays.stream(StyleTypeEnum.values())
                    .map(styleName -> new StyleEntity(styleName))
                    .collect(Collectors.toList());

            this.styleRepository.saveAll(categories);

        }
    }
}

