package uy.edu.um.airport.ui.AropuertoYAerolinea;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aeropuerto.Aeropuerto;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolinea;
import uy.edu.um.airport.entities.AeropuertoYAerolinea.AeropuertoYAerolineaMgr;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.persistence.AerolineaRepository;
import uy.edu.um.airport.persistence.AeropuertoRepository;
import uy.edu.um.airport.session.Session;

@Component
public class AeropuertoYAerolineaController {

    @Autowired
    private AeropuertoYAerolineaMgr aeropuertoYAerolineaMgr;

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    @Autowired
    private AerolineaRepository aerolineaRepository;

    @FXML
    private javafx.scene.control.TextField nombreAerolinea;
    @FXML
    private javafx.scene.control.TextField codigoIATAAerolinea;
    @FXML
    private javafx.scene.control.TextField codigoICAOAerolinea;
    @FXML
    private javafx.scene.control.TextField paisOrigen;

    //Unicamente para Super Usuario
    @FXML
    private javafx.scene.control.TextField codigoIATAAeropuerto;


    @FXML
    public void asociarAerolineaConAeropuertoDeUsuario() {
        // Obtener el aeropuerto asociado al usuario logueado
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        Aeropuerto aeropuertoUsuario = usuarioLogueado.getAeropuerto();
        String nombreAerolinea = this.nombreAerolinea.getText().trim();
        String codigoICAO = codigoICAOAerolinea.getText().trim();
        String nombrePais = paisOrigen.getText().trim();
        String codigoIATA = codigoIATAAerolinea.getText().trim();
        Aerolinea aerolinea = aerolineaRepository.findByCodigoIATA(codigoIATA);

        if(nombreAerolinea.isEmpty() || codigoIATA.isEmpty() || codigoICAO.isEmpty() || nombrePais.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de la Aerolínea a tu Aeropuerto", "Todos los campos son obligatorios.");
            return;
        }

        if (aerolinea == null) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de la Aerolínea a tu Aeropuerto", "La aerolínea solicitada no se encuentra registrada en el sistema.");
            return;
        }

        // Comprobar que el nombre y el código ICAO ingresados coinciden con los de la aerolínea recuperada
        if (!nombreAerolinea.equals(aerolinea.getNombre())) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de la Aerolínea a tu Aeropuerto", "El nombre de la aerolínea no coincide con la del código IATA.");
            return;
        }

        if (!codigoICAO.equals(aerolinea.getCodigoICAO())) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de la Aerolínea a tu Aeropuerto", "El código ICAO de la aerolínea no coincide con la del código IATA.");
            return;
        }

        if (!nombrePais.equals(aerolinea.getPaisDeOrigen())) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de la Aerolínea a tu Aeropuerto", "El país de la aerolínea no coincide con la del código IATA.");
            return;
        }

        // Crear la asociación y guardarla
        AeropuertoYAerolinea asociacion = new AeropuertoYAerolinea(aeropuertoUsuario, aerolinea);
        aeropuertoYAerolineaMgr.addAsociacion(asociacion);  // Asumiendo que el manager tiene un método addAsociacion()
        showAlert(Alert.AlertType.CONFIRMATION, "Aerolìnea asociada a Aeropuerto Exitosamente", " La aerolínea " + aerolinea.getNombre() + " fue asociada al aeropuerto " + aeropuertoUsuario.getNombre());

    }

    @FXML
    public void asociarAerolineaConAeropuertoDeSuperUsuario() {
        String codigoIATAAeropuerto = this.codigoIATAAeropuerto.getText().trim();
        Aeropuerto aeropuerto = aeropuertoRepository.findByCodigoIATA(codigoIATAAeropuerto);
        String nombreAerolinea = this.nombreAerolinea.getText().trim();
        String codigoIATA = codigoIATAAerolinea.getText().trim();
        Aerolinea aerolinea = aerolineaRepository.findByCodigoIATA(codigoIATA);
        String codigoICAO = codigoICAOAerolinea.getText().trim();
        String nombrePais = paisOrigen.getText().trim();


        if(codigoIATAAeropuerto.isEmpty() || nombreAerolinea.isEmpty() || codigoIATA.isEmpty() || codigoICAO.isEmpty() || nombrePais.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de una Aerolínea a un Aeropuerto", "Todos los campos son obligatorios.");
            return;
        }

        if (aeropuerto == null) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de una Aerolínea a un Aeropuerto", "El aeropuerto solicitado no se encuentra registrado en el sistema.");
            return;
        }

        if (aerolinea == null) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de una Aerolínea a un Aeropuerto", "La aerolínea solicitada no se encuentra registrada en el sistema.");
            return;
        }

        // Comprobar que el nombre, código ICAO y país ingresados coinciden con los de la aerolínea recuperada
        if (!nombreAerolinea.equals(aerolinea.getNombre())) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de una Aerolínea a un Aeropuerto", "El nombre de la aerolínea no coincide con la del código IATA.");
            return;
        }

        if (!codigoICAO.equals(aerolinea.getCodigoICAO())) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de una Aerolínea a un Aeropuerto", "El código ICAO de la aerolínea no coincide con la del código IATA.");
            return;
        }

        if (!nombrePais.equals(aerolinea.getPaisDeOrigen())) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asociación de una Aerolínea a un Aeropuerto", "El país de la aerolínea no coincide con la del código IATA.");
            return;
        }

        // Crear la asociación y guardarla
        AeropuertoYAerolinea asociacion = new AeropuertoYAerolinea(aeropuerto, aerolinea);
        aeropuertoYAerolineaMgr.addAsociacion(asociacion);  // Asumiendo que el manager tiene un método addAsociacion()
        showAlert(Alert.AlertType.CONFIRMATION, "Aerolìnea asociada a Aeropuerto Exitosamente", " La aerolínea " + aerolinea.getNombre() + " fue asociada al aeropuerto " + aeropuerto.getNombre());

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
