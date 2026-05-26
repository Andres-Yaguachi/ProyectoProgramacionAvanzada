package Presentacion;

import java.awt.*;
import javax.swing.*;

public class VistaSupervisor extends JPanel {

    public VistaSupervisor(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Panel Supervisor");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        JPanel centro = new JPanel(new GridLayout(3, 1, 15, 15));
        centro.setBackground(new Color(21, 44, 71));
        centro.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JButton btnPersonal = crearBoton("Agregar Personal", new Color(46, 134, 222));
        JButton btnPaquetes = crearBoton("Listar Paquetes", new Color(39, 174, 96));
        JButton btnHistorial = crearBoton("Ver Historial de Estado", new Color(230, 126, 34));

        btnPersonal.addActionListener(e -> base.cambiarVista("AGREGAR_PERSONAL"));
        btnPaquetes.addActionListener(e -> base.cambiarVista("LISTAR_PAQUETES"));
        btnHistorial.addActionListener(e -> base.cambiarVista("HISTORIAL_ESTADO"));

        centro.add(btnPersonal);
        centro.add(btnPaquetes);
        centro.add(btnHistorial);

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        JButton btnCerrar = crearBoton("Cerrar Sesión", new Color(100, 100, 100));
        btnCerrar.setPreferredSize(new Dimension(140, 35));
        btnCerrar.addActionListener(e -> base.cambiarVista("LOGIN"));
        footer.add(btnCerrar);

        add(header, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}