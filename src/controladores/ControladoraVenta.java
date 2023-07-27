/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.DetalleVenta;
import modelo.Pago;
import modelo.TipoPago;
import modelo.Venta;
import persistencia.ControladoraPersistencia;
import persistencia.DetalleVentaJpaController;
import persistencia.PagoJpaController;
import persistencia.TipoPagoJpaController;
import persistencia.VentaJpaController;

/**
 *
 * @author EcobarM
 */
public class ControladoraVenta extends ControladoraPersistencia{

    private VentaJpaController ventaJpa;
    private DetalleVentaJpaController detalleJpa;
    private PagoJpaController pagoJpa;
    private TipoPagoJpaController tipoPagoJpa;
    
    public ControladoraVenta() {
        this.ventaJpa = new VentaJpaController(getEmf());    
        this.detalleJpa = new DetalleVentaJpaController(getEmf());
        this.pagoJpa = new PagoJpaController(getEmf());
        this.tipoPagoJpa = new  TipoPagoJpaController(getEmf());
    }
    
    
    
    public Venta registrarVenta (Venta venta) throws Exception{
    
        this.ventaJpa.create(venta);
        return venta;
    }
    
    public void  registarPago(Pago pago )throws Exception{
    
        this.pagoJpa.create(pago);
        
    }
    
    public List<TipoPago>listarTipoPago(){
    
        return this.tipoPagoJpa.findTipoPagoEntities();
    }
    
    
    public List<Venta>listarVentas (){
        
        return this.ventaJpa.findVentaEntities();
    }
    
    public List<Venta>listarVentasDescentes(){
    
        return this.ventaJpa.ListarVentasDescentes();
    }
    
    public void registrarDetalleVenta(DetalleVenta detalleVenta) throws Exception {
        this.detalleJpa.create(detalleVenta);
    }
    
     public List<Venta>filtrarVentasPorCliente(Cliente cliente) throws Exception{
    
        return this.ventaJpa.ListarVentasPorCliente(cliente.getCodigo());
    }
    
    
    
    public List<Venta>ListarVentasPorFechas(String fechaDesde,String fechaHasta) throws Exception{
        return this.ventaJpa.ListarVentasPorFechas(fechaDesde, fechaHasta);
    }
    
}
