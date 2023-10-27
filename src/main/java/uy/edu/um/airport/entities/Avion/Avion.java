package uy.edu.um.airport.entities.Avion;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

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

    @OneToOne(mappedBy = "avion", cascade = CascadeType.ALL)
    private Vuelo vuelo;

    public Avion() {}

    public Avion(String matricula, int capacidadPasajeros, int capacidadBultos, Aerolinea aerolinea) {
        this.matricula = matricula;
        this.capacidad = capacidadPasajeros;
        this.bulto = capacidadBultos;
        this.aerolinea = aerolinea;
    }


    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}
