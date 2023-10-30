package uy.edu.um.airport.entities.Terminal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private String nombre;

    @ManyToOne // Muchas terminales pueden pertenecer a un aeropuerto
    private Aeropuerto aeropuerto;

    @OneToMany(mappedBy = "terminal")
    private List<Puerta> puertas;

    public Terminal(String nombre, List<Puerta> puertas, Aeropuerto aeropuerto) {
        this.nombre = nombre;
        this.puertas = puertas;
        this.aeropuerto = aeropuerto;
    }

    public Terminal() {
    }
}
