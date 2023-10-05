package uy.edu.um.airport.entities.Terminal;

import jakarta.persistence.*;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.Puerta.Puerta;

import java.util.List;

@Entity
@Table(name = "terminales", uniqueConstraints = {
        @UniqueConstraint(columnNames = "codigo")
})
public class Terminal {
    @Id
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "aeropuerto_id", nullable = false)
    private Aeropuerto aeropuerto;

    @Column(nullable = false)
    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL)
    private List<Puerta> puertas;

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

    // Getters y Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public List<Puerta> getPuertas() {
        return puertas;
    }

    public List<Puerta> addPuertas(List<Puerta> puertas) {
        this.puertas.addAll(puertas);
        return this.puertas;
    }

    public boolean isVuelosInternacionales() {
        return vuelosInternacionales;
    }

    public void setVuelosInternacionales(boolean vuelosInternacionales) {
        this.vuelosInternacionales = vuelosInternacionales;
    }

    // Método para generar el código a partir del nombre del aeropuerto y el nombre de la terminal
    private String generarCodigo(Aeropuerto aeropuerto, String nombre) {
        return aeropuerto.getCodigo() + "-" + nombre.replaceAll("\\s+", "").toUpperCase();
    }
}

