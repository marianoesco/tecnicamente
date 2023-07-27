package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Pago;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(TipoPago.class)
public class TipoPago_ { 

    public static volatile SingularAttribute<TipoPago, Integer> codigo;
    public static volatile SingularAttribute<TipoPago, String> nombre;
    public static volatile ListAttribute<TipoPago, Pago> pago;

}