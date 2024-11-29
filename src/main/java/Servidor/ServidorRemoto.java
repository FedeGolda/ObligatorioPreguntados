/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servidor;

import Modelo.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author jmv14
 */
public interface ServidorRemoto extends Remote{
    void AgregarUsuario(String nombre, String password) throws RemoteException;
    List<Usuario> usuariosConectados() throws RemoteException;

}
