/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Guess
 */
public class Pembayaran {

    private int jumlahTransfer;
    private int tanggalTransfer;
    private Customer customer;
    private Transaksi transaksi;
    private String status;

    public void Pembayaran (int jumlahTransfer, int tanggalTransfer, Customer customer, Transaksi transaksi, String status){
        this.jumlahTransfer = jumlahTransfer;
        this.tanggalTransfer = tanggalTransfer;
        this.customer = customer;
        this.transaksi = transaksi;
        this.status = status;
    }
    
    public int getJumlahTransfer() {
        return jumlahTransfer;
    }

    public void setJumlahTransfer(int jumlahTransfer) {
        this.jumlahTransfer = jumlahTransfer;
    }

    public int getTanggalTransfer() {
        return tanggalTransfer;
    }

    public void setTanggalTransfer(int tanggalTransfer) {
        this.tanggalTransfer = tanggalTransfer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
