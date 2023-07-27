package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.DetalleCompra;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:39")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Date> fecha;
    public static volatile SingularAttribute<Compra, Integer> codigo;
    public static volatile SingularAttribute<Compra, Float> precio;
    public static volatile ListAttribute<Compra, DetalleCompra> detalleCompra;
    public static volatile SingularAttribute<Compra, Usuario> usuario;

}