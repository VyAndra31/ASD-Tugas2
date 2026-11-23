public class MataKuliah {
    String kode;
    String nama;
    int sks;
    int semester;
    String jenisSemester;

    NodePrasyarat prasyarat;
    MataKuliah next;

    public MataKuliah(String kode, String nama, int sks, int semester, String jenisSemester) {
        if(kode == null || kode.trim().isEmpty()){
            throw new IllegalArgumentException("Kode mata kuliah tidak boleh kosong.");
        }
        if(nama == null || nama.trim().isEmpty()){
            throw new IllegalArgumentException("Nama mata kuliah tidak boleh kosong.");
        }
        if(sks <= 0){
            throw new IllegalArgumentException("SKS mata kuliah harus lebih dari 0.");
        }
        if(semester <= 0){
            throw new IllegalArgumentException("Semester kurikulum harus lebih dari 0.");
        }
        if(jenisSemester == null || jenisSemester.trim().isEmpty()){
            throw new IllegalArgumentException("Jenis semester tidak boleh kosong.");
        }

        String jenis = jenisSemester.trim();
        boolean jenisValid = jenis.equalsIgnoreCase("Ganjil")
                || jenis.equalsIgnoreCase("Genap")
                || jenis.equalsIgnoreCase("Keduanya");

        if(!jenisValid){
            throw new IllegalArgumentException("Jenis semester harus Ganjil, Genap, atau Keduanya: " + jenis);
        }

        this.kode = kode.trim();
        this.nama = nama.trim();
        this.sks = sks;
        this.semester = semester;
        this.jenisSemester = jenis;
        this.prasyarat = null;
        this.next = null;
    }
}