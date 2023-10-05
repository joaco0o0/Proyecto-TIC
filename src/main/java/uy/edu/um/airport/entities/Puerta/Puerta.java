package uy.edu.um.airport.entities.Puerta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Terminal.Terminal;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

import java.util.List;

@Entity
@Table(name = "puertas")
public class Puerta {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false)
    private String numero;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "terminal_codigo", nullable = false)
    private Terminal terminal;

    @Setter
    @Getter
    @OneToMany(mappedBy = "puerta", cascade = CascadeType.ALL)
    private List<Vuelo> vuelos;

    public Puerta() {
    }

    public Puerta(String numero, Terminal terminal) {
        this.numero = numero;
        this.terminal = terminal;
    }
}
