package Ventana.Agregar.Funcion;

import BaseDeDatos.Pelicula;
import BaseDeDatos.Sala;
import Estructuras.DateTime;
import Estructuras.Dia;
import Estructuras.Hora;
import Ventana.DraggedScene;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Funcion implements Initializable, DraggedScene {

    public static Stage agFuncion;
    public static Funcion controlador;
    @FXML
    private StackPane content;
    @FXML
    private JFXComboBox<String> dia;
    @FXML
    private JFXComboBox<String> hora;
    @FXML
    private JFXComboBox<String> pelicula;
    @FXML
    private JFXComboBox<String> sala;
    @FXML
    private JFXTextField valor;
    @FXML
    private Label estado;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        agFuncion.show();
        controlador.actValores();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
        valor.focusedProperty().addListener(observable -> valor.setUnFocusColor(Color.WHITE));
        dia.focusedProperty().addListener(observable -> dia.setUnFocusColor(Color.WHITE));
        hora.focusedProperty().addListener(observable -> hora.setUnFocusColor(Color.WHITE));
        pelicula.focusedProperty().addListener(observable -> pelicula.setUnFocusColor(Color.WHITE));
        sala.focusedProperty().addListener(observable -> sala.setUnFocusColor(Color.WHITE));
    }

    private void actValores() {
        dia.getItems().clear();
        hora.getItems().clear();
        pelicula.getItems().clear();
        sala.getItems().clear();
        for (Dia d : Dia.values()) {
            dia.getItems().add(d.toString());
        }
        for (Hora d : Hora.values()) {
            hora.getItems().add(d.toString());
        }
        for (BaseDeDatos.Pelicula d : BaseDeDatos.Pelicula.getPeliculas()) {
            pelicula.getItems().add(d.getNombre());
        }
        for (Sala d : Sala.getSalas()) {
            sala.getItems().add(d.getSala());
        }
    }

    @FXML
    void confirmar() {
        if (dia.getSelectionModel().getSelectedItem() == null) {
            dia.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El dia es obligatorio");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la función");
                });
            }).start();
            return;
        }
        if (hora.getSelectionModel().getSelectedItem() == null) {
            hora.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La hora es obligatoria");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la función");
                });
            }).start();
            return;
        }
        if (pelicula.getSelectionModel().getSelectedItem() == null) {
            pelicula.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La pelicula es obligatoria");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la función");
                });
            }).start();
            return;
        }
        if (sala.getSelectionModel().getSelectedItem() == null) {
            sala.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La sala es obligatoria");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la función");
                });
            }).start();
            return;
        }
        boolean valnum = false;
        try {
            Float.parseFloat(valor.getText().trim());
            valnum = true;
        } catch (Exception e) {
            valnum = false;
        }
        if (!valnum) {
            valor.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El valor es de tipo numerico");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la pelicula");
                });
            }).start();
            return;
        }
        if (valor.getText().isEmpty()) {
            valor.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El valor es obligatorio es obligatoria");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la pelicula");
                });
            }).start();
            return;
        }
        if (BaseDeDatos.Funcion.horasCruzadas(new DateTime(Dia.fromString(dia.getSelectionModel().getSelectedItem()), Hora.fromString(hora.getSelectionModel().getSelectedItem())), Sala.getSalaAt(sala.getSelectionModel().getSelectedIndex()))) {
            hora.setUnFocusColor(Color.RED);
            dia.setUnFocusColor(Color.RED);
            sala.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("Ya hay una función programada a la hora en la sala");
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    hora.setUnFocusColor(Color.WHITE);
                    dia.setUnFocusColor(Color.WHITE);
                    sala.setUnFocusColor(Color.WHITE);
                    estado.setStyle("" +
                            "-fx-background-color: #6d3ed0;" +
                            "-fx-text-fill: #ffffff;");
                    estado.setText("Complete la información de la pelicula");
                });
            }).start();
            return;
        }
        boolean[][] disponibilidad = new boolean[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                disponibilidad[i][j] = true;
            }
        }
        BaseDeDatos.Funcion.add(BaseDeDatos.Funcion.generateId(), new DateTime(Dia.fromString(dia.getSelectionModel().getSelectedItem()), Hora.fromString(hora.getSelectionModel().getSelectedItem())), Sala.getSalaAt(sala.getSelectionModel().getSelectedIndex()), Pelicula.getPeliculaAt(pelicula.getSelectionModel().getSelectedIndex()), Float.parseFloat(valor.getText().trim()), disponibilidad);
        BaseDeDatos.Funcion.save(content);
        BaseDeDatos.Funcion.load(content);
        estado.setStyle("" +
                "-fx-background-color:  #8cff66;" +
                "-fx-text-fill: #000000;");
        estado.setText("Función agregada exitosamente.");
        limpiar();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                estado.setStyle("" +
                        "-fx-background-color: #6d3ed0;" +
                        "-fx-text-fill: #ffffff;");
                estado.setText("Complete la información de la función");
            });
        }).start();
    }

    @FXML
    void salida() {
        agFuncion.hide();
    }

    private void limpiar() {
        valor.setText("");
    }
}
