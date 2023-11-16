package uy.edu.um.airport.entities.Puerta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import uy.edu.um.airport.entities.Terminal.Terminal; // Asegúrate de importar la clase Terminal adecuada

@Entity
@Table(name = "puertas")
@Getter
@Setter
public class Puerta {

    @Id
    private String id; // Cambiado a 'id' para representar la combinación de terminal y número de puerta

    private String numero;

    @ManyToOne
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    public Puerta(String numero, Terminal terminal) {
        this.numero = numero;
        this.terminal = terminal;
        this.id = terminal.getNombre() + numero;
    }

    public Puerta() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Puerta) {
            Puerta puerta = (Puerta) obj;
            return puerta.getId().equals(this.id);
        }
        return false;
    }

    public String toString() {
        return this.id;
    }
}

