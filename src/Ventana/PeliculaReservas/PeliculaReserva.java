package Ventana.PeliculaReservas;

import BaseDeDatos.Actual;
import BaseDeDatos.Funcion;
import BaseDeDatos.Pelicula;
import Estructuras.DateTime;
import Ventana.DraggedScene;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import Ventana.Reserva.Reserva;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PeliculaReserva implements Initializable, DraggedScene {

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
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        if (peliculaR.isShowing()) {
            peliculaR.hide();
        } else {
            peliculaR.show();
        }
    }

    public static void mostrarPelicula() {
        if (peliculaR.isShowing()) {
            controlador.setPelicula();
        } else {
            toogleVisible();
            controlador.setPelicula();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
    }

    private void setPelicula() {
        Pelicula _pelicula = Actual.getPelicula();
        funciones.getChildren().clear();
        Banner banner = new Banner();
        imagen.setImage(new Image(new File(_pelicula.getImagen()).toURI().toString()));
        rate.setRating(_pelicula.getRate());
        nombre.setText(_pelicula.getNombre());
        autor.setText(_pelicula.getAutor());
        genero.setText(_pelicula.getGenero());
        try {
            boolean ex = true;
            for (Funcion f : Funcion.getFunciones(_pelicula)) {
                funciones.getChildren().add(banner.nuevoBanner(f));
                ex = false;
            }
            if (ex) {
                funciones.getChildren().add(banner.nuevoBanner(null));
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

            if (funcion == null) {
                tiempo.setText("No hay funciones esta semana");
                precio.setText("");
                reservar.setVisible(false);
                return p;
            }

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
            precio.setText("$ " + String.valueOf(funcion.getValor()) + " c/e");

            reservar.setOnAction(event -> {
                toogleVisible();
                Actual.setFuncion(funcion);
                Reserva.toogleVisible();
                Reserva.controlador.setFuncion();
            });
            return p;
        }

    }

}
