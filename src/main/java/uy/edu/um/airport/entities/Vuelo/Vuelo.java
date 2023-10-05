package uy.edu.um.airport.entities.Vuelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Puerta.Puerta;
import uy.edu.um.airport.entities.Usuario.Usuario;

import java.util.List;

@Getter
@Entity
@Table(name = "vuelo", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigo")
})
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String codigo;

    @Column(nullable = false)
    private String aerolinea;

    @Column(nullable = false)
    private String origen;

    @Column(nullable = false)
    private String destino;

    @Column(nullable = false)
    private String fecha;

    @Setter
    @Column(nullable = false)
    private String hora;

    @Setter
    @Column(nullable = false)
    private String estado;

    @Setter
    @ManyToOne
    @JoinColumn(name = "puerta_id", nullable = true)
    private Puerta puerta;

    @Setter
    @Column(nullable = true)
    private String terminal;

    @Setter
    @Column(nullable = false)
    private String avion;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "vuelo_pasajero",
            joinColumns = @JoinColumn(name = "vuelo_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> pasajeros;

    @Setter
    @Column(nullable = false)
    private boolean habilitacionDestino;

    @Setter
    @Column(nullable = false)
    private boolean habilitacionOrigen;


    public Vuelo( String aerolinea, String origen, String destino, String fecha, String hora, String estado, Puerta puerta, String terminal, String avion) {
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.puerta = puerta;
        this.terminal = terminal;
        this.avion = avion;
        this.habilitacionDestino = false;
        this.habilitacionOrigen = false;
    }


    public Vuelo() {

    }
}
