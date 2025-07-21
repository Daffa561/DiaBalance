/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.homePage;

import controller.artikel.*;
import helper.SessionData;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class HomePageController implements Initializable {

    @FXML
    private HBox Articel;
    
    private List<ArtikelData>addedArtikel;
    @FXML
    private Button konsultasi;
    @FXML
    private Button Monitoring;
    @FXML
    private Button pindahRisko;

    @FXML
    private Label labelProfil,txtProfil;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addedArtikel = addedArtikel();

        labelProfil.setText("Halo, " + SessionData.currentUsername);
        txtProfil.setText("Selamat datang, " + SessionData.currentUsername + "!");
        try{
            for (int i  = 0; i < addedArtikel.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/homePage/Artikel.fxml"));
                VBox cardBox = fxmlloader.load();
                ArtikelController artikelController = fxmlloader.getController();
                artikelController.setDataDokter(addedArtikel.get(i));
                Articel.getChildren().add(cardBox);
            }
        }catch (IOException e){
            e.printStackTrace();
        }  
    }    
    private List<ArtikelData> addedArtikel(){
        List<ArtikelData> list = new ArrayList();
        
        
        ArtikelData artikel = new ArtikelData();
        artikel.setJudul("WASPADAI GEJALA DIABETES TIPE 1 PADA ANAK");
        artikel.setGambar("/controller/rkrp/image/Artikel.jpg");
        artikel.setLink("http://www.rsiadedarikupang.com/read/waspadai-gejala-diabetes-tipe-1");
        list.add(artikel);
        
        artikel = new ArtikelData();
        artikel.setJudul("OLAHRAGA YANG COCOK UNTUK PENDERITA DIABETES");
        artikel.setGambar("/controller/rkrp/image/Artikel2.jpg");
        artikel.setLink("https://homecareindonesia.co.id/tag/diabetes/");
        list.add(artikel);

        
        
        return list;
    }
    
    @FXML
    private void Risiko(ActionEvent event) {
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/formulir.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) pindahRisko.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                
        }   catch (Exception e) {
                e.printStackTrace();
        }
    }

    @FXML
    private void Monitoring(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/monitoring/monitoring_gula_darah.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @FXML
    private void Komunitas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/grupDanKomunitas/GrupdanKomunitasNew.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @FXML
    private void Pengingat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reminder/ReminderPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @FXML
    private void Konsultasi(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/konsultasi/DaftarDokter.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    @FXML
    private void Logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/TombolLogin.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
