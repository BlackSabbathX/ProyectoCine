package Ventana.PeliculaReservas;

import BaseDeDatos.Funcion;
import BaseDeDatos.Pelicula;
import Estructuras.DateTime;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    private VBox funciones;
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
        funciones.getChildren().clear();
        Banner banner = new Banner();
        pelicula = _pelicula;
        imagen.setImage(new Image(new File(pelicula.getImagen()).toURI().toString()));
        rate.setRating(pelicula.getRate());
        nombre.setText(pelicula.getNombre());
        autor.setText(pelicula.getAutor());
        genero.setText(pelicula.getGenero());
        try {
            for (Funcion f : Funcion.getFunciones(_pelicula)) {
                funciones.getChildren().add(banner.nuevoBanner(f));
            }
        } catch (IOException ignored) {
        }
    }

    public void volver() {
        toogleVisible();
        PrincipalUsuario.toogleVisible();
    }

    public void cerrar() {
        toogleVisible();
    }

    private class Banner {

        private Pane nuevoBanner(Funcion funcion) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/Ventana/PeliculaReservas/BannerFuncion.fxml"));

            final Pane p = (Pane) parent.lookup("#panel");
            Label tiempo = (Label) ((GridPane) p.getChildren().get(0)).getChildren().get(0);
            Label precio = (Label) ((GridPane) p.getChildren().get(0)).getChildren().get(1);
            JFXButton reservar = (JFXButton) ((GridPane) p.getChildren().get(0)).getChildren().get(2);

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (funcion.getDisponibles()[i][j]) {
                        reservar.setDisable(false);
                        break;
                    }
                }
            }

            String t;
            DateTime ti = funcion.getTiempo();
            t = ti.getDia().toString() + " a las " + ti.getHora().toString();
            tiempo.setText(t);
            precio.setText("$" + String.valueOf(funcion.getValor()));

            reservar.setOnAction(event -> {
                System.out.println("Reserva " + funcion.getPelicula().getNombre());
            });
            return p;
        }

    }

}
