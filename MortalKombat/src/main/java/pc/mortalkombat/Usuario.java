package pc.mortalkombat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.Serializable;

/**
 *
 * @author Sergio RC
 */
public class Usuario implements Serializable{
    private String username;
    private String nombre;
    private String password;
    private Guerrero[] guerreros=new Guerrero[4];
    private int victorias=0;
    private int derrotas=0;
    private int ataques=0;
    private int ataques_Efectivos=0;
    private int ataques_Fallidos=0;
    private int rendiciones=0;

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
        
    }

    public String getUsername() {
        return username;
    }

   public void generarGuerreros(String name, int i,String tipo){
       /*for (int i=0;i<guerreros.length;i++) {
            guerreros[i]=new Guerrero();
        }*/
       guerreros[i]=new Guerrero(name,tipo);
   }

    public Guerrero[] getGuerreros() {
        return guerreros;
    }
   
    
}
