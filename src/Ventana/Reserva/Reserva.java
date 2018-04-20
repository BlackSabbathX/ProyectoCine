package Ventana.Reserva;

import BaseDeDatos.Actual;
import BaseDeDatos.Funcion;
import Ventana.DraggedScene;
import Ventana.Pago.Pago;
import Ventana.PeliculaReservas.PeliculaReserva;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Reserva implements Initializable, DraggedScene {

    public static Stage reserva;
    public static Reserva controlador;
    @FXML
    private GridPane puestosl;
    @FXML
    private GridPane puestosr;
    @FXML
    private Label valor;
    @FXML
    private Label puestos;
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane pane;
    private boolean[][] selec;
    private int nselecc;
    private float total;

    public static void toogleVisible() {
        if (reserva.isShowing()) {
            reserva.hide();
        } else {
            reserva.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onDraggedScene(pane);
        selec = new boolean[10][10];
    }

    public void setFuncion() {
        Funcion _funcion = Actual.getFuncion();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                selec[i][j] = false;
            }
        }
        boolean[][] disp = _funcion.getDisponibles();
        nselecc = 0;
        total = 0f;
        actualizarSaldos(_funcion.getValor());
        puestosl.getChildren().clear();
        puestosr.getChildren().clear();
        for (int it = 0; it < 10; it++) {
            for (int jt = 0; jt < 5; jt++) {
                final int i = it;
                final int j = jt;
                final ImageView estado = new ImageView();
                if (disp[i][j]) {
                    estado.setImage(new Image(new File("imagenes/libre_128.png").toURI().toString()));
                    estado.setStyle("-fx-cursor: hand;");
                    estado.setOnMouseClicked(event -> {
                        if (selec[i][j]) {
                            estado.setImage(new Image(new File("imagenes/libre_128.png").toURI().toString()));
                            selec[i][j] = false;
                            nselecc--;
                        } else {
                            estado.setImage(new Image(new File("imagenes/seleccionado_128.png").toURI().toString()));
                            selec[i][j] = true;
                            nselecc++;
                        }
                        actualizarSaldos(_funcion.getValor());
                    });
                } else estado.setImage(new Image(new File("imagenes/ocupado_128.png").toURI().toString()));
                estado.setFitWidth(32);
                estado.setFitHeight(32);
                puestosl.add(estado, j, i);
            }
        }
        for (int it = 0; it < 10; it++) {
            for (int jt = 0; jt < 5; jt++) {
                final int i = it;
                final int j = jt + 5;
                final ImageView estado = new ImageView();
                if (disp[i][j]) {
                    estado.setImage(new Image(new File("imagenes/libre_128.png").toURI().toString()));
                    estado.setStyle("-fx-cursor: hand;");
                    estado.setOnMouseClicked(event -> {
                        if (selec[i][j]) {
                            estado.setImage(new Image(new File("imagenes/libre_128.png").toURI().toString()));
                            selec[i][j] = false;
                            nselecc--;
                        } else {
                            estado.setImage(new Image(new File("imagenes/seleccionado_128.png").toURI().toString()));
                            selec[i][j] = true;
                            nselecc++;
                        }
                        actualizarSaldos(_funcion.getValor());
                    });
                } else estado.setImage(new Image(new File("imagenes/ocupado_128.png").toURI().toString()));
                estado.setFitWidth(32);
                estado.setFitHeight(32);
                puestosr.add(estado, jt, it);
            }
        }
    }

    public void volver() {
        toogleVisible();
        PeliculaReserva.toogleVisible();
    }

    public void pagar() {
        if (nselecc <= 0) return;
        Pago.controlador.mostrar(total);
    }

    private void actualizarSaldos(float unitario) {
        total = unitario * nselecc;
        valor.setText("Total: $" + total);
        puestos.setText("No. de puestos: " + nselecc);
    }

    public void reservaOkay() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (selec[i][j]) {
                    Actual.getFuncion().setDisponibilidad(false, i, j);
                }
            }
        }
        setFuncion();
    }

}
