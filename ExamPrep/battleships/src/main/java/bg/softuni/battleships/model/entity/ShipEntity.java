package bg.softuni.battleships.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="ships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipEntity extends  BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private long health;

    @Column(nullable = false)
    private long power;

    @Column(nullable = false)
    private LocalDate created;

    @ManyToOne // many ships are owned by one user
    private UserEntity user;

    @ManyToOne
    private CategoryEntity category;
}
