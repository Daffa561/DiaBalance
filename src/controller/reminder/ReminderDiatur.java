package controller.reminder;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import java.time.LocalDate;

public class ReminderDiatur {
    @FXML private Label lblHari;
    @FXML private Label lblTanggal;
    @FXML private Label lblJudul;
    @FXML private Label lblDeskripsi;

    private controller.reminder.ReminderPage parent;

    public void setData(String judul, String deskripsi, LocalDate tanggal, ReminderPage parent) {
        this.parent = parent;
        lblJudul.setText(judul);
        lblDeskripsi.setText(deskripsi);
        lblTanggal.setText(tanggal.toString());

        // opsional, set hari dari tanggal:
        String hariSingkat = tanggal.getDayOfWeek().toString().substring(0,3).toUpperCase();
        lblHari.setText(hariSingkat);
    }

    @FXML
    private void handleSelesai() {
        Parent card = lblJudul.getParent().getParent();
        parent.removeFirstReminder(card, lblJudul.getText(), LocalDate.parse(lblTanggal.getText()));
    }
}
