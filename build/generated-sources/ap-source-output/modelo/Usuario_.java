package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Compra;
import modelo.NivelDeUsuario;
import modelo.ServicioTecnico;
import modelo.Venta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(Usuario.class)
public class Usuario_ extends Persona_ {

    public static volatile ListAttribute<Usuario, Compra> compra;
    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile ListAttribute<Usuario, Venta> venta;
    public static volatile ListAttribute<Usuario, ServicioTecnico> servicioTecnico;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile SingularAttribute<Usuario, NivelDeUsuario> nivel;

}