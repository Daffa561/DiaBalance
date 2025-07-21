package controller.daftarController;

import java.net.URL;
import java.util.ResourceBundle;
import helper.DatabaseHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DaftarController implements Initializable {

    @FXML
    private Button buatAkun;
    
    @FXML 
    private TextField Password;
    
    @FXML
    private TextField Username;
    
    @FXML
    private TextField Email;
    
    @FXML
    private Label Peringatan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML 
    private void kembaliHalamanLogin(ActionEvent event) {
        try {
            String username = Username.getText().trim(); 
            String password = Password.getText().trim(); 
            String email = Email.getText().trim();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Peringatan.setText("❌ Semua field harus diisi!");
                return;
            }

            DatabaseHelper.register(username, password, email);
            Peringatan.setText("✅ Register berhasil!");
            
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/TombolLogin.fxml"));
                            Parent root = loader.load();
                            
                            Stage stage = (Stage) buatAkun.getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                1500 // Delay 1.5 detik
            );
                
        } catch (Exception e) {
            Peringatan.setText("❌ Gagal register: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML 
    private void Masuk(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login/TombolLogin.fxml"));
            Parent root = loader.load();
                
            Stage stage = (Stage) buatAkun.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
                
        } catch (Exception e) {
            Peringatan.setText("❌ Gagal pindah halaman: " + e.getMessage());
            e.printStackTrace();
        }
    }
}