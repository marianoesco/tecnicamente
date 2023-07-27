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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */


@Entity
@Table(name="DetallesCompra")
public class DetalleCompra implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private int codigo;
    @Column(name="nombreProducto")
    private String nombreProducto;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="cantidad")
    private int cantidad;
    @Column(name="precio")
    private float precio;
    @Column(name="subtotal")
    private float subtotal;
    
    @ManyToOne
    @JoinColumn(name="compra")
    private Compra compra;
    
    @OneToOne
    @JoinColumn(name="producto")
    private Producto producto;
    
    public DetalleCompra() {
    }

    public DetalleCompra(String nombreProducto, String descripcion, int cantidad, float precio, float subtotal, Producto producto) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.producto = producto;
    }

   

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
    public float sumarSubtotal(List<DetalleCompra>detallesCompra){
        float suma =0;
        for (DetalleCompra detallesCompra1 : detallesCompra) {
            suma += detallesCompra1.subtotal;
        }
        return suma;
    }
    
    public int sumarCantidadTotal (List<DetalleCompra>detallesCompra){
        int cantidad=0;
        for (DetalleCompra detallesCompra1 : detallesCompra) {
            cantidad+= detallesCompra1.cantidad;
        }
        return cantidad;
    }
    
    public float restarSubtotal(List<DetalleCompra>detallesCompra, float restarSubtotal){
        float sub = sumarSubtotal(detallesCompra);
        sub-= restarSubtotal;
        return sub;
    }
    
    public int restarCantidadTotal (List<DetalleCompra>detallesCompra, float restarCantidadTotal){
        int sub = sumarCantidadTotal(detallesCompra);
        sub-= restarCantidadTotal;
        return sub;
    }


    
}
