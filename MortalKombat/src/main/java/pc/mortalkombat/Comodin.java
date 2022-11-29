package pc.mortalkombat;

import java.util.Timer;
import java.util.TimerTask;

public class Comodin extends Thread {

    private boolean isRunning = true;
    private boolean isPaused = false;
    private int contadorSegundos = 0;
    private int contadorMinutos = 0;
    private int contadorHoras = 0;

    private void verifica() {
        if (contadorSegundos < 59) {
            contadorSegundos++;
        } else {
            contadorSegundos = 0;
            if (contadorMinutos < 59) {
                contadorMinutos++;
            } else {
                contadorMinutos = 0;
                contadorHoras++;
            }
        }
    }

    private void setContador() {
        String hora = String.valueOf(contadorHoras);
        String minutos = String.valueOf(contadorMinutos);
        String segundos = String.valueOf(contadorSegundos);

        if (hora.length() == 1) {
            hora = "0" + hora;
        }
        if (minutos.length() == 1) {
            minutos = "0" + minutos;
        }
        if (segundos.length() == 1) {
            segundos = "0" + segundos;
        }
    }

    @Override
    public void run() {
        while (isRunning) {

            try {
                sleep(1000);
                verifica();
                setContador();
            } catch (InterruptedException ex) {
            }

            while (isPaused) {
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                }
            }
        }
    }

    public boolean pauseResume() {
        this.isPaused = !this.isPaused;
        return this.isPaused;
    }

    public void finish() {
        this.isRunning = false;
        this.isPaused = false;
    }
}
