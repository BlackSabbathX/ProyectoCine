package Ventana.Lista;

import BaseDeDatos.Cliente;
import BaseDeDatos.Pelicula;
import Estructuras.Accion;
import Estructuras.Tipo;
import Ventana.DraggedScene;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Listar implements Initializable, DraggedScene {

    public static Stage listar;
    public static Listar controlador;
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lista;
    @FXML
    private VBox tabla;
    private Accion accion;
    private Tipo tipo;

    public static void toogleVisible(Accion accion, Tipo tipo) {
        listar.show();
        controlador.setContent(accion, tipo);
    }

    public static void toogleVisible() {
        if (listar.isShowing()) {
            listar.hide();
        } else {
            listar.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onDraggedScene(pane);
    }

    private void setContent(Accion _accion, Tipo _tipo) {
        accion = _accion;
        tipo = _tipo;
        String _lista = "";
        switch (_accion) {
            case eliminar:
                _lista = _lista.concat("Eliminar " + _tipo.name() + "s");
                break;
            case listar:
                _lista = _lista.concat("Lista de " + _tipo.name() + "s");
                break;
        }
        lista.setText(_lista);
        tabla.getChildren().clear();
        Banner b = new Banner();
        switch (_tipo) {
            case cliente:
                for (Cliente c : Cliente.getClientes()) {
                    try {
                        tabla.getChildren().add(b.nuevoBanner(c));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case funcion:
                break;
            case pelicula:
                for (Pelicula c : Pelicula.getPeliculas()) {
                    try {
                        tabla.getChildren().add(b.nuevoBanner(c));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case sala:
                break;
        }
    }

    @FXML
    public void volver() {
        toogleVisible();
    }

    private class Banner {

        private Pane nuevoBanner(Pelicula pelicula) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/Ventana/Lista/Pelicula.fxml"));

            final Pane p = (Pane) parent.lookup("#panel");
            String style = "-fx-cursor: hand;";
            ImageView portada = (ImageView) p.getChildren().get(0);
            Label nombre = (Label) p.getChildren().get(1);
            Label autor = (Label) p.getChildren().get(2);
            Label numero = (Label) p.getChildren().get(3);

            portada.setImage(new Image(new File(pelicula.getImagen()).toURI().toString()));
            nombre.setText(pelicula.getNombre());
            autor.setText(pelicula.getAutor());
            numero.setText("" + pelicula.getPos());

            p.setStyle(style);

            EventHandler evt = (EventHandler<MouseEvent>) event -> {
                if (event.getClickCount() == 2) {
                    Pelicula.removeAt(pelicula.getPos());
                    Pelicula.save(content);
                    Pelicula.load(content);
                    setContent(accion, tipo);
                }
            };

            if (accion == Accion.eliminar)
                p.setOnMouseClicked(evt);
            return p;
        }


        private Pane nuevoBanner(Cliente cliente) throws IOException {
            Parent parent = FXMLLoader.load(getClass().getResource("/Ventana/Lista/Cliente.fxml"));

            final Pane p = (Pane) parent.lookup("#panel");
            String style = "-fx-cursor: hand;";
            Label numero = (Label) p.getChildren().get(0);
            Label nombre = (Label) p.getChildren().get(1);
            Label cedula = (Label) p.getChildren().get(2);

            nombre.setText(cliente.getNombre());
            numero.setText("" + cliente.getPos());
            cedula.setText(cliente.getCedula());

            p.setStyle(style);

            EventHandler evt = (EventHandler<MouseEvent>) event -> {
                if (event.getClickCount() == 2) {
                    Cliente.removeAt(cliente.getPos());
                    Cliente.save(content);
                    Cliente.load(content);
                    setContent(accion, tipo);
                }
            };

            if (accion == Accion.eliminar)
                p.setOnMouseClicked(evt);
            return p;
        }
    }

}
