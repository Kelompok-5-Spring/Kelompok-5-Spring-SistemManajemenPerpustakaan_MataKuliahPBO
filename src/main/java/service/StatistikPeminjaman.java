/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ASUS
 */
public class StatistikPeminjaman {

    public static final String[] HARI = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};

    // Array 2D: jumlah peminjaman [kategori][hari]
    private int[][] jumlah = new int[KatalogBuku.KATEGORI.length][HARI.length];

    public void tambah(int kategori, int hari) {
        jumlah[kategori][hari]++;
    }

    public void kurangi(int kategori, int hari) {
        jumlah[kategori][hari]--;
    }

    public void tampil() {
        System.out.println("--- Statistik Peminjaman (Kategori x Hari) ---");
        System.out.printf("%-20s", "Kategori");
        for (String h : HARI) {
            System.out.printf("%-8s", h);
        }
        System.out.println("Total");
        for (int i = 0; i < KatalogBuku.KATEGORI.length; i++) {
            System.out.printf("%-20s", KatalogBuku.KATEGORI[i]);
            int total = 0;
            for (int j = 0; j < HARI.length; j++) {
                System.out.printf("%-8d", jumlah[i][j]);
                total += jumlah[i][j];
            }
            System.out.println(total);
        }
    }
}