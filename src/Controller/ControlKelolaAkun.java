/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import View.KelolaAkun;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS PC
 */
public class ControlKelolaAkun implements ActionListener {

    private KelolaAkun kelolaakun;
    private Customer customer;
    
    ResultSet rs;
    Connection conn;
    Statement stat;

    public ControlKelolaAkun() throws SQLException {
        kelolaakun = new KelolaAkun();
        kelolaakun.setVisible(true);
        kelolaakun.addListener(this);
        customer = new Customer();
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        String query = "select * from customer";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            kelolaakun.getComboBoxNama().addItem(rs.getString("nama"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == kelolaakun.getBtnHapus()) {
            try {
                String nama = kelolaakun.getComboBoxNama().getSelectedItem().toString();
                customer.deleteCustomer(nama);
                new ControlMenuAdmin();
                kelolaakun.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolaakun.getBtnLogout()) {
            try {
                new ControlLoginAdmin();
                kelolaakun.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolaakun.getBtnTambah()) {
            try {
                Customer customer = new Customer();
                customer.setNama(kelolaakun.getTeksNama().getText());
                customer.setUsername(kelolaakun.getTeksUsername().getText());
                customer.setPassword(kelolaakun.getTeksPassword().getText());
                customer.addCustomer();
                new ControlMenuAdmin();
                kelolaakun.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolaakun.getBtnKelolaAkun()) {

        } else if (event == kelolaakun.getBtnKelolaWisata()) {
            try {
                new ControlKelolaPaketWisata();
                kelolaakun.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaAkun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
