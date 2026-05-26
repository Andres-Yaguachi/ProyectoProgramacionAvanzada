package Clases;

import Clases.Despacha;
import Clases.Persona;
import Clases.Ubicacion;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(OperadorDespacho.class)
public class OperadorDespacho_ { 

    public static volatile SingularAttribute<OperadorDespacho, Integer> idOperador;
    public static volatile CollectionAttribute<OperadorDespacho, Despacha> despachaCollection;
    public static volatile SingularAttribute<OperadorDespacho, Persona> cedula;
    public static volatile SingularAttribute<OperadorDespacho, Ubicacion> bodega;

}