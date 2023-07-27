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
@Table(name="ServiciosIncluido",uniqueConstraints={@UniqueConstraint(columnNames={"codigo,servicioTecnico"})})
public class ServicioIncluido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private int codigo;
    
    @Column(name="nombreServicio")
    private String nombreServicio;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="precio")
    private float precio;

    
    @OneToOne
    @JoinColumn(name="servicio")
    private Servicio servicio;
    
    @ManyToOne
    @JoinColumn(name="servicioTecnico")
    private ServicioTecnico servicioTecnico;
    
    public ServicioIncluido() {
    }

    public ServicioIncluido(String nombreServicio, String descripcion, float precio) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public ServicioIncluido(String nombreServicio, String descripcion, float precio, Servicio servicio) {
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.servicio = servicio;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public ServicioTecnico getServicioTecnico() {
        return servicioTecnico;
    }

    public void setServicioTecnico(ServicioTecnico servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
    }
    
    
   public float sumarSubtotal(List<ServicioIncluido>serviciosIncluidos ) {
        float suma = 0;
        for (ServicioIncluido serviciosIncluidos1 : serviciosIncluidos) {
            suma += serviciosIncluidos1.getPrecio();
        }
        return suma;
    }

    public int sumarCantidadTotal(List<ServicioIncluido> serviciosIncluidos) {
        int cantidad = 0;
        for (ServicioIncluido serviciosIncluido1 : serviciosIncluidos) {
            cantidad +=1;
        }

        return cantidad;
    }
    
    
     public float restarSubtotal(List<ServicioIncluido>servicioIncluidos, float restaTotal){
        float sub = sumarSubtotal(servicioIncluidos);
        sub-= restaTotal;
        return sub;
    }
    
    public int restarCantidadTotal (List<ServicioIncluido>servicioIncluidos ){
        int sub = sumarCantidadTotal(servicioIncluidos);
        sub = -1;
        return sub;
    }
    
    
}
