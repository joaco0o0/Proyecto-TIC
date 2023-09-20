package uy.edu.um.airport.entities.Usuario;

import jakarta.persistence.*;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;

@Entity  // Indica que esta clase es una entidad JPA
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "pasaporte")
})  // Esto es opcional, pero quiero que la tabla se llame "usuarios" y tenga restricciones únicas para 'email' y 'pasaporte'

public class Usuario {

    @Id  // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // La base de datos generará este valor al azar
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false)
    private String pasaporte;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Usuario(String nombre, String apellido, LocalDate fechaNacimiento, String pasaporte, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.pasaporte = pasaporte;
        this.email = email;
        this.password = password;

    }

    public Usuario() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
