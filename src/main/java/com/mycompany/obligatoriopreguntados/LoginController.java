package com.mycompany.obligatoriopreguntados;

import Modelo.Usuario;

import Modelo.UsuarioManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.*;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private final String usersFilePath = "usuarios.txt";

    // Método para manejar el inicio de sesión
    @FXML
    private void handleLogin() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isUserValid(username, password)) {
            App.setRoot("juego");  // Cambia a la pantalla del juego
        } else {
            showAlert("Error", "Usuario o contraseña incorrectos.");
        }
    }

    // Método para manejar la creación de un nuevo usuario
    @FXML
    private void handleCreateUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Usuario y contraseña no pueden estar vacíos.");
            return;
        }

        if (isUserExist(username)) {
            showAlert("Error", "El usuario ya existe.");
        } else {
            saveUser(username, password);
            showAlert("Éxito", "Usuario creado exitosamente.");
        }
    }

    // Verifica si el usuario existe y la contraseña es correcta
    private boolean isUserValid(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(usersFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(";");
                if (userData[0].equals(username) && userData[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Verifica si el usuario ya existe en el archivo
    private boolean isUserExist(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(usersFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(";");
                if (userData[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Guarda un nuevo usuario en el archivo
    private void saveUser(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersFilePath, true))) {
            bw.write(username + ";" + password);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Muestra una alerta en pantalla
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    public void iniciarSesion() {
            String nombreUsuario = usernameField.getText();
        String password = passwordField.getText();
    try {
        Usuario usuario = UsuarioManager.buscarUsuario(nombreUsuario, password);
        if (usuario != null) {
            // Usuario encontrado, continuar con el juego
                  
              App.setRoot("juego"); 
              
        } else {
            // Mostrar un mensaje de error: usuario no encontrado
        }
    } catch (IOException e) {
        e.printStackTrace();
        // Manejar el error de I/O
    }
}
}
