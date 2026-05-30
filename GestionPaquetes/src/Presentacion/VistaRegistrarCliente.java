package Presentacion;

import Clases.*;
import Logica.LogicaRecepcion;
import java.awt.*;
import javax.swing.*;

public class VistaRegistrarCliente extends JPanel {

    private VistaMensajesUsuario vmu;
    private Persona persona;
    private Cliente cliente;

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JTextField txtDireccion;
    private JTextField txtCiudad;
    private LogicaRecepcion logica = new LogicaRecepcion();

    public VistaRegistrarCliente(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        vmu = new VistaMensajesUsuario();

        txtCedula = new JTextField();
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtDireccion = new JTextField();
        txtCiudad = new JTextField();

        JTextField[] campos = {txtCedula, txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion, txtCiudad};
        for (JTextField c : campos) {
            estilizarCampo(c);
        }
        estilizarCampo(txtPassword);

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Registrar Cliente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBackground(new Color(21, 44, 71));
        form.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        form.add(crearCampo("Cédula:", txtCedula));
        form.add(crearCampo("Nombre:", txtNombre));
        form.add(crearCampo("Apellido:", txtApellido));
        form.add(crearCampo("Teléfono:", txtTelefono));
        form.add(crearCampo("Email:", txtEmail));
        form.add(crearCampo("Contraseña:", txtPassword));
        form.add(crearCampo("Dirección:", txtDireccion));
        form.add(crearCampo("Ciudad:", txtCiudad));

        JButton btnGuardar = crearBoton("Guardar", new Color(46, 134, 222));
        JButton btnVolver = crearBoton("Volver", new Color(100, 100, 100));

        btnVolver.addActionListener(e -> base.cambiarVista("RECEPCIONISTA"));
        btnGuardar.addActionListener(e -> registrarCliente(base));

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        footer.add(btnGuardar);
        footer.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(new JScrollPane(form), BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void registrarCliente(VistaPrincipal base) {
        String ced = txtCedula.getText().trim();
        String nom = txtNombre.getText().trim();
        String apl = txtApellido.getText().trim();
        String tel = txtTelefono.getText().trim();
        String eml = txtEmail.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();
        String dir = txtDireccion.getText().trim();
        String ciu = txtCiudad.getText().trim();

        if (ced.isEmpty() || nom.isEmpty()) {
            vmu.advertencias("Cédula y nombre son obligatorios.");
            return;
        }
        if (dir.isEmpty() || ciu.isEmpty()) {
            vmu.advertencias("Dirección y ciudad son obligatorios.");
            return;
        }

        persona = new Persona();
        persona.setCedula(ced);
        persona.setNombre(nom);
        persona.setApellido(apl);
        persona.setNumero(tel);
        persona.setEmail(eml);
        persona.setPassword(pass);

        cliente = new Cliente();
        cliente.setCedula(persona);
        cliente.setDireccion(dir);
        cliente.setCiudad(ciu);

        try {

            Recepcionista recActivo = base.getRecepcionistaLogueado();
            logica.registrarCliente(persona, cliente, recActivo);

            vmu.informacion("Cliente registrado exitosamente en el sistema.");
            base.cambiarVista("RECEPCIONISTA");

        } catch (Exception ex) {
            vmu.error("Error al guardar el cliente en la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public Persona getPersona() {
        return persona;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private JPanel crearCampo(String etiqueta, JTextField campo) {
        JPanel fila = new JPanel(new BorderLayout(10, 5));
        fila.setBackground(new Color(21, 44, 71));
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(Color.WHITE);
        lbl.setPreferredSize(new Dimension(110, 30));
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
