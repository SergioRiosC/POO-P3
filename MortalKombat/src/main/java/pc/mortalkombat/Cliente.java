/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author Sergio RC
 */
public class Cliente implements Runnable{
    private Socket socket;
    private ObjetoEnvio mensaje;
    private String username;
    private int port;
    private int puertoServidor = 10201;

    public Cliente(String username, int port) {
        this.username = username;
        this.port = port;
    }

    public void comenzar(){
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public void enviarMensaje(String mensaje) throws IOException {
        try{
            Socket envio = new Socket("127.0.0.1", puertoServidor);
            ObjetoEnvio objeto = new ObjetoEnvio();
            objeto.setMensaje(mensaje);
            objeto.setNombre(username);
            objeto.setPuerto(String.valueOf(port));
            ObjectOutputStream salida = new ObjectOutputStream(envio.getOutputStream());
            salida.writeObject(objeto);
            System.out.println("ENVIAAAADO  ");
            salida.close();
            envio.close();
        }
        catch(IOException e){
            System.out.println("Error al enviar mensaje");
        }
        
        
    }

    @Override
    public void run() {
        try{
            ServerSocket llegadaDeMensaje = new ServerSocket(port);
            ObjetoEnvio recibo;
            while(true){
                Socket socket = llegadaDeMensaje.accept();
                ObjectInputStream entradaDatos = new ObjectInputStream(socket.getInputStream());
                mensaje = (ObjetoEnvio) entradaDatos.readObject();
                System.out.println(mensaje.getMensaje());
                socket.close();
            }
        }
        catch(Exception e){
            System.out.println("Error al iniciar el cliente");
        }
    }
}
