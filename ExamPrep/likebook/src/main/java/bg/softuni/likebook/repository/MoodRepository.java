package bg.softuni.likebook.repository;

import bg.softuni.likebook.model.entity.MoodEntity;
import bg.softuni.likebook.model.enums.MoodTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoodRepository extends JpaRepository<MoodEntity, Long> {
    MoodEntity findByName(MoodTypeEnum moodName);
}
