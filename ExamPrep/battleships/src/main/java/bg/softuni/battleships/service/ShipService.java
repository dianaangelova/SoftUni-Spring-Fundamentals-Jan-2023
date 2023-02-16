package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.CreateShipDTO;
import bg.softuni.battleships.model.dto.ShipDTO;
import bg.softuni.battleships.model.entity.CategoryEntity;
import bg.softuni.battleships.model.entity.ShipEntity;
import bg.softuni.battleships.model.entity.UserEntity;
import bg.softuni.battleships.model.enums.CategoryName;
import bg.softuni.battleships.repository.CategoryRepository;
import bg.softuni.battleships.repository.ShipRepository;
import bg.softuni.battleships.repository.UserRepository;
import bg.softuni.battleships.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipService {
    private final ShipRepository shipRepository;
    private final CurrentUser currentUser;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository, CurrentUser currentUser, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.currentUser = currentUser;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public boolean create(CreateShipDTO createShipDTO) {

        Optional<ShipEntity> foundShipByName = this.shipRepository.findByName(createShipDTO.getName());

        if (foundShipByName.isPresent()) {
            return false;
        }

        CategoryName categoryName = switch (createShipDTO.getCategory()) {
            case 0 -> CategoryName.BATTLE;
            case 1 -> CategoryName.CARGO;
            case 2 -> CategoryName.PATROL;
            default -> CategoryName.BATTLE;
        };

        Optional<CategoryEntity> categoryMatch = this.categoryRepository.findByName(categoryName);

        ShipEntity ship = new ShipEntity();

        Optional<UserEntity> loggedUsername = this.userRepository.findById(currentUser.getId());

        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());

        ship.setCreated(createShipDTO.getCreated());

        ship.setCategory(categoryMatch.get());

        ship.setUser(loggedUsername.get());

        this.shipRepository.save(ship);
        return true;
    }

    public List<ShipDTO> getShipsOwnedBy(Long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getShipsNotOwnedBy(Long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getAllSorted() {
        return this.shipRepository.findByOrderByNameAscHealthAscPowerAsc()
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }
}
