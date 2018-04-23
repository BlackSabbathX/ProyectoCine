package Ventana.PrincipalAdministrador;

import BaseDeDatos.Actual;
import BaseDeDatos.Pelicula;
import Ventana.DraggedScene;
import Ventana.Login.Login;
import Ventana.PeliculaReservas.PeliculaReserva;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalAdministrador implements Initializable, DraggedScene {

    public static Stage usuario;
    public static PrincipalAdministrador controlador;
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        if (usuario.isShowing()) {
            usuario.hide();
        } else {
            usuario.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onDraggedScene(pane);
    }

    public void volver() {
        toogleVisible();
        Login.toogleVisible();
    }

    public void cerrar() {
        toogleVisible();
    }

    private class Banner {

        private Pane nuevoBanner(Pelicula pelicula) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/Ventana/PrincipalUsuario/BannerPelicula.fxml"));

            final Pane p = (Pane) parent.lookup("#panel");
            String style = "-fx-cursor: hand;";
            ImageView portada = (ImageView) p.getChildren().get(0);
            Label nombre = (Label) p.getChildren().get(1);
            Label autor = (Label) p.getChildren().get(2);
            Label genero = (Label) p.getChildren().get(3);
            Rating rate = (Rating) p.getChildren().get(4);

            portada.setImage(new Image(new File(pelicula.getImagen()).toURI().toString()));
            nombre.setText(pelicula.getNombre());
            autor.setText(pelicula.getAutor());
            genero.setText(pelicula.getGenero());
            rate.setRating(pelicula.getRate());

            portada.setStyle(style);
            nombre.setStyle(style);
            autor.setStyle(style);
            genero.setStyle(style);

            EventHandler evt = (EventHandler<MouseEvent>) event -> {
                toogleVisible();
                Actual.setPelicula(pelicula);
                PeliculaReserva.mostrarPelicula();
            };

            portada.setOnMouseClicked(evt);
            nombre.setOnMouseClicked(evt);
            autor.setOnMouseClicked(evt);
            genero.setOnMouseClicked(evt);
            return p;
        }
    }

}
