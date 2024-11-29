/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jmv14
 */
public interface Multijugador extends Remote {
    void crearLobby() throws RemoteException;
    void agregarJugador(Usuario jugador2) throws RemoteException;
    List<Partida> obtenerPartidasActivas() throws RemoteException;
    
}
