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
    ArrayList<Partida> partidas = new ArrayList<Partida>();
    Usuario usuarioActual;

    public ServidorHilo(DataInputStream in, DataOutputStream out, Usuario u) {
        this.in = in;
        this.out = out;
        this.usuarioActual = u;

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
                        jugadores = (ArrayList<Usuario>) in2.readObject();
                        for (int i = 0; i < jugadores.size(); i++) {
                            System.out.println("JUGADOR FOR: " + jugadores.get(i).getUsername());
                        }
                        break;

                    case "partida":
                        ObjectInputStream in3 = new ObjectInputStream(in);
                        Partida nP = (Partida) in3.readObject();
                        System.out.println("PARTIDAS SIZE ANTES: " + partidas.size());
                        this.partidas.add(nP);
                        System.out.println("PARTIDAS SIZE DESPUES: " + partidas.size());

                        break;

                    case "getPartida":
                        System.out.println("PARTIDAS SIZE1: " + this.partidas.size());
                        String[] info = in.readUTF().split("-");
                        System.out.println("READ: " + info[1] + " " + info[2]);
                        System.out.println("PARTIDAS SIZE2: " + this.partidas.size());
                        String host = info[1];
                        String pass = info[2];
                        System.out.println("PARTIDAS SIZE3: " + this.partidas.size());
                        for (int i = 0; i < this.partidas.size(); i++) {
                            System.out.println("PARTIDA:  " + this.partidas.get(i).host + "  " + "  " + this.partidas.get(i).clave);

                            if (this.partidas.get(i).clave.equals(pass) && this.partidas.get(i).host.equals(host)) {
                                ObjectOutputStream out2 = new ObjectOutputStream(out);
                                out2.writeObject(partidas.get(i));
                                System.out.println("OOOOUTTTT");
                                for (Usuario user : partidas.get(i).jugadores) {
                                    System.out.println("JUGADOR EN PARTIDA: " + user.getUsername());
                                }
                                break;
                            }
                        }

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
