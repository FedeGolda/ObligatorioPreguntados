/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Modelo.PartidaUnJugador;
import Modelo.SesionActual;
import Modelo.Usuario;
import Modelo.UsuariosEnServidor;
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
public class MenuController implements Initializable{
    

    @FXML
    private Label UsuarioLabel;
    @FXML
    private Button MultiJugadorButton;
    
    @FXML
    public void IniciarPartida() throws IOException{

    
    App.setRoot("Ruleta");
    }
    
    @FXML
    public void AMenuMultijugador() throws IOException{
      App.setRoot("menuMultijugador");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    SesionActual sesion = SesionActual.getInstance();
    Usuario usuario = sesion.getUsuario();
        try {
            UsuarioLabel.setText(usuario.getNombre());
        
        } catch (RemoteException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
  }
