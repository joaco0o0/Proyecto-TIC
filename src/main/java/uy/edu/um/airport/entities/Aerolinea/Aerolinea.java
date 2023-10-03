package uy.edu.um.airport.entities.Aerolinea;


import jakarta.persistence.*;

@Entity  // Indico que esta clase es una entidad JPA
@Table(name = "aerolinea", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigoICAO")})

public class Aerolinea {

    @Id
    private String codigoIATA;

    @Column(nullable = false)
    private String codigoICAO;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String paisDeOrigen;


    public Aerolinea(String codigoIATA, String codigoICAO, String nombre, String paisDeOrigen) {
        this.codigoIATA = codigoIATA;
        this.codigoICAO = codigoICAO;
        this.nombre = nombre;
        this.paisDeOrigen = paisDeOrigen;
    }

    public Aerolinea(){}

    public String getCodigoIATA() {
        return codigoIATA;
    }

    public void setCodigoIATA(String codigoIATA) {
        this.codigoIATA = codigoIATA;
    }

    public String getCodigoICAO() {
        return codigoICAO;
    }

    public void setCodigoICAO(String codigoICAO) {
        this.codigoICAO = codigoICAO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }
}
