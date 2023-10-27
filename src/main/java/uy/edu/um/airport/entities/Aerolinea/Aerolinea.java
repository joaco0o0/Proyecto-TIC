package uy.edu.um.airport.entities.Aerolinea;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolinea;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.util.List;

@Entity
@Table(name = "aerolinea", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigoICAO")
})
@Data
public class Aerolinea {

    @Getter
    @Setter
    @Id
    private String codigoIATA;

    @Getter
    @Setter
    @Column(nullable = false)
    private String codigoICAO;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nombre;

    @Getter
    @Setter
    @Column(nullable = false)
    private String paisDeOrigen;

    // Añadido para relación
    @OneToMany(mappedBy = "aerolinea")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "aerolinea")
    private List<AeropuertoYAerolinea> aeropuertos;

    // Relación inversa desde Aerolinea hacia Vuelo
    @OneToMany(mappedBy = "aerolineaIATA")
    private List<Vuelo> vuelosPorIATA;

    @OneToMany(mappedBy = "aerolineaICAO")
    private List<Vuelo> vuelosPorICAO;

    public Aerolinea(String codigoIATA, String codigoICAO, String nombre, String paisDeOrigen) {
        this.codigoIATA = codigoIATA;
        this.codigoICAO = codigoICAO;
        this.nombre = nombre;
        this.paisDeOrigen = paisDeOrigen;
    }

    public Aerolinea(){}
}

