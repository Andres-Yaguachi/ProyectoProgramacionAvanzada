package Logica;

import Clases.*;
import LogicaControllers.*;
import java.util.ArrayList;
import java.util.List;

public class LogicaCliente {

    PaqueteJpaController objPaqContr;
    HistorialEstadoJpaController objHistEst;
    LogicaDespachoEntrega logDesEnt;

    public LogicaCliente() {
        this.objPaqContr = new PaqueteJpaController();
        this.objHistEst = new HistorialEstadoJpaController();
        this.logDesEnt = new LogicaDespachoEntrega();
    }

    public Paquete encontrarPaqueteXNro(String nroSeguimiento) {
        return logDesEnt.buscarPaquete(nroSeguimiento);
    }

    public List<HistorialEstado> traerHisotrial(Paquete objPaquete) {
        List<HistorialEstado> listaDEstados = objHistEst.findHistorialEstadoEntities();
        List<HistorialEstado> historialFiltrado = new ArrayList<>();
        for (HistorialEstado he : listaDEstados) {
            if (he.getCodigoUnico().getCodigoUnico().equals(objPaquete.getCodigoUnico())) {
                historialFiltrado.add(he);
            }
        }
        return historialFiltrado;
    }

}
