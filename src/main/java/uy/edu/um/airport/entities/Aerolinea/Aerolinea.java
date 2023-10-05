package uy.edu.um.airport.entities.Aerolinea;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public Aerolinea(String codigoIATA, String codigoICAO, String nombre, String paisDeOrigen) {
        this.codigoIATA = codigoIATA;
        this.codigoICAO = codigoICAO;
        this.nombre = nombre;
        this.paisDeOrigen = paisDeOrigen;
    }

    public Aerolinea(){}
}
