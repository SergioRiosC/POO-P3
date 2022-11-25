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
    String tipo;
    Arma[] armas=new Arma[5];
    String[] nombres=new String[5];
    String nombre;
    int vida =100;
    boolean usada=false;
    public Guerrero(String name, String tipo) {
        this.nombre=name;
        this.tipo=tipo;
        /*tipo[0]="FUEGO";
        tipo[1]="AIRE";
        tipo[2]="AGUA";
        tipo[3]="MAGIA_BLANCA";
        tipo[4]="MAGIA_NEGRA";
        tipo[5]="ELECTRICIDAD";
        tipo[6]="HIELO";
        tipo[7]="ACIDO";
        tipo[8]="ESPIRITUALIDAD";
        tipo[9]="HIERRO";*/
        nombres[0]="Cuchillo";
        nombres[1]="Pistola";
        nombres[2]="Cañon";
        nombres[3]="Lapiz";
        nombres[4]="Manos";
        int c=0;
        for (int o=0;o<armas.length;o++) {
            armas[o]=new Arma();
            armas[o].nombre=nombres[o];
            for (int i = 0; i < 10; i++) {
                //System.out.println(c+": ARAMA: "+armas[o].nombre+" TIPO: "+tipo[i]+" DAÑO: "+armas[o].ataque[i]);
            }
            //System.out.println("--------------------------");
            c++;
        }
     
    
    }
    
}
