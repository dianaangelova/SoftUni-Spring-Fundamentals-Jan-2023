package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import jakarta.persistence.*;

@Entity
@Table(name="offers")
public class OfferEntity extends BaseEntity{

    private String description;

    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    @Column(name="image_url")
    private String imageUrl;

    private float mileage;

    private float price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    private int year;

    private ModelEntity model;

    private UserEntity seller;
}
