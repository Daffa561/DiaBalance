/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller.loginController;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.reminder.ReminderPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import helper.DatabaseHelper;

public class TombolLoginController implements Initializable {
    private int currentUserId;
    
    @FXML
    private Label label;
    
    @FXML
    private TextField TfNama,TfPassword;
    
    @FXML
    private Hyperlink link;
    
    @FXML
    private Button button;
    
    @FXML
private void handleButtonAction(ActionEvent event) {
    String nama = TfNama.getText();
    String password = TfPassword.getText();

    int userId = DatabaseHelper.login(nama, password);
    if (userId != -1) {
        this.currentUserId = userId; // simpan kalau perlu
        helper.SessionData.currentUserId = userId; // set global
        helper.SessionData.currentUsername = nama;

        label.setText("✅ Login berhasil!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/HomePage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        label.setText("❌ Username atau password salah!");
    }
}

    
    @FXML private void Daftar (){
          try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/daftar/Daftar.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) button.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                
            }   catch (Exception e) {
                e.printStackTrace();
        }
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
