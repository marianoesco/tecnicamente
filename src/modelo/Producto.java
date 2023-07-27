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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */


@Entity
@Table(name="Productos",uniqueConstraints={@UniqueConstraint(columnNames={"nombre,marca,tipoProducto"})})
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private int codigo;
    @Column(name="nombre")
    private String nombre;
    @Column(name="cantidad")
    private int cantidad;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private float precio;
    @Column(name="numeroSerie")
    private String numeroSerie;
    
    
    @ManyToOne
    @JoinColumn(name="marca")
    private Marca marca;
    
    @ManyToOne
    @JoinColumn(name="tipoProducto")
    private TipoProducto tipoProducto;
    
    
    public Producto() {
    }

    public Producto(String nombre, String descripcion, Marca marca, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.tipoProducto = tipoProducto;
    }

    public Producto(String nombre, String descripcion, float precio, Marca marca, TipoProducto tipoProducto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
        this.tipoProducto = tipoProducto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }
  

    
}
