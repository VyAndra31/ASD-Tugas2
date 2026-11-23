import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        PembacaFile pembaca = new PembacaFile();

        String[] fileMahasiswa = {
                "data/mhs_01.txt",
                "data/mhs_02.txt",
                "data/mhs_03.txt"
        };

        String[] namaMahasiswa = {
                "mahasiswa satu",
                "mahasiswa dua",
                "mahasiswa tiga"
        };

        try {
            pembaca.bacaMataKuliah("data/matakuliah.csv", graph);
            pembaca.bacaPrasyarat("data/prasyarat.csv", graph);

            for (int i = 0; i < 3; i++) {
                DaftarLulus daftarLulus = new DaftarLulus();

                String semester = pembaca.bacaMahasiswa(
                        fileMahasiswa[i], daftarLulus
                );

                System.out.println((i + 1) + ". " + namaMahasiswa[i]);
                System.out.println();

                Rekomendasi rekomendasi = new Rekomendasi(graph, daftarLulus);
                rekomendasi.tampilkanRekomendasi(semester);

                System.out.println();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + e.getMessage());
        }
    }
}