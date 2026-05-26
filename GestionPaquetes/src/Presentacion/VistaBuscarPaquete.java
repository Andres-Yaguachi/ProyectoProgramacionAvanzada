package Presentacion;

import Clases.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaBuscarPaquete extends JPanel {

    private VistaMensajesUsuario vmu;
    private DefaultTableModel modelo;
    private JTextField txtSeguimiento;

    public VistaBuscarPaquete(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        vmu = new VistaMensajesUsuario();
        txtSeguimiento = new JTextField();
        estilizarCampo(txtSeguimiento);

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Buscar Paquete");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        busqueda.setBackground(new Color(21, 44, 71));
        JLabel lbl = new JLabel("Nro. Seguimiento:");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(Color.WHITE);
        txtSeguimiento.setPreferredSize(new Dimension(200, 35));
        JButton btnBuscar = crearBoton("Buscar", new Color(46, 134, 222));
        btnBuscar.addActionListener(e -> buscar());
        busqueda.add(lbl);
        busqueda.add(txtSeguimiento);
        busqueda.add(btnBuscar);

        String[] columnas = {"Código", "Seguimiento", "Estado", "Origen", "Destino", "Destinatario", "Peso"};
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

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(21, 44, 71));
        centro.add(busqueda, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        JButton btnVolver = crearBoton("Volver", new Color(100, 100, 100));
        btnVolver.setPreferredSize(new Dimension(120, 35));
        btnVolver.addActionListener(e -> base.cambiarVista("OPERADOR"));
        footer.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void buscar() {
        String nro = txtSeguimiento.getText().trim();
        if (nro.isEmpty()) {
            vmu.advertencias("Ingrese un número de seguimiento.");
            return;
        }
        // tu compañero llama cargarResultado() con el paquete encontrado
    }

    public void cargarResultado(Paquete p) {
        modelo.setRowCount(0);
        if (p != null) {
            modelo.addRow(new Object[]{
                p.getCodigoUnico(),
                p.getNroSeguimiento(),
                p.getEstado(),
                p.getCiudadEnvio(),
                p.getCiudadDestino(),
                p.getDestinatarioNomb(),
                p.getPeso() + " kg"
            });
        } else {
            vmu.advertencias("Paquete no encontrado.");
        }
    }

    public String getNroSeguimiento() { return txtSeguimiento.getText().trim(); }

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
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}