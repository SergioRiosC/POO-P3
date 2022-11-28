/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author Sergio RC
 */
public class Cliente {

    String HOST;
    int PUERTO=8080;
    String clavePartida;
    DataInputStream in;
    DataOutputStream out;
    Scanner scanner = new Scanner(System.in);

    public Cliente(String HOST, String clave, String username) {
        this.HOST = HOST;
        this.clavePartida=clave;
        scanner.useDelimiter("\n");
        
        try {
            Socket sc = new Socket(HOST, 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            /*System.out.println("Ingresa tu USER: ");
            String username = scanner.next();
            
            System.out.println("Ingresa tu PASSWORD: ");
            String password = scanner.next();*/
            
            /*
            out.writeUTF(username);
            out.writeUTF(password);
            //Gerrero #1
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            //Gerrero #2
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            //Gerrero #3
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            //Gerrero #4
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            */
            out.writeUTF(username);
            System.out.println(in.readUTF());
            ClienteHilo hilo = new ClienteHilo(in, out);
            hilo.start();
            hilo.join();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

/*
    public static void main(String[] args) {

        
        
        scanner.useDelimiter("\n");

        try {
            Socket sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            System.out.println("Ingresa tu USER: ");
            String username = scanner.next();
            
            System.out.println("Ingresa tu PASSWORD: ");
            String password = scanner.next();
            
            
            out.writeUTF(username);
            out.writeUTF(password);
            //Gerrero #1
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            //Gerrero #2
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            //Gerrero #3
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            //Gerrero #4
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            System.out.println(in.readUTF());
            out.writeUTF(scanner.next());
            
            
            ClienteHilo hilo = new ClienteHilo(in, out);
            hilo.start();
            hilo.join();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    

    public void enviarAServidor(String s) throws IOException {
        out.writeUTF(s);
    }
}
