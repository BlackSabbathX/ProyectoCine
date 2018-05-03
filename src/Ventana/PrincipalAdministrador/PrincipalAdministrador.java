package Ventana.PrincipalAdministrador;

import BaseDeDatos.Actual;
import Estructuras.Accion;
import Estructuras.DateTime;
import Estructuras.Dia;
import Estructuras.Tipo;
import Ventana.Agregar.Cliente.Cliente;
import Ventana.Agregar.Funcion.Funcion;
import Ventana.Agregar.Sala.Sala;
import Ventana.DraggedScene;
import Ventana.Lista.Listar;
import Ventana.Login.Login;
import Ventana.Reserva.Reserva;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalAdministrador implements Initializable, DraggedScene {

    public static Stage administrador;
    public static PrincipalAdministrador controlador;
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane pane;
    @FXML
    private VBox funciones;

    public static void toogleVisible() {
        if (administrador.isShowing()) {
            administrador.hide();
        } else {
            controlador.loadContent();
            administrador.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onDraggedScene(pane);
        Actual.setCliente(new BaseDeDatos.Cliente("669272262", -1, "Administrador"));
    }

    public void loadContent() {
        funciones.getChildren().clear();
        Banner b = new Banner();
        boolean n = true;
        try {
            for (BaseDeDatos.Funcion f : BaseDeDatos.Funcion.getFunciones(Dia.hoy())) {
                funciones.getChildren().add(b.nuevoBanner(f));
                n = false;
            }
            if (n) funciones.getChildren().add(b.nuevoBanner(null));
        } catch (IOException ignored) {
        }
    }

    @FXML
    public void volver() {
        toogleVisible();
        Login.toogleVisible();
    }

    @FXML
    public void cerrar() {
        toogleVisible();
    }

    @FXML
    public void agregarP() {
        Ventana.Agregar.Pelicula.Pelicula.toogleVisible();
    }

    @FXML
    public void agregarF() {
        Funcion.toogleVisible();
    }

    @FXML
    public void agregarS() {
        Sala.toogleVisible();
    }

    @FXML
    public void agregarC() {
        Cliente.toogleVisible();
    }

    @FXML
    public void eliminarP() {
        Listar.toogleVisible(Accion.eliminar, Tipo.pelicula);
    }

    @FXML
    public void eliminarF() {
        Listar.toogleVisible(Accion.eliminar, Tipo.funcion);
    }

    @FXML
    public void eliminarC() {
        Listar.toogleVisible(Accion.eliminar, Tipo.cliente);
    }

    @FXML
    public void eliminarS() {
        Listar.toogleVisible(Accion.eliminar, Tipo.sala);
    }

    @FXML
    public void listaP() {
        Listar.toogleVisible(Accion.listar, Tipo.pelicula);
    }

    @FXML
    public void listaS() {
        Listar.toogleVisible(Accion.listar, Tipo.sala);
    }

    @FXML
    public void listaF() {
        Listar.toogleVisible(Accion.listar, Tipo.funcion);
    }

    @FXML
    public void listaC() {
        Listar.toogleVisible(Accion.listar, Tipo.cliente);
    }

    private class Banner {

        private Pane nuevoBanner(BaseDeDatos.Funcion funcion) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/Ventana/PrincipalAdministrador/BannerFuncion.fxml"));

            final Pane p = (Pane) parent.lookup("#panel");
            Label pelicula = (Label) ((GridPane) p.getChildren().get(0)).getChildren().get(0);
            Label tiempo = (Label) ((GridPane) p.getChildren().get(0)).getChildren().get(1);
            Label precio = (Label) ((GridPane) p.getChildren().get(0)).getChildren().get(2);
            JFXButton reservar = (JFXButton) ((GridPane) p.getChildren().get(0)).getChildren().get(3);

            if (funcion == null) {
                pelicula.setText("No hay funciones este dia");
                precio.setText("");
                tiempo.setText("");
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
            pelicula.setText(funcion.getPelicula().getNombre());

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
