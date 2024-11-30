/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author jmv14
 */
public class Partida extends UnicastRemoteObject implements Serializable, IPartida {
        private static final long serialVersionUID = 2L;
    private Usuario jugador1;
    private Usuario jugador2;
    private Usuario ganador;
    private int coronasJugador1;
    private int coronasJugador2;

    public Partida(Usuario jugador1) throws RemoteException{
        this.jugador1 = jugador1;
    }

    public Partida() throws RemoteException{
     
    }

    public Usuario getJugador1() {
        return jugador1;
    }

    @Override
    public String toString() {
        return "Partida{" + "jugador1=" + jugador1 + '}';
    }

    @Override
    public String PartidaTexto() throws RemoteException {
                return "Partida{" + "jugador1=" + jugador1 + '}';
    }

    public RemoteRef getRef() {
        return ref;
    }

   
}
