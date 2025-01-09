package domain;
import util.LectorCSV;
import util.Graficable;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GestorActivos {

    private LinkedList<Activo> activos;

    GestorActivos(LinkedList<Activo> activos){
        this.activos=activos;
    }

    public LinkedList<Activo> getActivos() {
        return activos;
    }

    public void setActivos(LinkedList<Activo> activos) {
        this.activos = activos;
    }

    public void calcularValor(){
        for (Activo a: activos)
            a.actualizar();
    }
    public void calcularValores(int semanas){
        for (int i=0; i<semanas;i++)
            calcularValor();
    }

    public static JPanel pintarGrafico(String nombre) {
        JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    LinkedList<Double> valoresHistorico= LectorCSV.leerValor(nombre);
                    Graficable graficable = new Graficable(valoresHistorico, this,nombre);
                    int[] limitesEjeY = graficable.calcularLimitesEjeY(valoresHistorico);
                    double rangoValores = limitesEjeY[1] - limitesEjeY[0];
                    double pasoY = (this.getHeight() - 2 * 50) / rangoValores;
                    graficable.dibujarGraficoLineas(g);
                }
            };
        return panel;
    }


}