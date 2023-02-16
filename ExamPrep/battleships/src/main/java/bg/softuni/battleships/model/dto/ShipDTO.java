package bg.softuni.battleships.model.dto;

import bg.softuni.battleships.model.entity.ShipEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ShipDTO {
    private Long id;
    private String name;
    private long health;
    private long power;

    public ShipDTO(ShipEntity ship) {
        this.id=ship.getId();
        this.name= ship.getName();
        this.health=ship.getHealth();
        this.power=ship.getPower();
    }
}
