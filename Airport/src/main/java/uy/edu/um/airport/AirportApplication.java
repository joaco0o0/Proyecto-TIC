package uy.edu.um.airport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.airport.entities.Usuario.Usuario;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class AirportApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirportApplication.class, args);
    }

}
