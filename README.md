# Sistem Manajemen Perpustakaan Kampus
Pengelolaan perpustakaan kampus kerap kali menghadapi kendala efisiensi, seperti penataan lokasi buku yang rumit, pencatatan statistik peminjaman yang kurang terstruktur, alur antrean mahasiswa yang tidak teratur, serta ketiadaan sistem pembatalan transaksi yang akurat. Tanpa penerapan struktur data yang tepat, pemrosesan data operasional harian perpustakaan dapat berujung pada ketidaksesuaian data dan penurunan kualitas pelayanan.

Program Sistem Manajemen Perpustakaan Kampus berbasis Java ini dikembangkan untuk mensimulasikan tata kelola perpustakaan yang efisien, terintegrasi, dan terstruktur. Program ini mengombinasikan struktur data statis berupa Array 1D untuk kategori, Array 2D untuk statistik peminjaman, dan Array 3D untuk peta lokasi fisik buku, serta struktur data dinamis berupa ArrayList untuk kelola koleksi buku, Queue untuk antrean peminjam, dan Stack untuk riwayat transaksi. Solusi ini bertujuan mempermudah seluruh operasional perpustakaan mulai dari pengelolaan koleksi hingga fitur pembatalan transaksi secara otomatis.

## Tugas Pemrograman Java
- **Mata Kuliah:** Pemrograman Berorientasi Objek
- **Dosen Pengampu:** M. Ibadurrahman Arrasyid Supriyanto M.Kom.
- **Program Studi:** Sistem Informasi
- **Angkatan:** 2025
- **Kelompok:** 5
  
## Anggota Kelompok
1. *MUHAMMAD ARZA DWIARTO ANUGERAH* - 2509116007 - Kelas A
2. *MUHAMMAD DAVI ADITYA PRATAMA* - 2509116070 - Kelas B
3. *AULIA ASHYLLA ANANDA PUTRI HARIAWAN* - 2509116076 - KELAS B
4. *DZULRRAHIM OSCAR ADITYA AKBAR* - 2509116107 - KELAS C

## Struktur File Proyek
Program dibagi ke dalam beberapa package agar setiap bagian memiliki tanggung jawab yang jelas. Package model berisi class yang merepresentasikan data seperti Buku dan Transaksi, package service berisi class yang mengelola struktur data beserta logika pengolahannya, package perpustakaan.ui berisi class yang berinteraksi dengan pengguna melalui menu, sedangkan package utama perpustakaan hanya berisi class Main sebagai titik awal program.  
<img width="413" height="360" alt="image" src="https://github.com/user-attachments/assets/3147eafd-f427-43e1-9bc4-68bb3b68bb12" />  
  
## Implementasi Array 1D, 2D, 3D  
* **Array 1D (Daftar Kategori):**
  Deklarasi konstanta kategori buku pada `KatalogBuku.java`:
  ```java
  public static final String[] KATEGORI = {"Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"};
  ```
  <img width="301" height="155" alt="image" src="https://github.com/user-attachments/assets/5d8fb01d-e8ff-4f21-a6ec-0de218ac03db" />


* **Array 2D (Statistik Peminjaman Harian):**
  Matriks [kategori][hari] pada `StatistikPeminjaman.java`:
  ```java
  public static final String[] HARI = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"};
  private int[][] jumlah = new int[KatalogBuku.KATEGORI.length][HARI.length];

  public void tambah(int kategori, int hari) {
      jumlah[kategori][hari]++;
  }
  ```
  <img width="767" height="182" alt="image" src="https://github.com/user-attachments/assets/6248e03b-de14-403f-9d98-6906a556cf61" />


* **Array 3D (Lokasi Penyimpanan Buku):**
  Pemetaan posisi fisik [lantai][rak][posisi] pada `KatalogBuku.java`:
  ```java
  public static final int LANTAI = 2, RAK = 3, POSISI = 4;
  private String[][][] lokasi = new String[LANTAI][RAK][POSISI];
  ```
  <img width="442" height="405" alt="image" src="https://github.com/user-attachments/assets/49a7a0f4-2d68-4653-96c4-2e584e177fb1" />


---

### 2. Implementasi ArrayList

Mengelola daftar objek `Buku` secara dinamis pada `KatalogBuku.java`:
```java
private ArrayList<Buku> daftarBuku = new ArrayList<>();

public boolean hapusBuku(Buku b) {
    lokasi[b.getLantai()][b.getRak()][b.getPosisi()] = null;
    return daftarBuku.remove(b);
}
```
<img width="432" height="485" alt="image" src="https://github.com/user-attachments/assets/5a10d68b-a08c-4b51-abda-21572895f599" />  
<br />
<img width="541" height="372" alt="image" src="https://github.com/user-attachments/assets/5f8168ba-ee3f-4268-aae8-76cb1202b048" />  
<br />
<img width="421" height="351" alt="image" src="https://github.com/user-attachments/assets/55f504a8-b072-4c3a-a7b8-58ff5b95c6ba" />  


---

### 3. Implementasi Queue dan Stack

* **Queue (Antrean Peminjam - FIFO):**
  Dikelola pada `AntreanPeminjam.java` menggunakan `LinkedList`:
  ```java
  private Queue<String> antrean = new LinkedList<>();

  public int masuk(String nama) {
      antrean.offer(nama);
      return antrean.size();
  }

  public String layani() {
      return antrean.poll();
  }
  ```
  <img width="568" height="672" alt="image" src="https://github.com/user-attachments/assets/bc212ace-6a05-41e0-a806-3a5693164928" />


* **Stack (Riwayat Transaksi - LIFO):**
  Dikelola pada `RiwayatTransaksi.java` untuk mendukung fitur pembatalan (*undo*):
  ```java
  private Stack<Transaksi> riwayat = new Stack<>();

  public void catat(Transaksi t) {
      riwayat.push(t);
  }

  public Transaksi ambilTerakhir() {
      return riwayat.pop();
  }
  ```
 <img width="682" height="210" alt="image" src="https://github.com/user-attachments/assets/9f74780c-e97c-49ff-a35b-6d683191db3e" />


---

### 4. Integrasi Layanan (Class Service)

`PerpustakaanService.java` bertindak sebagai koordinator yang menghubungkan seluruh komponen struktur data dalam satu alur bisnis operasional.

```java
public String prosesPinjam(int idBuku, int hari) {
    // Validasi antrean, ketersediaan buku, dan input hari
    String nama = antrean.layani();
    b.setTersedia(false);
    statistik.tambah(b.getKategori(), hari - 1);
    riwayat.catat(new Transaksi("PINJAM", nama, b, hari - 1, StatistikPeminjaman.HARI[hari - 1]));
    return nama + " berhasil meminjam \"" + b.getJudul() + "\".";
}
```

---
