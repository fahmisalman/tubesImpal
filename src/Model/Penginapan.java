/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASUS PC
 */
public class Penginapan {
    private int idPenginapan;
    private String namaPenginapan;
    private String fasilitas;
    private int kapasitas;
    private int harga;
    private String status;
    
    private Connection conn;
    private Statement stat;
    private String query;
    
    public Penginapan() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
    }
    
    public Penginapan (int id, String nama, String fasilitas, int kapasitas, int harga, String status) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        idPenginapan = id;
        namaPenginapan = nama;
        this.fasilitas = fasilitas;
        this.kapasitas = kapasitas;
        this.harga = harga;
        this.status = status;
    }

    /**
     * @return the idPenginapan
     */
    public int getIdPenginapan() {
        return idPenginapan;
    }

    /**
     * @param idPenginapan the idPenginapan to set
     */
    public void setIdPenginapan(int idPenginapan) {
        this.idPenginapan = idPenginapan;
    }

    /**
     * @return the namaPenginapan
     */
    public String getNamaPenginapan() {
        return namaPenginapan;
    }

    /**
     * @param namaPenginapan the namaPenginapan to set
     */
    public void setNamaPenginapan(String namaPenginapan) {
        this.namaPenginapan = namaPenginapan;
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
     * @return the kapasitas
     */
    public int getKapasitas() {
        return kapasitas;
    }

    /**
     * @param kapasitas the kapasitas to set
     */
    public void setKapasitas(int kapasitas) {
        this.kapasitas = kapasitas;
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

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Penginapan getPenginapan(String nama) throws SQLException {
        query = "Select * from penginapan where namaPenginapan = '" + namaPenginapan + "'";
        ResultSet rs = stat.executeQuery(query);
        Penginapan p = null;
        if (rs.next()) {
            idPenginapan = rs.getInt("idPenginapan");
            namaPenginapan = rs.getString("namaPenginapan");
            fasilitas = rs.getString("fasilitas");
            kapasitas = rs.getInt("kapasitas");
            harga = rs.getInt("harga");
            status = rs.getString("status");
            p = new Penginapan(idPenginapan, namaPenginapan, fasilitas, kapasitas, harga, status);
        }
        return p;
    }
    
}
