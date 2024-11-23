/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 *
 * @author jmv14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Categoria implements Serializable{
    private static final Categoria instancia = new Categoria();
     
    private String categoria;
    
    private Categoria(){}
    
    public static Categoria getInstance() {
        return instancia;
    }

    public  void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCategoria(){
    return categoria;
    }
}
