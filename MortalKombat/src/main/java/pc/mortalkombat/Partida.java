/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sergio RC
 */
public class Partida {

    ArrayList<Usuario> jugadores = new ArrayList<Usuario>();
    
    
    public static void main(String[] args) {
        ManejadorArchivos MA=new ManejadorArchivos();
        Scanner scanner =new Scanner(System.in);
        scanner.useDelimiter("\n");
        System.out.println("Ingresa tu USER: ");
        String username=scanner.next();
        System.out.println("Ingresa tu PASSWORD: ");
        String password=scanner.next();
        
        if(!revisa_usuario(username)){
            MA.escribir("archivos/usuarios.txt", "#" + username + "-" + password);
        }
        
        
        
        Cliente c = new Cliente(username);
        /*while (true) {
            //c.enviarAServidor("S");
            //c.enviarAServidor("");
        }*/

    }
    
    private static Boolean revisa_usuario(String username) {
        ManejadorArchivos MA=new ManejadorArchivos();
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
