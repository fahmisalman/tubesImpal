/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ASUS PC
 */
public class TempatWisata {
    private int idWisata;
    private String namaWisata;
    private String fasilitas;
    private int harga;

    private Connection conn;
    private Statement stat;
    private String query;
    
    public TempatWisata() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
    }
    
    public TempatWisata(int id, String nama, String fasilitas, int harga) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        idWisata = id;
        namaWisata = nama;
        this.fasilitas = fasilitas;
        this.harga = harga;
    }
    
    /**
     * @return the idWisata
     */
    public int getIdWisata() {
        return idWisata;
    }

    /**
     * @param idWisata the idWisata to set
     */
    public void setIdWisata(int idWisata) {
        this.idWisata = idWisata;
    }

    /**
     * @return the namaWisata
     */
    public String getNamaWisata() {
        return namaWisata;
    }

    /**
     * @param namaWisata the namaWisata to set
     */
    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    /**
     * @return the fasilitas
     */
    public String getFasilitas() {
        return fasilitas;
    }

    /**
     * @param fasilitas the fasilitas to set
     */
    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    /**
     * @return the harga
     */
    public int getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public void getTempatWisata(String nama) throws SQLException {
        query = "Select * from tempatWisata where namaWisata = '" + nama + "'";
        ResultSet rs = stat.executeQuery(query);
        if (rs.next()) {
            idWisata = rs.getInt("idWisata");
            namaWisata = rs.getString("namaWisata");
            fasilitas = rs.getString("fasilitas");
            harga = rs.getInt("harga");
        }
    }
    
}
