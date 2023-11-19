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

    @OneToOne(mappedBy = "avion", cascade = CascadeType.ALL)
    private uy.edu.um.airport.entities.Vuelo.Vuelo vuelo;

    public Avion() {}

    public Avion(String matricula, int capacidadPasajeros, int capacidadBultos, Aerolinea aerolinea) {
        this.matricula = matricula;
        this.capacidad = capacidadPasajeros;
        this.bulto = capacidadBultos;
        this.aerolinea = aerolinea;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getBulto() {
        return bulto;
    }

    public void setBulto(int bulto) {
        this.bulto = bulto;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public uy.edu.um.airport.entities.Vuelo.Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(uy.edu.um.airport.entities.Vuelo.Vuelo vuelo) {
        this.vuelo = vuelo;
    }
}
