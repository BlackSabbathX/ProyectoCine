package Ventana.Pago;

import BaseDeDatos.Actual;
import Ventana.DraggedScene;
import Ventana.Reserva.Reserva;
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

public class Pago implements Initializable, DraggedScene {

    public static Stage pago;
    public static Pago controlador;
    @FXML
    private StackPane content;
    @FXML
    private JFXTextField tarjeta;
    @FXML
    private JFXTextField mes;
    @FXML
    private JFXTextField ano;
    @FXML
    private JFXTextField ccv;
    @FXML
    private Label total;
    @FXML
    private Label codigo;
    @FXML
    private Label estado;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        if (pago.isShowing()) {
            pago.hide();
        } else {
            pago.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
        tarjeta.focusedProperty().addListener(observable -> tarjeta.setUnFocusColor(Color.WHITE));
        mes.focusedProperty().addListener(observable -> mes.setUnFocusColor(Color.WHITE));
        ano.focusedProperty().addListener(observable -> ano.setUnFocusColor(Color.WHITE));
        ccv.focusedProperty().addListener(observable -> ccv.setUnFocusColor(Color.WHITE));
    }

    @FXML
    void confirmar() {
        if (tarjeta.getText().isEmpty()) {
            tarjeta.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El numero de la tarjeta es obligatorio");
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
                    estado.setText("Medios aceptados");
                });
            }).start();
            return;
        }
        if (mes.getText().isEmpty()) {
            mes.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La fecha de vencimiento es obligatoria");
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
                    estado.setText("Medios aceptados");
                });
            }).start();
            return;
        }
        if (ano.getText().isEmpty()) {
            ano.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La fecha de vencimiento es obligatoria");
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
                    estado.setText("Medios aceptados");
                });
            }).start();
            return;
        }
        if (ccv.getText().isEmpty()) {
            ccv.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El codigo de seguridad es obligatorio");
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
                    estado.setText("Medios aceptados");
                });
            }).start();
            return;
        }
        int cod = BaseDeDatos.Reserva.generateId();
        BaseDeDatos.Reserva.add(cod, Actual.getFuncion(), Actual.getCliente());
        limpiar();
        codigo.setText("Transacción exitosa, codigo");
        total.setText(String.valueOf(cod));
        Reserva.controlador.reservaOkay();
        estado.setStyle("" +
                "-fx-background-color:  #8cff66;" +
                "-fx-text-fill: #000000;");
        estado.setText("Transacción exitosa, codigo de reserva generado");
        new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                estado.setStyle("" +
                        "-fx-background-color: #6d3ed0;" +
                        "-fx-text-fill: #ffffff;");
                estado.setText("Medios de pago");
            });
        }).start();
    }

    @FXML
    void salida() {
        toogleVisible();
    }

    public void mostrar(float valor) {
        pago.show();
        limpiar();
        codigo.setText("Pagos por ventanilla virtual");
        total.setText("$" + String.valueOf(valor));
    }

    private void limpiar() {
        tarjeta.setText("");
        ccv.setText("");
        mes.setText("");
        ano.setText("");
    }
}
