/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Modelo.ChatGPTClient;
import Modelo.Pregunta;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author jmv14
 */
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

     private Pregunta pregunta;

   private ChatGPTClient chatGPTClient;
public JuegoController(){};



    @Override
    public void initialize(URL location, ResourceBundle resources) {
          try {
        chatGPTClient = new ChatGPTClient();
        cargarPregunta();
     
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    private void cargarPregunta() {
      try {
        // Llama a la API para generar una nueva pregunta
      pregunta = chatGPTClient.generarPregunta();

        // Verifica que los valores de pregunta y opciones no sean nulos
        if (pregunta != null && pregunta.getPregunta() != null && pregunta.getOpciones() != null) {
            preguntaLabel.setText(pregunta.getPregunta());
            String[] opciones = pregunta.getOpciones();

            // Verifica que haya al menos cuatro opciones
            if (opciones.length >= 4) {
                opcion1Button.setText(opciones[0]);
                opcion2Button.setText(opciones[1]);
                opcion3Button.setText(opciones[2]);
                opcion4Button.setText(opciones[3]);
                
                 opcion1Button.setOnAction(event -> verificarRespuesta(opciones[0]));
                opcion2Button.setOnAction(event -> verificarRespuesta(opciones[1]));
                opcion3Button.setOnAction(event -> verificarRespuesta(opciones[2]));
                opcion4Button.setOnAction(event -> verificarRespuesta(opciones[3]));
            } else {
                // Maneja el caso en el que haya menos de cuatro opciones
                preguntaLabel.setText("Error: menos de cuatro opciones recibidas");
            }
        } else {
            // Maneja el caso en el que la pregunta o las opciones sean nulas
            preguntaLabel.setText("Error: datos de pregunta incompletos");
        }
    } catch (IOException e) {
        e.printStackTrace();
        preguntaLabel.setText("Error al cargar la pregunta");
    }
    }
    
    @FXML
      private void verificarRespuesta(String respuestaSeleccionada) {
        // Comprobar si la respuesta seleccionada es correcta
        if (respuestaSeleccionada.equals(pregunta.getRespuestaCorrecta())) {
            mostrarMensajeCorrecto();
            cargarPregunta(); // Cargar una nueva pregunta si la respuesta es correcta
        } else {
            mostrarMensajeIncorrecto();
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

    
}