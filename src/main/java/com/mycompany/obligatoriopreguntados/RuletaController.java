/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Modelo.Categoria;
import java.io.IOException;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class RuletaController {

    @FXML
    private Canvas canvas;

    private final String[] categories = {"Ciencia", "Historia", "Arte", "Deportes", "Entretenimiento"};
    private final Color[] colors = {Color.BLUE, Color.GREEN, Color.RED, Color.PURPLE, Color.YELLOW};
    private final Random random = new Random();
    private double currentAngle = 0;

    @FXML
    public void initialize() {
        drawWheel();
        canvas.setOnMouseClicked(event -> spinWheel());
    }

private void drawWheel() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    double centerX = canvas.getWidth() / 2;
    double centerY = canvas.getHeight() / 2;
    double radius = Math.min(centerX, centerY) - 10;
    double angleStep = 360.0 / categories.length;

    // Dibujar segmentos de la ruleta
    for (int i = 0; i < categories.length; i++) {
        gc.setFill(colors[i]);
        gc.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius,
                   currentAngle + i * angleStep, angleStep, javafx.scene.shape.ArcType.ROUND);
    }

    // Dibujar textos en cada segmento
    for (int i = 0; i < categories.length; i++) {
         drawText(gc, categories[i], centerX, centerY, radius, currentAngle + i * angleStep + angleStep / 2);
    }

    // Dibujar la flecha fija
    drawArrowAtPosition(gc, centerX, centerY, radius, 90); // 90 grados apunta hacia arriba
}
    
   private void drawArrowAtPosition(GraphicsContext gc, double centerX, double centerY, double radius, double angle) {
    gc.setFill(Color.BLACK);
    double radians = Math.toRadians(angle);

    // Coordenadas para el triángulo de la flecha
    double arrowTipX = centerX + (radius + 10) * Math.cos(radians);
    double arrowTipY = centerY - (radius + 10) * Math.sin(radians); // Hacia arriba
    double arrowBaseX1 = centerX + (radius + 20) * Math.cos(radians - Math.toRadians(10));
    double arrowBaseY1 = centerY - (radius + 20) * Math.sin(radians - Math.toRadians(10));
    double arrowBaseX2 = centerX + (radius + 20) * Math.cos(radians + Math.toRadians(10));
    double arrowBaseY2 = centerY - (radius + 20) * Math.sin(radians + Math.toRadians(10));

    // Dibujar el triángulo como flecha
    gc.fillPolygon(
        new double[]{arrowTipX, arrowBaseX1, arrowBaseX2},
        new double[]{arrowTipY, arrowBaseY1, arrowBaseY2},
        3
    );
}
    

    private void drawText(GraphicsContext gc, String text, double centerX, double centerY, double radius, double angle) {
    gc.setFill(Color.BLACK);
    gc.setFont(new Font("Arial", 14));
    double radians = Math.toRadians(angle);
    double x = centerX + (radius / 1.5) * Math.cos(radians);
    double y = centerY + (radius / 1.5) * Math.sin(radians);

    // Calcular el ancho y alto del texto
    javafx.scene.text.Text tempText = new javafx.scene.text.Text(text);
    tempText.setFont(gc.getFont());
    double textWidth = tempText.getBoundsInLocal().getWidth();
    double textHeight = tempText.getBoundsInLocal().getHeight();

    // Ajustar las coordenadas del texto para que quede centrado
    gc.fillText(text, x - textWidth / 2, y + textHeight / 4);
}

    private void spinWheel() {
        double spinAngle = 360 * 5 + random.nextInt(360); // Rotación de 5 vueltas completas + un ángulo aleatorio
        RotateTransition rotate = new RotateTransition(Duration.seconds(3), canvas);
        rotate.setByAngle(spinAngle);
        rotate.setOnFinished(event -> {
            try {
                onSpinEnd(spinAngle);
            } catch (IOException ex) {
                Logger.getLogger(RuletaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        rotate.play();
    }

    private void onSpinEnd(double spinAngle) throws IOException {
        currentAngle = (currentAngle + spinAngle) % 360;
        int selectedCategoryIndex = (int) ((360 - currentAngle) / (360 / categories.length)) % categories.length;
        String selectedCategory = categories[selectedCategoryIndex];
        System.out.println("Categoría seleccionada: " + selectedCategory);
        Categoria c = Categoria.getInstance();
        c.setCategoria(selectedCategory);
        App.setRoot("juego");
        
 
    }
    
     
}