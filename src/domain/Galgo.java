package domain;

import util.Distribucion;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Galgo {

    private static final int GALGO_WIDTH = 70;
    private static final int GALGO_HEIGHT = 40;
    String nombre;
    int velocidad;
    Distribucion distribucion;
    int posicion;
    Image[] runningIcons;
    Image idleIcon;
    int posRunning = 0;

    public Galgo(String nombre, int velocidadMedia) throws IOException {
        this.nombre = nombre;
        this.posicion = 0;
        this.runningIcons = new Image[5];
        for (int i = 0; i < runningIcons.length; i++) {
            this.runningIcons[i] = readIconImage("resources/galgos/Correr" + (i + 1) + ".png");
        }
        this.idleIcon = readIconImage("resources/galgos/idle.png");
        this.distribucion = new Distribucion(velocidadMedia, velocidadMedia/10);
        this.velocidad = (int) distribucion.generarValor();
    }

    private Image readIconImage(String path) throws IOException {
        BufferedImage img = ImageIO.read(new File(path));
        return img.getScaledInstance(GALGO_WIDTH, GALGO_HEIGHT, Image.SCALE_SMOOTH);
    }

    public void correr() {
        recalcularVelocidad();
        posicion += velocidad;
        int frameChangeRate = Math.max(1, velocidad / 5);
        posRunning = (posRunning + frameChangeRate) % runningIcons.length;
    }

    public void reiniciar() {
        this.posicion = 0;
        this.velocidad = (int) distribucion.generarValor();
        this.posRunning = 0;
    }

    public void recalcularVelocidad(){
        setVelocidad((int) distribucion.generarValor());
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void paint(Graphics g, int y) {
        if (posRunning == 0) {
            g.drawImage(idleIcon, posicion, y, null);
        } else {
            g.drawImage(runningIcons[Math.abs(posRunning) - 1], posicion, y, null);
        }
    }


}

