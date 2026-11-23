# Proyek 2 Algoritma dan Struktur Data

## Sistem Rekomendasi Pengambilan Mata Kuliah

Program Java ini memberikan rekomendasi mata kuliah yang dapat diambil pada semester berikutnya berdasarkan daftar mata kuliah yang sudah lulus, semester penawaran, dan prasyarat. Program juga menampilkan mata kuliah yang belum bisa diambil beserta alasannya.

### Anggota Kelompok

1. Livyandra Lita Halim (255150200111009)
2. Faisya Anindya Shafah (255150201111046)
3. Aisyah Zayyana Ghayda. DM (255150200111001)

## Struktur Data dan Algoritma

- **Graph (graf berarah):** menggambarkan hubungan prasyarat antar mata kuliah. Setiap mata kuliah terhubung ke daftar mata kuliah yang menjadi prasyaratnya.
- **Singly Linked List:** menyimpan daftar mata kuliah pada `Graph`, daftar prasyarat setiap mata kuliah, dan daftar mata kuliah yang telah lulus.
- **Linear Search:** mencari mata kuliah berdasarkan kode serta memeriksa apakah suatu kode sudah ada dalam daftar kelulusan.
- **Rekursi:** menelusuri prasyarat berantai agar alasan penolakan dapat mencantumkan prasyarat dari beberapa tingkat.

Seluruh struktur data dibuat sendiri tanpa menggunakan Java Collections.

## Struktur Folder

```text
ASD-Tugas2/
├── src/
│   ├── Main.java
│   ├── Graph.java
│   ├── MataKuliah.java
│   ├── NodePrasyarat.java
│   ├── DaftarLulus.java
│   ├── NodeLulus.java
│   ├── PembacaFile.java
│   └── Rekomendasi.java
├── data/
│   ├── matakuliah.csv
│   ├── prasyarat.csv
│   ├── mhs_01.txt
│   ├── mhs_02.txt
│   └── mhs_03.txt
├── README.md
└── .gitignore
```

## Fungsi Setiap File Java

- `Main.java`: menjalankan program, membaca data mata kuliah dan prasyarat sekali, lalu menampilkan rekomendasi untuk tiga mahasiswa secara berurutan.
- `Graph.java`: menambah dan mencari mata kuliah serta menghubungkan prasyaratnya.
- `MataKuliah.java`: menyimpan kode, nama, SKS, semester kurikulum, jenis semester penawaran, dan penunjuk daftar prasyarat.
- `NodePrasyarat.java`: menjadi node Linked List untuk relasi prasyarat.
- `DaftarLulus.java`: menyimpan dan mengecek kode mata kuliah yang telah lulus, sekaligus mencegah duplikasi.
- `NodeLulus.java`: menjadi node Linked List untuk daftar kelulusan.
- `PembacaFile.java`: membaca data dari berkas CSV dan TXT menggunakan `Scanner`.
- `Rekomendasi.java`: menghitung total SKS, memeriksa prasyarat termasuk prasyarat berantai, mengecek syarat khusus, dan mencetak dua daftar rekomendasi.

## Cara Menjalankan

**Persyaratan:** JDK Java (proyek telah dijalankan menggunakan JDK 24). Tidak memerlukan pustaka tambahan.

### Menggunakan IntelliJ IDEA

1. Buka folder proyek `ASD-Tugas2` di IntelliJ IDEA.
2. Pastikan folder `src` ditandai sebagai **Sources Root**, dan Project SDK sesuai dengan JDK yang terpasang.
3. Pastikan berkas input tersedia di folder `data`.
4. Jalankan `Main.java` dengan *working directory* folder utama `ASD-Tugas2`.

Program akan langsung memproses `mhs_01.txt`, `mhs_02.txt`, dan `mhs_03.txt` tanpa meminta input manual.

### Menggunakan Terminal Windows (PowerShell)

Jalankan dari folder utama proyek:

```powershell
javac -d out src\Main.java src\Graph.java src\MataKuliah.java src\NodePrasyarat.java src\DaftarLulus.java src\NodeLulus.java src\PembacaFile.java src\Rekomendasi.java
java -cp out Main
```

## Alur Program

1. `PembacaFile` membaca `data/matakuliah.csv` dan menambah mata kuliah ke `Graph`.
2. `PembacaFile` membaca `data/prasyarat.csv` dan menghubungkan mata kuliah dengan prasyaratnya.
3. Untuk setiap berkas mahasiswa, program membuat `DaftarLulus` baru agar data antar mahasiswa tidak tercampur.
4. `PembacaFile` membaca semester berikutnya dan daftar mata kuliah lulus dari berkas mahasiswa.
5. `Rekomendasi` menghitung total SKS dan memeriksa mata kuliah yang ditawarkan pada semester tersebut (termasuk penawaran `Keduanya`), yang belum lulus, serta memenuhi prasyarat.
6. Program menampilkan **daftar MK yang bisa diambil** dan **daftar MK yang belum bisa diambil semester depan** beserta alasannya untuk setiap mahasiswa.

Pemeriksaan khusus yang digunakan dalam kode: Capstone (`CAP`) membutuhkan minimal 60 SKS, Praktik Kerja Lapangan (`PKL`) minimal 100 SKS, dan Skripsi (`SKR`) diperiksa berdasarkan kelulusan seluruh mata kuliah wajib lainnya.

