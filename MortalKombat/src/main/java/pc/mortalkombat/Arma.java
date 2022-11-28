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
    String[] tipo=new String[10];
    int[] ataque=new int[10];
    
    
    String nombre;
    boolean usada=false;
    public Arma() {
        tipo[0]="FUEGO";
        tipo[1]="AIRE";
        tipo[2]="AGUA";
        tipo[3]="MAGIA_BLANCA";
        tipo[4]="MAGIA_NEGRA";
        tipo[5]="ELECTRICIDAD";
        tipo[6]="HIELO";
        tipo[7]="ACIDO";
        tipo[8]="ESPIRITUALIDAD";
        tipo[9]="HIERRO";
        for (int i=0;i<ataque.length;i++) {
            ataque[i]=(int) Math.floor(Math.random()*(100-20+1)+20);
        }
     
    
    }
    
    
}
