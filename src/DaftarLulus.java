public class DaftarLulus{
    NodeLulus head;

    public DaftarLulus(){
        this.head = null;
    }

    public boolean sudahLulus(String kode) {
        if (kode == null) return false;
        kode = kode.trim();

        NodeLulus temp = head;
        while (temp != null) {
            if (temp.kode.equalsIgnoreCase(kode)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void tambahLulus(String kode){
        if (kode == null) return;
        kode = kode.trim();
        if (kode.isEmpty() || sudahLulus(kode)) return;

        NodeLulus baru = new NodeLulus(kode);
        if (head == null) {
            head = baru;
        } else {
            NodeLulus temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
    }
}