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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */

@Entity
@Table(name="Compras")
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private int codigo;
    @Column(name="fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name="precio")
    private float precio;
    
    @ManyToOne
    @JoinColumn(name="usuario")
    private Usuario usuario;
    
    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> detalleCompra;
    
    public Compra() {
        
    }

    public Compra(Date fecha, float precio, Usuario usuario) {
        this.fecha = fecha;
        this.precio = precio;
        this.usuario = usuario;
        
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCompra> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(List<DetalleCompra> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }
    
    
    public float sumarCompras(List<Compra>compras){
        float suma=0;
        for (Compra compra : compras) {
            suma+=compra.getPrecio();
        }
        return suma;
    }

    @Override
    public String toString() {
        return "Compra{" + "codigo=" + codigo + ", fecha=" + fecha + ", precio=" + precio + ", usuario=" + usuario + ", detalleCompra=" + detalleCompra + '}';
    }
  
    
}
