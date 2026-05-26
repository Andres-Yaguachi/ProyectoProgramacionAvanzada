package Clases;

import Clases.EntregaPK;
import Clases.Paquete;
import Clases.Repartidor;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Entrega.class)
public class Entrega_ { 

    public static volatile SingularAttribute<Entrega, Repartidor> repartidor;
    public static volatile SingularAttribute<Entrega, String> nombreRec;
    public static volatile SingularAttribute<Entrega, Date> fechaHora;
    public static volatile SingularAttribute<Entrega, EntregaPK> entregaPK;
    public static volatile SingularAttribute<Entrega, String> observaciones;
    public static volatile SingularAttribute<Entrega, Paquete> paquete;

}