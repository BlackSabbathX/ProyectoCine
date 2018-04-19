package Ventana.Reserva;

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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Reserva implements Initializable {

    public static Stage reserva;
    public static Reserva controlador;
    @FXML
    GridPane puestosl;
    @FXML
    GridPane puestosr;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (reserva.isShowing()) {
            reserva.hide();
        } else {
            reserva.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                String path = "";
                ImageView estado = new ImageView(new Image(new File("libre.png").toURI().toString()));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                String path = "";
                ImageView estado = new ImageView(new Image(new File("seleccionado.png").toURI().toString()));
            }
        }
    }

    private void setPelicula(Pelicula _pelicula) {

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
                System.out.println("Reserva " + funcion.getPelicula().getNombre());
            });
            return p;
        }

    }

}
