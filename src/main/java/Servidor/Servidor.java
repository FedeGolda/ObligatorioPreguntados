package Servidor;


import Modelo.PartidaUnJugador;
import Modelo.SesionUsuarioImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class Servidor {

   
 public static void main(String[] args) {
     
        try {
            PartidaUnJugador juego =  new PartidaUnJugador();
            SesionUsuarioImpl gestionUsuarios = new SesionUsuarioImpl();
           Registry registry = LocateRegistry.createRegistry(1099);
           
           registry.rebind("PartidaUnJugador", juego);
            registry.rebind("GestionUsuarios", gestionUsuarios);
           System.out.println("Servidor listo");
       
        } catch (Exception e) {
             System.err.println("Excepción del servidor: " + e.toString());
            e.printStackTrace();
        }
    }

}
   