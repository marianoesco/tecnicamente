/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Font;
import java.text.SimpleDateFormat;
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
import modelo.ServicioTecnico;
import modelo.Usuario;
import modelo.Venta;
import reportes.Reporte;

/**
 *
 * @author Usuario
 */
public class gestionInforme extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private Usuario usuario;
    private JDesktopPane dskp_principal;
    
    DefaultTableModel tbCargaInforme = new DefaultTableModel(){

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
      
    };
    
    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * Creates new form gestionInforme
     */
    public gestionInforme(ControladoraPrincipal cp, Usuario usuario, JDesktopPane dskp_principal) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario = usuario;
        this.dskp_principal = dskp_principal;

        this.estadoInicial();
    }

    public void estadoInicial() {
        jdch_fechaDesdeGI.setEnabled(!isEnabled());
        jdch_fechaHastaGI.setEnabled(!isEnabled());

        jdch_fechaDesdeGI.setDate(new Date());
        jdch_fechaHastaGI.setDate(new Date());

        rdb_comprasGI.setEnabled(!isEnabled());
        rdb_servicioTecnicoGI.setEnabled(!isEnabled());
        rdb_ventasGI.setEnabled(!isEnabled());
        
        btngroup_filtrosGI.clearSelection();
        
        this.removerFilasInforme();
        
        tb_tablaInformesGI.setModel(tbCargaInforme);
        
        btn_filtrarGI.setEnabled(!isEnabled());
        btn_nuevoInformeGI.setEnabled(isEnabled());
        btn_cancelarGI.setEnabled(!isEnabled());
        btn_imprimirGI.setEnabled(!isEnabled());
        btn_salirGI.setEnabled(isEnabled());
        tb_tablaInformesGI.setEnabled(!isEnabled());

    }
    
    public void removerFilasInforme() {
        
        DefaultTableModel tbcargaInforme = (DefaultTableModel) tb_tablaInformesGI.getModel();
        for (int i = tb_tablaInformesGI.getRowCount() - 1; i >= 0; i--) {
            tbcargaInforme.removeRow(i);
        }
        
    }

    public void cambiarEstado() {
        jdch_fechaDesdeGI.setEnabled(isEnabled());
        jdch_fechaHastaGI.setEnabled(isEnabled());

        rdb_comprasGI.setEnabled(isEnabled());
        rdb_servicioTecnicoGI.setEnabled(isEnabled());
        rdb_ventasGI.setEnabled(isEnabled());
        btn_filtrarGI.setEnabled(isEnabled());
        btn_nuevoInformeGI.setEnabled(!isEnabled());
        btn_cancelarGI.setEnabled(isEnabled());
        btn_imprimirGI.setEnabled(isEnabled());

        tb_tablaInformesGI.setEnabled(isEnabled());

    }

    public void cargarTablaFiltradoCompras(List<Compra> Compras) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!Compras.isEmpty()) {
            String Cabecera[] = {"Codigo", "Fecha", "Total"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object Fila[] = new Object[tbCarga.getColumnCount()];
            tb_tablaInformesGI.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (Compra c : Compras) {
                Fila[0] = c.getCodigo();
                Fila[1] = formato.format(c.getFecha());
                Fila[2] = c.getPrecio();
                tbCarga.addRow(Fila);
            }

        }
        tb_tablaInformesGI.setModel(tbCarga);

    }

    public void cargarTablaFiltradoVentas(List<Venta> Ventas) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!Ventas.isEmpty()) {
            String Cabecera[] = {"Codigo", "Fecha", "Total"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object Fila[] = new Object[tbCarga.getColumnCount()];
            tb_tablaInformesGI.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (Venta v : Ventas) {
                Fila[0] = v.getCodigo();
                Fila[1] = formato.format(v.getFecha());
                Fila[2] = v.getPrecio();
                tbCarga.addRow(Fila);
            }

        }
        tb_tablaInformesGI.setModel(tbCarga);
    }

    public void cargarTablaFiltradoServicioEgresado(List<ServicioTecnico> ServicioEgresado) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!ServicioEgresado.isEmpty()) {
            String Cabecera[] = {"Codigo", "Fecha", "Total"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object Fila[] = new Object[tbCarga.getColumnCount()];
            tb_tablaInformesGI.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (ServicioTecnico Se : ServicioEgresado) {
                Fila[0] = Se.getCodigo();
                Fila[1] = formato.format(Se.getFechaDeEgreso());
                Fila[2] = Se.getPrecio();
                tbCarga.addRow(Fila);
            }

        }
        tb_tablaInformesGI.setModel(tbCarga);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngroup_filtrosGI = new javax.swing.ButtonGroup();
        pnl_encabezadoGI = new javax.swing.JPanel();
        lbl_tituloGI = new javax.swing.JLabel();
        pnl_pieGI = new javax.swing.JPanel();
        btn_nuevoInformeGI = new javax.swing.JButton();
        btn_cancelarGI = new javax.swing.JButton();
        btn_imprimirGI = new javax.swing.JButton();
        btn_salirGI = new javax.swing.JButton();
        pnl_derechoGI = new javax.swing.JPanel();
        pnl_izquierdoGI = new javax.swing.JPanel();
        pnl_centralGI = new javax.swing.JPanel();
        pnl_encabezadoCentralGI = new javax.swing.JPanel();
        lbl_fechaDesdeGI = new javax.swing.JLabel();
        jdch_fechaDesdeGI = new com.toedter.calendar.JDateChooser();
        lbl_fechaHastaGI = new javax.swing.JLabel();
        jdch_fechaHastaGI = new com.toedter.calendar.JDateChooser();
        rdb_comprasGI = new javax.swing.JRadioButton();
        rdb_ventasGI = new javax.swing.JRadioButton();
        rdb_servicioTecnicoGI = new javax.swing.JRadioButton();
        btn_filtrarGI = new javax.swing.JButton();
        pnl_centroGI = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tablaInformesGI = new javax.swing.JTable();

        setBorder(null);

        pnl_encabezadoGI.setPreferredSize(new java.awt.Dimension(675, 50));

        lbl_tituloGI.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGI.setText("Gestion de Informes");
        pnl_encabezadoGI.add(lbl_tituloGI);

        getContentPane().add(pnl_encabezadoGI, java.awt.BorderLayout.PAGE_START);

        pnl_pieGI.setPreferredSize(new java.awt.Dimension(675, 50));

        btn_nuevoInformeGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoInformeGI.setText("Nuevo ");
        btn_nuevoInformeGI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoInformeGIActionPerformed(evt);
            }
        });
        pnl_pieGI.add(btn_nuevoInformeGI);

        btn_cancelarGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGI.setText("Cancelar");
        btn_cancelarGI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGIActionPerformed(evt);
            }
        });
        pnl_pieGI.add(btn_cancelarGI);

        btn_imprimirGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_imprimirGI.setText("Imprimir");
        btn_imprimirGI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imprimirGIActionPerformed(evt);
            }
        });
        pnl_pieGI.add(btn_imprimirGI);

        btn_salirGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGI.setText("Salir");
        btn_salirGI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGIActionPerformed(evt);
            }
        });
        pnl_pieGI.add(btn_salirGI);

        getContentPane().add(pnl_pieGI, java.awt.BorderLayout.PAGE_END);

        pnl_derechoGI.setPreferredSize(new java.awt.Dimension(50, 264));

        javax.swing.GroupLayout pnl_derechoGILayout = new javax.swing.GroupLayout(pnl_derechoGI);
        pnl_derechoGI.setLayout(pnl_derechoGILayout);
        pnl_derechoGILayout.setHorizontalGroup(
            pnl_derechoGILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGILayout.setVerticalGroup(
            pnl_derechoGILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGI, java.awt.BorderLayout.LINE_END);

        pnl_izquierdoGI.setPreferredSize(new java.awt.Dimension(50, 264));

        javax.swing.GroupLayout pnl_izquierdoGILayout = new javax.swing.GroupLayout(pnl_izquierdoGI);
        pnl_izquierdoGI.setLayout(pnl_izquierdoGILayout);
        pnl_izquierdoGILayout.setHorizontalGroup(
            pnl_izquierdoGILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGILayout.setVerticalGroup(
            pnl_izquierdoGILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGI, java.awt.BorderLayout.LINE_START);

        pnl_centralGI.setLayout(new java.awt.BorderLayout());

        pnl_encabezadoCentralGI.setPreferredSize(new java.awt.Dimension(599, 50));

        lbl_fechaDesdeGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_fechaDesdeGI.setText("Desde");
        pnl_encabezadoCentralGI.add(lbl_fechaDesdeGI);

        jdch_fechaDesdeGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jdch_fechaDesdeGI.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_encabezadoCentralGI.add(jdch_fechaDesdeGI);

        lbl_fechaHastaGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_fechaHastaGI.setText("Hasta");
        pnl_encabezadoCentralGI.add(lbl_fechaHastaGI);

        jdch_fechaHastaGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jdch_fechaHastaGI.setPreferredSize(new java.awt.Dimension(120, 30));
        pnl_encabezadoCentralGI.add(jdch_fechaHastaGI);

        btngroup_filtrosGI.add(rdb_comprasGI);
        rdb_comprasGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        rdb_comprasGI.setText("Compras");
        pnl_encabezadoCentralGI.add(rdb_comprasGI);

        btngroup_filtrosGI.add(rdb_ventasGI);
        rdb_ventasGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        rdb_ventasGI.setText("Ventas");
        pnl_encabezadoCentralGI.add(rdb_ventasGI);

        btngroup_filtrosGI.add(rdb_servicioTecnicoGI);
        rdb_servicioTecnicoGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        rdb_servicioTecnicoGI.setText("Servicio Tecnico");
        pnl_encabezadoCentralGI.add(rdb_servicioTecnicoGI);

        btn_filtrarGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_filtrarGI.setText("Filtrar");
        btn_filtrarGI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filtrarGIActionPerformed(evt);
            }
        });
        pnl_encabezadoCentralGI.add(btn_filtrarGI);

        pnl_centralGI.add(pnl_encabezadoCentralGI, java.awt.BorderLayout.NORTH);

        pnl_centroGI.setLayout(new java.awt.BorderLayout());

        tb_tablaInformesGI.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_tablaInformesGI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_tablaInformesGI);

        pnl_centroGI.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnl_centralGI.add(pnl_centroGI, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGI, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoInformeGIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoInformeGIActionPerformed
        this.cambiarEstado();
    }//GEN-LAST:event_btn_nuevoInformeGIActionPerformed

    private void btn_cancelarGIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGIActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGIActionPerformed

    private void btn_salirGIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGIActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGIActionPerformed

    private void btn_filtrarGIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filtrarGIActionPerformed
        try {

            if (jdch_fechaDesdeGI.getDate().after(new Date())) {
                throw new Exception("No se puede filtrar por una fecha posterior a la actual");
            }

            if (jdch_fechaHastaGI.getDate().before(jdch_fechaDesdeGI.getDate())) {
                throw new Exception("No se puede elegir una fecha anterior a la fecha [desde] para filtrar");
            }

            if (!rdb_comprasGI.isSelected() && !rdb_servicioTecnicoGI.isSelected() && !rdb_ventasGI.isSelected()) {
                throw new Exception("Debe seleccionar una opcion para filtrar");

            }

            String fechaDesde = formato.format(jdch_fechaDesdeGI.getDate());
            String fechaHasta = formato.format(jdch_fechaHastaGI.getDate());

            if (rdb_comprasGI.isSelected()) {

                cargarTablaFiltradoCompras(this.cp.getControladoraCompra().listarComprasPorFechas(fechaDesde, fechaHasta));
            }

            if (rdb_servicioTecnicoGI.isSelected()) {

                cargarTablaFiltradoServicioEgresado(this.cp.getControladoraServicioTecnico().listarServiciosPorFechas(fechaDesde, fechaHasta));

            }

            if (rdb_ventasGI.isSelected()) {

                cargarTablaFiltradoVentas(this.cp.getControladoraVenta().ListarVentasPorFechas(fechaDesde, fechaHasta));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Informes", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btn_filtrarGIActionPerformed

    private void btn_imprimirGIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imprimirGIActionPerformed

        try {

            if (jdch_fechaDesdeGI.getDate().after(new Date())) {
                throw new Exception("No se puede filtrar por una fecha posterior a la actual");
            }

            if (jdch_fechaHastaGI.getDate().before(jdch_fechaDesdeGI.getDate())) {
                throw new Exception("No se puede elegir una fecha anterior a la fecha [desde] para filtrar");
            }

            if (!rdb_comprasGI.isSelected() && !rdb_servicioTecnicoGI.isSelected() && !rdb_ventasGI.isSelected()) {
                throw new Exception("Debe seleccionar una opcion para filtrar");

            }

            String fechaDesde = formato.format(jdch_fechaDesdeGI.getDate());
            String fechaHasta = formato.format(jdch_fechaHastaGI.getDate());

            JFrame visualizador = new JFrame();
            Reporte reporte = new Reporte();
            Map parametros = new HashMap();

            if (rdb_comprasGI.isSelected()) {

                if (!this.cp.getControladoraCompra().listarComprasPorFechas(fechaDesde, fechaHasta).isEmpty()) {
                    parametros.put("fechaDesde", fechaDesde);
                    parametros.put("fechaHasta", fechaHasta);
                    visualizador.setContentPane(reporte.crearReporteInformeCompra("/reportes/reporteInformeCompra.jasper", parametros));
                    visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    visualizador.setVisible(true);
                }

            }

            if (rdb_servicioTecnicoGI.isSelected()) {
                if(!this.cp.getControladoraServicioTecnico().listarServiciosPorFechas(fechaDesde, fechaHasta).isEmpty()){
                    parametros.put("fechaDesde",fechaDesde);
                    parametros.put("fechaHasta",fechaHasta);
                    visualizador.setContentPane(reporte.crearReporteInformeServicioTecnico("/reportes/ReporteInformeServicioTecnico.jasper", parametros));
                    visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    visualizador.setVisible(true);
                }
                
                
            }

            if (rdb_ventasGI.isSelected()) {
                if(!this.cp.getControladoraVenta().ListarVentasPorFechas(fechaDesde, fechaHasta).isEmpty()){
                    parametros.put("fechaDesde", fechaDesde);
                    parametros.put("fechaHasta", fechaHasta);
                    visualizador.setContentPane(reporte.crearReporteInformeVenta("/reportes/ReporteInformeVenta.jasper", parametros));
                    visualizador.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    visualizador.setVisible(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion Informes", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_imprimirGIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelarGI;
    private javax.swing.JButton btn_filtrarGI;
    private javax.swing.JButton btn_imprimirGI;
    private javax.swing.JButton btn_nuevoInformeGI;
    private javax.swing.JButton btn_salirGI;
    private javax.swing.ButtonGroup btngroup_filtrosGI;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdch_fechaDesdeGI;
    private com.toedter.calendar.JDateChooser jdch_fechaHastaGI;
    private javax.swing.JLabel lbl_fechaDesdeGI;
    private javax.swing.JLabel lbl_fechaHastaGI;
    private javax.swing.JLabel lbl_tituloGI;
    private javax.swing.JPanel pnl_centralGI;
    private javax.swing.JPanel pnl_centroGI;
    private javax.swing.JPanel pnl_derechoGI;
    private javax.swing.JPanel pnl_encabezadoCentralGI;
    private javax.swing.JPanel pnl_encabezadoGI;
    private javax.swing.JPanel pnl_izquierdoGI;
    private javax.swing.JPanel pnl_pieGI;
    private javax.swing.JRadioButton rdb_comprasGI;
    private javax.swing.JRadioButton rdb_servicioTecnicoGI;
    private javax.swing.JRadioButton rdb_ventasGI;
    private javax.swing.JTable tb_tablaInformesGI;
    // End of variables declaration//GEN-END:variables
}
