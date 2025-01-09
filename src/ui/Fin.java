package ui;

import domain.GestorClientes;
import domain.GestorFondos;
import domain.GestorMenu;
import main.Inicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fin extends JFrame {

    private GestorClientes gestorClientes;

    Fin(String mensaje, GestorClientes gestorClientes) {
        this.setTitle("Java Broker Final");
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);

        // Fondo
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icono = new ImageIcon("resources/screens/PantallaFinal.png");
                g.drawImage(icono.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        fondo.setBounds(0, 0, 500, 500);
        layeredPane.add(fondo, JLayeredPane.DEFAULT_LAYER);

        // Panel de contenido
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setOpaque(false);
        panelContenido.setBounds(50, 50, 400, 400);
        layeredPane.add(panelContenido, JLayeredPane.PALETTE_LAYER);

        JLabel labelMensaje = new JLabel(mensaje);
        labelMensaje.setFont(new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 50));
        labelMensaje.setForeground(Color.YELLOW);
        labelMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);


        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 20));
        btnAceptar.setForeground(Color.CYAN);
        btnAceptar.setBackground(Inicio.COLOR_BOTONES);
        btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fin.this.dispose();
                GestorFondos.iniciarFondos();
            }
        });

        panelContenido.add(Box.createVerticalGlue());
        panelContenido.add(labelMensaje);
        panelContenido.add(GestorMenu.crearTransparente());
        panelContenido.add(GestorMenu.crearTransparente());
        panelContenido.add(GestorMenu.crearTransparente());
        panelContenido.add(GestorMenu.crearTransparente());
        panelContenido.add(btnAceptar);
        panelContenido.add(Box.createVerticalGlue());

        this.requestFocus();
    }
}
