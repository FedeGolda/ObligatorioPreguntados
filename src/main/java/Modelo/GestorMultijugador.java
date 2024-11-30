/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Servidor.ServidorRemoto;
import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmv14
 */
public class GestorMultijugador extends UnicastRemoteObject implements Multijugador, Serializable {
     private final List<Partida> partidasActivas;
    
     @Override
    public void crearLobby() throws RemoteException, AccessException {
         try {
             SesionActual sesion = SesionActual.getInstance();
             Usuario jugador1 = sesion.getUsuario();
        
             Registry registry = LocateRegistry.getRegistry("localhost", 1099);
             ServidorRemoto servidor = (ServidorRemoto) registry.lookup("Servidor");
           
             servidor.AgregarPartida(jugador1);
             Partida nuevoLobby = new Partida(jugador1);
             partidasActivas.add(nuevoLobby); // Registra la nueva partida
         } catch (NotBoundException ex) {
             Logger.getLogger(GestorMultijugador.class.getName()).log(Level.SEVERE, null, ex);
         }
    
       
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
