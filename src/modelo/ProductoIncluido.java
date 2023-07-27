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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */
@Entity
@Table(name = "ProductosIncluido", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo,servicioTecnico"})})
public class ProductoIncluido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "nombreProducto")
    private String nombreProducto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "precio")
    private float precio;
    @Column(name = "subtotal")
    private float subtotal;

    @OneToOne
    @JoinColumn(name = "producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "servicioTecnico")
    private ServicioTecnico servicioTecnico;

    public ProductoIncluido() {
    }

    public ProductoIncluido(String nombreProducto, String descripcion, int cantidad, float precio, float subtotal) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public ProductoIncluido(String nombreProducto, String descripcion, int cantidad, float precio, float subtotal, Producto producto) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    public ServicioTecnico getServicioTecnico() {
        return servicioTecnico;
    }

    public void setServicioTecnico(ServicioTecnico servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
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

    public float sumarProductosIncluidos(List<ProductoIncluido> productosIncluidos) {
        float suma = 0;
        for (ProductoIncluido productosIncluido : productosIncluidos) {
            suma+= productosIncluido.subtotal;
        }

        return suma;
    }

    public int sumarCantidadTotal(List<ProductoIncluido> productoIncluidos) {
        int cantidad = 0;
        for (ProductoIncluido productoIncluido1 : productoIncluidos) {
            cantidad += productoIncluido1.getCantidad();
        }

        return cantidad;
    }
    
    public float restarSubtotal(List<ProductoIncluido>productoIncluidos, float restarSubtotal){
        float sub = sumarProductosIncluidos(productoIncluidos);
        sub-= restarSubtotal;
        return sub;
    }
    
    public int restarCantidadTotal (List<ProductoIncluido>productoIncluidos, int restarCantidadTotal){
        int sub = sumarCantidadTotal(productoIncluidos);
        sub-= restarCantidadTotal;
        return sub;
    }
    
}
