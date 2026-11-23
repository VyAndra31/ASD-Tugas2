import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PembacaFile {
    public void bacaMataKuliah(String namaFile, Graph graph) throws FileNotFoundException{
        Scanner in = new Scanner(new File(namaFile));
        int idxBaris = 0;

        try {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                idxBaris++;

                if (line.trim().isEmpty()) continue;
                if (idxBaris == 1 && line.toLowerCase().contains("kode")) continue;

                String[] data = line.split(";", -1);
                if (data.length < 5) {
                    System.out.println("Format mata kuliah salah di baris " + idxBaris + ": " + line);
                    continue;
                }

                String kode = data[0].trim();
                String nama = data[1].trim();
                String jenisSemester = data[4].trim();

                if (kode.isEmpty() || nama.isEmpty() || jenisSemester.isEmpty()) {
                    System.out.println("Data kosong pada mata kuliah baris " + idxBaris);
                    continue;
                }

                try {
                    int sks = Integer.parseInt(data[2].trim());
                    int semester = Integer.parseInt(data[3].trim());
                    graph.tambahMataKuliah(kode, nama, sks, semester, jenisSemester);
                } catch (NumberFormatException e) {
                    System.out.println("SKS/Semester bukan angka di baris " + idxBaris + ": " + line);
                }
            }
        } finally {
            in.close();
        }
    }

    public void bacaPrasyarat(String namaFile, Graph graph) throws FileNotFoundException {
        Scanner in = new Scanner(new File(namaFile));
        int idxBaris = 0;

        try {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                idxBaris++;

                if (line.trim().isEmpty()) continue;
                if (idxBaris == 1 && line.toLowerCase().contains("prasyarat")) continue;

                String[] data = line.split(";", -1);
                if (data.length < 2) {
                    System.out.println("Format prasyarat salah di baris " + idxBaris + ": " + line);
                    continue;
                }

                String kodePrasyarat = data[0].trim();
                String kodeMK = data[1].trim();

                if (kodePrasyarat.isEmpty() || kodeMK.isEmpty()) {
                    System.out.println("Data kosong pada prasyarat baris " + idxBaris);
                    continue;
                }

                graph.tambahPrasyarat(kodeMK, kodePrasyarat);
            }
        } finally {
            in.close();
        }
    }

    public String bacaMahasiswa(String namaFile, DaftarLulus daftarLulus) throws FileNotFoundException {
        Scanner in = new Scanner(new File(namaFile));
        int idxBaris = 0;
        String semBerikutnya = "";

        try {
            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                idxBaris++;

                if (line.trim().isEmpty()) continue;

                int batas = line.indexOf('=');
                if (batas == -1) {
                    System.out.println("Format file mhs salah di baris " + idxBaris + ": " + line);
                    continue;
                }

                String key = line.substring(0, batas).trim().toLowerCase();
                String val = line.substring(batas + 1).trim();


                if (key.equals("semester_berikutnya")) {
                    if (val.equalsIgnoreCase("ganjil")) semBerikutnya = "Ganjil";
                    else if (val.equalsIgnoreCase("gaenap")) semBerikutnya = "Genap";
                    else System.out.println("Semester tidak valid di baris " + idxBaris + ": " + val);
                } else if (key.equals("lulus") && !val.isEmpty()) {
                    String[] listKode = val.split(",", -1);
                    for (String k : listKode){
                        if (!k.trim().isEmpty()) daftarLulus.tambahLulus(k.trim());
                    }
                }
            }
        } finally {
            in.close();
        }

        return semBerikutnya;
    }
}