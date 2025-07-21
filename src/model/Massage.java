package model;

import java.time.LocalDateTime;

public class Massage {
    String pengirim,isi;
    LocalDateTime waktu;
    public Massage(String pengirim, String isi, LocalDateTime waktu) {
        this.pengirim = pengirim;
        this.isi = isi;
        this.waktu = waktu;
    }
    public String getPengirim() {
        return pengirim;
    }
    
    public String getIsi() {
        return isi;
    }
    
    public LocalDateTime getWaktu() {
        return waktu;
    }
    
    @Override
    public String toString() {
        return "Massage [pengirim=" + pengirim + ", isi=" + isi + ", waktu=" + waktu + "]";
    }
    
    
}
