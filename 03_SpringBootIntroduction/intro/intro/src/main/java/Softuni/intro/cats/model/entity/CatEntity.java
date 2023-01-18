package Softuni.intro.cats.model.entity;

import jakarta.persistence.*;
@Entity(name="cats")
public class CatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String catName;

    @ManyToOne()
    private OwnerEntity owner;

    public CatEntity() {
    }

    public long getId() {
        return id;
    }

    public CatEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getCatName() {
        return catName;
    }

    public CatEntity setCatName(String catName) {
        this.catName = catName;
        return this;
    }

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }
}
