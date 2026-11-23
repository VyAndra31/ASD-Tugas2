public class Graph {
    MataKuliah head;
    public Graph() {
        head = null;
    }
    public void tambahMataKuliah(String kode, String nama, int sks, int semester, String jenisSemester) {
        if(kode == null || kode.trim().isEmpty()){
            throw new IllegalArgumentException("Kode mata kuliah tidak boleh kosong.");
        }
        String kodeBersih = kode.trim();
        if(cariMataKuliah(kodeBersih) != null){
            throw new IllegalArgumentException("Kode mata kuliah duplikat: " + kodeBersih);
        }
        MataKuliah mataKuliahBaru = new MataKuliah(kodeBersih, nama, sks, semester, jenisSemester);
        MataKuliah saatIni = head;
        MataKuliah terakhir = null;

        while(saatIni != null){
            terakhir = saatIni;
            saatIni = saatIni.next;
        }

        if(terakhir == null){
            head = mataKuliahBaru;
        }else{
            terakhir.next = mataKuliahBaru;
        }
    }

    public MataKuliah cariMataKuliah(String kode){
        if(kode == null || kode.trim().isEmpty()){
            return null;
        }
        String kodeBersih = kode.trim();
        MataKuliah saatIni = head;
        while(saatIni != null && !saatIni.kode.equals(kodeBersih)){
            saatIni = saatIni.next;
        }
        return saatIni;
    }

    public void tambahPrasyarat(String kodeMK, String kodePrasyarat) {
        MataKuliah mataKuliah = cariMataKuliah(kodeMK);
        MataKuliah prasyarat = cariMataKuliah(kodePrasyarat);

        if(mataKuliah == null){
            throw new IllegalArgumentException("Mata kuliah tujuan prasyarat tidak ditemukan: " + kodeMK);
        }
        if(prasyarat == null){
            throw new IllegalArgumentException("Kode mata kuliah prasyarat tidak ditemukan: " + kodePrasyarat);
        }

        NodePrasyarat nodeSaatIni = mataKuliah.prasyarat;
        NodePrasyarat nodeTerakhir = null;

        while(nodeSaatIni != null && !nodeSaatIni.mataKuliah.kode.equals(prasyarat.kode)){
            nodeTerakhir = nodeSaatIni;
            nodeSaatIni = nodeSaatIni.next;
        }
        if(nodeSaatIni != null){
            return;
        }

        NodePrasyarat nodeBaru = new NodePrasyarat(prasyarat);

        if(nodeTerakhir == null){
            mataKuliah.prasyarat = nodeBaru;
        }else{
            nodeTerakhir.next = nodeBaru;
        }
    }
}