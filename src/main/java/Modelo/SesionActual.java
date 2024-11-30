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
public class SesionActual  implements Serializable{
      private static SesionActual instancia;
   public static  Usuario usuario;
      
     public static SesionActual getInstance() {
             if (instancia == null) {
            instancia = new SesionActual();
        }
        return instancia;
    }

    public static void setUsuario(Usuario usuario) {
        SesionActual.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
     
     
}
