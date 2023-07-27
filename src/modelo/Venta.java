/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author EcobarM
 */

@Entity
@Table(name="Ventas")
public class Venta implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="codigo")
   private int codigo; 
   @Column(name="fecha")
   @Temporal(TemporalType.DATE)
   private Date fecha;
   @Column(name="precio")
   private float precio;
   @Column(name="observacion")
   private String observacion;

   @ManyToOne
   @JoinColumn(name="cliente")
   private Cliente cliente;
   
   @ManyToOne
   @JoinColumn(name="usuario")
   private Usuario usuario;
   
   @OneToMany(mappedBy = "venta")
   private List<DetalleVenta>detalleVenta;
   
   @OneToMany(mappedBy = "venta")
   private List<Pago>pago;
   
    public Venta() {
    }

    public Venta(Date fecha, float precio, Cliente cliente, Usuario usuario) {
        this.fecha = fecha;
        this.precio = precio;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public List<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
        this.detalleVenta = detalleVenta;
    }
    
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float sumarVentas(List<Venta>ventas){
        float suma=0;
        for (Venta venta : ventas) {
            suma+=venta.getPrecio();
        }
        return suma;
    }
    
    
  
}
