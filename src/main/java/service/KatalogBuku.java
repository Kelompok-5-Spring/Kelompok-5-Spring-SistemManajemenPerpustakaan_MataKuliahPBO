/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Buku;
/**
 *
 * @author ASUS
 */
public class KatalogBuku {

    // Array 1D: daftar kategori buku
    public static final String[] KATEGORI = {"Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"};

    // Array 3D: lokasi buku [lantai][rak][posisi]
    public static final int LANTAI = 2, RAK = 3, POSISI = 4;
    private String[][][] lokasi = new String[LANTAI][RAK][POSISI];

    // ArrayList: daftar buku dinamis
    private ArrayList<Buku> daftarBuku = new ArrayList<>();

    private int idBerikutnya = 1;

    public Buku tambahBuku(String judul, int kategori) {
        for (int l = 0; l < LANTAI; l++) {
            for (int r = 0; r < RAK; r++) {
                for (int p = 0; p < POSISI; p++) {
                    if (lokasi[l][r][p] == null) {
                        lokasi[l][r][p] = judul;
                        Buku b = new Buku(idBerikutnya++, judul, kategori, l, r, p);
                        daftarBuku.add(b);
                        return b;
                    }
                }
            }
        }
        return null;
    }

    public Buku cariBuku(int id) {
        for (Buku b : daftarBuku) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean hapusBuku(Buku b) {
        lokasi[b.getLantai()][b.getRak()][b.getPosisi()] = null;
        return daftarBuku.remove(b);
    }

    public boolean berisi(Buku b) {
        return daftarBuku.contains(b);
    }

    public void tampilKategori() {
        System.out.println("--- Daftar Kategori Buku ---");
        for (int i = 0; i < KATEGORI.length; i++) {
            System.out.println((i + 1) + ". " + KATEGORI[i]);
        }
    }

    public void tampilBuku() {
        if (daftarBuku.isEmpty()) {
            System.out.println("Belum ada buku.");
            return;
        }
        System.out.println("--- Daftar Buku ---");
        System.out.printf("%-4s %-32s %-20s %-10s %s%n", "ID", "Judul", "Kategori", "Status", "Lokasi");
        for (Buku b : daftarBuku) {
            String status = b.isTersedia() ? "Tersedia" : "Dipinjam";
            System.out.printf("%-4d %-32s %-20s %-10s L%d-R%d-P%d%n", b.getId(), b.getJudul(),
                    KATEGORI[b.getKategori()], status, b.getLantai() + 1, b.getRak() + 1, b.getPosisi() + 1);
        }
    }

    public void tampilLokasi() {
        System.out.println("--- Peta Lokasi Penyimpanan Buku ---");
        for (int l = 0; l < LANTAI; l++) {
            System.out.println("Lantai " + (l + 1));
            for (int r = 0; r < RAK; r++) {
                System.out.println("  Rak " + (r + 1));
                for (int p = 0; p < POSISI; p++) {
                    String isi = lokasi[l][r][p];
                    System.out.println("    Posisi " + (p + 1) + ": " + (isi == null ? "(kosong)" : isi));
                }
            }
        }
    }
}
