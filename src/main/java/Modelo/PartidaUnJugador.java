/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Servidor.Servidor;
import com.mycompany.obligatoriopreguntados.App;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmv14
 */
public class PartidaUnJugador extends UnicastRemoteObject implements JuegoRemoto {
    
    
    
    private int Contador = 0;
    
    public void IniciarPartida(){
        try {
            App.setRoot("juego");
        } catch (IOException ex) {
            Logger.getLogger(PartidaUnJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Override
    public boolean verificarRespuesta(String respuesta, Pregunta pregunta) throws RemoteException {
        // Verifica si la respuesta es correcta
        return pregunta.getRespuestaCorrecta().equals(respuesta);
    }

    @Override
    public Pregunta cargarPregunta() throws RemoteException {
        try {
            ChatGPTClient chatgpt = new ChatGPTClient();
            Pregunta pregunta = chatgpt.generarPregunta();
            return pregunta;
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, "Error al cargar pregunta", ex);
        }
        return null;
    }

    @Override
    public boolean verificarGanador() throws RemoteException { 
        if(this.Contador == 3){
    return true;
        }
        return false;
    }

    public void setContador(int Contador) {
        this.Contador = Contador;
    }
 
 public void aumentarContador(){
     int valor = getContador() + 1;
this.Contador = valor;
 }

    private int getContador() {
       return this.Contador; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PartidaUnJugador() throws RemoteException {
    }
    
    
}