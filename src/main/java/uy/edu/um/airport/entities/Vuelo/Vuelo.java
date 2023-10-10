package uy.edu.um.airport.entities.Vuelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vuelo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "pasaporte")
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





}