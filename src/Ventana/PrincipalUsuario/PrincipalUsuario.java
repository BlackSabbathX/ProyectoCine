package Ventana.PrincipalUsuario;

import BaseDeDatos.Pelicula;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
        try {
            Pelicula.init(content);

            Pelicula.add(Pelicula.generateId(), "Mad Max: Fury Road", "Acción - Ciencia Ficción - Futurista", "I Don Know Rick", "madmax.jpg");
            Pelicula.add(Pelicula.generateId(), "Harry Potter Y La Piedra Filosofal", "Ciencia Ficción - Romance", "J. K. Rowling", "hppf.jpg");
            Pelicula.add(Pelicula.generateId(), "Un Puente Hacia Therabithia", "Ciencia Ficción - Romance - Aventura", "Hmmmm...", "puerta.jpg");

            Pelicula.save(content);
            Pelicula.load(content);
            Pelicula.save(content);
            Pelicula.load(content);

            peliculas.getChildren().addAll(
                    nuevoBanner(Pelicula.getPeliculaAt(0)),
                    nuevoBanner(Pelicula.getPeliculaAt(1)),
                    nuevoBanner(Pelicula.getPeliculaAt(2))
            );
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private Pane nuevoBanner(Pelicula pelicula) throws IOException {
        final Pelicula pel = pelicula;
        Parent parent = FXMLLoader.load(getClass().getResource("/Ventana/PrincipalUsuario/BannerPelicula.fxml"));
        final Pane p = (Pane) parent.lookup("#panel");
        ((ImageView) p.getChildren().get(0)).setImage(new Image(new File(pelicula.getImagen()).toURI().toString()));
        ((Label) p.getChildren().get(1)).setText(pelicula.getNombre());
        ((Label) p.getChildren().get(2)).setText(pelicula.getAutor());
        ((Label) p.getChildren().get(3)).setText(pelicula.getGenero());
        p.setOnMouseClicked(event -> {
            p.setStyle("" +
                    "-fx-background-color: #ff6639;"
            );
        });
        return p;
    }

}
