/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.Serializable;

/**
 *
 * @author Sergio RC
 */
public class Arma implements Serializable{
    String tipo;
    int ataque;
    
    
    String nombre;
    boolean usada=false;
    public Arma(String tipo, int ataque) {
        this.tipo = tipo;
        this.ataque = ataque;
    }
    
    
}
