/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jmv14
 */
public interface UsuariosEnServidor extends Remote{
    void ingresarUsuario() throws RemoteException;
    List<Usuario> usuariosActivos() throws RemoteException;
        String mensaje(String mensaje) throws RemoteException;
        String listaDeUsuarios() throws RemoteException;
     Usuario buscarUsuario(String nombre) throws RemoteException;
}
