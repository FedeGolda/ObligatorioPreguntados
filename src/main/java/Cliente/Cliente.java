/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Modelo.Categoria;
import Modelo.JuegoRemoto;
import Modelo.Pregunta;
import Modelo.SesionUsuario;
import Modelo.Usuario;
import Modelo.UsuarioRemote;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {


    public Cliente() {

    
    }
    
    // Método para cargar una pregunta desde el servidor
    public Pregunta cargarPregunta() {
        try { 
                   Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
              SesionUsuario gestionUsuario = (SesionUsuario) registry.lookup("GestionUsuarios");
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
            return servidor.verificarRespuesta(respuesta, pregunta);
        } catch (Exception e) {
            System.err.println("Error al verificar respuesta:");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verificarGanador() throws RemoteException, NotBoundException{
                 Registry registry = LocateRegistry.getRegistry("localhost", 1099);
               JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador");
               return servidor.verificarGanador();
    }

    public static void main(String[] args) throws RemoteException {
        // Instancia el cliente y realiza una llamada de prueba
         try {
            // Conectar al servidor RMI
              Registry registry = LocateRegistry.getRegistry("localhost", 1099);
          
              JuegoRemoto servidor = (JuegoRemoto) registry.lookup("PartidaUnJugador"); 
      SesionUsuario gestionUsuario = (SesionUsuario) registry.lookup("GestionUsuarios");
            System.out.println("Conexión con el servidor establecida.");
        } catch (Exception e) {
            System.err.println("Error al conectar con el servidor RMI:");
            e.printStackTrace();
        }
 
        // Llamada de prueba para cargar una pregunta
       
    }
    
    public void setUsuario(Usuario usuario) throws RemoteException, NotBoundException{
                     Registry registry = LocateRegistry.getRegistry("localhost", 1099);
               SesionUsuario servidor = (SesionUsuario) registry.lookup("GestionUsuarios");
               
              servidor.setUsuario(usuario);

    }
    
    public void registrarCliente(String Username, UsuarioRemote usuario) throws RemoteException, NotBoundException{
                        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
               SesionUsuario servidor = (SesionUsuario) registry.lookup("GestionUsuarios");
               servidor.registrarCliente(Username, usuario);
    }

}