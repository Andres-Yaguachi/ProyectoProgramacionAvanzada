package Logica;

import LogicaControllers.*;
import Clases.*;
import LogicaControllers.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;

public class LogicaDespachoEntrega {

    PaqueteJpaController objPaqContr;
    HistorialEstadoJpaController objHEContr;
    OperadorDespachoJpaController objOperContr;
    PersonaJpaController objPerContr;
    UbicacionJpaController UbiContr;
    OperadorDespacho objOperador;
    Persona objPersona;
    Ubicacion objUbi;

    public LogicaDespachoEntrega(String ci) {
        objPaqContr = new PaqueteJpaController();
        objHEContr = new HistorialEstadoJpaController();
        objPerContr = new PersonaJpaController();
        UbiContr = new UbicacionJpaController();
        objOperContr = new OperadorDespachoJpaController();
        objOperador = OperadorDes(ci);

    }

    public OperadorDespacho OperadorDes(String ci) {
        objPersona = objPerContr.findPersona(ci);
        List<OperadorDespacho> operadores = objOperContr.findOperadorDespachoEntities();
        for (OperadorDespacho ops : operadores) {
            if (ops.getCedula().equals(objPersona)) {
               objOperador = ops;
               return ops;
            }
        }
        return null;
        
    }

    public Paquete buscarPaquete(String codigo) {
        List<Paquete> paquetes = objPaqContr.findPaqueteEntities();
        for (Paquete p : paquetes) {
            if (p.getNroSeguimiento().equals(codigo)) {
                return p;//Paquete entregado
            }
        }
        return null;
    }

    public String registrarSalida(String codigo) throws NonexistentEntityException, Exception {
        Paquete p = buscarPaquete(codigo);
        if (p != null) {
            p.setEstado("En Transito");//Estado Cambiado a entregado
            objPaqContr.edit(p);//Estado cambiado en la DB
            cambiarHistorial(" ", p,objOperador.getBodega());
        }
        return (" ");
    }

    public void cambiarHistorial(String observaciones, Paquete p, Ubicacion u) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEstado(p.getEstado());
        historial.setFechaHora(new Date());
        if (observaciones.equals(" ")) { // Acordaraste de colocar un espacio si queda vacio este valor!!!
            historial.setObservaciones("Paquete " + p.getEstado() + " en: " + u.getNombre());
        } else {
            historial.setObservaciones(observaciones);
        }
        historial.setCodigoUnico(p);
        historial.setUbicacion(u);

        objHEContr.create(historial);

    }
}
