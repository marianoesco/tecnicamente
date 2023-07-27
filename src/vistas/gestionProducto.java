/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Font;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Marca;
import modelo.Producto;
import modelo.TipoProducto;
import modelo.Usuario;
import modelo.Utilidades;

/**
 *
 * @author EcobarM
 */
public class gestionProducto extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private Usuario usuario;
    private JDesktopPane dskp_principalVP;

    /**
     * Creates new form gestionProducto
     */
    public gestionProducto(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario = usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();
    }

    public void estadoInicial() {
        txt_codigoGP.setEnabled(!isEnabled());
        txt_nombreGP.setEnabled(!isEnabled());
        txt_precioGP.setEnabled(!isEnabled());
        txtA_descripcionGP.setEnabled(!isEnabled());
        cmb_marcaGP.setEnabled(!isEnabled());
        cmb_tipoProductoGP.setEnabled(!isEnabled());

        txt_buscarProductoPorNombreGP.setEnabled(isEnabled());

        btn_nuevoGP.setEnabled(isEnabled());
        btn_editarGP.setEnabled(isEnabled());
        btn_cancelarGP.setEnabled(!isEnabled());
        btn_guardarGP.setEnabled(!isEnabled());
        btn_salirGP.setEnabled(isEnabled());

        txt_codigoGP.setText("");
        txt_nombreGP.setText("");
        txt_precioGP.setText("");
        txtA_descripcionGP.setText("");

        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_marcaGP);

        if (cmb_marcaGP.getSelectedItem() == "[SELECCIONAR]") {
            cmb_tipoProductoGP.removeAllItems();
            cmb_tipoProductoGP.addItem("[SELECCIONAR]");
        }

        cargarTabla(this.cp.getControladoraProducto().listarProductos());
    }

    public void cambiarEstado() {
        
        Utilidades.cargarCombo(this.cp.getControladoraProducto().listarMarcas(), cmb_marcaGP);
        
        txt_nombreGP.setEnabled(isEnabled());

        txt_precioGP.setEnabled(isEnabled());
        
        txtA_descripcionGP.setEnabled(isEnabled());
        cmb_marcaGP.setEnabled(isEnabled());

        btn_nuevoGP.setEnabled(!isEnabled());
        btn_editarGP.setEnabled(!isEnabled());
        btn_cancelarGP.setEnabled(isEnabled());
        btn_guardarGP.setEnabled(isEnabled());

        

    }

    public void cargarTabla(List<Producto> productos) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        if (!productos.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre", "Descripcion", "Marca", "Tipo de Producto", "Cantidad", "Precio"};
            tbCarga.setColumnIdentifiers(Cabecera);
            tb_listadoProductosGP.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            Object fila[] = new Object[tbCarga.getColumnCount()];
            for (Producto p : productos) {
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
        
        tb_listadoProductosGP.setModel(tbCarga);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGP = new javax.swing.JPanel();
        lbl_tituloGP = new javax.swing.JLabel();
        pnl_izquierdoGP = new javax.swing.JPanel();
        pnl_pieGP = new javax.swing.JPanel();
        btn_nuevoGP = new javax.swing.JButton();
        btn_editarGP = new javax.swing.JButton();
        btn_cancelarGP = new javax.swing.JButton();
        btn_guardarGP = new javax.swing.JButton();
        btn_salirGP = new javax.swing.JButton();
        pnl_derechoGP = new javax.swing.JPanel();
        pnl_centralGP = new javax.swing.JPanel();
        pnl_encabezadoCentralGP = new javax.swing.JPanel();
        lbl_buscarProductoPorNombreGP = new javax.swing.JLabel();
        txt_buscarProductoPorNombreGP = new javax.swing.JTextField();
        pnl_formularioCentralGP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl_codigoGP = new javax.swing.JLabel();
        txt_codigoGP = new javax.swing.JTextField();
        lbl_nombreGP = new javax.swing.JLabel();
        txt_nombreGP = new javax.swing.JTextField();
        lbl_descripcionGP = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_descripcionGP = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        lbl_MarcaGP = new javax.swing.JLabel();
        cmb_marcaGP = new javax.swing.JComboBox();
        lbl_tipoProductoGP = new javax.swing.JLabel();
        cmb_tipoProductoGP = new javax.swing.JComboBox();
        lbl_precioGP = new javax.swing.JLabel();
        txt_precioGP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoProductosGP = new javax.swing.JTable();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion Productos");

        pnl_encabezadoGP.setPreferredSize(new java.awt.Dimension(692, 50));
        pnl_encabezadoGP.setLayout(new java.awt.BorderLayout());

        lbl_tituloGP.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGP.setText("Gestion de Productos");
        pnl_encabezadoGP.add(lbl_tituloGP, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_encabezadoGP, java.awt.BorderLayout.NORTH);

        pnl_izquierdoGP.setPreferredSize(new java.awt.Dimension(50, 159));

        javax.swing.GroupLayout pnl_izquierdoGPLayout = new javax.swing.GroupLayout(pnl_izquierdoGP);
        pnl_izquierdoGP.setLayout(pnl_izquierdoGPLayout);
        pnl_izquierdoGPLayout.setHorizontalGroup(
            pnl_izquierdoGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGPLayout.setVerticalGroup(
            pnl_izquierdoGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGP, java.awt.BorderLayout.WEST);

        pnl_pieGP.setPreferredSize(new java.awt.Dimension(692, 50));
        pnl_pieGP.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGP.setText("Nuevo");
        btn_nuevoGP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGPActionPerformed(evt);
            }
        });
        pnl_pieGP.add(btn_nuevoGP);

        btn_editarGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_editarGP.setText("Editar");
        btn_editarGP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGPActionPerformed(evt);
            }
        });
        pnl_pieGP.add(btn_editarGP);

        btn_cancelarGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGP.setText("Cancelar");
        btn_cancelarGP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGPActionPerformed(evt);
            }
        });
        pnl_pieGP.add(btn_cancelarGP);

        btn_guardarGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGP.setText("Guardar");
        btn_guardarGP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGPActionPerformed(evt);
            }
        });
        pnl_pieGP.add(btn_guardarGP);

        btn_salirGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGP.setText("Salir");
        btn_salirGP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGPActionPerformed(evt);
            }
        });
        pnl_pieGP.add(btn_salirGP);

        getContentPane().add(pnl_pieGP, java.awt.BorderLayout.SOUTH);

        pnl_derechoGP.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout pnl_derechoGPLayout = new javax.swing.GroupLayout(pnl_derechoGP);
        pnl_derechoGP.setLayout(pnl_derechoGPLayout);
        pnl_derechoGPLayout.setHorizontalGroup(
            pnl_derechoGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGPLayout.setVerticalGroup(
            pnl_derechoGPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGP, java.awt.BorderLayout.EAST);

        pnl_centralGP.setLayout(new java.awt.BorderLayout());

        pnl_encabezadoCentralGP.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        lbl_buscarProductoPorNombreGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarProductoPorNombreGP.setText("Buscar Por Nombre: ");
        pnl_encabezadoCentralGP.add(lbl_buscarProductoPorNombreGP);

        txt_buscarProductoPorNombreGP.setColumns(10);
        txt_buscarProductoPorNombreGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarProductoPorNombreGP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarProductoPorNombreGPKeyReleased(evt);
            }
        });
        pnl_encabezadoCentralGP.add(txt_buscarProductoPorNombreGP);

        pnl_centralGP.add(pnl_encabezadoCentralGP, java.awt.BorderLayout.PAGE_START);

        pnl_formularioCentralGP.setPreferredSize(new java.awt.Dimension(718, 150));
        pnl_formularioCentralGP.setLayout(new java.awt.GridLayout(2, 0));

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        lbl_codigoGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_codigoGP.setText("Codigo:");
        jPanel1.add(lbl_codigoGP);

        txt_codigoGP.setColumns(2);
        txt_codigoGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_codigoGP);

        lbl_nombreGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_nombreGP.setText("Nombre: ");
        jPanel1.add(lbl_nombreGP);

        txt_nombreGP.setColumns(15);
        txt_nombreGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_nombreGP);

        lbl_descripcionGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_descripcionGP.setText("Descripcion: ");
        jPanel1.add(lbl_descripcionGP);

        txtA_descripcionGP.setColumns(30);
        txtA_descripcionGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtA_descripcionGP.setLineWrap(true);
        txtA_descripcionGP.setRows(2);
        jScrollPane2.setViewportView(txtA_descripcionGP);

        jPanel1.add(jScrollPane2);

        pnl_formularioCentralGP.add(jPanel1);

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout();
        flowLayout2.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout2);

        lbl_MarcaGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_MarcaGP.setText("Marca: ");
        jPanel2.add(lbl_MarcaGP);

        cmb_marcaGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_marcaGP.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_marcaGP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_marcaGPItemStateChanged(evt);
            }
        });
        jPanel2.add(cmb_marcaGP);

        lbl_tipoProductoGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_tipoProductoGP.setText("Tipo Producto: ");
        jPanel2.add(lbl_tipoProductoGP);

        cmb_tipoProductoGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_tipoProductoGP.setPreferredSize(new java.awt.Dimension(130, 25));
        jPanel2.add(cmb_tipoProductoGP);

        lbl_precioGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_precioGP.setText("Precio: ");
        jPanel2.add(lbl_precioGP);

        txt_precioGP.setColumns(6);
        txt_precioGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_precioGP.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txt_precioGP);

        pnl_formularioCentralGP.add(jPanel2);

        pnl_centralGP.add(pnl_formularioCentralGP, java.awt.BorderLayout.SOUTH);

        tb_listadoProductosGP.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoProductosGP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_listadoProductosGP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_listadoProductosGPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_listadoProductosGP);

        pnl_centralGP.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGP, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tb_listadoProductosGPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listadoProductosGPMouseClicked

    }//GEN-LAST:event_tb_listadoProductosGPMouseClicked

    private void btn_editarGPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGPActionPerformed
        if (tb_listadoProductosGP.getSelectedRow() > -1 && tb_listadoProductosGP.getSelectedRowCount() == 1) {

            Integer seleccionado = (Integer) tb_listadoProductosGP.getValueAt(tb_listadoProductosGP.getSelectedRow(), 0);
            Producto productoSeleccionado = this.cp.getControladoraProducto().buscarProductoPorCodigo(seleccionado);

            if (productoSeleccionado != null) {
                this.cambiarEstado();
                txt_codigoGP.setText(String.valueOf(productoSeleccionado.getCodigo()));
                txt_nombreGP.setText(productoSeleccionado.getNombre());
                txtA_descripcionGP.setText(productoSeleccionado.getDescripcion());
                txt_precioGP.setText(String.valueOf(productoSeleccionado.getPrecio()));
                cmb_marcaGP.setSelectedItem(productoSeleccionado.getMarca());
                cmb_tipoProductoGP.setSelectedItem(productoSeleccionado.getTipoProducto());
            }
            tb_listadoProductosGP.clearSelection();
            
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto", "Gestion de Productos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_editarGPActionPerformed

    private void btn_nuevoGPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGPActionPerformed
        this.cambiarEstado();
        tb_listadoProductosGP.clearSelection();
    }//GEN-LAST:event_btn_nuevoGPActionPerformed

    private void btn_cancelarGPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGPActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGPActionPerformed

    private void btn_salirGPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGPActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGPActionPerformed

    private void btn_guardarGPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGPActionPerformed
        try {
            if (!txt_nombreGP.getText().trim().isEmpty() && !txtA_descripcionGP.getText().trim().isEmpty()
                    && cmb_marcaGP.getSelectedItem() != "[SELECCIONAR]" && cmb_marcaGP != null && cmb_tipoProductoGP.getSelectedItem() != "[SELECCIONAR]"
                    && cmb_tipoProductoGP.getSelectedItem() != null && !txt_precioGP.getText().trim().isEmpty()) {

                String nombre = txt_nombreGP.getText().trim();
                String descripcion = txtA_descripcionGP.getText().trim();
                float precio = Float.valueOf(txt_precioGP.getText().trim());
                
                Marca marca = (Marca) cmb_marcaGP.getSelectedItem();
                TipoProducto tipoProducto = (TipoProducto) cmb_tipoProductoGP.getSelectedItem();

                if (txt_codigoGP.getText().isEmpty()) {
                    Producto producto = new Producto(nombre, descripcion, precio, marca, tipoProducto);
                    this.cp.getControladoraProducto().registrarProducto(producto);
                    
                    JOptionPane.showMessageDialog(null,"Creado con exito", "Gestion de Productos", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (txt_precioGP.getText().trim().isEmpty() || Float.valueOf(txt_precioGP.getText().trim()) <= 0) {
                        throw new Exception("Debe ingresar un precio al producto");
                    }

                    Integer codigo = Integer.valueOf(txt_codigoGP.getText().trim());
                    Producto producto = this.cp.getControladoraProducto().buscarProductoPorCodigo(codigo);
                    
                    producto.setDescripcion(descripcion);
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setMarca(marca);
                    producto.setTipoProducto(tipoProducto);

                    this.cp.getControladoraProducto().modificarProducto(producto);
                    
                    JOptionPane.showMessageDialog(null,"Editado con exito", "Gestion de Productos", JOptionPane.INFORMATION_MESSAGE);

                }

                this.estadoInicial();
            } else {
                throw new Exception("Todos los campos son obligatorios");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Productos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarGPActionPerformed

    private void txt_buscarProductoPorNombreGPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarProductoPorNombreGPKeyReleased
        if (!txt_buscarProductoPorNombreGP.getText().trim().isEmpty()) {
            String nombre = txt_buscarProductoPorNombreGP.getText().trim();
            cargarTabla(this.cp.getControladoraProducto().filtrarProductoPorNombre(nombre));
        } else {
            cargarTabla(this.cp.getControladoraProducto().listarProductos());
        }
    }//GEN-LAST:event_txt_buscarProductoPorNombreGPKeyReleased

    private void cmb_marcaGPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_marcaGPItemStateChanged
        if (cmb_marcaGP.getSelectedItem() != "[SELECCIONAR]" && cmb_marcaGP != null) {
            Marca marca = (Marca) cmb_marcaGP.getSelectedItem();
            Utilidades.cargarCombo(marca.getTiposProductos(), cmb_tipoProductoGP);
            cmb_tipoProductoGP.setEnabled(isEnabled());
        } else {
            cmb_tipoProductoGP.removeAllItems();
            cmb_tipoProductoGP.addItem("[SELECCIONAR]");
            cmb_tipoProductoGP.setEnabled(!isEnabled());
        }
    }//GEN-LAST:event_cmb_marcaGPItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarGP;
    private javax.swing.JButton btn_editarGP;
    private javax.swing.JButton btn_guardarGP;
    private javax.swing.JButton btn_nuevoGP;
    private javax.swing.JButton btn_salirGP;
    private javax.swing.JComboBox cmb_marcaGP;
    private javax.swing.JComboBox cmb_tipoProductoGP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_MarcaGP;
    private javax.swing.JLabel lbl_buscarProductoPorNombreGP;
    private javax.swing.JLabel lbl_codigoGP;
    private javax.swing.JLabel lbl_descripcionGP;
    private javax.swing.JLabel lbl_nombreGP;
    private javax.swing.JLabel lbl_precioGP;
    private javax.swing.JLabel lbl_tipoProductoGP;
    private javax.swing.JLabel lbl_tituloGP;
    private javax.swing.JPanel pnl_centralGP;
    private javax.swing.JPanel pnl_derechoGP;
    private javax.swing.JPanel pnl_encabezadoCentralGP;
    private javax.swing.JPanel pnl_encabezadoGP;
    private javax.swing.JPanel pnl_formularioCentralGP;
    private javax.swing.JPanel pnl_izquierdoGP;
    private javax.swing.JPanel pnl_pieGP;
    private javax.swing.JTable tb_listadoProductosGP;
    private javax.swing.JTextArea txtA_descripcionGP;
    private javax.swing.JTextField txt_buscarProductoPorNombreGP;
    private javax.swing.JTextField txt_codigoGP;
    private javax.swing.JTextField txt_nombreGP;
    private javax.swing.JTextField txt_precioGP;
    // End of variables declaration//GEN-END:variables
}