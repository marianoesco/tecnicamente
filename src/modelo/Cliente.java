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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author EcobarM
 */
@Entity
@Table(name = "Clientes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"dni"})})
public class Cliente extends Persona implements Serializable {

    @Column(name = "dni")
    private int dni;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "correo")
    private String correo;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "localidad")
    private Localidad localidad;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="cliente_x_equipo",
            joinColumns=@JoinColumn(name="cliente_dni"),
            inverseJoinColumns = @JoinColumn(name="equipo_codigo")
    )
    private List<Equipo>equipos= new ArrayList<Equipo>();

    public Cliente() {
    }

    public Cliente(int dni, String direccion, String telefono, String correo, Provincia provincia, Localidad localidad, String apellido, String nombre, String registradoPor) {
        super(apellido, nombre, registradoPor);
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.provincia = provincia;
        this.localidad = localidad;
    }

   

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

 
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return getApellido() + " " +  getNombre();
    }

   

}
