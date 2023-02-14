package bg.softuni.battleships.repository;

import bg.softuni.battleships.model.entity.CategoryEntity;
import bg.softuni.battleships.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryName categoryName);
}
