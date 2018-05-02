package Ventana.PrincipalAdministrador;

import Estructuras.Accion;
import Estructuras.Tipo;
import Ventana.Agregar.Cliente.Cliente;
import Ventana.Agregar.Funcion.Funcion;
import Ventana.Agregar.Sala.Sala;
import Ventana.DraggedScene;
import Ventana.Lista.Listar;
import Ventana.Login.Login;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalAdministrador implements Initializable, DraggedScene {

    public static Stage administrador;
    public static PrincipalAdministrador controlador;
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        if (administrador.isShowing()) {
            administrador.hide();
        } else {
            administrador.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onDraggedScene(pane);
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

}
