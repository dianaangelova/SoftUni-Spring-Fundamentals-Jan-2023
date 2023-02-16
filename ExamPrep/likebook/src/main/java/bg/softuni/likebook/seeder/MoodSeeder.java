package bg.softuni.likebook.seeder;

import bg.softuni.likebook.model.entity.MoodEntity;
import bg.softuni.likebook.model.enums.MoodTypeEnum;
import bg.softuni.likebook.repository.MoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoodSeeder implements CommandLineRunner {

    private MoodRepository moodRepository;

    @Autowired
    public MoodSeeder(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (moodRepository.count() == 0) {
            List<MoodEntity> categories = Arrays.stream(MoodTypeEnum.values())
                    .map(moodName -> new MoodEntity(moodName))
                    .collect(Collectors.toList());

            this.moodRepository.saveAll(categories);

        }
    }
}

