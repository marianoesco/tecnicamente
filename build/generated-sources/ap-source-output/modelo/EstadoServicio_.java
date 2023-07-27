package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.ServicioTecnico;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(EstadoServicio.class)
public class EstadoServicio_ { 

    public static volatile SingularAttribute<EstadoServicio, Integer> codigo;
    public static volatile ListAttribute<EstadoServicio, ServicioTecnico> servicioTenico;
    public static volatile SingularAttribute<EstadoServicio, String> nombre;

}