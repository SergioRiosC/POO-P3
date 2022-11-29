/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pc.mortalkombat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author sebastianqr.2208
 */
public class Servidor extends javax.swing.JFrame implements Runnable {
    ArrayList<ArrayList<String>> usuarios = new ArrayList<>(); //matriz de usuarios
    ArrayList<Usuario> jugadoresFinales = new ArrayList<>(); //jugadores que se van a jugar
    private int puerto = 10201;
    private String password = "";
    private int hostPort = 0;
    private String host = "";

    ManejadorArchivos manejador = new ManejadorArchivos();

    int turno = 0;
    Usuario turnoActual;

    boolean iniciada = false;

    Partida partida;
    /**
     * Creates new form Servidor
     */
    public Servidor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        Thread entradaDatos = new Thread(this);
        entradaDatos.start();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reset(){
        usuarios = new ArrayList<>();
        jugadoresFinales = new ArrayList<>();
        puerto = 10201;
        password = "";
        hostPort = 0;
        host = "";
        turno = 0;
        iniciada = false;
        partida = null;
        turnoActual = null;
    }

    private void enviarMensaje(ObjetoEnvio mensaje, int puerto) throws IOException {
        String info=manejador.leer("archivos/"+getUser(puerto)+"/logs.txt");

        manejador.escribir("archivos/"+getUser(puerto)+"/logs.txt", info+" | RESPUESTA => "+ mensaje.getMensaje().replace("-", " ")+"#");
        try {
            Socket envioDeMensaje = new Socket("127.0.0.1", puerto);
            ObjectOutputStream reenvio = new ObjectOutputStream(envioDeMensaje.getOutputStream());
            reenvio.writeObject(mensaje);
            System.out.println("---Enviado---");
            reenvio.close();
            envioDeMensaje.close();
        } catch (IOException e) {
            System.out.println("Error al enviar mensaje");
        }
    }

    private String getUser(int port){
        for (Usuario jugador : jugadoresFinales) {
            if(jugador.port==port){
                return jugador.getUsername();
            }
        }
        return null;
    }

    private ArrayList<String> buscarUsuario(String nombre){
        for (ArrayList<String> usuario : usuarios) {
            if(usuario.get(0).equals(nombre)){
                return usuario;
            }
        }
        return null;
    }

    private void enviarATodos(ObjetoEnvio mensaje, String enviador) throws IOException{
        try{
            for (ArrayList<String> usuario : usuarios) {
                if(!usuario.get(0).equals(enviador)){
                    enviarMensaje(mensaje, Integer.parseInt(usuario.get(1)));
                }
            }
        }
        catch(IOException e){
            System.out.println("Error al enviar mensaje");
        }
    }

    private void enviarConectados(String miIp){
        for(ArrayList<String> i : usuarios){
            if(!i.get(1).equals(miIp)){
                try {
                    ObjetoEnvio mensaje = new ObjetoEnvio();
                    mensaje.setMensaje("conecta-" + i.get(0));
                    enviarMensaje(mensaje, Integer.parseInt(miIp));

                }
                catch (IOException ex) {
                    System.out.println("Error al enviar mensaje");
                }
            }
        }
    }

    private void sacarDeLista(String user){
        for (ArrayList<String> usuario : usuarios) {
            if(usuario.get(0).equals(user)){
                usuarios.remove(usuario);
                break;
            }
        }
        jugadoresFinales.clear();
        for(ArrayList<String> i: usuarios){
            Usuario u = new Usuario(i.get(0), Integer.parseInt(i.get(1)));
            jugadoresFinales.add(u);
        }
        turnoActual = jugadoresFinales.get(turno);
    }

    private int puertoDe(String name){
        for(ArrayList<String> i: usuarios){
            if(i.get(0).equals(name)){
                return Integer.parseInt(i.get(1));
            }
        }
        return 0;
    }

    private Guerrero getPersonaje(String usuario, String nombrePersonaje){
        ArrayList<Guerrero> aux = null;
        for(Usuario i: jugadoresFinales){
            if(i.getUsername().equals(usuario)){
                aux = i.guerreros;
                break;
            }
        }
        for(Guerrero j: aux){
            if(j.nombre.equals(nombrePersonaje)){
                return j;
            }
        }
        return null;
    }

    private Guerrero getSeleccionado(String usuario){
        for(Usuario i: jugadoresFinales){
            if(i.getUsername().equals(usuario)){
                return i.seleccionado;
            }
        }
        return null;
    }

    private Usuario getUsuario(String usuario){
        for(Usuario i: jugadoresFinales){
            if(i.getUsername().equals(usuario)){
                return i;
            }
        }
        return null;
    }

