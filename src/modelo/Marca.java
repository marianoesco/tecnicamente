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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */
@Entity
@Table(name = "Marcas", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private int codigo;

    @Column(name = "nombre")
    private String nombre;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="marca_x_tipoProducto",
            joinColumns=@JoinColumn(name="Marca_codigo"),
            inverseJoinColumns = @JoinColumn(name="TipoProducto_codigo")
    )
    private List<TipoProducto> tiposProductos= new ArrayList<TipoProducto>();
    
    
    @OneToMany(mappedBy = "marca")
    private List<Producto>producto;
    
    public Marca() {
    
    }

    public Marca(String nombre) {
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

    public List<TipoProducto> getTiposProductos() {
        return tiposProductos;
    }

    public void setTiposProductos(List<TipoProducto> tiposProductos) {
        this.tiposProductos = tiposProductos;
    }

    @Override
    public String toString() {
        return nombre ;
    }
    
    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }
  
    @Override
    public boolean equals(Object obj) {
   
        if(obj!="[SELECCIONAR]" && obj!=null){
            return this.codigo ==  ((Marca)obj).getCodigo();
        }else{
            return false;
        }
    }
    
}
