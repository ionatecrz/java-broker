package util;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Graficable {

    private LinkedList<Double> valores;
    private JPanel panel;
    private String titulo;

    public Graficable(LinkedList<Double> valores, JPanel panel, String titulo) {
        this.valores = valores;
        this.panel = panel;
        this.titulo = titulo;
    }

    public void dibujarGrafico() {
        panel.repaint();
    }

    public void dibujarGraficoLineas(Graphics g) {
        int ancho = panel.getWidth();
        int alto = panel.getHeight();
        int margen = 50;

        double valorMaximo = Collections.max(valores);
        double escala = (double) (alto - 2 * margen) / valorMaximo;
        escalarValores(escala);

        int[] limitesEjeY = calcularLimitesEjeY(valores);
        double rangoValores = limitesEjeY[1] - limitesEjeY[0];

        double pasoY = (alto - 2 * margen) / rangoValores;

        Graphics2D g2d = (Graphics2D) g;

        // Dibujar ejes
        dibujarEjeX(g2d, margen, alto, ancho, valores.size());
        dibujarEjeY(g2d, margen, alto,titulo);

        // Dibujar líneas entre los puntos
        dibujarLineas(g2d, margen, calcularPasoX(ancho, margen), (int) pasoY, valores.size(), limitesEjeY[1], limitesEjeY[0]);

        // Dibujar puntos
        dibujarPuntos(g2d, margen, calcularPasoX(ancho, margen), (int) pasoY, limitesEjeY[1], limitesEjeY[0]);

        // Dibujar título del gráfico
        dibujarTitulo(g2d, ancho, margen);
    }

    private void escalarValores(double escala) {
        for (int i = 0; i < valores.size(); i++) {
            valores.set(i, valores.get(i) * escala);
        }
    }

    public int[] calcularLimitesEjeY(LinkedList<Double> valores) {
        double minValor = Collections.min(valores);
        double maxValor = Collections.max(valores);
        return new int[]{(int) Math.floor(minValor), (int) Math.ceil(maxValor)};
    }


    public int calcularPasoX(int ancho, int margen) {
        return (ancho - 2 * margen) / (valores.size() - 1);
    }


    public void dibujarEjeX(Graphics2D g2d, int margen, int alto, int ancho, int numValores) {
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(margen, alto - margen, ancho - margen, alto - margen);

        // Cada 10 semanas
        for (int i = 0; i < numValores; i++) {
            if ((i + 1) % 10 == 0 || i == 0 || i == numValores - 1) {
                int x = margen + i * (ancho - 2 * margen) / (numValores - 1);
                g2d.drawString(String.valueOf(i + 1), x, alto - margen / 2 + 20);
            }
        }
    }

    public void dibujarEjeY(Graphics2D g2d, int margen, int alto,String nombre) {
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(margen, margen, margen, alto - margen);

        int numMarcasY = 5;

        double max=Collections.max(LectorCSV.leerValor(nombre));
        double min=Collections.min(LectorCSV.leerValor(nombre));
        double[] valoresMarcasY = new double[numMarcasY];

        for (int i = 0; i < numMarcasY; i++) {
            valoresMarcasY[i]=min+i*(max-min)/5;
        }

        double intervaloMarcasY = (double) (alto - 2 * margen) / (numMarcasY - 1);

        for (int i = 0; i < numMarcasY; i++) {
            int valorMarcaY = (int) (alto - margen - i * intervaloMarcasY);
            g2d.drawString(String.valueOf(Math.round(valoresMarcasY[i])), margen / 2 - 20, valorMarcaY + 5);
        }
    }

    public void dibujarLineas(Graphics2D g2d, int margen, int pasoX, int pasoY, int numValores, int maxValor, int minValor) {
        for (int i = 1; i < numValores; i++) {
            int x = margen + i * pasoX;
            int y = panel.getHeight() - margen - (int) ((valores.get(i) - minValor) * pasoY);
            int xAnterior = margen + (i - 1) * pasoX;
            int yAnterior = panel.getHeight() - margen - (int) ((valores.get(i - 1) - minValor) * pasoY);

            Color colorLinea;
            if (y > yAnterior) {
                colorLinea = Color.RED; // Línea ascendente
            } else {
                colorLinea = Color.GREEN; // Línea descendente u horizontal
            }

            g2d.setColor(colorLinea);
            g2d.drawLine(xAnterior, yAnterior, x, y);
        }
    }


    public void dibujarPuntos(Graphics2D g2d, int margen, int pasoX, int pasoY, int maxValor, int minValor) {
        for (int i = 0; i < valores.size(); i++) {
            int x = margen + i * pasoX;
            int y = panel.getHeight() - margen - (int) ((valores.get(i) - minValor) * pasoY);
            g2d.setColor(Color.GREEN.darker());
            g2d.fillOval(x - 4, y - 4, 8, 8);
        }
    }

    public void dibujarTitulo(Graphics2D g2d, int ancho, int margen) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Bernard MT", Font.BOLD, 16));
        FontMetrics metrics = g2d.getFontMetrics();
        int tituloWidth = metrics.stringWidth(titulo);
        int xTitulo = (ancho - tituloWidth) / 2;
        int yTitulo = margen / 2 + metrics.getHeight() / 2;
        g2d.drawString(titulo, xTitulo, yTitulo);
    }
}

