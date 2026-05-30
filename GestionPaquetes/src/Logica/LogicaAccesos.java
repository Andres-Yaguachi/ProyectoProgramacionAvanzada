package Logica;

import Clases.*;
import LogicaControllers.*;
import java.util.List;

public class LogicaAccesos {

    RecepcionistaJpaController objRecContr;
    OperadorDespachoJpaController objDesContr;
    RepartidorJpaController objRepContr;
    SupervisorJpaController objSuperContr;
    PersonaJpaController objPerContr;

    public LogicaAccesos() {
        this.objRecContr = new RecepcionistaJpaController();
        this.objDesContr = new OperadorDespachoJpaController();
        this.objRepContr = new RepartidorJpaController();
        this.objPerContr = new PersonaJpaController();
        this.objSuperContr = new SupervisorJpaController();
    }

    public Object cliente(String ci, String pass, int tipo) {
        Persona trabajador;
        Repartidor repartidor;
        OperadorDespacho operador;
        Recepcionista recepcionista;
        Supervisor supervisor;
        trabajador = objPerContr.findPersona(ci);

        if (!trabajador.getPassword().equals(pass)) {
            return null; // Si no coinciden, retornamos null
        }
        switch (tipo) {
            case 1:
                repartidor = repartidorAsignado(trabajador);
                return repartidor;
            case 2:
                recepcionista = recepcionistaAsignado(trabajador);
                return recepcionista;
            case 3:
                operador = operadorDes(trabajador);
                return operador;
            case 4:
                supervisor = supervisorAsignado(trabajador);
                return supervisor;
            default:
                return null;
        }
    }

    public Repartidor repartidorAsignado(Persona trab) {
        Repartidor objRepartidor;
        List<Repartidor> repartidores = objRepContr.findRepartidorEntities();
        for (Repartidor rep : repartidores) {
            if (rep.getCedula().getCedula().equals(trab.getCedula())) {
                objRepartidor = rep;
                return rep;
            }
        }
        return null;
    }

    public OperadorDespacho operadorDes(Persona trab) {
        List<OperadorDespacho> operadores = objDesContr.findOperadorDespachoEntities();
        for (OperadorDespacho ops : operadores) {
            if (ops.getCedula().getCedula().equals(trab.getCedula())) {
                return ops;
            }
        }
        return null;

    }

    public Recepcionista recepcionistaAsignado(Persona trab) {
        List<Recepcionista> recepcionistas = objRecContr.findRecepcionistaEntities();
        for (Recepcionista rep : recepcionistas) {
            if (rep.getCedula().getCedula().equals(trab.getCedula())) {
                return rep;
            }
        }
        return null;
    }

    public Supervisor supervisorAsignado(Persona trab) {
        List<Supervisor> supervisores = objSuperContr.findSupervisorEntities();
        for (Supervisor sup : supervisores) {
            if (sup.getCedula().getCedula().equals(trab.getCedula())) {
                return sup;
            }
        }
        return null;
    }

}
