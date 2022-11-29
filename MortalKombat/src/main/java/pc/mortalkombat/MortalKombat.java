/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pc.mortalkombat;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastianqr.2208
 */
public class MortalKombat extends javax.swing.JFrame implements Runnable{

    ManejadorArchivos manejador = new ManejadorArchivos();
    Usuario jugadorActual;
    Imagenes imagenes = new Imagenes();
    String claveCreacion = "";
    String ultimoComando = "";
    int contadorPartida;
    ArrayList<Usuario> jugadores = new ArrayList<>();
    //Cliente c;
    Partida p;

    private Socket socket;
    private ObjetoEnvio mensaje;
    private String username;
    private int port;
    private int puertoServidor = 10201;

    private void esconderTodos() {
        ip.setVisible(false);
        clave.setVisible(false);
        ip_text.setVisible(false);
        clave_text.setVisible(false);
        unirse_unirse.setVisible(false);

        clave_crear.setVisible(false);
        clave_crear_text.setVisible(false);
        crear_crear.setVisible(false);

        atras.setVisible(false);
    }

    private void mostrarUnirse() {
        ip.setVisible(true);
        clave.setVisible(true);
        ip_text.setVisible(true);
        clave_text.setVisible(true);
        unirse_unirse.setVisible(true);
        atras.setVisible(true);
    }

    private void esconderPrincipales() {
        crear_partida.setVisible(false);
        unirse.setVisible(false);
    }

    private void mostrarCrear() {
        clave_crear.setVisible(true);
        clave_crear_text.setVisible(true);
        atras.setVisible(true);
        crear_crear.setVisible(true);
    }

    private void mostrarPrincipales() {
        crear_partida.setVisible(true);
        unirse.setVisible(true);
    }

    private void limpiarTodos() {
        ip.setText("");
        clave.setText("");
        clave_crear.setText("");
    }

    private void logout() {
        jugadorActual = null;
        username_login.setText("");
        password_login.setText("");
        pantallas.setSelectedIndex(0);
    }

    private void agregarJugador(String name) {
        contadorPartida++;
        jugadores_conectados.append(contadorPartida + ".\t" + name + "\n");
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
            objeto.setMiPuerto(String.valueOf(jugadorActual.port));
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

    private void reconocerMensaje(String mensaje){
        System.out.println("INGRESAAAAANDO");
        String[] partes = mensaje.split("-");

        System.out.println("Partes: " + partes[0]);

        switch(partes[0]){
            case "conecta":
                agregarJugador(partes[1]);
                System.out.println("SE AGREGAAAAAA  A JUGAOR");
                break;

            case "comenzar":
                jugadores_partida.setText(jugadores_conectados.getText());
                pantallas.setSelectedIndex(5);
                break;
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
                recibo = (ObjetoEnvio) entradaDatos.readObject();
                System.out.println("RECIBIENDO MENSAJE: " + recibo.getMensaje());
                reconocerMensaje(recibo.getMensaje());
                socket.close();
            }
        }
        catch(Exception e){
            System.out.println("Error al iniciar el cliente");
        }
    }

