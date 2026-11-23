public class Rekomendasi {
    Graph graph;
    DaftarLulus daftarLulus;

    public Rekomendasi(Graph graph, DaftarLulus daftarLulus) {
        this.graph = graph;
        this.daftarLulus = daftarLulus;
    }

    public int hitungTotalSKS() {
        int totalSKS = 0;

        NodeLulus temp = daftarLulus.head;

        while (temp != null) {
            MataKuliah matkul = graph.cariMataKuliah(temp.kode);

            if (matkul == null) {
                throw new IllegalArgumentException(
                        "Mata kuliah tidak ditemukan: " + temp.kode
                );
            }

            totalSKS = totalSKS + matkul.sks;
            temp = temp.next;
        }

        return totalSKS;
    }

    private String cariPrasyaratBelumLulus(MataKuliah mk, DaftarLulus dikunjungi, String hasil) {
        NodePrasyarat temp = mk.prasyarat;

        while (temp != null) {
            MataKuliah syarat = temp.mataKuliah;

            if (!dikunjungi.sudahLulus(syarat.kode)) {
                dikunjungi.tambahLulus(syarat.kode);

                hasil = cariPrasyaratBelumLulus(syarat, dikunjungi, hasil);

                if (!daftarLulus.sudahLulus(syarat.kode)) {
                    if (!hasil.equals("")) {
                        hasil = hasil + ", ";
                    }

                    hasil = hasil + syarat.kode;
                }
            }

            temp = temp.next;
        }

        return hasil;
    }

    public boolean cekPrasyarat(MataKuliah mk) {
        DaftarLulus dikunjungi = new DaftarLulus();
        dikunjungi.tambahLulus(mk.kode);

        return cariPrasyaratBelumLulus(mk, dikunjungi, "").equals("");
    }

    public boolean cekMinimalSKS(String kode, int totalSKS) {
        if (kode.equals("CAP")) {
            return totalSKS >= 60;
        }

        if (kode.equals("PKL")) {
            return totalSKS >= 100;
        }

        return true;
    }

    public boolean cekSkripsi() {
        MataKuliah temp = graph.head;

        while (temp != null) {
            if (!temp.kode.equals("SKR")) {
                if (!daftarLulus.sudahLulus(temp.kode)) {
                    return false;
                }
            }

            temp = temp.next;
        }

        return true;
    }

    public String alasanTidakBisa(MataKuliah mk, int totalSKS) {
        String alasan = "";
        String belumLulus = "";

        NodePrasyarat temp = mk.prasyarat;

        while (temp != null) {
            DaftarLulus dikunjungi = new DaftarLulus();
            dikunjungi.tambahLulus(mk.kode);

            belumLulus = cariPrasyaratBelumLulus(mk, dikunjungi, "");
        }

        if (!belumLulus.equals("")) {
            alasan = "belum lulus " + belumLulus;
        }

        if (!cekMinimalSKS(mk.kode, totalSKS)) {
            if (!alasan.equals("")) {
                alasan = alasan + "; ";
            }

            if (mk.kode.equals("CAP")) {
                alasan = alasan + "SKS belum mencapai 60";
            } else if (mk.kode.equals("PKL")) {
                alasan = alasan + "SKS belum mencapai 100";
            }
        }

        if (mk.kode.equals("SKR") && !cekSkripsi()) {
            if (!alasan.equals("")) {
                alasan = alasan + "; ";
            }

            alasan = alasan + "belum memenuhi syarat semua MK wajib";
        }

        return alasan;
    }

    public void tampilkanRekomendasi(String semester) {
        int totalSKS = hitungTotalSKS();
        int nomor = 1;

        System.out.println("Daftar MK yang bisa diambil:");

        MataKuliah temp = graph.head;

        while (temp != null) {
            if ((temp.jenisSemester.equalsIgnoreCase(semester) || temp.jenisSemester.equalsIgnoreCase("Keduanya")) && !daftarLulus.sudahLulus(temp.kode)) {
                String alasan = alasanTidakBisa(temp, totalSKS);

                if (alasan.equals("")) {
                    System.out.printf("%2d. %-5s %-45s %d SKS%n", nomor, temp.kode, temp.nama, temp.sks);
                    nomor++;
                }
            }

            temp = temp.next;
        }

        System.out.println();
        System.out.println("Daftar MK yang belum bisa diambil semester depan:");

        temp = graph.head;

        while (temp != null) {
            if ((temp.jenisSemester.equalsIgnoreCase(semester) || temp.jenisSemester.equalsIgnoreCase("Keduanya")) && !daftarLulus.sudahLulus(temp.kode)) {
                String alasan = alasanTidakBisa(temp, totalSKS);

                if (!alasan.equals("")) {
                    System.out.println(temp.kode + ": " + alasan);
                }
            }

            temp = temp.next;
        }
    }
}


