package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Cliente;
import modelo.Equipo;
import modelo.EstadoServicio;
import modelo.Pago;
import modelo.ProductoIncluido;
import modelo.ServicioIncluido;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:39")
@StaticMetamodel(ServicioTecnico.class)
public class ServicioTecnico_ { 

    public static volatile SingularAttribute<ServicioTecnico, Integer> codigo;
    public static volatile SingularAttribute<ServicioTecnico, String> registradorPor;
    public static volatile SingularAttribute<ServicioTecnico, Date> fechaDeEgreso;
    public static volatile ListAttribute<ServicioTecnico, Pago> pagos;
    public static volatile SingularAttribute<ServicioTecnico, Cliente> cliente;
    public static volatile SingularAttribute<ServicioTecnico, Float> precio;
    public static volatile SingularAttribute<ServicioTecnico, EstadoServicio> estadoServicio;
    public static volatile SingularAttribute<ServicioTecnico, Date> fechaDeIngreso;
    public static volatile SingularAttribute<ServicioTecnico, String> ultimaModificacionPor;
    public static volatile ListAttribute<ServicioTecnico, ServicioIncluido> servicioIncluido;
    public static volatile SingularAttribute<ServicioTecnico, Equipo> equipo;
    public static volatile SingularAttribute<ServicioTecnico, String> comentarioTecnicos;
    public static volatile SingularAttribute<ServicioTecnico, Usuario> usuario;
    public static volatile SingularAttribute<ServicioTecnico, String> tipoFalla;
    public static volatile SingularAttribute<ServicioTecnico, Date> fechaDeModificacion;
    public static volatile ListAttribute<ServicioTecnico, ProductoIncluido> productoIncluido;

}