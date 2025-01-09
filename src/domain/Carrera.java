package domain;

import ui.Juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Carrera extends JPanel {
    private GestorClientes gestorClientes;
    private Galgo[] galgos;
    private boolean carreraEnCurso;
    private int pistaWidth = 800;
    private int meta = pistaWidth - 50;
    private Timer timer;
    private String ganador;
    private Juego juego;
    private double cantidad;
    private double porc;

    public Carrera(Galgo[] galgos,Juego juego,String ganador,double porc,GestorClientes gestorClientes) {
        this.galgos = galgos;
        this.ganador=ganador;
        this.porc=porc;
        this.cantidad=gestorClientes.getDineroTotal()*porc/gestorClientes.getNumClientes();
        this.gestorClientes=gestorClientes;
        this.juego=juego;
        carreraEnCurso = false;
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moverGalgos();
                repaint();
            }
        });
    }

    public boolean isCarreraEnCurso() {
        return carreraEnCurso;
    }

    public void iniciarCarrera() {
        if (!carreraEnCurso) {
            carreraEnCurso = true;
            timer.start();
        }
    }

    public void detenerCarrera() {
        if (carreraEnCurso) {
            carreraEnCurso = false;
            timer.stop();
        }
    }

    public void reiniciarCarrera() {
        for (Galgo galgo : galgos) {
            galgo.reiniciar();
        }
        carreraEnCurso = false;
    }


    public void moverGalgos() {
        ArrayList<String> ganadores = new ArrayList<>();
        for (Galgo galgo : galgos) {
            galgo.correr();
            if (galgo.posicion >= meta) {
                ganadores.add(galgo.nombre);
            }
        }
        if (!ganadores.isEmpty()) {
            detenerCarrera();
            String mensaje = "¡";
            if (ganadores.size() > 1) {
                mensaje += "Han llegado al mismo tiempo: ";
                for (int i = 0; i < ganadores.size(); i++) {
                    mensaje += ganadores.get(i);
                    if (i < ganadores.size() - 1) {
                        mensaje += " y ";
                    }
                }
            } else {
                mensaje += ganadores.get(0) + " ha ganado la carrera!";
            }
            JOptionPane.showMessageDialog(this, mensaje);
            if(ganadores.contains(ganador)){
                if (ganadores.size()==1){
                    gestorClientes.ajustarGanancias(3,cantidad);
                }else if(ganadores.size()==2){
                    gestorClientes.ajustarGanancias(2,cantidad);
                }//sino no se modificará el dinero ya que se reembolsa lo invertido
            }else{
                gestorClientes.ajustarPerdidas(porc);
            }
            juego.actualizarDinero();
            reiniciarCarrera();
            repaint();
        }





    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image fondo = new ImageIcon("resources/galgos/FondoGalgos.png").getImage();
        g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.RED);
        g.fillRect(meta, 189, 5, 157);
        for (int i = 0; i < galgos.length; i++) {
            int yPos = 175 + (i * 40);
            galgos[i].paint(g, yPos);
        }

    }
}
