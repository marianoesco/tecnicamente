package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Servicio;
import modelo.ServicioTecnico;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(ServicioIncluido.class)
public class ServicioIncluido_ { 

    public static volatile SingularAttribute<ServicioIncluido, String> nombreServicio;
    public static volatile SingularAttribute<ServicioIncluido, String> descripcion;
    public static volatile SingularAttribute<ServicioIncluido, Integer> codigo;
    public static volatile SingularAttribute<ServicioIncluido, Float> precio;
    public static volatile SingularAttribute<ServicioIncluido, Servicio> servicio;
    public static volatile SingularAttribute<ServicioIncluido, ServicioTecnico> servicioTecnico;

}