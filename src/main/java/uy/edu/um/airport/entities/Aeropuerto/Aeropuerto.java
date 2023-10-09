package uy.edu.um.airport.entities.Aeropuerto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Terminal.Terminal;

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
    @Setter
    @Column(nullable = false)
    private boolean aceptaVuelosInternacionales;

    @Getter
    @Setter
    @OneToMany(mappedBy = "aeropuerto", cascade = CascadeType.ALL)
    private List<Terminal> terminales;

    //@Getter
    //@Setter
    //@OneToMany(mappedBy = "aeropuerto", cascade = CascadeType.ALL)
    //private List<Pista> pistas;


    public Aeropuerto() {
    }

    public Aeropuerto(String codigo, String nombre, String pais, boolean aceptaVuelosInternacionales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.aceptaVuelosInternacionales = aceptaVuelosInternacionales;
    }
}

