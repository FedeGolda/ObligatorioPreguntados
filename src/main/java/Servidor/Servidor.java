/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelo.SesionUsuario;
import Modelo.SesionUsuarioImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099); // Puerto por defecto
            SesionUsuario sesionUsuario = new SesionUsuarioImpl();
            registry.rebind("SesionUsuario", sesionUsuario);
            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}