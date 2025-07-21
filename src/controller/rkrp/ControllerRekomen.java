package controller.rkrp;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ControllerRekomen {
    @FXML
    HBox boxrekomen;
    @FXML
    ImageView viewrekomen;
    @FXML
    Text kalimat1;
    @FXML
    Text kalimat2;
    @FXML
    Button rekomendasidokter;

      private String [] colors = {"White"};


        public void setRekomen(RekomenData rekomen){
    InputStream imgStream = getClass().getResourceAsStream(rekomen.getGambarrekomen());
    if (imgStream != null) {
        viewrekomen.setImage(new Image(imgStream));
    } else {
        System.out.println("Gambar tidak ditemukan: " + rekomen.getGambarrekomen());
    }

    kalimat1.setText(rekomen.getJudulrekomen());
    kalimat2.setText(rekomen.getKalimatrekomen());
    
    boxrekomen.setStyle("-fx-background-color: " + colors[(int)(Math.random()*colors.length)]);




}
        public void buttonrekomendokter(ActionEvent event){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/konsultasi/DaftarDokter.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
                }

    }

}
