package uy.edu.um.airport.entities.Terminal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Puerta.Puerta;

import java.util.List;

@Entity
@Table(name = "terminales", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigo")
})
public class Terminal {

    @Getter
    @Id
    private String codigo;

    @Getter
    @Column(nullable = false)
    private String nombre;

    @Getter
    @ManyToOne
    @JoinColumn(name = "aeropuerto_id", nullable = false)
    private Aeropuerto aeropuerto;

    @Getter
    @Setter
    @Column(nullable = false)
    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL)
    private List<Puerta> puertas;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean vuelosInternacionales;

    public Terminal() {
    }

    public Terminal(String nombre, Aeropuerto aeropuerto, List<Puerta> Puertas, boolean vuelosInternacionales) {
        this.codigo = generarCodigo(aeropuerto, nombre);
        this.nombre = nombre;
        this.aeropuerto = aeropuerto;
        this.puertas = Puertas;
        this.vuelosInternacionales = vuelosInternacionales;
    }



    // Método para generar el código a partir del nombre del aeropuerto y el nombre de la terminal
    private String generarCodigo(Aeropuerto aeropuerto, String nombre) {
        return aeropuerto.getCodigo() + "-" + nombre.replaceAll("\\s+", "").toUpperCase();
    }
}

