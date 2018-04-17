package Ventana.PrincipalUsuario;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalUsuario implements Initializable {

    public static Stage usuario;
    public static PrincipalUsuario controlador;
    @FXML
    private StackPane content;
    @FXML
    private VBox peliculas;

    public static void toogleVisible() {
        if (usuario.isShowing()) {
            usuario.hide();
        } else {
            usuario.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        peliculas.getChildren().add(BannerPelicula.nuevoBanner());
    }

    static class BannerPelicula {
        static Pane nuevoBanner() {
            Pane panel = new Pane();
            panel.getChildren().add(new Label("Jose padisanakndf"));
            return panel;
        }
    }

}
