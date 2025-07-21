package controller.monitoring;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.*;

public class MonitoringGulaDarahController implements Initializable {

    @FXML 
    private TextField tfHasilTes;
    @FXML 
    private TextArea areaGrafik;
    @FXML 
    private LineChart<String, Number> lineChartGula;
    @FXML 
    private CategoryAxis xAxisGula;
    @FXML 
    private ChoiceBox<String> txtBox;

    private final List<Integer> dataGulaDarah = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtBox.getItems().addAll("Hari", "Minggu", "Bulan");
        txtBox.setValue("Hari");
        loadDataDariFile();
        txtBox.setOnAction(e -> tampilkanGrafik());
        tampilkanGrafik();
    }

    @FXML
    private void handleSimpan() {
        try {
            int nilai = Integer.parseInt(tfHasilTes.getText());
            dataGulaDarah.add(nilai);
            tfHasilTes.clear();
            simpanDataKeFile();
            tampilkanGrafik();
        } catch (NumberFormatException e) {
            areaGrafik.setText("Masukkan angka yang valid!");
        }
    }

    @FXML
    private void handleHapus() {
        if (!dataGulaDarah.isEmpty()) {
            dataGulaDarah.remove(dataGulaDarah.size() - 1);
            simpanDataKeFile();
            tampilkanGrafik();
        } else {
            areaGrafik.setText("Data kosong, tidak ada yang dihapus.");
        }
    }

    private void tampilkanGrafik() {
        String filter = txtBox.getValue();
        Map<String, List<Integer>> dataKelompok = new LinkedHashMap<>();
        LocalDate start = LocalDate.now().minusDays(dataGulaDarah.size());

        for (int i = 0; i < dataGulaDarah.size(); i++) {
            LocalDate tgl = start.plusDays(i);
            String key;
            switch (filter) {
                case "Minggu":
                    int mingguKe = tgl.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
                    key = "Minggu ke-" + mingguKe + " " + tgl.getYear();
                    break;
                case "Bulan":
                    key = tgl.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + tgl.getYear();
                    break;
                default:
                    key = tgl.toString();
            }

            dataKelompok.computeIfAbsent(key, k -> new ArrayList<>()).add(dataGulaDarah.get(i));
        }

        // Tampilkan di TextArea
        StringBuilder sb = new StringBuilder("Hasil Kadar Gula:\n");
        dataKelompok.forEach((k, v) -> {
            double avg = v.stream().mapToInt(i -> i).average().orElse(0);
            sb.append(k).append(": ").append(String.format("%.2f", avg)).append(" mg/dL\n");
        });
        areaGrafik.setText(sb.toString());

        // Tampilkan di Grafik
        lineChartGula.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        dataKelompok.forEach((k, v) -> {
            double avg = v.stream().mapToInt(i -> i).average().orElse(0);
            series.getData().add(new XYChart.Data<>(k, avg));
        });
        xAxisGula.setCategories(FXCollections.observableArrayList(dataKelompok.keySet()));
        series.setName("Gula Darah");
        lineChartGula.getData().add(series);
    }

    private void simpanDataKeFile() {
        try (PrintWriter pw = new PrintWriter("data.txt")) {
            dataGulaDarah.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataDariFile() {
        File file = new File("data.txt");
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                try {
                    dataGulaDarah.add(Integer.parseInt(sc.nextLine()));
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleKembali(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void balik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/HomePage.fxml"));
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

