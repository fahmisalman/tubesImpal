/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Lokasi;
import Model.Penginapan;
import Model.TempatWisata;
import View.KelolaPaketWisata;
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
 * Ï
 *
 * @author ASUS PC
 */
public class ControlKelolaPaketWisata implements ActionListener {

    private KelolaPaketWisata kelolapaketwisata;
    private Lokasi l;
    
    ResultSet rs;
    Connection conn;
    Statement stat;

    public ControlKelolaPaketWisata() throws SQLException {
        kelolapaketwisata = new KelolaPaketWisata();
        kelolapaketwisata.setVisible(true);
        kelolapaketwisata.addListener(this);    
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        String query = "select * from lokasi";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            kelolapaketwisata.getComboNamaLokasi().addItem(rs.getString("namaLokasi"));
            kelolapaketwisata.getComboLokasi().addItem(rs.getString("namaLokasi"));
        }
        query = "select * from penginapan";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            kelolapaketwisata.getComboPenginapan().addItem(rs.getString("namaPenginapan"));
        }
        query = "select * from tempatWisata";
        rs = stat.executeQuery(query);
        while (rs.next()) {
            kelolapaketwisata.getComboWisata().addItem(rs.getString("namaWisata"));
        }
        l = new Lokasi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == kelolapaketwisata.getBtnHapusLokasi()) {
            try {
                l.deleteLokasi(kelolapaketwisata.getComboNamaLokasi().getSelectedItem().toString());
                new ControlMenuAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnHapusPenginapan()) {
            try {
                l.deletePenginapan(kelolapaketwisata.getComboPenginapan().getSelectedItem().toString());
                new ControlMenuAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnHapusWisata()) {
            try {
                l.deleteWisata(kelolapaketwisata.getComboWisata().getSelectedItem().toString());
                new ControlMenuAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnKelolaAkun()) {
            try {
                new ControlKelolaAkun();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnLogout()) {
            try {
                new ControlLoginAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnTambahLokasi()) {
            try {
                l.setNamaLokasi(kelolapaketwisata.getTeksNamaLokasi().getText());
                l.addLokasi(l);
                new ControlMenuAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnTambahPenginapan()) {
            try {
                l.setNamaLokasi(kelolapaketwisata.getComboLokasi().getSelectedItem().toString());
                Penginapan p = new Penginapan();
                p.setNamaPenginapan(kelolapaketwisata.getTeksNamaPenginapan().getText());
                p.setFasilitas(kelolapaketwisata.getTeksFasilitasPenginapan().getText());
                p.setHarga(Integer.parseInt(kelolapaketwisata.getTeksHargaPenginapan().getText()));
                p.setKapasitas(Integer.parseInt(kelolapaketwisata.getTeksKapasitasPenginapan().getText()));
                p.setStatus(kelolapaketwisata.getComboStatus().getSelectedItem().toString());
                l.setPenginapan(p);
                l.addPenginapan(p);
                new ControlMenuAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == kelolapaketwisata.getBtnTambahWisata()) {
            try {
                l.setNamaLokasi(kelolapaketwisata.getComboLokasi().getSelectedItem().toString());
                TempatWisata wisata = new TempatWisata();
                wisata.setNamaWisata(kelolapaketwisata.getTeksNamaWisata().getText());
                wisata.setFasilitas(kelolapaketwisata.getTeksFasilitasWisata().getText());
                wisata.setHarga(Integer.parseInt(kelolapaketwisata.getTeksHargaWisata().getText()));
                l.setWisata(wisata);
                l.addWisata(wisata);
                new ControlMenuAdmin();
                kelolapaketwisata.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(ControlKelolaPaketWisata.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
