package Clases;

import Clases.Paquete;
import Clases.Ubicacion;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-30T00:54:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(HistorialEstado.class)
public class HistorialEstado_ { 

    public static volatile SingularAttribute<HistorialEstado, String> estado;
    public static volatile SingularAttribute<HistorialEstado, Ubicacion> ubicacion;
    public static volatile SingularAttribute<HistorialEstado, Date> fechaHora;
    public static volatile SingularAttribute<HistorialEstado, String> observaciones;
    public static volatile SingularAttribute<HistorialEstado, Integer> idHistorial;
    public static volatile SingularAttribute<HistorialEstado, Paquete> codigoUnico;

}