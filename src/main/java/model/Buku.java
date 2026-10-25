/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Buku {
    private int id;
    private String judul;
    private int kategori;
    private int lantai, rak, posisi;
    private boolean tersedia = true;

    public Buku(int id, String judul, int kategori, int lantai, int rak, int posisi) {
        this.id = id;
        this.judul = judul;
        this.kategori = kategori;
        this.lantai = lantai;
        this.rak = rak;
        this.posisi = posisi;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public int getKategori() {
        return kategori;
    }

    public int getLantai() {
        return lantai;
    }

    public int getRak() {
        return rak;
    }

    public int getPosisi() {
        return posisi;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }
}
