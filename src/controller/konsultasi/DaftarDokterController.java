/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.konsultasi;

import controller.artikel.DokterData;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DaftarDokterController implements Initializable {

    @FXML
    private ScrollPane layoutDokter;
    @FXML
    private VBox Layout1;
    @FXML
    private VBox Layout2;
    
    
    private List<DokterData>addedDataDokter;
    @FXML
    private VBox layout3;
    @FXML
    private VBox layout4;
    @FXML
    private VBox layout5;
    @FXML
    private Button Home;
    @FXML
    private Button Monitoring;
    @FXML
    private Button Risiko;
    @FXML
    private Button Back;
    @FXML
    private Button komunitas;
    @FXML
    private Button pengingat;
    @FXML
    private Button konsultasi;
   
    @FXML
    private void Risiko(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/formulir.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    
    @FXML
     private void Home(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/HomePage.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) Home.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                
            }   catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<DokterData> addedDataDokter = addedDataDokter(1);
        List<DokterData> addedDataDokter1 = addedDataDokter(2);
        List<DokterData> addedDataDokter2 = addedDataDokter(3);
        List<DokterData> addedDataDokter3 = addedDataDokter(4);
        try{
            for (int i  = 0; i < addedDataDokter.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/konsultasi/profilDokter.fxml"));
                VBox cardBox = fxmlloader.load();
                ProfilDokterController doktercntrl = fxmlloader.getController();
                doktercntrl.setDokter(addedDataDokter.get(i));
                Layout1.getChildren().add(cardBox);
            }
            
            for (int i  = 0; i < addedDataDokter1.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/konsultasi/profilDokter.fxml"));
                VBox cardBox = fxmlloader.load();
                ProfilDokterController doktercntrl = fxmlloader.getController();
                doktercntrl.setDokter(addedDataDokter1.get(i));
                Layout2.getChildren().add(cardBox);
            }
            
             for (int i  = 0; i < addedDataDokter2.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/konsultasi/profilDokter.fxml"));
                VBox cardBox = fxmlloader.load();
                ProfilDokterController doktercntrl = fxmlloader.getController();
                doktercntrl.setDokter(addedDataDokter2.get(i));
                layout3.getChildren().add(cardBox);
             }
             
              for (int i  = 0; i < addedDataDokter3.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/konsultasi/profilDokter.fxml"));
                VBox cardBox = fxmlloader.load();
                ProfilDokterController doktercntrl = fxmlloader.getController();
                doktercntrl.setDokter(addedDataDokter3.get(i));
                layout4.getChildren().add(cardBox);
             }
            
        }catch (IOException e){
            e.printStackTrace();
        }
    }
        
     private List<DokterData> addedDataDokter(int Vbox){
        List<DokterData> list = new ArrayList();
        
        if(Vbox == 1){
        DokterData dokter = new DokterData();
        dokter.setNamaDokter("Dr. Azmii");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Paru-Paru");
        dokter.setPengalamanDokter("12 Tahun");
        list.add(dokter);
       
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Khairus");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("1 Tahun");
        list.add(dokter);
        
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Daffa");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Penyakit dalam");
        dokter.setPengalamanDokter("12 Tahun");
        list.add(dokter);
        
        
    }else if (Vbox == 2){
        DokterData dokter = new DokterData();
        dokter.setNamaDokter("Dr. Tirta pepeng");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Paru-Paru");
        dokter.setPengalamanDokter("12 Tahun");
        list.add(dokter);
       
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Musa");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("12 Tahun");
        list.add(dokter);
        
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Juan");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("1 Tahun");
        list.add(dokter);
        
    }else if (Vbox == 3){
         DokterData dokter = new DokterData();
        dokter.setNamaDokter("Dr. Tirta pepeng");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Paru-Paru");
        dokter.setPengalamanDokter("12 Tahun");
        list.add(dokter);
       
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Duta");
        dokter.setGambarDokter("/controller/rkrp/image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("14 Tahun");
        list.add(dokter);
        
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Kevin");
        dokter.setGambarDokter("/controller/rkrp/Image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("1 Tahun");
        list.add(dokter);
        
    }else if (Vbox == 4){
         DokterData dokter = new DokterData();
        dokter.setNamaDokter("Dr. Matius");
        dokter.setGambarDokter("/controller/rkrp/Image/Dokter.png");
        dokter.setSpesialisDokter("Pari-Paru");
        dokter.setPengalamanDokter("12 Tahun");
        list.add(dokter);
       
        dokter = new DokterData();
        dokter.setNamaDokter("Dr. Yosa");
        dokter.setGambarDokter("/controller/rkrp/Image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("1 Tahun");
        list.add(dokter);
        
         dokter = new DokterData();
        dokter.setNamaDokter("Dr. Romus");
        dokter.setGambarDokter("/controller/rkrp/Image/Dokter.png");
        dokter.setSpesialisDokter("Gizi");
        dokter.setPengalamanDokter("1 Tahun");
        list.add(dokter);
        
    }
        return list;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/konsultasi/rekomendasi.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
   
    
    


