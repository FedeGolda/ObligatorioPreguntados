/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

/**
 *
 * @author jmv14
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SesionUsuario extends Remote {
    Usuario getUsuario() throws RemoteException;
    void setUsuario(Usuario usuario) throws RemoteException;
    void cerrarSesion() throws RemoteException;
}

