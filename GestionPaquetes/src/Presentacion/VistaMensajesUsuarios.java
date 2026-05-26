package Presentacion;

import javax.swing.JOptionPane;

public class VistaMensajesUsuarios {

    public void error(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error: ", JOptionPane.ERROR_MESSAGE);
    }

    public void advertencias(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Advertencias: ", JOptionPane.WARNING_MESSAGE);
    }

    public void informacion(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Informacion: ", JOptionPane.INFORMATION_MESSAGE);
    }
}
