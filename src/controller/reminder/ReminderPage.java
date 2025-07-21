package controller.reminder;

import helper.DatabaseHelper;
import helper.SessionData;
import model.Reminder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.time.LocalDate;

public class ReminderPage {
    @FXML private HBox reminderContainer;

    @FXML
    public void initialize() {
        // Load reminders dari database, hanya saat queue kosong (pertama buka)
        if (SessionData.reminderQueue.isEmpty()) {
            DatabaseHelper.getRemindersByUserId(SessionData.currentUserId)
                .forEach(SessionData.reminderQueue::add);
        }

        // Tampilkan semua di UI
        reminderContainer.getChildren().clear();
        for (Reminder r : SessionData.reminderQueue) {
            addReminderToUI(r.getJudul(), r.getDeskripsi(), r.getTanggal());
        }
    }

    @FXML
    private void handleTambahJadwal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reminder/AddReminder.fxml"));
            Parent root = loader.load();

            AddReminder controller = loader.getController();
            controller.setParentController(this);

            Stage stage = new Stage();
            stage.setTitle("Tambah Reminder");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) { showError("Gagal membuka form tambah reminder"); }
    }

    public void addReminder(String judul, String deskripsi, LocalDate tanggal) {
        // Tambah ke DB
        DatabaseHelper.addReminder(SessionData.currentUserId, judul, tanggal.toString(), deskripsi);
        // Tambah ke Queue global
        SessionData.reminderQueue.add(new Reminder(judul, deskripsi, tanggal));
        // Tambah ke UI
        addReminderToUI(judul, deskripsi, tanggal);
    }

    private void addReminderToUI(String judul, String deskripsi, LocalDate tanggal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reminder/ReminderDiatur.fxml"));
            Parent card = loader.load();

            ReminderDiatur controller = loader.getController();
            controller.setData(judul, deskripsi, tanggal, this);

            reminderContainer.getChildren().add(card);
        } catch (IOException e) { showError("Gagal menambahkan reminder ke tampilan"); }
    }

    public void removeFirstReminder(Parent card, String judul, LocalDate tanggal) {
        // Hapus dari database
        DatabaseHelper.removeUserReminder(SessionData.currentUserId, judul, tanggal);

        // Hapus dari queue
        SessionData.reminderQueue.removeIf(r -> r.getJudul().equals(judul) && r.getTanggal().equals(tanggal));

        // Hapus dari UI
        reminderContainer.getChildren().remove(card);

        showInfo("Reminder selesai!");
    }


    private void showError(String msg) { new Alert(Alert.AlertType.ERROR, msg).showAndWait(); }
    private void showInfo(String msg) { new Alert(Alert.AlertType.INFORMATION, msg).showAndWait(); }

    // Navigasi lain (contoh): pastikan SessionData.currentUserId sudah di-set
    // @FXML private void goPengingat(ActionEvent e) {
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reminder/ReminderPage.fxml"));
    //         Parent root = loader.load();
    //         Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    //         stage.setScene(new Scene(root));
    //     } catch (IOException ex) { ex.printStackTrace(); }
    // }
    // }


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
