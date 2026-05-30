package Clases;

import Clases.Cliente;
import Clases.Paquete;
import Clases.Persona;
import Clases.Ubicacion;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-30T00:54:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Recepcionista.class)
public class Recepcionista_ { 

    public static volatile CollectionAttribute<Recepcionista, Paquete> paqueteCollection;
    public static volatile SingularAttribute<Recepcionista, Persona> cedula;
    public static volatile SingularAttribute<Recepcionista, Integer> idRecepcionista;
    public static volatile SingularAttribute<Recepcionista, String> turno;
    public static volatile SingularAttribute<Recepcionista, Ubicacion> local;
    public static volatile CollectionAttribute<Recepcionista, Cliente> clienteCollection;

}