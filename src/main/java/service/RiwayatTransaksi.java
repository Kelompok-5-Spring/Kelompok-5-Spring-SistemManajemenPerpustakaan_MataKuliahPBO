/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Stack;
import model.Transaksi;
/**
 *
 * @author ASUS
 */
public class RiwayatTransaksi {

    // Stack (LIFO): riwayat transaksi
    private Stack<Transaksi> riwayat = new Stack<>();

    public void catat(Transaksi t) {
        riwayat.push(t);
    }

    public Transaksi ambilTerakhir() {
        return riwayat.pop();
    }

    public Transaksi lihatTerakhir() {
        return riwayat.peek();
    }

    public boolean kosong() {
        return riwayat.isEmpty();
    }

    public void tampil() {
        if (riwayat.isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("Transaksi terakhir : " + riwayat.peek());
        System.out.println("--- Seluruh Riwayat (terbaru di atas) ---");
        for (int i = riwayat.size() - 1; i >= 0; i--) {
            System.out.println((riwayat.size() - i) + ". " + riwayat.get(i));
        }
    }
}
