package ui;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MenuGalgos extends JPanel {
    private Juego juego;
    private boolean empezo;
    private boolean primeraVez;
    private GestorClientes gestorClientes;
    private Carrera carrera;
    private JPanel carreraPanel;

    public MenuGalgos(Juego juego, GestorClientes gestorClientes) {
        this.juego = juego;
        this.gestorClientes = gestorClientes;
        empezo = false;
        primeraVez = true;
        setLayout(new BorderLayout());

        Galgo galgo1 = null;
        Galgo galgo2 = null;
        Galgo galgo3 = null;
        Galgo galgo4 = null;
        try {
            Random random = new Random();
            galgo1 = new Galgo("Ramón", random.nextInt(4, 11));
            galgo2 = new Galgo("Cajal", random.nextInt(4, 11));
            galgo3 = new Galgo("Ortega", random.nextInt(4, 11));
            galgo4 = new Galgo("Gasset", random.nextInt(4, 11));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Galgo[] galgos = new Galgo[]{galgo1, galgo2, galgo3, galgo4};

        String[] nombresGalgos = {"Ramón", "Cajal", "Ortega", "Gasset"};
        JComboBox<String> comboGalgos = new JComboBox<>(nombresGalgos);

        String[] porcentajes = {"5%", "10%", "15%"};
        JComboBox<String> comboApuesta = new JComboBox<>(porcentajes);

        JButton btnIniciar = GestorMenu.crearBoton("Iniciar Carrera",
                new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14),
                GestorMenu.getColorNaranja(), GestorMenu.getColorBotones());

        JButton btnInfo = GestorMenu.crearBoton("Cuotas",
                new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14),
                new Color(255, 0, 0), GestorMenu.getColorBotones());

        btnInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mensaje = "<html>" + "Cuotas:<br/>" +
                        "-Si aciertas al ganador y gana solo uno, cuadruplicas tu apuesta.<br/>" +
                        "-Si ganan dos y aciertas uno, duplicas tu apuesta.<br/>" +
                        "-En caso de que ganen más se reembolsará el dinero." +
                        "</html>";
                JOptionPane.showMessageDialog(MenuGalgos.this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JPanel panelControles = new JPanel();
        panelControles.setOpaque(false);
        panelControles.add(new JLabel("Seleccionar Galgo:"));
        panelControles.add(comboGalgos);
        panelControles.add(new JLabel("  Porcentaje del dinero total a apostar:"));
        panelControles.add(comboApuesta);
        panelControles.add(btnIniciar);
        panelControles.add(btnInfo);
        panelControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Carrera de galgos");
        titulo.setFont(new Font("Serif", Font.BOLD, 30));
        titulo.setForeground(new Color(255, 215, 0));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.setOpaque(false);
        panelSuperior.add(titulo, BorderLayout.NORTH);
        panelSuperior.add(panelControles, BorderLayout.CENTER);

        carreraPanel = new JPanel(new BorderLayout());
        carreraPanel.setOpaque(false);
        carrera = new Carrera(galgos, juego, "Ramón", 0.05, gestorClientes);

        carreraPanel.add(carrera, BorderLayout.CENTER);

        setOpaque(false);
        add(panelSuperior, BorderLayout.NORTH);
        add(carreraPanel, BorderLayout.CENTER);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (primeraVez) {
                    JOptionPane.showMessageDialog(MenuGalgos.this, "<html>" + "Tienes solo una oportunidad para apostar ¡Aprovéchala!<br/>" +
                            "Vuelve a pulsar en Iniciar Carrera si desea empezar"+
                            "</html>", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    primeraVez = false;
                } else if (!empezo) {
                    String galgoSeleccionado = (String) comboGalgos.getSelectedItem();
                    String porcentajeSeleccionado = (String) comboApuesta.getSelectedItem();
                    double porc = Double.parseDouble(porcentajeSeleccionado.substring(0, porcentajeSeleccionado.length() - 1)) / 100;

                    carrera = new Carrera(galgos, juego, galgoSeleccionado, porc, gestorClientes);
                    carreraPanel.removeAll();
                    carreraPanel.add(carrera, BorderLayout.CENTER);
                    carreraPanel.revalidate();
                    carreraPanel.repaint();

                    carrera.reiniciarCarrera();
                    carrera.iniciarCarrera();
                    empezo = true;
                } else {
                    JOptionPane.showMessageDialog(MenuGalgos.this, "Ya agotaste tu única tirada", "Basta por hoy", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.requestFocus();
    }
}
