package Clases;

import Clases.DespachaPK;
import Clases.OperadorDespacho;
import Clases.Paquete;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-30T00:54:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Despacha.class)
public class Despacha_ { 

    public static volatile SingularAttribute<Despacha, OperadorDespacho> operadorDespacho;
    public static volatile SingularAttribute<Despacha, Date> fechaHora;
    public static volatile SingularAttribute<Despacha, DespachaPK> despachaPK;
    public static volatile SingularAttribute<Despacha, Paquete> paquete;

}