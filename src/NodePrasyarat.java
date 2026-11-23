public class NodePrasyarat {
    MataKuliah mataKuliah;
    NodePrasyarat next;

    public NodePrasyarat(MataKuliah mataKuliah) {
        if(mataKuliah == null){
            throw new IllegalArgumentException("Node prasyarat harus menunjuk objek MataKuliah yang valid.");
        }

        this.mataKuliah = mataKuliah;
        this.next = null;
    }
}