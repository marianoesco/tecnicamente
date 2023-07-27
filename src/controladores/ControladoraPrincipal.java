/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.ImageIcon;
import persistencia.ControladoraPersistencia;
import vistas.Login;

/**
 *
 * @author EcobarM
 */
public class ControladoraPrincipal extends ControladoraPersistencia {

    private ControladoraLogueo controladoraLogueo;
    private ControladoraCliente controladoraCliente;
    private ControladoraCompra controladoraCompra;
    private ControladoraProducto controladoraProducto;
    private ControladoraServicioTecnico controladoraServicioTecnico;
    private ControladoraVenta controladoraVenta;

    public ControladoraPrincipal() {
        this.controladoraLogueo = new ControladoraLogueo();
        this.controladoraCliente = new ControladoraCliente();
        this.controladoraCompra = new ControladoraCompra();
        this.controladoraProducto = new ControladoraProducto();
        this.controladoraServicioTecnico = new ControladoraServicioTecnico();
        this.controladoraVenta = new ControladoraVenta();
    }

    public ControladoraLogueo getControladoraLogueo() {
        return controladoraLogueo;
    }

    public ControladoraCliente getControladoraCliente() {
        return controladoraCliente;
    }

    public ControladoraCompra getControladoraCompra() {
        return controladoraCompra;
    }

    public ControladoraProducto getControladoraProducto() {
        return controladoraProducto;
    }

    public ControladoraServicioTecnico getControladoraServicioTecnico() {
        return controladoraServicioTecnico;
    }

    public ControladoraVenta getControladoraVenta() {
        return controladoraVenta;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladoraPrincipal cp = new ControladoraPrincipal();

        Login login = new Login(cp);

        login.setVisible(true);

    }

}
