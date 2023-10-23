package uy.edu.um.airport.entities.Aeropuerto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Entity
@Table(name = "aeropuertos")
public class Aeropuerto {
    @Getter
    @Id
    private String codigo;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nombre;

    @Getter
    @Column(nullable = false)
    private String pais;

    @Getter
    @Column(nullable = false)
    private String ciudad;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean aceptaVuelosInternacionales;

    public Aeropuerto() {
    }

    public Aeropuerto(String codigo, String nombre, String pais, String ciudad, boolean aceptaVuelosInternacionales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.aceptaVuelosInternacionales = aceptaVuelosInternacionales;
    }
}

