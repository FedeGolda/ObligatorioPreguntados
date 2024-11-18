/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Modelo.PartidaUnJugador;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author jmv14
 */
public class MenuController {
    
    @FXML
    private Button PartidaUnJugadorButton;
    
    @FXML
    public void IniciarPartida() throws IOException{
    PartidaUnJugador partida = new PartidaUnJugador();
    
    partida.IniciarPartida();
    }
    
}
