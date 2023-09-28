package uy.edu.um.airport.ui.Equipaje;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.TextField;


import uy.edu.um.airport.entities.Equipaje.Equipaje;
import uy.edu.um.airport.entities.Equipaje.EquipajeMgr;
import uy.edu.um.airport.entities.Vuelo.Vuelo;
import uy.edu.um.airport.entities.Usuario.Usuario;

@Component
public class EquipajeController {
    @Autowired
    private EquipajeMgr equipajeMgr;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtVuelo;
    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtEquipajeId;

    public void HandleRegister(ActionEvent actionEvent) {
        String descripcion = txtDescripcion.getText();
        String vueloCodigo = txtVuelo.getText();
        String usuarioId = txtUsuario.getText();

        if (descripcion.isEmpty() || vueloCodigo.isEmpty() || usuarioId.isEmpty()) {
            System.err.println("Todos los campos son obligatorios.");
            return;
        }

        // Llama a un método para obtener el objeto Vuelo por su código
        Vuelo vuelo = equipajeMgr.findVueloByCodigo(vueloCodigo);

        if (vuelo == null) {
            System.err.println("No se ha encontrado el vuelo con el código proporcionado.");
            return;
        }

        // Llama a un método para obtener el objeto Usuario por su ID
        Usuario usuario = equipajeMgr.findUsuarioById(usuarioId);

        if (usuario == null) {
            System.err.println("No se ha encontrado el usuario con el ID proporcionado.");
            return;
        }

        Equipaje equipaje = new Equipaje(descripcion, "En espera", vuelo, usuario);
        equipajeMgr.addEquipaje(equipaje);
        System.out.println("Se ha registrado el equipaje.");
    }


    public void HandleDelete(ActionEvent actionEvent) {
        String equipajeIdText = txtEquipajeId.getText();

        if (equipajeIdText.isEmpty()) {
            System.err.println("El campo del ID del equipaje es obligatorio.");
            return;
        }

        try {
            Long equipajeId = Long.parseLong(equipajeIdText); // Convertir el ID del equipaje a Long (o el tipo que corresponda)

            // Llamar a un método para obtener el equipaje por su ID
            Equipaje equipaje = equipajeMgr.findEquipajeById(equipajeId);

            if (equipaje == null) {
                System.err.println("No se ha encontrado el equipaje con el ID proporcionado.");
                return;
            }

            // Llamar al método para eliminar el equipaje
            equipajeMgr.deleteEquipaje(equipaje);

            System.out.println("Se ha eliminado el equipaje.");
        } catch (NumberFormatException e) {
            System.err.println("El ID del equipaje debe ser un número válido.");
        }
    }


    public Equipaje HandleSearch(ActionEvent actionEvent) {
        String descripcion = txtDescripcion.getText();
        String vueloCode = txtVuelo.getText();
        String usuarioCode = txtUsuario.getText();

        if (descripcion.isEmpty() || usuarioCode.isEmpty() || vueloCode.isEmpty()) { // Falta el paréntesis de cierre
            System.err.println("Todos los campos son obligatorios.");
            return null;
        }

        Equipaje equipaje = equipajeMgr.findEquipaje(descripcion, vueloCode, usuarioCode); // Falta el paréntesis de cierre

        if (equipaje == null) {
            System.err.println("No se ha encontrado el equipaje.");
        } else {
            System.out.println("Se ha encontrado el equipaje.");
        }

        return equipaje; // Asegúrate de que el return esté fuera del bloque else.
    }

}
