/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.List;
import modelo.Marca;
import modelo.Producto;
import modelo.TipoProducto;
import persistencia.ControladoraPersistencia;
import persistencia.MarcaJpaController;
import persistencia.ProductoIncluidoJpaController;
import persistencia.ProductoJpaController;
import persistencia.TipoProductoJpaController;

/**
 *
 * @author EcobarM
 */
public class ControladoraProducto extends ControladoraPersistencia {
    
    private MarcaJpaController marcaJpa;
    private TipoProductoJpaController tipoProductoJpa;
    private ProductoJpaController productoJpa;
    
    public ControladoraProducto() {
        this.marcaJpa = new MarcaJpaController(getEmf());
        this.tipoProductoJpa = new TipoProductoJpaController(getEmf());
        this.productoJpa = new ProductoJpaController(getEmf());
    }
    
    public MarcaJpaController getMarcaJpa() {
        return marcaJpa;
    }
    
    public TipoProductoJpaController getTipoProductoJpa() {
        return tipoProductoJpa;
    }
    
    public Producto buscarProductoPorCodigo(int codigo) {
        return this.productoJpa.findProducto(codigo);
    }
    
    public Marca buscarMarcaPornombre(String nombreMarca) throws Exception {
        return this.marcaJpa.buscarXnombreMarca(nombreMarca);
    }    
    
    public TipoProducto buscarTipoProductoPorCodigo(int codigo) {
        return this.tipoProductoJpa.findTipoProducto(codigo);
    }
    
    public Marca buscarMarcaPorCodigo(int codigo) {
        return this.marcaJpa.findMarca(codigo);
    }
    
    public void registrarProducto(Producto producto) throws Exception {
        try {
            this.productoJpa.create(producto);
        } catch (Exception e) {
            throw new Exception("El producto " + producto.getNombre() + " ya existe");
        }
        
    }
    
    public void modificarProducto(Producto producto) throws Exception {
        try {
            this.productoJpa.edit(producto);
        } catch (Exception e) {
            throw new Exception("El producto " + producto.getNombre() + " ya existe");
        }
        
    }
    
    public void RegistarMarca(Marca marca) throws Exception {
        try {
            this.marcaJpa.create(marca);
        } catch (Exception e) {
            throw new Exception("La marca " + marca.getNombre() + " ya existe");
        }
        
    }
    
    public void RegistarTipoProducto(TipoProducto tipoProducto) throws Exception {
        try {
            this.tipoProductoJpa.create(tipoProducto);
        } catch (Exception e) {
            throw new Exception("El tipo de producto " + tipoProducto.getNombre() + " ya existe");
        }
        
    }
    
    public List<Producto> filtrarProductoPorNombre(String nombre) {
        
        return this.productoJpa.FiltrarProductoPorNombre(nombre);
    }
     
    public List<Producto> filtrarProducto(String nombre,Marca marca,TipoProducto tipo) {
        
        return this.productoJpa.FiltrarProductoPorTodo(nombre, marca, tipo);
    }
    
    public List<Producto> filtrarProductoPorMarca( Marca marca ) {
        
        return this.productoJpa.FiltrarProductoPorMarca(marca );
    }
    
     public void compreProducto(int cantidad, int codigo) throws Exception {
        try {
            Producto producto = this.productoJpa.findProducto(codigo);
            producto.setCantidad(producto.getCantidad() + cantidad);
            this.productoJpa.edit(producto);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public void vendiProducto(int cantidad, int codigo) throws Exception {
        try {
            Producto producto = this.productoJpa.findProducto(codigo);
            producto.setCantidad(producto.getCantidad() - cantidad);
            this.productoJpa.edit(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    
    public List<Producto> filtrarProductoPorMarcayTipo( Marca marca,TipoProducto tipo) {
        
        return this.productoJpa.FiltrarProductoPorMarcayTipo(marca, tipo);
    }
    
    public List<Marca>filtrarMarcaPorNombre(String nombre){
    
        return this.marcaJpa.FiltrarMarcaPorNombre(nombre);
    }
    public List<TipoProducto>filtrarTipoProductoPorNombre(String nombre){
    
        return this.tipoProductoJpa.FiltrarTipoProductoPorNombre(nombre);
    }
    
    public List<TipoProducto> listarTipoProductos() {
        
        return this.tipoProductoJpa.findTipoProductoEntities();
    }
    
    public List<Producto> listarProductos() {
        
        return this.productoJpa.findProductoEntities();
    }
    
    public List<Marca> listarMarcas() {
        
        return this.marcaJpa.findMarcaEntities();
    }
    
    public void ModificarMarca(Marca marca) throws Exception {
        try {
            this.marcaJpa.edit(marca);
        } catch (Exception e) {
            throw new Exception("La marca " + marca.getNombre() + " ya existe");
        }
        
    }
    
    public void ModificarTipoProducto(TipoProducto tipoProducto) throws Exception {
        try {
            this.tipoProductoJpa.edit(tipoProducto);
        } catch (Exception e) {
            throw new Exception("El tipo de producto " + tipoProducto.getNombre() + " ya existe");
        }
        
    }
    
}
