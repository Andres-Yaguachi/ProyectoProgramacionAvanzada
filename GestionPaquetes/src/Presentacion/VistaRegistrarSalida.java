package Presentacion;

import Clases.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

public class VistaRegistrarSalida extends JPanel {

    private VistaMensajesUsuario vmu;
    private Despacha despacha;
    private JTextField txtCodigoPaquete;

    public VistaRegistrarSalida(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        vmu = new VistaMensajesUsuario();
        txtCodigoPaquete = new JTextField();
        estilizarCampo(txtCodigoPaquete);

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Registrar Salida");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(new Color(21, 44, 71));
        form.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        form.add(crearCampo("Código Paquete:", txtCodigoPaquete));

        JButton btnGuardar = crearBoton("Registrar Salida", new Color(230, 126, 34));
        JButton btnVolver  = crearBoton("Volver", new Color(100, 100, 100));

        btnVolver.addActionListener(e -> base.cambiarVista("OPERADOR"));
        btnGuardar.addActionListener(e -> registrarSalida(base));

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        footer.add(btnGuardar);
        footer.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void registrarSalida(VistaPrincipal base) {
        String cod = txtCodigoPaquete.getText().trim();
        if (cod.isEmpty()) {
            vmu.advertencias("El código del paquete es obligatorio.");
            return;
        }
        if (!cod.matches("\\d+")) {
            vmu.error("El código del paquete debe ser numérico.");
            return;
        }

        despacha = new Despacha();
        despacha.setFechaHora(new Date());

        vmu.informacion("Salida lista para registrar.");
        base.cambiarVista("OPERADOR");
    }

    public Despacha getDespacha()    { return despacha; }
    public String getCodigoPaquete() { return txtCodigoPaquete.getText().trim(); }

    private JPanel crearCampo(String etiqueta, JTextField campo) {
        JPanel fila = new JPanel(new BorderLayout(10, 5));
        fila.setBackground(new Color(21, 44, 71));
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(Color.WHITE);
        lbl.setPreferredSize(new Dimension(150, 30));
        fila.add(lbl, BorderLayout.WEST);
        fila.add(campo, BorderLayout.CENTER);
        fila.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return fila;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBackground(new Color(40, 70, 110));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(46, 134, 222)),
            BorderFactory.createEmptyBorder(4, 7, 4, 7)
        ));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(150, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}