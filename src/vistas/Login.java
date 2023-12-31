/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.ControladoraPrincipal;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author EcobarM
 */
public class Login extends javax.swing.JFrame {

    private ControladoraPrincipal cp;
    private boolean mostrar = false;
    private Usuario usuario ;

    public Login(ControladoraPrincipal cp) {

        /*PERSONALIZACION DE VENTANA */
        this.cp = cp;
        this.setSize(700, 500);
        this.setResizable(false);

        this.setLocationRelativeTo(null);
        this.setIconImage(getIconImage());
        this.setTitle("Acceso al sistema");
        
        initComponents();

        /*FIN DE CONFIGURACION*/
        if (this.cp.getControladoraLogueo().getUsuarios() <= 0) {
            lbl_registrarmeLogin.setVisible(true);
        } else {
            lbl_registrarmeLogin.setVisible(false);
        }

    }

    @Override
    public Image getIconImage() {

        return Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logo-100.png"));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_fondoLogin = new javax.swing.JPanel();
        pnl_derechoLogin = new javax.swing.JPanel();
        lbl_nombreEmpresaLogin = new javax.swing.JLabel();
        lbl_logoLogin = new javax.swing.JLabel();
        lbl_iniciarSesion = new javax.swing.JLabel();
        lbl_nombreUsuarioLogin = new javax.swing.JLabel();
        lbl_claveLogin = new javax.swing.JLabel();
        txt_usuarioLogin = new javax.swing.JTextField();
        psw_claveLogin = new javax.swing.JPasswordField();
        lbl_registrarmeLogin = new javax.swing.JLabel();
        lbl_mostrarClaveLogin = new javax.swing.JLabel();
        lbl_entrarLogin = new javax.swing.JLabel();
        jsp_usuarioLogin = new javax.swing.JSeparator();
        jsp_claveLogin = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(700, 500));
        setName("frm_login"); // NOI18N
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_fondoLogin.setBackground(new java.awt.Color(116, 116, 116));

        pnl_derechoLogin.setBackground(new java.awt.Color(235, 135, 0));

        lbl_nombreEmpresaLogin.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_nombreEmpresaLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nombreEmpresaLogin.setText("TECNICAMENTE");

        lbl_logoLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_logoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo-100.png"))); // NOI18N

        javax.swing.GroupLayout pnl_derechoLoginLayout = new javax.swing.GroupLayout(pnl_derechoLogin);
        pnl_derechoLogin.setLayout(pnl_derechoLoginLayout);
        pnl_derechoLoginLayout.setHorizontalGroup(
            pnl_derechoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_derechoLoginLayout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(pnl_derechoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_nombreEmpresaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_logoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );
        pnl_derechoLoginLayout.setVerticalGroup(
            pnl_derechoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_derechoLoginLayout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(lbl_logoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(lbl_nombreEmpresaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );

        lbl_iniciarSesion.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_iniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_iniciarSesion.setText("Iniciar Sesion");

        lbl_nombreUsuarioLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nombreUsuarioLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario-50.png"))); // NOI18N

        lbl_claveLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_claveLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/contraseña-50.png"))); // NOI18N

        txt_usuarioLogin.setBackground(new java.awt.Color(116, 116, 116));
        txt_usuarioLogin.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txt_usuarioLogin.setBorder(null);

        psw_claveLogin.setBackground(new java.awt.Color(116, 116, 116));
        psw_claveLogin.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        psw_claveLogin.setToolTipText("");
        psw_claveLogin.setBorder(null);
        psw_claveLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                psw_claveLoginKeyPressed(evt);
            }
        });

