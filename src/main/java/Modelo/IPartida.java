/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jmv14
 */
public interface IPartida extends Remote{
    String PartidaTexto() throws RemoteException;
}
