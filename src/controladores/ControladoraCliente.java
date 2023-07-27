/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.List;
import modelo.Cliente;
import modelo.Equipo;
import modelo.Localidad;
import modelo.Provincia;
import persistencia.ClienteJpaController;
import persistencia.ControladoraPersistencia;
import persistencia.EquipoJpaController;
import persistencia.LocalidadJpaController;
import persistencia.ProvinciaJpaController;

/**
 *
 * @author EcobarM
 */
public class ControladoraCliente extends ControladoraPersistencia {

    private ClienteJpaController clienteJpa;
    private LocalidadJpaController localidadJpa;
    private ProvinciaJpaController provinciaJpa;
    private EquipoJpaController equipoJpa;

    public ControladoraCliente() {
        this.clienteJpa = new ClienteJpaController(getEmf());
        this.localidadJpa = new LocalidadJpaController(getEmf());
        this.provinciaJpa = new ProvinciaJpaController(getEmf());
        this.equipoJpa = new EquipoJpaController(getEmf());

    }

    public List<Cliente> listarClientes() {
        return this.clienteJpa.findClienteEntities();

    }

    public void registrarCliente(Cliente cliente) throws Exception {
        try {
            this.clienteJpa.create(cliente);
        } catch (Exception e) {
            throw new Exception("El cliente '" + cliente.getDni() + "' ya existe");
        }
    }

    public Cliente buscarClientePorDni(int dni){
        try {
            return this.clienteJpa.buscarClientePorDni(dni);
        } catch (Exception e) {
            return null;
        }
    }
    
    public void modificarCliente(Cliente cliente) throws Exception {
        try {
            this.clienteJpa.edit(cliente);
        } catch (Exception e) {
            throw new Exception("El cliente '" + cliente.getDni() + "' ya existe");
        }
    }
    
    public void modificarClienteEquipo(Cliente cliente) throws Exception{
        try {
            this.clienteJpa.edit(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
    }
    
    
    public List<Localidad>filtrarLocalidadPorProvincia(Provincia provincia){
    
        return this.localidadJpa.FiltrarLocalidadPorProvincia(provincia);
    }
    
    
    public List<Cliente> filtrarClientePorDni(int dni){
        
        return this.clienteJpa.FiltrarClientePorDni(dni);
    }
    

    public void registrarLocalidad(Localidad localidad) throws Exception {
        try {
            this.localidadJpa.create(localidad);
        } catch (Exception e) {
            throw new Exception("La localidad '" + localidad.getNombre() + "' con la provincia '" + localidad.getProvincia() + "' ya existe");
        }
    }

    public List<Localidad> listarLocalidades() {
        return this.localidadJpa.findLocalidadEntities();
    }

    public void modificarLocalidad(Localidad localidad) throws Exception {
        try {
            this.localidadJpa.edit(localidad);
        } catch (Exception e) {
            throw new Exception("La localidad '" + localidad.getNombre() + "' con la provincia '" + localidad.getProvincia() + "' ya existe");

        }

    }

    public List<Localidad>filtrarLocalidadPorNombre(String nombre){
        return this.localidadJpa.FiltrarLocalidadPorNombre(nombre);
    }
    
    public void registrarProvincia(Provincia provincia) throws Exception {
        try {
            this.provinciaJpa.create(provincia);
        } catch (Exception e) {
            throw new Exception("La provincia  '" + provincia.getNombre() + "' ya existe");
        }

    }
    

    public void modificarProvincia(Provincia provincia) throws Exception {

        try {
            this.provinciaJpa.edit(provincia);
        } catch (Exception e) {
            throw new Exception("La provincia '" + provincia.getNombre() + "'  ya existe");
        }

    }
    
    public List<Provincia> filtrarProvinciaPorNombre(String nombre){
        return this.provinciaJpa.FiltrarProvinciaPorNombre(nombre);
    }
    

    public Provincia buscarProvinciaPorCodigo(int codigo) {
        return this.provinciaJpa.findProvincia(codigo);
    }

    public Cliente buscarClientePorCodigo(int codigo){
        return clienteJpa.findCliente(codigo);
    }
    
    public Localidad buscarLocalidadPorCodigo(int codigo) {
        return localidadJpa.findLocalidad(codigo);
    }

    public List<Provincia> listarProvincias() {
        return provinciaJpa.findProvinciaEntities();
    }

   

}
