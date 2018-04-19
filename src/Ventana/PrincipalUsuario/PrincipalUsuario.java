package Ventana.PrincipalUsuario;

import BaseDeDatos.Funcion;
import BaseDeDatos.Pelicula;
import BaseDeDatos.Sala;
import Estructuras.DateTime;
import Estructuras.Dia;
import Estructuras.Hora;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

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
            peliculas.getChildren().clear();

            Banner banner = new Banner();

            Pelicula.init(content);
            Sala.init(content);
            Funcion.init(content);

            Pelicula.add(Pelicula.generateId(), "Mad Max: Fury Road", "Acción - Ciencia Ficción - Futurista", "I Don Know Rick", 4.3, "madmax.jpg");
            Pelicula.add(Pelicula.generateId(), "Harry Potter Y La Piedra Filosofal", "Ciencia Ficción - Romance", "J. K. Rowling", 4.5, "hppf.jpg");
            Pelicula.add(Pelicula.generateId(), "Un Puente Hacia Therabithia", "Ciencia Ficción - Romance - Aventura", "Hmmmm...", 3.8, "puerta.jpg");

            Pelicula.save(content);
            Pelicula.load(content);
            Pelicula.save(content);
            Pelicula.load(content);

            Sala.add(Sala.generateId(), "Sala KONDER");
            Sala.add(Sala.generateId(), "Sala Matrix");

            Sala.save(content);
            Sala.load(content);
            Sala.save(content);
            Sala.load(content);

            Funcion.add(Funcion.generateId(), new DateTime(Dia.jueves, Hora.cinco), Sala.getSalaAt(0), Pelicula.getPeliculaAt(1), 7000, new boolean[10][10]);
            Funcion.add(Funcion.generateId(), new DateTime(Dia.jueves, Hora.nueveA), Sala.getSalaAt(1), Pelicula.getPeliculaAt(2), 6000, new boolean[10][10]);
            Funcion.add(Funcion.generateId(), new DateTime(Dia.miercoles, Hora.siete), Sala.getSalaAt(0), Pelicula.getPeliculaAt(2), 6000, new boolean[10][10]);

            Funcion.getFuncionAt(0).setDisponibilidad(true, 2, 3);
            Funcion.getFuncionAt(1).setDisponibilidad(true, 2, 3);
            Funcion.getFuncionAt(2).setDisponibilidad(true, 2, 3);

            Funcion.save(content);
            Funcion.load(content);
            Funcion.save(content);
            Funcion.load(content);

            for (Pelicula p : Pelicula.getPeliculas()) {
                peliculas.getChildren().add(banner.nuevoBanner(p));
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
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
                PeliculaReserva.mostrarPelicula(pelicula);
            };

            portada.setOnMouseClicked(evt);
            nombre.setOnMouseClicked(evt);
            autor.setOnMouseClicked(evt);
            genero.setOnMouseClicked(evt);
            return p;
        }
    }

}
