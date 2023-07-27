/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Compra;
import modelo.DetalleCompra;
import modelo.Marca;
import modelo.Producto;
import modelo.TipoProducto;
import modelo.Usuario;
import modelo.Utilidades;
import reportes.Reporte;

/**
 *
 * @author EcobarM
 */
public class gestionCompra extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private List<DetalleCompra> detallesCompra = new LinkedList<>();
    private Usuario usuario;
    private JDesktopPane dskp_principalVP;

    DefaultTableModel tbcargaDetalleCompra = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    /**
     * Creates new form gestionCompra
     */
    public gestionCompra(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario = usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();

    }

    public void estadoInicial() {
        cargarTablaCompras(this.cp.getControladoraCompra().listarComprasDescendentes());
        cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());

        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_BuscarProductoPorMarcaGC);

        detallesCompra = new LinkedList<>();

        if(cmb_BuscarProductoPorMarcaGC.getSelectedItem()=="[SELECCIONAR]"){
            cmb_buscarProductoPorTipoGC.removeAllItems();
            cmb_buscarProductoPorTipoGC.addItem("[SELECCIONAR]");
        }
        
        
        jdch_fechaCompraGC.setDate(new Date());
        jdch_fechaCompraGC.setEnabled(!isEnabled());

        tb_listadoDetalleCompraGC.setEnabled(!isEnabled());
        tb_listadoProductoGC.setEnabled(!isEnabled());

        btn_nuevoGC.setEnabled(isEnabled());
        btn_cancelarGC.setEnabled(!isEnabled());
        btn_guardarGC.setEnabled(!isEnabled());
        btn_imprimirGC.setEnabled(isEnabled());
        btn_salirGC.setEnabled(isEnabled());
        btn_agregarSeleccionadoGC.setEnabled(!isEnabled());
        btn_quitarSeleccionadoGC.setEnabled(!isEnabled());

        txt_buscarProductoPorNombreGC.setText("");
        cmb_BuscarProductoPorMarcaGC.setEnabled(!isEnabled());
        cmb_buscarProductoPorTipoGC.setEnabled(!isEnabled());
        txt_buscarProductoPorNombreGC.setEnabled(!isEnabled());
        txt_cantidadProductoGC.setEnabled(!isEnabled());
        txt_precioCompraGC.setEnabled(!isEnabled());
        lbl_valorCantidadProductosGC.setText("");
        lbl_valorSubtotalCompraGC.setText("");

        this.removerFilasDetalles();

    }

    public void removerFilasDetalles() {
        tbcargaDetalleCompra = (DefaultTableModel) tb_listadoDetalleCompraGC.getModel();
        for (int i = tbcargaDetalleCompra.getRowCount() - 1; i >= 0; i--) {
            tbcargaDetalleCompra.removeRow(i);
        }

    }

    public void cambiarEstado() {

        //recargar cambios con los registros nuevos
        cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());
        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_BuscarProductoPorMarcaGC);

        btn_nuevoGC.setEnabled(!isEnabled());
        btn_cancelarGC.setEnabled(isEnabled());
        btn_guardarGC.setEnabled(isEnabled());
        btn_imprimirGC.setEnabled(isEnabled());
        btn_salirGC.setEnabled(isEnabled());
        btn_agregarSeleccionadoGC.setEnabled(isEnabled());
        btn_quitarSeleccionadoGC.setEnabled(isEnabled());

        jdch_fechaCompraGC.setEnabled(isEnabled());

        tb_listadoDetalleCompraGC.setEnabled(isEnabled());
        tb_listadoProductoGC.setEnabled(isEnabled());

        cmb_BuscarProductoPorMarcaGC.setEnabled(isEnabled());

        txt_cantidadProductoGC.setEnabled(isEnabled());
        txt_precioCompraGC.setEnabled(isEnabled());

    }

    public void cargarTablaProductos(List<Producto> productos) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        if (!productos.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre", "Descripcion", "Marca", "Tipo de Producto", "Cantidad"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoProductoGC.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbCarga.getColumnCount()];
            for (Producto p : productos) {
                fila[0] = p.getCodigo();
                fila[1] = p.getNombre();
                fila[2] = p.getDescripcion();
                fila[3] = p.getMarca().getNombre();
                fila[4] = p.getTipoProducto().getNombre();
                fila[5] = p.getCantidad();

                tbCarga.addRow(fila);

            }

            tb_listadoProductoGC.setModel(tbCarga);
        }

    }

    public void cargarTablaDetalleCompra(List<DetalleCompra> detallesCompra) {
        DefaultTableModel tbcarga = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!detallesCompra.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre", "Descripcion", "Marca", "Tipo de Producto", "Cantidad", "Precio Unitario ", "SubTotal"};
            tbcarga.setColumnIdentifiers(Cabecera);
            tb_listadoDetalleCompraGC.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbcarga.getColumnCount()];
            for (DetalleCompra d : detallesCompra) {
                fila[0] = d.getCodigo();
                fila[1] = d.getNombreProducto();
                fila[2] = d.getDescripcion();
                fila[3] = d.getProducto().getMarca().getNombre();
                fila[4] = d.getProducto().getTipoProducto().getNombre();
                fila[5] = d.getCantidad();
                fila[6] = d.getPrecio();
                fila[7] = d.getSubtotal();

                tbcarga.addRow(fila);

            }

        }
        tb_listadoDetalleCompraGC.setModel(tbcarga);
    }

    public void cargarTablaCompras(List<Compra> compras) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        if (!compras.isEmpty()) {
            String Cabecera[] = {"Codigo", "Fecha", "Precio", "Usuario"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoComprasGC.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbCarga.getColumnCount()];

            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

            for (Compra c : compras) {
                fila[0] = c.getCodigo();
                fila[1] = formato.format(c.getFecha());
                fila[2] = c.getPrecio();
                fila[3] = c.getUsuario().getNombreUsuario();
                tbCarga.addRow(fila);

            }

        }
        tb_listadoComprasGC.setModel(tbCarga);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGC = new javax.swing.JPanel();
        lbl_tituloGC = new javax.swing.JLabel();
        pnl_derechoGC = new javax.swing.JPanel();
        pnl_pieGC = new javax.swing.JPanel();
        btn_nuevoGC = new javax.swing.JButton();
        btn_cancelarGC = new javax.swing.JButton();
        btn_guardarGC = new javax.swing.JButton();
        btn_imprimirGC = new javax.swing.JButton();
        btn_salirGC = new javax.swing.JButton();
        pnl_izquierdoGC = new javax.swing.JPanel();
        pnl_centralGC = new javax.swing.JPanel();
        tbp_menuGC = new javax.swing.JTabbedPane();
        pnl_gestionGC = new javax.swing.JPanel();
        pnl_pieGestionGC = new javax.swing.JPanel();
        lbl_cantidadProductosCompraGC = new javax.swing.JLabel();
        lbl_valorCantidadProductosGC = new javax.swing.JLabel();
        lbl_subtotalCompraGC = new javax.swing.JLabel();
        lbl_valorSubtotalCompraGC = new javax.swing.JLabel();
        pnl_centralGestionGC = new javax.swing.JPanel();
        pnl_centralFormularioGC = new javax.swing.JPanel();
        pnl_tablaDetalleCompraGC = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_listadoDetalleCompraGC = new javax.swing.JTable();
        pnl_tablaProductoGC = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_listadoProductoGC = new javax.swing.JTable();
        pnl_botonesGC = new javax.swing.JPanel();
        lbl_cantidadProductoGC = new javax.swing.JLabel();
        txt_cantidadProductoGC = new javax.swing.JTextField();
        lbl_precioCompraGC = new javax.swing.JLabel();
        txt_precioCompraGC = new javax.swing.JTextField();
        btn_agregarSeleccionadoGC = new javax.swing.JButton();
        btn_quitarSeleccionadoGC = new javax.swing.JButton();
        pnl_formularioGC = new javax.swing.JPanel();
        pnl_busquedaProductoGC = new javax.swing.JPanel();
        lbl_buscarProductoPorMarca = new javax.swing.JLabel();
        cmb_BuscarProductoPorMarcaGC = new javax.swing.JComboBox();
        lbl_BuscarProductoPorTipoGC = new javax.swing.JLabel();
        cmb_buscarProductoPorTipoGC = new javax.swing.JComboBox();
        lbl_buscarProductoPorNombreGC = new javax.swing.JLabel();
        txt_buscarProductoPorNombreGC = new javax.swing.JTextField();
        pnl_encabezadoGestionGC = new javax.swing.JPanel();
        jdch_fechaCompraGC = new com.toedter.calendar.JDateChooser();
        pnl_listadoGC = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoComprasGC = new javax.swing.JTable();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setTitle("Gestion Compras");

        pnl_encabezadoGC.setPreferredSize(new java.awt.Dimension(100, 50));
        pnl_encabezadoGC.setLayout(new java.awt.GridLayout(1, 0));

        lbl_tituloGC.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGC.setText("GESTION DE COMPRAS");
        pnl_encabezadoGC.add(lbl_tituloGC);

        getContentPane().add(pnl_encabezadoGC, java.awt.BorderLayout.NORTH);

        pnl_derechoGC.setPreferredSize(new java.awt.Dimension(50, 264));

        javax.swing.GroupLayout pnl_derechoGCLayout = new javax.swing.GroupLayout(pnl_derechoGC);
        pnl_derechoGC.setLayout(pnl_derechoGCLayout);
        pnl_derechoGCLayout.setHorizontalGroup(
            pnl_derechoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGCLayout.setVerticalGroup(
            pnl_derechoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGC, java.awt.BorderLayout.EAST);

        pnl_pieGC.setPreferredSize(new java.awt.Dimension(698, 50));
        pnl_pieGC.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGC.setText("Nuevo");
        btn_nuevoGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_nuevoGC);

        btn_cancelarGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGC.setText("Cancelar");
        btn_cancelarGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_cancelarGC);

        btn_guardarGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGC.setText("Guardar");
        btn_guardarGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_guardarGC);

        btn_imprimirGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_imprimirGC.setText("Imprimir");
        btn_imprimirGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_imprimirGC);

        btn_salirGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGC.setText("Salir");
        btn_salirGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_salirGC);

        getContentPane().add(pnl_pieGC, java.awt.BorderLayout.SOUTH);

        pnl_izquierdoGC.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout pnl_izquierdoGCLayout = new javax.swing.GroupLayout(pnl_izquierdoGC);
        pnl_izquierdoGC.setLayout(pnl_izquierdoGCLayout);
        pnl_izquierdoGCLayout.setHorizontalGroup(
            pnl_izquierdoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGCLayout.setVerticalGroup(
            pnl_izquierdoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGC, java.awt.BorderLayout.WEST);

        pnl_centralGC.setLayout(new java.awt.BorderLayout());

        tbp_menuGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        pnl_gestionGC.setLayout(new java.awt.BorderLayout());

        pnl_pieGestionGC.setPreferredSize(new java.awt.Dimension(722, 50));
        pnl_pieGestionGC.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 15));

        lbl_cantidadProductosCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbl_cantidadProductosCompraGC.setText("CANTIDAD DE PRODUCTOS:");
        pnl_pieGestionGC.add(lbl_cantidadProductosCompraGC);

        lbl_valorCantidadProductosGC.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbl_valorCantidadProductosGC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnl_pieGestionGC.add(lbl_valorCantidadProductosGC);

        lbl_subtotalCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbl_subtotalCompraGC.setText("SUBTOTAL:");
        pnl_pieGestionGC.add(lbl_subtotalCompraGC);

        lbl_valorSubtotalCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbl_valorSubtotalCompraGC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pnl_pieGestionGC.add(lbl_valorSubtotalCompraGC);

        pnl_gestionGC.add(pnl_pieGestionGC, java.awt.BorderLayout.SOUTH);

        pnl_centralGestionGC.setLayout(new java.awt.BorderLayout());

        pnl_centralFormularioGC.setPreferredSize(new java.awt.Dimension(1160, 200));
        pnl_centralFormularioGC.setLayout(new java.awt.BorderLayout());

        pnl_tablaDetalleCompraGC.setPreferredSize(new java.awt.Dimension(941, 150));
        pnl_tablaDetalleCompraGC.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 150));

        tb_listadoDetalleCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoDetalleCompraGC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_listadoDetalleCompraGC);

        pnl_tablaDetalleCompraGC.add(jScrollPane3);

        pnl_centralFormularioGC.add(pnl_tablaDetalleCompraGC, java.awt.BorderLayout.SOUTH);

        pnl_tablaProductoGC.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(452, 100));

        tb_listadoProductoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoProductoGC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tb_listadoProductoGC);

        pnl_tablaProductoGC.add(jScrollPane2, java.awt.BorderLayout.NORTH);

        pnl_botonesGC.setPreferredSize(new java.awt.Dimension(519, 50));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        pnl_botonesGC.setLayout(flowLayout1);

        lbl_cantidadProductoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_cantidadProductoGC.setText("Cantidad: ");
        pnl_botonesGC.add(lbl_cantidadProductoGC);

        txt_cantidadProductoGC.setColumns(3);
        txt_cantidadProductoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_cantidadProductoGC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pnl_botonesGC.add(txt_cantidadProductoGC);

        lbl_precioCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_precioCompraGC.setText("Precio de Compra:");
        pnl_botonesGC.add(lbl_precioCompraGC);

        txt_precioCompraGC.setColumns(6);
        txt_precioCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_precioCompraGC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pnl_botonesGC.add(txt_precioCompraGC);

        btn_agregarSeleccionadoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarSeleccionadoGC.setText("Agregar");
        btn_agregarSeleccionadoGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarSeleccionadoGCActionPerformed(evt);
            }
        });
        pnl_botonesGC.add(btn_agregarSeleccionadoGC);

        btn_quitarSeleccionadoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_quitarSeleccionadoGC.setText("Quitar");
        btn_quitarSeleccionadoGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarSeleccionadoGCActionPerformed(evt);
            }
        });
        pnl_botonesGC.add(btn_quitarSeleccionadoGC);

        pnl_tablaProductoGC.add(pnl_botonesGC, java.awt.BorderLayout.SOUTH);

        pnl_centralFormularioGC.add(pnl_tablaProductoGC, java.awt.BorderLayout.CENTER);

        pnl_formularioGC.setPreferredSize(new java.awt.Dimension(1160, 100));
        pnl_formularioGC.setLayout(new java.awt.GridLayout(2, 0));

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10);
        flowLayout2.setAlignOnBaseline(true);
        pnl_busquedaProductoGC.setLayout(flowLayout2);

        lbl_buscarProductoPorMarca.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarProductoPorMarca.setText("Buscar Producto Por Marca: ");
        pnl_busquedaProductoGC.add(lbl_buscarProductoPorMarca);

        cmb_BuscarProductoPorMarcaGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_BuscarProductoPorMarcaGC.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_BuscarProductoPorMarcaGC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_BuscarProductoPorMarcaGCItemStateChanged(evt);
            }
        });
        pnl_busquedaProductoGC.add(cmb_BuscarProductoPorMarcaGC);

        lbl_BuscarProductoPorTipoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_BuscarProductoPorTipoGC.setText("Buscar Producto Por Tipo: ");
        pnl_busquedaProductoGC.add(lbl_BuscarProductoPorTipoGC);

        cmb_buscarProductoPorTipoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_buscarProductoPorTipoGC.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_buscarProductoPorTipoGC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_buscarProductoPorTipoGCItemStateChanged(evt);
            }
        });
        pnl_busquedaProductoGC.add(cmb_buscarProductoPorTipoGC);

        lbl_buscarProductoPorNombreGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarProductoPorNombreGC.setText("Buscar Producto Por Nombre: ");
        pnl_busquedaProductoGC.add(lbl_buscarProductoPorNombreGC);

        txt_buscarProductoPorNombreGC.setColumns(15);
        txt_buscarProductoPorNombreGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarProductoPorNombreGC.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_buscarProductoPorNombreGC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarProductoPorNombreGCKeyReleased(evt);
            }
        });
        pnl_busquedaProductoGC.add(txt_buscarProductoPorNombreGC);

        pnl_formularioGC.add(pnl_busquedaProductoGC);

        pnl_centralFormularioGC.add(pnl_formularioGC, java.awt.BorderLayout.NORTH);

        pnl_centralGestionGC.add(pnl_centralFormularioGC, java.awt.BorderLayout.CENTER);

        pnl_gestionGC.add(pnl_centralGestionGC, java.awt.BorderLayout.CENTER);

        pnl_encabezadoGestionGC.setPreferredSize(new java.awt.Dimension(820, 50));
        pnl_encabezadoGestionGC.setLayout(new java.awt.BorderLayout());

        jdch_fechaCompraGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jdch_fechaCompraGC.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_encabezadoGestionGC.add(jdch_fechaCompraGC, java.awt.BorderLayout.EAST);

        pnl_gestionGC.add(pnl_encabezadoGestionGC, java.awt.BorderLayout.PAGE_START);

        tbp_menuGC.addTab("Gestion", pnl_gestionGC);

        pnl_listadoGC.setLayout(new java.awt.BorderLayout());

        tb_listadoComprasGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoComprasGC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_listadoComprasGC);

        pnl_listadoGC.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tbp_menuGC.addTab("Listado", pnl_listadoGC);

        pnl_centralGC.add(tbp_menuGC, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGC, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGCActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGCActionPerformed

    private void btn_nuevoGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGCActionPerformed
        this.cambiarEstado();
    }//GEN-LAST:event_btn_nuevoGCActionPerformed

    private void btn_cancelarGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGCActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGCActionPerformed

    private void btn_agregarSeleccionadoGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarSeleccionadoGCActionPerformed
        try {
            boolean registrado = false;

            if (tb_listadoProductoGC.getSelectedRowCount() <= 0) {

                throw new Exception("Debe Seleccionar un Producto");

            }
            if (tb_listadoProductoGC.getSelectedRowCount() >= 2) {

                throw new Exception("Debe seleccionar un solo producto");

            }

            if (tb_listadoProductoGC.getSelectedRowCount() == 1) {

                if (txt_cantidadProductoGC.getText().trim().equals("") || txt_precioCompraGC.getText().trim().equals("")) {
                    throw new Exception("Rellanar los campos obligatorios");
                } else {
                    int codigo = (int) tb_listadoProductoGC.getValueAt(tb_listadoProductoGC.getSelectedRow(), 0);
                    Producto productoSeleccionado = cp.getControladoraProducto().buscarProductoPorCodigo(codigo);

                    for (DetalleCompra detallesCompra : detallesCompra) {
                        if (detallesCompra.getProducto().getCodigo() == productoSeleccionado.getCodigo()) {
                            registrado = true;
                            break;
                        }
                    }

                    if (registrado == false) {
                        Integer cantidadCompra = Integer.valueOf(txt_cantidadProductoGC.getText().trim());
                        Float precioCompra = Float.valueOf(txt_precioCompraGC.getText().trim());
                        DetalleCompra detalleCompra = new DetalleCompra(productoSeleccionado.getNombre(), productoSeleccionado.getDescripcion(), cantidadCompra, precioCompra, cantidadCompra * precioCompra, productoSeleccionado);
                        detallesCompra.add(detalleCompra);

                        lbl_valorCantidadProductosGC.setText(String.valueOf(detalleCompra.sumarCantidadTotal(detallesCompra)));
                        lbl_valorSubtotalCompraGC.setText(String.valueOf(detalleCompra.sumarSubtotal(detallesCompra)));
                        cargarTablaDetalleCompra(detallesCompra);

                    } else {
                        throw new Exception("El producto ya esta agregado");

                    }

                }

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Compras", JOptionPane.WARNING_MESSAGE);
        }
        txt_cantidadProductoGC.setText("");
        txt_precioCompraGC.setText("");
        tb_listadoProductoGC.clearSelection();
    }//GEN-LAST:event_btn_agregarSeleccionadoGCActionPerformed

    private void btn_quitarSeleccionadoGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarSeleccionadoGCActionPerformed
        try {

            if (tb_listadoDetalleCompraGC.getSelectedRowCount() <= 0) {
                throw new Exception("Debe seleccionar un detalle");
            }

            if (tb_listadoDetalleCompraGC.getSelectedRowCount() >= 2) {
                throw new Exception("Debe seleccionar solo un detalle");
            }

            if (tb_listadoDetalleCompraGC.getSelectedRowCount() == 1) {

                String nombre = (String) tb_listadoDetalleCompraGC.getValueAt(tb_listadoDetalleCompraGC.getSelectedRow(), 1);

                tbcargaDetalleCompra = (DefaultTableModel) tb_listadoDetalleCompraGC.getModel();
                tbcargaDetalleCompra.removeRow(tb_listadoDetalleCompraGC.getSelectedRow());
                tb_listadoDetalleCompraGC.setModel(tbcargaDetalleCompra);

                for (DetalleCompra detallesCompra1 : detallesCompra) {
                    if (detallesCompra1.getNombreProducto() == nombre) {
                        lbl_valorCantidadProductosGC.setText(String.valueOf(detallesCompra1.restarCantidadTotal(detallesCompra, detallesCompra1.getCantidad())));
                        lbl_valorSubtotalCompraGC.setText(String.valueOf(detallesCompra1.restarSubtotal(detallesCompra, detallesCompra1.getSubtotal())));
                        detallesCompra.remove(detallesCompra1);
                    }

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Compras", JOptionPane.WARNING_MESSAGE);

        }

        tb_listadoDetalleCompraGC.clearSelection();
    }//GEN-LAST:event_btn_quitarSeleccionadoGCActionPerformed

    private void btn_guardarGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGCActionPerformed
        try {
            if (tb_listadoDetalleCompraGC.getRowCount() <= 0) {
                throw new Exception("Debe agregar al menos un [1] producto al detalle");
            }

            Date fechaCompra = jdch_fechaCompraGC.getDate();
            Date fechaActual = new Date();

            if (fechaCompra.after(fechaActual)) {
                throw new Exception("No se puede generar una compra con una fecha posterior a la actual");
            }

            DetalleCompra detalleCompra = new DetalleCompra();
            Compra compra = new Compra(fechaActual, detalleCompra.sumarSubtotal(detallesCompra), usuario);

            compra = this.cp.getControladoraCompra().registrarCompra(compra);

            for (DetalleCompra detallesCompra1 : detallesCompra) {
                detallesCompra1.setCompra(compra);
                this.cp.getControladoraCompra().registrarDetalleCompra(detallesCompra1);
                this.cp.getControladoraProducto().compreProducto(detallesCompra1.getCantidad(), detallesCompra1.getProducto().getCodigo());
            }

            JOptionPane.showMessageDialog(null, "Creado Con Exito", "Gestion De Compra", JOptionPane.INFORMATION_MESSAGE);

            this.estadoInicial();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Compra", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarGCActionPerformed

    private void cmb_BuscarProductoPorMarcaGCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_BuscarProductoPorMarcaGCItemStateChanged
        if (cmb_BuscarProductoPorMarcaGC.getSelectedItem() != "[SELECCIONAR]" && cmb_BuscarProductoPorMarcaGC.getSelectedItem() != null) {
            Marca marca = (Marca) cmb_BuscarProductoPorMarcaGC.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarca(marca));
            Utilidades.cargarCombo(marca.getTiposProductos(), cmb_buscarProductoPorTipoGC);
            cmb_buscarProductoPorTipoGC.setEnabled(isEnabled());
        } else {
            cmb_buscarProductoPorTipoGC.removeAllItems();
            cmb_buscarProductoPorTipoGC.addItem("[SELECCIONAR]");
            cmb_buscarProductoPorTipoGC.setEnabled(!isEnabled());
            cargarTablaProductos(this.cp.getControladoraProducto().listarProductos());

        }
    }//GEN-LAST:event_cmb_BuscarProductoPorMarcaGCItemStateChanged

    private void cmb_buscarProductoPorTipoGCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_buscarProductoPorTipoGCItemStateChanged
        if (cmb_buscarProductoPorTipoGC.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorTipoGC.getSelectedItem() != null) {
            Marca marca = (Marca) cmb_BuscarProductoPorMarcaGC.getSelectedItem();
            TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGC.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarcayTipo(marca, tipo));
            txt_buscarProductoPorNombreGC.setEnabled(isEnabled());
        } else {
            if (cmb_BuscarProductoPorMarcaGC.getSelectedItem() != "[SELECCIONAR]" && cmb_BuscarProductoPorMarcaGC.getSelectedItem() != null) {
                Marca marca = (Marca) cmb_BuscarProductoPorMarcaGC.getSelectedItem();
                cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarca(marca));
            }

            txt_buscarProductoPorNombreGC.setText("");
            txt_buscarProductoPorNombreGC.setEnabled(!isEnabled());
        }
    }//GEN-LAST:event_cmb_buscarProductoPorTipoGCItemStateChanged

    private void txt_buscarProductoPorNombreGCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarProductoPorNombreGCKeyReleased
        if (!txt_buscarProductoPorNombreGC.getText().trim().isEmpty()) {
            String nombre = txt_buscarProductoPorNombreGC.getText().trim();
            Marca marca = (Marca) cmb_BuscarProductoPorMarcaGC.getSelectedItem();
            TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGC.getSelectedItem();
            cargarTablaProductos(this.cp.getControladoraProducto().filtrarProducto(nombre, marca, tipo));
        } else {
            if (cmb_buscarProductoPorTipoGC.getSelectedItem() != "[SELECCIONAR]" && cmb_buscarProductoPorTipoGC.getSelectedItem() != null) {
                Marca marca = (Marca) cmb_BuscarProductoPorMarcaGC.getSelectedItem();
                TipoProducto tipo = (TipoProducto) cmb_buscarProductoPorTipoGC.getSelectedItem();
                cargarTablaProductos(this.cp.getControladoraProducto().filtrarProductoPorMarcayTipo(marca, tipo));

            }

        }
    }//GEN-LAST:event_txt_buscarProductoPorNombreGCKeyReleased

    private void btn_imprimirGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirGCActionPerformed
        try {
            if (tb_listadoComprasGC.getSelectedRowCount() == 1) {
                JFrame visualizador = new JFrame();
                Reporte reporte = new Reporte();
                Map parametros = new HashMap();

                int codigoCompra = (int) tb_listadoComprasGC.getValueAt(tb_listadoComprasGC.getSelectedRow(), 0);
                
                parametros.put("idcompra",codigoCompra);
                visualizador.setContentPane(reporte.crearReporteCompra("/reportes/reporteComprabanteCompra.jasper", parametros));
                visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                visualizador.setVisible(true);

            }else{
                if(tb_listadoComprasGC.getSelectedRowCount()>=2){
                    throw new Exception("Debe seleccionar  una sola compra");
                }
                
                if(tb_listadoComprasGC.getSelectedRowCount()<=0){
                    throw new Exception("Debe seleccionar al menos una compra");
                }
            }
            
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Compra", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btn_imprimirGCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregarSeleccionadoGC;
    private javax.swing.JButton btn_cancelarGC;
    private javax.swing.JButton btn_guardarGC;
    private javax.swing.JButton btn_imprimirGC;
    private javax.swing.JButton btn_nuevoGC;
    private javax.swing.JButton btn_quitarSeleccionadoGC;
    private javax.swing.JButton btn_salirGC;
    private javax.swing.JComboBox cmb_BuscarProductoPorMarcaGC;
    private javax.swing.JComboBox cmb_buscarProductoPorTipoGC;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdch_fechaCompraGC;
    private javax.swing.JLabel lbl_BuscarProductoPorTipoGC;
    private javax.swing.JLabel lbl_buscarProductoPorMarca;
    private javax.swing.JLabel lbl_buscarProductoPorNombreGC;
    private javax.swing.JLabel lbl_cantidadProductoGC;
    private javax.swing.JLabel lbl_cantidadProductosCompraGC;
    private javax.swing.JLabel lbl_precioCompraGC;
    private javax.swing.JLabel lbl_subtotalCompraGC;
    private javax.swing.JLabel lbl_tituloGC;
    private javax.swing.JLabel lbl_valorCantidadProductosGC;
    private javax.swing.JLabel lbl_valorSubtotalCompraGC;
    private javax.swing.JPanel pnl_botonesGC;
    private javax.swing.JPanel pnl_busquedaProductoGC;
    private javax.swing.JPanel pnl_centralFormularioGC;
    private javax.swing.JPanel pnl_centralGC;
    private javax.swing.JPanel pnl_centralGestionGC;
    private javax.swing.JPanel pnl_derechoGC;
    private javax.swing.JPanel pnl_encabezadoGC;
    private javax.swing.JPanel pnl_encabezadoGestionGC;
    private javax.swing.JPanel pnl_formularioGC;
    private javax.swing.JPanel pnl_gestionGC;
    private javax.swing.JPanel pnl_izquierdoGC;
    private javax.swing.JPanel pnl_listadoGC;
    private javax.swing.JPanel pnl_pieGC;
    private javax.swing.JPanel pnl_pieGestionGC;
    private javax.swing.JPanel pnl_tablaDetalleCompraGC;
    private javax.swing.JPanel pnl_tablaProductoGC;
    private javax.swing.JTable tb_listadoComprasGC;
    private javax.swing.JTable tb_listadoDetalleCompraGC;
    private javax.swing.JTable tb_listadoProductoGC;
    private javax.swing.JTabbedPane tbp_menuGC;
    private javax.swing.JTextField txt_buscarProductoPorNombreGC;
    private javax.swing.JTextField txt_cantidadProductoGC;
    private javax.swing.JTextField txt_precioCompraGC;
    // End of variables declaration//GEN-END:variables
}
