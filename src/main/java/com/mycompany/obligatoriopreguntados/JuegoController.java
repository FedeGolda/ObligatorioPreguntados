package com.mycompany.obligatoriopreguntados;

import Cliente.Cliente;
import Modelo.PartidaUnJugador;
import Modelo.Pregunta;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class JuegoController implements Initializable {
    @FXML
    private Label preguntaLabel;
    @FXML
    private Button opcion1Button;
    @FXML
    private Button opcion2Button;
    @FXML
    private Button opcion3Button;
    @FXML
    private Button opcion4Button;
    @FXML
    private Label nombreJugador;

    private Pregunta pregunta;
    private Cliente cliente;
    private PartidaUnJugador partida;

    public JuegoController() {
        // Constructor vacío (necesario para JavaFX)
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cliente = new Cliente(); 

         
            cargarPregunta(); // Carga la primera pregunta
        } catch (Exception e) {
            mostrarMensajeError("Error de Inicialización", "No se pudo iniciar el juego. Intente nuevamente.");
            e.printStackTrace();
        }
    }

    private void cargarPregunta() {
        pregunta = cliente.cargarPregunta(); // Llama al método remoto para obtener una nueva pregunta
        if (pregunta != null && pregunta.getPregunta() != null && pregunta.getOpciones() != null) {
            preguntaLabel.setText(pregunta.getPregunta());
            String[] opciones = pregunta.getOpciones();

            if (opciones.length >= 4) {
                opcion1Button.setText(opciones[0]);
                opcion2Button.setText(opciones[1]);
                opcion3Button.setText(opciones[2]);
                opcion4Button.setText(opciones[3]);
                
                // Asigna acciones a cada botón de opción
                asignarAccionBoton(opcion1Button, opciones[0]);
                asignarAccionBoton(opcion2Button, opciones[1]);
                asignarAccionBoton(opcion3Button, opciones[2]);
                asignarAccionBoton(opcion4Button, opciones[3]);
            } else {
                mostrarMensajeError("Error de Pregunta", "Se recibieron menos de cuatro opciones.");
            }
        } else {
            mostrarMensajeError("Error de Pregunta", "Datos de pregunta incompletos.");
        }
    }

    private void asignarAccionBoton(Button boton, String respuesta) {
        boton.setOnAction(event -> {
            try {
                verificarRespuesta(respuesta);
            } catch (IOException ex) {
                Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(JuegoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void verificarRespuesta(String respuestaSeleccionada) throws IOException, NotBoundException {
        if (cliente.verificarRespuesta(respuestaSeleccionada, pregunta)) {
            mostrarMensajeCorrecto();
          cargarPregunta();
            if (cliente.verificarGanador() ) {
                App.setRoot("menu");
            } else {
                // Cargar una nueva pregunta si la respuesta es correcta
            }
        } else {
            mostrarMensajeIncorrecto();
            App.setRoot("menu");
        }
    }

    private void mostrarMensajeCorrecto() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Respuesta Correcta");
        alert.setHeaderText(null);
        alert.setContentText("¡Respuesta correcta! Generando nueva pregunta...");
        alert.showAndWait();
    }

    private void mostrarMensajeIncorrecto() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Respuesta Incorrecta");
        alert.setHeaderText(null);
        alert.setContentText("Respuesta incorrecta. Intenta nuevamente.");
        alert.showAndWait();
    }

    private void mostrarMensajeError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
