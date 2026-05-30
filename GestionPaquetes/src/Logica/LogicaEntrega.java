package Logica;

import Clases.*;
import LogicaControllers.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogicaEntrega {

    PaqueteJpaController objPaqContr;
    HistorialEstadoJpaController objHEContr;
    PersonaJpaController objPerContr;
    RepartidorJpaController objREPContr;
    EntregaJpaController objEntContr;
    UbicacionJpaController objUbiContr;
    AsignaPaqueteJpaController objAsignaPaqContr;
    Repartidor objRepartidor;

    public LogicaEntrega(Repartidor repartidor) {
        objPaqContr = new PaqueteJpaController();
        objHEContr = new HistorialEstadoJpaController();
        objPerContr = new PersonaJpaController();
        objREPContr = new RepartidorJpaController();
        objEntContr = new EntregaJpaController();
        objUbiContr = new UbicacionJpaController();
        objAsignaPaqContr = new AsignaPaqueteJpaController();
        objRepartidor = repartidor;

    }

    public Ubicacion obtenerUbicacion(Paquete p) {
        Ubicacion ubi;
        ubi = objUbiContr.findUbicacion(4);
        return ubi;

    }

    public void registrarEntrega(Paquete p, String receptor, String observaciones) throws Exception {
        Entrega entrega = new Entrega();
        Paquete objPaquete;

        objPaquete = objPaqContr.findPaquetexNroSeguimiento(p.getNroSeguimiento());
        if (objPaquete == null) {
            throw new Exception("No existe ningún paquete con ese código.");
        }
        if (!objPaquete.getEstado().equals("En Transito")) {
            throw new Exception("Solo se pueden entregar paquetes que estén 'En Transito'.");
        }

        objPaquete.setEstado("Entregado");
        objPaqContr.edit(objPaquete);

        List<AsignaPaquete> asignaciones = obtenerPaquetesAsignados(objRepartidor);
        for (AsignaPaquete ap : asignaciones) {
            if (ap.getPaquete().getCodigoUnico().equals(objPaquete.getCodigoUnico())) {
                ap.setEstado("Entregado");
                objAsignaPaqContr.edit(ap);
            }
        }

        entrega.setNombreRec(receptor);
        entrega.setObservaciones(observaciones);
        entrega.setFechaHora(new Date());

        cambiarHistorial(observaciones, objPaquete, receptor);
    }

    public void cambiarHistorial(String observaciones, Paquete p, String receptor) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEstado(p.getEstado());
        historial.setFechaHora(new Date());
        if (observaciones.trim().isEmpty()) {
            historial.setObservaciones("Paquete Entregado Correctamente a " + receptor);
        } else {
            historial.setObservaciones(observaciones);
        }
        historial.setCodigoUnico(p);
        historial.setUbicacion(obtenerUbicacion(p));

        objHEContr.create(historial);

    }

    public List<AsignaPaquete> obtenerPaquetesAsignados(Repartidor objRepartidor) {
        List<AsignaPaquete> asignaciones = objAsignaPaqContr.findAsignaPaqueteEntities();
        List<AsignaPaquete> asixRepartidor = new ArrayList<>();

        for (AsignaPaquete asignacion : asignaciones) {
            if (asignacion.getRepartidor().getIdRepartidor().equals(objRepartidor.getIdRepartidor())) {
                if (!"Entregado".equals(asignacion.getEstado())) {
                    asixRepartidor.add(asignacion);
                }
            }
        }
        return asixRepartidor;
    }

}
