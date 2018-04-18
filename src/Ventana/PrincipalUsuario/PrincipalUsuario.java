package Ventana.PrincipalUsuario;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
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
        peliculas.getChildren().add(nuevoBanner());
        peliculas.getChildren().add(nuevoBanner());
        peliculas.getChildren().add(nuevoBanner());
        peliculas.getChildren().add(nuevoBanner());
    }

    private Pane nuevoBanner() {
        HBox panel = new HBox();
//        panel.getStylesheets().add("Login.css");
        ImageView portada = new ImageView(new Image(new File("j.jpeg").toURI().toString()));
        portada.setFitHeight(100);
        portada.setPreserveRatio(true);
        panel.getChildren().add(portada);
        panel.getChildren().add(new Label("Jose padisanakndf"));
        return panel;
    }

}