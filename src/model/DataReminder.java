package model;

public class DataReminder {
    private String hari,jam,deskripsi;

    public DataReminder(String hari, String deskripsi) {
        this.hari = hari;
        this.deskripsi = deskripsi;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    
}
