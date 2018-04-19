package Ventana.Reserva;

import BaseDeDatos.Funcion;
import Estructuras.DateTime;
import Ventana.PeliculaReservas.PeliculaReserva;
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
    private boolean[][] selec;
    private int nselecc;

    public static void toogleVisible() {
        if (reserva.isShowing()) {
            reserva.hide();
        } else {
            reserva.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selec = new boolean[10][10];
    }

    public void setFuncion(Funcion _funcion) {
        boolean[][] disp = _funcion.getDisponibles();
        nselecc = 0;
        for (int it = 0; it < 10; it++) {
            for (int jt = 0; jt < 5; jt++) {
                final int i = it;
                final int j = jt;
                final ImageView estado = new ImageView();
                if (disp[i][j]) {
                    estado.setImage(new Image(new File("imagenes/libre_32.png").toURI().toString()));
                    estado.setStyle("-fx-cursor: hand;");
                    estado.setOnMouseClicked(event -> {
                        if (selec[i][j]) {
                            estado.setImage(new Image(new File("imagenes/libre_32.png").toURI().toString()));
                            selec[i][j] = false;
                            nselecc--;
                        } else {
                            estado.setImage(new Image(new File("imagenes/seleccionado_32.png").toURI().toString()));
                            selec[i][j] = true;
                            nselecc++;
                        }
                    });
                } else estado.setImage(new Image(new File("imagenes/ocupado_32.png").toURI().toString()));
                puestosl.add(estado, j, i);
            }
        }
        for (int it = 0; it < 10; it++) {
            for (int jt = 0; jt < 5; jt++) {
                final int i = it;
                final int j = jt + 5;
                final ImageView estado = new ImageView();
                if (disp[i][j]) {
                    estado.setImage(new Image(new File("imagenes/libre_32.png").toURI().toString()));
                    estado.setStyle("-fx-cursor: hand;");
                    estado.setOnMouseClicked(event -> {
                        if (selec[i][j]) {
                            estado.setImage(new Image(new File("imagenes/libre_32.png").toURI().toString()));
                            selec[i][j] = false;
                            nselecc--;
                        } else {
                            estado.setImage(new Image(new File("imagenes/seleccionado_32.png").toURI().toString()));
                            selec[i][j] = true;
                            nselecc++;
                        }
                    });
                } else estado.setImage(new Image(new File("imagenes/ocupado_32.png").toURI().toString()));
                puestosr.add(estado, jt, it);
            }
        }
    }

    public void volver() {
        toogleVisible();
        PeliculaReserva.toogleVisible();
    }

    public void pagar() {

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
