package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.CategoryEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="models")
public class ModelEntity extends BaseEntity{

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="start_year")
    private int startYear;

    @Column(name="end_year")
    private int endYear;

    @ManyToOne
    private BrandEntity brand;


}
