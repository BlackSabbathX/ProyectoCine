package Ventana.Pago;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Pago implements Initializable {

    public static Stage pago;
    public static Pago controlador;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (pago.isShowing()) {
            pago.hide();
        } else {
            pago.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
