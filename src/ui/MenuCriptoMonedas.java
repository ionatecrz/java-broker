package ui;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
public class MenuCriptoMonedas extends JPanel implements ActionListener {

    private LinkedList<GestorActivos> gestoresActivos;
    private HashMap<String,JButton> btns;

    public MenuCriptoMonedas(LinkedList<GestorActivos> gestoresActivos) {
        this.setLayout(null);
        this.gestoresActivos = gestoresActivos;
        this.btns=new HashMap<>();

        JLabel labelCripto = new JLabel("Criptomonedas");
        labelCripto.setFont(new Font("Serif", Font.BOLD, 20));
        labelCripto.setForeground(new Color(255, 192, 0));
        labelCripto.setBounds(370, 20, 200, 50);
        this.add(labelCripto);

        JPanel menuCripto = new JPanel(new GridLayout(2, 1, 0, 5));
        menuCripto.setBounds(345, 70, 180, 120);
        menuCripto.setOpaque(false);
        for (Activo a : gestoresActivos.get(3).getActivos()) {
            JPanel panel = new JPanel(new BorderLayout());
            JButton boton = GestorMenu.crearBoton(a.getNombre(), new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14), new Color(255, 192, 0), new Color(21, 96, 130));
            boton.setPreferredSize(new Dimension(100, 50));
            boton.addActionListener(this);
            btns.put(a.getNombre(),boton);
            panel.add(boton);
            panel.setOpaque(false);
            menuCripto.add(panel);
        }
        this.add(menuCripto);
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