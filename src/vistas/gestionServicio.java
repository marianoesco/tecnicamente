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
import modelo.Servicio;
import modelo.Usuario;

/**
 *
 * @author EcobarM
 */
public class gestionServicio extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private JDesktopPane dskp_principalVP;
    private Usuario usuario;

    /**
     * Creates new form gestionServicio
     */
    public gestionServicio(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario=usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();
    }

    public void estadoInicial() {

        txt_codigoGS.setEnabled(!isEnabled());
        txt_nombreGS.setEnabled(!isEnabled());
        txtA_descripcionGS.setEnabled(!isEnabled());
        txt_precioGS.setEnabled(!isEnabled());
        txt_buscarServicioPorNombreGS.setEnabled(isEnabled());
        
        
        tb_listadoServicioGS.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
        tb_listadoServicioGS.setEnabled(isEnabled());

        txt_codigoGS.setText("");
        txtA_descripcionGS.setText("");
        txt_nombreGS.setText("");
        txt_precioGS.setText("");
        
        btn_cancelarGS.setEnabled(!isEnabled());
        btn_guardarGS.setEnabled(!isEnabled());
        btn_nuevoGS.setEnabled(isEnabled());
        btn_editarGS.setEnabled(isEnabled());
        btn_salirGS.setEnabled(isEnabled());

        cargarTabla(this.cp.getControladoraServicioTecnico().listarServicios());

    }

    public void cambiarEstado() {

        txt_buscarServicioPorNombreGS.setEnabled(!isEnabled());
        
        tb_listadoServicioGS.setEnabled(!isEnabled());
        btn_cancelarGS.setEnabled(isEnabled());
        btn_guardarGS.setEnabled(isEnabled());
        btn_nuevoGS.setEnabled(!isEnabled());
        btn_editarGS.setEnabled(!isEnabled());

        txt_nombreGS.setEnabled(isEnabled());
        txtA_descripcionGS.setEnabled(isEnabled());
        txt_precioGS.setEnabled(isEnabled());

    }

    public void cargarTabla(List<Servicio> servicios) {
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
            for (Servicio s : servicios) {
                fila[0] = s.getCodigo();
                fila[1] = s.getNombre();
                fila[2] = s.getDescripcion();
                fila[3] = s.getPrecio();
                tbCarga.addRow(fila);
            }
            
        }
        tb_listadoServicioGS.setModel(tbCarga);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGS = new javax.swing.JPanel();
        lbl_tituloGS = new javax.swing.JLabel();
        pnl_derechoGS = new javax.swing.JPanel();
        pnl_pieGS = new javax.swing.JPanel();
        btn_nuevoGS = new javax.swing.JButton();
        btn_editarGS = new javax.swing.JButton();
        btn_cancelarGS = new javax.swing.JButton();
        btn_guardarGS = new javax.swing.JButton();
        btn_salirGS = new javax.swing.JButton();
        pnl_izquierdoGS = new javax.swing.JPanel();
        pnl_centralGS = new javax.swing.JPanel();
        pnl_encabezadoCentralGS = new javax.swing.JPanel();
        lbl_buscarServicioPorNombreGS = new javax.swing.JLabel();
        txt_buscarServicioPorNombreGS = new javax.swing.JTextField();
        pnl_izquierdoCentralGS = new javax.swing.JPanel();
        pnl_derechoCentralGS = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoServicioGS = new javax.swing.JTable();
        pnl_formularioGS = new javax.swing.JPanel();
        lbl_codigoGS = new javax.swing.JLabel();
        txt_codigoGS = new javax.swing.JTextField();
        lbl_nombreGS = new javax.swing.JLabel();
        txt_nombreGS = new javax.swing.JTextField();
        lbl_descripcionGS = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_descripcionGS = new javax.swing.JTextArea();
        lbl_precioGS = new javax.swing.JLabel();
        txt_precioGS = new javax.swing.JTextField();

        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestion Servicios");

        pnl_encabezadoGS.setPreferredSize(new java.awt.Dimension(50, 50));
        pnl_encabezadoGS.setLayout(new java.awt.GridLayout(1, 0));

        lbl_tituloGS.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGS.setText("Gestion de Servicios");
        pnl_encabezadoGS.add(lbl_tituloGS);

        getContentPane().add(pnl_encabezadoGS, java.awt.BorderLayout.NORTH);

        pnl_derechoGS.setPreferredSize(new java.awt.Dimension(50, 254));

        javax.swing.GroupLayout pnl_derechoGSLayout = new javax.swing.GroupLayout(pnl_derechoGS);
        pnl_derechoGS.setLayout(pnl_derechoGSLayout);
        pnl_derechoGSLayout.setHorizontalGroup(
            pnl_derechoGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGSLayout.setVerticalGroup(
            pnl_derechoGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGS, java.awt.BorderLayout.EAST);

        pnl_pieGS.setPreferredSize(new java.awt.Dimension(661, 50));
        pnl_pieGS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGS.setText("Nuevo");
        btn_nuevoGS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGSActionPerformed(evt);
            }
        });
        pnl_pieGS.add(btn_nuevoGS);

        btn_editarGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_editarGS.setText("Editar");
        btn_editarGS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGSActionPerformed(evt);
            }
        });
        pnl_pieGS.add(btn_editarGS);

        btn_cancelarGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGS.setText("Cancelar");
        btn_cancelarGS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGSActionPerformed(evt);
            }
        });
        pnl_pieGS.add(btn_cancelarGS);

        btn_guardarGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGS.setText("Guardar");
        btn_guardarGS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGSActionPerformed(evt);
            }
        });
        pnl_pieGS.add(btn_guardarGS);

        btn_salirGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGS.setText("Salir");
        btn_salirGS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGSActionPerformed(evt);
            }
        });
        pnl_pieGS.add(btn_salirGS);

        getContentPane().add(pnl_pieGS, java.awt.BorderLayout.SOUTH);

        pnl_izquierdoGS.setPreferredSize(new java.awt.Dimension(50, 254));

        javax.swing.GroupLayout pnl_izquierdoGSLayout = new javax.swing.GroupLayout(pnl_izquierdoGS);
        pnl_izquierdoGS.setLayout(pnl_izquierdoGSLayout);
        pnl_izquierdoGSLayout.setHorizontalGroup(
            pnl_izquierdoGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGSLayout.setVerticalGroup(
            pnl_izquierdoGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 486, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGS, java.awt.BorderLayout.WEST);

        pnl_centralGS.setLayout(new java.awt.BorderLayout());

        pnl_encabezadoCentralGS.setPreferredSize(new java.awt.Dimension(637, 50));
        pnl_encabezadoCentralGS.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        lbl_buscarServicioPorNombreGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarServicioPorNombreGS.setText("Buscar Servicio Por Nombre: ");
        pnl_encabezadoCentralGS.add(lbl_buscarServicioPorNombreGS);

        txt_buscarServicioPorNombreGS.setColumns(12);
        txt_buscarServicioPorNombreGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarServicioPorNombreGS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarServicioPorNombreGSKeyReleased(evt);
            }
        });
        pnl_encabezadoCentralGS.add(txt_buscarServicioPorNombreGS);

        pnl_centralGS.add(pnl_encabezadoCentralGS, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout pnl_izquierdoCentralGSLayout = new javax.swing.GroupLayout(pnl_izquierdoCentralGS);
        pnl_izquierdoCentralGS.setLayout(pnl_izquierdoCentralGSLayout);
        pnl_izquierdoCentralGSLayout.setHorizontalGroup(
            pnl_izquierdoCentralGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnl_izquierdoCentralGSLayout.setVerticalGroup(
            pnl_izquierdoCentralGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        pnl_centralGS.add(pnl_izquierdoCentralGS, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout pnl_derechoCentralGSLayout = new javax.swing.GroupLayout(pnl_derechoCentralGS);
        pnl_derechoCentralGS.setLayout(pnl_derechoCentralGSLayout);
        pnl_derechoCentralGSLayout.setHorizontalGroup(
            pnl_derechoCentralGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnl_derechoCentralGSLayout.setVerticalGroup(
            pnl_derechoCentralGSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );

        pnl_centralGS.add(pnl_derechoCentralGS, java.awt.BorderLayout.EAST);

        tb_listadoServicioGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoServicioGS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_listadoServicioGS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_listadoServicioGSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_listadoServicioGS);

        pnl_centralGS.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnl_formularioGS.setPreferredSize(new java.awt.Dimension(637, 100));

        lbl_codigoGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_codigoGS.setText("Codigo: ");
        pnl_formularioGS.add(lbl_codigoGS);

        txt_codigoGS.setColumns(5);
        txt_codigoGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_codigoGS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pnl_formularioGS.add(txt_codigoGS);

        lbl_nombreGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_nombreGS.setText("Nombre: ");
        pnl_formularioGS.add(lbl_nombreGS);

        txt_nombreGS.setColumns(15);
        txt_nombreGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_nombreGS.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        pnl_formularioGS.add(txt_nombreGS);

        lbl_descripcionGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_descripcionGS.setText("Descripcion: ");
        pnl_formularioGS.add(lbl_descripcionGS);

        jScrollPane2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 50));

        txtA_descripcionGS.setColumns(25);
        txtA_descripcionGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtA_descripcionGS.setLineWrap(true);
        txtA_descripcionGS.setRows(3);
        jScrollPane2.setViewportView(txtA_descripcionGS);

        pnl_formularioGS.add(jScrollPane2);

        lbl_precioGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_precioGS.setText("Precio: ");
        pnl_formularioGS.add(lbl_precioGS);

        txt_precioGS.setColumns(5);
        txt_precioGS.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_precioGS.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pnl_formularioGS.add(txt_precioGS);

        pnl_centralGS.add(pnl_formularioGS, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnl_centralGS, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoGSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGSActionPerformed
        this.cambiarEstado();
        tb_listadoServicioGS.clearSelection();
    }//GEN-LAST:event_btn_nuevoGSActionPerformed

    private void btn_cancelarGSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGSActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGSActionPerformed

    private void btn_editarGSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGSActionPerformed
        if (tb_listadoServicioGS.getSelectedRow() > -1 && tb_listadoServicioGS.getSelectedRowCount() == 1) {

            Integer seleccionado = (Integer) tb_listadoServicioGS.getValueAt(tb_listadoServicioGS.getSelectedRow(), 0);
            Servicio servicioSeleccionado = this.cp.getControladoraServicioTecnico().buscarServicioPorCodigo(seleccionado);

            if (servicioSeleccionado != null) {
                txt_codigoGS.setText(String.valueOf(servicioSeleccionado.getCodigo()));
                txt_nombreGS.setText(servicioSeleccionado.getNombre());
                txtA_descripcionGS.setText(servicioSeleccionado.getDescripcion());
                txt_precioGS.setText(String.valueOf(servicioSeleccionado.getPrecio()));

            }

            this.cambiarEstado();
            tb_listadoServicioGS.clearSelection();
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un servicio", "Gestion de Servicios", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_editarGSActionPerformed

    private void tb_listadoServicioGSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listadoServicioGSMouseClicked


    }//GEN-LAST:event_tb_listadoServicioGSMouseClicked

    private void btn_guardarGSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGSActionPerformed

        try {
            if (!txt_nombreGS.getText().trim().isEmpty() && !txtA_descripcionGS.getText().trim().isEmpty() && !txt_precioGS.getText().trim().isEmpty()) {
                String nombre = txt_nombreGS.getText().trim();
                String descripcion = txtA_descripcionGS.getText().trim();
                Float precio = Float.valueOf(txt_precioGS.getText().trim());

                if (txt_codigoGS.getText().trim().isEmpty()) { 
                    Servicio servicio = new Servicio(nombre, descripcion, precio);
                    this.cp.getControladoraServicioTecnico().registrarServicio(servicio);

                    JOptionPane.showMessageDialog(null, "Creado con Exito", "Gestion de Servicios", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    Integer codigo = Integer.valueOf(txt_codigoGS.getText().trim());
                    Servicio servicio = this.cp.getControladoraServicioTecnico().buscarServicioPorCodigo(codigo);
                    servicio.setNombre(nombre);
                    servicio.setDescripcion(descripcion);
                    servicio.setPrecio(precio);
                    this.cp.getControladoraServicioTecnico().modificarServicio(servicio);
                    JOptionPane.showMessageDialog(null, "Editado con Exito", "Gestion de Servicios", JOptionPane.INFORMATION_MESSAGE);

                }

                this.estadoInicial();

            } else {
                throw new Exception("Todos los campos son obligatorios");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Servicios", JOptionPane.WARNING_MESSAGE);

        }


    }//GEN-LAST:event_btn_guardarGSActionPerformed

    private void btn_salirGSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGSActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGSActionPerformed

    private void txt_buscarServicioPorNombreGSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarServicioPorNombreGSKeyReleased
        if (!txt_buscarServicioPorNombreGS.getText().trim().isEmpty()) {
            String nombre = txt_buscarServicioPorNombreGS.getText().trim();
            cargarTabla(this.cp.getControladoraServicioTecnico().filtrarServicioPorNombre(nombre));
        } else {
            cargarTabla(this.cp.getControladoraServicioTecnico().listarServicios());
        }
    }//GEN-LAST:event_txt_buscarServicioPorNombreGSKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarGS;
    private javax.swing.JButton btn_editarGS;
    private javax.swing.JButton btn_guardarGS;
    private javax.swing.JButton btn_nuevoGS;
    private javax.swing.JButton btn_salirGS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_buscarServicioPorNombreGS;
    private javax.swing.JLabel lbl_codigoGS;
    private javax.swing.JLabel lbl_descripcionGS;
    private javax.swing.JLabel lbl_nombreGS;
    private javax.swing.JLabel lbl_precioGS;
    private javax.swing.JLabel lbl_tituloGS;
    private javax.swing.JPanel pnl_centralGS;
    private javax.swing.JPanel pnl_derechoCentralGS;
    private javax.swing.JPanel pnl_derechoGS;
    private javax.swing.JPanel pnl_encabezadoCentralGS;
    private javax.swing.JPanel pnl_encabezadoGS;
    private javax.swing.JPanel pnl_formularioGS;
    private javax.swing.JPanel pnl_izquierdoCentralGS;
    private javax.swing.JPanel pnl_izquierdoGS;
    private javax.swing.JPanel pnl_pieGS;
    private javax.swing.JTable tb_listadoServicioGS;
    private javax.swing.JTextArea txtA_descripcionGS;
    private javax.swing.JTextField txt_buscarServicioPorNombreGS;
    private javax.swing.JTextField txt_codigoGS;
    private javax.swing.JTextField txt_nombreGS;
    private javax.swing.JTextField txt_precioGS;
    // End of variables declaration//GEN-END:variables
}
