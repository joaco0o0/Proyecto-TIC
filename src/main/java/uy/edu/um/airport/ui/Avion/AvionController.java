package uy.edu.um.airport.ui.Avion;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Avion.Avion;
import uy.edu.um.airport.entities.Avion.AvionMgr;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.persistence.AerolineaRepository;
import uy.edu.um.airport.session.Session;

@Component
public class AvionController {

    @Autowired
    private AvionMgr avionMgr;

    @Autowired
    private AerolineaMgr aerolineaMgr;

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @FXML
    private javafx.scene.control.TextField txtMatricula;

    @FXML
    private javafx.scene.control.TextField txtCapacidadAsientos;

    @FXML
    private javafx.scene.control.TextField txtCapacidadBultos;

    @FXML
    private javafx.scene.control.TextField txtIATA;

    @FXML
    public void handleAvionRegister(){
        // Obtener la aerolínea asociada al usuario logueado
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Aerolinea aerolineaUsuario = usuarioLogueado.getAerolinea();
        String matricula = txtMatricula.getText();
        String capacidadAsientos = txtCapacidadAsientos.getText();
        String capacidadBultos = txtCapacidadBultos.getText();

        if (matricula.isEmpty() || capacidadAsientos.isEmpty() || capacidadBultos.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "Todos los campos son obligatorios.");
            return;
        }

        if(avionMgr.existsByMatricula(matricula)){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "Ya existe un avión registrado en el sistema con esta matrícula.");
            return;
        }

        int CapacidadAsientos, CapacidadBultos;

        try {
            CapacidadAsientos = Integer.parseInt(capacidadAsientos);
            CapacidadBultos = Integer.parseInt(capacidadBultos);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "La capacidad de asientos y/o bultos deben ser números enteros válidos.");
            return;
        }

        if (CapacidadAsientos < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "La capacidad de asientos disponibles no puede ser menor a cero.");
            return;
        }

        if (CapacidadBultos < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "La capacidad de bultos disponibles no puede ser menor a cero.");
            return;
        }

        Avion avion = new Avion(matricula, CapacidadAsientos, CapacidadBultos, aerolineaUsuario);
        avionMgr.addAvion(avion);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Avión Exitoso", "El avión de matrícula " + matricula + " " + " fue registrado para la aerolínea " + aerolineaUsuario.getNombre());

    }

    @FXML
    public void handleAvionRegisterSuperUsuario(){
        String matricula = txtMatricula.getText();
        String capacidadAsientos = txtCapacidadAsientos.getText();
        String capacidadBultos = txtCapacidadBultos.getText();
        String codigoIATA = txtIATA.getText().trim();
        Aerolinea aerolinea = aerolineaRepository.findByCodigoIATA(codigoIATA);

        if (matricula.isEmpty() || capacidadAsientos.isEmpty() || capacidadBultos.isEmpty() || codigoIATA.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "Todos los campos son obligatorios.");
            return;
        }

        if(avionMgr.existsByMatricula(matricula)){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "Ya existe un avión registrado en el sistema con esta matrícula.");
            return;
        }

        int CapacidadAsientos, CapacidadBultos;

        try {
            CapacidadAsientos = Integer.parseInt(capacidadAsientos);
            CapacidadBultos = Integer.parseInt(capacidadBultos);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "La capacidad de asientos y/o bultos deben ser números enteros válidos.");
            return;
        }

        if (CapacidadAsientos < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "La capacidad de asientos disponibles no puede ser menor a cero.");
            return;
        }

        if (CapacidadBultos < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "La capacidad de bultos disponibles no puede ser menor a cero.");
            return;
        }

        if (aerolinea == null) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Avión", "El avión debe ser asociado a una aerolínea existente en el sistema.");
            return;
        }

        Avion avion = new Avion(matricula, CapacidadAsientos, CapacidadBultos, aerolinea);
        avionMgr.addAvion(avion);
        showAlert(Alert.AlertType.CONFIRMATION, "Registro de Avión Exitoso", "El avión de matrícula " + matricula + " " + " fue registrado para la aerolínea " + aerolinea.getNombre());

    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        ImageView imageView;
        if (alertType == Alert.AlertType.CONFIRMATION) {
            alert.getButtonTypes().setAll(ButtonType.OK);
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/photos/ok.png")));
        } else if (alertType == Alert.AlertType.ERROR) {
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/photos/error.png")));
        } else {
            imageView = null;
        }

        if (imageView != null) {
            imageView.setFitHeight(48);
            imageView.setFitWidth(48);
            alert.setGraphic(imageView);
        }

        alert.showAndWait();
    }

}
