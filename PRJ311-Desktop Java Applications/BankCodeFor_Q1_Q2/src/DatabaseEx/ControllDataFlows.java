/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseEx;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MTC
 */
public class ControllDataFlows {

    DataAccessObject dao;
    DefaultTableModel dftm = new DefaultTableModel();
    jdbcFormDemo form;
    HashSet hs = new HashSet();
    HashMap hm = new HashMap();

    public ControllDataFlows(jdbcFormDemo form) {
        dao = new DataAccessObject(new DBContext());
        this.form = form;
    }

    public void control() {
        loadData();
    }

    public void loadData() {
        try {
            String sql = "SELECT        StdID, StdName,DepartName,Gender,Age\n"
                    + "FROM            DepartInfo INNER JOIN\n"
                    + "                         StudentInfo ON DepartInfo.DepartID = StudentInfo.DepartmentID";
            ResultSet rs = dao.getPS(sql).executeQuery();
            Object[] title = {"StdID", "StdName", "DepartName", "Gender", "Age"};
            form.getCbbColumnSearch().setModel(new DefaultComboBoxModel(title));
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
            form.getjTable1().setModel(dftm);
        } catch (Exception ex) {
            System.out.println("Error while connect to Db " + ex.getMessage());
        }
    }

    public void delete() {
        try {
            String sql = "Delete StudentInfo where stdID = ?";
            PreparedStatement ps = dao.getPS(sql);
            if (form.getTxtStdID().getText().length() > 0) {
                ps.setString(1, form.getTxtStdID().getText());
                ps.executeUpdate();
                loadData();
            }
        } catch (Exception ex) {
            System.out.println("Error while connect to Db " + ex.getMessage());
        }
    }

    public void update() {
        try {
            PreparedStatement ps;
            String sql = "Update StudentInfo set StdName = ?,DepartmentID = ?,Gender = ?, Age = ? where StdID = ?";
            ps = dao.getPS(sql);
            ps.setString(1, form.getTxtStdName().getText());
            ps.setString(2, hm.get(form.getCbbDepartment().getSelectedItem()).toString());
            ps.setBoolean(3, form.getCbxGender().isSelected());
            ps.setInt(4, form.getSlbAge().getValue());
            ps.setString(5, form.getTxtStdID().getText());
            ps.executeUpdate();
            loadData();
        } catch (Exception ex) {

        }
    }

    public void insert() {
        try {
            PreparedStatement ps;
            String sql = "Insert into StudentInfo values (?, ?, ?, ?, ?)";
            ps = dao.getPS(sql);
            ps.setString(1, form.getTxtStdID().getText());
            ps.setString(2, form.getTxtStdName().getText());
            ps.setString(4, form.getCbxGender().getText().equals(true) ? "1" : "0");
            ps.setString(5, form.getSlbAge().getValue() + "");
            ps.setString(3, hm.get(form.getCbbDepartment().getSelectedItem()).toString());
            ps.executeUpdate();
            loadData();
        } catch (Exception ex) {

        }
    }

    DefaultTableModel dftm2;
    public void search() {
        String searchText = form.getTxtSearch().getText();
        if (form.getCbxSearchAll().isSelected()) {
            String sql = "Select s.StdID, s.StdName, d.DepartName, s.Gender, s.Age from StudentInfo s, DepartInfo d where "
                    + "s.DepartmentID = d.DepartID AND "
                    + "( s.StdID LIKE '%" + searchText + "%' OR s.StdName LIKE '%" + searchText + "%' OR "
                    + "d.DepartName LIKE '%" + searchText + "%' OR s.Gender LIKE '%" + searchText + "%' OR s.Age LIKE '%" + searchText + "%');";
            initTable(sql, dftm2, form.getTblResult());
        } else {
            String columnSelected = form.getCbbColumnSearch().getSelectedItem().toString();
            String sql = "Select s.StdID, s.StdName, d.DepartName, s.Gender, s.Age from StudentInfo s, DepartInfo d where "
                    + "s.DepartmentID = d.DepartID AND "
                    + columnSelected + " LIKE '%" + searchText + "%';";
            initTable(sql, dftm2, form.getTblResult());
        }
    }

    public void initTable(String sql, DefaultTableModel dftm, JTable table) {
        try {
            ResultSet rs = dao.getData(sql);
            Object[] title = {"StdID", "StdName", "DepartName", "Gender", "Age"};
            dftm.setColumnIdentifiers(title);
            dftm.setRowCount(0);
            while (rs.next()) {
                Vector vec = new Vector();
                vec.add(rs.getString(1));
                vec.add(rs.getString(2));
                vec.add(rs.getString(3));
                vec.add(rs.getBoolean(4));
                vec.add(rs.getInt(5));
                dftm.addRow(vec);
            }
            table.setModel(dftm);
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
