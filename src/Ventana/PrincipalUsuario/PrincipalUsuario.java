package Ventana.PrincipalUsuario;

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
        String style = "-fx-cursor: hand;";
        ImageView portada = (ImageView) p.getChildren().get(0);
        Label nombre = (Label) p.getChildren().get(1);
        Label autor = (Label) p.getChildren().get(2);
        Label genero = (Label) p.getChildren().get(3);

        portada.setImage(new Image(new File(pelicula.getImagen()).toURI().toString()));
        nombre.setText(pelicula.getNombre());
        autor.setText(pelicula.getAutor());
        genero.setText(pelicula.getGenero());

        portada.setStyle(style);
        nombre.setStyle(style);
        autor.setStyle(style);
        genero.setStyle(style);

        EventHandler evt = (EventHandler<MouseEvent>) event -> {
            System.out.println("click");
        };

        portada.setOnMouseClicked(evt);
        nombre.setOnMouseClicked(evt);
        autor.setOnMouseClicked(evt);
        genero.setOnMouseClicked(evt);
        return p;
    }

}
