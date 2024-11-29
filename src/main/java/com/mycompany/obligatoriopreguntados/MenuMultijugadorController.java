/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Modelo.SesionActual;
import Modelo.Usuario;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author jmv14
 */
public class MenuMultijugadorController {
 
    
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
}
