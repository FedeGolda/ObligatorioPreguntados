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

        for (int i = 0; i < categories.length; i++) {
            gc.setFill(colors[i]);
            gc.fillArc(centerX - radius, centerY - radius, 2 * radius, 2 * radius, 
                       currentAngle + i * angleStep, angleStep, javafx.scene.shape.ArcType.ROUND);
            drawText(gc, categories[i], centerX, centerY, radius, currentAngle + (i + 0.5) * angleStep);
        }
    }

    private void drawText(GraphicsContext gc, String text, double centerX, double centerY, double radius, double angle) {
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("Arial", 14));
        double radians = Math.toRadians(angle);
        double x = centerX + (radius / 1.5) * Math.cos(radians);
        double y = centerY + (radius / 1.5) * Math.sin(radians);
        gc.fillText(text, x - text.length() * 3, y);
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