    private void siguienteTurno() throws IOException {
        turno++;
        if(turno == jugadoresFinales.size()){
            turno = 0;
        }
        turnoActual = jugadoresFinales.get(turno);
        ObjetoEnvio envio = new ObjetoEnvio();
        envio.setMensaje("turno-Turno Actual: " + turnoActual.getUsername());
        enviarATodos(envio, "-1");
    }

    private void recargarArmas(Usuario u){
        boolean recargar = false;
        for(Guerrero g: u.guerreros){
            for(Arma a: g.armas){
                if(a.usada){
                    recargar = true;
                    break;
                }
            }
        }

        if(!recargar){
            for(Guerrero x: u.guerreros){
                for(Arma i: x.armas){
                    if(i.usada){
                        i.usada = false;
                    }
                }
            }
        }
    }

    private void reconocerMensaje(ObjetoEnvio entrada) throws IOException {
        // logs por aca:


        //////////
        String mensaje = entrada.getMensaje();
        String[] partes = mensaje.split("-");
        /*
        Tipos de mensaje:
        -nuevo -nombre -port -password
        -atacar -nombreAtacado -guerrero -arma -jugadorQueEnvia
        -select -personaje -quienEnvia
        -rendirse -jugadorQueEnvia
        -chat -mensaje -jugadorQueEnvia
        -chatprivado -mensaje -jugadorQueRecibe -jugadorQueEnvia
        -pasarTurno -jugadorQueEnvia
        -comodin -jugadorQueEnvia
        -conectar -nombre -port -password
         */

        ObjetoEnvio envio = new ObjetoEnvio();

        for(String i: partes){
            System.out.println(i);
        }

        if(partes[0].equals("nuevo")){
            System.out.println("nuevo si");
        }

        System.out.println("partes[0] = " + partes[0]);

        switch(partes[0]){
            case "nuevo":
                ArrayList<String> usuario = new ArrayList<>();
                usuario.add(partes[1]);
                usuario.add(partes[2]);
                usuarios.add(usuario);
                host = partes[1];
                password = partes[3];
                hostPort = Integer.parseInt(partes[2]);
                System.out.println("Nuevo host port: " + hostPort);
                System.out.println("Nuevo password: " + password);
                break;

            case "atacar":
                String usuarioQueAtaca = partes[4];
                if(turnoActual.getUsername().equals(usuarioQueAtaca)){
                    String nombreAtacado = partes[1];
                    String nombrePersonaje2 = partes[2];
                    String arma = partes[3];
                    Guerrero guerreroAtacante = getPersonaje(usuarioQueAtaca, nombrePersonaje2);
                    Arma armaAtacante = guerreroAtacante.getArma(arma);

                    if(guerreroAtacante == null || armaAtacante == null){
                        envio.setMensaje("error-Error al atacar (no se encontró el guerrero o el arma)");
                        enviarMensaje(envio, puertoDe(usuarioQueAtaca));
                        break;
                    }
                    else{
                        if(guerreroAtacante == null || armaAtacante == null){
                            envio.setMensaje("error-Error al atacar (Guerrero o Arma no reconocidos)");
                            enviarMensaje(envio, puertoDe(usuarioQueAtaca));
                            break;
                        }
                        else{

                        }

                        if(!armaAtacante.usada){
                            Guerrero guerreroAtacado = getSeleccionado(nombreAtacado);
                            guerreroAtacado.restarVida(armaAtacante.ataque);

                            getUsuario(nombreAtacado).actualizarGuerrero(guerreroAtacado);
                            armaAtacante.setUsada();
                            getUsuario(usuarioQueAtaca).modificarArma(armaAtacante, guerreroAtacante.nombre);

                            envio.setMensaje("ataqueRecibido-El jugador " + usuarioQueAtaca + " te ha atacado con " + armaAtacante.tipo + " y te ha dejado con " + guerreroAtacado.vida + " de vida");
                            enviarMensaje(envio, puertoDe(nombreAtacado));
                            envio = new ObjetoEnvio();
                            envio.setMensaje("ataqueRealizado-Has atacado a " + nombreAtacado + " con " + armaAtacante.tipo + " y le has dejado con " + guerreroAtacado.vida + " de vida");
                            enviarMensaje(envio, puertoDe(usuarioQueAtaca));

                            envio = new ObjetoEnvio();
                            envio.setMensaje("guerreros-");
                            envio.setGuerreros(getUsuario(nombreAtacado).guerreros);
                            enviarMensaje(envio, puertoDe(nombreAtacado));

                            envio = new ObjetoEnvio();
                            envio.setMensaje("guerreros-");
                            envio.setGuerreros(getUsuario(usuarioQueAtaca).guerreros);
                            enviarMensaje(envio, puertoDe(usuarioQueAtaca));

                            if(guerreroAtacado.vida <= 0){
                                envio = new ObjetoEnvio();
                                envio.setMensaje("anuncio-$ " + guerreroAtacado.nombre +  " de " + nombreAtacado + " ha muerto en manos de " + usuarioQueAtaca + "!");
                                enviarATodos(envio, "-1");

                                envio = new ObjetoEnvio();
                                envio.setMensaje("muerte-Tu guerrero " + guerreroAtacado.nombre + " ha muerto!");
                                enviarMensaje(envio, puertoDe(nombreAtacado));

                                getUsuario(nombreAtacado).eliminarGuerrero();
                                envio = new ObjetoEnvio();
                                envio.setMensaje("guerreros-");
                                envio.setGuerreros(getUsuario(nombreAtacado).guerreros);
                                enviarMensaje(envio, puertoDe(nombreAtacado));

                                try{
                                    envio = new ObjetoEnvio();
                                    getUsuario(nombreAtacado).setSeleccionado(getUsuario(nombreAtacado).guerreros.get(0));
                                    envio.setMensaje("seleccionarJugador-$ Se ha seleccionado a " + getUsuario(nombreAtacado).seleccionado.nombre + "!");
                                    enviarMensaje(envio, puertoDe(nombreAtacado));
                                }
                                catch(Exception e){
                                }

                                if(getUsuario(nombreAtacado).guerreros.size() == 0){
                                    envio = new ObjetoEnvio();
                                    envio.setMensaje("anuncio-$ " + nombreAtacado + " ha sido derrotado!");
                                    enviarATodos(envio, "-1");
                                    envio = new ObjetoEnvio();
                                    envio.setMensaje("derrota-Has sido derrotado!");
                                    enviarMensaje(envio, puertoDe(nombreAtacado));
                                    sacarDeLista(nombreAtacado);
                                }

                                envio = new ObjetoEnvio();
                                envio.setMensaje("guerreros-");
                                envio.setGuerreros(getUsuario(usuarioQueAtaca).guerreros);
                                enviarMensaje(envio, puertoDe(usuarioQueAtaca));

                                System.out.println("cANT USUARIOS: " + jugadoresFinales.size());

                                recargarArmas(getUsuario(usuarioQueAtaca)); //recarga de armas

                                if(jugadoresFinales.size() == 1){
                                    envio = new ObjetoEnvio();
                                    envio.setMensaje("ganador-El jugador " + jugadoresFinales.get(0).getUsername() + " ha ganado");
                                    enviarATodos(envio, "-1");

                                    reset();
                                }
                                else{
                                    siguienteTurno();
                                }
                            }
                            else{
                                siguienteTurno();
                            }
                        }
                        else{
                            envio.setMensaje("error-Esta arma ya ha sido usada, usa otra!");

                            enviarMensaje(envio, puertoDe(usuarioQueAtaca));
                        }
                    }

                }
                else{
                    envio.setMensaje("error-No es tu turno!");
                    enviarMensaje(envio, puertoDe(usuarioQueAtaca));
                }
                break;

            case "select":
                String nombrePersonaje = partes[1];
                String nombreJugador = partes[2];

                for(Usuario i: jugadoresFinales){
                    if(i.getUsername().equals(nombreJugador)){
                        i.setSeleccionado(getPersonaje(nombreJugador, nombrePersonaje));
                        break;
                    }
                }
                envio.setMensaje("seleccionarJugador-$ Se ha seleccionado a " + nombrePersonaje + "!" );
                enviarMensaje(envio, puertoDe(nombreJugador));

                break;
            case "rendirse":
                envio.setMensaje("rendirse-$ El jugador " + partes[1] + " se ha rendido! :/");
                enviarATodos(envio, partes[1]);
                envio = new ObjetoEnvio();
                envio.setMensaje("chao-Te has rendido, perdiste! :/");
                enviarMensaje(envio, puertoDe(partes[1]));
                sacarDeLista(partes[1]);

                if(jugadoresFinales.size() == 1){
                    envio = new ObjetoEnvio();
                    envio.setMensaje("ganador-El jugador " + jugadoresFinales.get(0).getUsername() + " ha ganado");
                    enviarATodos(envio, "-1");

                    reset();
                }
                else{
                    siguienteTurno();
                }
                break;

            case "chat":
                envio.setMensaje("chatprivado-" + partes[1] + "..... Enviado (publicamente) por: " + partes[2]);
                for(ArrayList<String> i: usuarios){
                    if(!i.get(0).equals(partes[2])){
                        try {
                            enviarMensaje(envio, Integer.parseInt(i.get(1)));
                        } catch (IOException e) {
                            System.out.println("Error al enviar mensaje");
                        }
                    }
                }
                break;

            case "chatprivado":
                ArrayList<String> jugadorAEnviar = buscarUsuario(partes[2]);
                ArrayList<String> nuevo =  new ArrayList<>();
                if(jugadorAEnviar != null){
                    try {
                        envio.setMensaje("chatprivado-" + partes[1] + "..... Enviado por: " + partes[3]);
                        enviarMensaje(envio, Integer.parseInt(jugadorAEnviar.get(1)));
                        nuevo.add(partes[1]);
                        nuevo.add(partes[2]);
                    } catch (IOException e) {
                        System.out.println("Error al enviar mensaje");
                    }
                }
                break;

            case "pasarTurno":
                if(partes[1].equals(turnoActual.getUsername())){
                    turno++;
                    if(turno == jugadoresFinales.size()){
                        turno = 0;
                    }
                    turnoActual = jugadoresFinales.get(turno);
                    envio.setMensaje("pasarTurno-$ El jugador " + partes[1] + " ha pasado el turno!");
                    enviarATodos(envio, partes[1]);
                }
                else{
                    envio.setMensaje("error-$ No es tu turno!");
                    enviarMensaje(envio, puertoDe(partes[1]));
                }
                envio = new ObjetoEnvio();
                envio.setMensaje("turno-Turno Actual: " + turnoActual.getUsername());
                enviarATodos(envio, "-1");
                break;
            case "comodin":
                break;
            case "conectar":
                if(password.equals(partes[3])){
                    if(!iniciada){
                        envio.setMensaje("conecta-" + partes[1]);
                        ArrayList<String> nuevoUsuario = new ArrayList<>();
                        nuevoUsuario.add(partes[1]);
                        nuevoUsuario.add(partes[2]);
                        usuarios.add(nuevoUsuario);
                        enviarATodos(envio, partes[1]);
                        System.out.println("JUGADOR CONECTADO");
                        enviarConectados(String.valueOf(entrada.getMiPuerto()));
                    }
                    else{
                        envio.setMensaje("alerta-El juego ya ha iniciado");
                        enviarMensaje(envio, Integer.parseInt(partes[2]));
                    }

                }
                break;
            case "comenzar":
                enviarATodos(entrada, String.valueOf(hostPort));
                enviarMensaje(entrada, hostPort);
                for(ArrayList<String> i: usuarios){
                    Usuario u = new Usuario(i.get(0), Integer.parseInt(i.get(1)));
                    String[] gs = manejador.archivosEnDirectorio("archivos/" + u.getUsername() + "/guerreros");
                    for(String j: gs){
                        u.addGuerrero(manejador.buscarGuerrero(u.getUsername(), j));
                    }
                    u.selectGuerrero(0);
                    jugadoresFinales.add(u);
                }
                turnoActual = jugadoresFinales.get(turno);
                iniciada = true;
                for(Usuario u: jugadoresFinales){
                    envio = new ObjetoEnvio();
                    envio.setMensaje("seleccionarJugador-$ Guerrero seleccionado: " + u.seleccionado.nombre);
                    enviarMensaje(envio, u.port);
                }
                envio = new ObjetoEnvio();
                envio.setMensaje("turno-Turno Actual: " + turnoActual.getUsername());
                enviarATodos(envio, "-1");
                break;

            case "logs": //logs -jugadorQueEnvia
                System.out.println("LOGS: ENVIA: "+partes[1]);
                envio.setMensaje("logs-" + manejador.leer("archivos/" + partes[1] + "/logs.txt"));
                enviarMensaje(envio, puertoDe(partes[1]));

            default:
                System.out.println("Se Marmol");
        }
    }

    @Override
    public void run() {

        while(true){
            ServerSocket llegadaMensaje = null;
            try{
                llegadaMensaje = new ServerSocket(puerto);
                ObjetoEnvio entrada;
                String usuario, ip, mensajeCompleto;
                Socket llegadaServidor = llegadaMensaje.accept();
                ObjectInputStream llegada = new ObjectInputStream(llegadaServidor.getInputStream());

                entrada = (ObjetoEnvio) llegada.readObject();
                System.out.println("LLLEEEGGGGAA");
                mensajeCompleto = entrada.getMensaje();
                System.out.println(mensajeCompleto);

                llegadaServidor.close();
                llegada.close();
                llegadaMensaje.close();
                reconocerMensaje(entrada);

            }
            catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}