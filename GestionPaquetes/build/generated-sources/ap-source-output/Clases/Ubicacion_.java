package Clases;

import Clases.HistorialEstado;
import Clases.OperadorDespacho;
import Clases.Recepcionista;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Ubicacion.class)
public class Ubicacion_ { 

    public static volatile SingularAttribute<Ubicacion, String> tipo;
    public static volatile SingularAttribute<Ubicacion, Integer> idLocal;
    public static volatile SingularAttribute<Ubicacion, String> ciudad;
    public static volatile CollectionAttribute<Ubicacion, Recepcionista> recepcionistaCollection;
    public static volatile CollectionAttribute<Ubicacion, HistorialEstado> historialEstadoCollection;
    public static volatile SingularAttribute<Ubicacion, String> direccion;
    public static volatile CollectionAttribute<Ubicacion, OperadorDespacho> operadorDespachoCollection;
    public static volatile SingularAttribute<Ubicacion, String> nombre;

}