package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="brands")
public class BrandEntity extends BaseEntity {

    @Column
    private String name;

    public BrandEntity(){}

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }


}
