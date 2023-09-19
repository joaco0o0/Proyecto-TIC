package uy.edu.um.airport.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uy.edu.um.airport.AirportApplication;

public class JavaFXApplication extends Application {
    private Parent root;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(AirportApplication.getContext()::getBean);
        root = fxmlLoader.load(getClass().getResource("/templates/inicio/InicioDeSesion.fxml").openStream());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        AirportApplication.getContext().close();
    }
}
