package uy.edu.um.airport.entities.Vuelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vuelo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigo")
})
public class Vuelo {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codigo;

    @Getter
    @Column(nullable = false)
    private String aerolinea;

    @Getter
    @Column(nullable = false)
    private String origen;

    @Getter
    @Column(nullable = false)
    private String destino;

    @Getter
    @Column(nullable = false)
    private String fecha;

    @Getter
    @Setter
    @Column(nullable = false)
    private String hora;

    @Getter
    @Setter
    @Column(nullable = false)
    private String estado;

    @Getter
    @Setter
    @Column(nullable = true)
    private String puerta;

    @Getter
    @Setter
    @Column(nullable = true)
    private String terminal;

    @Getter
    @Setter
    @Column(nullable = false)
    private String avion;

    public Vuelo(String codigo, String aerolinea, String origen, String destino, String fecha, String hora, String estado, String puerta, String terminal, String avion) {
        this.codigo = codigo;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.puerta = puerta;
        this.terminal = terminal;
        this.avion = avion;
    }

    public Vuelo() {

    }





}
