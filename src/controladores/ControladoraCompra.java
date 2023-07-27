/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.Date;
import java.util.List;
import modelo.Compra;
import modelo.DetalleCompra;
import org.eclipse.persistence.services.DevelopmentServices;
import persistencia.CompraJpaController;
import persistencia.ControladoraPersistencia;
import persistencia.DetalleCompraJpaController;
import persistencia.ProductoJpaController;

/**
 *
 * @author EcobarM
 */
public class ControladoraCompra extends ControladoraPersistencia {

    private CompraJpaController compraJpa;
    private DetalleCompraJpaController detalleCompraJpa;

    public ControladoraCompra() {
        this.compraJpa = new CompraJpaController(getEmf());
        this.detalleCompraJpa = new DetalleCompraJpaController(getEmf());
    }

    public CompraJpaController getCompraJpa() {
        return compraJpa;
    }

   

    public Compra registrarCompra(Compra compra) throws Exception {
        this.compraJpa.create(compra);
        return compra;
    }
    
   public void registrarDetalleCompra(DetalleCompra detalleCompra) throws Exception {
        this.detalleCompraJpa.create(detalleCompra);
    }
    

    public List<Compra> listarCompras() {

        return this.compraJpa.findCompraEntities();
    }

    
    public List<Compra>listarComprasDescendentes(){
    
        return this.compraJpa.ListarComprasDescentes();
    }
    
    public List<Compra>listarComprasPorFechas(String desde,String hasta) throws Exception{
        
        return this.compraJpa.ListarComprasPorFechas(desde, hasta);
        
    }
    

    
}
