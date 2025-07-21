package controller.rkrp;



import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class Isimakanan implements Initializable {

    @FXML
    private VBox boxMakanan;
    @FXML
    private ImageView viewMakanan;
    @FXML
    private Text namaMakanan;
    @FXML
    private Text deskripsiMakanan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    private String [] colors = {"White"};
    
    public void setMakanan(MakananData makanan){
    InputStream imgStream = getClass().getResourceAsStream(makanan.getGambarMakanan());
    if (imgStream != null) {
        viewMakanan.setImage(new Image(imgStream));
    } else {
        System.out.println("Gambar tidak ditemukan: " + makanan.getGambarMakanan());
    }

    namaMakanan.setText(makanan.getNamaMakanan());
    deskripsiMakanan.setText(makanan.getDeskrpsiMakanan());
    
    boxMakanan.setStyle("-fx-background-color: " + colors[(int)(Math.random()*colors.length)]);
}
    
}
