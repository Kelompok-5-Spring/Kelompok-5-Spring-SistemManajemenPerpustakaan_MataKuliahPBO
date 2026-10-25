/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author ASUS
 */
public class AntreanPeminjam {

    // Queue (FIFO): antrean mahasiswa peminjam
    private Queue<String> antrean = new LinkedList<>();

    public int masuk(String nama) {
        antrean.offer(nama);
        return antrean.size();
    }

    public String layani() {
        return antrean.poll();
    }

    public String lihatDepan() {
        return antrean.peek();
    }

    public boolean kosong() {
        return antrean.isEmpty();
    }

    public void tampil() {
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
            return;
        }
        System.out.println("--- Antrean Peminjam ---");
        int no = 1;
        for (String nama : antrean) {
            System.out.println(no + ". " + nama);
            no++;
        }
        System.out.println("Dilayani berikutnya: " + antrean.peek());
    }
}