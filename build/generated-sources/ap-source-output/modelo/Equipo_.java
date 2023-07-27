package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.TipoEquipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(Equipo.class)
public class Equipo_ { 

    public static volatile SingularAttribute<Equipo, String> descripcion;
    public static volatile SingularAttribute<Equipo, Integer> codigo;
    public static volatile SingularAttribute<Equipo, String> modelo;
    public static volatile SingularAttribute<Equipo, TipoEquipo> tipoEquipo;

}