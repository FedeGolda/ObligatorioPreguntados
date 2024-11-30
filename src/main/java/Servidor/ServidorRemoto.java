/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Servidor;

import Modelo.IPartida;
import Modelo.Partida;
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
    List<IPartida> partidasDisponibles() throws RemoteException;
    void AgregarPartida(Usuario usuario) throws RemoteException; 
}
