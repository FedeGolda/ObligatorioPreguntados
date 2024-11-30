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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmv14
 */
public class UsuariosEnServidorImp extends UnicastRemoteObject implements UsuariosEnServidor, Serializable {
  public static final List<Usuario> usuariosActivos = new CopyOnWriteArrayList<>();
    @Override
    public synchronized void ingresarUsuario() throws RemoteException, AccessException {
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        SesionActual sesion = SesionActual.getInstance();
        Usuario usuario = sesion.getUsuario();
        this.usuariosActivos.add(usuario);
    }

    @Override
    public synchronized List<Usuario> usuariosActivos() throws RemoteException{
     return new ArrayList<>(usuariosActivos);
    }

    public UsuariosEnServidorImp() throws RemoteException{
        super();
   
    }
    
      @Override
    public String mensaje(String mensaje) throws RemoteException{
        System.out.println("Usuario ingresado al servidor: " + mensaje);
        
        return "Exito al ingresar al servidor";
    }

    @Override
    public String listaDeUsuarios() throws RemoteException {
     StringBuilder sb = new StringBuilder();
     for(Usuario usuario : usuariosActivos){
      sb.append(usuario.getNombre()).append(", ");
     }
      if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
      
    }

    @Override
    public Usuario buscarUsuario(String nombre) throws RemoteException{
        Usuario usuarioABuscar = null;
        for(Usuario usuario : usuariosActivos){
            if(nombre == usuario.getNombre()){
             usuarioABuscar = usuario;   
            }
        }
        return usuarioABuscar;
    }
    
     
}
