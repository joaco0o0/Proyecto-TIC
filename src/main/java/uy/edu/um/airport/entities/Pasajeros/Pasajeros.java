package uy.edu.um.airport.entities.Pasajeros;

import jakarta.persistence.*;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

@Entity
@Table(name = "pasajeros")
@IdClass(PasajerosId.class)
public class Pasajeros {

    @Id
    @ManyToOne
    @JoinColumn(name = "vuelo", referencedColumnName = "numeroVuelo")
    private Vuelo vuelo;

    @Id
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "pasaporte")
    private Usuario usuario;

    public Pasajeros(Vuelo vuelo, Usuario usuario) {
        this.vuelo = vuelo;
        this.usuario = usuario;
    }

    public Pasajeros() {
    }

}