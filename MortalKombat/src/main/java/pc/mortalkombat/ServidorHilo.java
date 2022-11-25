/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio RC
 */
public class ServidorHilo extends Thread{
    
    DataInputStream in;
    DataOutputStream out;

    public ServidorHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        
        try {
            
            while(true){
                System.out.println("ESCUCHANDO...");
            String[] mensajeCliente=in.readUTF().split("#");
            System.out.println("IN: "+mensajeCliente);
            out.writeUTF(mensajeCliente[1]);
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
