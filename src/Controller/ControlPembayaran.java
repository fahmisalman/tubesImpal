/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import Model.Paket;
import View.Pembayaran;
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
 * @author fahminurfikri
 */
public class ControlPembayaran implements ActionListener{
    Pembayaran pembayaran;
    Customer customer;
    Paket paket;
    int id;
    
    ResultSet rs;
    Connection conn;
    Statement stat;
    
    public ControlPembayaran() throws SQLException {
        pembayaran = new Pembayaran();
        pembayaran.setVisible(true);
        pembayaran.addListener(this);
        paket = new Paket();
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        customer = new Customer();
        String query = "select * from transaksi join paket using (idPaket) join lokasi using (idLokasi) where transaksi.idCustomer = '" + customer.getIdCustomer() + "' and status = 'tidak lunas'";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            pembayaran.getComboPaket().addItem(rs.getString("namaLokasi"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == pembayaran.getBtnPesan()) {
            try {
                new ControlPesanPaket();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(event == pembayaran.getBtnPembayaran()) {
            try {
                new ControlPembayaran();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pembayaran.getBtnBatalPesan()) {
            try {
                new ControlBatalPesan();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pembayaran.getBtnLogout()) {
            try {
                new ControlLoginMember();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pembayaran.getBtnDaftarTransaksi()) {
            try {
                new ControlDaftarTransaksi();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pembayaran.getBtnSubmit()) {
            try {
                paket.updateTransaksi(id);
                new ControlMenuMember();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pembayaran.getComboPaket()) {
            try {
                String query = "select * from transaksi join paket using (idPaket) join lokasi using (idLokasi) where transaksi.idCustomer = '" + customer.getIdCustomer() + "' and status = 'tidak lunas'";
                rs = stat.executeQuery(query);
                while (rs.next()) {
                    pembayaran.getTeksTotalBiaya().setText(rs.getString("transaksi.harga"));
                    id = rs.getInt("idTransaksi");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControlPembayaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
