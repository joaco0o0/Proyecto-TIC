package uy.edu.um.airport.entities.Pasajeros;

import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.io.Serializable;
import java.util.Objects;

public class PasajerosId implements Serializable {

    private Usuario usuario;

    private Vuelo vuelo;

    public PasajerosId() {
    }

    public PasajerosId(Vuelo vuelo, Usuario usuario) {
        this.vuelo = vuelo;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasajerosId)) return false;
        PasajerosId that = (PasajerosId) o;
        return Objects.equals(getUsuario(), that.getUsuario()) && Objects.equals(getVuelo(), that.getVuelo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, vuelo);
    }
}