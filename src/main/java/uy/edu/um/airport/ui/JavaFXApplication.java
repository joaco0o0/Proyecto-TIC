package uy.edu.um.airport.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import uy.edu.um.airport.AirportApplication;
import uy.edu.um.airport.entities.Role.Rol;
import uy.edu.um.airport.entities.Usuario.Usuario;
import uy.edu.um.airport.entities.Usuario.UsuarioMgr;

import java.time.LocalDate;

public class JavaFXApplication extends Application {
    //@RequestMapping(path = "api/v1/usuarios")
    private Parent root;

    @Autowired
    private UsuarioMgr usuarioMgr;

    @Override
    public void init() throws Exception {
        usuarioMgr = AirportApplication.getContext().getBean(UsuarioMgr.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        if (primeravezqueseejecuta()){
            String nombre = "SuperAdmin";
            String apellidos = "SuperAdmin";
            LocalDate fechaNacimiento = LocalDate.now();
            String pasaporte = "123456789";
            String email = "manager@gmail.com";
            String contraseña = "manager";
            Rol rol = Rol.SUPER_USUARIO;
            Usuario newUser = new Usuario(nombre,apellidos,fechaNacimiento,pasaporte,email,contraseña,rol);
            usuarioMgr.addUsuario(newUser);
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AirportApplication.getContext()::getBean);
        root = fxmlLoader.load(getClass().getResource("/templates/inicio/InicioDeSesion.fxml").openStream());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private boolean primeravezqueseejecuta() {
        return AirportApplication.getContext().getBean(uy.edu.um.airport.entities.Usuario.UsuarioMgr.class).getCantidadDeUsuarios() == 0;
    }

    @Override
    public void stop() {
        AirportApplication.getContext().close();
    }
}
