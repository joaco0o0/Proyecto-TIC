package uy.edu.um.airport;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import uy.edu.um.airport.ui.JavaFXApplication;

@SpringBootApplication
public class AirportApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        AirportApplication.context = SpringApplication.run(AirportApplication.class);

        Application.launch(JavaFXApplication.class, args);
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
