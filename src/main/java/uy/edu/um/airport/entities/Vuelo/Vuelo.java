package uy.edu.um.airport.entities.Vuelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vuelo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "numeroVuelo"),
})
public class Vuelo {

    //Datos basicos de un vuelo
    @Id
    private Long numeroVuelo;
    @Column(nullable = false)
    private String codigoIATA;
    @Column(nullable = false)
    private String codigoICAO;


    //Detalles de la Ruta (por ahora vuelos de un solo tramo)
    @Column(nullable = false)
    private String aeropuertoOrigen;
    @Column(nullable = false)
    private String aeropuertoDestino;


    //Horarios de vuelo
    @Column(nullable = false)
    private LocalDate ETD;
    @Column(nullable = false)
    private LocalDate ETA;
    @Column(nullable = false)
    private LocalDate ATD;
    @Column(nullable = false)
    private LocalDate ATA;
    
    
    //Configuracion de la Aeronave
    @Column(nullable = false)
    private String matriculaAvion;
    @Column(nullable = false)
    private String configuracion;
    @Column(nullable = false)
    private int capacidadAsientos;
    @Column(nullable = false)
    private int capacidadBultos;


    public Vuelo(Long numeroVuelo, String codigoIATA, String codigoICAO, String aeropuertoOrigen, String aeropuertoDestino,
                 LocalDate ETD, LocalDate ETA, LocalDate ATD, LocalDate ATA, String matriculaAvion, String configuracion,
                 int capacidadAsientos, int capacidadBultos) {
        this.numeroVuelo = numeroVuelo;
        this.codigoIATA = codigoIATA;
        this.codigoICAO = codigoICAO;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.ETD = ETD;
        this.ETA = ETA;
        this.ATD = ATD;
        this.ATA = ATA;
        this.matriculaAvion = matriculaAvion;
        this.configuracion = configuracion;
        this.capacidadAsientos = capacidadAsientos;
        this.capacidadBultos = capacidadBultos;
    }

    public Vuelo(){}

    public Long getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(Long numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

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

    public String getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public void setAeropuertoOrigen(String aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public String getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public void setAeropuertoDestino(String aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public LocalDate getETD() {
        return ETD;
    }

    public void setETD(LocalDate ETD) {
        this.ETD = ETD;
    }

    public LocalDate getETA() {
        return ETA;
    }

    public void setETA(LocalDate ETA) {
        this.ETA = ETA;
    }

    public LocalDate getATD() {
        return ATD;
    }

    public void setATD(LocalDate ATD) {
        this.ATD = ATD;
    }

    public LocalDate getATA() {
        return ATA;
    }

    public void setATA(LocalDate ATA) {
        this.ATA = ATA;
    }

    public String getMatriculaAvion() {
        return matriculaAvion;
    }

    public void setMatriculaAvion(String matriculaAvion) {
        this.matriculaAvion = matriculaAvion;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public int getCapacidadAsientos() {
        return capacidadAsientos;
    }

    public void setCapacidadAsientos(int capacidadAsientos) {
        this.capacidadAsientos = capacidadAsientos;
    }

    public int getCapacidadBultos() {
        return capacidadBultos;
    }

    public void setCapacidadBultos(int capacidadBultos) {
        this.capacidadBultos = capacidadBultos;
    }
}