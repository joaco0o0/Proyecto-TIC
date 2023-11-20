package uy.edu.um.airport.ui.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.airport.entities.Aerolinea.Aerolinea;
import uy.edu.um.airport.entities.Aerolinea.AerolineaMgr;
import uy.edu.um.airport.entities.Pasajeros.Pasajeros;
import uy.edu.um.airport.entities.Pasajeros.PasajerosMgr;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;
import uy.edu.um.airport.entities.Vuelo.VueloMgr;
import uy.edu.um.airport.session.Session;

import java.io.IOException;
import java.time.LocalDate;

@Component
public class InterfazUsuarioAerolineaController {
    @Autowired
    private AerolineaMgr aerolineaMgr;
    @Autowired
    private VueloMgr VueloMgr;
    @Autowired
    private UsuarioMgr usuarioMgr;

    @Autowired
    private PasajerosMgr pasajerosMgr;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private DatePicker fechanacimiento;
    @FXML
    private TextField pasaporte;
    @FXML
    private TextField codigovuelo;

    @FXML
    public void openListaVuelos() {
        loadWindow("/Templates/Inicio/TablaVuelosLista.fxml");
    }

    @FXML
    public void openCrearVuelo() {
        loadWindow("/Templates/Inicio/CrearVuelo.fxml");}

    @FXML
    public void openCrearAvion() {
        loadWindow("/Templates/Inicio/CrearAvion.fxml");}

    @FXML
    public void openAsignarPasajeroAVuelo() {
        loadWindow("/Templates/Inicio/AsignarPasajeroAVuelo.fxml");}
    @FXML
    public void openRegistroPersonalAerolinea() {
        loadWindow("/Templates/Inicio/RegistroPersonalAerolinea.fxml");
    }


    private void loadWindow(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void asignarPasajeroAVuelo() {
        Usuario usuarioLogueado = Session.getInstance().getCurrentUser();
        String iata = usuarioLogueado.getAerolinea().getCodigoIATA();
        Aerolinea aerolineaEncontrada = aerolineaMgr.findAerolineaByCodigoIATA(iata);

        String nombrePasajero = nombre.getText();
        String apellidoPasajero = apellido.getText();
        LocalDate fechaNacimiento = fechanacimiento.getValue();
        String pasaportePasajero = pasaporte.getText();
        String numeroVuelo = codigovuelo.getText();

        if(nombrePasajero.isEmpty() || apellidoPasajero.isEmpty() || fechaNacimiento == null || pasaportePasajero.isEmpty() || numeroVuelo.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Pasajero", "Todos los campos son obligatorios.");
        }

        Long NumeroVuelo;
        try {
            NumeroVuelo = Long.parseLong(codigovuelo.getText().trim());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asignación de Pasaje a Pasajero", "El número de vuelo debe ser un número válido.");
            return;
        }
        if (NumeroVuelo < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en la Asignación de Pasaje a Pasajero", "El número de vuelo no puede ser menor a cero.");
            return;
        }

        uy.edu.um.airport.entities.Vuelo.Vuelo vueloEncontrado = VueloMgr.findVueloByCodigoVuelo(NumeroVuelo);

        if(vueloEncontrado == null){
            showAlert(Alert.AlertType.ERROR, "Error en la Asignación de Pasaje a Pasajero", "El vuelo solicitado no existe en el sistema.");
            return;
        }

        if(aerolineaEncontrada.getCodigoIATA() != vueloEncontrado.getAerolinea().getCodigoIATA()){
            showAlert(Alert.AlertType.ERROR, "Error en la Asignación de Pasaje a Pasajero", "El vuelo solicitado no pertenece a su aerolínea.");
            return;
        }

        if(vueloEncontrado.getCapacidadAsientos() == 0){
            showAlert(Alert.AlertType.ERROR, "Error en la Asignación de Pasaje a Pasajero", "El vuelo " + vueloEncontrado.getNumeroVuelo() + " no posee asientos disponibles.");
            return;
        }

        Usuario pasajero =  usuarioMgr.findUsuarioByPasaporte(pasaportePasajero);
        if (pasajero == null) {
            pasajero = new Usuario(nombrePasajero, apellidoPasajero, fechaNacimiento, pasaportePasajero, "sistema_aeropuerto@gmail.com","",Rol.USUARIO_FINAL);
            usuarioMgr.addUsuario(pasajero);
            System.out.println("Usuario no existia");
        }

        Pasajeros pasajeros = new Pasajeros(vueloEncontrado, pasajero);
        pasajerosMgr.addPasajero(pasajeros);
        vueloEncontrado.setCapacidadAsientos(vueloEncontrado.getCapacidadAsientos() - 1);
        VueloMgr.updateVuelo(vueloEncontrado);
        showAlert(Alert.AlertType.CONFIRMATION, "Pasajero Asignado a Vuelo Exitoso", "El pasajero " + pasajero.getNombre() + " " + pasajero.getApellido() + " fue asignado al vuelo " + vueloEncontrado.getNumeroVuelo());
    }

    @FXML
    public void asignarPasajeroAVueloSuperUsuario() {
        String nombrePasajero = nombre.getText();
        String apellidoPasajero = apellido.getText();
        LocalDate fechaNacimiento = fechanacimiento.getValue();
        String pasaportePasajero = pasaporte.getText();
        String numeroVuelo = codigovuelo.getText();

        if(nombrePasajero.isEmpty() || apellidoPasajero.isEmpty() || fechaNacimiento == null || pasaportePasajero.isEmpty() || numeroVuelo.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Error en la Asignación de Pasaje a Pasajero", "Todos los campos son obligatorios.");
        }

        Long NumeroVuelo;
        try {
            NumeroVuelo = Long.parseLong(codigovuelo.getText().trim());
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Pasajero", "El número de vuelo debe ser un número válido.");
            return;
        }
        if (NumeroVuelo < 0) {
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Pasajero", "El número de vuelo no puede ser menor a cero.");
            return;
        }

        uy.edu.um.airport.entities.Vuelo.Vuelo vueloEncontrado = VueloMgr.findVueloByCodigoVuelo(NumeroVuelo);

        if(vueloEncontrado == null){
            showAlert(Alert.AlertType.ERROR, "Error en el Registro de Pasajero", "El vuelo solicitado no existe en el sistema.");
            return;
        }

        if(vueloEncontrado.getCapacidadAsientos() == 0){
            showAlert(Alert.AlertType.ERROR, "Error en la asignación de Pasaje", "El vuelo " + vueloEncontrado.getNumeroVuelo() + " no posee asientos disponibles.");
            return;
        }

        Usuario pasajero =  usuarioMgr.findUsuarioByPasaporte(pasaportePasajero);
        if (pasajero == null) {
            pasajero = new Usuario(nombrePasajero, apellidoPasajero, fechaNacimiento, pasaportePasajero, "sistema_aeropuerto@gmail.com","",Rol.USUARIO_FINAL);
            usuarioMgr.addUsuario(pasajero);
            System.out.println("Usuario no existia");
        }

        Pasajeros pasajeros = new Pasajeros(vueloEncontrado, pasajero);
        pasajerosMgr.addPasajero(pasajeros);
        vueloEncontrado.setCapacidadAsientos(vueloEncontrado.getCapacidadAsientos() - 1);
        VueloMgr.updateVuelo(vueloEncontrado);
        showAlert(Alert.AlertType.CONFIRMATION, "Pasajero Asignado a Vuelo Exitoso", "El pasajero " + pasajero.getNombre() + " " + pasajero.getApellido() + " fue asignado al vuelo " + vueloEncontrado.getNumeroVuelo());
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