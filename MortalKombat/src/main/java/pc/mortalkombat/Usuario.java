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
public class Usuario{
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

    public Usuario(String username, String nombre, String password) {
        this.username = username;
        this.nombre = nombre;
        this.password = password;
    }

   
    
}
