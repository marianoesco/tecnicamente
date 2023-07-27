/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelo.Localidad;
import modelo.NivelDeUsuario;
import modelo.Permiso;
import modelo.Usuario;
import static vistas.VentanaPrincipal.mnu_parametrosVP;

/**
 *
 * @author USUARI
 */
public class gestionNiveles extends javax.swing.JInternalFrame {

    /**
     * Creates new form gestionNiveles
     */
    private ControladoraPrincipal cp;
    private Usuario usuario;
    private JDesktopPane dskp_principalVP;
    
    public gestionNiveles(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario = usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial(this.usuario.getNivel().getPermiso());
        
    }
    
    public void estadoInicial(Permiso permiso) {
        txt_codigoGN.setEnabled(!isEnabled());
        txt_nombreGN.setEnabled(!isEnabled());
        
        
        txt_buscarPorNombreGN.setEnabled(isEnabled());
        
        txt_buscarPorNombreGN.setText("");
        
        chb_clienteGN.setEnabled(!isEnabled());
        chb_compraGN.setEnabled(!isEnabled());
        chb_informe.setEnabled(!isEnabled());
        chb_parametroGN.setEnabled(!isEnabled());
        chb_productoGN.setEnabled(!isEnabled());
        chb_servicioTecnicoGN.setEnabled(!isEnabled());
        chb_usuarioGN.setEnabled(!isEnabled());
        chb_ventaGN.setEnabled(!isEnabled());
        chb_servicioGN.setEnabled(!isEnabled());
        chb_equipoGN.setEnabled(!isEnabled());
        
        btn_nuevoGN.setEnabled(isEnabled());
        btn_editarGN.setEnabled(isEnabled());
        btn_cancelarGN.setEnabled(!isEnabled());
        btn_guardarGN.setEnabled(!isEnabled());
        btn_salirGN.setEnabled(isEnabled());
        
        txt_codigoGN.setText("");
        txt_nombreGN.setText("");
        
        chb_clienteGN.setSelected(false);
        chb_compraGN.setSelected(false);
        chb_informe.setSelected(false);
        chb_parametroGN.setSelected(false);
        chb_productoGN.setSelected(false);
        chb_servicioTecnicoGN.setSelected(false);
        chb_usuarioGN.setSelected(false);
        chb_ventaGN.setSelected(false);
        chb_equipoGN.setSelected(false);
        chb_servicioGN.setSelected(false);
        
        tb_listadoNivelesGN.setEnabled(isEnabled());
        
        tb_listadoNivelesGN.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
        
        cargarTabla(this.cp.getControladoraLogueo().listarNiveles());
        
        nivelesVentana(permiso);
    }
    
    public void nivelesVentana(Permiso permiso) {
        
        if (permiso.isGestionParametro() == true) {
            VentanaPrincipal.mnu_parametrosVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_parametrosVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionEquipo() == true) {
            VentanaPrincipal.mnu_equiposVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_equiposVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionCliente() == true) {
            VentanaPrincipal.mnu_clienteVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_clienteVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionCompra() == true) {
            VentanaPrincipal.mnu_compraVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_compraVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionInforme() == true) {
            VentanaPrincipal.mnu_informesVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_informesVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionServicioTecnico() == true) {
            VentanaPrincipal.mnu_servicioTecnicoVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_servicioTecnicoVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionUsuario() == true) {
            VentanaPrincipal.mnu_usuarioVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_usuarioVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionVenta() == true) {
            VentanaPrincipal.mnu_ventaVP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_ventaVP.setEnabled(!isEnabled());
        }
        
        if (permiso.isGestionProducto() == true) {
            VentanaPrincipal.mnu_producto_VP.setEnabled(isEnabled());
        } else {
            VentanaPrincipal.mnu_producto_VP.setEnabled(!isEnabled());
        }
        
    }
    
