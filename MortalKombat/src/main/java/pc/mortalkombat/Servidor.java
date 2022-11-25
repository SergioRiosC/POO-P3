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
        ManejadorArchivos MA = new ManejadorArchivos();
        ServerSocket servidor = null;
        Socket sc = null;
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;
        Partida p = new Partida();
        Usuario u=null;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("INICIADO");
            while (true) {

                sc = servidor.accept();
                //System.out.println("Bievenido a la partida + FULANITO");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                String username = in.readUTF();
                String password = in.readUTF();
                if (!revisa_usuario(username)) {
                    if (MA.crear_carpeta("archivos/" + username) == 0) {
                        MA.escribir("archivos/usuarios.txt", "#" + username + "-" + password);
                        u = new Usuario(username, password);
                        MA.guardarUsuario(u);
                        p.jugadores.add(u);
                    }

                } else {
                    u = MA.buscarusuario(username);
                    MA.guardarUsuario(u);
                    p.jugadores.add(u);
                }
                //Gerrero #1
                out.writeUTF("Elije el nombre de tu guerrero #1");
                String nombre=in.readUTF();
                out.writeUTF("Elije el tipo de tu guerrero #1");
                String tipo=in.readUTF();
                u.generarGuerreros(nombre, 0,tipo);
                
                //Gerrero #2
                out.writeUTF("Elije el nombre de tu guerrero #2");
                String nombre2=in.readUTF();
                out.writeUTF("Elije el tipo de tu guerrero #2");
                String tipo2=in.readUTF();
                u.generarGuerreros(nombre2, 1,tipo2);
                
                //Gerrero #3
                out.writeUTF("Elije el nombre de tu guerrero #3");
                String nombre3=in.readUTF();
                out.writeUTF("Elije el tipo de tu guerrero #3");
                String tipo3=in.readUTF();
                u.generarGuerreros(nombre3, 2,tipo3);
                
                //Gerrero #4
                out.writeUTF("Elije el nombre de tu guerrero #4");
                String nombre4=in.readUTF();
                out.writeUTF("Elije el tipo de tu guerrero #4");
                String tipo4=in.readUTF();
                u.generarGuerreros(nombre4, 3,tipo4);
                
                
                
                
                

                /*for (Usuario jugador : p.jugadores) {
                    if (jugador.getUsername() == username) {
                        jugador.generarGuerreros();
                        break;
                    }
                }*/

                out.writeUTF("Bienvenido a la partida " + username);

                ServidorHilo hilo = new ServidorHilo(in, out, p.jugadores,u);

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

    private static Boolean revisa_usuario(String username) {
        ManejadorArchivos MA = new ManejadorArchivos();
        String todos = MA.leer("archivos/usuarios.txt");
        String[] usuarios = todos.split("#");
        String[] datos;

        for (String usuario : usuarios) {
            if (!usuario.equals("")) {
                datos = usuario.split("-");
                if (datos[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

}
