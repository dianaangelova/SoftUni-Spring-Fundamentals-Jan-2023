package bg.softuni.battleships.service;

import bg.softuni.battleships.model.dto.StartBattleDTO;
import bg.softuni.battleships.model.entity.ShipEntity;
import bg.softuni.battleships.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {

    private final ShipRepository shipRepository;

    @Autowired
    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDTO attackData) {

        Optional<ShipEntity> attackerOpt = this.shipRepository.findById(attackData.getAttackerID());
        Optional<ShipEntity> defenderOpt = this.shipRepository.findById(attackData.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        ShipEntity attackerShip = attackerOpt.get();
        ShipEntity defenderShip = defenderOpt.get();

        long newDefenderHealth = defenderShip.getHealth() - attackerShip.getPower();

        if (newDefenderHealth <= 0) {
            this.shipRepository.delete(defenderShip);
        } else {
            defenderShip.setHealth(newDefenderHealth);
            this.shipRepository.save(defenderShip);
        }
    }
}
