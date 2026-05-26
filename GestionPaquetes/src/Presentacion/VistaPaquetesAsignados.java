package Presentacion;

import Clases.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaPaquetesAsignados extends JPanel {

    private DefaultTableModel modelo;

    public VistaPaquetesAsignados(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Paquetes Asignados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        String[] columnas = {"Código", "Seguimiento", "Destino", "Dirección", "Destinatario", "Tel.", "Fecha Despacho"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable tabla = new JTable(modelo);
        tabla.setBackground(new Color(30, 58, 95));
        tabla.setForeground(Color.WHITE);
        tabla.setGridColor(new Color(46, 134, 222));
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.getTableHeader().setBackground(new Color(15, 30, 50));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setRowHeight(28);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setPreferredSize(new Dimension(120, 35));
        btnVolver.addActionListener(e -> base.cambiarVista("REPARTIDOR"));
        footer.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    public void cargarDatos(List<Despacha> despachos) {
        modelo.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (despachos != null) {
            for (Despacha d : despachos) {
                Paquete p = d.getPaquete();
                modelo.addRow(new Object[]{
                    p.getCodigoUnico(),
                    p.getNroSeguimiento(),
                    p.getCiudadDestino(),
                    p.getDireccionEntrega(),
                    p.getDestinatarioNomb(),
                    p.getDestinatarioTel(),
                    sdf.format(d.getFechaHora())
                });
            }
        }
    }
}