package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.model.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole name;

    public Role() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
