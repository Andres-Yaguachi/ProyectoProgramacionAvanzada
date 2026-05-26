package Clases;

import Clases.Cliente;
import Clases.OperadorDespacho;
import Clases.Recepcionista;
import Clases.Repartidor;
import Clases.Supervisor;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-05-26T07:17:39", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> password;
    public static volatile SingularAttribute<Persona, String> numero;
    public static volatile CollectionAttribute<Persona, Repartidor> repartidorCollection;
    public static volatile SingularAttribute<Persona, String> cedula;
    public static volatile CollectionAttribute<Persona, Recepcionista> recepcionistaCollection;
    public static volatile SingularAttribute<Persona, String> apellido;
    public static volatile CollectionAttribute<Persona, OperadorDespacho> operadorDespachoCollection;
    public static volatile CollectionAttribute<Persona, Supervisor> supervisorCollection;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> email;
    public static volatile CollectionAttribute<Persona, Cliente> clienteCollection;

}