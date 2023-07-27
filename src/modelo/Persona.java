/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author EcobarM
 */

@MappedSuperclass
public abstract class Persona {
   @Id
   @Column(name="codigo")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int codigo;
   @Column(name="apellido")
   private String apellido;
   @Column(name="nombre")
   private String nombre;
  

   @Column(name="registradorPor")
   private String registradoPor;
   
   @Column(name="ultimaModicacionPor")
   private String ultimaModificacionPor;
   
    public Persona() {
    }

    public Persona(String apellido, String nombre, String registradoPor) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.registradoPor = registradoPor;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(String registradoPor) {
        this.registradoPor = registradoPor;
    }

    public String getUltimaModificacionPor() {
        return ultimaModificacionPor;
    }

    public void setUltimaModificacionPor(String ultimaModificacionPor) {
        this.ultimaModificacionPor = ultimaModificacionPor;
    }

  
   
    
    
}
