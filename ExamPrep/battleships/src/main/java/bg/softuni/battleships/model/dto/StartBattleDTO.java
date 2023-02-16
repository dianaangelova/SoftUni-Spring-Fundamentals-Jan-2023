package bg.softuni.battleships.model.dto;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartBattleDTO {
    @Positive
    private long attackerID;
    @Positive
    private long defenderId;
}
