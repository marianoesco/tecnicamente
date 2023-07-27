package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.ServicioTecnico;
import modelo.TipoPago;
import modelo.Venta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:39")
@StaticMetamodel(Pago.class)
public class Pago_ { 

    public static volatile SingularAttribute<Pago, Integer> codigo;
    public static volatile SingularAttribute<Pago, Float> precio;
    public static volatile SingularAttribute<Pago, Venta> venta;
    public static volatile SingularAttribute<Pago, ServicioTecnico> serviciotecnico;
    public static volatile SingularAttribute<Pago, TipoPago> tipopago;

}