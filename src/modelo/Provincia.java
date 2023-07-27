/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */

@Entity
@Table(name="Provincias",uniqueConstraints={@UniqueConstraint(columnNames={"nombre"})})
public class Provincia implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "provincia")
    private List<Localidad>localidades;
    
    @OneToMany(mappedBy = "provincia")
    private List<Cliente>clientes;
    
    public Provincia() {
    }

    public Provincia(String nombre) {
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

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
     @Override
    public boolean equals(Object obj) {
        if(obj!="[SELECCIONAR]" && obj!=null){
            return this.codigo == ((Provincia)obj).getCodigo();
        }else{
            return false;
        }
    }
}
