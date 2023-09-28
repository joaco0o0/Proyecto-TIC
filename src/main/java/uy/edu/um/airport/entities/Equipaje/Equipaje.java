package uy.edu.um.airport.entities.Equipaje;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

@Entity  // Indico que esta clase es una entidad JPA
@Table(name = "equipaje", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
public class Equipaje {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String descripcion;

    @Getter
    @Setter
    @Column(nullable = false)
    private String estado;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id_vuelo", nullable = false)
    private Vuelo vuelo;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Equipaje(String descripcion, String estado, Vuelo vuelo, Usuario usuario) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.vuelo = vuelo;
        this.usuario = usuario;
    }

    public Equipaje() {

    }


}
