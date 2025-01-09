package domain;
import javax.swing.*;
import java.awt.*;

public class GestorMenu {

    static Color COLOR_BOTONES=new Color(21, 96, 130);
    static Color COLOR_NARANJA=new Color(255, 192, 0);

    public static Color getColorBotones() {
        return COLOR_BOTONES;
    }

    public static Color getColorNaranja() {
        return COLOR_NARANJA;
    }

    public static JButton crearBoton(String texto, Font font, Color Foreground, Color Background){
        JButton boton = new JButton(texto);
        boton.setFont(font);
        boton.setForeground(Foreground);
        boton.setBackground(Background);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    public static JButton crearBotonImagen(ImageIcon icono){
        JButton boton = new JButton(icono);
        int anchoImagen = icono.getIconWidth();
        int altoImagen = icono.getIconHeight();
        boton.setPreferredSize(new Dimension(anchoImagen, altoImagen));
        return boton;
    }

    public static JPanel crearLabel(String texto, Font font, Color color) {
        JPanel panelActivo = new JPanel();
        panelActivo.setLayout(new BorderLayout());
        JLabel labelActivo = new JLabel(texto);
        labelActivo.setFont(font);
        labelActivo.setHorizontalAlignment(SwingConstants.CENTER);

        if (color != null) {
            labelActivo.setForeground(color);
        }

        panelActivo.add(labelActivo, BorderLayout.CENTER);
        panelActivo.setBackground(Color.WHITE);
        return panelActivo;
    }

    public static JPanel crearTransparente(){
        JPanel transparentPanel = new JPanel();
        transparentPanel.setOpaque(false);
        return transparentPanel;
    }
}
