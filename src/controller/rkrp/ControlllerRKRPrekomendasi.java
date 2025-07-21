package controller.rkrp;

import javafx.scene.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
 
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.ArrayList;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControlllerRKRPrekomendasi implements Initializable {
    @FXML
    Button btnrekomenDokter;
    @FXML
    Button btnrekomenMakanan;
    @FXML
    Label labelskor;
    @FXML
    Label labelkategori;
    @FXML
    HBox resiko;
    @FXML
    Label panah;
    @FXML
    Label labelusia;
    @FXML
    Label labeljeniskelamin;
    @FXML
    Label labelberat;
    @FXML
    Label labeltinggi;
    @FXML
    HBox boxRekomen;
    
    @FXML
    Label labelskor1;
    @FXML
    Label labelskor2;
    @FXML
    Label labelskor3;
    @FXML
    Label labelskor4;
    @FXML
    Label labelskor5;
    @FXML
    Label labelskor6;

    @FXML
    Button btnMonitorring;
    @FXML
    Button btnKonsultasi;
    @FXML
    Button btnPengingat;
    @FXML
    Button btnKomunitas;



    public void setform(String inputkelamin, String inputusia, String inputberat, String inputtinggi){
        labeljeniskelamin.setText("Jenis kelamin: "+inputkelamin);
        labelusia.setText("Usia: "+inputusia);
        labelberat.setText("Berat badan: "+ inputberat);
        labeltinggi.setText("Tinggi badan "+ inputtinggi);

    }

    public void dapetSkor(String skor1, String skor2, String skor3, String skor4, String skor5, String skor6){
        labelskor1.setText(skor1);
        labelskor2.setText(skor2);
        labelskor3.setText(skor3);
        labelskor4.setText(skor4);
        labelskor5.setText(skor5);
        labelskor6.setText(skor6);

    }

    // public void setData(String berat,String tinggi, String usia, String skor, String inputkelamin, String kategori){

    // }
    private int skor;

    public int getSkor() {
        return skor;
    }
    public void setSkor(int skor){
        this.skor = skor;
        labelskor.setText("    "+skor);
        String kategori;

        if(skor<8){
            kategori = "resiko rendah";
        }else if (skor < 16) {
            kategori = "resiko sedang";
                        
        }else{
            kategori = "resiko tinggi";

            
        }

        labelkategori.setText(kategori);
        final double totalBarWidth = 564;
        final double maxSkor = 24.0;      

        double posX = (skor / maxSkor) * totalBarWidth;

        panah.setTranslateX(posX - (totalBarWidth / 2));
        labelskor.setTranslateX(posX - (totalBarWidth / 2));
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        }
        private void loadData() {
    try {
        File xmlFile = new File("data_formulir.xml");
        if (!xmlFile.exists()) return;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("Data");
        if (nodeList.getLength() > 0) {
            Element dataElement = (Element) nodeList.item(nodeList.getLength() - 1); // pakai data terakhir

            String usia = getTagValue("Usia", dataElement);
            String gender = getTagValue("Gender", dataElement);
            String berat = getTagValue("Berat", dataElement);
            String tinggi = getTagValue("Tinggi", dataElement);
            int skor = Integer.parseInt(getTagValue("Skor", dataElement));

            // Set label
            labelusia.setText("Usia : " + usia);
            labeljeniskelamin.setText("Jenis Kelamin: " + gender);
            labelberat.setText("Berat Badan: " + berat);
            labeltinggi.setText("Tinggi Badan: " + tinggi);

            // Set skor dan kategori
            setSkor(skor);

            // Set jawaban
            labelskor1.setText(getTagValue("Jawaban1", dataElement));
            labelskor2.setText(getTagValue("Jawaban2", dataElement));
            labelskor3.setText(getTagValue("Jawaban3", dataElement));
            labelskor4.setText(getTagValue("Jawaban4", dataElement));
            labelskor5.setText(getTagValue("Jawaban5", dataElement));
            labelskor6.setText(getTagValue("Jawaban6", dataElement));
        }
        
        String kategori;
        if (skor < 8) {
            kategori = "resiko rendah";
        } else if (skor < 16) {
            kategori = "resiko sedang";
           loadRekomendasi();
        } else {
            kategori = "resiko tinggi";
            loadRekomendasi();
        }

        labelkategori.setText(kategori);

        final double totalBarWidth = 564;
        final double maxSkor = 24.0;
        double posX = (skor / maxSkor) * totalBarWidth;
        panah.setTranslateX(posX - (totalBarWidth / 2));
        labelskor.setTranslateX(posX - (totalBarWidth / 2));

    } catch (Exception e) {
        e.printStackTrace();
    }
}



     private String getTagValue(String tag, Element element) {
        NodeList nList = element.getElementsByTagName(tag);
        if (nList.getLength() > 0 && nList.item(0).getTextContent() != null) {
            return nList.item(0).getTextContent();
        }
        return "Tidak ada";
    }
        private List<RekomenData> addRekomenDatas(){
        List<RekomenData> list = new ArrayList();
        RekomenData rekomen = new RekomenData();
        rekomen.setJudulrekomen("Rekomendasi Dokter");
        rekomen.setKalimatrekomen("Konsultasi dengan dokter terpercaya berdasarkan riwayat dan preferensi Anda");
        rekomen.setGambarrekomen("/controller/rkrp/image/hhh.jpg");
        list.add(rekomen);
        return list;
    }


    public void rekomendasiMakanan(){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/Daftarmakanan.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) labelskor.getScene().getWindow();
            stage.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
                }

    }
    public void rekomendasiDokter(){
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/Daftarmakanan.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) labelskor.getScene().getWindow();
            stage.setScene(new Scene(root));

            } catch (IOException e) {
                e.printStackTrace();
                }

    }
    private void loadRekomendasi() {
        List<RekomenData> addedMRekomenDatas = addRekomenDatas();
        try {
            for (int i = 0; i < addedMRekomenDatas.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/view/rkrp/RekomendasiDokter.fxml"));
                HBox cardBox = fxmlloader.load();
                ControllerRekomen rekomenController = fxmlloader.getController();
                rekomenController.setRekomen(addedMRekomenDatas.get(i));
                boxRekomen.getChildren().add(cardBox);
            }
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

 

