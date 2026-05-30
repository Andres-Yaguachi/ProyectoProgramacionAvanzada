package Presentacion;

<<<<<<< Updated upstream
=======
import Clases.*;
import Logica.LogicaCliente;
import java.util.List;
>>>>>>> Stashed changes
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaCliente extends JPanel {

    private JTextField txtSeguimiento;
    private DefaultTableModel modeloTabla;
<<<<<<< Updated upstream
=======
    private VistaMensajesUsuario vmu = new VistaMensajesUsuario();
    LogicaCliente logica = new LogicaCliente();
>>>>>>> Stashed changes

    public VistaCliente(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Seguimiento de Paquete");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        // Búsqueda
        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        busqueda.setBackground(new Color(21, 44, 71));

        JLabel lbl = new JLabel("Nro. Seguimiento:");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(Color.WHITE);

        txtSeguimiento = new JTextField(20);
        txtSeguimiento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSeguimiento.setBackground(new Color(40, 70, 110));
        txtSeguimiento.setForeground(Color.WHITE);
        txtSeguimiento.setCaretColor(Color.WHITE);
        txtSeguimiento.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 134, 222)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setBackground(new Color(46, 134, 222));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        busqueda.add(lbl);
        busqueda.add(txtSeguimiento);
        busqueda.add(btnBuscar);

        // Tabla historial
        String[] columnas = {"Fecha/Hora", "Estado", "Ubicación", "Observaciones"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modeloTabla);
        tabla.setBackground(new Color(30, 58, 95));
        tabla.setForeground(Color.WHITE);
        tabla.setGridColor(new Color(46, 134, 222));
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.getTableHeader().setBackground(new Color(15, 30, 50));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setRowHeight(28);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnBuscar.addActionListener(e -> {
            String nro = txtSeguimiento.getText().trim();
            if (nro.isEmpty()) {
<<<<<<< Updated upstream
                JOptionPane.showMessageDialog(this, "Ingrese un número de seguimiento", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            modeloTabla.setRowCount(0);
            // Datos de prueba
            modeloTabla.addRow(new Object[]{"2025-05-13 08:00", "Receptado", "Local Norte", "Paquete ingresado"});
            modeloTabla.addRow(new Object[]{"2025-05-13 10:30", "En Tránsito", "Bodega Central", "En camino"});
=======
                vmu.advertencias("Ingrese un número de seguimiento.");
                return;
            }
            modeloTabla.setRowCount(0);
            try {
                //Buscamos el paquete
                Paquete paqueteEncontrado = logica.encontrarPaqueteXNro(nro);

                if (paqueteEncontrado != null) {
                    //Si existe, traemos su historial
                    List<HistorialEstado> historial = logica.traerHisotrial(paqueteEncontrado);

                    if (historial.isEmpty()) {
                        vmu.informacion("El paquete existe, pero aún no tiene movimientos registrados.");
                    } else {
                        // Formateador para que la fecha se vea legible
                        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");

                        // 5. Recorremos el historial y agregamos filas a la tabla
                        for (HistorialEstado he : historial) {
                            String fecha = sdf.format(he.getFechaHora());
                            String estado = he.getEstado();
                            // Validamos que ubicación no sea nula para evitar NullPointerException
                            String ubicacion = (he.getUbicacion() != null) ? he.getUbicacion().getNombre() : "N/A";
                            String observaciones = he.getObservaciones();

                            modeloTabla.addRow(new Object[]{fecha, estado, ubicacion, observaciones});
                        }
                    }
                } else {
                    vmu.error("No se encontró ningún paquete con el código: " + nro);
                }

            } catch (Exception ex) {
                vmu.error("Error al consultar el historial: " + ex.getMessage());
                ex.printStackTrace();
            }
>>>>>>> Stashed changes
        });

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        btnVolver.setPreferredSize(new Dimension(120, 35));
        btnVolver.addActionListener(e -> base.cambiarVista("OPCION"));
        footer.add(btnVolver);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(21, 44, 71));
        centro.add(busqueda, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }
}
