package pc.mortalkombat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Sergio RC
 */
public class Usuario implements Serializable{
    private String username;
    private String nombre;
    private String password;
    ArrayList<Guerrero> guerreros = new ArrayList<>();

    public void setSeleccionado(Guerrero seleccionado) {
        this.seleccionado = seleccionado;
    }

    Guerrero seleccionado;
    private int victorias=0;
    private int derrotas=0;
    private int ataques=0;
    private int ataques_Efectivos=0;
    private int ataques_Fallidos=0;
    private int rendiciones=0;
    public int port;

    public Usuario(String username, int puerto){
        this.username=username;
        this.port=puerto;
    }

    public Usuario(String username, String password, int puerto) {
        this.username = username;
        this.password = password;
        this.port=puerto;
        
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword(){
        return password;
    }

    public void addGuerrero(Guerrero g){
        guerreros.add(g);
    }

    public void selectGuerrero(int pos){
        seleccionado = guerreros.get(pos);
    }

    public String guerrosToString(){
        String tmp = "";
        for(Guerrero i: guerreros){
            tmp += i.nombre + "- vida: " + i.vida + "%\n";
            tmp += "Armas: \n" + i.getArmas() + "\n";
        }
        return tmp;
    }

    public ArrayList<Guerrero> getGuerreros() {
        return guerreros;
    }
   
    
}
