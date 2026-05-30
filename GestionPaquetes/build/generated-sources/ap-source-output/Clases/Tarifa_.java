package Clases;

import Clases.Paquete;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-30T00:54:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tarifa.class)
public class Tarifa_ { 

    public static volatile SingularAttribute<Tarifa, String> descripcion;
    public static volatile CollectionAttribute<Tarifa, Paquete> paqueteCollection;
    public static volatile SingularAttribute<Tarifa, BigDecimal> precioBase;
    public static volatile SingularAttribute<Tarifa, BigDecimal> kgExtra;
    public static volatile SingularAttribute<Tarifa, Integer> idTarifa;

}