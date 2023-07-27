/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import modelo.Cliente;
import modelo.Localidad;
import modelo.Provincia;
import modelo.Usuario;
import modelo.Utilidades;
import vistas.gestionVenta;
import vistas.gestionServicioTecnico;

/**
 *
 * @author EcobarM
 */
public class gestionClientes extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private Usuario usuario;
    private JDesktopPane dskp_principalVP;

    /**
     * Creates new form gestionUsuarios
     */
    public gestionClientes(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;

        this.usuario = usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();
    }

    public void estadoInicial() {
        btn_nuevoGC.setEnabled(isEnabled());
        btn_editarGC.setEnabled(isEnabled());
        btn_cancelarGC.setEnabled(!isEnabled());
        btn_guardarGC.setEnabled(!isEnabled());
        btn_salirGC.setEnabled(isEnabled());

        txt_apellidoGC.setEnabled(!isEnabled());
        txt_codigoGC.setEnabled(!isEnabled());
        txt_dniGC.setEnabled(!isEnabled());
        txt_correoGC.setEnabled(!isEnabled());
        txtA_direccionGC.setEnabled(!isEnabled());
        txt_nombreGC.setEnabled(!isEnabled());
        txt_telefonoGC.setEnabled(!isEnabled());
        
        cmb_provinciasGC.setEnabled(!isEnabled());
        cmb_localidadesGC.setEnabled(!isEnabled());

        txt_buscarClientePorDniGC.setEnabled(isEnabled());
        txt_buscarClientePorDniGC.setText("");
        
        
        txt_apellidoGC.setDisabledTextColor(Color.BLACK);
        txt_codigoGC.setDisabledTextColor(Color.BLACK);
        txt_dniGC.setDisabledTextColor(Color.BLACK);
        txt_correoGC.setDisabledTextColor(Color.BLACK);
        txtA_direccionGC.setDisabledTextColor(Color.BLACK);
        txt_nombreGC.setDisabledTextColor(Color.BLACK);
        txt_telefonoGC.setDisabledTextColor(Color.BLACK);

        txt_apellidoGC.setText("");
        txt_codigoGC.setText("");
        txt_correoGC.setText("");
        txtA_direccionGC.setText("");
        txt_dniGC.setText("");
        txt_nombreGC.setText("");
        txt_telefonoGC.setText("");

        Utilidades.cargarCombo(this.cp.getControladoraCliente().listarProvincias(), cmb_provinciasGC);

        if (cmb_provinciasGC.getSelectedItem() == "[SELECCIONAR]") {
            cmb_localidadesGC.removeAllItems();
            cmb_localidadesGC.addItem("[SELECCIONAR]");
        }

        tb_listadoClientesGC.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));

        tb_listadoClientesGC.setEnabled(isEnabled());
        
        UIManager.put("ComboBox.disabledBackground", new Color(212, 212, 210));
        UIManager.put("ComboBox.disabledForeground", Color.BLACK);

        cargarTabla(this.cp.getControladoraCliente().listarClientes());
    }

    public void cargarTabla(List<Cliente> clientes) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!clientes.isEmpty()) {
            if (!this.usuario.getNivel().getNombre().equals("ADMINISTRADOR")) {
                String Cabecera[] = {"Codigo", "Dni", "Apellido", "Nombre", "Provincia", "Localidad", "Direccion", "Correo", "Telefono"};
                tbCarga.setColumnIdentifiers(Cabecera);
            } else {
                String cabecera[] = {"Codigo", "Dni", "Apellido", "Nombre", "Provincia", "Localidad", "Direccion", "Correo", "Telefono", "RegistradoPor", "UltimaModicacion"};
                tbCarga.setColumnIdentifiers(cabecera);
            }

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

                if (this.usuario.getNivel().getNombre().equals("ADMINISTRADOR")) {

                    Fila[9] = c.getRegistradoPor();
                    Fila[10] = c.getUltimaModificacionPor();
                }

                tbCarga.addRow(Fila);
            }

        }
        tb_listadoClientesGC.setModel(tbCarga);
    }

    public void cambiarEstado() {

        Utilidades.cargarCombo(this.cp.getControladoraCliente().listarProvincias(), cmb_provinciasGC);
        
        btn_nuevoGC.setEnabled(!isEnabled());
        btn_editarGC.setEnabled(!isEnabled());
        btn_cancelarGC.setEnabled(isEnabled());
        btn_guardarGC.setEnabled(isEnabled());

        txt_apellidoGC.setEnabled(isEnabled());
        txt_correoGC.setEnabled(isEnabled());
        txtA_direccionGC.setEnabled(isEnabled());
        txt_nombreGC.setEnabled(isEnabled());
        txt_telefonoGC.setEnabled(isEnabled());
        txt_dniGC.setEnabled(isEnabled());
        
        tb_listadoClientesGC.setEnabled(!isEnabled());
        
        txt_buscarClientePorDniGC.setText("");

        cmb_provinciasGC.setEnabled(isEnabled());

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
        btn_editarGC = new javax.swing.JButton();
        btn_cancelarGC = new javax.swing.JButton();
        btn_guardarGC = new javax.swing.JButton();
        btn_salirGC = new javax.swing.JButton();
        pnl_izquierdoGC = new javax.swing.JPanel();
        pnl_centralGC = new javax.swing.JPanel();
        pnl_encabezadoCentralGC = new javax.swing.JPanel();
        lbl_buscarPorDniGC = new javax.swing.JLabel();
        txt_buscarClientePorDniGC = new javax.swing.JTextField();
        pnl_listadoCentralGC = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoClientesGC = new javax.swing.JTable();
        pnl_formularioCentralGC = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl_codigoGC = new javax.swing.JLabel();
        txt_codigoGC = new javax.swing.JTextField();
        lbl_dniGC = new javax.swing.JLabel();
        txt_dniGC = new javax.swing.JTextField();
        lbl_apellidoGC = new javax.swing.JLabel();
        txt_apellidoGC = new javax.swing.JTextField();
        lbl_nombreGC = new javax.swing.JLabel();
        txt_nombreGC = new javax.swing.JTextField();
        lbl_telefonoGC = new javax.swing.JLabel();
        txt_telefonoGC = new javax.swing.JTextField();
        lbl_correoGC = new javax.swing.JLabel();
        txt_correoGC = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lbl_provinciaGC = new javax.swing.JLabel();
        cmb_provinciasGC = new javax.swing.JComboBox();
        lbl_localidadGC = new javax.swing.JLabel();
        cmb_localidadesGC = new javax.swing.JComboBox();
        lbl_direccionGC = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_direccionGC = new javax.swing.JTextArea();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Gestion Clientes");

        pnl_encabezadoGC.setPreferredSize(new java.awt.Dimension(651, 50));
        pnl_encabezadoGC.setLayout(new java.awt.BorderLayout());

        lbl_tituloGC.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGC.setText("Gestion de Clientes");
        pnl_encabezadoGC.add(lbl_tituloGC, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_encabezadoGC, java.awt.BorderLayout.NORTH);

        pnl_derechoGC.setPreferredSize(new java.awt.Dimension(30, 183));

        javax.swing.GroupLayout pnl_derechoGCLayout = new javax.swing.GroupLayout(pnl_derechoGC);
        pnl_derechoGC.setLayout(pnl_derechoGCLayout);
        pnl_derechoGCLayout.setHorizontalGroup(
            pnl_derechoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        pnl_derechoGCLayout.setVerticalGroup(
            pnl_derechoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGC, java.awt.BorderLayout.EAST);

        pnl_pieGC.setPreferredSize(new java.awt.Dimension(50, 50));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15);
        flowLayout2.setAlignOnBaseline(true);
        pnl_pieGC.setLayout(flowLayout2);

        btn_nuevoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGC.setText("Nuevo");
        btn_nuevoGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_nuevoGC);

        btn_editarGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_editarGC.setText("Editar");
        btn_editarGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_editarGC);

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

        btn_salirGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGC.setText("Salir");
        btn_salirGC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGCActionPerformed(evt);
            }
        });
        pnl_pieGC.add(btn_salirGC);

        getContentPane().add(pnl_pieGC, java.awt.BorderLayout.SOUTH);

        pnl_izquierdoGC.setPreferredSize(new java.awt.Dimension(30, 183));

        javax.swing.GroupLayout pnl_izquierdoGCLayout = new javax.swing.GroupLayout(pnl_izquierdoGC);
        pnl_izquierdoGC.setLayout(pnl_izquierdoGCLayout);
        pnl_izquierdoGCLayout.setHorizontalGroup(
            pnl_izquierdoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        pnl_izquierdoGCLayout.setVerticalGroup(
            pnl_izquierdoGCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGC, java.awt.BorderLayout.WEST);

        pnl_centralGC.setLayout(new java.awt.BorderLayout());

        pnl_encabezadoCentralGC.setMinimumSize(new java.awt.Dimension(50, 50));
        pnl_encabezadoCentralGC.setPreferredSize(new java.awt.Dimension(755, 50));

        lbl_buscarPorDniGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarPorDniGC.setText("Buscar Por Dni: ");
        pnl_encabezadoCentralGC.add(lbl_buscarPorDniGC);

        txt_buscarClientePorDniGC.setColumns(6);
        txt_buscarClientePorDniGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarClientePorDniGC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_buscarClientePorDniGC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarClientePorDniGCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_buscarClientePorDniGCKeyTyped(evt);
            }
        });
        pnl_encabezadoCentralGC.add(txt_buscarClientePorDniGC);

        pnl_centralGC.add(pnl_encabezadoCentralGC, java.awt.BorderLayout.NORTH);

        pnl_listadoCentralGC.setLayout(new java.awt.BorderLayout());

        tb_listadoClientesGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoClientesGC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_listadoClientesGC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_listadoClientesGCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_listadoClientesGC);

        pnl_listadoCentralGC.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnl_formularioCentralGC.setPreferredSize(new java.awt.Dimension(755, 150));
        pnl_formularioCentralGC.setLayout(new java.awt.GridLayout(2, 0));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        lbl_codigoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_codigoGC.setText("Codigo:");
        jPanel1.add(lbl_codigoGC);

        txt_codigoGC.setColumns(3);
        txt_codigoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_codigoGC);

        lbl_dniGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_dniGC.setText("Dni:");
        jPanel1.add(lbl_dniGC);

        txt_dniGC.setColumns(5);
        txt_dniGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_dniGC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_dniGC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dniGCKeyTyped(evt);
            }
        });
        jPanel1.add(txt_dniGC);

        lbl_apellidoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_apellidoGC.setText("Apellido:");
        jPanel1.add(lbl_apellidoGC);

        txt_apellidoGC.setColumns(10);
        txt_apellidoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_apellidoGC);

        lbl_nombreGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_nombreGC.setText("Nombre: ");
        jPanel1.add(lbl_nombreGC);

        txt_nombreGC.setColumns(10);
        txt_nombreGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_nombreGC);

        lbl_telefonoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_telefonoGC.setText("Telefono:");
        jPanel1.add(lbl_telefonoGC);

        txt_telefonoGC.setColumns(10);
        txt_telefonoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_telefonoGC.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_telefonoGC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefonoGCKeyTyped(evt);
            }
        });
        jPanel1.add(txt_telefonoGC);

        lbl_correoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_correoGC.setText("Correo: ");
        jPanel1.add(lbl_correoGC);

        txt_correoGC.setColumns(20);
        txt_correoGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jPanel1.add(txt_correoGC);

        pnl_formularioCentralGC.add(jPanel1);

        lbl_provinciaGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_provinciaGC.setText("Provincia: ");
        jPanel2.add(lbl_provinciaGC);

        cmb_provinciasGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_provinciasGC.setPreferredSize(new java.awt.Dimension(130, 25));
        cmb_provinciasGC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_provinciasGCItemStateChanged(evt);
            }
        });
        jPanel2.add(cmb_provinciasGC);

        lbl_localidadGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_localidadGC.setText("Localidad: ");
        jPanel2.add(lbl_localidadGC);

        cmb_localidadesGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cmb_localidadesGC.setPreferredSize(new java.awt.Dimension(130, 25));
        jPanel2.add(cmb_localidadesGC);

        lbl_direccionGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_direccionGC.setText("Direccion: ");
        jPanel2.add(lbl_direccionGC);

        txtA_direccionGC.setColumns(20);
        txtA_direccionGC.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtA_direccionGC.setLineWrap(true);
        txtA_direccionGC.setRows(1);
        jScrollPane2.setViewportView(txtA_direccionGC);

        jPanel2.add(jScrollPane2);

        pnl_formularioCentralGC.add(jPanel2);

        pnl_listadoCentralGC.add(pnl_formularioCentralGC, java.awt.BorderLayout.SOUTH);

        pnl_centralGC.add(pnl_listadoCentralGC, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGC, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGCActionPerformed
        this.dispose();


    }//GEN-LAST:event_btn_salirGCActionPerformed

    private void btn_nuevoGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGCActionPerformed
        this.cambiarEstado();
        tb_listadoClientesGC.clearSelection();

    }//GEN-LAST:event_btn_nuevoGCActionPerformed

    private void btn_editarGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGCActionPerformed

        if (tb_listadoClientesGC.getSelectedRow() > -1 && tb_listadoClientesGC.getSelectedRowCount() == 1) {
            Integer seleccionado = (Integer) tb_listadoClientesGC.getValueAt(tb_listadoClientesGC.getSelectedRow(), 0);
            Cliente clienteSeleccionado = this.cp.getControladoraCliente().buscarClientePorCodigo(seleccionado);

            if (clienteSeleccionado != null) {
                this.cambiarEstado();

                txt_codigoGC.setText(String.valueOf(clienteSeleccionado.getCodigo()));
                txt_nombreGC.setText(clienteSeleccionado.getNombre());
                txt_dniGC.setText(String.valueOf(clienteSeleccionado.getDni()));
                txt_apellidoGC.setText(clienteSeleccionado.getApellido());
                txt_correoGC.setText(clienteSeleccionado.getCorreo());
                txtA_direccionGC.setText(clienteSeleccionado.getDireccion());
                txt_telefonoGC.setText(clienteSeleccionado.getTelefono());
                cmb_provinciasGC.setSelectedItem(clienteSeleccionado.getProvincia());
                cmb_localidadesGC.setSelectedItem(clienteSeleccionado.getLocalidad());
            }

            tb_listadoClientesGC.clearSelection();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Gestion de Clientes", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_editarGCActionPerformed

    private void btn_cancelarGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGCActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGCActionPerformed

    private void btn_guardarGCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGCActionPerformed
        try {

            if (!txt_nombreGC.getText().trim().isEmpty() && !txt_apellidoGC.getText().trim().isEmpty()
                    && !txt_correoGC.getText().trim().isEmpty() && !txtA_direccionGC.getText().trim().isEmpty()
                    && !txt_dniGC.getText().trim().isEmpty() && !txt_telefonoGC.getText().trim().isEmpty()
                    && cmb_provinciasGC.getSelectedItem() != "[SELECCIONAR]" && cmb_localidadesGC.getSelectedItem() != "[SELECCIONAR]"
                    && cmb_provinciasGC.getSelectedItem() != null && cmb_localidadesGC.getSelectedItem() != null) {

                Integer dni = Integer.valueOf(txt_dniGC.getText().trim());
                String nombre = txt_nombreGC.getText().trim();
                String apellido = txt_apellidoGC.getText().trim();
                String correo = txt_correoGC.getText().trim();
                String direccion = txtA_direccionGC.getText().trim();
                String telefono = txt_telefonoGC.getText().trim();
                Provincia provincia = (Provincia) cmb_provinciasGC.getSelectedItem();
                Localidad localidad = (Localidad) cmb_localidadesGC.getSelectedItem();

                if (Utilidades.ValidadEmail(correo) == false) {
                    throw new Exception("Ingrese un correo valido");
                }

                if (txt_codigoGC.getText().trim().isEmpty()) {
                    String registradoPor = this.usuario.getNombreUsuario();
                    Cliente cliente = new Cliente(dni, direccion, telefono, correo, provincia, localidad, apellido, nombre, registradoPor);

                    this.cp.getControladoraCliente().registrarCliente(cliente);

                    JOptionPane.showMessageDialog(null, "Creado con Exito", "Gestion de Clientes", JOptionPane.INFORMATION_MESSAGE);

                } else {

                    Integer codigo = Integer.valueOf(txt_codigoGC.getText().trim());
                    Cliente cliente = this.cp.getControladoraCliente().buscarClientePorCodigo(codigo);

                    cliente.setApellido(apellido);
                    cliente.setDni(dni);
                    cliente.setNombre(nombre);
                    cliente.setDireccion(direccion);
                    cliente.setProvincia(provincia);
                    cliente.setLocalidad(localidad);
                    cliente.setTelefono(telefono);
                    cliente.setCorreo(correo);
                    cliente.setUltimaModificacionPor(this.usuario.getNombreUsuario());
                    this.cp.getControladoraCliente().modificarCliente(cliente);

                    JOptionPane.showMessageDialog(null, "Editado con Exito", "Gestion de Clientes", JOptionPane.INFORMATION_MESSAGE);

                }
                this.estadoInicial();
            } else {
                throw new Exception("Todos los campos son obligatorios ");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Clientes", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btn_guardarGCActionPerformed

    private void tb_listadoClientesGCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listadoClientesGCMouseClicked

    }//GEN-LAST:event_tb_listadoClientesGCMouseClicked

    private void cmb_provinciasGCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_provinciasGCItemStateChanged
        if (cmb_provinciasGC.getSelectedItem() != "[SELECCIONAR]" && cmb_provinciasGC != null) {
            Provincia provincia = (Provincia) cmb_provinciasGC.getSelectedItem();
            Utilidades.cargarCombo(this.cp.getControladoraCliente().filtrarLocalidadPorProvincia(provincia), cmb_localidadesGC);
            cmb_localidadesGC.setEnabled(isEnabled());
        } else {
            cmb_localidadesGC.removeAllItems();
            cmb_localidadesGC.addItem("[SELECCIONAR]");
            cmb_localidadesGC.setEnabled(!isEnabled());
        }
    }//GEN-LAST:event_cmb_provinciasGCItemStateChanged

    private void txt_buscarClientePorDniGCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarClientePorDniGCKeyReleased
        if (!txt_buscarClientePorDniGC.getText().trim().isEmpty()) {
            Integer dni = Integer.valueOf(txt_buscarClientePorDniGC.getText().trim());
            cargarTabla(this.cp.getControladoraCliente().filtrarClientePorDni(dni));
        } else {
            cargarTabla(this.cp.getControladoraCliente().listarClientes());
        }
    }//GEN-LAST:event_txt_buscarClientePorDniGCKeyReleased

    private void txt_dniGCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniGCKeyTyped
        Utilidades.DelimitarNumerosDni(evt, txt_dniGC);
    }//GEN-LAST:event_txt_dniGCKeyTyped

    private void txt_telefonoGCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefonoGCKeyTyped
        Utilidades.DelimitarNumerosTelefono(evt, txt_telefonoGC);
    }//GEN-LAST:event_txt_telefonoGCKeyTyped

    private void txt_buscarClientePorDniGCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarClientePorDniGCKeyTyped
        Utilidades.DelimitarNumerosDni(evt, txt_buscarClientePorDniGC);
    }//GEN-LAST:event_txt_buscarClientePorDniGCKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarGC;
    private javax.swing.JButton btn_editarGC;
    private javax.swing.JButton btn_guardarGC;
    private javax.swing.JButton btn_nuevoGC;
    private javax.swing.JButton btn_salirGC;
    private javax.swing.JComboBox cmb_localidadesGC;
    private javax.swing.JComboBox cmb_provinciasGC;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_apellidoGC;
    private javax.swing.JLabel lbl_buscarPorDniGC;
    private javax.swing.JLabel lbl_codigoGC;
    private javax.swing.JLabel lbl_correoGC;
    private javax.swing.JLabel lbl_direccionGC;
    private javax.swing.JLabel lbl_dniGC;
    private javax.swing.JLabel lbl_localidadGC;
    private javax.swing.JLabel lbl_nombreGC;
    private javax.swing.JLabel lbl_provinciaGC;
    private javax.swing.JLabel lbl_telefonoGC;
    private javax.swing.JLabel lbl_tituloGC;
    private javax.swing.JPanel pnl_centralGC;
    private javax.swing.JPanel pnl_derechoGC;
    private javax.swing.JPanel pnl_encabezadoCentralGC;
    private javax.swing.JPanel pnl_encabezadoGC;
    private javax.swing.JPanel pnl_formularioCentralGC;
    private javax.swing.JPanel pnl_izquierdoGC;
    private javax.swing.JPanel pnl_listadoCentralGC;
    private javax.swing.JPanel pnl_pieGC;
    private javax.swing.JTable tb_listadoClientesGC;
    private javax.swing.JTextArea txtA_direccionGC;
    private javax.swing.JTextField txt_apellidoGC;
    private javax.swing.JTextField txt_buscarClientePorDniGC;
    private javax.swing.JTextField txt_codigoGC;
    private javax.swing.JTextField txt_correoGC;
    private javax.swing.JTextField txt_dniGC;
    private javax.swing.JTextField txt_nombreGC;
    private javax.swing.JTextField txt_telefonoGC;
    // End of variables declaration//GEN-END:variables
}
