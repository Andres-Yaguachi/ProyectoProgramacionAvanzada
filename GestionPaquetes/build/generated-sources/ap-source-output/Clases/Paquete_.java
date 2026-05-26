package Clases;

import Clases.Cliente;
import Clases.Despacha;
import Clases.Entrega;
import Clases.Factura;
import Clases.HistorialEstado;
import Clases.Recepcionista;
import Clases.Tarifa;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, String> estado;
    public static volatile CollectionAttribute<Paquete, Despacha> despachaCollection;
    public static volatile SingularAttribute<Paquete, BigDecimal> peso;
    public static volatile SingularAttribute<Paquete, Date> fechaHora;
    public static volatile CollectionAttribute<Paquete, HistorialEstado> historialEstadoCollection;
    public static volatile SingularAttribute<Paquete, String> destinatarioTel;
    public static volatile CollectionAttribute<Paquete, Entrega> entregaCollection;
    public static volatile SingularAttribute<Paquete, Recepcionista> idRecepcionista;
    public static volatile SingularAttribute<Paquete, String> destinatarioNomb;
    public static volatile SingularAttribute<Paquete, Integer> codigoUnico;
    public static volatile CollectionAttribute<Paquete, Factura> facturaCollection;
    public static volatile SingularAttribute<Paquete, String> direccionEntrega;
    public static volatile SingularAttribute<Paquete, Cliente> idCliente;
    public static volatile SingularAttribute<Paquete, String> ciudadEnvio;
    public static volatile SingularAttribute<Paquete, Tarifa> idTarifa;
    public static volatile SingularAttribute<Paquete, String> ciudadDestino;
    public static volatile SingularAttribute<Paquete, String> nroSeguimiento;
    public static volatile SingularAttribute<Paquete, String> tipoEnvio;

}