        lbl_registrarmeLogin.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_registrarmeLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_registrarmeLogin.setText("Registrarme");
        lbl_registrarmeLogin.setPreferredSize(new java.awt.Dimension(80, 20));
        lbl_registrarmeLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_registrarmeLoginMouseClicked(evt);
            }
        });

        lbl_mostrarClaveLogin.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_mostrarClaveLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_mostrarClaveLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/invisible-50.png"))); // NOI18N
        lbl_mostrarClaveLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_mostrarClaveLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_mostrarClaveLoginMouseClicked(evt);
            }
        });

        lbl_entrarLogin.setBackground(new java.awt.Color(235, 135, 0));
        lbl_entrarLogin.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbl_entrarLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_entrarLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-login-20.png"))); // NOI18N
        lbl_entrarLogin.setText("Entrar");
        lbl_entrarLogin.setOpaque(true);
        lbl_entrarLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_entrarLoginMouseClicked(evt);
            }
        });

        jsp_usuarioLogin.setBackground(new java.awt.Color(235, 135, 0));
        jsp_usuarioLogin.setForeground(new java.awt.Color(235, 135, 0));

        jsp_claveLogin.setBackground(new java.awt.Color(235, 135, 0));
        jsp_claveLogin.setForeground(new java.awt.Color(235, 135, 0));

        javax.swing.GroupLayout pnl_fondoLoginLayout = new javax.swing.GroupLayout(pnl_fondoLogin);
        pnl_fondoLogin.setLayout(pnl_fondoLoginLayout);
        pnl_fondoLoginLayout.setHorizontalGroup(
            pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_fondoLoginLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                        .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_fondoLoginLayout.createSequentialGroup()
                                .addComponent(lbl_claveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_entrarLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbl_registrarmeLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jsp_claveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(psw_claveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 5, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl_fondoLoginLayout.createSequentialGroup()
                                .addComponent(lbl_nombreUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jsp_usuarioLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(txt_usuarioLogin, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(18, 18, 18)
                        .addComponent(lbl_mostrarClaveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_iniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(pnl_derechoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnl_fondoLoginLayout.setVerticalGroup(
            pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                .addComponent(pnl_derechoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lbl_iniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                        .addComponent(txt_usuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jsp_usuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_nombreUsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_fondoLoginLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_fondoLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(psw_claveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jsp_claveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_mostrarClaveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(pnl_fondoLoginLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lbl_claveLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)))
                .addComponent(lbl_registrarmeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_entrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnl_fondoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_mostrarClaveLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_mostrarClaveLoginMouseClicked

        ImageIcon image = new ImageIcon("imagenes/visible-50.png");

        if (mostrar == false) {
            lbl_mostrarClaveLogin.setIcon(image);
            psw_claveLogin.setEchoChar((char) 0);
            mostrar = true;

        } else {
            image = new ImageIcon("imagenes/invisible-50.png");
            lbl_mostrarClaveLogin.setIcon(image);
            psw_claveLogin.setEchoChar((char) '\u25cf');
            mostrar = false;
        }


    }//GEN-LAST:event_lbl_mostrarClaveLoginMouseClicked

    private void lbl_entrarLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_entrarLoginMouseClicked

        if (txt_usuarioLogin.getText().trim().isEmpty() || psw_claveLogin.getText().trim().isEmpty()) {
            if (txt_usuarioLogin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo del usuario esta vacio y ambos son obligatorios", "Login", JOptionPane.WARNING_MESSAGE);
            }

            if (psw_claveLogin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de la clave esta vacio y ambos son obligatorios", "Login", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            try {
                String nombreUsuario = txt_usuarioLogin.getText().trim();
                String clave = psw_claveLogin.getText().trim();

                this.usuario = this.cp.getControladoraLogueo().buscarXnombreUsuario(nombreUsuario);
                if (nombreUsuario.equals(usuario.getNombreUsuario())) {
                    if (clave.equals(usuario.getClave())) {
                        JOptionPane.showMessageDialog(null, "Logueo Correcto", "Login", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(cp, usuario);
                        ventanaPrincipal.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", "Login", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario Incorrecto", "Login", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Usuario Inexistente", "Login", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_lbl_entrarLoginMouseClicked

    private void lbl_registrarmeLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_registrarmeLoginMouseClicked
        Registrarme registro = new Registrarme(cp);
        this.dispose();
        registro.setVisible(true);
    }//GEN-LAST:event_lbl_registrarmeLoginMouseClicked

    private void psw_claveLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_psw_claveLoginKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //CONSULTA SI LOS CAMPOS ESTA VACIO//
            if (txt_usuarioLogin.getText().trim().isEmpty() || psw_claveLogin.getText().trim().isEmpty()) {

                if (txt_usuarioLogin.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo del usuario esta vacio y ambos son obligatorios", "Login", JOptionPane.WARNING_MESSAGE);
                }

                if (psw_claveLogin.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de la clave esta vacio y ambos son obligatorios", "Login", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                try {
                    String nombreUsuario = String.valueOf(txt_usuarioLogin.getText().trim());
                    String clave = String.valueOf(psw_claveLogin.getPassword());

                    this.usuario = this.cp.getControladoraLogueo().buscarXnombreUsuario(nombreUsuario);
                    if (nombreUsuario.equals(usuario.getNombreUsuario())) {
                        if (clave.equals(usuario.getClave())) {
                            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(cp, usuario);
                            JOptionPane.showMessageDialog(null, "Logueo Correcto", "Login", JOptionPane.INFORMATION_MESSAGE);
                            ventanaPrincipal.setExtendedState(MAXIMIZED_BOTH);
                            ventanaPrincipal.setVisible(true);
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", "Login", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario Incorrecto", "Login", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Usuario Inexistente", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }

        }


    }//GEN-LAST:event_psw_claveLoginKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jsp_claveLogin;
    private javax.swing.JSeparator jsp_usuarioLogin;
    private javax.swing.JLabel lbl_claveLogin;
    private javax.swing.JLabel lbl_entrarLogin;
    private javax.swing.JLabel lbl_iniciarSesion;
    private javax.swing.JLabel lbl_logoLogin;
    private javax.swing.JLabel lbl_mostrarClaveLogin;
    private javax.swing.JLabel lbl_nombreEmpresaLogin;
    private javax.swing.JLabel lbl_nombreUsuarioLogin;
    private javax.swing.JLabel lbl_registrarmeLogin;
    private javax.swing.JPanel pnl_derechoLogin;
    private javax.swing.JPanel pnl_fondoLogin;
    private javax.swing.JPasswordField psw_claveLogin;
    private javax.swing.JTextField txt_usuarioLogin;
    // End of variables declaration//GEN-END:variables
}
