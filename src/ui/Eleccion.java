package ui;
import domain.*;
import main.Inicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Eleccion extends JFrame {

    private final ImageIcon iconImage = new ImageIcon("resources/logo/Logo.png");

    public Eleccion() {
        super("Java Broker");
        this.setIconImage(iconImage.getImage());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        init();
        this.setSize(screenSize.width,screenSize.height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.requestFocus();
    }

    private void init(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Random random=new Random();
        //fondo

        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icono = new ImageIcon("resources/screens/PantallaClientes.png");
                g.drawImage(icono.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        fondo.setLayout(null);

        //menú
        JPanel menu = new JPanel(new FlowLayout());


        JButton botonFondo1=GestorMenu.crearBoton("Fondo de Inversión 1", Inicio.FONT,Inicio.COLOR_NARANJA,Inicio.COLOR_BOTONES);
        botonFondo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Cliente> clientes=new LinkedList<>();
                clientes.add(new Cliente("James Bagner",101,random.nextInt(55000,75000),new HashMap<>()));
                clientes.add(new Cliente("William Thompson",104,random.nextInt(45000,50000),new HashMap<>()));
                GestorClientes gestorClientes=new GestorClientes(clientes);
                Juego juego=new Juego(gestorClientes, GestorFondos.iniciarFondos(),0.1);
                Eleccion.this.dispose();
            }
        });
        menu.add(botonFondo1);

        menu.add(GestorMenu.crearTransparente());

        JButton botonFondo2= GestorMenu.crearBoton("Fondo de Inversión 2",Inicio.FONT,Inicio.COLOR_NARANJA,Inicio.COLOR_BOTONES);
        botonFondo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Cliente> clientes=new LinkedList<>();
                clientes.add(new Cliente("Robert Wilson",203,random.nextInt(150000,165000),new HashMap<>()));
                clientes.add(new Cliente("Jessica Hummington",204,random.nextInt(80000,100000),new HashMap<>()));
                GestorClientes gestorClientes=new GestorClientes(clientes);
                Juego juego=new Juego(gestorClientes,GestorFondos.iniciarFondos(),0.2);
                Eleccion.this.dispose();
            }
        });
        menu.add(botonFondo2);

        menu.add(GestorMenu.crearTransparente());

        JButton botonFondo3=GestorMenu.crearBoton("Fondo de Inversión 3",Inicio.FONT,Inicio.COLOR_NARANJA,Inicio.COLOR_BOTONES);
        botonFondo3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Cliente> clientes=new LinkedList<>();
                clientes.add(new Cliente("Warren Buffet",301,random.nextInt(165000,170000),new HashMap<>()));
                clientes.add(new Cliente("Christopher White",303,random.nextInt(190000,200000),new HashMap<>()));
                GestorClientes gestorClientes=new GestorClientes(clientes);
                Juego juego=new Juego(gestorClientes,GestorFondos.iniciarFondos(),0.3);
                Eleccion.this.dispose();
            }
        });
        menu.add(botonFondo3);

        menu.setOpaque(false);

        // Colocar menú
        menu.setBounds((int) ((screenSize.width-(screenSize.width/1.2)) / 2), screenSize.height/2+100, (int) (screenSize.width/1.2), (int) (screenSize.height/3));

        fondo.add(menu);

        this.add(fondo);
        this.setVisible(true);
    }

}