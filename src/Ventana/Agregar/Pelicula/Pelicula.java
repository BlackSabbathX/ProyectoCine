package Ventana.Agregar.Pelicula;

import BaseDeDatos.Actual;
import Ventana.DraggedScene;
import Ventana.Reserva.Reserva;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Pelicula implements Initializable, DraggedScene {

    public static Stage agPelicula;
    public static Pelicula controlador;
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
    private AnchorPane pane;

    public static void toogleVisible() {
        if (agPelicula.isShowing()) {
            agPelicula.hide();
        } else {
            agPelicula.show();
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
            return;
        }
        if (mes.getText().isEmpty()) {
            mes.setUnFocusColor(Color.RED);
            return;
        }
        if (ano.getText().isEmpty()) {
            ano.setUnFocusColor(Color.RED);
            return;
        }
        if (ccv.getText().isEmpty()) {
            ccv.setUnFocusColor(Color.RED);
            return;
        }
        int cod = BaseDeDatos.Reserva.generateId();
        BaseDeDatos.Reserva.add(cod, Actual.getFuncion(), Actual.getCliente());
        limpiar();
        codigo.setText("Transacción exitosa, codigo");
        total.setText(String.valueOf(cod));
        Reserva.controlador.reservaOkay();
    }

    @FXML
    void salida() {
        toogleVisible();
    }

    public void mostrar(float valor) {
        agPelicula.show();
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
