/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jmv14
 */
public class GestorMultijugador extends UnicastRemoteObject implements Multijugador, Serializable {
     private final List<Partida> partidasActivas;
    
     @Override
    public void crearLobby() throws RemoteException {
        SesionActual sesion = SesionActual.getInstance();
        Usuario jugador1 = sesion.getUsuario();
        Partida nuevoLobby = new Partida(jugador1);
           partidasActivas.add(nuevoLobby); // Registra la nueva partida
    
       
    }

    public GestorMultijugador() throws RemoteException{
     this.partidasActivas = new ArrayList<>();
    }
    

    @Override
    public void agregarJugador(Usuario jugador2) throws RemoteException {
      
    }

    @Override
    public synchronized List<Partida> obtenerPartidasActivas() throws RemoteException {
        return new ArrayList<>(partidasActivas); // Retorna una copia para evitar modificaciones externas
    }
 
}
