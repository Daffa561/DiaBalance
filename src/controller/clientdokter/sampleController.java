/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controller.clientdokter;


import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class sampleController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane ap_main;
    @FXML
    private Button button_send;
    @FXML
    private TextField tf_message;
    @FXML
    private ScrollPane sp_main;
    @FXML
    private VBox vbox_messages;
    
    private Client client;
    @FXML
    private Button kembali;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            client = new Client(new Socket("192.168.10.29", 1234));
            System.out.println("Connected to server");
        }catch(IOException e){
            e.printStackTrace();
        }
 
    vbox_messages.heightProperty().addListener(new ChangeListener<Number>() {
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            sp_main.setVvalue((Double) newValue); 
        }
    });

        client.receiveMessageFromServer(vbox_messages);
        
        button_send.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event){
                String messageToSend = tf_message.getText();
                if (!messageToSend.isEmpty()){
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    
                    hBox.setPadding(new Insets(5, 5, 5, 10));
                    Text text = new Text(messageToSend);
                    TextFlow textFlow = new TextFlow(text);
                    
                    textFlow.setStyle("-fx-text-fill: rgb(239,242,255); " +
                                "-fx-background-color: rgb(15,125,242); " +
                                        "-fx-background-radius: 20px;");
                    
                    textFlow.setPadding(new javafx.geometry.Insets(5, 10, 5, 10));
                    text.setFill(Color.color(0.934, 0.945, 0.996));
                    
                    hBox.getChildren().add(textFlow);
                    vbox_messages.getChildren().add(hBox);
                    
                    client.sendMessageToServer(messageToSend);
                    tf_message.clear();  
                }
            }
        });
        
    }
    
    public static void addLabel(String msgFromServer, VBox vBox){
         HBox hBox = new HBox();
          hBox.setAlignment(Pos.CENTER_LEFT);
          hBox.setPadding(new Insets(5, 5, 5 ,10));
          
          Text text = new Text(msgFromServer);
          TextFlow textFlow = new TextFlow(text);
          textFlow.setStyle("-fx-background-color: rgb(233,233,235); " +
                          "-fx-background-radius: 20px;");
          textFlow.setPadding(new Insets(5, 10, 5, 10));
          hBox.getChildren().add(textFlow);
          
      Platform.runLater(new Runnable() {
        @Override
            public void run() {
                vBox.getChildren().add(hBox);
             }
        });
    }

    @FXML
    private void Balik(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homePage/HomePage.fxml"));
                Parent root = loader.load();
                
                Stage stage = (Stage) kembali.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                
            }   catch (Exception e) {
                e.printStackTrace();
        }
    }
}
