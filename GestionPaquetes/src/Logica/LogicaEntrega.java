package Logica;

import Clases.*;
import LogicaControllers.*;
import java.util.Date;
import java.util.List;

public class LogicaEntrega {

    PaqueteJpaController objPaqContr;
    HistorialEstadoJpaController objHEContr;
    PersonaJpaController objPerContr;
    RepartidorJpaController objREPContr;
    EntregaJpaController objEntContr;
    UbicacionJpaController objUbiContr;
    Repartidor objRepartidor;
    Persona objPersona;
    EntregaPK objEntregaPK;

    public LogicaEntrega(Repartidor ci) {
        objPaqContr = new PaqueteJpaController();
        objHEContr = new HistorialEstadoJpaController();
        objPerContr = new PersonaJpaController();
        objREPContr = new RepartidorJpaController();
        objEntContr = new EntregaJpaController();
        objUbiContr = new UbicacionJpaController();
        objRepartidor = (ci);

    }

    public Ubicacion obtenerUbicacion(Paquete p) {
        Ubicacion ubi = new Ubicacion();
        ubi = objUbiContr.findUbicacion(4);
        return ubi;

    }

    public void llenarEntrega(Entrega ent, Paquete p) throws Exception {
        Entrega objEntrega = ent;
        Paquete objPaquete;
        ent.getPaquete();
        objPaquete = objPaqContr.findPaquetexNroSeguimiento(p.getNroSeguimiento());
        if(objPaquete.getEstado().equals("En Transito")){
             objEntContr.create(objEntrega);
        }else {
            String aviso = "no se puede entregar un paquete que no esta en transito";
            return;
        }

       

    }

    public void cambiarHistorial(String observaciones, Paquete p, String receptor) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEstado(p.getEstado());
        historial.setFechaHora(new Date());
        if (observaciones.equals(" ")) {
            historial.setObservaciones("Paquete Entregado Correctamente a " + receptor);
        } else {
            historial.setObservaciones(observaciones);
        }
        historial.setCodigoUnico(p);
        historial.setUbicacion(obtenerUbicacion(p));

        objHEContr.create(historial);

    }

}
