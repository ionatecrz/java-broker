package ui;
import domain.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuComprar extends JPanel implements ActionListener {

    private LinkedList<GestorActivos> gestoresActivos;
    private JComboBox<String> BoxClientes;
    private JComboBox<String> BoxActivos;
    private LinkedList<Activo> activos;
    private JCheckBox seleccionarTodos;
    private JTextField num;
    private JTextField mostrarCant;
    private Juego juego;
    private GestorClientes gestorClientes;

    public MenuComprar(GestorClientes gestorClientes, LinkedList<GestorActivos> gestoresActivos, Juego juego) {
        this.juego = juego;
        this.gestorClientes = gestorClientes;
        this.gestoresActivos = gestoresActivos;
        this.setLayout(new GridLayout(6, 1));
        Color colorNaranja = new Color(255, 192, 0);
        Color colorBotones = new Color(21, 96, 130);

        //COMPRAR
        JPanel paneltxtComprar = new JPanel();
        JLabel labelComprar = new JLabel("Comprar");
        labelComprar.setFont(new Font("Serif", Font.BOLD, 30));
        labelComprar.setForeground(new Color(0, 195, 0));
        paneltxtComprar.add(labelComprar);
        paneltxtComprar.setOpaque(false);
        this.add(paneltxtComprar);


        //PANEL CLIENTES
        JPanel panelClientes = new JPanel(new FlowLayout());
        LinkedList<Cliente> clientes = gestorClientes.getClientes();
        LinkedList<String> nombreClientes = new LinkedList<>();
        for (Cliente c : clientes) {
            nombreClientes.add(c.getNombre());
        }
        JComboBox<String> comboBoxClientes = new JComboBox<>(nombreClientes.toArray(new String[0]));
        comboBoxClientes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setForeground(colorBotones);
                } else {
                    c.setForeground(colorNaranja);
                }
                return c;
            }
        });
        BoxClientes = comboBoxClientes;
        JCheckBox checkBox = new JCheckBox("Seleccionar todos");
        checkBox.setForeground(colorBotones);
        checkBox.setOpaque(false);
        seleccionarTodos = checkBox;
        panelClientes.add(comboBoxClientes);
        panelClientes.add(GestorMenu.crearTransparente());
        panelClientes.add(GestorMenu.crearTransparente());
        panelClientes.add(GestorMenu.crearTransparente());
        panelClientes.add(checkBox);
        panelClientes.setOpaque(false);
        this.add(panelClientes);

        //PANEL ACTIVOS
        JPanel panelActivos = new JPanel();
        LinkedList<Activo> activos = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            for (Activo a : gestoresActivos.get(i).getActivos()) {
                activos.add(a);
            }
        }
        LinkedList<String> nombreActivos = new LinkedList<>();
        for (Activo a : activos) {
            nombreActivos.add(a.getNombre());
        }
        this.activos = activos;
        JComboBox<String> comboBoxActivos = new JComboBox<>(nombreActivos.toArray(new String[0]));
        BoxActivos = comboBoxActivos;
        comboBoxActivos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setForeground(colorBotones);
                } else {
                    c.setForeground(colorNaranja);
                }
                return c;
            }
        });
        JTextField numAcciones = new JTextField();
        num = numAcciones;
        numAcciones.setPreferredSize(new Dimension(150, 30));
        panelActivos.add(GestorMenu.crearLabel("Activos:", new Font("Serif", Font.BOLD, 14),null));
        panelActivos.add(comboBoxActivos);
        panelActivos.add(GestorMenu.crearTransparente());
        panelActivos.add(GestorMenu.crearTransparente());
        panelActivos.add(GestorMenu.crearLabel("Cantidad de acciones:", new Font("Serif", Font.BOLD, 14),null));
        panelActivos.add(numAcciones);
        panelActivos.setOpaque(false);
        this.add(panelActivos);

        //CANTIDAD
        JLabel txtcant = new JLabel("Coste de la compra que realizaría por cliente: ");
        JPanel panelCalc = new JPanel(new FlowLayout());
        panelCalc.setPreferredSize(new Dimension(500, 30));
        JTextField mostrarCant = new JTextField(10);
        mostrarCant.setEditable(false);
        this.mostrarCant=mostrarCant;
        panelCalc.add(txtcant);
        panelCalc.add(mostrarCant);
        panelCalc.setOpaque(false);
        this.add(panelCalc);

        this.add(GestorMenu.crearTransparente());


        //COMPRAR
        JButton botonComprar = GestorMenu.crearBoton("Comprar",new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14),Color.red,colorNaranja);
        JPanel panelC = new JPanel();
        JButton botonCalc = GestorMenu.crearBoton("Calcular",new Font("Bahnschrift SemiBold Condensed", Font.BOLD, 14),Color.red,colorNaranja);
        botonCalc.setSize(150, 30);
        botonCalc.addActionListener(this);
        botonComprar.addActionListener(this);
        panelC.add(botonComprar);
        panelC.add(botonCalc);
        panelC.setOpaque(false);
        this.add(panelC);
        this.setOpaque(false);
        }


    @Override
    public void actionPerformed(ActionEvent e){
        if (BoxClientes.getSelectedItem() == null & !seleccionarTodos.isSelected() || BoxActivos.getSelectedItem() == null || num.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Introduce valores para realizar la operación.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            if(e.getActionCommand().equals("Comprar")){
                String activoSeleccionado = (String) BoxActivos.getSelectedItem();
                int cantidad;
                try {
                    cantidad = Integer.parseInt(num.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El valor introducido en 'Cantidad de acciones' no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                for (GestorActivos gestor:gestoresActivos) {
                    for (Activo a : gestor.getActivos()) {
                        if (activoSeleccionado.equals(a.getNombre())) {

                            Activo activoDef = a;
                            LinkedList<Cliente> clientes1 = new LinkedList<>();
                            if (seleccionarTodos.isSelected()) {
                                for (Cliente cliente : gestorClientes.getClientes()) {
                                    cliente.invertir(activoDef, cantidad);
                                    clientes1.add(cliente);
                                }
                                this.gestorClientes.setClientes(clientes1);
                                juego.setGestorClientes(this.gestorClientes);
                                juego.mostrarLiquidos.setText(gestorClientes.getClientesLiquido());
                                juego.contadorDinero.setText(String.format("%.2f", gestorClientes.getDineroTotal())+" €");
                                juego.repaint();
                            } else {
                                String clienteSeleccionado = (String) BoxClientes.getSelectedItem();
                                for (Cliente cliente : gestorClientes.getClientes()) {
                                    if (cliente.getNombre().equals(clienteSeleccionado)) {
                                        cliente.invertir(activoDef, cantidad);
                                        juego.setCantidadDinero(gestorClientes.getDineroTotal());
                                        juego.setGestorClientes(this.gestorClientes);
                                        juego.mostrarLiquidos.setText(gestorClientes.getClientesLiquido());
                                        juego.contadorDinero.setText(String.format("%.2f", gestorClientes.getDineroTotal())+" €");
                                        juego.repaint();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }else if(e.getActionCommand().equals("Calcular")){
                if (!(num.getText().isEmpty() || BoxActivos.getSelectedItem() == null)) {
                    String activoSeleccionado = (String) BoxActivos.getSelectedItem();
                    int cantidadAcciones;
                    try {
                        cantidadAcciones = Integer.parseInt(num.getText());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "El valor introducido en 'Cantidad de acciones' no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    for (Activo a : activos) {
                        if (activoSeleccionado.equals(a.getNombre())) {
                            double costoTotal = cantidadAcciones * a.getValor();
                            String costoFormateado = String.format("%.2f", costoTotal);
                            mostrarCant.setText(costoFormateado);
                            break;
                        }
                    }
                }
            }
        }
        this.requestFocus();
    }
}