/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author jmv14
 */
public class LobbyEsperaController {
    @FXML
    public void IniciarPartida(){}
    
        @FXML
    public void volverAlMenu() throws IOException{
    App.setRoot("menu");
    }
  
}
