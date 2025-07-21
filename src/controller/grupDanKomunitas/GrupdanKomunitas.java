package controller.grupDanKomunitas;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class GrupdanKomunitas {

    @FXML
    private ListView<String> listGrup,listDiskusi;
    
    @FXML
    private Label labelNamaGrup;
    
    @FXML
    private Label labelDeskripsi;
    
    @FXML
    private Button btnGabung;
    
    @FXML
    private Button btnKeluar;
    
    @FXML
    private TextField komentarField;

    private ObservableList<String> daftarGrup = FXCollections.observableArrayList(
        "Grup Sehat", "Komunitas Diabetes", "Pecinta Herbal"
    );

    private ObservableList<String> isiDiskusi = FXCollections.observableArrayList();

    private boolean sudahGabung = false;

    @FXML
    public void initialize() {
        listGrup.setItems(daftarGrup);
        listDiskusi.setItems(isiDiskusi);

        // Disable tombol saat belum gabung
        btnGabung.setDisable(true);
        btnKeluar.setDisable(true);
        komentarField.setDisable(true);

        // Inisialisasi awal data grup dan tampilan
        // listGrup.getItems().addAll("Grup Diabetes Tipe 1", "Grup Gaya Hidup Sehat", "Grup Edukasi Diabetes", "Grup Pejuang Sehat");
        // labelNamaGrup.setText("Pilih grup untuk melihat detail.");
        // labelDeskripsi.setText("");
        // listDiskusi.getItems().clear();
        // btnGabung.setDisable(false);
        // btnKeluar.setDisable(true);

        // Listener saat memilih grup
        listGrup.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                labelNamaGrup.setText(newVal);
                labelDeskripsi.setText("Deskripsi untuk " + newVal);
                isiDiskusi.clear();
                // isiDiskusi.add("Selamat datang di " + newVal + "!");
                // isiDiskusi.add("Silakan mulai berdiskusi...");

                btnGabung.setDisable(false);
                btnKeluar.setDisable(true);
                komentarField.setDisable(true);
                sudahGabung = false;

                
            }
        });
    }

    @FXML
    private void handleGabungGrup() {
        sudahGabung = true;
        btnGabung.setDisable(true);
        btnKeluar.setDisable(false);
        komentarField.setDisable(false);
        isiDiskusi.add("Anda telah bergabung dalam grup.");
        isiDiskusi.add("Selamat Datang!");
        isiDiskusi.add("Silakan mulai berdiskusi...");
    }

    @FXML
    private void handleKeluarGrup() {
        sudahGabung = false;
        btnGabung.setDisable(false);
        btnKeluar.setDisable(true);
        komentarField.setDisable(true);
        isiDiskusi.add("Anda telah keluar dari grup.");
    }

    @FXML
    private void handleKirimKomentar() {
        if (!sudahGabung) {
            isiDiskusi.add("Anda harus bergabung dulu untuk mengirim komentar.");
            return;
        }

        String komentar = komentarField.getText();
        if (komentar != null && !komentar.trim().isEmpty()) {
            isiDiskusi.add("Anda: " + komentar);
            komentarField.clear();
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

    
}
