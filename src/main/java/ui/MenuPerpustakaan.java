/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import java.util.Scanner;
import model.Buku;
import service.AntreanPeminjam;
import service.KatalogBuku;
import service.PerpustakaanService;
/**
 *
 * @author ASUS
 */
public class MenuPerpustakaan {

    private PerpustakaanService service;
    private Scanner sc = new Scanner(System.in);

    public MenuPerpustakaan(PerpustakaanService service) {
        this.service = service;
    }

    private int bacaInt(String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    private void tampilMenu() {
        System.out.println();
        System.out.println("===== SISTEM MANAJEMEN PERPUSTAKAAN KAMPUS =====");
        System.out.println(" 1. Tambah buku");
        System.out.println(" 2. Tampilkan daftar buku");
        System.out.println(" 3. Hapus buku");
        System.out.println(" 4. Tampilkan kategori buku");
        System.out.println(" 5. Statistik peminjaman (kategori x hari)");
        System.out.println(" 6. Peta lokasi penyimpanan buku");
        System.out.println(" 7. Tambah antrean peminjam");
        System.out.println(" 8. Tampilkan antrean peminjam");
        System.out.println(" 9. Proses peminjaman (layani antrean)");
        System.out.println("10. Pengembalian buku");
        System.out.println("11. Lihat riwayat transaksi");
        System.out.println("12. Batalkan transaksi terakhir");
        System.out.println(" 0. Keluar");
    }

    private void tambahBuku() {
        KatalogBuku katalog = service.getKatalog();
        System.out.print("Judul buku : ");
        String judul = sc.nextLine().trim();
        if (judul.isEmpty()) {
            System.out.println("Judul tidak boleh kosong!");
            return;
        }
        katalog.tampilKategori();
        int k = bacaInt("Pilih kategori (1-" + KatalogBuku.KATEGORI.length + "): ");
        if (k < 1 || k > KatalogBuku.KATEGORI.length) {
            System.out.println("Kategori tidak valid!");
            return;
        }
        Buku b = katalog.tambahBuku(judul, k - 1);
        if (b != null) {
            System.out.println("Buku berhasil ditambahkan (ID " + b.getId() + ").");
            System.out.println("Lokasi: Lantai " + (b.getLantai() + 1) + ", Rak " + (b.getRak() + 1)
                    + ", Posisi " + (b.getPosisi() + 1));
        } else {
            System.out.println("Rak penuh, buku tidak dapat ditambahkan!");
        }
    }

    private void hapusBuku() {
        int id = bacaInt("ID buku yang akan dihapus: ");
        System.out.println(service.hapusBuku(id));
    }

    private void tambahAntrean() {
        System.out.print("Nama mahasiswa: ");
        String nama = sc.nextLine().trim();
        if (nama.isEmpty()) {
            System.out.println("Nama tidak boleh kosong!");
            return;
        }
        int urutan = service.getAntrean().masuk(nama);
        System.out.println(nama + " masuk antrean urutan ke-" + urutan);
    }

    private void prosesPinjam() {
        AntreanPeminjam antrean = service.getAntrean();
        if (antrean.kosong()) {
            System.out.println("Tidak ada mahasiswa dalam antrean!");
            return;
        }
        service.getKatalog().tampilBuku();
        System.out.println("Mahasiswa yang dilayani: " + antrean.lihatDepan());
        int id = bacaInt("ID buku yang dipinjam: ");
        int hari = bacaInt("Hari peminjaman (1=Senin ... 7=Minggu): ");
        System.out.println(service.prosesPinjam(id, hari));
    }

    private void prosesKembali() {
        int id = bacaInt("ID buku yang dikembalikan: ");
        System.out.print("Nama mahasiswa yang mengembalikan: ");
        String nama = sc.nextLine().trim();
        System.out.println(service.prosesKembali(id, nama));
    }

    public void jalankan() {
        int pilih;
        do {
            tampilMenu();
            pilih = bacaInt("Pilih menu: ");
            System.out.println();
            switch (pilih) {
                case 1: tambahBuku(); break;
                case 2: service.getKatalog().tampilBuku(); break;
                case 3: hapusBuku(); break;
                case 4: service.getKatalog().tampilKategori(); break;
                case 5: service.getStatistik().tampil(); break;
                case 6: service.getKatalog().tampilLokasi(); break;
                case 7: tambahAntrean(); break;
                case 8: service.getAntrean().tampil(); break;
                case 9: prosesPinjam(); break;
                case 10: prosesKembali(); break;
                case 11: service.getRiwayat().tampil(); break;
                case 12: System.out.println(service.batalkanTransaksi()); break;
                case 0: System.out.println("Terima kasih, program selesai."); break;
                default: System.out.println("Menu tidak valid!");
            }
        } while (pilih != 0);
        sc.close();
    }
}
