package Ventana.Agregar.Cliente;

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

public class Cliente implements Initializable, DraggedScene {

    public static Stage agCliente;
    public static Cliente controlador;
    @FXML
    private StackPane content;
    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField cedula;
    @FXML
    private Label estado;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        agCliente.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
        nombre.focusedProperty().addListener(observable -> nombre.setUnFocusColor(Color.WHITE));
        cedula.focusedProperty().addListener(observable -> cedula.setUnFocusColor(Color.WHITE));
    }

    @FXML
    void confirmar() {
        if (nombre.getText().isEmpty()) {
            nombre.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El nombre del cliente es obligatorio");
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
                    estado.setText("Digite los datos del cliente");
                });
            }).start();
            return;
        }
        if (cedula.getText().isEmpty()) {
            cedula.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La cedula del cliente es obligatoria");
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
                    estado.setText("Digite los datos del cliente");
                });
            }).start();
            return;
        }
        BaseDeDatos.Cliente.add(cedula.getText().trim(), nombre.getText().trim());
        BaseDeDatos.Cliente.save(content);
        BaseDeDatos.Cliente.load(content);
        estado.setStyle("" +
                "-fx-background-color:  #8cff66;" +
                "-fx-text-fill: #000000;");
        estado.setText(nombre.getText() + " agregad@ exitosamente.");
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
                estado.setText("Digite los datos del cliente");
            });
        }).start();
    }

    @FXML
    void salida() {
        agCliente.hide();
    }

    private void limpiar() {
        nombre.setText("");
        cedula.setText("");
    }
}
