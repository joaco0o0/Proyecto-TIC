package uy.edu.um.airport.ui.AropuertoYAerolinea;

import javafx.fxml.FXML;
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

        String nombreAerolineaIngresado = nombreAerolinea.getText().trim();
        String codigoICAOIngresado = codigoICAOAerolinea.getText().trim();
        String nombrePais = paisOrigen.getText().trim();
        String codigoIATA = codigoIATAAerolinea.getText().trim();
        Aerolinea aerolinea = aerolineaRepository.findByCodigoIATA(codigoIATA);

        if (aerolinea == null) {
            System.err.println("La aerolínea con el código IATA proporcionado no existe.");
            return;
        }


        // Comprobar que el nombre y el código ICAO ingresados coinciden con los de la aerolínea recuperada
        if (!codigoICAOIngresado.equals(aerolinea.getCodigoICAO())) {
            System.err.println("El código ICAO ingresado no coincide con el de la aerolínea.");
            return;
        }
        if (!nombreAerolineaIngresado.equals(aerolinea.getNombre())){
            System.err.println("El nombre ingresado no coincide con el de la aerolínea.");
            return;
        }

        if(!nombrePais.equals(aerolinea.getPaisDeOrigen())){
            System.err.println("El país ingresado no coincide con el de la aerolínea.");
            return;
        }

        // Aquí podrías, si es necesario, actualizar otros datos de la aerolínea con la información de los otros campos TextField.

        // Crear la asociación y guardarla
        AeropuertoYAerolinea asociacion = new AeropuertoYAerolinea(aeropuertoUsuario, aerolinea);
        aeropuertoYAerolineaMgr.addAsociacion(asociacion);  // Asumiendo que el manager tiene un método addAsociacion()
    }


    @FXML
    public void asociarAerolineaConAeropuertoDeSuperUsuario() {

        String nombreAerolineaIngresado = nombreAerolinea.getText().trim();
        String codigoICAOIngresado = codigoICAOAerolinea.getText().trim();
        String nombrePais = paisOrigen.getText().trim();
        String codigoIATA = codigoIATAAerolinea.getText().trim();
        Aerolinea aerolinea = aerolineaRepository.findByCodigoIATA(codigoIATA);

        if (aerolinea == null) {
            System.err.println("La aerolínea con el código IATA proporcionado no existe.");
            return;
        }

        // Comprobar que el nombre, código ICAO y país ingresados coinciden con los de la aerolínea recuperada
        if (!codigoICAOIngresado.equals(aerolinea.getCodigoICAO())) {
            System.err.println("El código ICAO ingresado no coincide con el de la aerolínea.");
            return;
        }
        if (!nombreAerolineaIngresado.equals(aerolinea.getNombre())) {
            System.err.println("El nombre ingresado no coincide con el de la aerolínea.");
            return;
        }
        if (!nombrePais.equals(aerolinea.getPaisDeOrigen())) {
            System.err.println("El país ingresado no coincide con el de la aerolínea.");
            return;
        }

        // Obtener el aeropuerto usando el código IATA proporcionado en el TextField
        String codigoIATAAeropuertoIngresado = codigoIATAAeropuerto.getText().trim();
        Aeropuerto aeropuerto = aeropuertoRepository.findByCodigoIATA(codigoIATAAeropuertoIngresado);

        if (aeropuerto == null) {
            System.err.println("El aeropuerto con el código IATA proporcionado no existe.");
            return;
        }

        // Crear la asociación y guardarla
        AeropuertoYAerolinea asociacion = new AeropuertoYAerolinea(aeropuerto, aerolinea);
        aeropuertoYAerolineaMgr.addAsociacion(asociacion);  // Asumiendo que el manager tiene un método addAsociacion()
    }


}
