
package net.mconcoba.views;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.mconcoba.contollers.StudentController;
import net.mconcoba.models.StudentModel;

/**
 *
 * @author mconcoba
 */
public class ScorginsView extends javax.swing.JFrame {
    
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    String columnas[] = {"Clave","Nombres","Apellidos","Bimestre 1","Bimestre 2","Bimestre 3","Bimestre 4","Promedio"};
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        
    };
    StudentController studetC = new StudentController();
    ArrayList<Object[]> data = new ArrayList<>();

    
    public ScorginsView() {
        initComponents();
        cargarDatos();
        desactivarControles();
    }

    private void cargarDatos(){
        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        this.data = studetC.consultar();
        modelo.getColumnName(0);
        modelo.setNumRows(0);
        for(Object [] dato: this.data){
            this.modelo.addRow(dato);
        }
        tbl_notas.setModel(modelo);
    }
    
    
    public void nuevo(){
        switch (tipoDeOperacion){
            case NINGUNO:
                limpiarControles();
                tbl_notas.getSelectionModel().clearSelection();
                activarControles();
                txt_nombre.requestFocus();
                btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/save.png")));
                btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/close.png")));
                btn_nuevo.setText("Guardar");
                btn_eliminar.setText("Cancelar");
                btn_eliminar.setEnabled(true);
                btn_editar.setEnabled(false);
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            case GUARDAR:
                
                if(txt_nombre.getText().isEmpty() || txt_apellido.getText().isEmpty() || txt_nota1.getText().isEmpty() || 
                        txt_nota2.getText().isEmpty() || txt_nota3.getText().isEmpty() || txt_nota4.getText().isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "Datos Incompletos", "Error", 0);
                } else if(Float.parseFloat(txt_nota1.getText()) > 100 || Float.parseFloat(txt_nota2.getText()) > 100 ||
                          Float.parseFloat(txt_nota3.getText()) > 100 || Float.parseFloat(txt_nota4.getText()) > 100) {
                    JOptionPane.showMessageDialog(null, "Favor de verificar las notas no deben de ser mayor a 100", "Error", 0);
                }else{
                    StudentModel std = new StudentModel(0, txt_nombre.getText(),
                                    txt_apellido.getText(),
                                    Float.parseFloat(txt_nota1.getText()),
                                    Float.parseFloat(txt_nota2.getText()),
                                    Float.parseFloat(txt_nota3.getText()),
                                    Float.parseFloat(txt_nota4.getText()),
                                    Float.parseFloat("0"), null,null);
                    studetC.guardar(std);
                    desactivarControles();
                    limpiarControles();
                    tbl_notas.getSelectionModel().clearSelection();
                    btn_nuevo.setText("Nuevo");
                    btn_eliminar.setText("Eliminar");
                    cargarDatos();
                    tipoDeOperacion = operaciones.NINGUNO;
                }
            break;
        }
    }
    
    public void eliminar(){
        int selectedRowIndex = tbl_notas.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)tbl_notas.getModel();
        switch (tipoDeOperacion) {
            case GUARDAR:
                desactivarControles();
                limpiarControles();
                tbl_notas.getSelectionModel().clearSelection();
                btn_nuevo.setText("Nuevo");
                btn_eliminar.setText("Eliminar");
                // btn_editar.setEnabled(true);
                tipoDeOperacion = operaciones.NINGUNO;
            break;
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                tbl_notas.getSelectionModel().clearSelection();
                btn_editar.setText("Editar");
                btn_eliminar.setText("Eliminar");
                btn_nuevo.setEnabled(true);
                tipoDeOperacion = operaciones.NINGUNO;
            break;
            default:
                if (selectedRowIndex >= 0) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el registro?", "Eliminar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respuesta == JOptionPane.YES_OPTION) {
                        StudentModel std = new StudentModel(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()), 
                                                    txt_nombre.getText(),
                                                    txt_apellido.getText(),
                                                    Float.parseFloat(txt_nota1.getText()),
                                                    Float.parseFloat(txt_nota2.getText()),
                                                    Float.parseFloat(txt_nota3.getText()),
                                                    Float.parseFloat(txt_nota4.getText()),
                                                    Float.parseFloat("0"),
                                                    null,
                                                    null);
                        studetC.eliminar(std);
                        desactivarControles();
                        limpiarControles();
                        tbl_notas.getSelectionModel().clearSelection();
                        cargarDatos();
                        tipoDeOperacion = operaciones.NINGUNO;
                    }else{
                        limpiarControles();
                        tbl_notas.getSelectionModel().clearSelection();   
                    }
                        
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
                
        
        }
    } 
    
    public void editar(){
        int selectedRowIndex = tbl_notas.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)tbl_notas.getModel();
        switch (tipoDeOperacion) {
            case NINGUNO:
                if (selectedRowIndex >= 0) {
                    btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/save.png")));
                    btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/close.png")));
                    btn_editar.setText("Actualizar");
                    btn_eliminar.setText("Cancelar");
                    btn_eliminar.setEnabled(true);
                    btn_nuevo.setEnabled(false);
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
            break;
            case ACTUALIZAR:
                if(txt_nombre.getText().isEmpty() || txt_apellido.getText().isEmpty() || txt_nota1.getText().isEmpty() || 
                        txt_nota2.getText().isEmpty() || txt_nota3.getText().isEmpty() || txt_nota4.getText().isEmpty() ){
                    JOptionPane.showMessageDialog(null, "Datos Incompletos", "Error", 0);
                } else {
                    
                     StudentModel std = new StudentModel(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()),
                                    txt_nombre.getText(),
                                    txt_apellido.getText(),
                                    Float.parseFloat(txt_nota1.getText()),
                                    Float.parseFloat(txt_nota2.getText()),
                                    Float.parseFloat(txt_nota3.getText()),
                                    Float.parseFloat(txt_nota4.getText()),
                                    Float.parseFloat("0"), null,null);
                    studetC.editar(std);
                    desactivarControles();
                    limpiarControles();
                    tbl_notas.getSelectionModel().clearSelection();
                    btn_editar.setText("Editar");
                    btn_eliminar.setText("Eliminar");
                    btn_nuevo.setEnabled(true);
                    cargarDatos();
                    tipoDeOperacion = operaciones.NINGUNO;
                }
            break;
        }
    }
    
     public void desactivarControles(){
        txt_nombre.setEnabled(false);
        txt_apellido.setEnabled(false);
        txt_nota1.setEnabled(false);
        txt_nota2.setEnabled(false);
        txt_nota3.setEnabled(false);
        txt_nota4.setEnabled(false);
        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/add.png")));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/update.png")));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/delete.png")));
    }
    
    public void activarControles(){
        txt_nombre.setEnabled(true);
        txt_apellido.setEnabled(true);
        txt_nota1.setEnabled(true);
        txt_nota2.setEnabled(true);
        txt_nota3.setEnabled(true);
        txt_nota4.setEnabled(true);
    }
    
    public void limpiarControles(){
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_nota1.setText("");
        txt_nota2.setText("");
        txt_nota3.setText("");
        txt_nota4.setText("");
    }
    
    public void keyPressed(KeyEvent ke, javax.swing.JTextField input) {
        if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || ke.getKeyCode() == 8 ) {
           input.setEditable(true);
        }  else {
           input.setEditable(false);
        }
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_nota1 = new javax.swing.JTextField();
        txt_nota2 = new javax.swing.JTextField();
        txt_nota3 = new javax.swing.JTextField();
        txt_nota4 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_notas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_editar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Nombres:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Apellidos:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Nota 1");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Nota 2");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Nota 4");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Nota 3");

        txt_nota1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nota1KeyPressed(evt);
            }
        });

        txt_nota2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nota2KeyPressed(evt);
            }
        });

        txt_nota3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nota3KeyPressed(evt);
            }
        });

        txt_nota4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nota4KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_nota1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_nota2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_nota3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_nota4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txt_nombre))
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(txt_apellido))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(118, 118, 118))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombre)
                    .addComponent(txt_apellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nota1)
                    .addComponent(txt_nota2)
                    .addComponent(txt_nota3)
                    .addComponent(txt_nota4))
                .addGap(33, 33, 33))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista"));

        tbl_notas.setBorder(new javax.swing.border.MatteBorder(null));
        tbl_notas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Nombres", "Apellidos", "Bimestre 1 ", "Bimestre 2", "Bimestre 3 ", "Bimestre 4 ", "Promedio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_notas.getTableHeader().setReorderingAllowed(false);
        tbl_notas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_notasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbl_notasMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_notas);
        if (tbl_notas.getColumnModel().getColumnCount() > 0) {
            tbl_notas.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_notas.getColumnModel().getColumn(3).setPreferredWidth(30);
            tbl_notas.getColumnModel().getColumn(4).setPreferredWidth(30);
            tbl_notas.getColumnModel().getColumn(5).setPreferredWidth(30);
            tbl_notas.getColumnModel().getColumn(6).setPreferredWidth(30);
            tbl_notas.getColumnModel().getColumn(7).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/update.png"))); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editarMouseClicked(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminarMouseClicked(evt);
            }
        });

        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/add.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(88, 88, 88)
                .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(88, 88, 88)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 48)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Calificaciones");
        jLabel7.setToolTipText("");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/mconcoba/resource/list.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(122, 122, 122)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminarMouseClicked
        eliminar();
    }//GEN-LAST:event_btn_eliminarMouseClicked

    private void btn_editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editarMouseClicked
        editar();
    }//GEN-LAST:event_btn_editarMouseClicked

    private void tbl_notasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_notasMouseClicked
        DefaultTableModel model = (DefaultTableModel)tbl_notas.getModel();
        int selectedRowIndex = tbl_notas.getSelectedRow();
        btn_editar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        txt_nombre.setText(model.getValueAt(selectedRowIndex, 1).toString());
        txt_apellido.setText(model.getValueAt(selectedRowIndex, 2).toString());
        txt_nota1.setText(model.getValueAt(selectedRowIndex, 3).toString());
        txt_nota2.setText(model.getValueAt(selectedRowIndex, 4).toString());
        txt_nota3.setText(model.getValueAt(selectedRowIndex, 5).toString());
        txt_nota4.setText(model.getValueAt(selectedRowIndex, 6).toString());
        
    }//GEN-LAST:event_tbl_notasMouseClicked

    private void btn_nuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nuevoMouseClicked
        nuevo();
    }//GEN-LAST:event_btn_nuevoMouseClicked

    private void tbl_notasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_notasMouseExited
       //desactivarControles();
    }//GEN-LAST:event_tbl_notasMouseExited

    private void txt_nota1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nota1KeyPressed
       keyPressed(evt, txt_nota1);
    }//GEN-LAST:event_txt_nota1KeyPressed

    private void txt_nota2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nota2KeyPressed
        keyPressed(evt, txt_nota2);
    }//GEN-LAST:event_txt_nota2KeyPressed

    private void txt_nota3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nota3KeyPressed
        keyPressed(evt, txt_nota3);
    }//GEN-LAST:event_txt_nota3KeyPressed

    private void txt_nota4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nota4KeyPressed
        keyPressed(evt, txt_nota4);
    }//GEN-LAST:event_txt_nota4KeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_notas;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_nota1;
    private javax.swing.JTextField txt_nota2;
    private javax.swing.JTextField txt_nota3;
    private javax.swing.JTextField txt_nota4;
    // End of variables declaration//GEN-END:variables
}
