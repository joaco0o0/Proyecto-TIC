package uy.edu.um.airport.entities.Pista;

import jakarta.persistence.*;
import lombok.Getter;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;

@Entity
@Table(name = "pistas")
public class Pista {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codigo;

    @Getter
    @Column(nullable = false)
    private String nombre;

    @Getter
    @ManyToOne
    @JoinColumn(name = "aeropuerto_id", nullable = false)
    private Aeropuerto aeropuerto;

    public Pista( String nombre, Aeropuerto aeropuerto) {
        this.nombre = nombre;
        this.aeropuerto = aeropuerto;
    }

    public Pista() {
    }
}
