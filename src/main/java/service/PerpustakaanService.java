/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Buku;
import model.Transaksi;
 
/**
 *
 * @author ASUS
 */
public class PerpustakaanService {

    private KatalogBuku katalog = new KatalogBuku();
    private StatistikPeminjaman statistik = new StatistikPeminjaman();
    private AntreanPeminjam antrean = new AntreanPeminjam();
    private RiwayatTransaksi riwayat = new RiwayatTransaksi();

    public PerpustakaanService() {
        katalog.tambahBuku("Algoritma dan Pemrograman Java", 0);
        katalog.tambahBuku("Kalkulus Dasar", 1);
        katalog.tambahBuku("Jaringan Komputer Modern", 2);
        katalog.tambahBuku("Sistem Basis Data", 3);
    }

    public KatalogBuku getKatalog() {
        return katalog;
    }

    public StatistikPeminjaman getStatistik() {
        return statistik;
    }

    public AntreanPeminjam getAntrean() {
        return antrean;
    }

    public RiwayatTransaksi getRiwayat() {
        return riwayat;
    }

    public String hapusBuku(int id) {
        Buku b = katalog.cariBuku(id);
        if (b == null) {
            return "Buku dengan ID tersebut tidak ditemukan!";
        }
        if (!b.isTersedia()) {
            return "Buku sedang dipinjam, tidak dapat dihapus!";
        }
        katalog.hapusBuku(b);
        return "Buku \"" + b.getJudul() + "\" berhasil dihapus.";
    }

    public String prosesPinjam(int idBuku, int hari) {
        if (antrean.kosong()) {
            return "Tidak ada mahasiswa dalam antrean!";
        }
        Buku b = katalog.cariBuku(idBuku);
        if (b == null) {
            return "Buku tidak ditemukan!";
        }
        if (!b.isTersedia()) {
            return "Buku sedang dipinjam!";
        }
        if (hari < 1 || hari > 7) {
            return "Hari tidak valid!";
        }
        String nama = antrean.layani();
        b.setTersedia(false);
        statistik.tambah(b.getKategori(), hari - 1);
        riwayat.catat(new Transaksi("PINJAM", nama, b, hari - 1, StatistikPeminjaman.HARI[hari - 1]));
        return nama + " berhasil meminjam \"" + b.getJudul() + "\".";
    }

    public String prosesKembali(int idBuku, String nama) {
        Buku b = katalog.cariBuku(idBuku);
        if (b == null) {
            return "Buku tidak ditemukan!";
        }
        if (b.isTersedia()) {
            return "Buku ini tidak sedang dipinjam!";
        }
        b.setTersedia(true);
        riwayat.catat(new Transaksi("KEMBALI", nama, b, -1, ""));
        return "Buku \"" + b.getJudul() + "\" berhasil dikembalikan.";
    }

    public String batalkanTransaksi() {
        if (riwayat.kosong()) {
            return "Tidak ada transaksi yang dapat dibatalkan!";
        }
        Transaksi t = riwayat.ambilTerakhir();
        Buku b = t.getBuku();
        if (!katalog.berisi(b)) {
            return "Buku pada transaksi ini sudah dihapus, transaksi dibuang: " + t;
        }
        if (t.getJenis().equals("PINJAM")) {
            b.setTersedia(true);
            statistik.kurangi(b.getKategori(), t.getHari());
        } else {
            b.setTersedia(false);
        }
        return "Transaksi dibatalkan: " + t;
    }
}
