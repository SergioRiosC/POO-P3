/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sergio RC
 */
public class Guerrero implements Serializable{
    Arma[] armas=new Arma[5];

    String nombre;
    int vida =100;
    boolean usada=false;

    public Guerrero(String name, ArrayList<Arma> armas) {
        this.nombre=name;

        for (int i=0;i<armas.size();i++) {
            this.armas[i]=armas.get(i);
        }
     
    
    }

    public Arma getArma(String tip){
        for(Arma i: armas){
            if(i.tipo.equals(tip)){
                return i;
            }
        }
        return null;
    }

    public String getArmas(){
        String tmp = "";

        for(Arma i: armas){
            tmp += i.tipo + "\t usada: " + i.usada + "\t ataque: " + i.ataque + "\n";
        }
        return tmp;
    }
    public void restarVida(int ataque){
        vida-=ataque;
        if(vida<0){
            vida=0;
        }
    }
    
}
