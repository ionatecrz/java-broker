package ui;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.*;


public class Juego extends JFrame implements ActionListener {

    private final ImageIcon iconImage = new ImageIcon("resources/logo/Logo.png");

    private GestorClientes gestorClientes;

    private HashMap<String,Boolean> visibles;

    public JLabel mostrarActivos;
    public JLabel mostrarLiquidos;
    public JLabel mostrarPortfolio;
    public JLabel mostrarObjetivo;

    private double objetivo;

    private JLabel contadorSemanas;

    private double cantidadDinero;

    public JLabel contadorDinero;

    private JPanel menuActualFondos;

    private JLabel menuActualPortfolio;

    private JPanel menuGalgos;

    private JPanel menuActualVender;

    private JPanel menuActualComprar;

    private JPanel menuActualCriptomonedas;

    private JPanel fondo;

    private final LinkedList<GestorActivos> gestoresActivos;

    private int semanas;

    private static final int TOTAL_SEMANAS=52;

    public Juego(GestorClientes gestorClientes, LinkedList<GestorActivos> gestoresActivos, double objetivo) {
        super("Java Broker");
        this.setIconImage(iconImage.getImage());
        this.semanas=0;
        this.cantidadDinero=gestorClientes.getDineroTotal();
        this.objetivo=cantidadDinero+(objetivo*cantidadDinero);
        this.gestorClientes=gestorClientes;
        this.gestoresActivos=gestoresActivos;
        MenuFondosIndexados menuFondosIndexados=new MenuFondosIndexados(gestoresActivos);
        this.menuActualFondos=menuFondosIndexados;
        MenuCriptoMonedas menuCriptoMonedas=new MenuCriptoMonedas(gestoresActivos);
        this.menuActualCriptomonedas=menuCriptoMonedas;
        MenuVender menuVender=new MenuVender(gestorClientes,gestoresActivos,this);
        this.menuActualVender=menuVender;
        MenuGalgos menuGalgos=new MenuGalgos(this,gestorClientes);
        this.menuGalgos=menuGalgos;
        this.mostrarActivos=new JLabel();
        MenuComprar menuComprar=new MenuComprar(gestorClientes,gestoresActivos,this);
        this.menuActualComprar=menuComprar;
        this.mostrarLiquidos=new JLabel();
        this.menuActualPortfolio= MenuPortfolio.crearLabel(gestorClientes);
        this.fondo=drawFondo();
        this.contadorDinero=mostrarContadorDinero();
        this.contadorSemanas=mostrarContadorSemanas();
        this.mostrarPortfolio=new JLabel("Portfolio");
        mostrarPortfolio.setFont(new Font("Serif", Font.BOLD, 30));
        this.visibles=new HashMap<>();
        this.visibles.put("Fondos Indexados",false);
        this.visibles.put("Criptomonedas",false);
        this.visibles.put("Alto Riesgo",false);
        this.visibles.put("Comprar",false);
        this.visibles.put("Vender",false);
        this.visibles.put("Portfolio",false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        init();
        this.setSize(screenSize.width,screenSize.height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.requestFocus();
    }

    public int getSemanas() {
        return semanas;
    }
    public void adelantarSemanas(int semanas){
            this.setSemanas(this.getSemanas()+semanas);
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }

    public double getCantidadDinero() {
        return cantidadDinero;
    }
    public void setCantidadDinero(double cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }

    private JPanel drawFondo(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Fondo

        JPanel fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icono = new ImageIcon("resources/screens/PantallaJuego.png");
                g.drawImage(icono.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        fondo.setLayout(null);

        //MENÚ

        JPanel menu = new JPanel(new GridLayout(17, 1));
        Font font = new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14);
        Color colorNaranja = new Color(255, 192, 0);

        // menú
        Color colorBotones = new Color(21, 96, 130);

        menu.add(GestorMenu.crearLabel("ACTIVOS",new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 20),null));
        menu.add(GestorMenu.crearTransparente());

        //FONDOS
        JButton botonFondos=GestorMenu.crearBoton("Fondos Indexados",font,colorNaranja,colorBotones);
        botonFondos.addActionListener(this);
        menu.add(botonFondos);

        menu.add(GestorMenu.crearTransparente());

        //CRIPTOMONEDAS
        JButton botonCripto=GestorMenu.crearBoton("Criptomonedas",font,colorNaranja,colorBotones);
        botonCripto.addActionListener(this);
        menu.add(botonCripto);

        menu.add(GestorMenu.crearTransparente());

        //ALTO RIESGO
        JButton botonRiesgo=GestorMenu.crearBoton("Alto Riesgo",font,colorNaranja,colorBotones);
        botonRiesgo.addActionListener(this);
        menu.add(botonRiesgo);
        menu.add(GestorMenu.crearTransparente());
        menu.add(GestorMenu.crearTransparente());

        //COMPRAR
        JButton botonComprar=GestorMenu.crearBoton("Comprar",font,Color.red,colorNaranja);
        botonComprar.addActionListener(this);
        menu.add(botonComprar);

        menu.add(GestorMenu.crearTransparente());

        //VENDER
        JButton botonVender=GestorMenu.crearBoton("Vender",font,Color.red,colorNaranja);
        botonVender.addActionListener(this);
        menu.add(botonVender);

        menu.add(GestorMenu.crearTransparente());

        //ADELANTAR
        JButton botonAdelantar=GestorMenu.crearBoton("Adelantar",font,new Color(0, 32, 96),new Color(150, 220, 248));
        botonAdelantar.addActionListener(this);
        menu.add(botonAdelantar);

        menu.add(GestorMenu.crearTransparente());

        //PORTFOLIO
        JButton botonPortfolio=GestorMenu.crearBoton("Portfolio",font,new Color(39, 83, 23),new Color(180, 229, 162));
        botonPortfolio.addActionListener(this);
        menu.add(botonPortfolio);

        menu.setOpaque(false);

        // Colocar menú
         int width = (int) (screenSize.width/7.3);
         int height = (int) (screenSize.width/2.3);
         double valorDouble = ((screenSize.width - width) / 2.5);
         int x = (screenSize.width - width) / 2- (int)valorDouble-(int) (screenSize.width/25.6);
         int y = (screenSize.height - height)/2;

        menu.setBounds(x, y, width, height);

        fondo.add(menu);

        JLabel mostrarObjetivo = new JLabel(objetivo+" €");
        mostrarObjetivo.setFont(new Font("Rockwell", Font.BOLD, 30));
        mostrarObjetivo.setOpaque(false);
        mostrarObjetivo.setForeground(new Color(255,195,0));
        mostrarObjetivo.setBounds(320, -20, 200, 100);

        fondo.add(mostrarObjetivo);

        return fondo;
    }

    public void setGestorClientes(GestorClientes gestorClientes){
        this.gestorClientes=gestorClientes;
    }

    private void init(){
        JPanel fondo=this.drawFondo();
        //contador de semanas
        contadorSemanas.setBounds(735, -20, 200, 100);
        contadorSemanas.setOpaque(false);
        fondo.add(contadorSemanas);
        //contador de dinero
        contadorDinero.setBounds(1100, -20, 200, 100);
        contadorDinero.setOpaque(false);
        fondo.add(contadorDinero);
        //Fondos
        menuActualFondos.setOpaque(false);
        menuActualFondos.setBounds(340, 40, 1200, 400);
        menuActualFondos.setVisible(false);
        fondo.add(menuActualFondos);
        //Criptomonedas
        menuActualCriptomonedas.setOpaque(false);
        menuActualCriptomonedas.setBounds(350, 40, 1200, 300);
        menuActualCriptomonedas.setVisible(false);
        fondo.add(menuActualCriptomonedas);
        //Alto Riesgo
        menuGalgos.setOpaque(false);
        menuGalgos.setBounds(390, 80, 800, 500);
        menuGalgos.setVisible(false);
        fondo.add(menuGalgos);
        //Vender
        menuActualVender.setOpaque(false);
        menuActualVender.setBounds(485, 100, 600, 500);
        menuActualVender.setVisible(false);
        fondo.add(menuActualVender);
        mostrarActivos.setForeground(new Color(21, 96, 130));
        mostrarActivos.setBounds(485, 340, 600, 200);
        mostrarActivos.setVisible(false);
        fondo.add(mostrarActivos);
        //Comprar
        menuActualComprar.setOpaque(false);
        menuActualComprar.setBounds(485, 100, 600, 500);
        menuActualComprar.setVisible(false);
        fondo.add(menuActualComprar);
        mostrarLiquidos.setForeground(new Color(21, 96, 130));
        mostrarLiquidos.setBounds(485, 350, 600, 200);
        mostrarLiquidos.setVisible(false);
        fondo.add(mostrarLiquidos);
        //Portfolio
        mostrarPortfolio.setForeground(new Color(21, 96, 130));
        mostrarPortfolio.setBounds(730, 50, 200, 50);
        mostrarPortfolio.setVisible(false);
        fondo.add(mostrarPortfolio);
        menuActualPortfolio.setOpaque(false);
        menuActualPortfolio.setBounds(350,120,400,800);
        menuActualPortfolio.setVisible(visibles.get("Portfolio"));
        fondo.add(menuActualPortfolio);
        this.add(fondo);
        this.setVisible(true);
    }

    private JLabel mostrarContadorSemanas(){
        JLabel cont_semanas = new JLabel("Semana: " + this.semanas);
        cont_semanas.setFont(new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 20));
        cont_semanas.setOpaque(false);
        return cont_semanas;
    }
    private JLabel mostrarContadorDinero(){
        JLabel cont_dinero = new JLabel(cantidadDinero+" €");
        cont_dinero.setFont(new Font("Rockwell", Font.BOLD, 30));
        cont_dinero.setOpaque(false);
        cont_dinero.setForeground(new Color(0,195,0));
        cont_dinero.setBounds(1100, -20, 200, 100);
        return cont_dinero;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String accion = e.getActionCommand();

        if (accion.equals("Fondos Indexados")) {
            this.visiblesAFalse();
            visibles.put("Fondos Indexados",true);
            actualizaryPintar(); //CREO UN HILO
        }
        else if(accion.equals("Criptomonedas")) {
            this.visiblesAFalse();
            visibles.put("Criptomonedas",true);
            actualizaryPintar();//CREO UN HILO
        }else if(accion.equals("Alto Riesgo")) {
            this.visiblesAFalse();
            visibles.put("Alto Riesgo",true);
            actualizaryPintar();//CREO UN HILO
        }
        else if(accion.equals("Comprar")) {
            this.visiblesAFalse();
            visibles.put("Comprar", true);
            actualizarVisibles();
            mostrarLiquidos.setText(gestorClientes.getClientesLiquido());
            actualizaryPintar();//CREO UN HILO
        }

        else if(accion.equals("Vender")) {
            this.visiblesAFalse();
            visibles.put("Vender",true);
            mostrarActivos.setText(gestorClientes.getClientesActivos());
            actualizaryPintar();
        }
        else if (accion.equals("Adelantar")) {
            JDialog dialog = new JDialog(this, "Semanas a adelantar", true);
            dialog.setLayout(null);
            dialog.setSize(270, 175);
            dialog.setLocationRelativeTo(this);

            JLabel label = new JLabel("Adelantar: ");
            label.setSize(75, 25);
            label.setLocation(10, 10);
            dialog.add(label);

            JTextField textField = new JTextField();
            textField.setSize(150, 25);
            textField.setLocation(85, 10);
            dialog.add(textField);
            dialog.setResizable(false);

            JLabel label_act = new JLabel("Semana actual: "+semanas);
            label_act.setSize(175, 25);
            label_act.setLocation(10, 50);
            dialog.add(label_act);

            JButton okButton = new JButton("OK");
            okButton.setSize(60, 30);
            okButton.setLocation(100, 90);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int semanas_add = Integer.parseInt(textField.getText());
                        if (semanas+semanas_add>TOTAL_SEMANAS) {
                            String restante = String.valueOf(TOTAL_SEMANAS - semanas);
                            JOptionPane.showMessageDialog(dialog, "No puedes adelantar más semanas de las que quedan para que termine el juego.\n(" + restante + " semanas restantes)",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(semanas_add<0){
                            JOptionPane.showMessageDialog(dialog, "Todavía no tenemos la tecnología para viajar atrás en el tiempo, somos brokers no Hollywood.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            Juego.this.adelantarSemanas(semanas_add);

                            for (GestorActivos g : gestoresActivos) {
                                g.calcularValores(semanas_add);
                            }

                            contadorSemanas.setText("Semana: " + semanas);

                            if (Juego.this.visibles.get("Portfolio")) {
                                Juego.this.visiblesAFalse();
                                visibles.put("Portfolio", true);
                                Juego.this.menuActualPortfolio.setText(gestorClientes.getPortfolio());
                                Juego.this.menuActualPortfolio.setText("<html>" + menuActualPortfolio.getText().replaceAll("\n", "<br>") + "</html>");
                                actualizaryPintar(); // CREO UN HILO
                            }

                            dialog.dispose();
                            if (Juego.this.semanas == 52) {
                                gestorClientes.venderTodo();
                                if (gestorClientes.getDineroTotal() >= objetivo) {
                                    Fin finG=new Fin("¡HAS GANADO!",gestorClientes);
                                } else {
                                    Fin finP=new Fin("¡HAS PERDIDO!",gestorClientes);
                                }
                                Juego.this.dispose();
                            }

                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            dialog.add(okButton);
            dialog.setVisible(true);
        }

        else if(accion.equals("Portfolio")) {
            this.visiblesAFalse();
            visibles.put("Portfolio",true);
            this.menuActualPortfolio.setText(gestorClientes.getPortfolio());
            this.menuActualPortfolio.setText("<html>" + menuActualPortfolio.getText().replaceAll("\n", "<br>") + "</html>");
            actualizaryPintar();//CREO UN HILO
        }
        this.requestFocus();
    }

    private void visiblesAFalse(){
        for (String s:visibles.keySet()){
            visibles.put(s,false);
        }
    }

    public void actualizarDinero(){
        contadorDinero.setText(String.format("%.2f", gestorClientes.getDineroTotal())+" €");
    }

    private void actualizarVisibles(){
        menuActualPortfolio.setVisible(visibles.get("Portfolio"));
        mostrarPortfolio.setVisible(visibles.get("Portfolio"));
        menuActualFondos.setVisible(visibles.get("Fondos Indexados"));
        menuActualCriptomonedas.setVisible(visibles.get("Criptomonedas"));
        menuGalgos.setVisible(visibles.get("Alto Riesgo"));
        menuActualVender.setVisible(visibles.get("Vender"));
        mostrarActivos.setVisible(visibles.get("Vender"));
        menuActualComprar.setVisible(visibles.get("Comprar"));
        mostrarLiquidos.setVisible(visibles.get("Comprar"));
    }

    public void actualizaryPintar(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                actualizarVisibles();
                Juego.this.repaint();
            }
        });
        t.start();
    }

}
