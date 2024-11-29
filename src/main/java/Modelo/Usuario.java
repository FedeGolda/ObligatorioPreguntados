/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Cliente.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteRef;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author jmv14
 */

public class Usuario  extends UnicastRemoteObject implements Serializable {
       private static final long serialVersionUID = 1L;
    private String nombreUsuario;
    private String password;
    private Cliente cliente;
  
    
    public Usuario() throws RemoteException{
       
    }

    public Usuario(String nombreUsuario, String password) throws RemoteException{
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.cliente = new Cliente();
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

    public Cliente getCliente() {
        if(this.cliente == null){
        this.cliente = new Cliente();
        }
        return cliente;
    }

  public void IngresarUsuario() throws RemoteException, NotBoundException {
      this.cliente.IngresarUsuario();
  }
  
  public void crearLobby() throws RemoteException, NotBoundException{
  this.cliente.crearLobby();
    System.out.println("Lobby creado");
  }

    public RemoteRef getRef() {
        return ref;
    }

    

 

}
