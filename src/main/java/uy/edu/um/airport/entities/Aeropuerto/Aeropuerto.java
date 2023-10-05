package uy.edu.um.airport.entities.Aeropuerto;

import jakarta.persistence.*;
import uy.edu.um.airport.entities.Terminal.Terminal;

import java.util.List;

@Entity
@Table(name = "aeropuertos")
public class Aeropuerto {
    @Id
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private boolean aceptaVuelosInternacionales;

    @OneToMany(mappedBy = "aeropuerto", cascade = CascadeType.ALL)
    private List<Terminal> terminales;

    public Aeropuerto() {
    }

    public Aeropuerto(String codigo, String nombre, String pais, boolean aceptaVuelosInternacionales) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.aceptaVuelosInternacionales = aceptaVuelosInternacionales;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean isAceptaVuelosInternacionales() {
        return aceptaVuelosInternacionales;
    }

    public void setAceptaVuelosInternacionales(boolean aceptaVuelosInternacionales) {
        this.aceptaVuelosInternacionales = aceptaVuelosInternacionales;
    }

    public List<Terminal> getTerminales() {
        return terminales;
    }

    public void setTerminales(List<Terminal> terminales) {
        this.terminales = terminales;
    }
}

