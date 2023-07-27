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
import modelo.DetalleVenta;
import modelo.Marca;
import modelo.Pago;
import modelo.Producto;
import modelo.TipoProducto;
import modelo.Usuario;
import modelo.Utilidades;
import modelo.Venta;
import modelo.TipoPago;
import reportes.Reporte;

/**
 *
 * @author EcobarM
 */
public class gestionVenta extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private List<DetalleVenta> detallesVenta = new LinkedList<>();
    private Usuario usuario;
    private JDesktopPane dskp_principalVP;
    private List<Pago> pagos = new LinkedList<>();
    private float precioTotal = 0;

    DefaultTableModel tbcargaDetalleVenta = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    /**
     * Creates new form gestionVenta
     */
    public gestionVenta(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario = usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();
    }

    public void estadoInicial() {

        precioTotal = 0;

        detallesVenta = new LinkedList<>();

        Utilidades.cargarCombo(this.cp.getControladoraVenta().listarTipoPago(), cmb_tipoPagoGV);
        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_buscarProductoPorMarcaGV);
        if (cmb_buscarProductoPorMarcaGV.getSelectedItem() == "[SELECCIONAR]") {
            cmb_buscarProductoPorTipoGV.removeAllItems();
            cmb_buscarProductoPorTipoGV.addItem("[SELECCIONAR]");
            cmb_buscarProductoPorTipoGV.setEnabled(!isEnabled());
        }

        cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());
        cargarTablaVentas(this.cp.getControladoraVenta().listarVentasDescentes());
        cargarTablaClientes(this.cp.getControladoraCliente().listarClientes());

        jdch_fechaVentaGV.setDate(new Date());
        jdch_fechaVentaGV.setEnabled(!isEnabled());

        btn_filtrarVentaPorDniGV.setEnabled(isEnabled());
        btn_nuevoGV.setEnabled(isEnabled());
        btn_agregarSeleccionadoGV.setEnabled(!isEnabled());
        btn_quitarSeleccionadoGV.setEnabled(!isEnabled());
        btn_guardarGV.setEnabled(!isEnabled());
        btn_imprimirGV.setEnabled(isEnabled());
        btn_cancelarGV.setEnabled(!isEnabled());
        btn_salirGV.setEnabled(isEnabled());

        tb_listadoDetalleVentaGV.setEnabled(!isEnabled());
        tb_listadoProductoGV.setEnabled(!isEnabled());
        tb_listadoClienteGV.setEnabled(!isEnabled());
        tb_listadoClienteGV.clearSelection();

        txt_buscarClientePorDniGV.setText("");
        lbl_valorCantidadProductoGV.setText("");
        lbl_valorSubtotalGV.setText("");

        txt_buscarClientePorDniGV.setEnabled(!isEnabled());
        txt_cantidadProductoGV.setEnabled(!isEnabled());

        txt_cantidadProductoGV.setText("");

        cmb_buscarProductoPorMarcaGV.setEnabled(!isEnabled());
        cmb_buscarProductoPorTipoGV.setEnabled(!isEnabled());

        cmb_tipoPagoGV.setEnabled(!isEnabled());
        txt_precioPagoGV.setEnabled(!isEnabled());
        txt_precioPagoGV.setText("");
        btn_agregarPagoGV.setEnabled(!isEnabled());

        this.removerFilasDetalles();
        tb_listadoDetalleVentaGV.setModel(tbcargaDetalleVenta);
    }

    public void cambiarEstado() {

        //recargar cambios con los registros nuevos
        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_buscarProductoPorMarcaGV);
        cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());
        cargarTablaClientes(this.cp.getControladoraCliente().listarClientes());

        txt_buscarClientePorDniGV.setEnabled(isEnabled());
        btn_nuevoGV.setEnabled(!isEnabled());
        btn_agregarSeleccionadoGV.setEnabled(isEnabled());
        btn_quitarSeleccionadoGV.setEnabled(isEnabled());
        btn_guardarGV.setEnabled(isEnabled());
        btn_imprimirGV.setEnabled(isEnabled());
        btn_cancelarGV.setEnabled(isEnabled());

        cmb_buscarProductoPorMarcaGV.setEnabled(isEnabled());

        cmb_tipoPagoGV.setEnabled(isEnabled());
        txt_precioPagoGV.setEnabled(isEnabled());
        btn_agregarPagoGV.setEnabled(isEnabled());

        tb_listadoClienteGV.setEnabled(isEnabled());
        tb_listadoDetalleVentaGV.setEnabled(isEnabled());
        tb_listadoProductoGV.setEnabled(isEnabled());

        txt_cantidadProductoGV.setEnabled(isEnabled());

    }

    public void removerFilasDetalles() {
        tbcargaDetalleVenta = (DefaultTableModel) tb_listadoDetalleVentaGV.getModel();
        for (int i = tbcargaDetalleVenta.getRowCount() - 1; i >= 0; i--) {
            tbcargaDetalleVenta.removeRow(i);
        }

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

            tb_listadoClienteGV.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));

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
        tb_listadoClienteGV.setModel(tbCarga);
    }

    public void cargarTablaVentas(List<Venta> ventas) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        if (!ventas.isEmpty() && ventas != null) {
            String Cabecera[] = {"Codigo", "Fecha", "Precio", "Cliente", "Usuario"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoVentasGV.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbCarga.getColumnCount()];

            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

            for (Venta v : ventas) {
                fila[0] = v.getCodigo();
                fila[1] = formato.format(v.getFecha());
                fila[2] = v.getPrecio();
                fila[3] = v.getCliente().getApellido() + " " + v.getCliente().getNombre();
                fila[4] = v.getUsuario().getNombreUsuario();
                tbCarga.addRow(fila);

            }

        }
        tb_listadoVentasGV.setModel(tbCarga);
    }

    public void cargarTablaDetalleVenta(List<DetalleVenta> detallesVenta) {
        DefaultTableModel tbcarga = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!detallesVenta.isEmpty()) {
            String Cabecera[] = {"Codigo Producto", "Nombre", "Descripcion", "Marca", "Tipo de Producto", "Cantidad", "Precio Unitario", "SubTotal"};
            tbcarga.setColumnIdentifiers(Cabecera);
            tb_listadoDetalleVentaGV.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbcarga.getColumnCount()];
            for (DetalleVenta d : detallesVenta) {
                fila[0] = d.getProducto().getCodigo();
                fila[1] = d.getNombreProducto();
                fila[2] = d.getDescripcion();
                fila[3] = d.getProducto().getMarca();
                fila[4] = d.getProducto().getTipoProducto();
                fila[5] = d.getCantidad();
                fila[6] = d.getPrecio();
                fila[7] = d.getSubtotal();

                tbcarga.addRow(fila);

            }

        }
        tb_listadoDetalleVentaGV.setModel(tbcarga);
    }

    public void cargarTablaProductos(List<Producto> productos) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        if (!productos.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre", "Descripcion", "Marca", "Tipo de Producto", "Stock", "Precio Unitario"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoProductoGV.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbCarga.getColumnCount()];
            for (Producto p : productos) {

                if (p.getPrecio() > 0) {
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
        tb_listadoProductoGV.setModel(tbCarga);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGV = new javax.swing.JPanel();
        lbl_tituloGV = new javax.swing.JLabel();
        pnl_derechoGV = new javax.swing.JPanel();
        pnl_pieGV = new javax.swing.JPanel();
        btn_nuevoGV = new javax.swing.JButton();
        btn_cancelarGV = new javax.swing.JButton();
        btn_guardarGV = new javax.swing.JButton();
        btn_imprimirGV = new javax.swing.JButton();
        btn_salirGV = new javax.swing.JButton();
        pnl_izquierdoGV = new javax.swing.JPanel();
        pnl_centralGV = new javax.swing.JPanel();
        tbp_menuGV = new javax.swing.JTabbedPane();
        pnl_formularioGV = new javax.swing.JPanel();
        pnl_encabezadoGestionGV = new javax.swing.JPanel();
        jdch_fechaVentaGV = new com.toedter.calendar.JDateChooser();
        pnl_centralGestionGV = new javax.swing.JPanel();
        pnl_busquedaGV = new javax.swing.JPanel();
        tbp_busquedaGV = new javax.swing.JTabbedPane();
        pnl_busquedaClienteGV = new javax.swing.JPanel();
        pnl_encabezadoBusquedaClienteGV = new javax.swing.JPanel();
        lbl_buscarClientePorDniGV = new javax.swing.JLabel();
        txt_buscarClientePorDniGV = new javax.swing.JTextField();
        pnl_listadoClienteGV = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tb_listadoClienteGV = new javax.swing.JTable();
        pnl_busquedaProductoGV = new javax.swing.JPanel();
        pnl_tablaDetalleVentaGV = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoDetalleVentaGV = new javax.swing.JTable();
        pnl_filtradoProductoGV = new javax.swing.JPanel();
        lbl_busquedaProductoPorMarca = new javax.swing.JLabel();
        cmb_buscarProductoPorMarcaGV = new javax.swing.JComboBox();
        lbl_busquedaProductoPorTipoGV = new javax.swing.JLabel();
        cmb_buscarProductoPorTipoGV = new javax.swing.JComboBox();
        lbl_buscarProductoPorNombreGV = new javax.swing.JLabel();
        txt_buscarProductoPorNombreGV = new javax.swing.JTextField();
        pnl_tablaProductoGV = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_listadoProductoGV = new javax.swing.JTable();
        pnl_botonesAgregadoGV = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbl_cantidadProductoGV = new javax.swing.JLabel();
        txt_cantidadProductoGV = new javax.swing.JTextField();
        btn_agregarSeleccionadoGV = new javax.swing.JButton();
        btn_quitarSeleccionadoGV = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbl_tipoPagoGV = new javax.swing.JLabel();
        cmb_tipoPagoGV = new javax.swing.JComboBox();
        lbl_precioPagoGV = new javax.swing.JLabel();
        txt_precioPagoGV = new javax.swing.JTextField();
        btn_agregarPagoGV = new javax.swing.JButton();
        pnl_pieGestionGV = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl_cantidadProductoVentaGV = new javax.swing.JLabel();
        lbl_valorCantidadProductoGV = new javax.swing.JLabel();
        lbl_SubTotalGV = new javax.swing.JLabel();
        lbl_valorSubtotalGV = new javax.swing.JLabel();
        pnl_listadoGV = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_listadoVentasGV = new javax.swing.JTable();
        pnl_buscarVentaPorDniGV = new javax.swing.JPanel();
        lbl_buscarVentaPorClienteGV = new javax.swing.JLabel();
        txt_buscarVentaPorDniGV = new javax.swing.JTextField();
        btn_filtrarVentaPorDniGV = new javax.swing.JButton();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion Ventas");

        pnl_encabezadoGV.setPreferredSize(new java.awt.Dimension(685, 50));
        pnl_encabezadoGV.setLayout(new java.awt.GridLayout(1, 0));

        lbl_tituloGV.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGV.setText("Gestion de Ventas");
        pnl_encabezadoGV.add(lbl_tituloGV);

        getContentPane().add(pnl_encabezadoGV, java.awt.BorderLayout.NORTH);

        pnl_derechoGV.setPreferredSize(new java.awt.Dimension(50, 249));

        javax.swing.GroupLayout pnl_derechoGVLayout = new javax.swing.GroupLayout(pnl_derechoGV);
        pnl_derechoGV.setLayout(pnl_derechoGVLayout);
        pnl_derechoGVLayout.setHorizontalGroup(
            pnl_derechoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGVLayout.setVerticalGroup(
            pnl_derechoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGV, java.awt.BorderLayout.EAST);

        pnl_pieGV.setPreferredSize(new java.awt.Dimension(685, 50));
        pnl_pieGV.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGV.setText("Nuevo");
        btn_nuevoGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGVActionPerformed(evt);
            }
        });
        pnl_pieGV.add(btn_nuevoGV);

        btn_cancelarGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGV.setText("Cancelar");
        btn_cancelarGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGVActionPerformed(evt);
            }
        });
        pnl_pieGV.add(btn_cancelarGV);

        btn_guardarGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGV.setText("Guardar");
        btn_guardarGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGVActionPerformed(evt);
            }
        });
        pnl_pieGV.add(btn_guardarGV);

        btn_imprimirGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_imprimirGV.setText("Imprimir");
        btn_imprimirGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirGVActionPerformed(evt);
            }
        });
        pnl_pieGV.add(btn_imprimirGV);

        btn_salirGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGV.setText("Salir");
        btn_salirGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGVActionPerformed(evt);
            }
        });
        pnl_pieGV.add(btn_salirGV);

        getContentPane().add(pnl_pieGV, java.awt.BorderLayout.SOUTH);

        pnl_izquierdoGV.setPreferredSize(new java.awt.Dimension(50, 249));

        javax.swing.GroupLayout pnl_izquierdoGVLayout = new javax.swing.GroupLayout(pnl_izquierdoGV);
        pnl_izquierdoGV.setLayout(pnl_izquierdoGVLayout);
        pnl_izquierdoGVLayout.setHorizontalGroup(
            pnl_izquierdoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGVLayout.setVerticalGroup(
            pnl_izquierdoGVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGV, java.awt.BorderLayout.WEST);

        pnl_centralGV.setLayout(new java.awt.BorderLayout());

        tbp_menuGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        pnl_formularioGV.setLayout(new java.awt.BorderLayout());

        pnl_encabezadoGestionGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        pnl_encabezadoGestionGV.setPreferredSize(new java.awt.Dimension(808, 50));
        pnl_encabezadoGestionGV.setLayout(new java.awt.BorderLayout());

        jdch_fechaVentaGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jdch_fechaVentaGV.setPreferredSize(new java.awt.Dimension(120, 20));
        pnl_encabezadoGestionGV.add(jdch_fechaVentaGV, java.awt.BorderLayout.EAST);

        pnl_formularioGV.add(pnl_encabezadoGestionGV, java.awt.BorderLayout.NORTH);

        pnl_centralGestionGV.setLayout(new java.awt.BorderLayout());

        pnl_busquedaGV.setLayout(new java.awt.GridLayout(1, 0));

        tbp_busquedaGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tbp_busquedaGV.setPreferredSize(new java.awt.Dimension(1070, 130));

        pnl_busquedaClienteGV.setPreferredSize(new java.awt.Dimension(1059, 130));
        pnl_busquedaClienteGV.setLayout(new java.awt.BorderLayout());

        lbl_buscarClientePorDniGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarClientePorDniGV.setText("Buscar Cliente Por Dni: ");
        pnl_encabezadoBusquedaClienteGV.add(lbl_buscarClientePorDniGV);

        txt_buscarClientePorDniGV.setColumns(5);
        txt_buscarClientePorDniGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarClientePorDniGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_buscarClientePorDniGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarClientePorDniGVActionPerformed(evt);
            }
        });
        txt_buscarClientePorDniGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarClientePorDniGVKeyReleased(evt);
            }
        });
        pnl_encabezadoBusquedaClienteGV.add(txt_buscarClientePorDniGV);

        pnl_busquedaClienteGV.add(pnl_encabezadoBusquedaClienteGV, java.awt.BorderLayout.NORTH);

        pnl_listadoClienteGV.setPreferredSize(new java.awt.Dimension(452, 350));
        pnl_listadoClienteGV.setLayout(new java.awt.BorderLayout());

        jScrollPane5.setPreferredSize(new java.awt.Dimension(452, 400));

        tb_listadoClienteGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoClienteGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tb_listadoClienteGV);

        pnl_listadoClienteGV.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        pnl_busquedaClienteGV.add(pnl_listadoClienteGV, java.awt.BorderLayout.SOUTH);

        tbp_busquedaGV.addTab("Cliente", pnl_busquedaClienteGV);

        pnl_busquedaProductoGV.setPreferredSize(new java.awt.Dimension(1065, 150));
        pnl_busquedaProductoGV.setLayout(new java.awt.BorderLayout());

        pnl_tablaDetalleVentaGV.setPreferredSize(new java.awt.Dimension(808, 100));
        pnl_tablaDetalleVentaGV.setLayout(new java.awt.GridLayout(1, 0));

        tb_listadoDetalleVentaGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoDetalleVentaGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_listadoDetalleVentaGV);

        pnl_tablaDetalleVentaGV.add(jScrollPane1);

        pnl_busquedaProductoGV.add(pnl_tablaDetalleVentaGV, java.awt.BorderLayout.SOUTH);

        pnl_filtradoProductoGV.setPreferredSize(new java.awt.Dimension(1065, 50));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        pnl_filtradoProductoGV.setLayout(flowLayout1);

        lbl_busquedaProductoPorMarca.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_busquedaProductoPorMarca.setText("Buscar Producto Por Marca: ");
        pnl_filtradoProductoGV.add(lbl_busquedaProductoPorMarca);

        cmb_buscarProductoPorMarcaGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_buscarProductoPorMarcaGV.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_buscarProductoPorMarcaGV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_buscarProductoPorMarcaGVItemStateChanged(evt);
            }
        });
        cmb_buscarProductoPorMarcaGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_buscarProductoPorMarcaGVActionPerformed(evt);
            }
        });
        pnl_filtradoProductoGV.add(cmb_buscarProductoPorMarcaGV);

        lbl_busquedaProductoPorTipoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_busquedaProductoPorTipoGV.setText("Buscar Producto Por Tipo:");
        pnl_filtradoProductoGV.add(lbl_busquedaProductoPorTipoGV);

        cmb_buscarProductoPorTipoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_buscarProductoPorTipoGV.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_buscarProductoPorTipoGV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_buscarProductoPorTipoGVItemStateChanged(evt);
            }
        });
        pnl_filtradoProductoGV.add(cmb_buscarProductoPorTipoGV);

        lbl_buscarProductoPorNombreGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarProductoPorNombreGV.setText("Buscar Producto Por Nombre:");
        pnl_filtradoProductoGV.add(lbl_buscarProductoPorNombreGV);

        txt_buscarProductoPorNombreGV.setColumns(15);
        txt_buscarProductoPorNombreGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarProductoPorNombreGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarProductoPorNombreGVKeyReleased(evt);
            }
        });
        pnl_filtradoProductoGV.add(txt_buscarProductoPorNombreGV);

        pnl_busquedaProductoGV.add(pnl_filtradoProductoGV, java.awt.BorderLayout.NORTH);

        pnl_tablaProductoGV.setPreferredSize(new java.awt.Dimension(300, 300));
        pnl_tablaProductoGV.setLayout(new java.awt.GridLayout(2, 0));

        jScrollPane4.setPreferredSize(new java.awt.Dimension(452, 100));

        tb_listadoProductoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoProductoGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tb_listadoProductoGV);

        pnl_tablaProductoGV.add(jScrollPane4);

        pnl_botonesAgregadoGV.setPreferredSize(new java.awt.Dimension(300, 50));
        pnl_botonesAgregadoGV.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setPreferredSize(new java.awt.Dimension(1226, 50));

        lbl_cantidadProductoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_cantidadProductoGV.setText("Cantidad:");
        jPanel3.add(lbl_cantidadProductoGV);

        txt_cantidadProductoGV.setColumns(3);
        txt_cantidadProductoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_cantidadProductoGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidadProductoGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadProductoGVKeyTyped(evt);
            }
        });
        jPanel3.add(txt_cantidadProductoGV);

        btn_agregarSeleccionadoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarSeleccionadoGV.setText("Agregar");
        btn_agregarSeleccionadoGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarSeleccionadoGVActionPerformed(evt);
            }
        });
        jPanel3.add(btn_agregarSeleccionadoGV);

        btn_quitarSeleccionadoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_quitarSeleccionadoGV.setText("Quitar");
        btn_quitarSeleccionadoGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarSeleccionadoGVActionPerformed(evt);
            }
        });
        jPanel3.add(btn_quitarSeleccionadoGV);

        pnl_botonesAgregadoGV.add(jPanel3);

        jPanel4.setPreferredSize(new java.awt.Dimension(1226, 50));

        lbl_tipoPagoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_tipoPagoGV.setText("(3) Tipo Pago:");
        jPanel4.add(lbl_tipoPagoGV);

        cmb_tipoPagoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_tipoPagoGV.setMinimumSize(new java.awt.Dimension(120, 20));
        cmb_tipoPagoGV.setPreferredSize(new java.awt.Dimension(130, 25));
        jPanel4.add(cmb_tipoPagoGV);

        lbl_precioPagoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_precioPagoGV.setText("Precio:");
        jPanel4.add(lbl_precioPagoGV);

        txt_precioPagoGV.setColumns(5);
        txt_precioPagoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_precioPagoGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precioPagoGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precioPagoGVKeyTyped(evt);
            }
        });
        jPanel4.add(txt_precioPagoGV);

        btn_agregarPagoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarPagoGV.setText("Agregar Pago");
        btn_agregarPagoGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarPagoGVActionPerformed(evt);
            }
        });
        jPanel4.add(btn_agregarPagoGV);

        pnl_botonesAgregadoGV.add(jPanel4);

        pnl_tablaProductoGV.add(pnl_botonesAgregadoGV);

        pnl_busquedaProductoGV.add(pnl_tablaProductoGV, java.awt.BorderLayout.CENTER);

        tbp_busquedaGV.addTab(" Producto", pnl_busquedaProductoGV);

        pnl_busquedaGV.add(tbp_busquedaGV);

        pnl_centralGestionGV.add(pnl_busquedaGV, java.awt.BorderLayout.CENTER);

        pnl_formularioGV.add(pnl_centralGestionGV, java.awt.BorderLayout.CENTER);

        pnl_pieGestionGV.setPreferredSize(new java.awt.Dimension(1048, 50));
        pnl_pieGestionGV.setLayout(new java.awt.BorderLayout());

        lbl_cantidadProductoVentaGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_cantidadProductoVentaGV.setText("CANTIDAD DE PRODUCTOS: ");
        jPanel1.add(lbl_cantidadProductoVentaGV);

        lbl_valorCantidadProductoGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(lbl_valorCantidadProductoGV);

        lbl_SubTotalGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_SubTotalGV.setText("TOTAL:");
        jPanel1.add(lbl_SubTotalGV);

        lbl_valorSubtotalGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(lbl_valorSubtotalGV);

        pnl_pieGestionGV.add(jPanel1, java.awt.BorderLayout.CENTER);

        pnl_formularioGV.add(pnl_pieGestionGV, java.awt.BorderLayout.SOUTH);

        tbp_menuGV.addTab("Gestion", pnl_formularioGV);

        pnl_listadoGV.setLayout(new java.awt.BorderLayout());

        tb_listadoVentasGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoVentasGV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_listadoVentasGV);

        pnl_listadoGV.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pnl_buscarVentaPorDniGV.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        lbl_buscarVentaPorClienteGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarVentaPorClienteGV.setText("Buscar venta Por Dni:");
        pnl_buscarVentaPorDniGV.add(lbl_buscarVentaPorClienteGV);

        txt_buscarVentaPorDniGV.setColumns(6);
        txt_buscarVentaPorDniGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarVentaPorDniGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_buscarVentaPorDniGV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarVentaPorDniGVKeyReleased(evt);
            }
        });
        pnl_buscarVentaPorDniGV.add(txt_buscarVentaPorDniGV);

        btn_filtrarVentaPorDniGV.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_filtrarVentaPorDniGV.setText("Filtrar");
        btn_filtrarVentaPorDniGV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrarVentaPorDniGVActionPerformed(evt);
            }
        });
        pnl_buscarVentaPorDniGV.add(btn_filtrarVentaPorDniGV);

        pnl_listadoGV.add(pnl_buscarVentaPorDniGV, java.awt.BorderLayout.PAGE_START);

        tbp_menuGV.addTab("Listado", pnl_listadoGV);

        pnl_centralGV.add(tbp_menuGV, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGV, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscarClientePorDniGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarClientePorDniGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarClientePorDniGVActionPerformed

    private void cmb_buscarProductoPorMarcaGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorMarcaGVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_buscarProductoPorMarcaGVActionPerformed

    private void cmb_buscarProductoPorMarcaGVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorMarcaGVItemStateChanged
        if (cmb_buscarProductoPorMarcaGV.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorMarcaGV.getSelectedItem() != null) {
            Marca marca = (Marca) cmb_buscarProductoPorMarcaGV.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarca(marca));
            Utilidades.cargarCombo(marca.getTiposProductos(), cmb_buscarProductoPorTipoGV);
            cmb_buscarProductoPorTipoGV.setEnabled(isEnabled());
        } else {
            cmb_buscarProductoPorTipoGV.removeAllItems();
            cmb_buscarProductoPorTipoGV.addItem("[SELECCIONAR]");
            cmb_buscarProductoPorTipoGV.setEnabled(!isEnabled());
            cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());

        }
    }//GEN-LAST:event_cmb_buscarProductoPorMarcaGVItemStateChanged

    private void cmb_buscarProductoPorTipoGVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorTipoGVItemStateChanged
        if (cmb_buscarProductoPorTipoGV.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorTipoGV.getSelectedItem() != null) {
            Marca marca = (Marca) cmb_buscarProductoPorMarcaGV.getSelectedItem();
            TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGV.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarcayTipo(marca, tipo));
            txt_buscarProductoPorNombreGV.setEnabled(isEnabled());
        } else {
            if (cmb_buscarProductoPorMarcaGV.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorMarcaGV.getSelectedItem() != null) {
                Marca marca = (Marca) cmb_buscarProductoPorMarcaGV.getSelectedItem();
                cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarca(marca));
            }

            txt_buscarProductoPorNombreGV.setText("");
            txt_buscarProductoPorNombreGV.setEnabled(!isEnabled());
        }
    }//GEN-LAST:event_cmb_buscarProductoPorTipoGVItemStateChanged

    private void txt_buscarProductoPorNombreGVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarProductoPorNombreGVKeyReleased
        if (!txt_buscarProductoPorNombreGV.getText().trim().isEmpty()) {
            String nombre = txt_buscarProductoPorNombreGV.getText().trim();
            Marca marca = (Marca) cmb_buscarProductoPorMarcaGV.getSelectedItem();
            TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGV.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProducto(nombre, marca, tipo));
        } else {
            if (cmb_buscarProductoPorTipoGV.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorTipoGV.getSelectedItem() != null) {
                Marca marca = (Marca) cmb_buscarProductoPorMarcaGV.getSelectedItem();
                TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGV.getSelectedItem();
                cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarcayTipo(marca, tipo));
            }

        }
    }//GEN-LAST:event_txt_buscarProductoPorNombreGVKeyReleased

    private void btn_agregarSeleccionadoGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarSeleccionadoGVActionPerformed
        try {
            boolean registrado = false;

            if (tb_listadoProductoGV.getSelectedRowCount() <= 0) {

                throw new Exception("Debe Seleccionar un Producto");

            }
            if (tb_listadoProductoGV.getSelectedRowCount() >= 2) {

                throw new Exception("Debe seleccionar un solo producto");

            }

            if (tb_listadoProductoGV.getSelectedRowCount() == 1) {

                if (txt_cantidadProductoGV.getText().trim().isEmpty()) {
                    throw new Exception("Rellanar los campos obligatorios");
                } else {

                    int codigo = (int) tb_listadoProductoGV.getValueAt(tb_listadoProductoGV.getSelectedRow(), 0);
                    Producto productoSeleccionado = cp.getControladoraProducto().buscarProductoPorCodigo(codigo);

                    for (DetalleVenta detallesVenta1 : detallesVenta) {
                        if (detallesVenta1.getProducto().getCodigo() == productoSeleccionado.getCodigo()) {
                            registrado = true;
                            break;
                        }
                    }

                    if (registrado == false) {
                        Integer cantidadVenta = Integer.valueOf(txt_cantidadProductoGV.getText().trim());

//                        if (cantidadVenta > productoSeleccionado.getCantidad()) {
//                            throw new Exception("No se puede agregar una cantidad mayor al existente ");
//                        }
                        if (productoSeleccionado.getPrecio() <= 0) {
                            throw new Exception("No se puede agregar un producto sin precio de venta");
                        }

                        DetalleVenta detalleVenta = new DetalleVenta(productoSeleccionado.getNombre(), productoSeleccionado.getDescripcion(), cantidadVenta, productoSeleccionado.getPrecio(), cantidadVenta * productoSeleccionado.getPrecio(), productoSeleccionado);

                        detallesVenta.add(detalleVenta);

                        precioTotal = detalleVenta.sumarSubtotal(detallesVenta);

                        lbl_valorCantidadProductoGV.setText(String.valueOf(detalleVenta.sumarCantidadTotal(detallesVenta)));
                        lbl_valorSubtotalGV.setText(String.valueOf(detalleVenta.sumarSubtotal(detallesVenta)));
                        cargarTablaDetalleVenta(detallesVenta);

                    } else {
                        throw new Exception("El producto ya esta agregado");

                    }

                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de ventas", JOptionPane.WARNING_MESSAGE);
        }
        txt_cantidadProductoGV.setText("");

        tb_listadoProductoGV.clearSelection();
    }//GEN-LAST:event_btn_agregarSeleccionadoGVActionPerformed

    private void btn_nuevoGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGVActionPerformed
        this.cambiarEstado();
    }//GEN-LAST:event_btn_nuevoGVActionPerformed

    private void btn_cancelarGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGVActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGVActionPerformed

    private void btn_salirGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGVActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGVActionPerformed

    private void btn_quitarSeleccionadoGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarSeleccionadoGVActionPerformed
        try {

            if (tb_listadoDetalleVentaGV.getSelectedRowCount() <= 0) {
                throw new Exception("Debe seleccionar un detalle");
            }

            if (tb_listadoDetalleVentaGV.getSelectedRowCount() >= 2) {
                throw new Exception("Debe seleccionar solo un detalle");
            }

            if (tb_listadoDetalleVentaGV.getSelectedRowCount() == 1) {

                int codigo = (int) tb_listadoDetalleVentaGV.getValueAt(tb_listadoDetalleVentaGV.getSelectedRow(), 0);
                Producto productoSeleccionado = this.cp.getControladoraProducto().buscarProductoPorCodigo(codigo);

                tbcargaDetalleVenta = (DefaultTableModel) tb_listadoDetalleVentaGV.getModel();
                tbcargaDetalleVenta.removeRow(tb_listadoDetalleVentaGV.getSelectedRow());
                tb_listadoDetalleVentaGV.setModel(tbcargaDetalleVenta);

                for (DetalleVenta detallesVenta1 : detallesVenta) {
                    if (detallesVenta1.getProducto().getCodigo() == productoSeleccionado.getCodigo()) {
                        lbl_valorCantidadProductoGV.setText(String.valueOf(detallesVenta1.restarCantidadTotal(detallesVenta, detallesVenta1.getCantidad())));
                        lbl_valorSubtotalGV.setText(String.valueOf(detallesVenta1.restarSubtotal(detallesVenta, detallesVenta1.getSubtotal())));
                        detallesVenta.remove(detallesVenta1);
                    }

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Compras", JOptionPane.WARNING_MESSAGE);

        }

        tb_listadoDetalleVentaGV.clearSelection();
    }//GEN-LAST:event_btn_quitarSeleccionadoGVActionPerformed

    private void txt_buscarClientePorDniGVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarClientePorDniGVKeyReleased
        if (!txt_buscarClientePorDniGV.getText().trim().isEmpty()) {
            Integer dni = Integer.valueOf(txt_buscarClientePorDniGV.getText().trim());
            cargarTablaClientes(this.cp.getControladoraCliente().filtrarClientePorDni(dni));
        } else {
            cargarTablaClientes(this.cp.getControladoraCliente().listarClientes());

        }


    }//GEN-LAST:event_txt_buscarClientePorDniGVKeyReleased

    private void btn_guardarGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGVActionPerformed

        try {
            if (tb_listadoDetalleVentaGV.getRowCount() <= 0) {
                throw new Exception("Debe agregar al menos un [1] producto al detalle");
            }

            Date fechaVenta = jdch_fechaVentaGV.getDate();
            Date fechaActual = new Date();
            Cliente cliente = new Cliente();

            if (fechaVenta.after(fechaActual)) {
                throw new Exception("No se puede generar una compra con una fecha posterior a la actual");
            }

            if (tb_listadoClienteGV.getSelectedRowCount() <= 0) {
                throw new Exception("Debe Seleccionar un cliente");

            } else if (tb_listadoClienteGV.getSelectedRowCount() >= 2) {
                throw new Exception("Debe Seleccionar un solo cliente");

            } else if (tb_listadoClienteGV.getSelectedRowCount() == 1) {
                int codigo = (int) tb_listadoClienteGV.getValueAt(tb_listadoClienteGV.getSelectedRow(), 0);
                cliente = this.cp.getControladoraCliente().buscarClientePorCodigo(codigo);
            }

            DetalleVenta detalleVenta = new DetalleVenta();
            Venta venta = new Venta(fechaVenta, detalleVenta.sumarSubtotal(detallesVenta), cliente, usuario);

            if (pagos.isEmpty()) {
                throw new Exception("Debe agregar al menos [1] pago");
            }

            venta = this.cp.getControladoraVenta().registrarVenta(venta);

            for (DetalleVenta detallesVenta1 : detallesVenta) {
                detallesVenta1.setVenta(venta);
                this.cp.getControladoraVenta().registrarDetalleVenta(detallesVenta1);
                this.cp.getControladoraProducto().vendiProducto(detallesVenta1.getCantidad(), detallesVenta1.getProducto().getCodigo());
            }

            for (Pago pago : pagos) {
                pago.setVenta(venta);
                this.cp.getControladoraVenta().registarPago(pago);
            }

            JOptionPane.showMessageDialog(null, "Creado Con Exito", "Gestion De Venta", JOptionPane.INFORMATION_MESSAGE);

            JFrame visualizador = new JFrame();
            Reporte reporte = new Reporte();
            Map parametros = new HashMap();

            parametros.put("idVenta", venta.getCodigo());
            visualizador.setContentPane(reporte.crearReporteVenta("/reportes/reporteComprobanteVenta.jasper", parametros));
            visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
            visualizador.setVisible(true);

            this.estadoInicial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Venta", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_guardarGVActionPerformed

    private void txt_buscarVentaPorDniGVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarVentaPorDniGVKeyReleased

    }//GEN-LAST:event_txt_buscarVentaPorDniGVKeyReleased

    private void btn_imprimirGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirGVActionPerformed

        try {
            if (tb_listadoVentasGV.getSelectedRowCount() == 1) {
                JFrame visualizador = new JFrame();
                Reporte reporte = new Reporte();
                Map parametros = new HashMap();

                int codigoVenta = (int) tb_listadoVentasGV.getValueAt(tb_listadoVentasGV.getSelectedRow(), 0);

                parametros.put("idVenta", codigoVenta);
                visualizador.setContentPane(reporte.crearReporteVenta("/reportes/reporteComprobanteVenta.jasper", parametros));
                visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                visualizador.setVisible(true);

            } else {
                if (tb_listadoVentasGV.getSelectedRowCount() >= 2) {
                    throw new Exception("Debe seleccionar una sola venta");
                }

                if (tb_listadoVentasGV.getSelectedRowCount() <= 0) {
                    throw new Exception("Debe seleccionar al menos una venta");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Venta", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_imprimirGVActionPerformed

    private void btn_filtrarVentaPorDniGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarVentaPorDniGVActionPerformed

        if (!txt_buscarVentaPorDniGV.getText().trim().isEmpty()) {
            Integer dni = Integer.valueOf(txt_buscarVentaPorDniGV.getText().trim());
            Cliente cliente = this.cp.getControladoraCliente().buscarClientePorDni(dni);
            try {
                cargarTablaVentas(this.cp.getControladoraVenta().filtrarVentasPorCliente(cliente));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se encontro una venta registrada con este dni", "Gestion Venta", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar un dni", "Gestion Venta", JOptionPane.WARNING_MESSAGE);
        }

        txt_buscarVentaPorDniGV.setText("");


    }//GEN-LAST:event_btn_filtrarVentaPorDniGVActionPerformed

    private void btn_agregarPagoGVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarPagoGVActionPerformed
        try {

            if (!txt_precioPagoGV.getText().trim().isEmpty() && cmb_tipoPagoGV.getSelectedItem() != "[SELECCIONAR]" && cmb_tipoPagoGV.getSelectedItem() != null) {

                if (tb_listadoDetalleVentaGV.getRowCount() >= 1) {
                    DetalleVenta detalleVenta = new DetalleVenta();
                    float PrecioPago = Float.valueOf(txt_precioPagoGV.getText().trim());
                    TipoPago tipoPago = (TipoPago) cmb_tipoPagoGV.getSelectedItem();

                    if (precioTotal == 0) {
                        precioTotal = detalleVenta.sumarSubtotal(detallesVenta);
                    }

                    if (pagos.size() <= 2 && detalleVenta.sumarSubtotal(detallesVenta) != 0) {
                        Pago pago = new Pago(PrecioPago, tipoPago);

                        if (precioTotal - pago.getPrecio() >= 0) {

                            pagos.add(pago);
                            precioTotal -= pago.getPrecio();

                            lbl_valorSubtotalGV.setText(String.valueOf(precioTotal));
                        } else {
                            throw new Exception("No se pudo agregar el pago");
                        }

                    } else {
                        throw new Exception("Solo puede agregar hasta [3] pagos");
                    }
                    Utilidades.cargarCombo(this.cp.getControladoraVenta().listarTipoPago(), cmb_tipoPagoGV);
                    txt_precioPagoGV.setText("");

                    JOptionPane.showMessageDialog(null, "Pago agregado correctamente", "Gestion Ventas", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    throw new Exception("Debe agregar al menos [1] producto");
                }

            } else {
                throw new Exception("Debe rellenar los campos obligatorios");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Venta", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btn_agregarPagoGVActionPerformed

    private void txt_precioPagoGVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioPagoGVKeyTyped
        Utilidades.DelimitarNumeros(evt, txt_precioPagoGV);
    }//GEN-LAST:event_txt_precioPagoGVKeyTyped

    private void txt_cantidadProductoGVKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadProductoGVKeyTyped
        Utilidades.DelimitarNumeros(evt, txt_cantidadProductoGV);
    }//GEN-LAST:event_txt_cantidadProductoGVKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregarPagoGV;
    private javax.swing.JButton btn_agregarSeleccionadoGV;
    private javax.swing.JButton btn_cancelarGV;
    private javax.swing.JButton btn_filtrarVentaPorDniGV;
    private javax.swing.JButton btn_guardarGV;
    private javax.swing.JButton btn_imprimirGV;
    private javax.swing.JButton btn_nuevoGV;
    private javax.swing.JButton btn_quitarSeleccionadoGV;
    private javax.swing.JButton btn_salirGV;
    private javax.swing.JComboBox cmb_buscarProductoPorMarcaGV;
    private javax.swing.JComboBox cmb_buscarProductoPorTipoGV;
    private javax.swing.JComboBox cmb_tipoPagoGV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser jdch_fechaVentaGV;
    private javax.swing.JLabel lbl_SubTotalGV;
    private javax.swing.JLabel lbl_buscarClientePorDniGV;
    private javax.swing.JLabel lbl_buscarProductoPorNombreGV;
    private javax.swing.JLabel lbl_buscarVentaPorClienteGV;
    private javax.swing.JLabel lbl_busquedaProductoPorMarca;
    private javax.swing.JLabel lbl_busquedaProductoPorTipoGV;
    private javax.swing.JLabel lbl_cantidadProductoGV;
    private javax.swing.JLabel lbl_cantidadProductoVentaGV;
    private javax.swing.JLabel lbl_precioPagoGV;
    private javax.swing.JLabel lbl_tipoPagoGV;
    private javax.swing.JLabel lbl_tituloGV;
    private javax.swing.JLabel lbl_valorCantidadProductoGV;
    private javax.swing.JLabel lbl_valorSubtotalGV;
    private javax.swing.JPanel pnl_botonesAgregadoGV;
    private javax.swing.JPanel pnl_buscarVentaPorDniGV;
    private javax.swing.JPanel pnl_busquedaClienteGV;
    private javax.swing.JPanel pnl_busquedaGV;
    private javax.swing.JPanel pnl_busquedaProductoGV;
    private javax.swing.JPanel pnl_centralGV;
    private javax.swing.JPanel pnl_centralGestionGV;
    private javax.swing.JPanel pnl_derechoGV;
    private javax.swing.JPanel pnl_encabezadoBusquedaClienteGV;
    private javax.swing.JPanel pnl_encabezadoGV;
    private javax.swing.JPanel pnl_encabezadoGestionGV;
    private javax.swing.JPanel pnl_filtradoProductoGV;
    private javax.swing.JPanel pnl_formularioGV;
    private javax.swing.JPanel pnl_izquierdoGV;
    private javax.swing.JPanel pnl_listadoClienteGV;
    private javax.swing.JPanel pnl_listadoGV;
    private javax.swing.JPanel pnl_pieGV;
    private javax.swing.JPanel pnl_pieGestionGV;
    private javax.swing.JPanel pnl_tablaDetalleVentaGV;
    private javax.swing.JPanel pnl_tablaProductoGV;
    private javax.swing.JTable tb_listadoClienteGV;
    private javax.swing.JTable tb_listadoDetalleVentaGV;
    private javax.swing.JTable tb_listadoProductoGV;
    private javax.swing.JTable tb_listadoVentasGV;
    private javax.swing.JTabbedPane tbp_busquedaGV;
    private javax.swing.JTabbedPane tbp_menuGV;
    private javax.swing.JTextField txt_buscarClientePorDniGV;
    private javax.swing.JTextField txt_buscarProductoPorNombreGV;
    private javax.swing.JTextField txt_buscarVentaPorDniGV;
    private javax.swing.JTextField txt_cantidadProductoGV;
    private javax.swing.JTextField txt_precioPagoGV;
    // End of variables declaration//GEN-END:variables
}
