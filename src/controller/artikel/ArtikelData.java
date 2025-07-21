/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.artikel;

/**
 *
 * @author LENOVO
 */
public class ArtikelData {
    private String Judul;
    private String link;
    private String gambar;
    private String baca;

    public ArtikelData(){
        
    }
    
    public String getBaca() {
        return baca;
    }

    public void setBaca(String baca) {
        this.baca = baca;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String Judul) {
        this.Judul = Judul;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
    
    
}
