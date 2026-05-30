/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

/**
 *
 * @author Usuario iTC
 */

import java.awt.*;
import javax.swing.*;
 
public class VistaSeleccionPersonal extends JPanel {
 
    public VistaSeleccionPersonal(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));
 
        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Seleccione tipo de Personal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);
 
        JPanel centro = new JPanel(new GridLayout(2, 2, 20, 20));
        centro.setBackground(new Color(21, 44, 71));
        centro.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));
 
        JButton btnSupervisor  = crearBoton("Supervisor",            new Color(46, 134, 222));
        JButton btnRecep       = crearBoton("Recepcionista",          new Color(39, 174, 96));
        JButton btnOperador    = crearBoton("Operador de Despacho",   new Color(230, 126, 34));
        JButton btnRepartidor  = crearBoton("Repartidor",             new Color(142, 68, 173));
 
        // Cada botón lleva al login pasando el rol elegido
        btnSupervisor.addActionListener(e -> irALogin(base, "SUPERVISOR"));
        btnRecep.addActionListener(e      -> irALogin(base, "RECEPCIONISTA"));
        btnOperador.addActionListener(e   -> irALogin(base, "OPERADOR"));
        btnRepartidor.addActionListener(e -> irALogin(base, "REPARTIDOR"));
 
        centro.add(btnSupervisor);
        centro.add(btnRecep);
        centro.add(btnOperador);
        centro.add(btnRepartidor);
 
        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        JButton btnVolver = crearBotonPequeno("Volver", new Color(100, 100, 100));
        btnVolver.addActionListener(e -> base.cambiarVista("OPCION"));
        footer.add(btnVolver);
 
        add(header, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
 

    private void irALogin(VistaPrincipal base, String rol) {
        base.setRolElegido(rol);
        base.cambiarVista("LOGIN");
    }

 
    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
 
    private JButton crearBotonPequeno(String texto, Color color) {
        JButton btn = crearBoton(texto, color);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(120, 35));
        return btn;
    }
}
