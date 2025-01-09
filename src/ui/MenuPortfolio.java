package ui;
import domain.*;

import javax.swing.*;

public class MenuPortfolio {
    public static JLabel crearLabel(GestorClientes gestorClientes) {
        JLabel label = new JLabel(gestorClientes.getPortfolio());
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setText("<html>" + label.getText().replaceAll("\n", "<br>") + "</html>");
        return label;
    }

}
