package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Equipo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(TipoEquipo.class)
public class TipoEquipo_ { 

    public static volatile SingularAttribute<TipoEquipo, Integer> codigo;
    public static volatile SingularAttribute<TipoEquipo, String> nombre;
    public static volatile ListAttribute<TipoEquipo, Equipo> equipos;

}