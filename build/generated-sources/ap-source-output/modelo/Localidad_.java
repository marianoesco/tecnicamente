package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cliente;
import modelo.Provincia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(Localidad.class)
public class Localidad_ { 

    public static volatile SingularAttribute<Localidad, Integer> codigo;
    public static volatile SingularAttribute<Localidad, Provincia> provincia;
    public static volatile ListAttribute<Localidad, Cliente> clientes;
    public static volatile SingularAttribute<Localidad, String> nombre;

}