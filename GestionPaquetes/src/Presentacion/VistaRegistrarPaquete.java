package Presentacion;

import Clases.*;
import java.awt.*;
import javax.swing.*;

public class VistaRegistrarPaquete extends JPanel {

    private VistaMensajesUsuario vmu;
    private Paquete paquete;

    private JTextField txtPeso;
    private JTextField txtTipoEnvio;
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

        txtPeso           = new JTextField();
        txtTipoEnvio      = new JTextField();
        txtCiudadEnvio    = new JTextField();
        txtCiudadDestino  = new JTextField();
        txtDireccion      = new JTextField();
        txtDestNombre     = new JTextField();
        txtDestTel        = new JTextField();
        txtNroSeguimiento = new JTextField();

        JTextField[] campos = {txtPeso, txtTipoEnvio, txtCiudadEnvio, txtCiudadDestino,
                               txtDireccion, txtDestNombre, txtDestTel, txtNroSeguimiento};
        for (JTextField c : campos) estilizarCampo(c);

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

        form.add(crearCampo("Nro. Seguimiento:", txtNroSeguimiento));
        form.add(crearCampo("Peso (kg):", txtPeso));
        form.add(crearCampo("Tipo Envío:", txtTipoEnvio));
        form.add(crearCampo("Ciudad Envío:", txtCiudadEnvio));
        form.add(crearCampo("Ciudad Destino:", txtCiudadDestino));
        form.add(crearCampo("Dirección Entrega:", txtDireccion));
        form.add(crearCampo("Destinatario:", txtDestNombre));
        form.add(crearCampo("Tel. Destinatario:", txtDestTel));

        JButton btnGuardar = crearBoton("Guardar", new Color(46, 134, 222));
        JButton btnVolver  = crearBoton("Volver", new Color(100, 100, 100));

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
        String nro  = txtNroSeguimiento.getText().trim();
        String peso = txtPeso.getText().trim();
        String tipo = txtTipoEnvio.getText().trim();
        String cEnv = txtCiudadEnvio.getText().trim();
        String cDes = txtCiudadDestino.getText().trim();
        String dir  = txtDireccion.getText().trim();
        String dNom = txtDestNombre.getText().trim();
        String dTel = txtDestTel.getText().trim();

        if (nro.isEmpty() || cEnv.isEmpty() || cDes.isEmpty() || dNom.isEmpty()) {
            vmu.advertencias("Complete todos los campos obligatorios.");
            return;
        }
        if (!peso.matches("\\d+(\\.\\d+)?")) {
            vmu.error("El peso debe ser un valor numérico.");
            return;
        }

        paquete = new Paquete();
        paquete.setNroSeguimiento(nro);
        paquete.setPeso(new java.math.BigDecimal(peso));
        paquete.setTipoEnvio(tipo);
        paquete.setEstado("Receptado");
        paquete.setCiudadEnvio(cEnv);
        paquete.setCiudadDestino(cDes);
        paquete.setDireccionEntrega(dir);
        paquete.setDestinatarioNomb(dNom);
        paquete.setDestinatarioTel(dTel);
        paquete.setFechaHora(new java.util.Date());

        vmu.informacion("Paquete listo para guardar. Nro: " + nro);
        base.cambiarVista("RECEPCIONISTA");
    }

    public Paquete getPaquete() { return paquete; }

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
        btn.setPreferredSize(new Dimension(120, 35));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}