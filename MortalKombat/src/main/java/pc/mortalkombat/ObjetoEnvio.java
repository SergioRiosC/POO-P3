package pc.mortalkombat;

import java.io.Serializable;

public class ObjetoEnvio implements Serializable {
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMiPuerto() {
        return miPuerto;
    }

    public void setMiPuerto(String miPuerto) {
        this.miPuerto = miPuerto;
    }

    public String getMiIp() {
        return miIp;
    }

    public void setMiIp(String miIp) {
        this.miIp = miIp;
    }

    private String nombre, ip, puerto, mensaje, miIp, miPuerto;
}
