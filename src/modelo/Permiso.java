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
@Table(name = "Permisos")
public class Permiso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "gestionCompra")
    private boolean gestionCompra;

    @Column(name = "gestionVenta")
    private boolean gestionVenta;

    @Column(name = "gestionCliente")
    private boolean gestionCliente;

    @Column(name="gestionEquipo")
    private boolean gestionEquipo;
    
    @Column(name = "gestionServicioTecnico")
    private boolean gestionServicioTecnico;

    @Column(name="gestionServicios")
    private boolean gestionServicio;
    
    @Column(name = "gestionInforme")
    private boolean gestionInforme;

    @Column(name = "gestionUsuario")
    private boolean gestionUsuario;

    @Column(name = "gestionProducto")
    private boolean gestionProducto;

    @Column(name="gestionParametro")
    private boolean gestionParametro;
 
    
    public Permiso() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }


    public boolean isGestionCompra() {
        return gestionCompra;
    }

    public void setGestionCompra(boolean gestionCompra) {
        this.gestionCompra = gestionCompra;
    }

    public boolean isGestionVenta() {
        return gestionVenta;
    }

    public void setGestionVenta(boolean gestionVenta) {
        this.gestionVenta = gestionVenta;
    }

    public boolean isGestionCliente() {
        return gestionCliente;
    }

    public void setGestionCliente(boolean gestionCliente) {
        this.gestionCliente = gestionCliente;
    }

    public boolean isGestionServicioTecnico() {
        return gestionServicioTecnico;
    }

    public void setGestionServicioTecnico(boolean gestionServicioTecnico) {
        this.gestionServicioTecnico = gestionServicioTecnico;
    }

    public boolean isGestionInforme() {
        return gestionInforme;
    }

    public void setGestionInforme(boolean gestionInforme) {
        this.gestionInforme = gestionInforme;
    }

    public boolean isGestionUsuario() {
        return gestionUsuario;
    }

    public void setGestionUsuario(boolean gestionUsuario) {
        this.gestionUsuario = gestionUsuario;
    }

    public boolean isGestionParametro() {
        return gestionParametro;
    }

    public void setGestionParametro(boolean gestionParametro) {
        this.gestionParametro = gestionParametro;
    }

    public boolean isGestionServicio() {
        return gestionServicio;
    }

    public void setGestionServicio(boolean gestionServicio) {
        this.gestionServicio = gestionServicio;
    }
    
    public boolean isGestionProducto() {
        return gestionProducto;
    }

    public void setGestionProducto(boolean gestionProducto) {
        this.gestionProducto = gestionProducto;
    }

    public boolean isGestionEquipo() {
        return gestionEquipo;
    }

    public void setGestionEquipo(boolean gestionEquipo) {
        this.gestionEquipo = gestionEquipo;
    }

    public Permiso(boolean gestionCompra, boolean gestionVenta, boolean gestionCliente, boolean gestionEquipo, boolean gestionServicioTecnico, boolean gestionServicio, boolean gestionInforme, boolean gestionUsuario, boolean gestionProducto, boolean gestionParametro) {
        this.gestionCompra = gestionCompra;
        this.gestionVenta = gestionVenta;
        this.gestionCliente = gestionCliente;
        this.gestionEquipo = gestionEquipo;
        this.gestionServicioTecnico = gestionServicioTecnico;
        this.gestionServicio = gestionServicio;
        this.gestionInforme = gestionInforme;
        this.gestionUsuario = gestionUsuario;
        this.gestionProducto = gestionProducto;
        this.gestionParametro = gestionParametro;
    }

    
   

    @Override
    public String toString() {
        return "Permiso{" + "gestionCompra=" + gestionCompra + ", gestionVenta=" + gestionVenta + ", gestionCliente=" + gestionCliente + ", gestionServicioTecnico=" + gestionServicioTecnico + ", gestionInforme=" + gestionInforme + ", gestionUsuario=" + gestionUsuario + ", gestionProducto=" + gestionProducto + ", gestionParametro=" + gestionParametro + '}';
    }
    

}
