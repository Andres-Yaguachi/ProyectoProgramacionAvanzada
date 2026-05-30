package Presentacion;

import Clases.*;
import Logica.*;
import java.awt.*;
import javax.swing.*;

public class VistaLogin extends JPanel {

    private JTextField txtCedula;
    private JPasswordField txtPassword;

    private LogicaAccesos logica = new LogicaAccesos();

    public VistaLogin(VistaPrincipal base) {
        setLayout(new BorderLayout());
        setBackground(new Color(21, 44, 71));

        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(15, 30, 50));
        header.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));
        JLabel titulo = new JLabel("Acceso Personal");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo);

        // Formulario
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(21, 44, 71));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtCedula = new JTextField(20);
        txtPassword = new JPasswordField(20);
        estilizarCampo(txtCedula);
        estilizarCampo(txtPassword);

        gbc.gridx = 0; gbc.gridy = 0;
        form.add(crearLabel("Cédula:"), gbc);
        gbc.gridx = 1;
        form.add(txtCedula, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        form.add(crearLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        form.add(txtPassword, gbc);

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        botones.setBackground(new Color(21, 44, 71));

        JButton btnIngresar = crearBoton("Ingresar", new Color(46, 134, 222));
        JButton btnVolver = crearBoton("Volver", new Color(100, 100, 100));

        btnVolver.addActionListener(e -> base.cambiarVista("OPCION"));
        btnIngresar.addActionListener(e -> {
<<<<<<< Updated upstream
            String cedula = txtCedula.getText().trim();
            String pass = new String(txtPassword.getPassword()).trim();
            // TODO: conectar con lógica de negocio
            if (cedula.equals("1103456789")) {
                base.cambiarVista("SUPERVISOR");
            } else if (cedula.equals("1103456790")) {
                base.cambiarVista("RECEPCIONISTA");
            } else if (cedula.equals("1103456791")) {
                base.cambiarVista("OPERADOR");
            } else if (cedula.equals("1103456792")) {
                base.cambiarVista("REPARTIDOR");
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
=======
            String destino = base.getRolElegido();

            if (destino == null || destino.isEmpty()) {
                vmu.error("No se seleccionó un tipo de personal.");
                return;
            }

            // Capturar datos del formulario
            String cedula = txtCedula.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (cedula.isEmpty() || password.isEmpty()) {
                vmu.error("Por favor, ingrese su cédula y contraseña.");
                return;
            }
            int tipo = 0;
            switch (destino) {
                case "REPARTIDOR":
                    tipo = 1;
                    break;
                case "RECEPCIONISTA":
                    tipo = 2;
                    break;
                case "OPERADOR":
                    tipo = 3;
                    break;
                case "SUPERVISOR":
                    tipo = 4;
                    break;
            }

            Object usuarioLogueado = logica.cliente(cedula, password, tipo);

            //Validamos y guardamos la sesión según el rol correspondiente
            if (usuarioLogueado != null) {

                switch (destino) {
                    case "REPARTIDOR":
                        base.setRepartidorLogueado((Repartidor) usuarioLogueado);
                        break;
                    case "RECEPCIONISTA":
                        base.setRecepcionistaLogueado((Recepcionista) usuarioLogueado);
                        break;
                    case "OPERADOR":
                        base.setOperadorLogueado((OperadorDespacho) usuarioLogueado);
                        break;
                    case "SUPERVISOR":
                        base.setSupervisorLogueado((Supervisor) usuarioLogueado);
                        break;
                }

                base.cambiarVista(destino);

                txtCedula.setText("");
                txtPassword.setText("");

            } else {
                vmu.error("Credenciales incorrectas o el usuario no corresponde a este rol.");
>>>>>>> Stashed changes
            }
        });

        botones.add(btnIngresar);
        botones.add(btnVolver);

        add(header, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBackground(new Color(40, 70, 110));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(46, 134, 222)),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(130, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
