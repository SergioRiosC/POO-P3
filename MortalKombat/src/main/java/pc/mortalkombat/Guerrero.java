/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

/**
 *
 * @author Sergio RC
 */
public class Guerrero {
    String[] tipo=new String[10];
    Arma[] armas=new Arma[5];
    String nombre;
    boolean usada=false;
    public Guerrero() {
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
        int c=0;
        for (int o=0;o<armas.length;o++) {
            armas[o]=new Arma();
            for (int i = 0; i < 10; i++) {
                System.out.println(c+": ARAMA: "+tipo[i]+" DAÑO: "+armas[o].ataque[i]);
            }
            System.out.println("--------------------------");
            c++;
        }
     
    
    }
    
}
