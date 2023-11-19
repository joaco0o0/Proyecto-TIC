package uy.edu.um.airport.entities.Terminal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Puerta.Puerta;

import java.util.List;

@Entity
@Table(name = "terminales")
@Getter
@Setter
public class Terminal {

    @Id
    private String id;

    @Column (nullable = false)
    private String nombre;

    @ManyToOne
    private Aeropuerto aeropuerto;

    @OneToMany(mappedBy = "terminal")
    private List<Puerta> puertas;

    public Terminal(String nombre, List<Puerta> puertas, Aeropuerto aeropuerto) {
        this.nombre = nombre;
        this.puertas = puertas;
        this.aeropuerto = aeropuerto;
        this.id = aeropuerto.getCodigoIATA() + nombre;
    }

    public Terminal() {
    }
}
