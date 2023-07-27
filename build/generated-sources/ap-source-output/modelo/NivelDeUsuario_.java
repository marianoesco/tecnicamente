package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Permiso;
import modelo.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-07-24T12:52:38")
@StaticMetamodel(NivelDeUsuario.class)
public class NivelDeUsuario_ { 

    public static volatile SingularAttribute<NivelDeUsuario, Integer> codigo;
    public static volatile SingularAttribute<NivelDeUsuario, Permiso> permiso;
    public static volatile ListAttribute<NivelDeUsuario, Usuario> usuarios;
    public static volatile SingularAttribute<NivelDeUsuario, String> nombre;

}