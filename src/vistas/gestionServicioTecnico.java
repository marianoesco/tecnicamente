/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Equipo;
import modelo.EstadoServicio;
import modelo.Marca;
import modelo.NivelDeUsuario;
import modelo.Pago;
import modelo.Producto;
import modelo.ProductoIncluido;
import modelo.Servicio;
import modelo.ServicioIncluido;
import modelo.ServicioTecnico;
import modelo.TipoEquipo;
import modelo.TipoPago;
import modelo.TipoProducto;
import modelo.Usuario;
import modelo.Utilidades;
import reportes.Reporte;

/**
 *
 * @author EcobarM
 */
public class gestionServicioTecnico extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private Usuario usuario;
    private JDesktopPane dskp_principalVP;
    private List<ProductoIncluido> productoIncluidos = new LinkedList<>();
    private List<ServicioIncluido> servicioIncluidos = new LinkedList<>();

    private List<ProductoIncluido> productosIncluidosEl = new LinkedList<>();
    private List<ServicioIncluido> servicioIncluidosEl = new LinkedList<>();

    private ProductoIncluido productoIncluidoSum = new ProductoIncluido();
    private ServicioIncluido servicioIncluidoSum = new ServicioIncluido();
    private List<Equipo> equiposPorCliente = new LinkedList<>();
    private ServicioTecnico servicioTecnicoED = null;
    private List<Pago> pagos = new LinkedList<>();

    private float precioTotal = 0;
    private float precioServicios = 0;
    private float preciosProductos = 0;

    private boolean editando;

    DefaultTableModel tbCargaProductoIncluido = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    DefaultTableModel tbCargaServicioIncluido = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    /**
     * Creates new form gestionServicioTecnico
     */
    public gestionServicioTecnico(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario = usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();

    }

    public void estadoInicial() {

        editando = false;

        productoIncluidos = new LinkedList<>();
        servicioIncluidos = new LinkedList<>();
        equiposPorCliente = new LinkedList<>();

        productosIncluidosEl = new LinkedList<>();
        servicioIncluidosEl = new LinkedList<>();

        this.removerFilas(tbCargaProductoIncluido);
        this.removerFilas(tbCargaServicioIncluido);

        tb_listadoProductoIncluidosGST.setModel(tbCargaProductoIncluido);
        tb_listadoServiciosIncluidosGST.setModel(tbCargaServicioIncluido);

        Utilidades.cargarCombo(this.cp.getControladoraVenta().listarTipoPago(), cmb_tipoPagoGST);
        Utilidades.cargarCombo(this.cp.getControladoraServicioTecnico().listarTipoEquipo(), cmb_buscarEquipoPorTipoGST);
        Utilidades.cargarCombo(this.cp.getControladoraServicioTecnico().listarEstado(), cmb_estadoServicioGST);
        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_buscarProductoPorMarcaGST);
        cargarTablaClientes(this.cp.getControladoraCliente().listarClientes());
        cargarTablaServicioTecnico(this.cp.getControladoraServicioTecnico().listarServiciosTecnico());
        cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());
        cargarTablaServicios(this.cp.getControladoraServicioTecnico().listarServicios());
        cargarTablasEquipos(this.cp.getControladoraServicioTecnico().listarEquipo());

        lbl_valorTotalServicioTecnicoGST.setText("");
        jdch_fechaGST.setDate(new Date());
        jdch_fechaGST.setEnabled(!isEnabled());

        btn_nuevoGST.setEnabled(isEnabled());
        btn_editarGST.setEnabled(isEnabled());
        btn_guardarGST.setEnabled(!isEnabled());
        btn_imprimirGST.setEnabled(isEnabled());
        btn_cancelarGST.setEnabled(!isEnabled());
        btn_salirGST.setEnabled(isEnabled());

        btn_listadotodosEquiposGST.setEnabled(!isEnabled());
        btn_verMisEquiposGST.setEnabled(!isEnabled());
        
        btn_agregarProductoSeleccionadoGST.setEnabled(!isEnabled());
        btn_quitarProductoSeleccionadoGST.setEnabled(!isEnabled());

        btn_agregarServicioSeleccionadoGST.setEnabled(!isEnabled());
        btn_quitarServicioSeleccionadoGST.setEnabled(!isEnabled());

        txtA_cometariosTecnicosGST.setEnabled(!isEnabled());
        txtA_tipoFallaGST.setEnabled(!isEnabled());

        txtA_cometariosTecnicosGST.setText("");
        txtA_tipoFallaGST.setText("");

        
        txt_buscarServicioPorDniGST.setEnabled(isEnabled());
        
        txt_buscarClientePorDniGST.setEnabled(!isEnabled());
        txt_buscarEquipoPorModeloGST.setEnabled(!isEnabled());
        txt_buscarProductoPorNombreGST.setEnabled(!isEnabled());
        txt_buscarServiciosPorNombreGST.setEnabled(!isEnabled());
        txt_cantidadProductoIncluidoGST.setEnabled(!isEnabled());
        cmb_estadoServicioGST.setEnabled(!isEnabled());
        cmb_buscarEquipoPorTipoGST.setEnabled(!isEnabled());
        cmb_buscarProductoPorMarcaGST.setEnabled(!isEnabled());
        cmb_buscarProductoPorTipoGST.setEnabled(!isEnabled());

        cmb_tipoPagoGST.setEnabled(!isEnabled());
        txt_precioPagoGST.setText("");
        txt_cantidadProductoIncluidoGST.setText("");
        btn_agregarPagoGST.setEnabled(!isEnabled());

    }

    public void cambiarEstado() {

        //recargar cambios con los registros nuevos
        Utilidades.cargarCombo(this.cp.getControladoraServicioTecnico().listarTipoEquipo(), cmb_buscarEquipoPorTipoGST);
        Utilidades.cargarCombo(this.cp.getControladoraServicioTecnico().listarEstado(), cmb_estadoServicioGST);
        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_buscarProductoPorMarcaGST);
        cargarTablaClientes(this.cp.getControladoraCliente().listarClientes());
        cargarTablaServicioTecnico(this.cp.getControladoraServicioTecnico().listarServiciosTecnico());
        cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());
        cargarTablaServicios(this.cp.getControladoraServicioTecnico().listarServicios());
        cargarTablasEquipos(this.cp.getControladoraServicioTecnico().listarEquipo());

        btn_nuevoGST.setEnabled(!isEnabled());
        btn_editarGST.setEnabled(!isEnabled());
        btn_guardarGST.setEnabled(isEnabled());
        btn_cancelarGST.setEnabled(isEnabled());

        btn_agregarProductoSeleccionadoGST.setEnabled(isEnabled());
        btn_quitarProductoSeleccionadoGST.setEnabled(isEnabled());
        btn_agregarServicioSeleccionadoGST.setEnabled(isEnabled());
        btn_quitarServicioSeleccionadoGST.setEnabled(isEnabled());

        
        btn_listadotodosEquiposGST.setEnabled(isEnabled());
        btn_verMisEquiposGST.setEnabled(isEnabled());
        
        txt_buscarClientePorDniGST.setEnabled(isEnabled());
        
        txtA_cometariosTecnicosGST.setEnabled(isEnabled());
        txtA_tipoFallaGST.setEnabled(isEnabled());
        txt_buscarClientePorDniGST.setEnabled(isEnabled());
        txt_buscarProductoPorNombreGST.setEnabled(!isEnabled());
        txt_buscarServiciosPorNombreGST.setEnabled(isEnabled());
        txt_cantidadProductoIncluidoGST.setEnabled(isEnabled());
        cmb_estadoServicioGST.setEnabled(isEnabled());
        cmb_buscarEquipoPorTipoGST.setEnabled(isEnabled());
        cmb_buscarProductoPorMarcaGST.setEnabled(isEnabled());
        cmb_buscarProductoPorTipoGST.setEnabled(!isEnabled());
        
        cmb_tipoPagoGST.setEnabled(isEnabled());
        txt_precioPagoGST.setText("");
        txt_cantidadProductoIncluidoGST.setText("");
        btn_agregarPagoGST.setEnabled(isEnabled());

    }

    public void removerFilas(DefaultTableModel model) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void cargarServicioIncluidos(List<ServicioIncluido> serviciosIncluidos) {
        DefaultTableModel tb_cargaServiciosIncluidos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!serviciosIncluidos.isEmpty()) {
            String Cabecera[] = {"Codigo Servicio", "Nombre", "Descripcion", "Precio"};
            tb_cargaServiciosIncluidos.setColumnIdentifiers(Cabecera);
            tb_listadoServiciosIncluidosGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tb_cargaServiciosIncluidos.getColumnCount()];
            for (ServicioIncluido s : serviciosIncluidos) {
                fila[0] = s.getServicio().getCodigo();
                fila[1] = s.getNombreServicio();
                fila[2] = s.getDescripcion();
                fila[3] = s.getPrecio();

                tb_cargaServiciosIncluidos.addRow(fila);
            }

        }

        tb_listadoServiciosIncluidosGST.setModel(tb_cargaServiciosIncluidos);
    }

    public void cargarProductosIncluidos(List<ProductoIncluido> productosIncluidos) {
        DefaultTableModel tb_cargaProductosIncluidos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!productosIncluidos.isEmpty()) {

            String Cabecera[] = {"Codigo Producto", "Nombre", "Marca", "Tipo de Producto", "Descripcion", "Cantidad", "Precio Unitario", "SubTotal"};
            tb_cargaProductosIncluidos.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tb_cargaProductosIncluidos.getColumnCount()];
            tb_listadoProductoIncluidosGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (ProductoIncluido p : productosIncluidos) {
                fila[0] = p.getProducto().getCodigo();
                fila[1] = p.getNombreProducto();
                fila[2] = p.getProducto().getMarca();
                fila[3] = p.getProducto().getTipoProducto();
                fila[4] = p.getProducto().getDescripcion();
                fila[5] = p.getCantidad();
                fila[6] = p.getPrecio();
                fila[7] = p.getCantidad() * p.getPrecio();
                tb_cargaProductosIncluidos.addRow(fila);
            }

        }
        tb_listadoProductoIncluidosGST.setModel(tb_cargaProductosIncluidos);

    }

    public void cargarTablaProductos(List<Producto> productos) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        if (!productos.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre", "Descripcion", "Marca", "Tipo de Producto", "Cantidad", "Precio Unitario"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoProductosGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbCarga.getColumnCount()];
            for (Producto p : productos) {

                if (p.getCantidad() > 0 && p.getPrecio() > 0) {
                    fila[0] = p.getCodigo();
                    fila[1] = p.getNombre();
                    fila[2] = p.getDescripcion();
                    fila[3] = p.getMarca();
                    fila[4] = p.getTipoProducto();
                    fila[5] = p.getCantidad();
                    fila[6] = p.getPrecio();

                    tbCarga.addRow(fila);

                }

            }

        }
        tb_listadoProductosGST.setModel(tbCarga);
    }

    public void cargarTablaServicioTecnico(List<ServicioTecnico> servicioTecnicos) {
        DefaultTableModel tb_cargaServiciosTecnicos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

        if (!servicioTecnicos.isEmpty()) {
            String cabecera[] = {"Codigo", "Fecha Ingreso", "Fecha Modificacion", "Fecha Entrega", "Precio Total", "Estado", " Cliente", "Usuario"};
            tb_listadoServicioTecnicoGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            tb_cargaServiciosTecnicos.setColumnIdentifiers(cabecera);
            Object fila[] = new Object[tb_cargaServiciosTecnicos.getColumnCount()];

            for (ServicioTecnico st : servicioTecnicos) {
                fila[0] = st.getCodigo();
                if (st.getFechaDeIngreso() != null) {
                    fila[1] = formato.format(st.getFechaDeIngreso());
                }
                if (st.getFechaDeModificacion() != null) {
                    fila[2] = formato.format(st.getFechaDeModificacion());
                }
                if (st.getFechaDeEgreso() != null) {
                    fila[3] = formato.format(st.getFechaDeEgreso());
                }

                fila[4] = st.getPrecio();
                fila[5] = st.getEstadoServicio().getNombre();
                fila[6] = st.getCliente().getNombre();
                fila[7] = st.getUsuario().getNombreUsuario();
                tb_cargaServiciosTecnicos.addRow(fila);
            }

        }

        tb_listadoServicioTecnicoGST.setModel(tb_cargaServiciosTecnicos);
    }

    public void cargarTablaServicios(List<Servicio> servicios) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!servicios.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre", "Descripcion", "Precio"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tbCarga.getColumnCount()];
            tb_listadoServiciosGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (Servicio s : servicios) {
                fila[0] = s.getCodigo();
                fila[1] = s.getNombre();
                fila[2] = s.getDescripcion();
                fila[3] = s.getPrecio();
                tbCarga.addRow(fila);
            }

        }
        tb_listadoServiciosGST.setModel(tbCarga);
    }

    public void cargarTablasEquipos(List<Equipo> equipos) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!equipos.isEmpty()) {
            String Cabecera[] = {"Codigo", "Modelo", "Descripcion", "Tipo Equipo"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tbCarga.getColumnCount()];
            tb_listadoEquiposPorClienteGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (Equipo e : equipos) {
                fila[0] = e.getCodigo();
                fila[1] = e.getModelo();
                fila[2] = e.getDescripcion();
                fila[3] = e.getTipoEquipo();
                tbCarga.addRow(fila);
            }

        }
        tb_listadoEquiposPorClienteGST.setModel(tbCarga);
    }

    public void cargarTablaClientes(List<Cliente> clientes) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!clientes.isEmpty()) {

            String Cabecera[] = {"Codigo", "Dni", "Apellido", "Nombre", "Provincia", "Localidad", "Direccion", "Correo", "Telefono"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoClientesGST.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object Fila[] = new Object[tbCarga.getColumnCount()];
            for (Cliente c : clientes) {
                Fila[0] = c.getCodigo();
                Fila[1] = c.getDni();
                Fila[2] = c.getApellido();
                Fila[3] = c.getNombre();
                Fila[4] = c.getProvincia();
                Fila[5] = c.getLocalidad();
                Fila[6] = c.getDireccion();
                Fila[7] = c.getCorreo();
                Fila[8] = c.getTelefono();

                tbCarga.addRow(Fila);
            }

        }
        tb_listadoClientesGST.setModel(tbCarga);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGST = new javax.swing.JPanel();
        lbl_tituloGST = new javax.swing.JLabel();
        pnl_pieGST = new javax.swing.JPanel();
        btn_nuevoGST = new javax.swing.JButton();
        btn_editarGST = new javax.swing.JButton();
        btn_cancelarGST = new javax.swing.JButton();
        btn_guardarGST = new javax.swing.JButton();
        btn_imprimirGST = new javax.swing.JButton();
        btn_salirGST = new javax.swing.JButton();
        pnl_izquierdoGST = new javax.swing.JPanel();
        pnl_derechoGST = new javax.swing.JPanel();
        pnl_centralGST = new javax.swing.JPanel();
        tbp_menuGST = new javax.swing.JTabbedPane();
        pnl_gestionGST = new javax.swing.JPanel();
        pnl_subtotalServicioGST = new javax.swing.JPanel();
        pnl_estadoGST = new javax.swing.JPanel();
        pnl_subtotalGST = new javax.swing.JPanel();
        lbl_totalServicioTecnicoGST = new javax.swing.JLabel();
        lbl_valorTotalServicioTecnicoGST = new javax.swing.JLabel();
        tbp_incluidos = new javax.swing.JTabbedPane();
        pnl_clientesGST = new javax.swing.JPanel();
        pnl_busquedaClienteGST = new javax.swing.JPanel();
        lbl_buscarClientePorDniGST = new javax.swing.JLabel();
        txt_buscarClientePorDniGST = new javax.swing.JTextField();
        pnl_listadoClienteGST = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoClientesGST = new javax.swing.JTable();
        pnl_pieGestionClienteGST = new javax.swing.JPanel();
        pnl_ClienteSeleccionadoGST = new javax.swing.JPanel();
        btn_verMisEquiposGST = new javax.swing.JButton();
        btn_listadotodosEquiposGST = new javax.swing.JButton();
        pnl_equipoGST = new javax.swing.JPanel();
        pnl_listadoEquipoGST = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_listadoEquiposPorClienteGST = new javax.swing.JTable();
        pnl_buscarEquipoGST = new javax.swing.JPanel();
        lbl_buscarEquipoPorTipoGST = new javax.swing.JLabel();
        cmb_buscarEquipoPorTipoGST = new javax.swing.JComboBox();
        lbl_buscarEquipoPorModeloGST = new javax.swing.JLabel();
        txt_buscarEquipoPorModeloGST = new javax.swing.JTextField();
        pnl_gestionEquipoSGT = new javax.swing.JPanel();
        pnl_formularioGST = new javax.swing.JPanel();
        pnl_comentariosGST = new javax.swing.JPanel();
        lbl_tipoFallaGST = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtA_tipoFallaGST = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtA_cometariosTecnicosGST = new javax.swing.JTextArea();
        pnl_estadoServicioGST = new javax.swing.JPanel();
        lbl_estadoServicioGST = new javax.swing.JLabel();
        cmb_estadoServicioGST = new javax.swing.JComboBox();
        pnl_productosIncluidosGST = new javax.swing.JPanel();
        pnl_listadoProductoIncluidosGST = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tb_listadoProductoIncluidosGST = new javax.swing.JTable();
        pnl_busquedaProductoGST = new javax.swing.JPanel();
        pnl_filtradoBusquedaGST = new javax.swing.JPanel();
        lbl_busquedaProductoPorMarcaGST = new javax.swing.JLabel();
        cmb_buscarProductoPorMarcaGST = new javax.swing.JComboBox();
        lbl_busquedaProductoPorTipoGST = new javax.swing.JLabel();
        cmb_buscarProductoPorTipoGST = new javax.swing.JComboBox();
        lbl_buscarProductoPorNombreGST = new javax.swing.JLabel();
        txt_buscarProductoPorNombreGST = new javax.swing.JTextField();
        pnl_botonesAgregadoGST = new javax.swing.JPanel();
        lbl_cantidadProductoGST = new javax.swing.JLabel();
        txt_cantidadProductoIncluidoGST = new javax.swing.JTextField();
        btn_agregarProductoSeleccionadoGST = new javax.swing.JButton();
        btn_quitarProductoSeleccionadoGST = new javax.swing.JButton();
        pnl_tablaProductoGST = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tb_listadoProductosGST = new javax.swing.JTable();
        pnl_servicios_incluidosGST = new javax.swing.JPanel();
        pnl_listadoServicioIncluidoGST = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tb_listadoServiciosIncluidosGST = new javax.swing.JTable();
        pnl_busquedaServicioInculuidosGST = new javax.swing.JPanel();
        pnl_busquedaServicioGST = new javax.swing.JPanel();
        lbl_buscarServicioPorNombreGST = new javax.swing.JLabel();
        txt_buscarServiciosPorNombreGST = new javax.swing.JTextField();
        pnl_botonesAgreadoServicioGST = new javax.swing.JPanel();
        pnl_botonesAgregarProductoGST = new javax.swing.JPanel();
        btn_agregarServicioSeleccionadoGST = new javax.swing.JButton();
        btn_quitarServicioSeleccionadoGST = new javax.swing.JButton();
        pnl_botonesAgregarPagoGST = new javax.swing.JPanel();
        lbl_tipoPagoGSTl = new javax.swing.JLabel();
        cmb_tipoPagoGST = new javax.swing.JComboBox();
        txt_precioPagoGST = new javax.swing.JTextField();
        btn_agregarPagoGST = new javax.swing.JButton();
        pnl_listadoServiciosGST = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tb_listadoServiciosGST = new javax.swing.JTable();
        pnl_encabezadoGestionGST = new javax.swing.JPanel();
        jdch_fechaGST = new com.toedter.calendar.JDateChooser();
        pnl_listadoGST = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_listadoServicioTecnicoGST = new javax.swing.JTable();
        pnl_encabezadoListadoGST = new javax.swing.JPanel();
        lbl_buscarServicioPorClienteGST = new javax.swing.JLabel();
        txt_buscarServicioPorDniGST = new javax.swing.JTextField();
        btn_filtrarServicioPorDniGST = new javax.swing.JButton();

        setBorder(null);
        setIconifiable(true);
        setTitle("Gestion Servicios Tecnicos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pnl_encabezadoGST.setPreferredSize(new java.awt.Dimension(716, 50));
        pnl_encabezadoGST.setLayout(new java.awt.GridLayout(1, 0));

        lbl_tituloGST.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGST.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGST.setText("Gestion de Servicio Tecnico");
        pnl_encabezadoGST.add(lbl_tituloGST);

        getContentPane().add(pnl_encabezadoGST, java.awt.BorderLayout.NORTH);

        pnl_pieGST.setPreferredSize(new java.awt.Dimension(716, 50));
        pnl_pieGST.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGST.setText("Nuevo");
        btn_nuevoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGSTActionPerformed(evt);
            }
        });
        pnl_pieGST.add(btn_nuevoGST);

        btn_editarGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_editarGST.setText("Editar");
        btn_editarGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGSTActionPerformed(evt);
            }
        });
        pnl_pieGST.add(btn_editarGST);

        btn_cancelarGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGST.setText("Cancelar");
        btn_cancelarGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGSTActionPerformed(evt);
            }
        });
        pnl_pieGST.add(btn_cancelarGST);

        btn_guardarGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGST.setText("Guardar");
        btn_guardarGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGSTActionPerformed(evt);
            }
        });
        pnl_pieGST.add(btn_guardarGST);

        btn_imprimirGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_imprimirGST.setText("Imprimir");
        btn_imprimirGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirGSTActionPerformed(evt);
            }
        });
        pnl_pieGST.add(btn_imprimirGST);

        btn_salirGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGST.setText("Salir");
        btn_salirGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGSTActionPerformed(evt);
            }
        });
        pnl_pieGST.add(btn_salirGST);

        getContentPane().add(pnl_pieGST, java.awt.BorderLayout.SOUTH);

        pnl_izquierdoGST.setPreferredSize(new java.awt.Dimension(50, 255));

        javax.swing.GroupLayout pnl_izquierdoGSTLayout = new javax.swing.GroupLayout(pnl_izquierdoGST);
        pnl_izquierdoGST.setLayout(pnl_izquierdoGSTLayout);
        pnl_izquierdoGSTLayout.setHorizontalGroup(
            pnl_izquierdoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGSTLayout.setVerticalGroup(
            pnl_izquierdoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGST, java.awt.BorderLayout.WEST);

        pnl_derechoGST.setPreferredSize(new java.awt.Dimension(50, 255));

        javax.swing.GroupLayout pnl_derechoGSTLayout = new javax.swing.GroupLayout(pnl_derechoGST);
        pnl_derechoGST.setLayout(pnl_derechoGSTLayout);
        pnl_derechoGSTLayout.setHorizontalGroup(
            pnl_derechoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGSTLayout.setVerticalGroup(
            pnl_derechoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGST, java.awt.BorderLayout.EAST);

        pnl_centralGST.setLayout(new java.awt.BorderLayout());

        tbp_menuGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        pnl_gestionGST.setLayout(new java.awt.BorderLayout());

        pnl_subtotalServicioGST.setPreferredSize(new java.awt.Dimension(1117, 70));
        pnl_subtotalServicioGST.setLayout(new java.awt.GridLayout(1, 0));

        pnl_estadoGST.setPreferredSize(new java.awt.Dimension(300, 50));
        pnl_subtotalServicioGST.add(pnl_estadoGST);

        pnl_subtotalGST.setPreferredSize(new java.awt.Dimension(250, 56));

        lbl_totalServicioTecnicoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_totalServicioTecnicoGST.setText("Total:");
        pnl_subtotalGST.add(lbl_totalServicioTecnicoGST);

        lbl_valorTotalServicioTecnicoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        pnl_subtotalGST.add(lbl_valorTotalServicioTecnicoGST);

        pnl_subtotalServicioGST.add(pnl_subtotalGST);

        pnl_gestionGST.add(pnl_subtotalServicioGST, java.awt.BorderLayout.SOUTH);

        tbp_incluidos.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        pnl_clientesGST.setLayout(new java.awt.BorderLayout());

        pnl_busquedaClienteGST.setPreferredSize(new java.awt.Dimension(765, 50));

        lbl_buscarClientePorDniGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarClientePorDniGST.setText("Filtrar Cliente Por Dni: ");
        pnl_busquedaClienteGST.add(lbl_buscarClientePorDniGST);

        txt_buscarClientePorDniGST.setColumns(7);
        txt_buscarClientePorDniGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarClientePorDniGST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_buscarClientePorDniGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarClientePorDniGSTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscarClientePorDniGSTKeyTyped(evt);
            }
        });
        pnl_busquedaClienteGST.add(txt_buscarClientePorDniGST);

        pnl_clientesGST.add(pnl_busquedaClienteGST, java.awt.BorderLayout.NORTH);

        pnl_listadoClienteGST.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 100));

        tb_listadoClientesGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoClientesGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_listadoClientesGST);

        pnl_listadoClienteGST.add(jScrollPane1);

        pnl_clientesGST.add(pnl_listadoClienteGST, java.awt.BorderLayout.CENTER);

        pnl_pieGestionClienteGST.setPreferredSize(new java.awt.Dimension(780, 50));
        pnl_pieGestionClienteGST.setLayout(new java.awt.BorderLayout());

        pnl_ClienteSeleccionadoGST.setPreferredSize(new java.awt.Dimension(250, 50));
        pnl_ClienteSeleccionadoGST.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        btn_verMisEquiposGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_verMisEquiposGST.setText("Ver mis Equipos");
        btn_verMisEquiposGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verMisEquiposGSTActionPerformed(evt);
            }
        });
        pnl_ClienteSeleccionadoGST.add(btn_verMisEquiposGST);

        btn_listadotodosEquiposGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_listadotodosEquiposGST.setText("Todos los Equipos");
        btn_listadotodosEquiposGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_listadotodosEquiposGSTActionPerformed(evt);
            }
        });
        pnl_ClienteSeleccionadoGST.add(btn_listadotodosEquiposGST);

        pnl_pieGestionClienteGST.add(pnl_ClienteSeleccionadoGST, java.awt.BorderLayout.CENTER);

        pnl_clientesGST.add(pnl_pieGestionClienteGST, java.awt.BorderLayout.SOUTH);

        tbp_incluidos.addTab("Cliente", pnl_clientesGST);

        pnl_equipoGST.setLayout(new java.awt.GridLayout(2, 0));

        pnl_listadoEquipoGST.setPreferredSize(new java.awt.Dimension(780, 200));
        pnl_listadoEquipoGST.setLayout(new java.awt.BorderLayout());

        tb_listadoEquiposPorClienteGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoEquiposPorClienteGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tb_listadoEquiposPorClienteGST);

        pnl_listadoEquipoGST.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnl_buscarEquipoGST.setPreferredSize(new java.awt.Dimension(1107, 50));

        lbl_buscarEquipoPorTipoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarEquipoPorTipoGST.setText("Buscar Equipo Por Tipo:");
        pnl_buscarEquipoGST.add(lbl_buscarEquipoPorTipoGST);

        cmb_buscarEquipoPorTipoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_buscarEquipoPorTipoGST.setPreferredSize(new java.awt.Dimension(140, 25));
        cmb_buscarEquipoPorTipoGST.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_buscarEquipoPorTipoGSTItemStateChanged(evt);
            }
        });
        pnl_buscarEquipoGST.add(cmb_buscarEquipoPorTipoGST);

        lbl_buscarEquipoPorModeloGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarEquipoPorModeloGST.setText("Buscar Equipo Por Modelo:");
        pnl_buscarEquipoGST.add(lbl_buscarEquipoPorModeloGST);

        txt_buscarEquipoPorModeloGST.setColumns(15);
        txt_buscarEquipoPorModeloGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarEquipoPorModeloGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarEquipoPorModeloGSTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscarEquipoPorModeloGSTKeyTyped(evt);
            }
        });
        pnl_buscarEquipoGST.add(txt_buscarEquipoPorModeloGST);

        pnl_listadoEquipoGST.add(pnl_buscarEquipoGST, java.awt.BorderLayout.PAGE_START);

        pnl_equipoGST.add(pnl_listadoEquipoGST);

        pnl_gestionEquipoSGT.setLayout(new java.awt.BorderLayout());

        pnl_formularioGST.setLayout(new java.awt.BorderLayout());

        pnl_comentariosGST.setPreferredSize(new java.awt.Dimension(208, 50));

        lbl_tipoFallaGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_tipoFallaGST.setText("Tipo Falla: ");
        pnl_comentariosGST.add(lbl_tipoFallaGST);

        txtA_tipoFallaGST.setColumns(30);
        txtA_tipoFallaGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtA_tipoFallaGST.setLineWrap(true);
        txtA_tipoFallaGST.setRows(2);
        txtA_tipoFallaGST.setWrapStyleWord(true);
        jScrollPane4.setViewportView(txtA_tipoFallaGST);

        pnl_comentariosGST.add(jScrollPane4);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Comentarios Tecnicos: ");
        pnl_comentariosGST.add(jLabel3);

        txtA_cometariosTecnicosGST.setColumns(30);
        txtA_cometariosTecnicosGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtA_cometariosTecnicosGST.setLineWrap(true);
        txtA_cometariosTecnicosGST.setRows(2);
        jScrollPane5.setViewportView(txtA_cometariosTecnicosGST);

        pnl_comentariosGST.add(jScrollPane5);

        pnl_formularioGST.add(pnl_comentariosGST, java.awt.BorderLayout.CENTER);

        lbl_estadoServicioGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_estadoServicioGST.setText("Estado: ");
        pnl_estadoServicioGST.add(lbl_estadoServicioGST);

        cmb_estadoServicioGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_estadoServicioGST.setPreferredSize(new java.awt.Dimension(140, 25));
        pnl_estadoServicioGST.add(cmb_estadoServicioGST);

        pnl_formularioGST.add(pnl_estadoServicioGST, java.awt.BorderLayout.SOUTH);

        pnl_gestionEquipoSGT.add(pnl_formularioGST, java.awt.BorderLayout.CENTER);

        pnl_equipoGST.add(pnl_gestionEquipoSGT);

        tbp_incluidos.addTab("Equipo", pnl_equipoGST);

        pnl_productosIncluidosGST.setLayout(new java.awt.BorderLayout());

        pnl_listadoProductoIncluidosGST.setPreferredSize(new java.awt.Dimension(421, 200));
        pnl_listadoProductoIncluidosGST.setLayout(new java.awt.GridLayout(1, 0));

        tb_listadoProductoIncluidosGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoProductoIncluidosGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tb_listadoProductoIncluidosGST);

        pnl_listadoProductoIncluidosGST.add(jScrollPane7);

        pnl_productosIncluidosGST.add(pnl_listadoProductoIncluidosGST, java.awt.BorderLayout.SOUTH);

        pnl_busquedaProductoGST.setLayout(new java.awt.BorderLayout());

        pnl_filtradoBusquedaGST.setPreferredSize(new java.awt.Dimension(1055, 50));

        lbl_busquedaProductoPorMarcaGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_busquedaProductoPorMarcaGST.setText("Buscar Producto Por Marca: ");
        pnl_filtradoBusquedaGST.add(lbl_busquedaProductoPorMarcaGST);

        cmb_buscarProductoPorMarcaGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_buscarProductoPorMarcaGST.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_buscarProductoPorMarcaGST.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_buscarProductoPorMarcaGSTItemStateChanged(evt);
            }
        });
        cmb_buscarProductoPorMarcaGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_buscarProductoPorMarcaGSTActionPerformed(evt);
            }
        });
        pnl_filtradoBusquedaGST.add(cmb_buscarProductoPorMarcaGST);

        lbl_busquedaProductoPorTipoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_busquedaProductoPorTipoGST.setText("Buscar Producto Por Tipo:");
        pnl_filtradoBusquedaGST.add(lbl_busquedaProductoPorTipoGST);

        cmb_buscarProductoPorTipoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_buscarProductoPorTipoGST.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_buscarProductoPorTipoGST.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_buscarProductoPorTipoGSTItemStateChanged(evt);
            }
        });
        cmb_buscarProductoPorTipoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_buscarProductoPorTipoGSTActionPerformed(evt);
            }
        });
        pnl_filtradoBusquedaGST.add(cmb_buscarProductoPorTipoGST);

        lbl_buscarProductoPorNombreGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarProductoPorNombreGST.setText("Buscar Producto Por Nombre:");
        pnl_filtradoBusquedaGST.add(lbl_buscarProductoPorNombreGST);

        txt_buscarProductoPorNombreGST.setColumns(15);
        txt_buscarProductoPorNombreGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarProductoPorNombreGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarProductoPorNombreGSTKeyReleased(evt);
            }
        });
        pnl_filtradoBusquedaGST.add(txt_buscarProductoPorNombreGST);

        pnl_busquedaProductoGST.add(pnl_filtradoBusquedaGST, java.awt.BorderLayout.PAGE_START);

        pnl_botonesAgregadoGST.setPreferredSize(new java.awt.Dimension(954, 50));

        lbl_cantidadProductoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_cantidadProductoGST.setText("Cantidad:");
        pnl_botonesAgregadoGST.add(lbl_cantidadProductoGST);

        txt_cantidadProductoIncluidoGST.setColumns(5);
        txt_cantidadProductoIncluidoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_cantidadProductoIncluidoGST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidadProductoIncluidoGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadProductoIncluidoGSTKeyTyped(evt);
            }
        });
        pnl_botonesAgregadoGST.add(txt_cantidadProductoIncluidoGST);

        btn_agregarProductoSeleccionadoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarProductoSeleccionadoGST.setText("Agregar");
        btn_agregarProductoSeleccionadoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarProductoSeleccionadoGSTActionPerformed(evt);
            }
        });
        pnl_botonesAgregadoGST.add(btn_agregarProductoSeleccionadoGST);

        btn_quitarProductoSeleccionadoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_quitarProductoSeleccionadoGST.setText("Quitar");
        btn_quitarProductoSeleccionadoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarProductoSeleccionadoGSTActionPerformed(evt);
            }
        });
        pnl_botonesAgregadoGST.add(btn_quitarProductoSeleccionadoGST);

        pnl_busquedaProductoGST.add(pnl_botonesAgregadoGST, java.awt.BorderLayout.SOUTH);

        pnl_tablaProductoGST.setPreferredSize(new java.awt.Dimension(954, 50));
        pnl_tablaProductoGST.setRequestFocusEnabled(false);
        pnl_tablaProductoGST.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane8.setPreferredSize(new java.awt.Dimension(452, 150));

        tb_listadoProductosGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoProductosGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tb_listadoProductosGST);

        pnl_tablaProductoGST.add(jScrollPane8);

        pnl_busquedaProductoGST.add(pnl_tablaProductoGST, java.awt.BorderLayout.CENTER);

        pnl_productosIncluidosGST.add(pnl_busquedaProductoGST, java.awt.BorderLayout.CENTER);

        tbp_incluidos.addTab("Productos", pnl_productosIncluidosGST);

        pnl_servicios_incluidosGST.setLayout(new java.awt.BorderLayout());

        pnl_listadoServicioIncluidoGST.setPreferredSize(new java.awt.Dimension(1107, 100));
        pnl_listadoServicioIncluidoGST.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane9.setPreferredSize(new java.awt.Dimension(452, 350));

        tb_listadoServiciosIncluidosGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoServiciosIncluidosGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(tb_listadoServiciosIncluidosGST);

        pnl_listadoServicioIncluidoGST.add(jScrollPane9);

        pnl_servicios_incluidosGST.add(pnl_listadoServicioIncluidoGST, java.awt.BorderLayout.CENTER);

        pnl_busquedaServicioInculuidosGST.setLayout(new java.awt.BorderLayout());

        pnl_busquedaServicioGST.setPreferredSize(new java.awt.Dimension(863, 50));

        lbl_buscarServicioPorNombreGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarServicioPorNombreGST.setText("Buscar servicio por Nombre:");
        pnl_busquedaServicioGST.add(lbl_buscarServicioPorNombreGST);

        txt_buscarServiciosPorNombreGST.setColumns(15);
        txt_buscarServiciosPorNombreGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarServiciosPorNombreGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarServiciosPorNombreGSTKeyReleased(evt);
            }
        });
        pnl_busquedaServicioGST.add(txt_buscarServiciosPorNombreGST);

        pnl_busquedaServicioInculuidosGST.add(pnl_busquedaServicioGST, java.awt.BorderLayout.PAGE_START);

        pnl_botonesAgreadoServicioGST.setLayout(new java.awt.GridLayout(1, 0));

        btn_agregarServicioSeleccionadoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarServicioSeleccionadoGST.setText("Agregar ");
        btn_agregarServicioSeleccionadoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarServicioSeleccionadoGSTActionPerformed(evt);
            }
        });

        btn_quitarServicioSeleccionadoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_quitarServicioSeleccionadoGST.setText("Quitar");
        btn_quitarServicioSeleccionadoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarServicioSeleccionadoGSTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_botonesAgregarProductoGSTLayout = new javax.swing.GroupLayout(pnl_botonesAgregarProductoGST);
        pnl_botonesAgregarProductoGST.setLayout(pnl_botonesAgregarProductoGSTLayout);
        pnl_botonesAgregarProductoGSTLayout.setHorizontalGroup(
            pnl_botonesAgregarProductoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
            .addGroup(pnl_botonesAgregarProductoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnl_botonesAgregarProductoGSTLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btn_agregarServicioSeleccionadoGST)
                    .addGap(5, 5, 5)
                    .addComponent(btn_quitarServicioSeleccionadoGST)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnl_botonesAgregarProductoGSTLayout.setVerticalGroup(
            pnl_botonesAgregarProductoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(pnl_botonesAgregarProductoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnl_botonesAgregarProductoGSTLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnl_botonesAgregarProductoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btn_agregarServicioSeleccionadoGST)
                        .addComponent(btn_quitarServicioSeleccionadoGST))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnl_botonesAgreadoServicioGST.add(pnl_botonesAgregarProductoGST);

        lbl_tipoPagoGSTl.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_tipoPagoGSTl.setText("(3) Tipo Pago: ");

        cmb_tipoPagoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_tipoPagoGST.setPreferredSize(new java.awt.Dimension(130, 25));

        txt_precioPagoGST.setColumns(5);
        txt_precioPagoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_precioPagoGST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precioPagoGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioPagoGSTKeyTyped(evt);
            }
        });

        btn_agregarPagoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarPagoGST.setText("Agregar Pago");
        btn_agregarPagoGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarPagoGSTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_botonesAgregarPagoGSTLayout = new javax.swing.GroupLayout(pnl_botonesAgregarPagoGST);
        pnl_botonesAgregarPagoGST.setLayout(pnl_botonesAgregarPagoGSTLayout);
        pnl_botonesAgregarPagoGSTLayout.setHorizontalGroup(
            pnl_botonesAgregarPagoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 553, Short.MAX_VALUE)
            .addGroup(pnl_botonesAgregarPagoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnl_botonesAgregarPagoGSTLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lbl_tipoPagoGSTl)
                    .addGap(5, 5, 5)
                    .addComponent(cmb_tipoPagoGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(txt_precioPagoGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(5, 5, 5)
                    .addComponent(btn_agregarPagoGST)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnl_botonesAgregarPagoGSTLayout.setVerticalGroup(
            pnl_botonesAgregarPagoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(pnl_botonesAgregarPagoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnl_botonesAgregarPagoGSTLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(pnl_botonesAgregarPagoGSTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_botonesAgregarPagoGSTLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(lbl_tipoPagoGSTl))
                        .addGroup(pnl_botonesAgregarPagoGSTLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(cmb_tipoPagoGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnl_botonesAgregarPagoGSTLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txt_precioPagoGST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btn_agregarPagoGST))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnl_botonesAgreadoServicioGST.add(pnl_botonesAgregarPagoGST);

        pnl_busquedaServicioInculuidosGST.add(pnl_botonesAgreadoServicioGST, java.awt.BorderLayout.SOUTH);

        pnl_listadoServiciosGST.setPreferredSize(new java.awt.Dimension(863, 150));
        pnl_listadoServiciosGST.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane10.setPreferredSize(new java.awt.Dimension(452, 150));

        tb_listadoServiciosGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoServiciosGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane10.setViewportView(tb_listadoServiciosGST);

        pnl_listadoServiciosGST.add(jScrollPane10);

        pnl_busquedaServicioInculuidosGST.add(pnl_listadoServiciosGST, java.awt.BorderLayout.CENTER);

        pnl_servicios_incluidosGST.add(pnl_busquedaServicioInculuidosGST, java.awt.BorderLayout.NORTH);

        tbp_incluidos.addTab("Servicio", pnl_servicios_incluidosGST);

        pnl_gestionGST.add(tbp_incluidos, java.awt.BorderLayout.CENTER);

        pnl_encabezadoGestionGST.setPreferredSize(new java.awt.Dimension(738, 50));
        pnl_encabezadoGestionGST.setLayout(new java.awt.BorderLayout());

        jdch_fechaGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jdch_fechaGST.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_encabezadoGestionGST.add(jdch_fechaGST, java.awt.BorderLayout.EAST);

        pnl_gestionGST.add(pnl_encabezadoGestionGST, java.awt.BorderLayout.NORTH);

        tbp_menuGST.addTab("Gestion", pnl_gestionGST);

        pnl_listadoGST.setLayout(new java.awt.BorderLayout());

        tb_listadoServicioTecnicoGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoServicioTecnicoGST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_listadoServicioTecnicoGST);

        pnl_listadoGST.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pnl_encabezadoListadoGST.setPreferredSize(new java.awt.Dimension(770, 50));

        lbl_buscarServicioPorClienteGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarServicioPorClienteGST.setText("Buscar Servicio Tecnico Por Dni: ");
        pnl_encabezadoListadoGST.add(lbl_buscarServicioPorClienteGST);

        txt_buscarServicioPorDniGST.setColumns(6);
        txt_buscarServicioPorDniGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarServicioPorDniGST.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_buscarServicioPorDniGST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarServicioPorDniGSTKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscarServicioPorDniGSTKeyTyped(evt);
            }
        });
        pnl_encabezadoListadoGST.add(txt_buscarServicioPorDniGST);

        btn_filtrarServicioPorDniGST.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_filtrarServicioPorDniGST.setText("Filtrar");
        btn_filtrarServicioPorDniGST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrarServicioPorDniGSTActionPerformed(evt);
            }
        });
        pnl_encabezadoListadoGST.add(btn_filtrarServicioPorDniGST);

        pnl_listadoGST.add(pnl_encabezadoListadoGST, java.awt.BorderLayout.PAGE_START);

        tbp_menuGST.addTab("Listado", pnl_listadoGST);

        pnl_centralGST.add(tbp_menuGST, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGST, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_buscarProductoPorMarcaGSTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorMarcaGSTItemStateChanged
        if (cmb_buscarProductoPorMarcaGST.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorMarcaGST.getSelectedItem() != null) {
            Marca marca = (Marca) cmb_buscarProductoPorMarcaGST.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarca(marca));
            Utilidades.cargarCombo(marca.getTiposProductos(), cmb_buscarProductoPorTipoGST);
            cmb_buscarProductoPorTipoGST.setEnabled(isEnabled());
        } else {
            cmb_buscarProductoPorTipoGST.removeAllItems();
            cmb_buscarProductoPorTipoGST.addItem("[SELECCIONAR]");
            cmb_buscarProductoPorTipoGST.setEnabled(!isEnabled());
            cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());

        }
    }//GEN-LAST:event_cmb_buscarProductoPorMarcaGSTItemStateChanged

    private void cmb_buscarProductoPorMarcaGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorMarcaGSTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_buscarProductoPorMarcaGSTActionPerformed

    private void cmb_buscarProductoPorTipoGSTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorTipoGSTItemStateChanged
        if (cmb_buscarProductoPorTipoGST.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorTipoGST.getSelectedItem() != null) {
            Marca marca = (Marca) cmb_buscarProductoPorMarcaGST.getSelectedItem();
            TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGST.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarcayTipo(marca, tipo));
            txt_buscarProductoPorNombreGST.setEnabled(isEnabled());
        } else {
            if (cmb_buscarProductoPorMarcaGST.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorMarcaGST.getSelectedItem() != null) {
                Marca marca = (Marca) cmb_buscarProductoPorMarcaGST.getSelectedItem();
                cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarca(marca));
            }

            txt_buscarProductoPorNombreGST.setText("");
            txt_buscarProductoPorNombreGST.setEnabled(!isEnabled());
        }
    }//GEN-LAST:event_cmb_buscarProductoPorTipoGSTItemStateChanged

    private void txt_buscarProductoPorNombreGSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarProductoPorNombreGSTKeyReleased
        if (!txt_buscarProductoPorNombreGST.getText().trim().isEmpty()) {
            String nombre = txt_buscarProductoPorNombreGST.getText().trim();
            Marca marca = (Marca) cmb_buscarProductoPorMarcaGST.getSelectedItem();
            TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGST.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProducto(nombre, marca, tipo));
        } else {
            if (cmb_buscarProductoPorTipoGST.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorTipoGST.getSelectedItem() != null) {
                Marca marca = (Marca) cmb_buscarProductoPorMarcaGST.getSelectedItem();
                TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGST.getSelectedItem();
                cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarcayTipo(marca, tipo));
            }

        }
    }//GEN-LAST:event_txt_buscarProductoPorNombreGSTKeyReleased

    private void btn_nuevoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGSTActionPerformed
        this.cambiarEstado();
        tb_listadoServicioTecnicoGST.clearSelection();
    }//GEN-LAST:event_btn_nuevoGSTActionPerformed

    private void btn_editarGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGSTActionPerformed
        try {
            if (tb_listadoServicioTecnicoGST.getSelectedRow() > -1 && tb_listadoServicioTecnicoGST.getSelectedRowCount() == 1) {
                Integer seleccionado = (Integer) tb_listadoServicioTecnicoGST.getValueAt(tb_listadoServicioTecnicoGST.getSelectedRow(), 0);
                servicioTecnicoED = this.cp.getControladoraServicioTecnico().buscarServicioTecnicoPorCodigo(seleccionado);

                if (servicioTecnicoED.getEstadoServicio().getNombre().equals("Entregado")) {
                    throw new Exception("No se puede editar un servicio tecnico entregado");
                }

                if (servicioTecnicoED != null) {
                    this.cambiarEstado();

                    for (int i = 0; i < tb_listadoClientesGST.getRowCount(); i++) {
                        if (tb_listadoClientesGST.getValueAt(i, 1).equals(servicioTecnicoED.getCliente().getDni())) {
                            tb_listadoClientesGST.changeSelection(i, 1, false, false);
                            break;
                        }
                    }

                    for (int i = 0; i < tb_listadoEquiposPorClienteGST.getRowCount(); i++) {
                        if (tb_listadoEquiposPorClienteGST.getValueAt(i, 0).equals(servicioTecnicoED.getEquipo().getCodigo())) {
                            tb_listadoEquiposPorClienteGST.changeSelection(i, 1, false, false);
                            break;
                        }
                    }

                    cmb_estadoServicioGST.setSelectedItem(servicioTecnicoED.getEstadoServicio());
                    txtA_cometariosTecnicosGST.setText(servicioTecnicoED.getComentarioTecnicos());
                    txtA_tipoFallaGST.setText(servicioTecnicoED.getTipoFalla());
                    productoIncluidos = servicioTecnicoED.getProductoIncluido();
                    servicioIncluidos = servicioTecnicoED.getServicioIncluido();
                    cargarProductosIncluidos(productoIncluidos);
                    cargarServicioIncluidos(servicioIncluidos);
                    lbl_valorTotalServicioTecnicoGST.setText(String.valueOf(servicioTecnicoED.getPrecio()));

                    editando = true;
                    tb_listadoServicioTecnicoGST.clearSelection();
                }

            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Servicio Tecnico", "Gestion de Servicios Tecnicos", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btn_editarGSTActionPerformed

    private void btn_cancelarGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGSTActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGSTActionPerformed

    private void btn_salirGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGSTActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGSTActionPerformed

    private void btn_agregarServicioSeleccionadoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarServicioSeleccionadoGSTActionPerformed
        try {
            boolean registrado = false;

            if (tb_listadoServiciosGST.getSelectedRowCount() <= 0) {

                throw new Exception("Debe seleccionar un servicio");

            }
            if (tb_listadoServiciosGST.getSelectedRowCount() >= 2) {

                throw new Exception("Debe seleccionar un solo servicio");

            }

            if (tb_listadoServiciosGST.getSelectedRowCount() == 1) {
                int codigo = (int) tb_listadoServiciosGST.getValueAt(tb_listadoServiciosGST.getSelectedRow(), 0);
                Servicio servicioSeleccionado = this.cp.getControladoraServicioTecnico().buscarServicioPorCodigo(codigo);
                for (ServicioIncluido servicioIncluido : servicioIncluidos) {
                    if (servicioIncluido.getServicio().getCodigo() == servicioSeleccionado.getCodigo()) {
                        registrado = true;
                        break;
                    }
                }

                if (registrado == false) {
                    ServicioIncluido servicioIncluido = new ServicioIncluido(servicioSeleccionado.getNombre(), servicioSeleccionado.getDescripcion(), servicioSeleccionado.getPrecio(), servicioSeleccionado);
                    servicioIncluidos.add(servicioIncluido);
                    cargarServicioIncluidos(servicioIncluidos);
                    lbl_valorTotalServicioTecnicoGST.setText(String.valueOf(productoIncluidoSum.sumarProductosIncluidos(productoIncluidos) + servicioIncluidoSum.sumarSubtotal(servicioIncluidos)));
                } else {
                    throw new Exception("El servicio ya esta agregado");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }
        tb_listadoServiciosGST.clearSelection();

    }//GEN-LAST:event_btn_agregarServicioSeleccionadoGSTActionPerformed

    private void btn_agregarProductoSeleccionadoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarProductoSeleccionadoGSTActionPerformed
        try {
            boolean registrado = false;

            if (tb_listadoProductosGST.getSelectedRowCount() <= 0) {

                throw new Exception("Debe seleccionar un producto");

            }
            if (tb_listadoProductosGST.getSelectedRowCount() >= 2) {

                throw new Exception("Debe seleccionar un solo producto");

            }

            if (tb_listadoProductosGST.getSelectedRowCount() == 1) {

                int codigo = (int) tb_listadoProductosGST.getValueAt(tb_listadoProductosGST.getSelectedRow(), 0);
                Producto productoSeleccionado = this.cp.getControladoraProducto().buscarProductoPorCodigo(codigo);

                for (ProductoIncluido productoIncluido : productoIncluidos) {
                    if (productoIncluido.getProducto().getCodigo() == productoSeleccionado.getCodigo()) {
                        registrado = true;
                        break;
                    }
                }

                if (registrado == false) {
                    Integer cantidadProducto = Integer.valueOf(txt_cantidadProductoIncluidoGST.getText().trim());

                    if (cantidadProducto > productoSeleccionado.getCantidad()) {
                        throw new Exception("No se puede agregar una cantidad mayor al existente ");
                    }
                    ProductoIncluido productoIncluido = new ProductoIncluido(productoSeleccionado.getNombre(), productoSeleccionado.getDescripcion(), cantidadProducto, productoSeleccionado.getPrecio(), cantidadProducto * productoSeleccionado.getPrecio(), productoSeleccionado);
                    productoIncluidos.add(productoIncluido);
                    cargarProductosIncluidos(productoIncluidos);
                    txt_cantidadProductoIncluidoGST.setText("");
                    lbl_valorTotalServicioTecnicoGST.setText(String.valueOf(productoIncluidoSum.sumarProductosIncluidos(productoIncluidos) + servicioIncluidoSum.sumarSubtotal(servicioIncluidos)));
                } else {
                    throw new Exception("El producto ya esta agregado");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }
        tb_listadoProductosGST.clearSelection();
    }//GEN-LAST:event_btn_agregarProductoSeleccionadoGSTActionPerformed

    private void btn_quitarProductoSeleccionadoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarProductoSeleccionadoGSTActionPerformed
        try {

            if (tb_listadoProductoIncluidosGST.getSelectedRowCount() <= 0) {

                throw new Exception("Debe Seleccionar un producto");

            }
            if (tb_listadoProductoIncluidosGST.getSelectedRowCount() >= 2) {

                throw new Exception("Debe seleccionar un solo producto");

            }

            if (tb_listadoProductoIncluidosGST.getSelectedRowCount() == 1) {
                int codigo = (int) tb_listadoProductoIncluidosGST.getValueAt(tb_listadoProductoIncluidosGST.getSelectedRow(), 0);
                Producto productoSeleccionado = this.cp.getControladoraProducto().buscarProductoPorCodigo(codigo);
                for (ProductoIncluido productoIncluido : productoIncluidos) {
                    if (productoIncluido.getProducto().getCodigo() == productoSeleccionado.getCodigo()) {
                        lbl_valorTotalServicioTecnicoGST.setText(String.valueOf(productoIncluidoSum.restarSubtotal(productoIncluidos, productoIncluido.getSubtotal()) + servicioIncluidoSum.sumarSubtotal(servicioIncluidos)));
                        productosIncluidosEl.add(productoIncluido);
                        productoIncluidos.remove(productoIncluido);
                        break;
                    }
                }
                cargarProductosIncluidos(productoIncluidos);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }
        tb_listadoProductoIncluidosGST.clearSelection();

    }//GEN-LAST:event_btn_quitarProductoSeleccionadoGSTActionPerformed

    private void btn_quitarServicioSeleccionadoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarServicioSeleccionadoGSTActionPerformed
        try {

            if (tb_listadoServiciosIncluidosGST.getSelectedRowCount() <= 0) {

                throw new Exception("Debe seleccionar un servicio");

            }
            if (tb_listadoServiciosIncluidosGST.getSelectedRowCount() >= 2) {

                throw new Exception("Debe seleccionar un solo servicio");

            }

            if (tb_listadoServiciosIncluidosGST.getSelectedRowCount() == 1) {
                int codigo = (int) tb_listadoServiciosIncluidosGST.getValueAt(tb_listadoServiciosIncluidosGST.getSelectedRow(), 0);
                Servicio servicioSeleccionado = this.cp.getControladoraServicioTecnico().buscarServicioPorCodigo(codigo);
                for (ServicioIncluido servicioIncluido : servicioIncluidos) {
                    if (servicioIncluido.getServicio().getCodigo() == servicioSeleccionado.getCodigo()) {
                        lbl_valorTotalServicioTecnicoGST.setText(String.valueOf(productoIncluidoSum.sumarProductosIncluidos(productoIncluidos) - servicioIncluidoSum.restarSubtotal(servicioIncluidos, servicioIncluido.getPrecio())));
                        servicioIncluidosEl.add(servicioIncluido);
                        servicioIncluidos.remove(servicioIncluido);
                        break;
                    }
                }

                cargarServicioIncluidos(servicioIncluidos);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_quitarServicioSeleccionadoGSTActionPerformed

    private void btn_guardarGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGSTActionPerformed
        try {
            Cliente cliente = null;
            Equipo equipo = null;
            Date fechaActual = jdch_fechaGST.getDate();
            String comentariosTecnicos = "";
            String TipoFalla = "";
            EstadoServicio estadoServicio = null;

            if (tb_listadoClientesGST.getSelectedRowCount() <= 0) {
                throw new Exception("Debe seleccionar un cliente");

            }

            if (tb_listadoClientesGST.getSelectedRowCount() > 1) {
                throw new Exception("Debe seleccionar un solo cliente");
            }

            if (tb_listadoClientesGST.getSelectedRowCount() == 1) {
                int dniCliente = (int) tb_listadoClientesGST.getValueAt(tb_listadoClientesGST.getSelectedRow(), 1);
                cliente = this.cp.getControladoraCliente().buscarClientePorDni(dniCliente);
            }

            if (tb_listadoEquiposPorClienteGST.getSelectedRowCount() > 2) {
                throw new Exception("Debe seleccionar un solo equipo");
            }

            if (tb_listadoEquiposPorClienteGST.getSelectedRowCount() <= 0) {
                throw new Exception("Debe selecionar un solo equipo");
            }

            if (tb_listadoClientesGST.getSelectedRowCount() == 1) {
                int codigoEquipo = (int) tb_listadoEquiposPorClienteGST.getValueAt(tb_listadoEquiposPorClienteGST.getSelectedRow(), 0);
                equipo = this.cp.getControladoraServicioTecnico().buscarEquipoPorCodigo(codigoEquipo);
            }

            if (!txtA_cometariosTecnicosGST.getText().trim().isEmpty() && !txtA_tipoFallaGST.getText().trim().isEmpty() && cmb_estadoServicioGST.getSelectedItem() != null && cmb_estadoServicioGST.getSelectedItem() != "[SELECCIONAR]") {
                comentariosTecnicos = txtA_cometariosTecnicosGST.getText().trim();
                TipoFalla = txtA_tipoFallaGST.getText().trim();
                estadoServicio = (EstadoServicio) cmb_estadoServicioGST.getSelectedItem();
            } else {
                throw new Exception("Todos los campos son obligatorios");
            }

            if (editando == true) {

                if (!productoIncluidos.isEmpty()) {
                    for (ProductoIncluido productoIncluido1 : productoIncluidos) {
                        if (productoIncluido1.getCodigo() == 0) {
                            productoIncluido1.setServicioTecnico(servicioTecnicoED);
                            this.cp.getControladoraServicioTecnico().registrarProductoIncluido(productoIncluido1);
                            this.cp.getControladoraProducto().vendiProducto(productoIncluido1.getCantidad(), productoIncluido1.getProducto().getCodigo());
                        }

                    }

                }

                if (!productosIncluidosEl.isEmpty()) {
                    for (ProductoIncluido productoIncluido : productosIncluidosEl) {

                        this.cp.getControladoraProducto().compreProducto(productoIncluido.getCantidad(), productoIncluido.getProducto().getCodigo());
                        this.cp.getControladoraServicioTecnico().eliminarProductoEliminado(productoIncluido);
                    }
                }

                if (!servicioIncluidos.isEmpty()) {
                    for (ServicioIncluido servicioIncluido1 : servicioIncluidos) {
                        if (servicioIncluido1.getCodigo() == 0) {
                            servicioIncluido1.setServicioTecnico(servicioTecnicoED);
                            this.cp.getControladoraServicioTecnico().registrarServicioIncluido(servicioIncluido1);
                        }
                    }

                }

                if (!servicioIncluidosEl.isEmpty()) {
                    for (ServicioIncluido servicioIncluido : servicioIncluidosEl) {
                        this.cp.getControladoraServicioTecnico().eliminarServicioIncluido(servicioIncluido);
                    }
                }

                if (estadoServicio.getNombre().equals("Entregado") && servicioIncluidos.isEmpty()) {
                    throw new Exception("Debe agregar al menos un servicio ");
                }

                if (estadoServicio.getNombre().equals("Entregado") && pagos.isEmpty()) {
                    throw new Exception("Debe agregar al menos un pago");
                }

                for (Pago pago : pagos) {
                    pago.setServiciotecnico(servicioTecnicoED);
                    this.cp.getControladoraVenta().registarPago(pago);
                }

                servicioTecnicoED.setFechaDeModificacion(fechaActual);

                if (servicioTecnicoED.getFechaDeEgreso() == null && estadoServicio.getNombre().equals("Entregado")) {
                    servicioTecnicoED.setFechaDeEgreso(fechaActual);
                }

                float TotalServicio = productoIncluidoSum.sumarProductosIncluidos(productoIncluidos) + servicioIncluidoSum.sumarSubtotal(servicioIncluidos);
                servicioTecnicoED.setUltimaModificacionPor(this.usuario.getNombreUsuario());
                servicioTecnicoED.setTipoFalla(TipoFalla);
                servicioTecnicoED.setComentarioTecnicos(comentariosTecnicos);
                servicioTecnicoED.setEstadoServicio(estadoServicio);
                servicioTecnicoED.setPrecio(TotalServicio);
                servicioTecnicoED.setProductoIncluido(productoIncluidos);
                servicioTecnicoED.setServicioIncluido(servicioIncluidos);
                this.cp.getControladoraServicioTecnico().modificarServicioTecnico(servicioTecnicoED);

                JOptionPane.showMessageDialog(null, "Editado con Exito", "Gestion de Servicio Tecnico", JOptionPane.INFORMATION_MESSAGE);

                if (estadoServicio.getNombre().equals("Entregado") && !pagos.isEmpty()) {
                    JFrame visualizador = new JFrame();
                    Reporte reporte = new Reporte();
                    Map parametros = new HashMap();

                    parametros.put("idServicioTecnicomain", servicioTecnicoED.getCodigo());
                    visualizador.setContentPane(reporte.crearReporteServicioTecnico("/reportes/reporteComprobanteTecnico.jasper", parametros));
                    visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    visualizador.setVisible(true);
                }

            } else {

                float TotalServicio = productoIncluidoSum.sumarProductosIncluidos(productoIncluidos) + servicioIncluidoSum.sumarSubtotal(servicioIncluidos);

                boolean equipoRegistrado = false;
                equiposPorCliente = cliente.getEquipos();

                for (Equipo equipos : equiposPorCliente) {
                    if (equipos.getCodigo() == equipo.getCodigo()) {
                        equipoRegistrado = true;
                        break;
                    }

                }

                if (equipoRegistrado == false) {
                    equiposPorCliente.add(equipo);
                    cliente.setEquipos(equiposPorCliente);
                    this.cp.getControladoraCliente().modificarCliente(cliente);
                }

                ServicioTecnico servicioTecnico = new ServicioTecnico(fechaActual, comentariosTecnicos, TipoFalla, TotalServicio, usuario.getNombreUsuario(), usuario, estadoServicio, cliente, equipo);
                servicioTecnico = this.cp.getControladoraServicioTecnico().registrarServicioTecnico(servicioTecnico);

                if (!productoIncluidos.isEmpty()) {
                    for (ProductoIncluido productoIncluido1 : productoIncluidos) {
                        productoIncluido1.setServicioTecnico(servicioTecnico);
                        this.cp.getControladoraServicioTecnico().registrarProductoIncluido(productoIncluido1);
                        this.cp.getControladoraProducto().vendiProducto(productoIncluido1.getCantidad(), productoIncluido1.getProducto().getCodigo());
                    }

                }

                if (!servicioIncluidos.isEmpty()) {
                    for (ServicioIncluido servicioIncluido1 : servicioIncluidos) {
                        servicioIncluido1.setServicioTecnico(servicioTecnico);
                        this.cp.getControladoraServicioTecnico().registrarServicioIncluido(servicioIncluido1);
                    }
                }
                JOptionPane.showMessageDialog(null, "Creado con Exito", "Gestion de Servicio Tecnico", JOptionPane.INFORMATION_MESSAGE);

                JFrame visualizador = new JFrame();
                Reporte reporte = new Reporte();
                Map parametros = new HashMap();

                parametros.put("idServicioTecnicomain", servicioTecnico.getCodigo());
                visualizador.setContentPane(reporte.crearReporteServicioTecnico("/reportes/reporteComprobanteTecnico.jasper", parametros));
                visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                visualizador.setVisible(true);

            }

            this.estadoInicial();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarGSTActionPerformed

    private void btn_verMisEquiposGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verMisEquiposGSTActionPerformed
        try {
            if (editando == false) {
                if (tb_listadoClientesGST.getSelectedRowCount() == 1) {
                    int codigoCliente = (int) tb_listadoClientesGST.getValueAt(tb_listadoClientesGST.getSelectedRow(), 0);
                    Cliente cliente = this.cp.getControladoraCliente().buscarClientePorCodigo(codigoCliente);
                    List<Equipo> equipos = cliente.getEquipos();
                    
                    if(equipos.isEmpty()){
                        throw new Exception("El cliente no tiene equipos asignados");
                    }
                    
                    cargarTablasEquipos(equipos);
                } else {
                    if (tb_listadoClientesGST.getSelectedRowCount() <= 0) {
                        throw new Exception("Debe seleccionar un cliente para ver sus equipos");

                    } else if (tb_listadoClientesGST.getSelectedRowCount() > 1) {
                        throw new Exception("Debe seleccionar un solo cliente para ver sus equipos");

                    }
                }

            } else {
                throw new Exception("Solo puede visualizar los equipos de cliente para un nuevo servicio");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btn_verMisEquiposGSTActionPerformed

    private void txt_buscarServicioPorDniGSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarServicioPorDniGSTKeyReleased

    }//GEN-LAST:event_txt_buscarServicioPorDniGSTKeyReleased

    private void btn_filtrarServicioPorDniGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarServicioPorDniGSTActionPerformed

        if (!txt_buscarServicioPorDniGST.getText().trim().isEmpty()) {
            Integer dni = Integer.valueOf(txt_buscarServicioPorDniGST.getText().trim());
            Cliente cliente = this.cp.getControladoraCliente().buscarClientePorDni(dni);
            try {
                cargarTablaServicioTecnico(this.cp.getControladoraServicioTecnico().filtrarServiciosPorCliente(cliente));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " No se encontro un servicio tecnico con este dni", "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            cargarTablaServicioTecnico(this.cp.getControladoraServicioTecnico().listarServiciosTecnico());

        }


    }//GEN-LAST:event_btn_filtrarServicioPorDniGSTActionPerformed

    private void cmb_buscarEquipoPorTipoGSTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_buscarEquipoPorTipoGSTItemStateChanged
        if (cmb_buscarEquipoPorTipoGST.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarEquipoPorTipoGST.getSelectedItem() != null) {
            TipoEquipo tipoEquipo = (TipoEquipo) cmb_buscarEquipoPorTipoGST.getSelectedItem();
            cargarTablasEquipos(this.cp.getControladoraServicioTecnico().BuscarEquipoPorTipo(tipoEquipo));
            txt_buscarEquipoPorModeloGST.setEnabled(isEnabled());
        } else {
            cargarTablasEquipos(this.cp.getControladoraServicioTecnico().listarEquipo());
            txt_buscarEquipoPorModeloGST.setText("");
            txt_buscarEquipoPorModeloGST.setEnabled(!isEnabled());
        }


    }//GEN-LAST:event_cmb_buscarEquipoPorTipoGSTItemStateChanged

    private void txt_buscarEquipoPorModeloGSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarEquipoPorModeloGSTKeyReleased
        if (!txt_buscarEquipoPorModeloGST.getText().isEmpty()) {
            String modelo = txt_buscarEquipoPorModeloGST.getText().trim();
            TipoEquipo tipoEquipo = (TipoEquipo) cmb_buscarEquipoPorTipoGST.getSelectedItem();
            cargarTablasEquipos(this.cp.getControladoraServicioTecnico().filtrarEquipoPorTodo(tipoEquipo, modelo));
        }
    }//GEN-LAST:event_txt_buscarEquipoPorModeloGSTKeyReleased

    private void txt_buscarEquipoPorModeloGSTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarEquipoPorModeloGSTKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarEquipoPorModeloGSTKeyTyped

    private void txt_buscarClientePorDniGSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarClientePorDniGSTKeyReleased
        if (!txt_buscarClientePorDniGST.getText().trim().isEmpty()) {
            Integer dni = Integer.valueOf(txt_buscarClientePorDniGST.getText().trim());
            cargarTablaClientes(this.cp.getControladoraCliente().filtrarClientePorDni(dni));
        } else {
            cargarTablaClientes(this.cp.getControladoraCliente().listarClientes());
        }


    }//GEN-LAST:event_txt_buscarClientePorDniGSTKeyReleased

    private void btn_imprimirGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirGSTActionPerformed
        try {
            if (tb_listadoServicioTecnicoGST.getSelectedRowCount() == 1) {
                JFrame visualizador = new JFrame();
                Reporte reporte = new Reporte();
                Map parametros = new HashMap();

                int codigoServicioTecnico = (int) tb_listadoServicioTecnicoGST.getValueAt(tb_listadoServicioTecnicoGST.getSelectedRow(), 0);

                parametros.put("idServicioTecnicomain", codigoServicioTecnico);
                visualizador.setContentPane(reporte.crearReporteServicioTecnico("/reportes/reporteComprobanteTecnico.jasper", parametros));
                visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                visualizador.setVisible(true);

            } else {
                if (tb_listadoServicioTecnicoGST.getSelectedRowCount() >= 2) {
                    throw new Exception("Debe seleccionar un solo servicio tecnico");
                }

                if (tb_listadoServicioTecnicoGST.getSelectedRowCount() <= 0) {
                    throw new Exception("Debe seleccionar al menos un servicio tecnico");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_imprimirGSTActionPerformed

    private void btn_listadotodosEquiposGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_listadotodosEquiposGSTActionPerformed
        try {
            if (editando == false) {

                List<Equipo> equipos = this.cp.getControladoraServicioTecnico().listarEquipo();
                cargarTablasEquipos(equipos);

            } else {
                throw new Exception("Solo puede visualizar los equipos de cliente para un nuevo servicio");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_listadotodosEquiposGSTActionPerformed

    private void txt_buscarClientePorDniGSTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarClientePorDniGSTKeyTyped
        Utilidades.DelimitarNumerosDni(evt, txt_buscarClientePorDniGST);
    }//GEN-LAST:event_txt_buscarClientePorDniGSTKeyTyped

    private void txt_buscarServicioPorDniGSTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarServicioPorDniGSTKeyTyped
        Utilidades.DelimitarNumerosDni(evt, txt_buscarServicioPorDniGST);
    }//GEN-LAST:event_txt_buscarServicioPorDniGSTKeyTyped

    private void cmb_buscarProductoPorTipoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorTipoGSTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_buscarProductoPorTipoGSTActionPerformed

    private void btn_agregarPagoGSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarPagoGSTActionPerformed
        try {
            if (!txt_precioPagoGST.getText().trim().isEmpty() && cmb_tipoPagoGST.getSelectedItem() != null
                    && cmb_tipoPagoGST.getSelectedItem() != "[SELECCIONAR]") {

                ServicioIncluido servicioIncluido = new ServicioIncluido();
                ProductoIncluido productoIncluido = new ProductoIncluido();

                EstadoServicio estado = (EstadoServicio) cmb_estadoServicioGST.getSelectedItem();

                if (pagos.size() <= 2 && estado.getNombre().equals("Entregado")) {
                    if (tb_listadoServiciosIncluidosGST.getRowCount() >= 1) {

                        if (precioServicios == 0) {
                            precioServicios += servicioIncluido.sumarSubtotal(servicioIncluidos);
                        }

                    }

                    if (tb_listadoProductoIncluidosGST.getRowCount() >= 1) {

                        if (preciosProductos == 0) {
                            preciosProductos += productoIncluido.sumarProductosIncluidos(productoIncluidos);

                        }

                    }

                    if (tb_listadoServiciosIncluidosGST.getRowCount() == 0) {
                        throw new Exception("Para agregar un pago debe agregar por lo menos un servicio ");
                    }

                    precioTotal= precioServicios+ preciosProductos;
                    
                    TipoPago tipopago = (TipoPago) cmb_tipoPagoGST.getSelectedItem();
                    float precioPago = Float.valueOf(txt_precioPagoGST.getText().trim());

                    Pago pago = new Pago(precioPago, tipopago);

                    if (precioTotal - pago.getPrecio() >= 0) {
                        pagos.add(pago);

                        precioTotal -= pago.getPrecio();

                        lbl_valorTotalServicioTecnicoGST.setText(String.valueOf(precioTotal));

                    }else{
                        throw new Exception("No se pudo agregar el pago");
                    }

                    txt_precioPagoGST.setText("");
                    Utilidades.cargarCombo(this.cp.getControladoraVenta().listarTipoPago(), cmb_tipoPagoGST);

                    JOptionPane.showMessageDialog(null, "Pago agregado correctamente", "Gestion Servicio Tecnico", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    if (pagos.size() >= 3) {

                        throw new Exception("No puede agregar mas de tres formas de pago");

                    }

                    if (!estado.getNombre().equals("Entregado")) {
                        throw new Exception("No se puede agregar un pago en un estado diferente a Entregado");

                    }

                }

            } else {
                throw new Exception("Debe rellenar los campos obligatorios");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Servicio Tecnico", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_agregarPagoGSTActionPerformed

    private void txt_buscarServiciosPorNombreGSTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarServiciosPorNombreGSTKeyReleased
        if (!txt_buscarServiciosPorNombreGST.getText().trim().isEmpty()) {
            String nombre = txt_buscarServiciosPorNombreGST.getText().trim();
            cargarTablaServicios(this.cp.getControladoraServicioTecnico().filtrarServicioPorNombre(nombre));
        } else {
            cargarTablaServicios(this.cp.getControladoraServicioTecnico().listarServicios());
        }
    }//GEN-LAST:event_txt_buscarServiciosPorNombreGSTKeyReleased

    private void txt_precioPagoGSTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioPagoGSTKeyTyped
        Utilidades.DelimitarNumeros(evt, txt_precioPagoGST);
    }//GEN-LAST:event_txt_precioPagoGSTKeyTyped

    private void txt_cantidadProductoIncluidoGSTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadProductoIncluidoGSTKeyTyped
        Utilidades.DelimitarNumeros(evt, txt_cantidadProductoIncluidoGST);
    }//GEN-LAST:event_txt_cantidadProductoIncluidoGSTKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregarPagoGST;
    private javax.swing.JButton btn_agregarProductoSeleccionadoGST;
    private javax.swing.JButton btn_agregarServicioSeleccionadoGST;
    private javax.swing.JButton btn_cancelarGST;
    private javax.swing.JButton btn_editarGST;
    private javax.swing.JButton btn_filtrarServicioPorDniGST;
    private javax.swing.JButton btn_guardarGST;
    private javax.swing.JButton btn_imprimirGST;
    private javax.swing.JButton btn_listadotodosEquiposGST;
    private javax.swing.JButton btn_nuevoGST;
    private javax.swing.JButton btn_quitarProductoSeleccionadoGST;
    private javax.swing.JButton btn_quitarServicioSeleccionadoGST;
    private javax.swing.JButton btn_salirGST;
    private javax.swing.JButton btn_verMisEquiposGST;
    private javax.swing.JComboBox cmb_buscarEquipoPorTipoGST;
    private javax.swing.JComboBox cmb_buscarProductoPorMarcaGST;
    private javax.swing.JComboBox cmb_buscarProductoPorTipoGST;
    private javax.swing.JComboBox cmb_estadoServicioGST;
    private javax.swing.JComboBox cmb_tipoPagoGST;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private com.toedter.calendar.JDateChooser jdch_fechaGST;
    private javax.swing.JLabel lbl_buscarClientePorDniGST;
    private javax.swing.JLabel lbl_buscarEquipoPorModeloGST;
    private javax.swing.JLabel lbl_buscarEquipoPorTipoGST;
    private javax.swing.JLabel lbl_buscarProductoPorNombreGST;
    private javax.swing.JLabel lbl_buscarServicioPorClienteGST;
    private javax.swing.JLabel lbl_buscarServicioPorNombreGST;
    private javax.swing.JLabel lbl_busquedaProductoPorMarcaGST;
    private javax.swing.JLabel lbl_busquedaProductoPorTipoGST;
    private javax.swing.JLabel lbl_cantidadProductoGST;
    private javax.swing.JLabel lbl_estadoServicioGST;
    private javax.swing.JLabel lbl_tipoFallaGST;
    private javax.swing.JLabel lbl_tipoPagoGSTl;
    private javax.swing.JLabel lbl_tituloGST;
    private javax.swing.JLabel lbl_totalServicioTecnicoGST;
    private javax.swing.JLabel lbl_valorTotalServicioTecnicoGST;
    private javax.swing.JPanel pnl_ClienteSeleccionadoGST;
    private javax.swing.JPanel pnl_botonesAgreadoServicioGST;
    private javax.swing.JPanel pnl_botonesAgregadoGST;
    private javax.swing.JPanel pnl_botonesAgregarPagoGST;
    private javax.swing.JPanel pnl_botonesAgregarProductoGST;
    private javax.swing.JPanel pnl_buscarEquipoGST;
    private javax.swing.JPanel pnl_busquedaClienteGST;
    private javax.swing.JPanel pnl_busquedaProductoGST;
    private javax.swing.JPanel pnl_busquedaServicioGST;
    private javax.swing.JPanel pnl_busquedaServicioInculuidosGST;
    private javax.swing.JPanel pnl_centralGST;
    private javax.swing.JPanel pnl_clientesGST;
    private javax.swing.JPanel pnl_comentariosGST;
    private javax.swing.JPanel pnl_derechoGST;
    private javax.swing.JPanel pnl_encabezadoGST;
    private javax.swing.JPanel pnl_encabezadoGestionGST;
    private javax.swing.JPanel pnl_encabezadoListadoGST;
    private javax.swing.JPanel pnl_equipoGST;
    private javax.swing.JPanel pnl_estadoGST;
    private javax.swing.JPanel pnl_estadoServicioGST;
    private javax.swing.JPanel pnl_filtradoBusquedaGST;
    private javax.swing.JPanel pnl_formularioGST;
    private javax.swing.JPanel pnl_gestionEquipoSGT;
    private javax.swing.JPanel pnl_gestionGST;
    private javax.swing.JPanel pnl_izquierdoGST;
    private javax.swing.JPanel pnl_listadoClienteGST;
    private javax.swing.JPanel pnl_listadoEquipoGST;
    private javax.swing.JPanel pnl_listadoGST;
    private javax.swing.JPanel pnl_listadoProductoIncluidosGST;
    private javax.swing.JPanel pnl_listadoServicioIncluidoGST;
    private javax.swing.JPanel pnl_listadoServiciosGST;
    private javax.swing.JPanel pnl_pieGST;
    private javax.swing.JPanel pnl_pieGestionClienteGST;
    private javax.swing.JPanel pnl_productosIncluidosGST;
    private javax.swing.JPanel pnl_servicios_incluidosGST;
    private javax.swing.JPanel pnl_subtotalGST;
    private javax.swing.JPanel pnl_subtotalServicioGST;
    private javax.swing.JPanel pnl_tablaProductoGST;
    private javax.swing.JTable tb_listadoClientesGST;
    private javax.swing.JTable tb_listadoEquiposPorClienteGST;
    private javax.swing.JTable tb_listadoProductoIncluidosGST;
    private javax.swing.JTable tb_listadoProductosGST;
    private javax.swing.JTable tb_listadoServicioTecnicoGST;
    private javax.swing.JTable tb_listadoServiciosGST;
    private javax.swing.JTable tb_listadoServiciosIncluidosGST;
    private javax.swing.JTabbedPane tbp_incluidos;
    private javax.swing.JTabbedPane tbp_menuGST;
    private javax.swing.JTextArea txtA_cometariosTecnicosGST;
    private javax.swing.JTextArea txtA_tipoFallaGST;
    private javax.swing.JTextField txt_buscarClientePorDniGST;
    private javax.swing.JTextField txt_buscarEquipoPorModeloGST;
    private javax.swing.JTextField txt_buscarProductoPorNombreGST;
    private javax.swing.JTextField txt_buscarServicioPorDniGST;
    private javax.swing.JTextField txt_buscarServiciosPorNombreGST;
    private javax.swing.JTextField txt_cantidadProductoIncluidoGST;
    private javax.swing.JTextField txt_precioPagoGST;
    // End of variables declaration//GEN-END:variables
}
