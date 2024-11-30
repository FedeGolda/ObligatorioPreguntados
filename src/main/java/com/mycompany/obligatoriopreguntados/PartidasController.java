/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriopreguntados;

import Cliente.Cliente;
import Modelo.IPartida;
import Modelo.Partida;
import Modelo.SesionActual;
import Modelo.Usuario;
import Servidor.ServidorRemoto;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 *
 * @author jmv14
 */
public class PartidasController {
      @FXML
    private ListView<String> ListView;

    private Cliente cliente;

    @FXML
    public void initialize() throws RemoteException, NotBoundException {
        SesionActual sesion = SesionActual.getInstance();
        Usuario usuario = sesion.getUsuario();
        cliente = usuario.getCliente();
        cargarPartidas();
    }

    private void cargarPartidas() throws RemoteException, NotBoundException {
         Registry registry = LocateRegistry.getRegistry("localhost", 1099);
             ServidorRemoto servidor = (ServidorRemoto) registry.lookup("Servidor");
        List<IPartida> partidas = servidor.partidasDisponibles();
             ObservableList<String> partidasTexto = FXCollections.observableArrayList();

        // Asumiendo que la clase Partida tiene un método toString() adecuado
        for (IPartida partida : partidas) {
            partidasTexto.add(partida.PartidaTexto()); // Cambia esto si necesitas otro formato
        }

        // Configurar la lista en el ListView
        ListView.setItems(partidasTexto);
    
    }
    
   
}
