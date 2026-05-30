package Presentacion;

import Clases.*;
import java.awt.*;
import javax.swing.*;

public class VistaPrincipal extends JFrame {

    private CardLayout layout;
    private JPanel contenedor;
<<<<<<< Updated upstream
=======
    private VistaListarPaquetes vistaListarPaquetes;
    private VistaHistorialEstado vistaHistorialEstado;
    private VistaPaquetesAsignados vistaPaquetesAsignados;
    private VistaBuscarPaquete vistaBuscarPaquete;
    private String rolElegido;
    private Recepcionista recepcionistaLogueado;
    private OperadorDespacho operadorLogueado;
    private Repartidor repartidorLogueado;
    private Supervisor supervisorLogueado;
>>>>>>> Stashed changes

    public VistaPrincipal() {
        setTitle("Sistema de Gestión de Paquetes");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        layout = new CardLayout();
        contenedor = new JPanel(layout);

<<<<<<< Updated upstream
=======
        vistaListarPaquetes = new VistaListarPaquetes(this);
        vistaHistorialEstado = new VistaHistorialEstado(this);
        vistaPaquetesAsignados = new VistaPaquetesAsignados(this);
        vistaBuscarPaquete = new VistaBuscarPaquete(this);

        //  inico, opcion, cliente, login
>>>>>>> Stashed changes
        contenedor.add(new VistaInicio(this), "INICIO");
        contenedor.add(new VistaOpcionUsuario(this), "OPCION");
        contenedor.add(new VistaCliente(this), "CLIENTE");
        contenedor.add(new VistaSeleccionPersonal(this), "SELECCION_PERSONAL");
        contenedor.add(new VistaRecepcionista(this), "RECEPCIONISTA");
        contenedor.add(new VistaOperador(this), "OPERADOR");
        contenedor.add(new VistaRepartidor(this), "REPARTIDOR");
        contenedor.add(new VistaSupervisor(this), "SUPERVISOR");
<<<<<<< Updated upstream
=======
        contenedor.add(new VistaAgregarPersonal(this), "AGREGAR_PERSONAL");
        contenedor.add(vistaListarPaquetes, "LISTAR_PAQUETES");
        contenedor.add(vistaHistorialEstado, "HISTORIAL_ESTADO");
        contenedor.add(new VistaRegistrarCliente(this), "REGISTRAR_CLIENTE");
        contenedor.add(new VistaRegistrarPaquete(this), "REGISTRAR_PAQUETE");
        contenedor.add(vistaPaquetesAsignados, "PAQUETES_ASIGNADOS");
        contenedor.add(new VistaRegistrarEntrega(this), "REGISTRAR_ENTREGA");
        contenedor.add(vistaBuscarPaquete, "BUSCAR_PAQUETE");
        contenedor.add(new VistaRegistrarSalida(this), "REGISTRAR_SALIDA");
        contenedor.add(new VistaLogin(this), "LOGIN");
>>>>>>> Stashed changes

        cambiarVista("INICIO");
        add(contenedor);
        setVisible(true);
    }

    public void cambiarVista(String clave) {
        layout.show(contenedor, clave);
    }
<<<<<<< Updated upstream
=======

    public VistaListarPaquetes getVistaListarPaquetes() {
        return vistaListarPaquetes;
    }

    public VistaHistorialEstado getVistaHistorialEstado() {
        return vistaHistorialEstado;
    }

    public VistaPaquetesAsignados getVistaPaquetesAsignados() {
        return vistaPaquetesAsignados;
    }

    public VistaBuscarPaquete getVistaBuscarPaquete() {
        return vistaBuscarPaquete;
    }

    public String getRolElegido() {
        return rolElegido;
    }

    public void setRolElegido(String rol) {
        this.rolElegido = rol;
    }

    public void setRecepcionistaLogueado(Recepcionista recep) {
        this.recepcionistaLogueado = recep;
    }

    public Recepcionista getRecepcionistaLogueado() {
        return this.recepcionistaLogueado;
    }

    public OperadorDespacho getOperadorLogueado() {
        return operadorLogueado;
    }

    public void setOperadorLogueado(OperadorDespacho operadorLogueado) {
        this.operadorLogueado = operadorLogueado;
    }

    public void setRepartidorLogueado(Repartidor repartidor) {
        this.repartidorLogueado = repartidor;
    }

    public Repartidor getRepartidorLogueado() {
        return this.repartidorLogueado;
    }

    public void setSupervisorLogueado(Supervisor supervisor) {
        this.supervisorLogueado = supervisor;
    }

    public Supervisor getSupervisorLogueado() {
        return this.supervisorLogueado;
    }

>>>>>>> Stashed changes
}
