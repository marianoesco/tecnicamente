package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Producto;
import modelo.ServicioTecnico;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(ProductoIncluido.class)
public class ProductoIncluido_ { 

    public static volatile SingularAttribute<ProductoIncluido, String> descripcion;
    public static volatile SingularAttribute<ProductoIncluido, Integer> codigo;
    public static volatile SingularAttribute<ProductoIncluido, Float> precio;
    public static volatile SingularAttribute<ProductoIncluido, ServicioTecnico> servicioTecnico;
    public static volatile SingularAttribute<ProductoIncluido, Float> subtotal;
    public static volatile SingularAttribute<ProductoIncluido, Integer> cantidad;
    public static volatile SingularAttribute<ProductoIncluido, Producto> producto;
    public static volatile SingularAttribute<ProductoIncluido, String> nombreProducto;

}