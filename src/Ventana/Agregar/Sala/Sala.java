package Ventana.Agregar.Sala;

import Ventana.DraggedScene;
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

public class Sala implements Initializable, DraggedScene {

    public static Stage agSala;
    public static Sala controlador;
    @FXML
    private JFXTextField sala;
    @FXML
    private StackPane content;
    @FXML
    private Label estado;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        agSala.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
        sala.focusedProperty().addListener(observable -> sala.setUnFocusColor(Color.WHITE));
    }

    @FXML
    void confirmar() {
        if (sala.getText().isEmpty()) {
            sala.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El nombre de la sala es obligatorio");
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
                    estado.setText("Digite el nombre de la sala");
                });
            }).start();
            return;
        }
        BaseDeDatos.Sala.add(BaseDeDatos.Sala.generateId(), sala.getText().trim());
        BaseDeDatos.Sala.save(content);
        BaseDeDatos.Sala.load(content);
        estado.setStyle("" +
                "-fx-background-color:  #8cff66;" +
                "-fx-text-fill: #000000;");
        estado.setText(sala.getText() + " agregada exitosamente.");
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
                estado.setText("Digite el nombre de la sala");
            });
        }).start();
    }

    @FXML
    void salida() {
        agSala.hide();
    }

    private void limpiar() {
        sala.setText("");
    }
}
