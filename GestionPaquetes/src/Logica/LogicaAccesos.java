package Logica;

import Clases.*;
import LogicaControllers.*;
import java.util.List;

public class LogicaAccesos {

    RecepcionistaJpaController objRecContr;
    DespachaJpaController objDesContr;
    RepartidorJpaController objRepContr;
    PersonaJpaController objPerContr;

    public LogicaAccesos() {
        this.objRecContr = new RecepcionistaJpaController();
        this.objDesContr = new DespachaJpaController();
        this.objRepContr = new RepartidorJpaController();
        this.objPerContr = new PersonaJpaController();
    }

    public Object cliente(String ci) {
        Persona trabajador;
        trabajador = objPerContr.findPersona(ci);
        if (trabajador == null) {
            return null;
        }
        return trabajador;
    }
    
        public Repartidor RepartidorAsignado(String ci) {
        Persona objPersona = objPerContr.findPersona(ci);
        List<Repartidor> repartidores = objREPContr.findRepartidorEntities();
        for (Repartidor rep : repartidores) {
            if (rep.getCedula().equals(objPersona)) {
                objRepartidor = rep;
                return rep;
            }
        }
        return null;
    }
        public OperadorDespacho OperadorDes(String ci) {
        objPerContr = objPerContr.findPersona(ci);
        List<OperadorDespacho> operadores = objOperContr.findOperadorDespachoEntities();
        for (OperadorDespacho ops : operadores) {
            if (ops.getCedula().equals(objPerContr)) {
               objOperador = ops;
               return ops;
            }
        }
        return null;
        
    }


}
