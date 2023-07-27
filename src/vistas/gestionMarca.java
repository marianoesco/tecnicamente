/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Marca;
import modelo.TipoProducto;
import modelo.Usuario;
import modelo.Utilidades;
import vistas.gestionProducto;

/**
 *
 * @author EcobarM
 */
public class gestionMarca extends javax.swing.JInternalFrame {

    private ControladoraPrincipal cp;
    private List<TipoProducto> tipoProductoAUX = new LinkedList<>();
    private boolean editando;
    private JDesktopPane dskp_principalVP;
    private Usuario usuario;

    DefaultTableModel tbcargaTipoProductoIncluidos = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    /**
     * Creates new form gestionMarca
     */
    public gestionMarca(ControladoraPrincipal cp,Usuario usuario, JDesktopPane dskp_principalVP) {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.cp = cp;
        this.usuario=usuario;
        this.dskp_principalVP = dskp_principalVP;
        this.estadoInicial();
    }

    public void estadoInicial() {

        cargarTablaTipoProducto(this.cp.getControladoraProducto().listarTipoProductos());
        cargarTablaMarcas(this.cp.getControladoraProducto().listarMarcas());

        txt_codigoGM.setEnabled(!isEnabled());
        txt_nombreGM.setEnabled(!isEnabled());

        txt_codigoGM.setText("");
        txt_nombreGM.setText("");

        tipoProductoAUX = new LinkedList<>();

        txt_buscarMarcaPorNombreGM.setEnabled(isEnabled());
        
        txt_buscarMarcaPorNombreGM.setText("");
        
        txt_buscarTipoProductoPorNombreGM.setEnabled(!isEnabled());
        
        removerFilas(tbcargaTipoProductoIncluidos);
        tb_listadoTipoProductoIncluidosGM.setModel(tbcargaTipoProductoIncluidos);

        tb_listadoTipoProductoIncluidosGM.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));

        btn_agregarSeleccionadoGM.setEnabled(!isEnabled());
        btn_quitarSeleccionadoGM.setEnabled(!isEnabled());

        tb_listadoMarcasGM.setEnabled(isEnabled());
       
        
        btn_nuevoGM.setEnabled(isEnabled());
        btn_editarGM.setEnabled(isEnabled());
        btn_cancelarGM.setEnabled(!isEnabled());
        btn_guardarGM.setEnabled(!isEnabled());
        btn_salirGM.setEnabled(isEnabled());

        editando = false;

    }

    public void removerFilas(DefaultTableModel model) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public void cambiarEstado() {
        cargarTablaTipoProducto(this.cp.getControladoraProducto().listarTipoProductos());
        
        
        tb_listadoMarcasGM.setEnabled(!isEnabled());
        tb_listadoTipoProductoGM.setEnabled(isEnabled());
        tb_listadoTipoProductoIncluidosGM.setEnabled(isEnabled());
        
        txt_buscarTipoProductoPorNombreGM.setEnabled(isEnabled());
        
        txt_codigoGM.setEnabled(!isEnabled());
        txt_nombreGM.setEnabled(isEnabled());
        btn_agregarSeleccionadoGM.setEnabled(isEnabled());
        btn_quitarSeleccionadoGM.setEnabled(isEnabled());

        btn_nuevoGM.setEnabled(!isEnabled());
        btn_editarGM.setEnabled(!isEnabled());
        btn_cancelarGM.setEnabled(isEnabled());
        btn_guardarGM.setEnabled(isEnabled());

    }

    public void cargarTablaTipoProducto(List<TipoProducto> TipoProducto) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!TipoProducto.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tbCarga.getColumnCount()];
            tb_listadoTipoProductoGM.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (TipoProducto Tp : TipoProducto) {
                fila[0] = Tp.getCodigo();
                fila[1] = Tp.getNombre();
                tbCarga.addRow(fila);
            }
            
        }
        tb_listadoTipoProductoGM.setModel(tbCarga);
        
    }

    public void cargarTablaTipoProductoIncluidos(List<TipoProducto> TipoProducto) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!TipoProducto.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tbCarga.getColumnCount()];
            tb_listadoTipoProductoIncluidosGM.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (TipoProducto Tp : TipoProducto) {
                fila[0] = Tp.getCodigo();
                fila[1] = Tp.getNombre();
                tbCarga.addRow(fila);
            }
           
        }
         tb_listadoTipoProductoIncluidosGM.setModel(tbCarga);
    }

    public void cargarTablaMarcas(List<Marca> Marca) {
        DefaultTableModel tbCarga = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (!Marca.isEmpty()) {
            String Cabecera[] = {"Codigo", "Nombre"};
            tbCarga.setColumnIdentifiers(Cabecera);
            Object fila[] = new Object[tbCarga.getColumnCount()];
            tb_listadoMarcasGM.getTableHeader().setFont(new Font("Century Ghotic", Font.BOLD, 14));
            for (Marca m : Marca) {
                fila[0] = m.getCodigo();
                fila[1] = m.getNombre();
                tbCarga.addRow(fila);
            }
            
        }
        tb_listadoMarcasGM.setModel(tbCarga);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_encabezadoGM = new javax.swing.JPanel();
        lbl_tituloGM = new javax.swing.JLabel();
        pnl_derechoGM = new javax.swing.JPanel();
        pnl_izquierdoGM = new javax.swing.JPanel();
        pnl_pieGM = new javax.swing.JPanel();
        btn_nuevoGM = new javax.swing.JButton();
        btn_editarGM = new javax.swing.JButton();
        btn_cancelarGM = new javax.swing.JButton();
        btn_guardarGM = new javax.swing.JButton();
        btn_salirGM = new javax.swing.JButton();
        pnl_centralGM = new javax.swing.JPanel();
        tbp_centralGM = new javax.swing.JTabbedPane();
        pnl_gestionMarcaGM = new javax.swing.JPanel();
        pnl_centralGestionGM = new javax.swing.JPanel();
        pnl_tablaTipoProductoGM = new javax.swing.JPanel();
        pnl_encabezadoGestionGM = new javax.swing.JPanel();
        lbl_buscarTipoProductoPorNombreGM = new javax.swing.JLabel();
        txt_buscarTipoProductoPorNombreGM = new javax.swing.JTextField();
        pnl_tablaTipoGM = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_listadoTipoProductoGM = new javax.swing.JTable();
        pnl_botonesAgregadoGM = new javax.swing.JPanel();
        btn_agregarSeleccionadoGM = new javax.swing.JButton();
        btn_quitarSeleccionadoGM = new javax.swing.JButton();
        pnl_tipoProductoIncluidoGM = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_listadoTipoProductoIncluidosGM = new javax.swing.JTable();
        pnl_formularioGestionGM = new javax.swing.JPanel();
        lbl_codigoGM = new javax.swing.JLabel();
        txt_codigoGM = new javax.swing.JTextField();
        lbl_nombreGM = new javax.swing.JLabel();
        txt_nombreGM = new javax.swing.JTextField();
        pnl_listadoMarcasGM = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_listadoMarcasGM = new javax.swing.JTable();
        pnl_encabezadoCentralGM = new javax.swing.JPanel();
        lbl_buscarMarcaPorNombreGM = new javax.swing.JLabel();
        txt_buscarMarcaPorNombreGM = new javax.swing.JTextField();
        pnl_izquierdoCentralGM = new javax.swing.JPanel();
        pnl_derechoCentralGM = new javax.swing.JPanel();

        setBorder(null);
        setIconifiable(true);
        setResizable(true);
        setTitle("Gestion Marca");

        pnl_encabezadoGM.setPreferredSize(new java.awt.Dimension(661, 50));
        pnl_encabezadoGM.setLayout(new java.awt.BorderLayout());

        lbl_tituloGM.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_tituloGM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tituloGM.setText("Gestion de Marcas");
        pnl_encabezadoGM.add(lbl_tituloGM, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_encabezadoGM, java.awt.BorderLayout.NORTH);

        pnl_derechoGM.setPreferredSize(new java.awt.Dimension(50, 208));

        javax.swing.GroupLayout pnl_derechoGMLayout = new javax.swing.GroupLayout(pnl_derechoGM);
        pnl_derechoGM.setLayout(pnl_derechoGMLayout);
        pnl_derechoGMLayout.setHorizontalGroup(
            pnl_derechoGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_derechoGMLayout.setVerticalGroup(
            pnl_derechoGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_derechoGM, java.awt.BorderLayout.EAST);

        pnl_izquierdoGM.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout pnl_izquierdoGMLayout = new javax.swing.GroupLayout(pnl_izquierdoGM);
        pnl_izquierdoGM.setLayout(pnl_izquierdoGMLayout);
        pnl_izquierdoGMLayout.setHorizontalGroup(
            pnl_izquierdoGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        pnl_izquierdoGMLayout.setVerticalGroup(
            pnl_izquierdoGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );

        getContentPane().add(pnl_izquierdoGM, java.awt.BorderLayout.WEST);

        pnl_pieGM.setPreferredSize(new java.awt.Dimension(661, 50));
        pnl_pieGM.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 15));

        btn_nuevoGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_nuevoGM.setText("Nuevo");
        btn_nuevoGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoGMActionPerformed(evt);
            }
        });
        pnl_pieGM.add(btn_nuevoGM);

        btn_editarGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_editarGM.setText("Editar");
        btn_editarGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarGMActionPerformed(evt);
            }
        });
        pnl_pieGM.add(btn_editarGM);

        btn_cancelarGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_cancelarGM.setText("Cancelar");
        btn_cancelarGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarGMActionPerformed(evt);
            }
        });
        pnl_pieGM.add(btn_cancelarGM);

        btn_guardarGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_guardarGM.setText("Guardar");
        btn_guardarGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarGMActionPerformed(evt);
            }
        });
        pnl_pieGM.add(btn_guardarGM);

        btn_salirGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_salirGM.setText("Salir");
        btn_salirGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirGMActionPerformed(evt);
            }
        });
        pnl_pieGM.add(btn_salirGM);

        getContentPane().add(pnl_pieGM, java.awt.BorderLayout.SOUTH);

        pnl_centralGM.setLayout(new java.awt.BorderLayout());

        tbp_centralGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N

        pnl_gestionMarcaGM.setLayout(new java.awt.BorderLayout());

        pnl_centralGestionGM.setLayout(new java.awt.BorderLayout());

        pnl_tablaTipoProductoGM.setLayout(new java.awt.BorderLayout());

        pnl_encabezadoGestionGM.setPreferredSize(new java.awt.Dimension(741, 50));
        pnl_encabezadoGestionGM.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 15));

        lbl_buscarTipoProductoPorNombreGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarTipoProductoPorNombreGM.setText("Buscar Tipo Producto por Nombre : ");
        pnl_encabezadoGestionGM.add(lbl_buscarTipoProductoPorNombreGM);

        txt_buscarTipoProductoPorNombreGM.setColumns(10);
        txt_buscarTipoProductoPorNombreGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarTipoProductoPorNombreGM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarTipoProductoPorNombreGMKeyReleased(evt);
            }
        });
        pnl_encabezadoGestionGM.add(txt_buscarTipoProductoPorNombreGM);

        pnl_tablaTipoProductoGM.add(pnl_encabezadoGestionGM, java.awt.BorderLayout.NORTH);

        pnl_tablaTipoGM.setPreferredSize(new java.awt.Dimension(700, 300));
        pnl_tablaTipoGM.setLayout(new java.awt.GridLayout(2, 0));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(450, 100));

        tb_listadoTipoProductoGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoTipoProductoGM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tb_listadoTipoProductoGM);

        pnl_tablaTipoGM.add(jScrollPane1);

        pnl_botonesAgregadoGM.setPreferredSize(new java.awt.Dimension(50, 50));

        btn_agregarSeleccionadoGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_agregarSeleccionadoGM.setText("Agregar ");
        btn_agregarSeleccionadoGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarSeleccionadoGMActionPerformed(evt);
            }
        });
        pnl_botonesAgregadoGM.add(btn_agregarSeleccionadoGM);

        btn_quitarSeleccionadoGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btn_quitarSeleccionadoGM.setText("Quitar");
        btn_quitarSeleccionadoGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarSeleccionadoGMActionPerformed(evt);
            }
        });
        pnl_botonesAgregadoGM.add(btn_quitarSeleccionadoGM);

        pnl_tablaTipoGM.add(pnl_botonesAgregadoGM);

        pnl_tablaTipoProductoGM.add(pnl_tablaTipoGM, java.awt.BorderLayout.CENTER);

        pnl_centralGestionGM.add(pnl_tablaTipoProductoGM, java.awt.BorderLayout.CENTER);

        pnl_tipoProductoIncluidoGM.setPreferredSize(new java.awt.Dimension(766, 150));
        pnl_tipoProductoIncluidoGM.setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane3.setPreferredSize(new java.awt.Dimension(100, 100));

        tb_listadoTipoProductoIncluidosGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoTipoProductoIncluidosGM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tb_listadoTipoProductoIncluidosGM);

        pnl_tipoProductoIncluidoGM.add(jScrollPane3);

        pnl_centralGestionGM.add(pnl_tipoProductoIncluidoGM, java.awt.BorderLayout.SOUTH);

        pnl_gestionMarcaGM.add(pnl_centralGestionGM, java.awt.BorderLayout.CENTER);

        pnl_formularioGestionGM.setPreferredSize(new java.awt.Dimension(745, 50));
        pnl_formularioGestionGM.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        lbl_codigoGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_codigoGM.setText("Codigo: ");
        pnl_formularioGestionGM.add(lbl_codigoGM);

        txt_codigoGM.setColumns(5);
        txt_codigoGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_codigoGM.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pnl_formularioGestionGM.add(txt_codigoGM);

        lbl_nombreGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_nombreGM.setText("Nombre: ");
        pnl_formularioGestionGM.add(lbl_nombreGM);

        txt_nombreGM.setColumns(15);
        txt_nombreGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        pnl_formularioGestionGM.add(txt_nombreGM);

        pnl_gestionMarcaGM.add(pnl_formularioGestionGM, java.awt.BorderLayout.SOUTH);

        tbp_centralGM.addTab("Gestion", pnl_gestionMarcaGM);

        pnl_listadoMarcasGM.setLayout(new java.awt.BorderLayout());

        tb_listadoMarcasGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tb_listadoMarcasGM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tb_listadoMarcasGM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_listadoMarcasGMMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_listadoMarcasGM);

        pnl_listadoMarcasGM.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnl_encabezadoCentralGM.setPreferredSize(new java.awt.Dimension(745, 50));
        pnl_encabezadoCentralGM.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 15));

        lbl_buscarMarcaPorNombreGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_buscarMarcaPorNombreGM.setText("Buscar Marca por nombre : ");
        pnl_encabezadoCentralGM.add(lbl_buscarMarcaPorNombreGM);

        txt_buscarMarcaPorNombreGM.setColumns(10);
        txt_buscarMarcaPorNombreGM.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_buscarMarcaPorNombreGM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarMarcaPorNombreGMKeyReleased(evt);
            }
        });
        pnl_encabezadoCentralGM.add(txt_buscarMarcaPorNombreGM);

        pnl_listadoMarcasGM.add(pnl_encabezadoCentralGM, java.awt.BorderLayout.NORTH);

        pnl_izquierdoCentralGM.setPreferredSize(new java.awt.Dimension(150, 529));

        javax.swing.GroupLayout pnl_izquierdoCentralGMLayout = new javax.swing.GroupLayout(pnl_izquierdoCentralGM);
        pnl_izquierdoCentralGM.setLayout(pnl_izquierdoCentralGMLayout);
        pnl_izquierdoCentralGMLayout.setHorizontalGroup(
            pnl_izquierdoCentralGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnl_izquierdoCentralGMLayout.setVerticalGroup(
            pnl_izquierdoCentralGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        pnl_listadoMarcasGM.add(pnl_izquierdoCentralGM, java.awt.BorderLayout.LINE_START);

        pnl_derechoCentralGM.setPreferredSize(new java.awt.Dimension(150, 529));

        javax.swing.GroupLayout pnl_derechoCentralGMLayout = new javax.swing.GroupLayout(pnl_derechoCentralGM);
        pnl_derechoCentralGM.setLayout(pnl_derechoCentralGMLayout);
        pnl_derechoCentralGMLayout.setHorizontalGroup(
            pnl_derechoCentralGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnl_derechoCentralGMLayout.setVerticalGroup(
            pnl_derechoCentralGMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
        );

        pnl_listadoMarcasGM.add(pnl_derechoCentralGM, java.awt.BorderLayout.LINE_END);

        tbp_centralGM.addTab("Listado", pnl_listadoMarcasGM);

        pnl_centralGM.add(tbp_centralGM, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnl_centralGM, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nuevoGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoGMActionPerformed
        this.cambiarEstado();
        tb_listadoMarcasGM.clearSelection();
        tb_listadoTipoProductoGM.clearSelection();
        tb_listadoTipoProductoIncluidosGM.clearSelection();
        
    }//GEN-LAST:event_btn_nuevoGMActionPerformed

    private void btn_editarGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarGMActionPerformed
        if (tb_listadoMarcasGM.getSelectedRow() > -1 && tb_listadoMarcasGM.getSelectedRowCount() == 1) {

            Integer seleccionado = (Integer) tb_listadoMarcasGM.getValueAt(tb_listadoMarcasGM.getSelectedRow(), 0);
            Marca MarcaSeleccionado = this.cp.getControladoraProducto().buscarMarcaPorCodigo(seleccionado);

            if (MarcaSeleccionado != null) {
                
                this.cambiarEstado();

                for (TipoProducto tipoProducto : MarcaSeleccionado.getTiposProductos()) {
                    tipoProductoAUX.add(tipoProducto);
                }

                txt_codigoGM.setText(String.valueOf(MarcaSeleccionado.getCodigo()));
                txt_nombreGM.setText(MarcaSeleccionado.getNombre());
                cargarTablaTipoProductoIncluidos(MarcaSeleccionado.getTiposProductos());

            }

            editando = true;
            tb_listadoMarcasGM.clearSelection();
            
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una marca", "Gestion de Marcas", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_editarGMActionPerformed

    private void btn_cancelarGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarGMActionPerformed
        this.estadoInicial();
    }//GEN-LAST:event_btn_cancelarGMActionPerformed

    private void btn_salirGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirGMActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirGMActionPerformed

    private void btn_quitarSeleccionadoGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarSeleccionadoGMActionPerformed
        try {

            if (tb_listadoTipoProductoIncluidosGM.getSelectedRowCount() <= 0) {
                throw new Exception("Debe seleccionar un tipo Producto");

            } else if (tb_listadoTipoProductoIncluidosGM.getSelectedRowCount() >= 2) {
                throw new Exception("Debe seleccionar un solo tipo Producto");

            } else if (tb_listadoTipoProductoIncluidosGM.getSelectedRowCount() == 1) {

                if (editando == false) {
                    tbcargaTipoProductoIncluidos = (DefaultTableModel) tb_listadoTipoProductoIncluidosGM.getModel();
                    tbcargaTipoProductoIncluidos.removeRow(tb_listadoTipoProductoIncluidosGM.getSelectedRow());
                    tb_listadoTipoProductoIncluidosGM.setModel(tbcargaTipoProductoIncluidos);
                    tb_listadoTipoProductoGM.clearSelection();

                } else {
                    for (TipoProducto tipoProductoAUX1 : tipoProductoAUX) {
                        if (tipoProductoAUX1.getCodigo() == (int) tb_listadoTipoProductoIncluidosGM.getValueAt(tb_listadoTipoProductoIncluidosGM.getSelectedRow(), 0)) {
                            tipoProductoAUX.remove(tipoProductoAUX1);
                            tbcargaTipoProductoIncluidos = (DefaultTableModel) tb_listadoTipoProductoIncluidosGM.getModel();
                            tbcargaTipoProductoIncluidos.removeRow(tb_listadoTipoProductoIncluidosGM.getSelectedRow());
                            break;
                        }
                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Marcas", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_quitarSeleccionadoGMActionPerformed

    private void btn_guardarGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarGMActionPerformed
        try {
            if (!txt_nombreGM.getText().isEmpty()) {

                String nombre = txt_nombreGM.getText().trim();

                if (tb_listadoTipoProductoIncluidosGM.getRowCount() <= 0) {
                    throw new Exception("Debe agregar un tipo producto para la marca");
                }
                List<TipoProducto> tipos = new LinkedList<>();

                for (int i = 0; i < tb_listadoTipoProductoIncluidosGM.getRowCount(); i++) {
                    int codigo = (int) tb_listadoTipoProductoIncluidosGM.getValueAt(i, 0);
                    TipoProducto tipoProducto = this.cp.getControladoraProducto().buscarTipoProductoPorCodigo(codigo);
                    tipos.add(tipoProducto);
                }

                if (txt_codigoGM.getText().trim().isEmpty()) {
                    Marca marca = new Marca(nombre);
                    this.cp.getControladoraProducto().RegistarMarca(marca);
                    marca.setTiposProductos(tipos);
                    this.cp.getControladoraProducto().ModificarMarca(marca);
                    JOptionPane.showMessageDialog(null, "Creado con Exito", "Gestion de Tipo Producto", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Integer codigo = Integer.valueOf(txt_codigoGM.getText().trim());
                    Marca marca = this.cp.getControladoraProducto().buscarMarcaPorCodigo(codigo);
                    marca.setNombre(nombre);
                    marca.setTiposProductos(tipoProductoAUX);
                    this.cp.getControladoraProducto().ModificarMarca(marca);
                    JOptionPane.showMessageDialog(null, "Editado con Exito", "Gestion de Tipo Producto", JOptionPane.INFORMATION_MESSAGE);
                }

                this.estadoInicial();

            } else {
                throw new Exception("Todos los campos son obligatorios");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Marcas", JOptionPane.WARNING_MESSAGE);

        }

    }//GEN-LAST:event_btn_guardarGMActionPerformed

    private void tb_listadoMarcasGMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_listadoMarcasGMMouseClicked

    }//GEN-LAST:event_tb_listadoMarcasGMMouseClicked

    private void txt_buscarMarcaPorNombreGMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarMarcaPorNombreGMKeyReleased
        if (!txt_buscarMarcaPorNombreGM.getText().trim().isEmpty()) {
            String nombre = txt_buscarMarcaPorNombreGM.getText().trim();
            cargarTablaMarcas(this.cp.getControladoraProducto().filtrarMarcaPorNombre(nombre));
        } else {
            cargarTablaMarcas(this.cp.getControladoraProducto().listarMarcas());
        }
    }//GEN-LAST:event_txt_buscarMarcaPorNombreGMKeyReleased

    private void txt_buscarTipoProductoPorNombreGMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarTipoProductoPorNombreGMKeyReleased
        if (!txt_buscarTipoProductoPorNombreGM.getText().trim().isEmpty()) {
            String nombre = txt_buscarTipoProductoPorNombreGM.getText().trim();
            cargarTablaTipoProducto(this.cp.getControladoraProducto().filtrarTipoProductoPorNombre(nombre));
        } else {
            cargarTablaTipoProducto(this.cp.getControladoraProducto().listarTipoProductos());
        }
    }//GEN-LAST:event_txt_buscarTipoProductoPorNombreGMKeyReleased

    private void btn_agregarSeleccionadoGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarSeleccionadoGMActionPerformed
        try {
            boolean registrado = false;

            if (tb_listadoTipoProductoGM.getSelectedRowCount() <= 0) {
                throw new Exception("Debe seleccionar un tipo Producto");

            } else if (tb_listadoTipoProductoGM.getSelectedRowCount() >= 2) {
                throw new Exception("Debe seleccionar un solo tipo Producto");

            } else if (tb_listadoTipoProductoGM.getSelectedRowCount() == 1) {

                if (editando == false) {
                    int codigo = (int) tb_listadoTipoProductoGM.getValueAt(tb_listadoTipoProductoGM.getSelectedRow(), 0);
                    TipoProducto tipoProductoseleccionado = this.cp.getControladoraProducto().buscarTipoProductoPorCodigo(codigo);
                    for (int i = 0; i < tb_listadoTipoProductoIncluidosGM.getRowCount(); i++) {
                        if (tb_listadoTipoProductoIncluidosGM.getValueAt(i, 1).equals(tipoProductoseleccionado.getNombre())) {
                            registrado = true;
                            break;
                        }

                    }

                    if (registrado == false) {
                        tipoProductoAUX.add(tipoProductoseleccionado);
                        cargarTablaTipoProductoIncluidos(tipoProductoAUX);

                        tb_listadoTipoProductoGM.clearSelection();
                    } else {
                        throw new Exception("El tipo de producto ya existe");
                    }
                } else {
                    int codigo = (int) tb_listadoTipoProductoGM.getValueAt(tb_listadoTipoProductoGM.getSelectedRow(), 0);
                    TipoProducto tipoProductoSeleccionado = this.cp.getControladoraProducto().buscarTipoProductoPorCodigo(codigo);
                    for (TipoProducto tipoProductoAUX1 : tipoProductoAUX) {
                        if (tipoProductoAUX1.getCodigo() == codigo) {
                            registrado = true;
                            break;
                        }
                    }

                    if (registrado == false) {
                        tipoProductoAUX.add(tipoProductoSeleccionado);
                    } else {
                        throw new Exception("El tipo de producto ya existe");
                    }

                    cargarTablaTipoProductoIncluidos(tipoProductoAUX);
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Gestion de Marcas", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_agregarSeleccionadoGMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregarSeleccionadoGM;
    private javax.swing.JButton btn_cancelarGM;
    private javax.swing.JButton btn_editarGM;
    private javax.swing.JButton btn_guardarGM;
    private javax.swing.JButton btn_nuevoGM;
    private javax.swing.JButton btn_quitarSeleccionadoGM;
    private javax.swing.JButton btn_salirGM;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_buscarMarcaPorNombreGM;
    private javax.swing.JLabel lbl_buscarTipoProductoPorNombreGM;
    private javax.swing.JLabel lbl_codigoGM;
    private javax.swing.JLabel lbl_nombreGM;
    private javax.swing.JLabel lbl_tituloGM;
    private javax.swing.JPanel pnl_botonesAgregadoGM;
    private javax.swing.JPanel pnl_centralGM;
    private javax.swing.JPanel pnl_centralGestionGM;
    private javax.swing.JPanel pnl_derechoCentralGM;
    private javax.swing.JPanel pnl_derechoGM;
    private javax.swing.JPanel pnl_encabezadoCentralGM;
    private javax.swing.JPanel pnl_encabezadoGM;
    private javax.swing.JPanel pnl_encabezadoGestionGM;
    private javax.swing.JPanel pnl_formularioGestionGM;
    private javax.swing.JPanel pnl_gestionMarcaGM;
    private javax.swing.JPanel pnl_izquierdoCentralGM;
    private javax.swing.JPanel pnl_izquierdoGM;
    private javax.swing.JPanel pnl_listadoMarcasGM;
    private javax.swing.JPanel pnl_pieGM;
    private javax.swing.JPanel pnl_tablaTipoGM;
    private javax.swing.JPanel pnl_tablaTipoProductoGM;
    private javax.swing.JPanel pnl_tipoProductoIncluidoGM;
    private javax.swing.JTable tb_listadoMarcasGM;
    private javax.swing.JTable tb_listadoTipoProductoGM;
    private javax.swing.JTable tb_listadoTipoProductoIncluidosGM;
    private javax.swing.JTabbedPane tbp_centralGM;
    private javax.swing.JTextField txt_buscarMarcaPorNombreGM;
    private javax.swing.JTextField txt_buscarTipoProductoPorNombreGM;
    private javax.swing.JTextField txt_codigoGM;
    private javax.swing.JTextField txt_nombreGM;
    // End of variables declaration//GEN-END:variables
}
