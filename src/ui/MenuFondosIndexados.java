package ui;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
public class MenuFondosIndexados extends JPanel implements ActionListener {

    private LinkedList<GestorActivos> gestoresActivos;
    private HashMap<String,JButton> btns;

    public MenuFondosIndexados(LinkedList<GestorActivos> gestoresActivos) {
        this.setLayout(null);
        this.gestoresActivos = gestoresActivos;
        this.btns=new HashMap<>();

        JLabel labelEuropa = new JLabel("Europa");
        labelEuropa.setFont(new Font("Serif", Font.BOLD, 20));
        labelEuropa.setForeground(new Color(255, 192, 0));
        labelEuropa.setBounds(110, 20, 80, 50);
        this.add(labelEuropa);

        JPanel menuEuropa = new JPanel(new GridLayout(6, 1, 0, 5));
        menuEuropa.setBounds(40, 70, 200, (6 * 50) + (5 * 5));
        menuEuropa.setOpaque(false);
        for (Activo a : gestoresActivos.get(0).getActivos()) {
            JPanel panel = new JPanel(new BorderLayout());
            JButton boton = GestorMenu.crearBoton(a.getNombre(), new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14), new Color(255, 192, 0), new Color(21, 96, 130));
            boton.setPreferredSize(new Dimension(200, 50));
            boton.addActionListener(this);
            btns.put(a.getNombre(),boton);
            panel.add(boton);
            panel.setOpaque(false);
            menuEuropa.add(panel);
        }
        this.add(menuEuropa);

        JLabel labelAmerica = new JLabel("América");
        labelAmerica.setFont(new Font("Serif", Font.BOLD, 20));
        labelAmerica.setForeground(new Color(255, 192, 0));
        labelAmerica.setBounds(410, 20, 100, 50);
        this.add(labelAmerica);

        JPanel menuAmerica = new JPanel(new GridLayout(5, 1, 0, 5));
        menuAmerica.setBounds(340, 70, 200, (5 * 50) + (4 * 5));
        menuAmerica.setOpaque(false);
        for (Activo a : gestoresActivos.get(1).getActivos()) {
            JPanel panel = new JPanel(new BorderLayout());
            JButton boton = GestorMenu.crearBoton(a.getNombre(), new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14), new Color(255, 192, 0), new Color(21, 96, 130));
            boton.setPreferredSize(new Dimension(200, 50));
            boton.addActionListener(this);
            btns.put(a.getNombre(),boton);
            panel.add(boton);
            panel.setOpaque(false);
            menuAmerica.add(panel);
        }
        this.add(menuAmerica);

        // Resto del Mundo
        JLabel labelResto = new JLabel("Resto del Mundo");
        labelResto.setFont(new Font("Serif", Font.BOLD, 20));
        labelResto.setForeground(new Color(255, 192, 0));
        labelResto.setBounds(690, 20, 160, 50);
        this.add(labelResto);

        JPanel menuResto = new JPanel(new GridLayout(5, 1, 0, 5));
        menuResto.setBounds(665, 70, 200, (5 * 50) + (4 * 5));
        menuResto.setOpaque(false);
        for (Activo a : gestoresActivos.get(2).getActivos()) {
            JPanel panel = new JPanel(new BorderLayout());
            JButton boton = GestorMenu.crearBoton(a.getNombre(), new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14), new Color(255, 192, 0), new Color(21, 96, 130));
            boton.setPreferredSize(new Dimension(200, 50));
            boton.addActionListener(this);
            btns.put(a.getNombre(),boton);
            panel.add(boton);
            panel.setOpaque(false);
            menuResto.add(panel);
        }
        this.add(menuResto);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();
        JPanel panel=GestorActivos.pintarGrafico(accion);
        JFrame frame = new JFrame();
        frame.setTitle(accion);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}