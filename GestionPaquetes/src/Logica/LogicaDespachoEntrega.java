package Logica;

import LogicaControllers.*;
import Clases.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class LogicaDespachoEntrega {

    PaqueteJpaController objPaqContr;
    HistorialEstadoJpaController objHEContr;
    OperadorDespachoJpaController objOperContr;
    PersonaJpaController objPerContr;
    UbicacionJpaController UbiContr;
    DespachaJpaController despContr;
    AsignaPaqueteJpaController objAsignaPaqContr;
    RepartidorJpaController objRepContr;

    public LogicaDespachoEntrega() {
        objPaqContr = new PaqueteJpaController();
        objHEContr = new HistorialEstadoJpaController();
        objPerContr = new PersonaJpaController();
        UbiContr = new UbicacionJpaController();
        objOperContr = new OperadorDespachoJpaController();
        despContr = new DespachaJpaController();
        objAsignaPaqContr = new AsignaPaqueteJpaController();
        objRepContr = new RepartidorJpaController();
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

    public void registrarSalida(Paquete paquete, OperadorDespacho operador) throws Exception {
        Paquete p = buscarPaquete(paquete.getNroSeguimiento());
        Despacha despacha = new Despacha();
        DespachaPK desPk = new DespachaPK();
        Repartidor asignado;
        if (p != null) {
            p.setEstado("En Transito");
            objPaqContr.edit(p); // Actualiza en la BD                     
            asignado = asignarRepartidor(p);

            cambiarHistorial("Salida de bodega registrada", p, operador.getBodega());
            despacha.setFechaHora(new Date());
            despacha.setOperadorDespacho(operador);
            despacha.setPaquete(p);
            desPk.setCodigoUnico(p.getCodigoUnico());
            desPk.setIdOperador(operador.getIdOperador());
            despacha.setDespachaPK(desPk);

            despContr.create(despacha);
        } else {
            throw new Exception("El paquete con código " + paquete.getNroSeguimiento() + " no existe.");
        }
    }

    private Repartidor asignarRepartidor(Paquete paquete) throws Exception {
        List<Repartidor> repartidores = objRepContr.findRepartidorEntities();
        List<AsignaPaquete> asignaciones = objAsignaPaqContr.findAsignaPaqueteEntities();
        List<Repartidor> candidatos = new ArrayList<>();
        Repartidor repartidorGanador;
        int minPedidos = Integer.MAX_VALUE;

        for (Repartidor rep : repartidores) {
            int pedidosPendientes = 0;
            for (AsignaPaquete ap : asignaciones) {
                if (ap.getRepartidor().getIdRepartidor().equals(rep.getIdRepartidor())
                        && !ap.getEstado().equals("Entregado")) {
                    pedidosPendientes++;
                }
            }
            // Aplicamos criterio de selección de mínimos
            if (pedidosPendientes < minPedidos) {
                minPedidos = pedidosPendientes;
                candidatos.clear(); // Encontramos un reapartidor con menos entregas
                candidatos.add(rep);
            } else if (pedidosPendientes == minPedidos) {
                candidatos.add(rep); //candidatos iguales
            }
        }

        if (candidatos.size() == 1) {
            repartidorGanador = candidatos.get(0);
        } else {
            Random sorteo = new Random();
            int indiceElegido = sorteo.nextInt(candidatos.size());
            repartidorGanador = candidatos.get(indiceElegido);
        }

        AsignaPaquete objAsiPaq = new AsignaPaquete();
        objAsiPaq.setPaquete(paquete);
        objAsiPaq.setRepartidor(repartidorGanador);
        objAsiPaq.setEstado("Asignado");
        objAsiPaq.setFechaAsignada(new Date());

        objAsignaPaqContr.create(objAsiPaq);
        return repartidorGanador;
    }

    public void cambiarHistorial(String observaciones, Paquete p, Ubicacion u) {
        HistorialEstado historial = new HistorialEstado();
        historial.setEstado(p.getEstado());
        historial.setFechaHora(new Date());
        if (observaciones.equals(" ")) {
            historial.setObservaciones("Paquete " + p.getEstado() + " en: " + u.getNombre());
        } else {
            historial.setObservaciones(observaciones);
        }
        historial.setCodigoUnico(p);
        historial.setUbicacion(u);
        objHEContr.create(historial);

    }
}
