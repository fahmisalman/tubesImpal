/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import View.DaftarTransaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fahminurfikri
 */
public class ControlDaftarTransaksi implements ActionListener{

    private DaftarTransaksi daftarTransaksi;
    private Customer customer;
    
    private Connection conn;
    private Statement stat;
    private String query;
    private ResultSet rs;
    
    public ControlDaftarTransaksi() throws SQLException {
        daftarTransaksi = new DaftarTransaksi();
        daftarTransaksi.setVisible(true);
        daftarTransaksi.addListener(this);
        customer = new Customer();
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        query = "select * from transaksi join paket using (idPaket) join lokasi using (idLokasi) where transaksi.idCustomer = '" + customer.getIdCustomer() + "'";
        rs = stat.executeQuery(query);
        int i = 0;
        while (rs.next()) {
            daftarTransaksi.getTableTransaksi().setValueAt(rs.getInt("idTransaksi"), i, 0);
            daftarTransaksi.getTableTransaksi().setValueAt(rs.getString("namaLokasi"), i, 1);
            daftarTransaksi.getTableTransaksi().setValueAt(rs.getString("tanggal"), i, 2);
            daftarTransaksi.getTableTransaksi().setValueAt(rs.getString("status"), i, 3);
            i++;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == daftarTransaksi.getBtnKembali()) {
            new ControlMenuMember();
            daftarTransaksi.dispose();
        }
    }
    
}
