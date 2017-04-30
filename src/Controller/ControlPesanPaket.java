/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Lokasi;
import Model.Paket;
import Model.Penginapan;
import Model.TempatWisata;
import View.PesanPaket;
import View.PesanPaket2;
import View.PesanPaket3;
import View.PesanPaket4;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author fahminurfikri
 */
public class ControlPesanPaket implements ActionListener{
    
    PesanPaket pesanPaket;
    PesanPaket2 pesanPaket2;
    PesanPaket3 pesanPaket3;
    PesanPaket4 pesanPaket4;
    Paket paket;
    Lokasi lokasi;
    Penginapan penginapan;
    TempatWisata tempatWisata;
    
    String query, query2, query3, detailWisata, detailPenginapan;
    ResultSet rs;
    Connection conn;
    Statement stat;
    int temp;
    
    
    public ControlPesanPaket() throws SQLException {
        pesanPaket = new PesanPaket();
        pesanPaket.setVisible(true);
        pesanPaket.addListener(this);
        pesanPaket2 = new PesanPaket2();
        pesanPaket2.addListener(this);
        pesanPaket2.setVisible(false);
        pesanPaket3 = new PesanPaket3();
        pesanPaket3.addListener(this);
        pesanPaket3.setVisible(false);
        pesanPaket4 = new PesanPaket4();
        pesanPaket4.setVisible(false);
        pesanPaket4.addListener(this);
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        query = "select * from lokasi";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            pesanPaket.getComboLokasi().addItem(rs.getString("namaLokasi"));
        }
        paket = new Paket();
        lokasi = new Lokasi();
        penginapan = new Penginapan();
        tempatWisata = new TempatWisata();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        
        // Pesan Paket 1
        if (event == pesanPaket.getBtnPesan()) {
            try {
                new ControlPesanPaket();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
            pesanPaket.dispose();
        } else if(event == pesanPaket.getBtnPembayaran()) {
            try {
                new ControlPembayaran();
                pesanPaket.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket.getBtnBatalPesan()) {
            try {
                new ControlBatalPesan();
                pesanPaket.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket.getBtnLogout()) {
            try {
                new ControlLoginMember();
                pesanPaket.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket.getBtnSubmit()) {
            try {
                if (pesanPaket.getTeksJumlahOrang().getText() != "" && pesanPaket.getTeksJumlahOrang().getText() != "0" && pesanPaket.getTextJumlahHari().getText() != "" && pesanPaket.getTextJumlahHari().getText() != "0") {
                    pesanPaket2.setVisible(true);
                    query2 = "select * from lokasi join TempatWisata using (idLokasi) where namaLokasi = '" + pesanPaket.getComboLokasi().getSelectedItem().toString() + "'";
                    rs = stat.executeQuery(query2);
                    while (rs.next()) {
                        pesanPaket2.getComboLokasi().addItem(rs.getString("namaWisata"));
                    }
                    query3 = "select * from lokasi join Penginapan using (idLokasi) where namaLokasi = '" + pesanPaket.getComboLokasi().getSelectedItem().toString() + "'";
                    rs = stat.executeQuery(query3);
                    while (rs.next()) {
                        pesanPaket3.getComboPenginapan().addItem(rs.getString("namaPenginapan"));
                    }
                    pesanPaket.setVisible(false);
                    paket.setTanggal(pesanPaket.getTeksTanggal().toString());
                    if (pesanPaket.getTeksJumlahOrang().getText() != "0" && pesanPaket.getTextJumlahHari().getText() != "0") {
                        paket.setJumahOrang(Integer.parseInt(pesanPaket.getTeksJumlahOrang().getText()));
                        paket.setJumlahHari(Integer.parseInt(pesanPaket.getTextJumlahHari().getText()));
                        paket.getIdLokasi(pesanPaket.getComboLokasi().getSelectedItem().toString());
                        paket.setTanggal(pesanPaket.getTeksTanggal().getText());
                    }
                } else {
                    JOptionPane.showMessageDialog(pesanPaket, "Masukan salah", "error", JOptionPane.ERROR_MESSAGE);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket.getBtnDaftarTransaksi()) {
            try {
                new ControlDaftarTransaksi();
                pesanPaket.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket.getComboLokasi()) {
//            pesanPaket.getTeksJumlahOrang().setText(pesanPaket.getComboLokasi().getSelectedItem().toString());
        }
        
        // Pesan Paket 2
        if (event == pesanPaket2.getBtnPesan()) {
            try {
                new ControlPesanPaket();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
            pesanPaket2.dispose();
        } else if(event == pesanPaket2.getBtnPembayaran()) {
            try {
                new ControlPembayaran();
                pesanPaket2.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket2.getBtnBatalPesan()) {
            try {
                new ControlBatalPesan();
                pesanPaket2.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket2.getBtnLogout()) {
            try {
                new ControlLoginMember();
                pesanPaket2.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket2.getBtnDaftarTransaksi()) {
            try {
                new ControlDaftarTransaksi();
                pesanPaket2.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket2.getBtnLanjut()) {
            pesanPaket3.setVisible(true);
            pesanPaket2.setVisible(false);
        } else if (event == pesanPaket2.getBtnKembali()){
            pesanPaket2.setVisible(false);
            pesanPaket.setVisible(true);
        } else if (event == pesanPaket2.getComboLokasi()) {
            try {
                paket.getIdWisata(pesanPaket2.getComboLokasi().getSelectedItem().toString());
                pesanPaket2.getTeksDetailWisata().setText(paket.getTempatWisata().getFasilitas());
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pesan Paket 3
        if (event == pesanPaket3.getBtnPesan1()) {
            try {
                new ControlPesanPaket();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
            pesanPaket3.dispose();
        } else if(event == pesanPaket3.getBtnPembayaran1()) {
            try {
                new ControlPembayaran();
                pesanPaket3.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket3.getBtnBatalPesan1()) {
            try {
                new ControlBatalPesan();
                pesanPaket3.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket3.getBtnLogout()) {
            try {
                new ControlLoginMember();
                pesanPaket3.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket3.getBtnDaftarTransaksi1()) {
            try {
                new ControlDaftarTransaksi();
                pesanPaket3.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket3.getBtnLanjut()) {
            pesanPaket4.setVisible(true);
            pesanPaket3.setVisible(false);
            pesanPaket4.getTeksLokasi().setText(paket.getLokasi().getNamaLokasi());
            pesanPaket4.getTeksPenginapan().setText(paket.getPenginapan().getNamaPenginapan());
            pesanPaket4.getTeksWisata().setText(paket.getTempatWisata().getNamaWisata());
            pesanPaket4.getTeksJml().setText(String.valueOf(paket.getJumlahHari() + " / " + paket.getJumahOrang()));
            pesanPaket4.getTeksTglBerangkat().setText(paket.getTanggal());
        } else if (event == pesanPaket3.getBtnKembali()){
            pesanPaket3.setVisible(false);
            pesanPaket2.setVisible(true);
        } else if (event == pesanPaket3.getComboPenginapan()) {
            try {
                paket.getIdPenginapan(pesanPaket3.getComboPenginapan().getSelectedItem().toString());
                pesanPaket3.getTeksDetailPenginapan().setText(paket.getPenginapan().getFasilitas());
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Pesan Paket 4
        if (event == pesanPaket4.getBtnPesan1()) {
            try {
                new ControlPesanPaket();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
            pesanPaket4.dispose();
        } else if(event == pesanPaket4.getBtnPembayaran1()) {
            try {
                new ControlPembayaran();
                pesanPaket4.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket4.getBtnBatalPesan1()) {
            try {
                new ControlBatalPesan();
                pesanPaket4.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket4.getBtnLogout1()) {
            try {
                new ControlLoginMember();
                pesanPaket4.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket4.getBtnDaftarTransaksi1()) {
            try {
                new ControlDaftarTransaksi();
                pesanPaket4.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket4.getBtnSubmit1()) {
            try {
                paket.addPaket();
                new ControlMenuMember();
                pesanPaket4.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlPesanPaket.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == pesanPaket4.getBtnKembali()){
            pesanPaket4.setVisible(false);
            pesanPaket3.setVisible(true);
        }
        
    }
    
}
