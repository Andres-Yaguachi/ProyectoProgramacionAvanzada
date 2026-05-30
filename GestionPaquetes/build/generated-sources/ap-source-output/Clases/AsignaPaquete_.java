package Clases;

import Clases.Paquete;
import Clases.Repartidor;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-30T00:54:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(AsignaPaquete.class)
public class AsignaPaquete_ { 

    public static volatile SingularAttribute<AsignaPaquete, String> estado;
    public static volatile SingularAttribute<AsignaPaquete, Repartidor> repartidor;
    public static volatile SingularAttribute<AsignaPaquete, Date> fechaAsignada;
    public static volatile SingularAttribute<AsignaPaquete, Integer> idAsignaPaquete;
    public static volatile SingularAttribute<AsignaPaquete, Paquete> paquete;

}