package model;

import java.time.LocalDate;

public class Reminder {
    private String judul, deskripsi;
    private LocalDate tanggal;

    public Reminder(String judul, String deskripsi, LocalDate tanggal) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public String getJudul() { return judul; }
    public String getDeskripsi() { return deskripsi; }
    public LocalDate getTanggal() { return tanggal; }
}
