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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio RC
 */
public class ServidorHilo extends Thread {

    DataInputStream in;

    DataOutputStream out;
    ArrayList<Usuario> jugadores;
    Partida partidas;
    Usuario usuarioActual;

    public ServidorHilo(DataInputStream in, DataOutputStream out, Usuario u, ArrayList<Usuario> jugadores ) {
        this.in = in;
        this.out = out;
        this.usuarioActual = u;
        this.jugadores = jugadores;

    }

    @Override
    public void run() {

        try {
            while (true) {

                System.out.println("ESCUCHANDO...");
                String[] mensajeCliente = in.readUTF().split("-");
                System.out.println("IN: " + mensajeCliente[0] + ": " + mensajeCliente[1]);
                String respuesta = "";

                switch (mensajeCliente[1]) {
                    case "jugadores":
                        ObjectInputStream in2 = new ObjectInputStream(in);
                        Usuario u =(Usuario) in2.readObject();
                        System.out.println("RECIBIDO: "+u.getUsername());
                        System.out.println("SIZE ANTES: "+jugadores.size());
                        jugadores.add(u);
                        System.out.println("SIZE DESPUES: "+jugadores.size());
                        for (int i = 0; i < jugadores.size(); i++) {
                            System.out.println("JUGADOR FOR: " + jugadores.get(i).getUsername());
                        }
                        break;

                    case "partida":
                        ObjectInputStream in3 = new ObjectInputStream(in);
                        Partida nP = (Partida) in3.readObject();
                        

                        break;

                    case "getPartida":
                        String[] info = in.readUTF().split("-");
                        
                        String host = info[1];
                        String pass = info[2];
                        

                        /*
                        for (Partida partida : partidas) {
                            
                            System.out.println("PARTIDA:  "+partida.host+"  "+"  "+partida.clave);
                            
                            if (partida.clave.equals(pass) && partida.host.equals(host)) {
                                ObjectOutputStream out2 = new ObjectOutputStream(out);
                                out2.writeObject(partida);
                                System.out.println("OOOOUTTTT");
                                for (Usuario user : partida.jugadores) {
                                    System.out.println("JUGADOR EN PARTIDA: " + user.getUsername());
                                }
                                break;
                            }
                        }*/
                        break;

                    case "atacar":
                        Usuario jugadorAAtacar = null;
                        String[] tipoArma = null;
                        int[] dañoArma = null;
                        System.out.println("ATACA");
                        for (Usuario jugador : jugadores) {
                            System.out.println("USUARIO: " + jugador.getUsername());
                            System.out.println("MENSAJE[2] " + mensajeCliente[2]);
                            if (jugador.getUsername().equals(mensajeCliente[2])) {
                                jugadorAAtacar = jugador;
                                break;
                            }
                        }
                        if (jugadorAAtacar != null) {
                            for (Guerrero guerrero : usuarioActual.getGuerreros()) {
                                if (guerrero.nombre.equals(mensajeCliente[3])) {
                                    for (Arma arma : guerrero.armas) {
                                        if (arma.nombre.equals(mensajeCliente[4])) {
                                            tipoArma = arma.tipo;
                                            dañoArma = arma.ataque;
                                            break;
                                        }
                                    }
                                    break;
                                }
                            }
                        }

                        if (tipoArma != null && dañoArma != null) {

                            for (Guerrero guerrero : jugadorAAtacar.getGuerreros()) {
                                int i = 0;
                                for (String tipo : tipoArma) {
                                    if (tipo.equals(guerrero.tipo)) {
                                        guerrero.vida = -dañoArma[i];
                                        respuesta += "Ataque realizado: " + guerrero.nombre + " -> " + guerrero.vida + "\n";
                                        break;
                                    }
                                    i++;
                                }
                            }
                        }

                        //respuesta = "Jugador no encontrado";
                        break;
                    case "rendirse":
                        respuesta = "rendido";
                        break;
                    case "salida mutua":
                        respuesta = "saliendo todos";
                        break;
                    case "recarga":
                        respuesta = "recargadas";
                        break;
                    case "comodin":
                        respuesta = "comodin";
                        break;
                    case "seleccionar":
                        respuesta = "seleccionado";
                        break;
                    case "pasar":
                        respuesta = "pasando turno";
                        break;
                    case "chat":
                        respuesta = "chat publico";
                        break;
                    case "chatprivado":
                        respuesta = "chat priv";
                        break;
                    default:
                        respuesta = "Comando desconocido";
                        break;

                }
                out.writeUTF(respuesta);
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
