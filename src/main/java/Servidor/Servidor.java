package Servidor;



import Modelo.GestorMultijugador;
import Modelo.Partida;
import Modelo.PartidaUnJugador;
import Modelo.Usuario;
import Modelo.UsuariosEnServidorImp;
import java.rmi.Remote;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class Servidor extends UnicastRemoteObject {
  List<Partida> listaPartidas;

    public Servidor() throws RemoteException{
        this.listaPartidas = new ArrayList();
    }
    

 public static void main(String[] args) {
      
        try {
            PartidaUnJugador juego =  new PartidaUnJugador();   
            
            UsuariosEnServidorImp usuariosActivos = new UsuariosEnServidorImp();
            GestorMultijugador multijugador = new GestorMultijugador();
            
          Registry registry = LocateRegistry.createRegistry(1099);
         
           registry.rebind("usuariosActivos", usuariosActivos);
           registry.rebind("PartidaUnJugador", juego);
           registry.rebind("multijugador", multijugador);
           
           System.out.println("Servidor listo");
           
         
       
        } catch (Exception e) {
             System.err.println("Excepción del servidor: " + e.toString());
            e.printStackTrace();
        }
    }

}
   