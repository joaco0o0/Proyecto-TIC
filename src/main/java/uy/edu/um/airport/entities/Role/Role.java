package uy.edu.um.airport.entities.Role;


import jakarta.persistence.*;

@Entity //Indico que esta clase es una entidad JPA
@Table(name = "roles",  uniqueConstraints = { @UniqueConstraint(columnNames = "name")})

public class Role {

    @Id //Indica que la variable id es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La base de datos generará este valor al azar
    private Long id;  //Cada rol va a tener un determinado id. Cada vez que se cree un rol, se le asignará un id

    @Column(nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
