package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
