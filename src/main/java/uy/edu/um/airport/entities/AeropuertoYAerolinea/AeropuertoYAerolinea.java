package uy.edu.um.airport.entities.AeropuertoYAerolinea;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;

@Entity
@Table(name = "aeropuertoYAerolinea")
@IdClass(AeropuertoYAerolineaId.class)
public class AeropuertoYAerolinea {

    @Id
    @ManyToOne
    @JoinColumn(name = "codigoIATA_aeropuerto", referencedColumnName = "codigoIATA")
    private Aeropuerto aeropuerto;

    @Id
    @ManyToOne
    @JoinColumn(name = "codigoIATA_aerolinea", referencedColumnName = "codigoIATA")
    private Aerolinea aerolinea;

    public AeropuertoYAerolinea(Aeropuerto aeropuerto, Aerolinea aerolinea) {
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
    }

    public AeropuertoYAerolinea() {
    }
}