/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller.konsultasi;

import controller.artikel.DokterData;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ProfilDokterController implements Initializable {

    @FXML
    private VBox boxDokter;
    @FXML
    private ImageView profilDokter;
    @FXML
    private Text namaDokter;
    @FXML
    private Text spesialisDokter;
    @FXML
    private Text pengalaman;
    @FXML
    private Button chatDokter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    private String [] colors = {"White"};
    
    public void setDokter(DokterData dokter){
    InputStream imgStream = getClass().getResourceAsStream(dokter.getGambarDokter());
    if (imgStream != null) {
        profilDokter.setImage(new Image(imgStream));
    } else {
        System.out.println("Gambar tidak ditemukan: " + dokter.getGambarDokter());
    }

    namaDokter.setText(dokter.getNamaDokter());
    spesialisDokter.setText(dokter.getSpesialisDokter());
    pengalaman.setText(dokter.getPengalamanDokter());
    
    boxDokter.setStyle("-fx-background-color: " + colors[(int)(Math.random()*colors.length)]);
}

    @FXML
    private void chat(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/clientdokter/sample.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) chatDokter.getScene().getWindow();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/rekomendasi.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    
     
}