    public void cambiarEstado() {
        txt_codigoGN.setEnabled(!isEnabled());
        txt_nombreGN.setEnabled(isEnabled());
        
        tb_listadoNivelesGN.setEnabled(!isEnabled());
        
        chb_clienteGN.setEnabled(isEnabled());
        chb_compraGN.setEnabled(isEnabled());
        chb_informe.setEnabled(isEnabled());
        chb_parametroGN.setEnabled(isEnabled());
        chb_productoGN.setEnabled(isEnabled());
        chb_servicioTecnicoGN.setEnabled(isEnabled());
        chb_usuarioGN.setEnabled(isEnabled());
        chb_ventaGN.setEnabled(isEnabled());
        chb_servicioGN.setEnabled(isEnabled());
        chb_equipoGN.setEnabled(isEnabled());
        
        btn_nuevoGN.setEnabled(!isEnabled());
        btn_editarGN.setEnabled(!isEnabled());
        btn_cancelarGN.setEnabled(isEnabled());
        btn_guardarGN.setEnabled(isEnabled());
        
    }
    
    public void cargarTabla(List<NivelDeUsuario> niveles) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if (!niveles.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tbCarga.getColumnCount()];
            for (NivelDeUsuario n : niveles) {
                fila[0] = n.getCodigo();
                fila[1] = n.getNombre();
                tbCarga.addRow(fila);
            }
            
        }
        tb_listadoNivelesGN.setModel(tbCarga);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGN = new javax.swing.JPanel();
        lbl_titutloGN = new javax.swing.JLabel();
        pnl_derechoGN = new javax.swing.JPanel();
        pnl_izquierdoGN = new javax.swing.JPanel();
        pnl_pieGN = new javax.swing.JPanel();
        btn_nuevoGN = new javax.swing.JButton();
        btn_editarGN = new javax.swing.JButton();
        btn_cancelarGN = new javax.swing.JButton();
        btn_guardarGN = new javax.swing.JButton();
        btn_salirGN = new javax.swing.JButton();
        pnl_centralGN = new javax.swing.JPanel();
        pnl_formularioGN = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chb_parametroGN = new javax.swing.JCheckBox();
        chb_compraGN = new javax.swing.JCheckBox();
        chb_ventaGN = new javax.swing.JCheckBox();
        chb_servicioGN = new javax.swing.JCheckBox();
        chb_servicioTecnicoGN = new javax.swing.JCheckBox();
        chb_equipoGN = new javax.swing.JCheckBox();
        chb_clienteGN = new javax.swing.JCheckBox();
        chb_productoGN = new javax.swing.JCheckBox();
        chb_informe = new javax.swing.JCheckBox();
        chb_usuarioGN = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        lbl_codigoGN = new javax.swing.JLabel();
        txt_codigoGN = new javax.swing.JTextField();
        lbl_nombreGN = new javax.swing.JLabel();
        txt_nombreGN = new javax.swing.JTextField();
        pnl_derechoCentralGN = new javax.swing.JPanel();
        pnl_izquierdoCentralGN = new javax.swing.JPanel();
        pnl_encabezadoCentralGN = new javax.swing.JPanel();
        lbl_buscarPorNivelesGN = new javax.swing.JLabel();
        txt_buscarPorNombreGN = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_listadoNivelesGN = new javax.swing.JTable();

        setBorder(null);

        pnl_encabezadoGN.setPreferredSize(new java.awt.Dimension(819, 50));
        pnl_encabezadoGN.setLayout(new java.awt.BorderLayout());

        lbl_titutloGN.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_titutloGN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_titutloGN.setText("Gestion de Niveles");
        pnl_encabezadoGN.add(lbl_titutloGN, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_encabezadoGN, java.awt.BorderLayout.NORTH);

        pnl_derechoGN.setPreferredSize(new java.awt.Dimension(30, 222));

        javax.swing.GroupLayout pnl_derechoGNLayout = new javax.swing.GroupLayout(pnl_derechoGN);
        pnl_derechoGN.setLayout(pnl_derechoGNLayout);
        pnl_derechoGNLayout.setHorizontalGroup(
            pnl_derechoGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        pnl_derechoGNLayout.setVerticalGroup(
            pnl_derechoGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGN, java.awt.BorderLayout.EAST);

        pnl_izquierdoGN.setPreferredSize(new java.awt.Dimension(30, 222));

        javax.swing.GroupLayout pnl_izquierdoGNLayout = new javax.swing.GroupLayout(pnl_izquierdoGN);
        pnl_izquierdoGN.setLayout(pnl_izquierdoGNLayout);
        pnl_izquierdoGNLayout.setHorizontalGroup(
            pnl_izquierdoGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        pnl_izquierdoGNLayout.setVerticalGroup(
            pnl_izquierdoGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 686, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGN, java.awt.BorderLayout.WEST);

        pnl_pieGN.setPreferredSize(new java.awt.Dimension(819, 50));
        pnl_pieGN.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGN.setText("Nuevo");
        btn_nuevoGN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGNActionPerformed(evt);
            }
        });
        pnl_pieGN.add(btn_nuevoGN);

        btn_editarGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_editarGN.setText("Editar");
        btn_editarGN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGNActionPerformed(evt);
            }
        });
        pnl_pieGN.add(btn_editarGN);

        btn_cancelarGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGN.setText("Cancelar");
        btn_cancelarGN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGNActionPerformed(evt);
            }
        });
        pnl_pieGN.add(btn_cancelarGN);

        btn_guardarGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGN.setText("Guardar");
        btn_guardarGN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGNActionPerformed(evt);
            }
        });
        pnl_pieGN.add(btn_guardarGN);

        btn_salirGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGN.setText("Salir");
        btn_salirGN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGNActionPerformed(evt);
            }
        });
        pnl_pieGN.add(btn_salirGN);

        getContentPane().add(pnl_pieGN, java.awt.BorderLayout.SOUTH);

        pnl_centralGN.setLayout(new java.awt.BorderLayout());

        pnl_formularioGN.setPreferredSize(new java.awt.Dimension(1071, 100));
        pnl_formularioGN.setLayout(new java.awt.GridLayout(2, 0));

        chb_parametroGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_parametroGN.setText("Parametro");
        jPanel2.add(chb_parametroGN);

        chb_compraGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_compraGN.setText("Compra");
        jPanel2.add(chb_compraGN);

        chb_ventaGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_ventaGN.setText("Venta");
        jPanel2.add(chb_ventaGN);

        chb_servicioGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_servicioGN.setText("Servicio");
        jPanel2.add(chb_servicioGN);

        chb_servicioTecnicoGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_servicioTecnicoGN.setText("Servicio Tecnico");
        jPanel2.add(chb_servicioTecnicoGN);

        chb_equipoGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_equipoGN.setText("Equipo");
        jPanel2.add(chb_equipoGN);

        chb_clienteGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_clienteGN.setText("Cliente");
        jPanel2.add(chb_clienteGN);

        chb_productoGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_productoGN.setText("Producto");
        jPanel2.add(chb_productoGN);

        chb_informe.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_informe.setText("Informe");
        jPanel2.add(chb_informe);

        chb_usuarioGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        chb_usuarioGN.setText("Usuario");
        jPanel2.add(chb_usuarioGN);

        pnl_formularioGN.add(jPanel2);

        lbl_codigoGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_codigoGN.setText("Codigo: ");
        jPanel1.add(lbl_codigoGN);

        txt_codigoGN.setColumns(3);
        txt_codigoGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_codigoGN);

        lbl_nombreGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_nombreGN.setText("Nombre: ");
        jPanel1.add(lbl_nombreGN);

        txt_nombreGN.setColumns(10);
        txt_nombreGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_nombreGN);

        pnl_formularioGN.add(jPanel1);

        pnl_centralGN.add(pnl_formularioGN, java.awt.BorderLayout.SOUTH);

        pnl_derechoCentralGN.setPreferredSize(new java.awt.Dimension(150, 485));

        javax.swing.GroupLayout pnl_derechoCentralGNLayout = new javax.swing.GroupLayout(pnl_derechoCentralGN);
        pnl_derechoCentralGN.setLayout(pnl_derechoCentralGNLayout);
        pnl_derechoCentralGNLayout.setHorizontalGroup(
            pnl_derechoCentralGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnl_derechoCentralGNLayout.setVerticalGroup(
            pnl_derechoCentralGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        pnl_centralGN.add(pnl_derechoCentralGN, java.awt.BorderLayout.EAST);

        pnl_izquierdoCentralGN.setPreferredSize(new java.awt.Dimension(150, 485));

        javax.swing.GroupLayout pnl_izquierdoCentralGNLayout = new javax.swing.GroupLayout(pnl_izquierdoCentralGN);
        pnl_izquierdoCentralGN.setLayout(pnl_izquierdoCentralGNLayout);
        pnl_izquierdoCentralGNLayout.setHorizontalGroup(
            pnl_izquierdoCentralGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnl_izquierdoCentralGNLayout.setVerticalGroup(
            pnl_izquierdoCentralGNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
        );

        pnl_centralGN.add(pnl_izquierdoCentralGN, java.awt.BorderLayout.LINE_START);

        pnl_encabezadoCentralGN.setPreferredSize(new java.awt.Dimension(899, 50));

        lbl_buscarPorNivelesGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarPorNivelesGN.setText("Buscar por nombre:");
        pnl_encabezadoCentralGN.add(lbl_buscarPorNivelesGN);

        txt_buscarPorNombreGN.setColumns(10);
        txt_buscarPorNombreGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarPorNombreGN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarPorNombreGNKeyReleased(evt);
            }
        });
        pnl_encabezadoCentralGN.add(txt_buscarPorNombreGN);

        pnl_centralGN.add(pnl_encabezadoCentralGN, java.awt.BorderLayout.PAGE_START);

        tb_listadoNivelesGN.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoNivelesGN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_listadoNivelesGN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_listadoNivelesGNMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_listadoNivelesGN);

        pnl_centralGN.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGN, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editarGNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGNActionPerformed
        if (tb_listadoNivelesGN.getSelectedRow() > -1 && tb_listadoNivelesGN.getSelectedRowCount() == 1) {
            Integer seleccionado = (Integer) tb_listadoNivelesGN.getValueAt(tb_listadoNivelesGN.getSelectedRow(), 0);
            NivelDeUsuario nivelSeleccionado = this.cp.getControladoraLogueo().buscarNivelPorCodigo(seleccionado);
            
            if (nivelSeleccionado != null) {
                txt_codigoGN.setText(String.valueOf(nivelSeleccionado.getCodigo()));
                txt_nombreGN.setText(nivelSeleccionado.getNombre());
                
                chb_clienteGN.setSelected(nivelSeleccionado.getPermiso().isGestionCliente());
                chb_compraGN.setSelected(nivelSeleccionado.getPermiso().isGestionCompra());
                chb_informe.setSelected(nivelSeleccionado.getPermiso().isGestionInforme());
                chb_parametroGN.setSelected(nivelSeleccionado.getPermiso().isGestionParametro());
                chb_productoGN.setSelected(nivelSeleccionado.getPermiso().isGestionProducto());
                chb_servicioTecnicoGN.setSelected(nivelSeleccionado.getPermiso().isGestionServicioTecnico());
                chb_usuarioGN.setSelected(nivelSeleccionado.getPermiso().isGestionUsuario());
                chb_ventaGN.setSelected(nivelSeleccionado.getPermiso().isGestionVenta());
                chb_servicioGN.setSelected(nivelSeleccionado.getPermiso().isGestionServicio());
                chb_equipoGN.setSelected(nivelSeleccionado.getPermiso().isGestionEquipo());
                this.cambiarEstado();
            }
            
            
            tb_listadoNivelesGN.clearSelection();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un nivel", "Gestion de Niveles", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_editarGNActionPerformed

    private void btn_nuevoGNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGNActionPerformed
        this.cambiarEstado();
        tb_listadoNivelesGN.clearSelection();

    }//GEN-LAST:event_btn_nuevoGNActionPerformed

    private void btn_cancelarGNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGNActionPerformed
        this.estadoInicial(this.usuario.getNivel().getPermiso());
    }//GEN-LAST:event_btn_cancelarGNActionPerformed

    private void btn_guardarGNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGNActionPerformed
        boolean GestionParametro = chb_parametroGN.isSelected();
        boolean GestionCompra = chb_compraGN.isSelected();
        boolean GestionVenta = chb_ventaGN.isSelected();
        boolean GestionServicioTecnico = chb_servicioTecnicoGN.isSelected();
        boolean GestionProducto = chb_productoGN.isSelected();
        boolean GestionCliente = chb_clienteGN.isSelected();
        boolean GestionUsuario = chb_usuarioGN.isSelected();
        boolean GestionInforme = chb_informe.isSelected();
        boolean GesionEquipo = chb_equipoGN.isSelected();
        boolean GestionServicio = chb_servicioGN.isSelected();
        int permisosSeleccionados = 0;
        
        try {
            
            if (chb_clienteGN.isSelected()) {
                permisosSeleccionados++;
            }
            if (chb_compraGN.isSelected()) {
                permisosSeleccionados++;
            }
            
            if (chb_equipoGN.isSelected()) {
                permisosSeleccionados++;
            }
            
            if (chb_informe.isSelected()) {
                permisosSeleccionados++;
            }
            if (chb_parametroGN.isSelected()) {
                permisosSeleccionados++;
            }
            if (chb_productoGN.isSelected()) {
                permisosSeleccionados++;
            }
            if (chb_servicioTecnicoGN.isSelected()) {
                permisosSeleccionados++;
            }
            if (chb_usuarioGN.isSelected()) {
                permisosSeleccionados++;
            }
            if (chb_ventaGN.isSelected()) {
                permisosSeleccionados++;
            }
            
            if (chb_servicioGN.isSelected()) {
                permisosSeleccionados++;
            }
            
            if (!txt_nombreGN.getText().trim().isEmpty() && permisosSeleccionados >= 1) {
                
                String NombreNivel = txt_nombreGN.getText().trim();
                
                if (txt_codigoGN.getText().trim().isEmpty()) {
                    Permiso permiso = new Permiso(GestionCompra, GestionVenta, GestionCliente, GesionEquipo, GestionServicioTecnico, GestionServicio, GestionInforme, GestionUsuario, GestionProducto, GestionParametro);
                    permiso = this.cp.getControladoraLogueo().registarPermiso(permiso);
                    NivelDeUsuario niveldeUsuario = new NivelDeUsuario(NombreNivel, permiso);
                    this.cp.getControladoraLogueo().registrarNivel(niveldeUsuario);
                    JOptionPane.showMessageDialog(null, "Creado con Exito", "Gestion de Niveles", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Integer codigo = Integer.valueOf(txt_codigoGN.getText().trim());
                    NivelDeUsuario niveldeUsuario = this.cp.getControladoraLogueo().buscarNivelPorCodigo(codigo);
                    Permiso permiso = niveldeUsuario.getPermiso();
                    permiso.setGestionCliente(GestionCliente);
                    permiso.setGestionCompra(GestionCompra);
                    permiso.setGestionInforme(GestionInforme);
                    permiso.setGestionEquipo(GesionEquipo);
                    permiso.setGestionParametro(GestionParametro);
                    permiso.setGestionProducto(GestionProducto);
                    permiso.setGestionServicioTecnico(GestionServicioTecnico);
                    permiso.setGestionUsuario(GestionUsuario);
                    permiso.setGestionVenta(GestionVenta);
                    
                    niveldeUsuario.setNombre(NombreNivel);
                    
                    this.cp.getControladoraLogueo().modificarPermiso(permiso);
                    this.cp.getControladoraLogueo().modificarNivel(niveldeUsuario);
                    JOptionPane.showMessageDialog(null, "Editado con Exito", "Gestion de Niveles", JOptionPane.INFORMATION_MESSAGE);
                }
                this.usuario = this.cp.getControladoraLogueo().buscarXnombreUsuario(this.usuario.getNombreUsuario());
                
                this.estadoInicial(this.usuario.getNivel().getPermiso());
            } else {
                if (permisosSeleccionados < 1) {
                    throw new Exception("Seleccione al menos un permiso para este nivel");
                } else if (txt_nombreGN.getText().trim().isEmpty()) {
                    throw new Exception("Todos los campos son obligatorios");
                }
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Niveles", JOptionPane.WARNING_MESSAGE);
        }
        

    }//GEN-LAST:event_btn_guardarGNActionPerformed

    private void btn_salirGNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGNActionPerformed
        this.dispose();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(cp, usuario);
    }//GEN-LAST:event_btn_salirGNActionPerformed

    private void tb_listadoNivelesGNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listadoNivelesGNMouseClicked

    }//GEN-LAST:event_tb_listadoNivelesGNMouseClicked

    private void txt_buscarPorNombreGNKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarPorNombreGNKeyReleased
        if (!txt_buscarPorNombreGN.getText().trim().isEmpty()) {
            String nombre = txt_buscarPorNombreGN.getText().trim();
            cargarTabla(this.cp.getControladoraLogueo().filtrarXnombreNivel(nombre));
            
        } else {
            cargarTabla(this.cp.getControladoraLogueo().listarNiveles());
        }
        

    }//GEN-LAST:event_txt_buscarPorNombreGNKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarGN;
    private javax.swing.JButton btn_editarGN;
    private javax.swing.JButton btn_guardarGN;
    private javax.swing.JButton btn_nuevoGN;
    private javax.swing.JButton btn_salirGN;
    private javax.swing.JCheckBox chb_clienteGN;
    private javax.swing.JCheckBox chb_compraGN;
    private javax.swing.JCheckBox chb_equipoGN;
    private javax.swing.JCheckBox chb_informe;
    private javax.swing.JCheckBox chb_parametroGN;
    private javax.swing.JCheckBox chb_productoGN;
    private javax.swing.JCheckBox chb_servicioGN;
    private javax.swing.JCheckBox chb_servicioTecnicoGN;
    private javax.swing.JCheckBox chb_usuarioGN;
    private javax.swing.JCheckBox chb_ventaGN;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_buscarPorNivelesGN;
    private javax.swing.JLabel lbl_codigoGN;
    private javax.swing.JLabel lbl_nombreGN;
    private javax.swing.JLabel lbl_titutloGN;
    private javax.swing.JPanel pnl_centralGN;
    private javax.swing.JPanel pnl_derechoCentralGN;
    private javax.swing.JPanel pnl_derechoGN;
    private javax.swing.JPanel pnl_encabezadoCentralGN;
    private javax.swing.JPanel pnl_encabezadoGN;
    private javax.swing.JPanel pnl_formularioGN;
    private javax.swing.JPanel pnl_izquierdoCentralGN;
    private javax.swing.JPanel pnl_izquierdoGN;
    private javax.swing.JPanel pnl_pieGN;
    private javax.swing.JTable tb_listadoNivelesGN;
    private javax.swing.JTextField txt_buscarPorNombreGN;
    private javax.swing.JTextField txt_codigoGN;
    private javax.swing.JTextField txt_nombreGN;
    // End of variables declaration//GEN-END:variables
}
