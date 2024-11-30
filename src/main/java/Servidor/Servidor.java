package Servidor;



import Modelo.GestorMultijugador;
import Modelo.IPartida;
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



public class Servidor extends UnicastRemoteObject implements ServidorRemoto{
  List<IPartida> listaPartidas = new CopyOnWriteArrayList<>();
  List<Usuario> usuarios = new CopyOnWriteArrayList<>();

    public Servidor() throws RemoteException{
        this.listaPartidas = new ArrayList();
        this.usuarios = new ArrayList();
    }
    

 public static void main(String[] args) {
      
        try {
            PartidaUnJugador juego =  new PartidaUnJugador();   
            
            UsuariosEnServidorImp usuariosActivos = new UsuariosEnServidorImp();
            GestorMultijugador multijugador = new GestorMultijugador();
            Servidor servidor = new Servidor();
          Registry registry = LocateRegistry.createRegistry(1099);
         
           registry.rebind("usuariosActivos", usuariosActivos);
            registry.rebind("Servidor", servidor);
           registry.rebind("PartidaUnJugador", juego);
           registry.rebind("multijugador", multijugador);
           
           System.out.println("Servidor listo");
           
         
       
        } catch (Exception e) {
             System.err.println("Excepción del servidor: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void AgregarUsuario(String nombre, String password) throws RemoteException {
        Usuario usuario =  new Usuario(nombre, password);
        this.usuarios.add(usuario);
        System.out.println("Usuario agregado");
         System.out.println("Usuarios conectados:");
        for(Usuario u : usuarios){
        System.out.println(u.getNombre());
        }
    }

    @Override
    public synchronized List<Usuario> usuariosConectados() throws RemoteException {
        return new ArrayList<>(usuarios);
      
    }
   
  @Override
    public  List<IPartida> partidasDisponibles() throws RemoteException {
          return new ArrayList<>(listaPartidas);
    }

    @Override
    public void AgregarPartida(Usuario usuario) throws RemoteException {
  
       Partida nuevaPartida = new Partida(usuario);
       listaPartidas.add(nuevaPartida);
       System.out.println("Partida creada");
    }

 

}
   