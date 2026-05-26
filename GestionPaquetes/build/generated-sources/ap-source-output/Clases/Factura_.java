package Clases;

import Clases.Paquete;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fecha;
    public static volatile SingularAttribute<Factura, BigDecimal> total;
    public static volatile SingularAttribute<Factura, Paquete> paquetecodigounico;
    public static volatile SingularAttribute<Factura, String> nroFactura;
    public static volatile SingularAttribute<Factura, BigDecimal> iva;
    public static volatile SingularAttribute<Factura, BigDecimal> subtotal;
    public static volatile SingularAttribute<Factura, Integer> idFactura;

}