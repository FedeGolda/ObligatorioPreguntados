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
     
   public String getNombreUsuario() throws RemoteException{
    String nombre = usuario.getNombre();
          return nombre;
   }
   
   public String getPasswordUsuario() throws RemoteException{
     String password = usuario.getPassword();
     return password;
   }
     
}
