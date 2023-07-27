package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cliente;
import modelo.DetalleVenta;
import modelo.Pago;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Date> fecha;
    public static volatile SingularAttribute<Venta, Cliente> cliente;
    public static volatile SingularAttribute<Venta, Integer> codigo;
    public static volatile SingularAttribute<Venta, Float> precio;
    public static volatile SingularAttribute<Venta, Usuario> usuario;
    public static volatile ListAttribute<Venta, DetalleVenta> detalleVenta;
    public static volatile ListAttribute<Venta, Pago> pago;
    public static volatile SingularAttribute<Venta, String> observacion;

}