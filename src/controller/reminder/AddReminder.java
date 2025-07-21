package controller.reminder;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class AddReminder {
    @FXML private TextField txtJudul;
    @FXML private TextArea txtDeskripsi;
    @FXML private DatePicker tanggalPicker;

    private ReminderPage parent;

    /**
     * Setter yang dipanggil dari ReminderPage supaya controller ini tahu parent-nya
     */
    public void setParentController(ReminderPage parent) {
        this.parent = parent;
    }

    /**
     * Event handler tombol "Simpan"
     */
    @FXML
    private void handleSubmit() {
        String judul = txtJudul.getText();
        String deskripsi = txtDeskripsi.getText();
        LocalDate tanggal = tanggalPicker.getValue();

        if (judul == null || judul.isEmpty() || tanggal == null) {
            showWarning("Judul dan tanggal wajib diisi!");
            return;
        }

        // Panggil method di parent untuk tambah reminder baru
        parent.addReminder(judul, deskripsi, tanggal);

        // Tutup window AddReminder
        Stage stage = (Stage) txtJudul.getScene().getWindow();
        stage.close();
    }

    /**
     * Helper untuk tampilkan pesan peringatan
     */
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
