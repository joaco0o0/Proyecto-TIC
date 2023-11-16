package uy.edu.um.airport.ui.Vuelo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ListaVuelosController {

    @FXML
    private Label LabelHora;

    public void initialize() {
        actualizarHora();
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarHora()));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }

    private void actualizarHora() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LabelHora.setText(ahora.format(formatter));
    }
}
