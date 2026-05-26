package Clases;

import Clases.Entrega;
import Clases.Persona;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Repartidor.class)
public class Repartidor_ { 

    public static volatile SingularAttribute<Repartidor, String> estado;
    public static volatile SingularAttribute<Repartidor, Persona> cedula;
    public static volatile SingularAttribute<Repartidor, Integer> idRepartidor;
    public static volatile CollectionAttribute<Repartidor, Entrega> entregaCollection;
    public static volatile SingularAttribute<Repartidor, String> vehiculo;
    public static volatile SingularAttribute<Repartidor, String> placa;

}