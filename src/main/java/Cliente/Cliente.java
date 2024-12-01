/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Modelo.Categoria;

import Modelo.JuegoRemoto;
import Modelo.Multijugador;
import Modelo.Partida;

import Modelo.Pregunta;

import Modelo.Usuario;
import Modelo.UsuarioRemote;
import Modelo.UsuariosEnServidor;
import Servidor.ServidorRemoto;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Cliente {


    public Cliente() {

    
    }
    
    // Método para cargar una pregunta desde el servidor
    public Pregunta cargarPregunta() {
        try { 
                   Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
   
              Categoria c = Categoria.getInstance();
              
              String categoria = c.getCategoria();
            return servidor.cargarPregunta(categoria);
        } catch (Exception e) {
            System.err.println("Error al cargar pregunta:");
            e.printStackTrace();
            return null;
        }
    }
       
    // Método para verificar la respuesta del usuario en el servidor
    public boolean verificarRespuesta(String respuesta, Pregunta pregunta) {
        try {
               Registry registry = LocateRegistry.getRegistry("localhost", 1099);
               JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
               if(servidor.verificarRespuesta(respuesta, pregunta) == true){
               servidor.aumentarContador();
               }else{
                 servidor.reiniciarContador();
               }
            return servidor.verificarRespuesta(respuesta, pregunta);
            
        } catch (Exception e) {
            System.err.println("Error al verificar respuesta:");
            e.printStackTrace();
            return false;
        }
    }
    
    public String contadorString() throws RemoteException, NotBoundException{
       Registry registry = LocateRegistry.getRegistry("localhost", 1099);
       JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
        String contadorString = servidor.getContadorString();
        return contadorString;
    }
    public void configurarDificultad() throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
        servidor.configurarDificultad();
    }
    public void crearLobby() throws RemoteException, NotBoundException{
         Registry registry = LocateRegistry.getRegistry("localhost", 1099);
         Multijugador multijugador = (Multijugador) registry.lookup("multijugador");
        
         
         multijugador.crearLobby();
 
    

    }
    
     public List<Partida> obtenerPartidas() {
        try {
              Registry registry = LocateRegistry.getRegistry("localhost", 1099);
         Multijugador multijugador = (Multijugador) registry.lookup("multijugador");
            return multijugador.obtenerPartidasActivas();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

     public String UsuariosConectados() throws RemoteException, NotBoundException{
       Registry registry = LocateRegistry.getRegistry("localhost", 1099);
     UsuariosEnServidor gestionDeUsuarios = (UsuariosEnServidor) registry.lookup("usuariosActivos");
        return(gestionDeUsuarios.listaDeUsuarios());
     }
    
    public boolean verificarGanador() throws RemoteException, NotBoundException{
                 Registry registry = LocateRegistry.getRegistry("localhost", 1099);
               JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
               return servidor.verificarGanador();
    }
    
  public void IngresarUsuario() throws RemoteException, NotBoundException{
  Registry registry = LocateRegistry.getRegistry("localhost", 1099);
     UsuariosEnServidor gestionDeUsuarios = (UsuariosEnServidor) registry.lookup("usuariosActivos");
  
     gestionDeUsuarios.ingresarUsuario();


    
  }
  
  public void reinciarContador() throws RemoteException, NotBoundException{
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
      servidor.reiniciarContador();

  }
  public void aumentarCOntador() throws RemoteException, NotBoundException{
     Registry registry = LocateRegistry.getRegistry("localhost", 1099);
               JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
              servidor.aumentarContador();
  }

    public static void main(String[] args) throws RemoteException {
        // Instancia el cliente y realiza una llamada de prueba
         try {
            // Conectar al servidor RMI
              Registry registry = LocateRegistry.getRegistry("localhost", 1099);
          
              JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador"); 
             UsuariosEnServidor gestionUsuarios = (UsuariosEnServidor) registry.lookup("usuariosActivos");
              List<Usuario> usuariosActivos = gestionUsuarios.usuariosActivos();
              for(Usuario usuario : usuariosActivos){
                  System.out.println(usuario.getNombre());
              }
                System.out.println("Conexión con el servidor establecida.");
        } catch (Exception e) {
            System.err.println("Error al conectar con el servidor RMI:");
            e.printStackTrace();
        }
 
        // Llamada de prueba para cargar una pregunta
       
    }
    

}