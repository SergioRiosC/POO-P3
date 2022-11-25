/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio RC
 */
public class Servidor {
     
    
    
    public static void main(String[] args) {
        ServerSocket servidor =null;
        Socket sc=null;
        final int PUERTO =5000;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            servidor =new ServerSocket(PUERTO);
            System.out.println("INICIADO");
            while (true){
                
                sc=servidor.accept();
                //System.out.println("Bievenido a la partida + FULANITO");
                
                in= new DataInputStream(sc.getInputStream());
                out=new DataOutputStream(sc.getOutputStream());
                
                out.writeUTF("Bienvenido a la partida "+in.readUTF());
                ServidorHilo hilo=new ServidorHilo(in, out);
                
                hilo.start();
                System.out.println("CONEXION CLIENTE");
                /*
                String mensaje =in.readUTF();
                
                System.out.println(mensaje);
                if(mensaje==""||mensaje==null){
                    break;
                }else{
                    Guerrero g=new Guerrero();
                }*/
                //out.writeUTF("SERVER: ACCEPT");
                
                //sc.close();
                //System.out.println("CLIENTE DESCONECTADO");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
