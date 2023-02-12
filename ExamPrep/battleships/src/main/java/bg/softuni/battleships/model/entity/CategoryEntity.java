package bg.softuni.battleships.model.entity;

import bg.softuni.battleships.model.enums.CategoryName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseEntity {

    @Column(unique = true, nullable = false)
    //@Enumerated(EnumType.ORDINAL)
    private CategoryName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CategoryEntity(CategoryName categoryName) {
        this.name = categoryName;
    }
}
