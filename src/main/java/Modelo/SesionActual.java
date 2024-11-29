/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author jmv14
 */
public class SesionActual implements Serializable{
      private static final SesionActual instancia = new SesionActual();
      private Usuario usuario;
      
     public static SesionActual getInstance() {
        return instancia;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
     
     
}
