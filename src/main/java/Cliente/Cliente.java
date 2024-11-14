/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Modelo.SesionUsuario;
import Modelo.Usuario;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            SesionUsuario sesionUsuario = (SesionUsuario) registry.lookup("SesionUsuario");

            // Ejemplo: establecer el usuario en sesión
            Usuario usuario = new Usuario("nombreUsuario", "password");
            sesionUsuario.setUsuario(usuario);

            // Ejemplo: obtener el usuario en sesión
            Usuario usuarioActivo = sesionUsuario.getUsuario();
            System.out.println("Usuario activo: " + usuarioActivo.getNombreUsuario());

            // Cerrar sesión
            sesionUsuario.cerrarSesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}