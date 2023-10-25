package uy.edu.um.airport.entities.AeropuertoYAerolinea;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aeropuertoYAerolinea", uniqueConstraints = {
})
@Data
public class AeropuertoYAerolinea {

    @Getter
    @Setter
    @Id
    private String codigo_IATA_aeropuerto;

    @Getter
    @Setter
    private String codigo_IATA_aerolinea;

    public AeropuertoYAerolinea(String codigo_IATA_aeropuerto, String codigo_IATA_aerolinea) {
        this.codigo_IATA_aeropuerto = codigo_IATA_aeropuerto;
        this.codigo_IATA_aerolinea = codigo_IATA_aerolinea;
    }

    public AeropuertoYAerolinea() {
    }
}