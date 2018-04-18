package Ventana.PeliculaReservas;

import BaseDeDatos.Pelicula;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PeliculaReserva implements Initializable {

    public static Stage peliculaR;
    public static PeliculaReserva controlador;
    @FXML
    private ImageView imagen;
    @FXML
    private Rating rate;
    @FXML
    private Label nombre;
    @FXML
    private Label autor;
    @FXML
    private Label genero;
//    @FXML
//    private Pane info;
    @FXML
    private StackPane content;
    private Pelicula pelicula;

    public static void toogleVisible() {
        if (peliculaR.isShowing()) {
            peliculaR.hide();
        } else {
            peliculaR.show();
        }
    }

    public static void mostrarPelicula(Pelicula _pelicula) {
        if (peliculaR.isShowing()) {
            controlador.setPelicula(_pelicula);
        } else {
            toogleVisible();
            controlador.setPelicula(_pelicula);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void setPelicula(Pelicula _pelicula) {
        pelicula = _pelicula;
        imagen.setImage(new Image(new File(pelicula.getImagen()).toURI().toString()));
//        imagen.setX((info.getWidth() / 2) - (imagen.getFitWidth() / 2) + 17);
        rate.setRating(pelicula.getRate());
        nombre.setText(pelicula.getNombre());
        autor.setText(pelicula.getAutor());
        genero.setText(pelicula.getGenero());
    }

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
            System.out.println("click" + pelicula.getNombre());
        };

        portada.setOnMouseClicked(evt);
        nombre.setOnMouseClicked(evt);
        autor.setOnMouseClicked(evt);
        genero.setOnMouseClicked(evt);
        return p;
    }

}
