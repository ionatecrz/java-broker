package main;
import domain.*;
import ui.Eleccion;
import util.IOFichero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame{

    private final ImageIcon iconImage = new ImageIcon("resources/logo/Logo.png");

    public static Font FONT = new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14);
    public static Color COLOR_NARANJA = new Color(255, 192, 0);
    public static Color COLOR_BOTONES = new Color(0, 58, 81);

    public int ancho;

    public int alto;


    public Inicio() {
        super("Java Broker");
        GestorFondos.iniciarFondos();
        this.setIconImage(iconImage.getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        ancho= screenSize.width;
        alto= screenSize.height;
        init();
        this.setSize(ancho,alto);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.requestFocus();
    }

    private void init() {
        //Fondo
        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icono = new ImageIcon("resources/screens/PantallaInicio.png");
                g.drawImage(icono.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        fondo.setLayout(null);

        //menú
        JPanel menu = new JPanel(new GridLayout(5, 1));

        //JUGAR
        JButton botonJugar = GestorMenu.crearBoton("Jugar", FONT, COLOR_NARANJA, COLOR_BOTONES);
        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eleccion eleccion=new Eleccion();
                Inicio.this.dispose();
            }
        });
        menu.add(botonJugar);

        menu.add(GestorMenu.crearTransparente());

        //INFORMACION
        JButton botonInfo = GestorMenu.crearBoton("Información", FONT, COLOR_NARANJA, COLOR_BOTONES);
        botonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Inicio.this, IOFichero.read("resources/informacion.txt"), "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(botonInfo);

        menu.add(GestorMenu.crearTransparente());

        //CREDITOS
        JButton botonCreditos = GestorMenu.crearBoton("Créditos", FONT, COLOR_NARANJA, COLOR_BOTONES);
        botonCreditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Inicio.this, IOFichero.read("resources/creditos.txt"), "Créditos", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        menu.add(botonCreditos);

        menu.setOpaque(false);

        // Centrar menú
        Double valorDouble = (ancho*(1 - 1/3.7) / 2.5);
        int x = (int) (ancho*(1 - 1/3.7) / 2 + valorDouble.intValue());
        int y = (int) (alto*(1 - 1/2.88)/2);
        menu.setBounds(x, y, (int) (ancho/3.7), (int) (alto/2.88));
        fondo.add(menu);
        add(fondo);
        setVisible(true);
    }

    public static void main(String[] args) {
        Inicio inicio=new Inicio();
    }
}
