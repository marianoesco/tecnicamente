/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */
@Entity
@Table(name = "Usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombreUsuario,nivel"})})
public class Usuario extends Persona implements Serializable {

    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Column(name = "clave")
    private String clave;
  

 
    @ManyToOne
    @JoinColumn(name="nivel")
    private NivelDeUsuario nivel;
    
    @OneToMany(mappedBy = "usuario")
    private List<Venta>venta;
    
    @OneToMany(mappedBy = "usuario")
    private List<Compra>compra;
    
    @OneToMany(mappedBy = "usuario")
    private List<ServicioTecnico>servicioTecnico;
    
    public Usuario() {
    }

    public Usuario(String nombreUsuario, String clave, NivelDeUsuario nivel, String apellido, String nombre, String registradoPor) {
        super(apellido, nombre, registradoPor);
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nivel = nivel;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }


    public NivelDeUsuario getNivel() {
        return nivel;
    }

    public void setNivel(NivelDeUsuario nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return nombreUsuario ;
    }

}
