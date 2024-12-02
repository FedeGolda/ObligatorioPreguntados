/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Cliente.Cliente;
import Modelo.SesionActual;
import Modelo.Usuario;
import Servidor.ServidorRemoto;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author jmv14
 */
public class MenuMultijugadorController implements Initializable{
 
     @FXML
    private Label UsuarioLabel;
    @FXML
    public void CrearLobby() throws RemoteException, NotBoundException{
       try {
        SesionActual sesion = SesionActual.getInstance();
        Usuario jugador = sesion.getUsuario();
        if(jugador == null){
         System.out.println("Jugador es null");   
        }
         
        jugador.crearLobby();
        App.setRoot("LobbyEspera");
    } catch (Exception e) {
        e.printStackTrace(); // Para detectar posibles errores
    }
    }
    
    @FXML
    public void Partidas() throws IOException{
    App.setRoot("Partidas");
    }
    
    @FXML
    public void volverAlMenu() throws IOException{
    App.setRoot("menu");
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
}
