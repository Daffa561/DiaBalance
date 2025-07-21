package controller.rkrp;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DaftarMakananController implements Initializable {

    @FXML
    private ScrollPane layoutMakanan;
    @FXML
    private VBox Layout1;
    @FXML
    private VBox Layout2;
        @FXML
    private VBox Layout3;
        @FXML
    private VBox Layout4;
        @FXML
    private VBox Layout5;
    @FXML
    private Label umurLabel;
    @FXML
    private Label jenisKelaminLabel;
    @FXML
    Button btnMonitorring;
    @FXML
    Button btnKonsultasi;
    @FXML
    Button btnPengingat;
    @FXML
    Button btnKomunitas;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<MakananData> addedMakananData = addedMakananData(1);
        List<MakananData> addedMakananData1 = addedMakananData(2);
        List<MakananData> addedMakananData2 = addedMakananData(3);
        List<MakananData> addedMakananData3 = addedMakananData(4);
        List<MakananData> addedMakananData4 = addedMakananData(5);
        // loadData();
        try{
            for (int i  = 0; i < addedMakananData.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/rkrp/isimakanan.fxml"));
                VBox cardBox = fxmlloader.load();
                Isimakanan makanaController = fxmlloader.getController();
                makanaController.setMakanan(addedMakananData.get(i));
                Layout1.getChildren().add(cardBox);
            }
            
            for (int i  = 0; i < addedMakananData1.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/rkrp/isimakanan.fxml"));
                VBox cardBox = fxmlloader.load();
                Isimakanan makanaController = fxmlloader.getController();
                makanaController.setMakanan(addedMakananData1.get(i));
                Layout2.getChildren().add(cardBox);
            }
            for (int i  = 0; i < addedMakananData2.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/rkrp/isimakanan.fxml"));
                VBox cardBox = fxmlloader.load();
                Isimakanan makanaController = fxmlloader.getController();
                makanaController.setMakanan(addedMakananData2.get(i));
                Layout3.getChildren().add(cardBox);
            }
            for (int i  = 0; i < addedMakananData3.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/rkrp/isimakanan.fxml"));
                VBox cardBox = fxmlloader.load();
                Isimakanan makanaController = fxmlloader.getController();
                makanaController.setMakanan(addedMakananData3.get(i));
                Layout4.getChildren().add(cardBox);
            }
            for (int i  = 0; i < addedMakananData4.size(); i++){
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/rkrp/isimakanan.fxml"));
                VBox cardBox = fxmlloader.load();
                Isimakanan makanaController = fxmlloader.getController();
                makanaController.setMakanan(addedMakananData4.get(i));
                Layout5.getChildren().add(cardBox);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
        
     private List<MakananData> addedMakananData(int Vbox){
        List<MakananData> list = new ArrayList();
        
        if(Vbox == 1){
        MakananData makanan = new MakananData();
        makanan.setNamaMakanan("Vitamin");
        makanan.setGambarMakanan("/controller/rkrp/image/vitamin3.jpg");
        makanan.setDeskrpsiMakanan("membantu menjaga imunitas tubuh");
        list.add(makanan);
       
        makanan = new MakananData();
        makanan.setNamaMakanan("Alpukat");
        makanan.setGambarMakanan("/controller/rkrp/image/alpukat.jpg");
        makanan.setDeskrpsiMakanan("sebagai pengganti karbohidrat,tapi harus diperhatikan. karena kalorinya tinggi");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Apel");
        makanan.setGambarMakanan("/controller/rkrp/image/apel3.jpg");
        makanan.setDeskrpsiMakanan("karena kaya serat, mampu menunda lonjakan kadar gula");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Ubi jalar");
        makanan.setGambarMakanan("/controller/rkrp/image/ubi.jpg");
        makanan.setDeskrpsiMakanan("Dengan rasa manis alami, sebagai pengganti karbohidrat, diolah dengan cara dipanggang, kukus, atau rebus.");
        list.add(makanan);

    }else if (Vbox == 2){
        MakananData 

        makanan = new MakananData();
        makanan.setNamaMakanan("susu");
        makanan.setGambarMakanan("/controller/rkrp/image/suus.jpg");
        makanan.setDeskrpsiMakanan("sebagai sumber kalsium, hindari susu yang sudah dicampur gula");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Telur");
        makanan.setGambarMakanan("/controller/rkrp/image/telur2.jpg");
        makanan.setDeskrpsiMakanan("makanan rendah lemak, dengan indeks glikemik yang juga rendah");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Yogurt");
        makanan.setGambarMakanan("/controller/rkrp/image/yogurt2.jpg");
        makanan.setDeskrpsiMakanan("Probiotik dalam yoghurt dipercaya juga dapat gula darah tetap normal. konsumsilah yogurt tanpa pemanis.");
        list.add(makanan);        

        makanan = new MakananData();
        makanan.setNamaMakanan("Ayam");
        makanan.setGambarMakanan("/controller/rkrp/image/ayam.jpg");
        makanan.setDeskrpsiMakanan("untuk memenuhi protein harian, tapi buang kulitnya yaa");
        list.add(makanan);

    }else if (Vbox == 3){
        MakananData makanan = new MakananData();
        makanan.setNamaMakanan("Bayam");
        makanan.setGambarMakanan("/controller/rkrp/image/bayam.jpg");
        makanan.setDeskrpsiMakanan("dapat mencegah pengingkatan gula darah");
        list.add(makanan);
       
        makanan = new MakananData();
        makanan.setNamaMakanan("Tomat");
        makanan.setGambarMakanan("/controller/rkrp/image/tomat.jpg");
        makanan.setDeskrpsiMakanan("dapat menurunkan tekanan darah dan meningkatkan HDL (lemak baik)");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Brokoli");
        makanan.setGambarMakanan("/controller/rkrp/image/brokoli.jpg");
        makanan.setDeskrpsiMakanan(" kaya akan serat, vitamin C, dan potasium");
        list.add(makanan);




    }else if (Vbox == 4){
        MakananData makanan = new MakananData();
        makanan = new MakananData();
        makanan.setNamaMakanan("Kacang Polong");
        makanan.setGambarMakanan("/image/kacangpolong.jpg");
        makanan.setDeskrpsiMakanan("kandungan serat, protein, dan mineral yang tinggi dalam kacang polong.");
        list.add(makanan);
       
        makanan = new MakananData();
        makanan.setNamaMakanan("Tahu");
        makanan.setGambarMakanan("/image/tahu.jpg");
        makanan.setDeskrpsiMakanan("menurunkan resiko diabetes tipe2");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Oatmeal");
        makanan.setGambarMakanan("/image/oatmeal.jpg");
        makanan.setDeskrpsiMakanan("mencegah lonjakangula darah, konsumsi yang tidak ada tambahan gula");
        list.add(makanan);

    }else if (Vbox == 5){
        MakananData makanan = new MakananData();
        makanan.setNamaMakanan("Mie Shirataki");
        makanan.setGambarMakanan("/image/mie.jpg");
        makanan.setDeskrpsiMakanan("Mie yang kalori dan karbohidratnya lebih rendah dari mie tepung, aman untuk penderita diabetes");
        list.add(makanan);

        makanan = new MakananData();
        makanan.setNamaMakanan("Salmon");
        makanan.setGambarMakanan("/image/salmon2.jpg");
        makanan.setDeskrpsiMakanan("karena tinggi omega 3, membantu mengurangi komplikasi serangan jantung dan stroke");
        list.add(makanan);
       
        makanan = new MakananData();
        makanan.setNamaMakanan("sarden");
        makanan.setGambarMakanan("/image/sarden.jpg");
        makanan.setDeskrpsiMakanan("karena tinggi omega 3, membantu mengurangi komplikasi serangan jantung dan stroke");
        list.add(makanan);
       
        

        

    }
        return list;
    }

        @FXML
private void balik (ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rekomendasi.fxml"));
        Parent root = loader.load();

        // Dapatkan stage dari button yang ditekan
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void Home(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/HomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
                e.printStackTrace();
        }
    }

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


        

}





   
    
    


