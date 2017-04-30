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
public class Lokasi {

    private int idLokasi;
    private String namaLokasi;
//    private ArrayList<TempatWisata> wisata = new ArrayList<>();
//    private ArrayList<Penginapan> penginapan = new ArrayList<>();
    private TempatWisata wisata;
    private Penginapan penginapan;
    
    private Connection conn;
    private Statement stat;
    private String query;
    
    public Lokasi() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
    }
    
    public Lokasi(int id, String nama) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        idLokasi = id;
        namaLokasi = nama;
    }
    
    /**
     * @return the idLokasi
     */
    public int getIdLokasi() {
        return idLokasi;
    }

    /**
     * @param idLokasi the idLokasi to set
     */
    public void setIdLokasi(int idLokasi) {
        this.idLokasi = idLokasi;
    }

    /**
     * @return the namaLokasi
     */
    public String getNamaLokasi() {
        return namaLokasi;
    }

    /**
     * @param namaLokasi the namaLokasi to set
     */
    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }
    
    public void addLokasi(Lokasi l) throws SQLException {
        query = "insert into lokasi (namaLokasi) values ('" + l.getNamaLokasi() + "')";
        stat.execute(query);
    }
    
    public void deleteLokasi(Lokasi l) throws SQLException {
        query = "delete from lokasi where namaLokasi = '" + l.getNamaLokasi()+ "'";
        stat.execute(query);
    }
    
    public void deleteLokasi(String nama) throws SQLException {
        query = "delete from lokasi where namaLokasi = '" + nama + "'";
        stat.execute(query);
    }

    public void addWisata(TempatWisata a) throws SQLException {
        query = "Select * from lokasi where namaLokasi = '" + namaLokasi + "'";
        ResultSet rs = stat.executeQuery(query);
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("idLokasi");
        }
        if (id != 0) {
            query = "insert into TempatWisata (namaWisata, fasilitas, harga, idLokasi) values ('" + wisata.getNamaWisata()+ "', '" + wisata.getFasilitas() + "', " + wisata.getHarga() + ", " + id + ")";
            stat.execute(query);
        }
    }

    public void deleteWisata(TempatWisata a) throws SQLException {
        query = "delete from TempatWisata where namaWisata = '" + wisata.getNamaWisata() + "'";
        stat.execute(query);
    }
    
    public void deleteWisata(String nama) throws SQLException {
        query = "delete from TempatWisata where namaWisata = '" + nama + "'";
        stat.execute(query);
    }

    public void addPenginapan(Penginapan p) throws SQLException {
        query = "Select * from lokasi where namaLokasi = '" + namaLokasi + "'";
        ResultSet rs = stat.executeQuery(query);
        int id = 0;
        if (rs.next()) {
            id = rs.getInt("idLokasi");
        }
        if (id != 0) {
            query = "insert into penginapan (namaPenginapan, status, harga, fasilitas, kapasitas, idLokasi) values ('" + penginapan.getNamaPenginapan() + "', '" + penginapan.getStatus() + "', " + penginapan.getHarga() + ", '" + penginapan.getFasilitas() + "', " + penginapan.getKapasitas() + ", " + id + ")";
            stat.execute(query);
        }
    }

    public void deletePenginapan(Penginapan p) throws SQLException {
        query = "delete from penginapan where namaPenginapan = '" + penginapan.getNamaPenginapan() + "'";
        stat.execute(query);
    }
    
    public void deletePenginapan(String nama) throws SQLException {
        query = "delete from penginapan where namaPenginapan = '" + nama + "'";
        stat.execute(query);
    }

    /**
     * @return the wisata
     */
    public TempatWisata getWisata() {
        return wisata;
    }

    /**
     * @return the penginapan
     */
    public Penginapan getPenginapan() {
        return penginapan;
    }

    /**
     * @param wisata the wisata to set
     */
    public void setWisata(TempatWisata wisata) {
        this.wisata = wisata;
    }

    /**
     * @param penginapan the penginapan to set
     */
    public void setPenginapan(Penginapan penginapan) {
        this.penginapan = penginapan;
    }
    
    public void getLokasi(String nama) throws SQLException {
        query = "Select * from lokasi where namaLokasi = '" + nama + "'";
        ResultSet rs = stat.executeQuery(query);
        if (rs.next()) {
            idLokasi = rs.getInt("idLokasi");
            namaLokasi = rs.getString("namaLokasi");
        }
    }

}
