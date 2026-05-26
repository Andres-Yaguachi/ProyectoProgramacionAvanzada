package Presentacion;

import Clases.*;
import java.awt.*;
import javax.swing.*;

public class VistaAgregarPersonal extends JPanel {

    private VistaMensajesUsuario vmu;
    private Persona persona;
    private String rol;
    private String placa;
    private String vehiculo;

    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtNumero;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRol;

    public VistaAgregarPersonal(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        vmu = new VistaMensajesUsuario();

        txtCedula   = new JTextField();
        txtNombre   = new JTextField();
        txtApellido = new JTextField();
        txtNumero   = new JTextField();
        txtEmail    = new JTextField();
        txtPassword = new JPasswordField();
        cmbRol      = new JComboBox<>(new String[]{"Recepcionista", "Operador de Despacho", "Repartidor"});

        JTextField[] campos = {txtCedula, txtNombre, txtApellido, txtNumero, txtEmail};
        for (JTextField c : campos) estilizarCampo(c);
        estilizarCampo(txtPassword);
        cmbRol.setBackground(new Color(40, 70, 110));
        cmbRol.setForeground(Color.WHITE);

        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Agregar Personal");
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
        form.add(crearCampo("Teléfono:", txtNumero));
        form.add(crearCampo("Email:", txtEmail));
        form.add(crearCampo("Contraseña:", txtPassword));
        form.add(crearCampoCombo("Rol:", cmbRol));

        JButton btnGuardar = crearBoton("Guardar", new Color(46, 134, 222));
        JButton btnVolver  = crearBoton("Volver", new Color(100, 100, 100));

        btnVolver.addActionListener(e -> base.cambiarVista("SUPERVISOR"));
        btnGuardar.addActionListener(e -> guardarPersonal(base));

        JPanel footer = new JPanel();
        footer.setBackground(new Color(21, 44, 71));
        footer.add(btnGuardar);
        footer.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(new JScrollPane(form), BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    private void guardarPersonal(VistaPrincipal base) {
        String ced  = txtCedula.getText().trim();
        String nom  = txtNombre.getText().trim();
        String apl  = txtApellido.getText().trim();
        String num  = txtNumero.getText().trim();
        String eml  = txtEmail.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (ced.isEmpty() || nom.isEmpty()) {
            vmu.advertencias("Cédula y nombre son obligatorios.");
            return;
        }

        persona = new Persona();
        persona.setCedula(ced);
        persona.setNombre(nom);
        persona.setApellido(apl);
        persona.setNumero(num);
        persona.setEmail(eml);
        persona.setPassword(pass);

        rol = (String) cmbRol.getSelectedItem();

        if (rol.equals("Repartidor")) {
            placa    = JOptionPane.showInputDialog(this, "Ingrese placa del vehículo:");
            vehiculo = JOptionPane.showInputDialog(this, "Ingrese tipo de vehículo (Moto/Auto):");
            if (placa == null || placa.trim().isEmpty()) {
                vmu.advertencias("La placa es obligatoria.");
                return;
            }
        }

        vmu.informacion("Personal listo para guardar.");
        base.cambiarVista("SUPERVISOR");
    }

    public Persona getPersona()   { return persona; }
    public String getRol()        { return rol; }
    public String getPlaca()      { return placa; }
    public String getVehiculo()   { return vehiculo; }

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

    private JPanel crearCampoCombo(String etiqueta, JComboBox<?> combo) {
        JPanel fila = new JPanel(new BorderLayout(10, 5));
        fila.setBackground(new Color(21, 44, 71));
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(Color.WHITE);
        lbl.setPreferredSize(new Dimension(110, 30));
        fila.add(lbl, BorderLayout.WEST);
        fila.add(combo, BorderLayout.CENTER);
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