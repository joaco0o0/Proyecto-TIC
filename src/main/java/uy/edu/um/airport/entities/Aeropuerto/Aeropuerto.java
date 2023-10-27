package uy.edu.um.airport.entities.Aeropuerto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolinea;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.util.List;

@Entity
@Table(name = "aeropuertos")
public class Aeropuerto {
    @Getter
    @Setter
    @Id
    private String codigoIATA;

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


    // Añadido para relación
    @OneToMany(mappedBy = "aeropuerto")
    private List<Usuario> usuarios;


    @OneToMany(mappedBy = "aeropuerto")
    private List<AeropuertoYAerolinea> aerolineas;

    @OneToMany(mappedBy = "aeropuertoOrigen")
    private List<Vuelo> vuelosDeOrigen;

    @OneToMany(mappedBy = "aeropuertoDestino")
    private List<Vuelo> vuelosDeDestino;






    public Aeropuerto() {
    }

    public Aeropuerto(String codigoIATA, String nombre,String ciudad, String pais) {
        this.codigoIATA = codigoIATA;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
    }
}
