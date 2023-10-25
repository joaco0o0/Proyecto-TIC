package uy.edu.um.airport.entities.Avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;

@Entity
@Table(name = "aviones")
public class Avion {

    @Getter
    @Setter
    @Id
    private String matricula;

    @Getter
    @Setter
    @Column(nullable = false)
    private int capacidad;

    @Getter
    @Setter
    @Column(nullable = false)
    private int bulto;


    @ManyToOne
    @JoinColumn(name = "codigoIATA_aerolinea", referencedColumnName = "codigoIATA")
    private Aerolinea aerolinea;

    public Avion() {
    }

    public Avion(String matricula, int capacidad, int bulto, Aerolinea aerolinea) {
        this.matricula = matricula;
        this.capacidad = capacidad;
        this.bulto = bulto;
        this.aerolinea = aerolinea;
    }
}
