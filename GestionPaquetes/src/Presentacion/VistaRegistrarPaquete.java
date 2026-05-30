package Presentacion;

import Clases.*;
import Logica.LogicaRecepcion;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class VistaRegistrarPaquete extends JPanel {

    private VistaMensajesUsuario vmu;
    private Paquete paquete;
    private LogicaRecepcion logica = new LogicaRecepcion();

    // Componentes del formulario
    private JTextField txtCedulaCliente;
    private JTextField txtPeso;
    private JComboBox<String> cbxTipoEnvio;
    private JComboBox<String> cbxTarifa; // NUEVO: Desplegable para la tarifa
    private List<Tarifa> listaTarifas;
    private JTextField txtCiudadEnvio;
    private JTextField txtCiudadDestino;
    private JTextField txtDireccion;
    private JTextField txtDestNombre;
    private JTextField txtDestTel;
    private JTextField txtNroSeguimiento;

    public VistaRegistrarPaquete(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        vmu = new VistaMensajesUsuario();

        // Inicialización de componentes
        txtCedulaCliente = new JTextField(); // NUEVO
        txtPeso = new JTextField();
        cbxTipoEnvio = new JComboBox<>(new String[]{"Paquete", "Documento"});

        txtCiudadEnvio = new JTextField();
        txtCiudadDestino = new JTextField();
        txtDireccion = new JTextField();
        txtDestNombre = new JTextField();
        txtDestTel = new JTextField();
        txtNroSeguimiento = new JTextField();
        cbxTarifa = new JComboBox<>();
        listaTarifas = logica.obtenerTodasLasTarifas();

        if (listaTarifas != null && !listaTarifas.isEmpty()) {
            for (Tarifa t : listaTarifas) {
                cbxTarifa.addItem(t.getDescripcion() + " - $" + t.getPrecioBase());
            }
        } else {
            cbxTarifa.addItem("No hay tarifas configuradas");
        }

        // Estilizamos 
        estilizarComboBox(cbxTarifa);
        JTextField[] campos = {txtCedulaCliente, txtPeso, txtCiudadEnvio, txtCiudadDestino,
            txtDireccion, txtDestNombre, txtDestTel, txtNroSeguimiento};
        for (JTextField c : campos) {
            estilizarCampo(c);
        }

        // Estilizar el JComboBox para que combine con tu diseño oscuro
        estilizarComboBox(cbxTipoEnvio);

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Registrar Paquete");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(new Color(21, 44, 71));
        form.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        // Construcción del formulario en pantalla
        form.add(crearCampo("Cédula Cliente:", txtCedulaCliente));
        form.add(crearCampo("Peso (kg):", txtPeso));
        form.add(crearCampo("Tipo Envío:", cbxTipoEnvio));
        form.add(crearCampo("Tarifa Aplicable:", cbxTarifa));
        form.add(crearCampo("Ciudad Envío:", txtCiudadEnvio));
        form.add(crearCampo("Ciudad Destino:", txtCiudadDestino));
        form.add(crearCampo("Dirección Entrega:", txtDireccion));
        form.add(crearCampo("Destinatario:", txtDestNombre));
        form.add(crearCampo("Tel. Destinatario:", txtDestTel));

        JButton btnGuardar = crearBoton("Guardar", new Color(46, 134, 222));
        JButton btnVolver = crearBoton("Volver", new Color(100, 100, 100));

        btnVolver.addActionListener(e -> base.cambiarVista("RECEPCIONISTA"));
        btnGuardar.addActionListener(e -> registrarPaquete(base));

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        footer.add(btnGuardar);
        footer.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(new JScrollPane(form), BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void registrarPaquete(VistaPrincipal base) {
        String cedCliente = txtCedulaCliente.getText().trim();
        String peso = txtPeso.getText().trim();
        String tipo = cbxTipoEnvio.getSelectedItem().toString();
        String cEnv = txtCiudadEnvio.getText().trim();
        String cDes = txtCiudadDestino.getText().trim();
        String dir = txtDireccion.getText().trim();
        String dNom = txtDestNombre.getText().trim();
        String dTel = txtDestTel.getText().trim();

        // Validaciones obligatorias incluyendo la cédula
        if (cedCliente.isEmpty() || cEnv.isEmpty() || cDes.isEmpty() || dNom.isEmpty()) {
            vmu.advertencias("Complete todos los campos obligatorios (Cédula, Ciudades y Destinatario).");
            return;
        }
        if (!peso.matches("\\d+(\\.\\d+)?")) {
            vmu.error("El peso debe ser un valor numérico.");
            return;
        }

        // Armamos el objeto entidad Paquete
        paquete = new Paquete();
        paquete.setPeso(new java.math.BigDecimal(peso));
        paquete.setTipoEnvio(tipo);
        paquete.setCiudadEnvio(cEnv);
        paquete.setCiudadDestino(cDes);
        paquete.setDireccionEntrega(dir);
        paquete.setDestinatarioNomb(dNom);
        paquete.setDestinatarioTel(dTel);

        try {
            Recepcionista recActivo = base.getRecepcionistaLogueado();
            Cliente clienteAsociado = logica.obtenerClientePorCedula(cedCliente);

            if (clienteAsociado == null) {
                vmu.error("El cliente con cédula '" + cedCliente + "' no está registrado. Regístrelo primero.");
                return;
            }

            // NUEVO: Capturamos la tarifa seleccionada directamente desde la lista
            if (listaTarifas == null || listaTarifas.isEmpty()) {
                vmu.error("No se pueden registrar paquetes sin tarifas en el sistema.");
                return;
            }

            int tarSeleccionada = cbxTarifa.getSelectedIndex();

            
            Tarifa tarifaAsociada = listaTarifas.get(tarSeleccionada);

            
            logica.registrarPaquete(paquete, recActivo, clienteAsociado, tarifaAsociada, " ");
            vmu.informacion("Paquete registrado y guardado de manera exitosa. Tracking: " + paquete.getNroSeguimiento());

            txtCedulaCliente.setText("");
            txtPeso.setText("");
            txtCiudadEnvio.setText("");
            txtCiudadDestino.setText("");
            txtDireccion.setText("");
            txtDestNombre.setText("");
            txtDestTel.setText("");

            base.cambiarVista("RECEPCIONISTA");

        } catch (Exception ex) {
            vmu.error("Error al procesar y guardar el paquete: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Paquete getPaquete() {
        return paquete;
    }

    // MODIFICADO: Ahora acepta cualquier 'JComponent' (JTextField, JComboBox, etc.)
    private JPanel crearCampo(String etiqueta, JComponent campo) {
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

    // NUEVO: Método helper para mantener la paleta de colores oscuros en el ComboBox
    private void estilizarComboBox(JComboBox<String> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setBackground(new Color(40, 70, 110));
        combo.setForeground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(new Color(46, 134, 222)));
        // Cambiar renderizador para asegurar que los elementos desplegables mantengan el color texto blanco
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (isSelected) {
                    c.setBackground(new Color(46, 134, 222));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(40, 70, 110));
                    c.setForeground(Color.WHITE);
                }
                return c;
            }
        });
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(120, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
