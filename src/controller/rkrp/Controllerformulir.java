package controller.rkrp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controllerformulir implements Initializable {

    @FXML private TextField textusia;
    @FXML private TextField textberat;
    @FXML private TextField texttinggi;
    @FXML private Label label2;
    @FXML private ChoiceBox<String> genderChoice;

    @FXML private RadioButton tidak1Button, tidak2Button, tidak3Button, tidak4Button, tidak5Button, tidak6Button;
    @FXML private RadioButton ya1bButton, ya2bButton, ya3bButton, ya4bButton, ya5bButton, ya6bButton;
    @FXML private ToggleGroup tGroup1, tGroup2, tGroup3, tGroup4, tGroup5, tGroup6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderChoice.getItems().addAll("Laki-laki", "Perempuan");
        genderChoice.setValue(" "); // default value
    }

    @FXML
    private void proseshasil(ActionEvent event) {
        String jeniskelamin = genderChoice.getValue();
        String usia = textusia.getText();
        String berat = textberat.getText();
        String tinggi = texttinggi.getText();

        if (jeniskelamin.isEmpty() || usia.isEmpty() || berat.isEmpty() || tinggi.isEmpty()) {
            label2.setText("Isi semua terlebih dahulu");
            return;
        }

        if (!usia.matches("\\d+") || !tinggi.matches("\\d+") || !berat.matches("\\d+")) {
            label2.setText("Usia, tinggi, dan berat harus angka");
            return;
        }

        int usiaInt = Integer.parseInt(usia);
        int beratInt = Integer.parseInt(berat);
        int tinggiInt = Integer.parseInt(tinggi);
        double tinggiMeter = tinggiInt / 100.0;

        int skor = 0;
        if (usiaInt >= 35) skor += 2;

        double bmi = beratInt / (tinggiMeter * tinggiMeter);
        if (bmi <= 24) {
            skor += 0;
        } else if (bmi <= 30) {
            skor += 2;
        } else if (bmi <= 35) {
            skor += 3;
        } else {
            skor += 4;
        }

        boolean jawabSemua =
            (ya1bButton.isSelected() || tidak1Button.isSelected()) &&
            (ya2bButton.isSelected() || tidak2Button.isSelected()) &&
            (ya3bButton.isSelected() || tidak3Button.isSelected()) &&
            (ya4bButton.isSelected() || tidak4Button.isSelected()) &&
            (ya5bButton.isSelected() || tidak5Button.isSelected()) &&
            (ya6bButton.isSelected() || tidak6Button.isSelected());

        if (!jawabSemua) {
            label2.setText("Jawab semua pertanyaan!");
            return;
        }

        if (ya1bButton.isSelected()) skor += 5;
        if (ya2bButton.isSelected()) skor += 3;
        if (ya3bButton.isSelected()) skor += 3;
        if (ya4bButton.isSelected()) skor += 3;
        if (tidak5Button.isSelected()) skor += 2;
        if (tidak6Button.isSelected()) skor += 2;

        String skor1 = ((RadioButton) tGroup1.getSelectedToggle()).getText();
        String skor2 = ((RadioButton) tGroup2.getSelectedToggle()).getText();
        String skor3 = ((RadioButton) tGroup3.getSelectedToggle()).getText();
        String skor4 = ((RadioButton) tGroup4.getSelectedToggle()).getText();
        String skor5 = ((RadioButton) tGroup5.getSelectedToggle()).getText();
        String skor6 = ((RadioButton) tGroup6.getSelectedToggle()).getText();

        simpanKeXML(usia, berat, tinggi, jeniskelamin, skor1, skor2, skor3, skor4, skor5, skor6, skor);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/rekomendasi.fxml"));
            Parent root = loader.load();

            ControlllerRKRPrekomendasi hasilcontroller = loader.getController();
            hasilcontroller.setSkor(skor);
            hasilcontroller.dapetSkor(skor1, skor2, skor3, skor4, skor5, skor6);
            hasilcontroller.setform(jeniskelamin, usia, berat, tinggi);

            Stage stage = (Stage) label2.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void simpanKeXML(String usia, String berat, String tinggi, String gender,
                             String jawaban1, String jawaban2, String jawaban3,
                             String jawaban4, String jawaban5, String jawaban6, int skor) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            Element root;

            File file = new File("data_formulir.xml");
            if (file.exists()) {
                doc = builder.parse(file);
                root = doc.getDocumentElement();
            } else {
                doc = builder.newDocument();
                root = doc.createElement("DataFormulir");
                doc.appendChild(root);
            }

            Element data = doc.createElement("Data");
            data.appendChild(buatElemen(doc, "Usia", usia));
            data.appendChild(buatElemen(doc, "Berat", berat));
            data.appendChild(buatElemen(doc, "Tinggi", tinggi));
            data.appendChild(buatElemen(doc, "Gender", gender));
            data.appendChild(buatElemen(doc, "Jawaban1", jawaban1));
            data.appendChild(buatElemen(doc, "Jawaban2", jawaban2));
            data.appendChild(buatElemen(doc, "Jawaban3", jawaban3));
            data.appendChild(buatElemen(doc, "Jawaban4", jawaban4));
            data.appendChild(buatElemen(doc, "Jawaban5", jawaban5));
            data.appendChild(buatElemen(doc, "Jawaban6", jawaban6));
            data.appendChild(buatElemen(doc, "Skor", String.valueOf(skor)));

            root.appendChild(data);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(file));

            System.out.println("Data berhasil disimpan ke data_formulir.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Element buatElemen(Document doc, String nama, String nilai) {
        Element elem = doc.createElement(nama);
        elem.appendChild(doc.createTextNode(nilai));
        return elem;
    }

    // NAVIGASI BUTTON: langsung pakai FXMLLoader
    @FXML private void Home(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/HomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void Risiko(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/rkrp/formulir.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void Monitoring(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/monitoring/monitoring_gula_darah.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void Komunitas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/grupDanKomunitas/GrupdanKomunitasNew.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void Pengingat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reminder/ReminderPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void Konsultasi(ActionEvent event) {
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
