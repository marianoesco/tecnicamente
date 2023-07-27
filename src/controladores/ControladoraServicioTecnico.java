/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Equipo;
import modelo.EstadoServicio;
import modelo.ProductoIncluido;
import modelo.Servicio;
import modelo.ServicioIncluido;
import modelo.ServicioTecnico;
import modelo.TipoEquipo;
import persistencia.ControladoraPersistencia;
import persistencia.EquipoJpaController;
import persistencia.EstadoServicioJpaController;
import persistencia.ProductoIncluidoJpaController;
import persistencia.ServicioIncluidoJpaController;
import persistencia.ServicioJpaController;
import persistencia.ServicioTecnicoJpaController;
import persistencia.TipoEquipoJpaController;

/**
 *
 * @author EcobarM
 */
public class ControladoraServicioTecnico extends ControladoraPersistencia {

    private ServicioTecnicoJpaController servicioTecnicoJpa;
    private ProductoIncluidoJpaController productoIncluidoJpa;
    private ServicioIncluidoJpaController servicioIncluidoJpa;
    private EquipoJpaController equipoJpa;
    private EstadoServicioJpaController estadoServicioJpa;
    private ServicioJpaController servicioJpa;
    private TipoEquipoJpaController tipoEquipoJpa;

    public ControladoraServicioTecnico() {
        this.servicioTecnicoJpa = new ServicioTecnicoJpaController(getEmf());
        this.productoIncluidoJpa = new ProductoIncluidoJpaController(getEmf());
        this.servicioIncluidoJpa = new ServicioIncluidoJpaController(getEmf());
        this.equipoJpa = new EquipoJpaController(getEmf());
        this.estadoServicioJpa = new EstadoServicioJpaController(getEmf());
        this.servicioJpa = new ServicioJpaController(getEmf());
        this.tipoEquipoJpa = new TipoEquipoJpaController(getEmf());
    }

    public ServicioTecnicoJpaController getServicioTecnicoJpa() {
        return servicioTecnicoJpa;
    }

    public ProductoIncluidoJpaController getProductoIncluidoJpa() {
        return productoIncluidoJpa;
    }

    public ServicioIncluidoJpaController getServicioIncluidoJpa() {
        return servicioIncluidoJpa;
    }

    public EquipoJpaController getEquipoJpa() {
        return equipoJpa;
    }

    public EstadoServicioJpaController getEstadoServicioJpa() {
        return estadoServicioJpa;
    }

    public ServicioJpaController getServicioJpa() {
        return servicioJpa;
    }

    public ServicioTecnico registrarServicioTecnico(ServicioTecnico servicioTecnico) throws Exception {
        this.servicioTecnicoJpa.create(servicioTecnico);
        return servicioTecnico;
    }

    public ServicioTecnico buscarServicioTecnicoPorCodigo(int codigo) {
        return this.servicioTecnicoJpa.findServicioTecnico(codigo);
    }

    public ServicioIncluido buscarServicioIncluidoPorCodigo(int codigo) {
        return this.servicioIncluidoJpa.findServicioIncluido(codigo);
    }

    public ProductoIncluido buscarProductoIncluidoPorCodigo(int codigo) {
        return this.productoIncluidoJpa.findProductoIncluido(codigo);
    }
    
    public List<ServicioTecnico>listarServiciosTecnicosDescentes(){
        return this.servicioTecnicoJpa.ListarServiciosTecnicosDescentes();
    }
    

    public void modificarServicioTecnico(ServicioTecnico servicioTecnico) throws Exception {

        this.servicioTecnicoJpa.edit(servicioTecnico);
    }

    public void registrarEstadoServicio(EstadoServicio Estadoservicio) throws Exception {
        try {
            this.estadoServicioJpa.create(Estadoservicio);
        } catch (Exception e) {
            throw new Exception("El estado " + Estadoservicio.getNombre() + " ya existe");
        }

    }

    public void registrarServicioIncluido(ServicioIncluido servicioIncluido) throws Exception {
        try {
            this.servicioIncluidoJpa.create(servicioIncluido);

        } catch (Exception e) {
            throw new Exception("El servicio incluido ya existe");
        }

    }
    
