package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Producto;
import modelo.TipoProducto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile SingularAttribute<Marca, Integer> codigo;
    public static volatile ListAttribute<Marca, TipoProducto> tiposProductos;
    public static volatile ListAttribute<Marca, Producto> producto;
    public static volatile SingularAttribute<Marca, String> nombre;

}