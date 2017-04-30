/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import Model.Paket;
import View.BatalPesanan;
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
public class ControlBatalPesan implements ActionListener{

    BatalPesanan batalPesanan;
    Customer customer;
    Paket paket;
    int id = 0;
    
    String query;
    ResultSet rs;
    Connection conn;
    Statement stat;
    
    public ControlBatalPesan() throws SQLException {
        batalPesanan = new BatalPesanan();
        batalPesanan.setVisible(true);
        batalPesanan.addListener(this);
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        customer = new Customer();
        query = "select * from paket join lokasi using (idLokasi) where idCustomer = '" + customer.getIdCustomer() + "'";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            batalPesanan.getComboPaket().addItem(rs.getString("namaLokasi"));
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == batalPesanan.getBtnPesan()) {
            try {
                new ControlPesanPaket();
                batalPesanan.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(event == batalPesanan.getBtnPembayaran()) {
            try {
                new ControlPembayaran();
                batalPesanan.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == batalPesanan.getBtnBatalPesan()) {
            try {
                new ControlBatalPesan();
                batalPesanan.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == batalPesanan.getBtnLogout()) {
            try {
                new ControlLoginMember();
                batalPesanan.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == batalPesanan.getBtnBatal()) {
            try {
                paket = new Paket();
                if (id != 0) {
                    paket.deletePaket(id);
                    new ControlMenuMember();
                    batalPesanan.dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == batalPesanan.getBtnDaftarTransaksi()) {
            try {
                new ControlDaftarTransaksi();
                batalPesanan.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == batalPesanan.getComboPaket()) {
            try {
                int idP = 0, idT = 0;
                query = "select * from paket join lokasi using (idLokasi) where idCustomer = '" + customer.getIdCustomer() + "' and namaLokasi = '" + batalPesanan.getComboPaket().getSelectedItem().toString() + "'";
                rs = stat.executeQuery(query);
                while (rs.next()) {
                    id = rs.getInt("idPaket");
                    idP = rs.getInt("idPenginapan");
                    idT = rs.getInt("idTempatWisata");
                }
                System.out.println(id);
                query = "select * from penginapan where idPenginapan = '" + idP + "'";
                rs = stat.executeQuery(query);
                while (rs.next()) {
                    batalPesanan.getTeksPenginapan().setText(rs.getString("namaPenginapan"));
                }
                query = "select * from tempatWisata where idWisata = '" + idT + "'";
                rs = stat.executeQuery(query);
                while (rs.next()) {
                    batalPesanan.getTeksWisata().setText(rs.getString("namaWisata"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControlBatalPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
