/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio RC
 */
public class ClienteHilo extends Thread{
    
    DataInputStream in;
    DataOutputStream out;
    Scanner scanner =new Scanner(System.in);
    String username;
    Usuario u;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
        
        scanner.useDelimiter("\n");
    }

    @Override
    public void run() {
        try {
            //Recibe mensaje de bienvenida
            System.out.println(in.readUTF());
            while(true){
                //listo para mandar mensajes
                System.out.println("ESCUCHANDO....");
                //out.writeUTF(username+"-"+scanner.next());
                
                
                //respuesta del Servidor
                System.out.println(in.readUTF());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void enviarAServidor(String user ,String s){
        try {
            System.out.println("ENVIAR: "+user+"-"+s);
            out.writeUTF(user+"-"+s);
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void enviarJugadores(Usuario jugador){
        try {
            System.out.println("ENVIANDO JUG");
            ObjectOutputStream out2=new ObjectOutputStream(out);
            out2.writeObject(jugador);
            //out2.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarPartida(Partida p){
        try {
            System.out.println("ENVIANDO PAR");
            ObjectOutputStream out2=new ObjectOutputStream(out);
            out2.writeObject(p);
            //out2.close();
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Partida getPartida(String host,String pass, String user){ //host = ip; pass = password; user = usuario a conectar
        enviarAServidor(user, "getPartida");
        enviarAServidor(user, host+"-"+pass);
        Partida p=null;
        try {
            System.out.println("GET PAR");
            ObjectInputStream in2 = new ObjectInputStream(in);
            try {
                
                p=(Partida)in2.readObject();
                
                //out2.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
    
}
