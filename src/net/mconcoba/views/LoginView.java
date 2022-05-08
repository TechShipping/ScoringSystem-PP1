
package net.mconcoba.views;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.mconcoba.db.Conexion;
import rojeru_san.RSPanelsSlider;

/**
 *
 * @author mconcoba
 */
public class LoginView extends javax.swing.JFrame {

    private ImageIcon imagen;
    private Icon icono;
    static String usuario;
    
    public LoginView() {
        initComponents();
        
        this.mostartImagen(back, "src/net/mconcoba/resource/fondo.png");
    }
    
    private void mostartImagen(JLabel lbl, String ruta){
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(this.imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
        lbl.setIcon(icono);
        
        this.repaint();
        
    }
   
    private void verificacion(String usuario, String pas) {
        String user = "", pass = "";
        try {
            String sql = "SELECT * FROM usuarios WHERE usuario = '" + usuario + "'";
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstancia().getConexion().prepareCall("SELECT * from usuario_activos WHERE usuario = '" + usuario + "'");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                this.usuario =  resultado.getString(2) + " " + resultado.getString(3);
                user = resultado.getString(4);
                pass = resultado.getString(5);
               
            }

            if (user.equals(usuario) && pass.equals(pas)) {
                pnl_session.setVisible(false);
                pnl_loader.setVisible(true);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        dispose();
                        HomeView home = new HomeView();
                        home.setLocationRelativeTo(null); 
                        home.setVisible(true);
                    } catch (Exception e){
                        System.err.println(e);
                    }
                }).start();
            } else {
                tbl_error.setText("Usuaraio y/o contraseña incorrectos!");
            }

        } catch (SQLException ex) {
            System.err.println(ex);
            //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fondo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        content = new rojeru_san.RSPanelsSlider();
        pnl_session = new javax.swing.JPanel();
        txt_user = new rojeru_san.RSMTextFull();
        txt_password = new rojeru_san.RSMPassView();
        teach = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tbl_error = new javax.swing.JLabel();
        btn_login = new rojeru_san.RSButtonRiple();
        pnl_loader = new javax.swing.JPanel();
        loader = new rojerusan.componentes.RSProgressMaterial();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        back = new javax.swing.JLabel();

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        fondo.setFocusCycleRoot(true);
        fondo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        pnl_session.setBackground(new java.awt.Color(255, 255, 255));
        pnl_session.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnl_session.setToolTipText("");
        pnl_session.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnl_session.setFocusCycleRoot(true);
        pnl_session.setInheritsPopupMenu(true);
        pnl_session.setName("pnl_session"); // NOI18N
        pnl_session.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_user.setPlaceholder("Usuario...");
        pnl_session.add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 290, -1));

        txt_password.setPlaceholder("Contraseña");
        pnl_session.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 290, -1));

        teach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        teach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/teach.png"))); // NOI18N
        teach.setAutoscrolls(true);
        teach.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        teach.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        teach.setName(""); // NOI18N
        pnl_session.add(teach, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 120, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/pass.png"))); // NOI18N
        pnl_session.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/user.png"))); // NOI18N
        pnl_session.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        tbl_error.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        tbl_error.setForeground(new java.awt.Color(204, 0, 0));
        tbl_error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnl_session.add(tbl_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 230, 40));

        btn_login.setText("Iniciar Sesion");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        pnl_session.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, -1));

        content.add(pnl_session, "card2");

        pnl_loader.setBackground(new java.awt.Color(255, 255, 255));
        pnl_loader.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        pnl_loader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loader.setAnchoProgress(15);
        pnl_loader.add(loader, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 180, 180));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 112, 192));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Por favor espere!");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnl_loader.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 330, -1));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 112, 192));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cargando...");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnl_loader.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 300, -1));

        content.add(pnl_loader, "card3");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 92;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(65, 50, 0, 50);
        jPanel2.add(content, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(380, 373));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 550, 550));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 161;
        gridBagConstraints.ipady = 177;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 50, 33, 0);
        jPanel2.add(jPanel1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
       
        if(txt_user.getText().isEmpty()){
            tbl_error.setText("Ingrese usuaraio y contraseña!");
        } else {
            verificacion(txt_user.getText(), txt_password.getText());
        }
        
    }//GEN-LAST:event_btn_loginActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private rojeru_san.RSButtonRiple btn_login;
    private rojeru_san.RSPanelsSlider content;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojerusan.componentes.RSProgressMaterial loader;
    private javax.swing.JPanel pnl_loader;
    private javax.swing.JPanel pnl_session;
    private javax.swing.JLabel tbl_error;
    private javax.swing.JLabel teach;
    private rojeru_san.RSMPassView txt_password;
    private rojeru_san.RSMTextFull txt_user;
    // End of variables declaration//GEN-END:variables
}
