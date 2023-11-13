package uy.edu.um.airport.entities.Vuelo;

import jakarta.persistence.*;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Avion.Avion;
import uy.edu.um.airport.entities.Puerta.Puerta;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vuelo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "numeroVuelo"),
})
public class Vuelo {

    @Id
    private Long numeroVuelo;

    @ManyToOne
    @JoinColumn(name = "codigoIATA_aerolinea", referencedColumnName = "codigoIATA")
    private Aerolinea aerolineaIATA;

    @ManyToOne
    @JoinColumn(name = "codigoICAO_aerolinea", referencedColumnName = "codigoICAO")
    private Aerolinea aerolineaICAO;

    @Column(nullable = false)
    private EstadoVuelo estadoAeropuertoOrigen;

    @Column(nullable = false)
    private EstadoVuelo estadoAeropuertoDestino;

    @Column(nullable = false)
    private EstadoVuelo estadoVuelo;

    @ManyToOne
    @JoinColumn(name = "codigoIATA_aeropuerto_origen", referencedColumnName = "codigoIATA")
    private Aeropuerto aeropuertoOrigen;

    @ManyToOne
    @JoinColumn(name = "codigoIATA_aeropuerto_destino", referencedColumnName = "codigoIATA")
    private Aeropuerto aeropuertoDestino;

    @Column(nullable = false)
    private LocalDateTime ETD;

    @Column(nullable = false)
    private LocalDateTime ETA;

    @Column
    private LocalDateTime ATD;

    @Column
    private LocalDateTime ATA;

    @OneToOne
    @JoinColumn(name = "matriculaAvion", referencedColumnName = "matricula")
    private Avion avion;

    @Column
    private String configuracion;

    @ManyToOne
    private Puerta puertaOrigen;

    @ManyToOne
    private Puerta puertaDestino;

    @Column
    private int capacidadAsientos;

    @Column
    private int capacidadBultos;

    public Vuelo() {
    }

    public Vuelo(Long numeroVuelo, Aerolinea aerolineaIATA, Aerolinea aerolineaICAO, EstadoVuelo estadoAeropuertoOrigen, EstadoVuelo estadoAeropuertoDestino,
                 EstadoVuelo estadoVuelo, Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, LocalDateTime ETD, LocalDateTime ETA, LocalDateTime ATD,
                 LocalDateTime ATA, Avion avion, String configuracion,Puerta puertaOrigen, Puerta puertaDestino, int capacidadAsientos, int capacidadBultos) {
        this.numeroVuelo = numeroVuelo;
        this.aerolineaIATA = aerolineaIATA;
        this.aerolineaICAO = aerolineaICAO;
        this.estadoAeropuertoOrigen = estadoAeropuertoOrigen;
        this.estadoAeropuertoDestino = estadoAeropuertoDestino;
        this.estadoVuelo = estadoVuelo;
        this.aeropuertoOrigen = aeropuertoOrigen;
        this.aeropuertoDestino = aeropuertoDestino;
        this.ETD = ETD;
        this.ETA = ETA;
        this.ATD = ATD;
        this.ATA = ATA;
        this.avion = avion;
        this.configuracion = configuracion;
        this.puertaOrigen = puertaOrigen;
        this.puertaDestino = puertaDestino;
        this.capacidadAsientos = capacidadAsientos;
        this.capacidadBultos = capacidadBultos;
    }

    public void setPuertaOrigen(Puerta puerta) {
        this.puertaOrigen = puerta;
    }

    public void setPuertaDestino(Puerta puerta) {
        this.puertaDestino = puerta;
    }

    public Puerta getPuertaAsignadaDespegue() {
        return puertaOrigen;
    }

    public LocalDateTime getHorarioDespegue() {
        return ETD;
    }

    public Puerta getPuertaAsignadaAterrizaje() {
        return puertaDestino;
    }

    public LocalDateTime getHorarioAterrizaje() {
        return ETA;
    }

    public enum EstadoVuelo {
        PENDIENTE, ACEPTADO, RECHAZADO
    }

    public Long getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(Long numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public Aerolinea getAerolineaIATA() {
        return aerolineaIATA;
    }

    public String getAerolineaIATAString() {
        return aerolineaIATA.getCodigoIATA();
    }

    public void setAerolineaIATA(Aerolinea aerolineaIATA) {
        this.aerolineaIATA = aerolineaIATA;
    }

    public Aerolinea getAerolineaICAO() {
        return aerolineaICAO;
    }

    public void setAerolineaICAO(Aerolinea aerolineaICAO) {
        this.aerolineaICAO = aerolineaICAO;
    }

    public EstadoVuelo getEstadoVuelo() {
        return estadoVuelo;
    }

    public void setEstadoVuelo(EstadoVuelo estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }

    public void setEstadoAeropuertoOrigen(EstadoVuelo estadoAeropuertoOrigen) {
        this.estadoAeropuertoOrigen = estadoAeropuertoOrigen;
    }
    public void setEstadoAeropuertoDestino(EstadoVuelo estadoAeropuertoDestino) {
        this.estadoAeropuertoDestino = estadoAeropuertoDestino;
    }

    public EstadoVuelo getEstadoAeropuertoOrigen() {
        return estadoAeropuertoOrigen;
    }
    public EstadoVuelo getEstadoAeropuertoDestino() {
        return estadoAeropuertoDestino;
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public String getNombreAeropuertoOrigen() {
        return aeropuertoOrigen.getNombre();
    }

    public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
        this.aeropuertoOrigen = aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }

    public String getNombreAeropuertoDestino() {
        return aeropuertoDestino.getNombre();
    }

    public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
        this.aeropuertoDestino = aeropuertoDestino;
    }

    public LocalDateTime getETD() {
        return ETD;
    }

    public void setETD(LocalDateTime ETD) {
        this.ETD = ETD;
    }

    public LocalDateTime getETA() {
        return ETA;
    }

    public void setETA(LocalDateTime ETA) {
        this.ETA = ETA;
    }

    public LocalDateTime getATD() {
        return ATD;
    }

    public void setATD(LocalDateTime ATD) {
        this.ATD = ATD;
    }

    public LocalDateTime getATA() {
        return ATA;
    }

    public void setATA(LocalDateTime ATA) {
        this.ATA = ATA;
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

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
}