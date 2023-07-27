package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Producto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(TipoProducto.class)
public class TipoProducto_ { 

    public static volatile SingularAttribute<TipoProducto, Integer> codigo;
    public static volatile ListAttribute<TipoProducto, Producto> producto;
    public static volatile SingularAttribute<TipoProducto, String> nombre;

}