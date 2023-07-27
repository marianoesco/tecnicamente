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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */
@Entity
@Table(name = "ServiciosTecnicos")
public class ServicioTecnico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "fechaDeIngreso")
    @Temporal(TemporalType.DATE)
    private Date fechaDeIngreso;

    @Column(name = "fechaDeModificacion")
    @Temporal(TemporalType.DATE)
    private Date fechaDeModificacion;

    @Column(name = "fechaDeEgreso")
    @Temporal(TemporalType.DATE)
    private Date fechaDeEgreso;

    @Column(name = "comentariosTecnicos")
    private String comentarioTecnicos;

    @Column(name = "tipoFalla")
    private String tipoFalla;

    @Column(name = "ultimaModificacionPor")
    private String ultimaModificacionPor;

    @Column(name = "precio")
    private float precio;

    @Column(name = "registradoPor")
    private String registradorPor;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "estadoServicio")
    private EstadoServicio estadoServicio;
    
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name="Equipo")
    private Equipo equipo;
    
    @OneToMany(mappedBy = "servicioTecnico")
    private List<ServicioIncluido> servicioIncluido;

    @OneToMany(mappedBy = "servicioTecnico")
    private List<ProductoIncluido> productoIncluido;
    
    @OneToMany(mappedBy = "serviciotecnico")
    private List<Pago>pagos;

    public ServicioTecnico() {

    }
    
    
    public ServicioTecnico(Date fechaDeIngreso, String comentarioTecnicos, String tipoFalla, float precio, String registradorPor, Usuario usuario, EstadoServicio estadoServicio, Cliente cliente, Equipo equipo) {
        this.fechaDeIngreso = fechaDeIngreso;
        this.comentarioTecnicos = comentarioTecnicos;
        this.tipoFalla = tipoFalla;
        this.precio = precio;
        this.registradorPor = registradorPor;
        this.usuario = usuario;
        this.estadoServicio = estadoServicio;
        this.cliente = cliente;
        this.equipo = equipo;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public String getComentarioTecnicos() {
        return comentarioTecnicos;
    }

    public void setComentarioTecnicos(String comentarioTecnicos) {
        this.comentarioTecnicos = comentarioTecnicos;
    }

    public String getTipoFalla() {
        return tipoFalla;
    }

    public void setTipoFalla(String tipoFalla) {
        this.tipoFalla = tipoFalla;
    }

    public String getRegistradorPor() {
        return registradorPor;
    }

    public void setRegistradorPor(String registradorPor) {
        this.registradorPor = registradorPor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EstadoServicio getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(EstadoServicio estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public Date getFechaDeModificacion() {
        return fechaDeModificacion;
    }

    public void setFechaDeModificacion(Date fechaDeModificacion) {
        this.fechaDeModificacion = fechaDeModificacion;
    }

    public Date getFechaDeEgreso() {
        return fechaDeEgreso;
    }

    public void setFechaDeEgreso(Date fechaDeEgreso) {
        this.fechaDeEgreso = fechaDeEgreso;
    }

    public String getUltimaModificacionPor() {
        return ultimaModificacionPor;
    }

    public void setUltimaModificacionPor(String ultimaModificacionPor) {
        this.ultimaModificacionPor = ultimaModificacionPor;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<ServicioIncluido> getServicioIncluido() {
        return servicioIncluido;
    }

    public void setServicioIncluido(List<ServicioIncluido> servicioIncluido) {
        this.servicioIncluido = servicioIncluido;
    }

    public List<ProductoIncluido> getProductoIncluido() {
        return productoIncluido;
    }

    public void setProductoIncluido(List<ProductoIncluido> productoIncluido) {
        this.productoIncluido = productoIncluido;
    }
    
    
    
    public float sumarServiciosTecnicos(List<ServicioTecnico>servicioTecnicos){
       float suma=0;
        for (ServicioTecnico servicioTecnico : servicioTecnicos) {
            suma+=servicioTecnico.getPrecio();
        }
        return suma;
    }
    

}
