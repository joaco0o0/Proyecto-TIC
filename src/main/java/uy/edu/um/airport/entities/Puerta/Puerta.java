package uy.edu.um.airport.entities.Puerta;

import jakarta.persistence.*;
import uy.edu.um.airport.entities.Terminal.Terminal;
import uy.edu.um.airport.entities.Vuelo.Vuelo;

@Entity
@Table(name = "puertas")
public class Puerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @ManyToOne
    @JoinColumn(name = "terminal_codigo", nullable = false)
    private Terminal terminal;

    @OneToOne(mappedBy = "puerta", cascade = CascadeType.ALL)
    private Vuelo vueloEnCurso;

    public Puerta() {
    }

    public Puerta(String numero, Terminal terminal) {
        this.numero = numero;
        this.terminal = terminal;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Vuelo getVueloEnCurso() {
        return vueloEnCurso;
    }

    public void setVueloEnCurso(Vuelo vueloEnCurso) {
        this.vueloEnCurso = vueloEnCurso;
    }
}
