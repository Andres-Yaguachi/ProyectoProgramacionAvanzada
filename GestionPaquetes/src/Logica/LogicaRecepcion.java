package Logica;

import LogicaControllers.*;
import Clases.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class LogicaRecepcion {

    //Controladores
    ClienteJpaController objCliContr;
    FacturaJpaController objFacContr;
    HistorialEstadoJpaController objHEContr;
    PersonaJpaController objPerContr;
    PaqueteJpaController objPaqContr;
    TarifaJpaController objTarContr;
    UbicacionJpaController UbiContr;
    RecepcionistaJpaController objRecContr;

    //Clases
    public LogicaRecepcion() {
        this.objCliContr = new ClienteJpaController();
        this.objFacContr = new FacturaJpaController();
        this.objHEContr = new HistorialEstadoJpaController();
        this.objPerContr = new PersonaJpaController();
        this.objPaqContr = new PaqueteJpaController();
        this.objTarContr = new TarifaJpaController();
        this.UbiContr = new UbicacionJpaController();
        this.objRecContr = new RecepcionistaJpaController();
    }

    public String generadorCDUnico() {
        Date anio = new Date();
        StringBuilder str = new StringBuilder();

        int ultimo = objPaqContr.getPaqueteCount();

        str.append("LOH-");
        str.append(anio.getYear() + 1900);
        str.append("-");
        str.append(ultimo + 1);

        return str.toString();
    }

    public void registrarCliente(Persona persona, Cliente cliente, Recepcionista rec) throws Exception {
        if (objPerContr.findPersona(persona.getCedula()) == null) {
            objPerContr.create(persona);
        } else {

            persona = objPerContr.findPersona(persona.getCedula());
        }

        cliente.setCedula(persona);
        cliente.setIdRecepcionista(rec);

        objCliContr.create(cliente);
    }

    public void registrarPaquete(Paquete paquete, Recepcionista recep, Cliente cliente, Tarifa tar, String observaciones) throws Exception {

<<<<<<< Updated upstream
    }

    public void registrarPaquete(BigDecimal peso, String tipo_envio,
            String ciudad_envio, String direccion_entrega, String ciudad_destino,
            int idRecepcionista,
            int idCliente, int idTarifa, String destinatarioNomb, String destinatarioTel, String observaciones) {
        Paquete paquete = new Paquete();
        Recepcionista recep = objRecContr.findRecepcionista(idRecepcionista);
        Cliente cliente = objCliContr.findCliente(idCliente);
        Tarifa tar = objTarContr.findTarifa(idTarifa);
        paquete.setPeso(peso);
        paquete.setTipoEnvio(tipo_envio);
        paquete.setEstado("Registrado");
        paquete.setCiudadEnvio(ciudad_envio);
        paquete.setDireccionEntrega(direccion_entrega);
        paquete.setCiudadDestino(ciudad_destino);
        paquete.setNroSeguimiento(generadorCDUnico());
=======
        // Asignamos los valores lógicos internos obligatorios
        paquete.setNroSeguimiento(generadorNroSeguimiento());
>>>>>>> Stashed changes
        paquete.setFechaHora(new Date());
        paquete.setEstado("Receptado");
        paquete.setIdRecepcionista(recep);
        paquete.setIdCliente(cliente);
        paquete.setIdTarifa(tar);
<<<<<<< Updated upstream
        paquete.setDestinatarioNomb(destinatarioNomb);
        paquete.setDestinatarioTel(destinatarioTel);
        
        objPaqContr.create(paquete);
        
        //Cambiar el Historial 
        cambiarHistorial(observaciones, paquete, UbiContr.findUbicacion(recep.getLocal().getIdLocal()) );
=======
        objPaqContr.create(paquete);
        //Logica de Precio para la Factura
        BigDecimal precioXpeso = paquete.getPeso().multiply(tar.getKgExtra());
        BigDecimal precioFin = tar.getPrecioBase().add(precioXpeso);
        generarFactura(paquete, precioFin);
        cambiarHistorial(observaciones, paquete, UbiContr.findUbicacion(recep.getLocal().getIdLocal()));
>>>>>>> Stashed changes
    }

    public void cambiarHistorial(String observaciones, Paquete p, Ubicacion u) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEstado(p.getEstado());
        historial.setFechaHora(new Date());
        if (observaciones == null || observaciones.trim().isEmpty() || observaciones.equals(" ")) {
            historial.setObservaciones("Paquete " + p.getEstado() + " en: " + u.getNombre());
        } else {
            historial.setObservaciones(observaciones);
        }
        historial.setCodigoUnico(p);
        historial.setUbicacion(u);
        
        
        objHEContr.create(historial);
        
    }
    
    public void recepcion(){
        
    }

    public void generarFactura(Paquete paqueteGuardado, BigDecimal subtotal) throws Exception {
        Factura nuevaFactura = new Factura();
        BigDecimal ivaFijo = new BigDecimal("0.15");
        String nroFactura = "FAC-" + (System.currentTimeMillis() % 100000);

        nuevaFactura.setFecha(new java.util.Date());
        BigDecimal iva = subtotal.multiply(ivaFijo);
        BigDecimal total = subtotal.add(iva);

        iva = iva.setScale(2, RoundingMode.HALF_UP);
        total = total.setScale(2, RoundingMode.HALF_UP);
        subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
        // Redondear a 2 decimales para que se vea bien en la BD y en la vista
        nuevaFactura.setSubtotal(subtotal);
        nuevaFactura.setIva(iva);
        nuevaFactura.setTotal(total);

        nuevaFactura.setPaquetecodigounico(paqueteGuardado);
        objFacContr.create(nuevaFactura);
    }

    public Cliente obtenerCliente(int idCliente) {
        return objCliContr.findCliente(idCliente);
    }

    public Tarifa obtenerTarifa(int idTarifa) {
        return objTarContr.findTarifa(idTarifa);
    }

    public Cliente obtenerClientePorCedula(String cedula) {
        List<Cliente> clientes = objCliContr.findClienteEntities();
        for (Cliente c : clientes) {
            if (c.getCedula().getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public java.util.List<Tarifa> obtenerTodasLasTarifas() {
        return objTarContr.findTarifaEntities();
    }

}
