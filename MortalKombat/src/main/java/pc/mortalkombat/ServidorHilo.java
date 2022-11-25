/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pc.mortalkombat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    Usuario usuarioActual;

    public ServidorHilo(DataInputStream in, DataOutputStream out, ArrayList<Usuario> jugadores, Usuario u) {
        this.in = in;
        this.out = out;
        this.jugadores = jugadores;
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

                switch (mensajeCliente[1].toLowerCase()) {
                    case "atacar":
                        Usuario jugadorAAtacar = null;
                        String[] tipoArma = null;
                        int[] dañoArma = null;

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
                                        respuesta+= "Ataque realizado: "+guerrero.nombre+" -> "+guerrero.vida+"\n";
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
        }
    }

}
