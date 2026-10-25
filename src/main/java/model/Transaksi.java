/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Transaksi {
    private String jenis;
    private String peminjam;
    private Buku buku;
    private int hari;
    private String namaHari;

    public Transaksi(String jenis, String peminjam, Buku buku, int hari, String namaHari) {
        this.jenis = jenis;
        this.peminjam = peminjam;
        this.buku = buku;
        this.hari = hari;
        this.namaHari = namaHari;
    }

    public String getJenis() {
        return jenis;
    }

    public String getPeminjam() {
        return peminjam;
    }

    public Buku getBuku() {
        return buku;
    }

    public int getHari() {
        return hari;
    }

    @Override
    public String toString() {
        String info = jenis + " | " + peminjam + " | " + buku.getJudul();
        if (hari >= 0) {
            info += " | " + namaHari;
        }
        return info;
    }
}
