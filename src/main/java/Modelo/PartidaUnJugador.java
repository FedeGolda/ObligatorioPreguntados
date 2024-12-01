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
    
    
    private String dificultad;
    private int Contador = 0;
    
    public void IniciarPartida(){
        try {
            App.setRoot("Ruleta");
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
    public Pregunta cargarPregunta( String categoria) throws RemoteException {
        try {
            
                  
             
            ChatGPTClient chatgpt = new ChatGPTClient();
            Pregunta pregunta = chatgpt.generarPregunta(categoria, this.dificultad);
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
 
    @Override
 public void aumentarContador(){
     int valor = getContador() + 1;
this.Contador = valor;
 }

    private int getContador() {
       return this.Contador; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PartidaUnJugador() throws RemoteException {
    }

    @Override
    public void configurarDificultad() throws RemoteException {
     int rango = this.Contador > 6 ? 3 : (this.Contador > 3 ? 2 : 1);

switch (rango) {
    case 3:
        this.dificultad = "tiene altos conocimientos del tema";
        break;
    case 2:
        this.dificultad = "tiene un conocimiento moderado del tema";
        break;
    case 1:
    default:
        this.dificultad = "desconoce totalmente del tema";
        break;
}
    }
    
    
}
