/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SesionUsuarioImpl extends UnicastRemoteObject implements SesionUsuario {
    private Usuario usuario;

    public SesionUsuarioImpl() throws RemoteException {
        super();
    }

    @Override
    public Usuario getUsuario() throws RemoteException {
        return usuario;
    }

    @Override
    public void setUsuario(Usuario usuario) throws RemoteException {
        this.usuario = usuario;
    }

    @Override
    public void cerrarSesion() throws RemoteException {
        this.usuario = null;
    }
}