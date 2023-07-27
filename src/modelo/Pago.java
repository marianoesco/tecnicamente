/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */

@Entity
@Table(name="pagos")
public class Pago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private int codigo;
    
    @Column(name="precio")
    private float precio;
    
    @ManyToOne
    @JoinColumn(name="tipopago")
    private TipoPago tipopago;

    
    @ManyToOne
    @JoinColumn(name="venta")
    private Venta venta;
    
    @ManyToOne
    @JoinColumn(name="serviciotecnico")
    private ServicioTecnico serviciotecnico;
    
    
    
    public Pago() {
    }
    
    
    public Pago(float precio, TipoPago tipopago) {
        this.precio = precio;
        this.tipopago = tipopago;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public TipoPago getTipopago() {
        return tipopago;
    }

    public void setTipopago(TipoPago tipopago) {
        this.tipopago = tipopago;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public ServicioTecnico getServiciotecnico() {
        return serviciotecnico;
    }

    public void setServiciotecnico(ServicioTecnico serviciotecnico) {
        this.serviciotecnico = serviciotecnico;
    }
    
    
    
    
}
