/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sergio RC
 */
public class Partida implements Serializable{
    ArrayList<Usuario> jugadores=new ArrayList<Usuario>();
    String clave;
    String host;

    public Partida(String clave, String host){
        this.clave=clave;
        this.host=host;
    }

    public Partida(String clave,String host, ArrayList<Usuario> jugadores) {
        this.clave = clave;
        this.host=host;
        this.jugadores = jugadores;
    }
    
 
    
}
