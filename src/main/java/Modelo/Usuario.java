/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

/**
 *
 * @author jmv14
 */

public class Usuario  extends UnicastRemoteObject implements  UsuarioRemote {
    private String nombreUsuario;
    private String password;

    public Usuario() throws RemoteException{
    }

    public Usuario(String nombreUsuario, String password) throws RemoteException{
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return nombreUsuario.equals(usuario.nombreUsuario) && password.equals(usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, password);
    }

    @Override
    public void notificar(String mensaje) throws RemoteException {
          System.out.println("Mensaje del servidor: " + mensaje); 
    }
}
