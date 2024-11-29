/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Cliente.Cliente;
import Modelo.Partida;
import Modelo.SesionActual;
import Modelo.Usuario;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author jmv14
 */
public class PartidasController {
      @FXML
    private ListView<String> partidasListView;

    private Cliente cliente;

    @FXML
    public void initialize() {
        SesionActual sesion = SesionActual.getInstance();
        Usuario usuario = sesion.getUsuario();
        cliente = usuario.getCliente();
        cargarPartidas();
    }

    private void cargarPartidas() {
        List<Partida> partidas = cliente.obtenerPartidas();
        if (partidas != null) {
            partidasListView.getItems().clear();
            for (Partida partida : partidas) {
                partidasListView.getItems().add("Partida de: " + partida.getJugador1().getNombreUsuario());
            }
        }
    }
    
   
}
