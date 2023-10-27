package uy.edu.um.airport.entities.AeropuertoYAerolinea;

import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;

import java.io.Serializable;
import java.util.Objects;

public class AeropuertoYAerolineaId implements Serializable {

    private Aeropuerto aeropuerto;

    private Aerolinea aerolinea;

    public AeropuertoYAerolineaId() {
    }

    public AeropuertoYAerolineaId(Aeropuerto aeropuerto, Aerolinea aerolinea) {
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AeropuertoYAerolineaId that = (AeropuertoYAerolineaId) o;
        return aeropuerto.equals(that.aeropuerto) && aerolinea.equals(that.aerolinea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aeropuerto, aerolinea);
    }
}
