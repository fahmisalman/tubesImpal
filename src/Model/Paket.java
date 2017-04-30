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
public class Paket extends Customer{
    private int idPaket;
    private Lokasi lokasi;
    private String tanggal;
    private int harga;
    private int jumahOrang;
    private int jumlahHari;
    private Penginapan penginapan;
    private TempatWisata tempatWisata;
    private Customer customer;
    private int total;
    
    private Connection conn;
    private Statement stat;
    private String query;

    /**
     * @return the idPaket
     * @throws java.sql.SQLException
     */
    
    public Paket() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/TujuhBelasRibuPulau", "root", "");
        stat = conn.createStatement();
        lokasi = new Lokasi();
        penginapan = new Penginapan();
        tempatWisata = new TempatWisata();
        customer = new Customer();
    }
    
    public int getIdPaket() {
        return idPaket;
    }

    /**
     * @param idPaket the idPaket to set
     */
    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }

    /**
     * @return the tanggal
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * @param tanggal the tanggal to set
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
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
     * @return the jumahOrang
     */
    public int getJumahOrang() {
        return jumahOrang;
    }

    /**
     * @param jumahOrang the jumahOrang to set
     */
    public void setJumahOrang(int jumahOrang) {
        this.jumahOrang = jumahOrang;
    }

    /**
     * @return the jumlahHari
     */
    public int getJumlahHari() {
        return jumlahHari;
    }

    /**
     * @param jumlahHari the jumlahHari to set
     */
    public void setJumlahHari(int jumlahHari) {
        this.jumlahHari = jumlahHari;
    }
    
    public void getIdWisata(String nama) throws SQLException {
        query = "Select * from tempatWisata where namaWisata = '" + nama + "'";
        ResultSet rs = stat.executeQuery(query);
        if (rs.next()) {
            tempatWisata.setIdWisata(rs.getInt("idWisata"));
            tempatWisata.setNamaWisata(rs.getString("namaWisata"));
            tempatWisata.setFasilitas(rs.getString("fasilitas"));
            tempatWisata.setHarga(rs.getInt("harga"));
        }
    }
    
    public void getIdLokasi(String nama) throws SQLException {
        query = "Select * from lokasi where namaLokasi = '" + nama + "'";
        ResultSet rs = stat.executeQuery(query);
        if (rs.next()) {
            lokasi.setIdLokasi(rs.getInt("idLokasi"));
            lokasi.setNamaLokasi(rs.getString("namaLokasi"));
        }
    }
    
    public void getIdPenginapan(String nama) throws SQLException {
        query = "Select * from penginapan where namaPenginapan = '" + nama + "'";
        ResultSet rs = stat.executeQuery(query);
        if (rs.next()) {
            penginapan.setIdPenginapan(rs.getInt("idPenginapan"));
            penginapan.setNamaPenginapan(rs.getString("namaPenginapan"));
            penginapan.setFasilitas(rs.getString("fasilitas"));
            penginapan.setHarga(rs.getInt("harga"));
            penginapan.setKapasitas(rs.getInt("kapasitas"));
            penginapan.setStatus(rs.getString("status"));
        }
    }
    
    public void getUser(String nama) throws SQLException {
        query = "Select * from customer where username = '" + nama + "'";
        ResultSet rs = stat.executeQuery(query);
        if (rs.next()) {
            getCustomer().setIdCustomer(rs.getInt("idCustomer"));
            getCustomer().setUsername(rs.getString("username"));
            getCustomer().setPassword(rs.getString("password"));
            getCustomer().setNama(rs.getString("nama"));
        }
    }

    /**
     * @return the lokasi
     */
    public Lokasi getLokasi() {
        return lokasi;
    }

    /**
     * @param lokasi the lokasi to set
     */
    public void setLokasi(Lokasi lokasi) {
        this.lokasi = lokasi;
    }

    /**
     * @return the penginapan
     */
    public Penginapan getPenginapan() {
        return penginapan;
    }

    /**
     * @param penginapan the penginapan to set
     */
    public void setPenginapan(Penginapan penginapan) {
        this.penginapan = penginapan;
    }

    /**
     * @return the tempatWisata
     */
    public TempatWisata getTempatWisata() {
        return tempatWisata;
    }

    /**
     * @param tempatWisata the tempatWisata to set
     */
    public void setTempatWisata(TempatWisata tempatWisata) {
        this.tempatWisata = tempatWisata;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void addPaket() throws SQLException {
        total = (penginapan.getHarga() * jumahOrang) + tempatWisata.getHarga();
        query = "insert into paket (harga, jumlahOrang, jumlahHari, tanggal, idLokasi, idPenginapan, idTempatWisata, idCustomer) values ('" + total + "', '" + jumahOrang + "', '" + jumlahHari + "', '" + tanggal +  "', '" + lokasi.getIdLokasi() + "', '" + penginapan.getIdPenginapan() + "', '" + tempatWisata.getIdWisata() + "', '" + customer.getIdCustomer() + "')";
        stat.execute(query);
        addTransaksi();
    }
    
    public void deletePaket(int id) throws SQLException {
        query = "delete from paket where idPaket = '" + id + "'";
        stat.execute(query);
        deleteTransaksi(id);
    }
    
    public void addTransaksi() throws SQLException {
        query = "select * from paket where harga = '" + total + "'";
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            idPaket = rs.getInt("idPaket");
        }
        query = "insert into transaksi (harga, idPaket, status, idCustomer) values ('" + total + "', '" + idPaket + "', 'tidak lunas', '" + customer.getIdCustomer() + "')";
        stat.execute(query);
    }
    
    public void deleteTransaksi(int id) throws SQLException {
        query = "select * from transaksi where idPaket = '" + id + "'";
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            id = rs.getInt("idTransaksi");
        }
        query = "delete from transaksi where idTransaksi = '" + id + "'";
        stat.execute(query);
    }
    
    public void updateTransaksi(int id) throws SQLException {
        query = "select * from transaksi where idTransaksi = '" + id + "'";
        ResultSet rs = stat.executeQuery(query);
        while (rs.next()) {
            id = rs.getInt("idTransaksi");
        }
        query = "update transaksi set status = 'lunas' where idTransaksi = '" + id + "'";
        stat.execute(query);
    }
    
}
