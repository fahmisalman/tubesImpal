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
public class Transaksi {
    private int kodeTransaksi;
    private Paket paket;
    private int harga;

    /**
     * @return the kodeTransaksi
     */
    public int getKodeTransaksi() {
        return kodeTransaksi;
    }

    /**
     * @param kodeTransaksi the kodeTransaksi to set
     */
    public void setKodeTransaksi(int kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    /**
     * @return the paket
     */
    public Paket getPaket() {
        return paket;
    }

    /**
     * @param paket the paket to set
     */
    public void setPaket(Paket paket) {
        this.paket = paket;
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
    
    
}
