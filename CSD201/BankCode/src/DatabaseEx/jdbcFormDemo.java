/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseEx;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MTC
 */
public class jdbcFormDemo extends javax.swing.JFrame {

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JButton getBtnLoad() {
        return btnLoad;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JComboBox<String> getCbbColumnSearch() {
        return cbbColumnSearch;
    }

    public JComboBox<String> getCbbDepartment() {
        return cbbDepartment;
    }

    public JCheckBox getCbxGender() {
        return cbxGender;
    }

    public JCheckBox getCbxSearchAll() {
        return cbxSearchAll;
    }

    public JTable getjTable1() {
        return jTable1;
    }

    public JSlider getSlbAge() {
        return slbAge;
    }

    public JTable getTblResult() {
        return tblResult;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JTextField getTxtStdID() {
        return txtStdID;
    }

    public JTextField getTxtStdName() {
        return txtStdName;
    }
    
    
    
    public static int count = 0;
    /**
     * Creates new form jdbcFormDemo
     */
    DBContext db;
    HashSet hs = new HashSet();
    DefaultTableModel dftm;
    DefaultTableModel dftm2;
    HashMap hm = new HashMap();
    
    public jdbcFormDemo() {
        initComponents();
        db = new DBContext();
        dftm = new DefaultTableModel();
        dftm2 = new DefaultTableModel();
        Action();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnLoad = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtStdID = new javax.swing.JTextField();
        txtStdName = new javax.swing.JTextField();
        cbbDepartment = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxGender = new javax.swing.JCheckBox();
        slbAge = new javax.swing.JSlider();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResult = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cbxSearchAll = new javax.swing.JCheckBox();
        cbbColumnSearch = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        jLabel1.setText("Student ID");

        jLabel2.setText("Student Name");

        jLabel3.setText("Department");

        jLabel4.setText("Age");

        jLabel5.setText("Gender");

        cbxGender.setText("Male");

        slbAge.setMajorTickSpacing(20);
        slbAge.setMinorTickSpacing(10);
        slbAge.setPaintLabels(true);
        slbAge.setPaintTicks(true);
        slbAge.setValue(20);
        slbAge.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        slbAge.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slbAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxGender, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbbDepartment, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtStdID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtStdName))
                        .addGap(22, 22, 22))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtStdID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtStdName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxGender))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addComponent(slbAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tblResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblResult);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbxSearchAll.setText("Search All");

        jLabel6.setText("Column");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnUpdate)
                        .addGap(52, 52, 52)
                        .addComponent(btnLoad)
                        .addGap(47, 47, 47)
                        .addComponent(btnDelete)
                        .addGap(134, 134, 134))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
            .addGroup(layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbColumnSearch, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnSearch)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxSearchAll)
                    .addComponent(jLabel6))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLoad)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(cbxSearchAll))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbColumnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        try {
            Connection con = db.getConnection();
            System.out.println("hello2");
            String sql = "SELECT        StdID, StdName,DepartName,Gender,Age\n"
                    + "FROM            DepartInfo INNER JOIN\n"
                    + "                         StudentInfo ON DepartInfo.DepartID = StudentInfo.DepartmentID";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Object[] title = {"StdID", "StdName", "DepartName", "Gender", "Age"};
            cbbColumnSearch.setModel(new DefaultComboBoxModel(title));
            dftm.setColumnIdentifiers(title);
            dftm.setRowCount(0);
            hs.clear();
            
            while (rs.next()) {
                Vector vec = new Vector();
                vec.add(rs.getString(1));
                hs.add(rs.getString(1));
                vec.add(rs.getString(2));
                vec.add(rs.getString(3));
                vec.add(rs.getBoolean(4));
                vec.add(rs.getInt(5));
                dftm.addRow(vec);
            }
            jTable1.setModel(dftm);
        } catch (Exception ex) {
            System.out.println("Error while connect to Db " + ex.getMessage());
        }
        loadDepartment();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            Connection con = db.getConnection();
            String sql = "Delete StudentInfo where stdID = ?";
            PreparedStatement ps = con.prepareStatement(sql);            
            if (txtStdID.getText().length()>0){
                ps.setString(1, txtStdID.getText());
                ps.executeUpdate();
                btnLoad.doClick();
            }
        } catch (Exception ex) {
            Logger.getLogger(jdbcFormDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            Connection con = db.getConnection();
            PreparedStatement ps;
            if (hs.contains(txtStdID.getText())){
                String sql = "Update StudentInfo set StdName = ?,DepartmentID = ?,Gender = ?, Age = ? where StdID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, txtStdName.getText());
                ps.setString(2, hm.get(cbbDepartment.getSelectedItem()).toString());
                ps.setBoolean(3, cbxGender.isSelected());
                ps.setInt(4, slbAge.getValue());
                ps.setString(5, txtStdID.getText());
                ps.executeUpdate();
                btnLoad.doClick();
                
            } else {
                String sql = "Insert into StudentInfo values (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, txtStdID.getText());
                ps.setString(2, txtStdName.getText());
                ps.setString(4, cbxGender.getText().equals(true)?"1":"0");
                ps.setString(5,slbAge.getValue()+"");
                ps.setString(3, hm.get(cbbDepartment.getSelectedItem()).toString());
                ps.executeUpdate();
                btnLoad.doClick();
            }
        } catch (Exception ex) {
            Logger.getLogger(jdbcFormDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchText = txtSearch.getText();
        try {
            Connection con = db.getConnection();
            if (cbxSearchAll.isSelected()){
                String sql = "Select s.StdID, s.StdName, d.DepartName, s.Gender, s.Age from StudentInfo s, DepartInfo d where "
                                + "s.DepartmentID = d.DepartID AND "
                                + "( s.StdID LIKE '%"+searchText+"%' OR s.StdName LIKE '%"+searchText+"%' OR "
                                + "d.DepartName LIKE '%"+searchText+"%' OR s.Gender LIKE '%"+searchText+"%' OR s.Age LIKE '%"+searchText+"%');";
                
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                Object[] title = {"StdID", "StdName", "DepartName", "Gender", "Age"};
                dftm2.setColumnIdentifiers(title);
                dftm2.setRowCount(0);
                
                while (rs.next()) {
                    Vector vec = new Vector();
                    vec.add(rs.getString(1));
                    vec.add(rs.getString(2));
                    vec.add(rs.getString(3));
                    vec.add(rs.getBoolean(4));
                    vec.add(rs.getInt(5));
                    dftm2.addRow(vec);
                }
                tblResult.setModel(dftm2);
            } else {
                 String columnSelected = cbbColumnSearch.getSelectedItem().toString();
                        String sql = "Select s.StdID, s.StdName, d.DepartName, s.Gender, s.Age from StudentInfo s, DepartInfo d where "
                                + "s.DepartmentID = d.DepartID AND "
                                + columnSelected +" LIKE '%"+searchText+"%';";
                        PreparedStatement ps = con.prepareStatement(sql);                      
                        ResultSet rs = ps.executeQuery();
                        Object[] title =  {"StdID", "StdName", "DepartName", "Gender", "Age"};
                        dftm2.setColumnIdentifiers(title);
                        dftm2.setRowCount(0);

                        while (rs.next()) {
                            Vector vec = new Vector();
                            vec.add(rs.getString(1));
                            vec.add(rs.getString(2));
                            vec.add(rs.getString(3));
                            vec.add(rs.getBoolean(4));
                            vec.add(rs.getInt(5));
                            dftm2.addRow(vec);
                        }
                        tblResult.setModel(dftm2);
            }
        } catch (Exception ex) {
            Logger.getLogger(jdbcFormDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void Action() {
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int n = jTable1.getSelectedRow();
                if (n >= 0) {
                    txtStdID.setText(jTable1.getValueAt(n, 0).toString());
                    txtStdName.setText(jTable1.getValueAt(n, 1).toString());
                    cbbDepartment.setSelectedItem(jTable1.getValueAt(n, 2));
                    cbxGender.setSelected((boolean)jTable1.getValueAt(n, 3));
                    slbAge.setValue(Integer.parseInt(jTable1.getValueAt(n, 4).toString()));
                }
            }
        });
    }

    private void loadDepartment() {
        cbbDepartment.removeAllItems();
        try {
            Connection con = db.getConnection();
            String sql = "Select * from DepartInfo";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cbbDepartment.addItem(rs.getString(2));
                hm.put(rs.getString(2), rs.getString(1));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(jdbcFormDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jdbcFormDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jdbcFormDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jdbcFormDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jdbcFormDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbColumnSearch;
    private javax.swing.JComboBox<String> cbbDepartment;
    private javax.swing.JCheckBox cbxGender;
    private javax.swing.JCheckBox cbxSearchAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JSlider slbAge;
    private javax.swing.JTable tblResult;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStdID;
    private javax.swing.JTextField txtStdName;
    // End of variables declaration//GEN-END:variables
}

//Ex1: chuyen nam nu --> radio box
//Ex2: display Male, Female thay vi true, false
//Ex3: List select song song vs cbb
//Ex4: lam bang controller

//Ex tach 2 nut insert update
//neu co trong thi update enable true caretupdate

//id name department cbx, select where 