    /**
     * Creates new form Main
     */
    public MortalKombat() {
        initComponents();
        jugar.setIcon(imagenes.jugar);
        configuracion.setIcon(imagenes.configuracion);
        esconderTodos();
        jugadores_conectados.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        unirse_partida2 = new javax.swing.JButton();
        jColorChooser1 = new javax.swing.JColorChooser();
        pantallas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        username_login = new javax.swing.JTextField();
        login_but = new javax.swing.JButton();
        password_login = new javax.swing.JPasswordField();
        registrarse = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        username_registrarse = new javax.swing.JTextField();
        password_registrarse = new javax.swing.JPasswordField();
        registrarse_but = new javax.swing.JButton();
        registrarse1 = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        configuracion = new javax.swing.JButton();
        configuracion_label = new javax.swing.JLabel();
        jugar = new javax.swing.JButton();
        jugar_label = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        atras = new javax.swing.JToggleButton();
        crear_partida = new javax.swing.JToggleButton();
        clave_crear = new javax.swing.JTextField();
        ip = new javax.swing.JTextField();
        unirse = new javax.swing.JToggleButton();
        clave_crear_text = new javax.swing.JLabel();
        ip_text = new javax.swing.JLabel();
        clave = new javax.swing.JTextField();
        clave_text = new javax.swing.JLabel();
        unirse_unirse = new javax.swing.JToggleButton();
        crear_crear = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jugadores_conectados = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        comenzar_partida = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        clave_espera = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        terminal = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jugadores_partida = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();

        unirse_partida2.setBackground(new java.awt.Color(204, 204, 204));
        unirse_partida2.setForeground(new java.awt.Color(255, 255, 255));
        unirse_partida2.setText("Unirse");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 50)); // NOI18N
        jLabel1.setText("Login");

        login_but.setText("Entrar");
        login_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_butActionPerformed(evt);
            }
        });

        registrarse.setText("Registrarse");
        registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(username_login, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(password_login, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(496, 496, 496)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(439, 439, 439)
                        .addComponent(login_but, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registrarse)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_login, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password_login, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addComponent(login_but, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registrarse)
                .addContainerGap())
        );

        pantallas.addTab("tab1", jPanel1);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 50)); // NOI18N
        jLabel2.setText("Registrarse");

        registrarse_but.setText("Registrarse");
        registrarse_but.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarse_butActionPerformed(evt);
            }
        });

        registrarse1.setText("Login");
        registrarse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarse1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(username_registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(password_registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1040, 1040, 1040)
                .addComponent(registrarse1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(428, 428, 428)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(registrarse_but, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(password_registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(username_registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addComponent(registrarse_but, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registrarse1)
                .addContainerGap())
        );

        pantallas.addTab("tab2", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Herculanum", 1, 70)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 255, 0));
        jLabel3.setText("Menu Principal");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 6, 664, -1));

        configuracion.setText("jButton1");
        configuracion.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                configuracionMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                configuracionMouseMoved(evt);
            }
        });
        configuracion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                configuracionFocusGained(evt);
            }
        });
        configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracionActionPerformed(evt);
            }
        });
        jPanel3.add(configuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 104, 340, 560));

        configuracion_label.setFont(new java.awt.Font("Herculanum", 0, 24)); // NOI18N
        configuracion_label.setForeground(new java.awt.Color(255, 255, 255));
        configuracion_label.setText("Configuracion");
        jPanel3.add(configuracion_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 687, -1, -1));

        jugar.setText("jButton1");
        jugar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jugarMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jugarMouseMoved(evt);
            }
        });
        jugar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jugarFocusGained(evt);
            }
        });
        jugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jugarActionPerformed(evt);
            }
        });
        jPanel3.add(jugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 121, 340, 560));

        jugar_label.setFont(new java.awt.Font("Herculanum", 0, 24)); // NOI18N
        jugar_label.setForeground(new java.awt.Color(255, 255, 255));
        jugar_label.setText("Jugar");
        jPanel3.add(jugar_label, new org.netbeans.lib.awtextra.AbsoluteConstraints(955, 685, -1, -1));

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, -1));

        pantallas.addTab("tab3", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Herculanum", 1, 70)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 0));
        jLabel4.setText("PARTIDA - MENU");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        atras.setBackground(new java.awt.Color(102, 102, 102));
        atras.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        atras.setForeground(new java.awt.Color(255, 255, 255));
        atras.setText("Atras");
        atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasActionPerformed(evt);
            }
        });
        jPanel4.add(atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 140, 30));

        crear_partida.setBackground(new java.awt.Color(102, 102, 102));
        crear_partida.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        crear_partida.setForeground(new java.awt.Color(255, 255, 255));
        crear_partida.setText("Crear Partida");
        crear_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_partidaActionPerformed(evt);
            }
        });
        jPanel4.add(crear_partida, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 140, 30));
        jPanel4.add(clave_crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 410, 50));
        jPanel4.add(ip, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 410, 50));

        unirse.setBackground(new java.awt.Color(102, 102, 102));
        unirse.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        unirse.setForeground(new java.awt.Color(255, 255, 255));
        unirse.setText("Unirse");
        unirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unirseActionPerformed(evt);
            }
        });
        jPanel4.add(unirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 140, 30));

        clave_crear_text.setFont(new java.awt.Font("Herculanum", 0, 36)); // NOI18N
        clave_crear_text.setForeground(new java.awt.Color(255, 255, 255));
        clave_crear_text.setText("clave");
        jPanel4.add(clave_crear_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, -1, -1));

        ip_text.setFont(new java.awt.Font("Herculanum", 0, 36)); // NOI18N
        ip_text.setForeground(new java.awt.Color(255, 255, 255));
        ip_text.setText("ip");
        jPanel4.add(ip_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, -1, -1));
        jPanel4.add(clave, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 410, 50));

        clave_text.setFont(new java.awt.Font("Herculanum", 0, 36)); // NOI18N
        clave_text.setForeground(new java.awt.Color(255, 255, 255));
        clave_text.setText("clave");
        jPanel4.add(clave_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, -1, -1));

        unirse_unirse.setBackground(new java.awt.Color(102, 102, 102));
        unirse_unirse.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        unirse_unirse.setForeground(new java.awt.Color(255, 255, 255));
        unirse_unirse.setText("Unirse");
        unirse_unirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    unirse_unirseActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        jPanel4.add(unirse_unirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 140, 30));

        crear_crear.setBackground(new java.awt.Color(102, 102, 102));
        crear_crear.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        crear_crear.setForeground(new java.awt.Color(255, 255, 255));
        crear_crear.setText("Crear");
        crear_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crear_crearActionPerformed(evt);
            }
        });
        jPanel4.add(crear_crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 140, 30));

        jButton1.setText("Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 700, -1, -1));

        pantallas.addTab("tab4", jPanel4);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Herculanum", 1, 70)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 0));
        jLabel5.setText("Sala de espera");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        jugadores_conectados.setBackground(java.awt.Color.lightGray);
        jugadores_conectados.setColumns(20);
        jugadores_conectados.setRows(5);
        jugadores_conectados.setEnabled(false);
        jScrollPane1.setViewportView(jugadores_conectados);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 270, 560));

        jLabel6.setFont(new java.awt.Font("Herculanum", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jugadores Conectados");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, -1, 30));

        comenzar_partida.setText("Comenzar Partida");
        comenzar_partida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzar_partidaActionPerformed(evt);
            }
        });
        jPanel5.add(comenzar_partida, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 330, 50));

        jButton4.setText("Menu Principal");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 700, -1, -1));

        clave_espera.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        clave_espera.setForeground(new java.awt.Color(255, 255, 255));
        clave_espera.setText("jLabel7");
        jPanel5.add(clave_espera, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, -1));

        pantallas.addTab("tab5", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(153, 153, 0));
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 404, -1, 168));

        terminal.setBackground(new java.awt.Color(0, 0, 0));
        terminal.setColumns(20);
        terminal.setForeground(new java.awt.Color(153, 153, 0));
        terminal.setRows(5);
        terminal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                terminalKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(terminal);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 578, 1265, 155));

        jugadores_partida.setEditable(false);
        jugadores_partida.setBackground(new java.awt.Color(0, 0, 0));
        jugadores_partida.setColumns(20);
        jugadores_partida.setForeground(new java.awt.Color(153, 153, 0));
        jugadores_partida.setLineWrap(true);
        jugadores_partida.setRows(5);
        jScrollPane4.setViewportView(jugadores_partida);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 216));

        jTextArea4.setEditable(false);
        jTextArea4.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea4.setColumns(20);
        jTextArea4.setForeground(new java.awt.Color(153, 153, 0));
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jScrollPane5.setViewportView(jTextArea4);

        jPanel6.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 228, -1, 170));

        jTextArea5.setEditable(false);
        jTextArea5.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea5.setColumns(20);
        jTextArea5.setForeground(new java.awt.Color(153, 153, 0));
        jTextArea5.setLineWrap(true);
        jTextArea5.setRows(5);
        jScrollPane6.setViewportView(jTextArea5);

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 540, 560));

        jTextArea6.setEditable(false);
        jTextArea6.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea6.setColumns(20);
        jTextArea6.setForeground(new java.awt.Color(153, 153, 0));
        jTextArea6.setLineWrap(true);
        jTextArea6.setRows(5);
        jScrollPane7.setViewportView(jTextArea6);

        jPanel6.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 460, 280));

        jTextArea7.setEditable(false);
        jTextArea7.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea7.setColumns(20);
        jTextArea7.setForeground(new java.awt.Color(153, 153, 0));
        jTextArea7.setLineWrap(true);
        jTextArea7.setRows(5);
        jScrollPane8.setViewportView(jTextArea7);

        jPanel6.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 460, 270));

        pantallas.addTab("tab6", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pantallas, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pantallas, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jugarActionPerformed
        // TODO add your handling code here:
        pantallas.setSelectedIndex(3);
    }//GEN-LAST:event_jugarActionPerformed

    private void jugarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jugarFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jugarFocusGained

    private void jugarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jugarMouseMoved
        // TODO add your handling code here:
        configuracion_label.setForeground(Color.white);
        jugar_label.setForeground(Color.red);
    }//GEN-LAST:event_jugarMouseMoved

    private void jugarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jugarMouseDragged
        // TODO add your handling code here
    }//GEN-LAST:event_jugarMouseDragged

    private void configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracionActionPerformed
        // TODO add your handling code here:

        /*
        jugadorActual.generarGuerreros(nombre_guerrero1.getText(), 0, tipo_guerrero1.getText());
        
        jugadorActual.generarGuerreros(nombre_guerrero2.getText(), 1, tipo_guerrero2.getText());
        
        jugadorActual.generarGuerreros(nombre_guerrero3.getText(), 2, tipo_guerrero3.getText());
        
        jugadorActual.generarGuerreros(nombre_guerrero4.getText(), 3, tipo_guerrero4.getText());
         */
        System.out.println("CONF");
        jugadorActual.generarGuerreros("GUERRERRO 1", 0, "HIELO");
        jugadorActual.generarGuerreros("GUERRERRO 2", 1, "FUEGO");
        jugadorActual.generarGuerreros("GUERRERRO 3", 2, "AGUA");
        jugadorActual.generarGuerreros("GUERRERRO 4", 3, "AIRE");

    }//GEN-LAST:event_configuracionActionPerformed

    private void configuracionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_configuracionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_configuracionFocusGained

    private void configuracionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionMouseMoved
        // TODO add your handling code here:
        jugar_label.setForeground(Color.white);
        configuracion_label.setForeground(Color.red);
    }//GEN-LAST:event_configuracionMouseMoved

    private void configuracionMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configuracionMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_configuracionMouseDragged

    private void registrarse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarse1ActionPerformed
        // TODO add your handling code here:
        pantallas.setSelectedIndex(0);
    }//GEN-LAST:event_registrarse1ActionPerformed

    private void registrarse_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarse_butActionPerformed
        // TODO add your handling code here:
        String user = username_registrarse.getText();
        String password = String.valueOf(password_registrarse.getPassword());
        int puerto= Integer.parseInt( manejador.leer("archivos/puerto.txt"));
        Usuario usr = new Usuario(user, password,puerto);
        manejador.escribir("archivos/puerto.txt", String.valueOf(puerto+1));

        int existe = manejador.crear_carpeta("archivos/" + user);

        if (existe == 0) {
            boolean creador = manejador.guardarUsuario(usr);
            if (creador) {
                JOptionPane.showMessageDialog(pantallas, "Usuario " + user + " creado correctamente!!!", "CONFIRMACION", JOptionPane.INFORMATION_MESSAGE);
                username_login.setText(user);
                password_login.setText(password);
                pantallas.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(pantallas, "Error al crear el usuario!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(pantallas, "El usuario ---" + user + "--- ya existe!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_registrarse_butActionPerformed

    private void registrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarseActionPerformed
        // TODO add your handling code here:
        pantallas.setSelectedIndex(1);
    }//GEN-LAST:event_registrarseActionPerformed

    private void login_butActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_butActionPerformed
        // TODO add your handling code here:
        String user = username_login.getText();
        String password = String.valueOf(password_login.getPassword());

        String lectura = manejador.leer("archivos/" + user + "/password.txt");

        if (!lectura.equals("-1")) {

            if (lectura.equals(password)) {
                pantallas.setSelectedIndex(2);
                jugadorActual = manejador.buscarusuario(user);
                System.out.println("PUERTO A USAR: "+jugadorActual.port);
                port = jugadorActual.port;
                username = jugadorActual.getUsername();
                comenzar();
                
                //c = new Cliente("sebas", jugadorActual.port);
                
                //c.comenzar();
                
            } else {
                JOptionPane.showMessageDialog(pantallas, "Contraseña incorrecta!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(pantallas, "El usuario no existe!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_login_butActionPerformed

    private void unirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unirseActionPerformed
        // TODO add your handling code here:
        mostrarUnirse();
        esconderPrincipales();
    }//GEN-LAST:event_unirseActionPerformed

    private void crear_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_partidaActionPerformed
        // TODO add your handling code here:
        mostrarCrear();
        esconderPrincipales();
    }//GEN-LAST:event_crear_partidaActionPerformed

    private void atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasActionPerformed
        // TODO add your handling code here:
        esconderTodos();
        mostrarPrincipales();
        limpiarTodos();
    }//GEN-LAST:event_atrasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        pantallas.setSelectedIndex(2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void crear_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crear_crearActionPerformed
        // TODO add your handling code here:
        pantallas.setSelectedIndex(4);
        claveCreacion = clave_crear.getText();
        contadorPartida = 0;
        agregarJugador(jugadorActual.getUsername());
        p=new Partida(claveCreacion,"127.0.0.1");
        p.jugadores.add(jugadorActual);
        jugadores.add(jugadorActual);
        

        clave_espera.setText("Clave: " + claveCreacion);
        clave_espera.setVerticalAlignment(0);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(MortalKombat.class.getName()).log(Level.SEVERE, null, ex);
        }
        comenzar_partida.setVisible(true);
        try{
            enviarMensaje("nuevo-" + jugadorActual.getUsername() + "-" + jugadorActual.port + "-" + claveCreacion);
        }
        catch(IOException e){
            
        }
    }//GEN-LAST:event_crear_crearActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        pantallas.setSelectedIndex(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    //aca es donde se lee la consola
    private void terminalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_terminalKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String[] todo = terminal.getText().split("\n");
            String tmp = todo[todo.length - 1];
            if (!tmp.equals(ultimoComando)) {
                ultimoComando = tmp;
                System.out.println(ultimoComando);
            }

        }
    }//GEN-LAST:event_terminalKeyPressed

    private void unirse_unirseActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_unirse_unirseActionPerformed
        // TODO add your handling code here:
        jugadores.add(jugadorActual);
        agregarJugador(jugadorActual.getUsername());
        //p=c.hilo.getPartida(ip.getText(), clave.getText(), jugadorActual.getUsername());
        //p.jugadores.add(jugadorActual);
        System.out.println("UNIDO");
        
        comenzar_partida.setVisible(false);
        clave_espera.setText("Esperando al Anfitrion...");
        pantallas.setSelectedIndex(4);
        try {
            //-conectar -nombre -port -password
            enviarMensaje("conectar-" + jugadorActual.getUsername() + "-" + jugadorActual.port + "-" + clave.getText());
        }
        catch(IOException e){

        }
    }//GEN-LAST:event_unirse_unirseActionPerformed

    private void comenzar_partidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzar_partidaActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(pantallas, "¿Estas seguro que quieres comenzar la partida?", "Comenzar Partida", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            comenzar_partida.setVisible(false);
            try {
                enviarMensaje("comenzar-");
            }
            catch(IOException e){

            }
        }
    }//GEN-LAST:event_comenzar_partidaActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MortalKombat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MortalKombat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MortalKombat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MortalKombat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MortalKombat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton atras;
    private javax.swing.JTextField clave;
    private javax.swing.JTextField clave_crear;
    private javax.swing.JLabel clave_crear_text;
    private javax.swing.JLabel clave_espera;
    private javax.swing.JLabel clave_text;
    private javax.swing.JButton comenzar_partida;
    private javax.swing.JButton configuracion;
    private javax.swing.JLabel configuracion_label;
    private javax.swing.JToggleButton crear_crear;
    private javax.swing.JToggleButton crear_partida;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel ip_text;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jugadores_conectados;
    private javax.swing.JTextArea jugadores_partida;
    private javax.swing.JButton jugar;
    private javax.swing.JLabel jugar_label;
    private javax.swing.JButton login_but;
    private javax.swing.JTabbedPane pantallas;
    private javax.swing.JPasswordField password_login;
    private javax.swing.JPasswordField password_registrarse;
    private javax.swing.JToggleButton registrarse;
    private javax.swing.JToggleButton registrarse1;
    private javax.swing.JButton registrarse_but;
    private javax.swing.JTextArea terminal;
    private javax.swing.JToggleButton unirse;
    private javax.swing.JButton unirse_partida2;
    private javax.swing.JToggleButton unirse_unirse;
    private javax.swing.JTextField username_login;
    private javax.swing.JTextField username_registrarse;
    // End of variables declaration//GEN-END:variables
}
