/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    public ClienteHilo(DataInputStream in, DataOutputStream out,String username) {
        this.in = in;
        this.out = out;
        this.username=username;
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
                out.writeUTF(username+"#"+scanner.next());
                
                
                //respuesta del Servidor
                System.out.println(in.readUTF());
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
