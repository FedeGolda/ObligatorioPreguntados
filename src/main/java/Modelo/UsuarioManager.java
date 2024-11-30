/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jmv14
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {

    public static Usuario buscarUsuario(String nombreUsuario, String password) throws IOException {
        List<Usuario> usuarios = cargarUsuariosDesdeArchivo("J:/Programacion3/Java/NetBeans-22/Projects/PreguntadosDDA/ObligatorioPreguntados/usuarios.txt");

        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getPassword().equals(password)) {
                return usuario; // Retorna el usuario si coincide
            }
        }
        return null; // Retorna null si no hay coincidencia
    }

    private static List<Usuario> cargarUsuariosDesdeArchivo(String rutaArchivo) throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";"); // Asume que el archivo está en formato nombre,password
                if (datos.length == 2) {
                    usuarios.add(new Usuario(datos[0], datos[1]));
                }
            }
        }
        return usuarios;
    }
}
