/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentHashMap;

public class SesionUsuarioImpl extends UnicastRemoteObject implements SesionUsuario {
    private Usuario usuario;
 private static ConcurrentHashMap<String, UsuarioRemote> clientesConectados = new ConcurrentHashMap<>();
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
        public  void registrarCliente(String idCliente, UsuarioRemote cliente) throws RemoteException {
        clientesConectados.put(idCliente, cliente);
        System.out.println("Cliente registrado: " + idCliente);
        cliente.notificar("Bienvenido al servidor.");
    }
    
}