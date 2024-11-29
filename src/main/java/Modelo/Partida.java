/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author jmv14
 */
public class Partida implements Serializable {
    private Usuario jugador1;
    private Usuario jugador2;
    private Usuario ganador;
    private int coronasJugador1;
    private int coronasJugador2;

    public Partida(Usuario jugador1) {
        this.jugador1 = jugador1;
    }

    public Partida() {
     
    }

    public Usuario getJugador1() {
        return jugador1;
    }

   
}