    public TipoEquipo BuscarPorTipo(TipoEquipo tipoEquipo){
       
        return this.tipoEquipoJpa.findTipoEquipo(tipoEquipo.getCodigo());
    }
    
    
    public void eliminarServicioIncluido(ServicioIncluido servicioIncluido)throws Exception{
        try {
            this.servicioIncluidoJpa.destroy(servicioIncluido.getCodigo());
        } catch (Exception e) {
            throw new Exception("El servicio inlcuido no se puede eliminar");
        }
        
        
    }
    
    public void eliminarProductoEliminado(ProductoIncluido productoIncluido)throws Exception{
        try {
            this.productoIncluidoJpa.destroy(productoIncluido.getCodigo());
            
        } catch (Exception e) {
            throw new Exception("El producto incluido no se puede eliminar");
        }
        
        
    }
    

    public void registrarProductoIncluido(ProductoIncluido ProductoIncluido) throws Exception {
        try {
            this.productoIncluidoJpa.create(ProductoIncluido);

        } catch (Exception e) {
            throw new Exception("El producto incluido ya existe");
        }

    }

    public List<EstadoServicio> listarEstado() {

        return this.estadoServicioJpa.findEstadoServicioEntities();
    }

    public void registrarServicio(Servicio servicio) throws Exception {
        try {
            this.servicioJpa.create(servicio);
        } catch (Exception e) {
            throw new Exception("El servicio " + servicio.getNombre() + " ya existe");
        }

    }

    public void registrarTipoEquipo(TipoEquipo tipo) throws Exception {
        try {
            this.tipoEquipoJpa.create(tipo);
        } catch (Exception e) {
            throw new Exception("El tipo de Equipo " + tipo.getNombre() + " ya existe");
        }
    }

    public Equipo buscarEquipoPorCodigo(int codigo) {
        return this.equipoJpa.findEquipo(codigo);
    }

    public Servicio buscarServicioPorCodigo(int codigo) {
        return this.servicioJpa.findServicio(codigo);
    }

    public void modificarServicio(Servicio servicio) throws Exception {
        try {
            this.servicioJpa.edit(servicio);
        } catch (Exception e) {
            throw new Exception("El servicio " + servicio.getNombre() + " ya existe");
        }

    }

    public List<Equipo>filtrarEquipoPorTodo(TipoEquipo tipoEquipo,String modelo){
    
        return this.equipoJpa.FiltrarEquipoPorTodo(modelo, tipoEquipo);
    }
    
    public List<Servicio> listarServicios() {

        return this.servicioJpa.findServicioEntities();
    }

    public List<ServicioTecnico> listarServiciosTecnico() {

        return this.servicioTecnicoJpa.findServicioTecnicoEntities();
    }

    public List<Equipo> listarEquipo() {

        return this.equipoJpa.findEquipoEntities();
    }

   
    
    public void registrarEquipo(Equipo equipo) throws Exception {
        try {
            this.equipoJpa.create(equipo);
        } catch (Exception e) {
            throw new Exception( "El equipo " + equipo.getModelo() + " ya existe ");
        }

    }
    
    public void modificarEquipo(Equipo equipo) throws Exception {
        try {
            this.equipoJpa.edit(equipo);
        } catch (Exception e) {
            throw new Exception( "El equipo " + equipo.getModelo() + " ya existe ");
        }

    }
    
    
    public List<Servicio>filtrarServicioPorNombre(String nombre){
    
        return this.servicioJpa.FiltrarProductoPorNombre(nombre);
    }
    
    
    
    public List<ServicioTecnico>filtrarServiciosPorCliente(Cliente cliente) throws Exception{
    
        return this.servicioTecnicoJpa.ListarServiciosTecnicosPorCliente(cliente.getCodigo());
    }

    public List<ServicioTecnico>listarServiciosPorFechas(String fechaDesde,String fechaHasta)throws Exception{
        
        return this.servicioTecnicoJpa.ListarServiciosPorFechas(fechaDesde, fechaHasta);
    }
    
    
    public List<TipoEquipo> listarTipoEquipo() {

        return this.tipoEquipoJpa.findTipoEquipoEntities();
    }

    
    public List<Equipo> filtrarEquipoPorNombre(String nombre) {
        
        return this.equipoJpa.FiltrarEquipoPorNombre(nombre);
    }
    
    
    public List<Equipo> BuscarEquipoPorTipo( TipoEquipo tipoEquipo ) {
        
        return this.equipoJpa.BuscarEquipoPorTipo(tipoEquipo);
    }
    
    
}
