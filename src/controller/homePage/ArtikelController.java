/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.homePage;

import controller.artikel.ArtikelData;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ArtikelController implements Initializable {

    @FXML
    private VBox boxes;
    @FXML
    private ImageView mage;
    @FXML
    private Text judul;
    @FXML
    private Hyperlink link;
    
    private ArtikelData artikel;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private String [] colors = {"White"};
    
     public void setDataDokter(ArtikelData artikel){
    InputStream imgStream = getClass().getResourceAsStream(artikel.getGambar());
    if (imgStream != null) {
        mage.setImage(new Image(imgStream));
    } else {
        System.out.println("Gambar tidak ditemukan: " + artikel.getGambar());
    }
    
    this.artikel = artikel;
    judul.setText(artikel.getJudul());
    link.setText(artikel.getLink());
    boxes.setStyle("-fx-background-color: " + colors[(int)(Math.random()*colors.length)]);
}

    @FXML
    private void OneClick(ActionEvent event) {
        try {
        java.awt.Desktop.getDesktop().browse(new java.net.URI(artikel.getLink()));
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
