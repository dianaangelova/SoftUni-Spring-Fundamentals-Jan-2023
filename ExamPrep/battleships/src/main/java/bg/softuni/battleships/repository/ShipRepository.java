package bg.softuni.battleships.repository;

import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    Optional<ShipEntity> findByName(String name);
    List<ShipEntity> findByUserId(Long ownerId);
    List<ShipEntity> findByUserIdNot(Long ownerId);
    List<ShipEntity> findByOrderByNameAscHealthAscPowerAsc();
}
