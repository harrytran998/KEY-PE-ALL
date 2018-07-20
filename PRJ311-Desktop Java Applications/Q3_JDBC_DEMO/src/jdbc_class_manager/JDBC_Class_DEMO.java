/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_class_manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DemonsLight
 */
class Dao {

    DBContext2 db;
    Connection con;

    public Dao(DBContext2 db, Connection con) {
        this.db = db;
        try {
            con = db.getConnection();
        } catch (Exception e) {
        }
        this.con = con;
    }

    public ResultSet getData(String sql) throws SQLException {
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    }

    public PreparedStatement getPs(String sql) throws SQLException {
        return con.prepareStatement(sql);
    }
}

public class JDBC_Class_DEMO {

    Demo2 demo;
    DefaultTableModel dftm;
    DefaultTableModel dftmSearch;
    Dao dao;
    HashSet<String> hs;
    HashMap<String, String> hm;

    public static void main(String[] args) {
        Connection con = null;
        JDBC_Class_DEMO x = new JDBC_Class_DEMO(new Demo2(), new Dao(new DBContext2(), con));
        x.handlingTable();
    }

    public JDBC_Class_DEMO(Demo2 demo, Dao dao) {
        this.demo = demo;
        this.dao = dao;
    }

    public void handlingTable() {

        demo.setVisible(true);
        hs = new HashSet<>();
        hm = new HashMap<>();
        dftm = new DefaultTableModel();
        dftmSearch = new DefaultTableModel();

        demo.getBtnLoad().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStuInfoToTable(demo.getTable(), dftm);
                loadDepartmen();
                loadDepartmentToList();
            }
        });
        demo.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE dbo.StudentInfo WHERE ID = ?";
                try {
                    delete(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(JDBC_Class_DEMO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        demo.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "INSERT dbo.StudentInfo\n"
                        + "( ID,NameStu, DepartID, Gender,Age) VALUES(?,?,?,?,?)";
                try {
                    insert(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(JDBC_Class_DEMO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        demo.getBtnUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "UPDATE dbo.StudentInfo SET "
                        + "NameStu = ?, DepartID = ?, Gender = ?, Age = ?\n"
                        + "WHERE ID  = ?";
                try {
                    update(sql);
                } catch (SQLException ex) {
                    Logger.getLogger(JDBC_Class_DEMO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        demo.getChbxSearchAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStuInfoToTable(demo.getTblSearch(), dftmSearch);
            }
        });

        demo.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "";
                try {
                    if (demo.getChbxSearchAll().isSelected()) {
                        sql = "SELECT s.ID, s.NameStu, d.NameDepart, s.Gender, s.Age FROM dbo.Department d JOIN dbo.StudentInfo s\n"
                                + "ON s.DepartID = d.ID\n"
                                + "AND (s.NameStu LIKE '%" + demo.getTfSearch().getText() + "%'\n"
                                + "OR d.NameDepart LIKE '%" + demo.getTfSearch().getText() + "%'\n"
                                + "OR s.Gender LIKE '%" + demo.getTfSearch().getText() + "%'\n"
                                + "OR s.Age LIKE '%" + demo.getTfSearch().getText() + "%')";
                    } else {
                        if (demo.getCbProperties().getSelectedItem().toString().equalsIgnoreCase("NameDepart")) {
                            sql = "SELECT s.ID, s.NameStu, s.NameDepart, s.Gender, s.Age FROM dbo.Department d JOIN dbo.StudentInfo s\n"
                                    + "ON s.DepartID = d.ID\n"
                                    + "AND d." + demo.getCbProperties().getSelectedItem().toString()
                                    + " LIKE '%" + demo.getTfSearch().getText() + "%'";
                        } else {
                            sql = "SELECT s.ID, s.NameStu, s.DepartID, s.Gender, s.Age FROM dbo.Department d JOIN dbo.StudentInfo s\n"
                                    + "ON s.DepartID = d.ID\n"
                                    + "AND s." + demo.getCbProperties().getSelectedItem().toString()
                                    + " LIKE '%" + demo.getTfSearch().getText() + "%'";
                        }
                    }
                    initTableSQL(sql, dftmSearch, demo.getTblSearch());
                } catch (Exception ex) {
                }
            }
        });
    }

    public void delete(String sql) throws SQLException {
        PreparedStatement ps = dao.getPs(sql);
        if (demo.getTfID().getText().length() > 0) {
            ps.setString(1, demo.getTfID().getText());
            ps.executeUpdate();
            loadStuInfoToTable(demo.getTable(), dftm);
        }
    }

    public void insert(String sql) throws SQLException {
        PreparedStatement ps;
        if (hs.contains(demo.getTfID().getText())) {

        } else {
            ps = dao.getPs(sql);
            ps.setString(1, demo.getTfID().getText());
            ps.setString(2, demo.getTfName().getText());
            ps.setString(3, hm.get(demo.getCbDepartment().getSelectedItem()));
            if (demo.getChbxMale().isSelected()) {
                ps.setBoolean(4, true);
            } else {
                ps.setBoolean(4, false);
            }
            ps.setInt(5, demo.getSlbAge().getValue());
            ps.executeUpdate();
            loadStuInfoToTable(demo.getTable(), dftm);
        }
    }

    public void update(String sql) throws SQLException {
        PreparedStatement ps;
        if (hs.contains(demo.getTfID().getText())) {
            //do nothing
            ps = dao.getPs(sql);
            ps.setString(1, demo.getTfName().getText());
            ps.setString(2, hm.get(demo.getCbDepartment().getSelectedItem()));
            if (demo.getChbxMale().isSelected()) {
                ps.setBoolean(3, true);
            } else {
                ps.setBoolean(3, false);
            }
            ps.setInt(4, demo.getSlbAge().getValue());
            ps.setString(5, demo.getTfID().getText());
            ps.executeUpdate();
            loadStuInfoToTable(demo.getTable(), dftm);
        }
    }

    public void search(String sql) {
        initTableSQL(sql, dftmSearch, demo.getTblSearch());
    }

    public void initTableSQL(String sql, DefaultTableModel dftm, JTable table) {
        try {
            ResultSet rs = dao.getData(sql);
            Object[] title = {"ID", "Name", "Department Name", "Gender", "Age"};
            dftm.setColumnIdentifiers(title);
            dftm.setRowCount(0);
            hs.clear();
            while (rs.next()) {
                Vector vec = new Vector();
                vec.addElement(rs.getString(1));
                hs.add(rs.getString(1));
                vec.addElement(rs.getString(2));
                vec.addElement(rs.getString(3));
                vec.addElement((rs.getBoolean(4) == true) ? "Female" : "Male");
                vec.addElement(rs.getString(5));
                dftm.addRow(vec);
                table.setModel(dftm);
            }
//            initProperties(rs);
        } catch (Exception es) {
            es.printStackTrace();
        }
    }

    public void initProperties(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberColum = rsmd.getColumnCount();

            for (int i = 1; i <= numberColum; i++) {
                String name = rsmd.getColumnName(i);
                demo.getCbProperties().addItem(name);
            }
        } catch (Exception e) {
        }
    }

    private void loadStuInfoToTable(JTable temp, DefaultTableModel dftmX) {
        ActionPerformed();
        try {
            String sql = "SELECT s.ID, s.NameStu, d.NameDepart, s.Gender, s.Age FROM dbo.Department d JOIN dbo.StudentInfo s\n"
                    + "ON s.DepartID = d.ID";
            initTableSQL(sql, dftmX, temp);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        temp.setModel(dftmX);
    }

    private void loadDepartmen() {
        try {
            String sql = "Select * from Department";
            ResultSet rs = dao.getData(sql);
            demo.getCbDepartment().removeAllItems();
            while (rs.next()) {
                demo.getCbDepartment().addItem(rs.getString(2));
                hm.put(rs.getString(2), rs.getString(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadDepartmentToList() {
        DefaultListModel<String> x = new DefaultListModel<>();
        try {
            String sql = "Select * from Department";
            ResultSet rs = dao.getData(sql);
            while (rs.next()) {
                x.addElement(rs.getString(2));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        demo.getListDepartment().setModel(x);
    }

    private void ActionPerformed() {
        demo.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectRow = demo.getTable().getSelectedRow();
                if (selectRow >= 0) {
                    demo.getBtnDelete().setEnabled(true);
                    demo.getTfID().setText((String) demo.getTable().getValueAt(selectRow, 0));
                    demo.getTfName().setText((String) demo.getTable().getValueAt(selectRow, 1));
                    demo.getSlbAge().setValue(Integer.parseInt((String) demo.getTable().getValueAt(selectRow, 4)));
                    boolean isFermale = ("Female".equals((String) demo.getTable().getValueAt(selectRow, 3)));
                    demo.getChbxFermale().setSelected(isFermale);
                    demo.getChbxMale().setSelected(!isFermale);
                    if (isFermale) {
                        demo.getRbtnFermale().setSelected(true);
                    } else {
                        demo.getRbtnMale().setSelected(true);
                    }
                    demo.getCbDepartment().setSelectedItem((String) demo.getTable().getValueAt(selectRow, 2));
                    demo.getListDepartment().setSelectedValue((String) demo.getTable().getValueAt(selectRow, 2), true);
                } else {
                    demo.getBtnDelete().setEnabled(false);
                }
            }
        });
    }

}
