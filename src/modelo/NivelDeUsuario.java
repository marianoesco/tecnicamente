/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "Nivel",uniqueConstraints = {
@UniqueConstraint(columnNames = {"nombre"})})
public class NivelDeUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "nombre")
    private String nombre;
    
    @OneToOne
    @JoinColumn(name="permiso")
    private Permiso permiso;

    @OneToMany(mappedBy = "nivel")
    private List<Usuario>usuarios;
    
    
    public NivelDeUsuario() {
    }

    public NivelDeUsuario(String nombre, Permiso permiso) {
        this.nombre = nombre;
        this.permiso = permiso;
    }

    public NivelDeUsuario(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!="[SELECCIONAR]" && obj!=null) {
            return this.codigo == ((NivelDeUsuario)obj).getCodigo();
        }else{
            return false;
        }
    }
    
}
