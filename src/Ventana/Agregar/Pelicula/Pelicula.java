package Ventana.Agregar.Pelicula;

import Ventana.DraggedScene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class Pelicula implements Initializable, DraggedScene {

    public static Stage agPelicula;
    public static Pelicula controlador;
    @FXML
    private StackPane content;
    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField autor;
    @FXML
    private JFXTextField genero;
    @FXML
    private Label estado;
    @FXML
    private Rating rate;
    @FXML
    private JFXButton portada;
    @FXML
    private AnchorPane pane;
    private boolean portadaSelec;
    private String porName;

    public static void toogleVisible() {
        agPelicula.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
        nombre.focusedProperty().addListener(observable -> nombre.setUnFocusColor(Color.WHITE));
        autor.focusedProperty().addListener(observable -> autor.setUnFocusColor(Color.WHITE));
        genero.focusedProperty().addListener(observable -> genero.setUnFocusColor(Color.WHITE));
        portadaSelec = false;
        porName = "";
    }

    @FXML
    public void seleccionarPortada() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );
        File p = chooser.showOpenDialog(agPelicula);
        if (p != null) {
            try {
                Files.copy(Paths.get(p.getAbsolutePath()), Paths.get(new File("portada/" + p.getName()).getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                portadaSelec = true;
                porName = p.getName();
                portada.setText(porName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void confirmar() {
        if (nombre.getText().isEmpty()) {
            nombre.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El nombre es obligatorio");
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
        if (autor.getText().isEmpty()) {
            autor.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El/La autor(a) es obligatorio");
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
        if (genero.getText().isEmpty()) {
            genero.setUnFocusColor(Color.RED);
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("El genero es obligatorio");
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
        if (!portadaSelec || porName.trim().equals("")) {
            estado.setStyle("" +
                    "-fx-background-color:  #ff371e;" +
                    "-fx-text-fill: #000000;");
            estado.setText("La portada es obligatoria");
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
        BaseDeDatos.Pelicula.add(BaseDeDatos.Pelicula.generateId(), nombre.getText().trim(), genero.getText().trim(), autor.getText().trim(), rate.getRating(), "portada/" + porName);
        BaseDeDatos.Pelicula.save(content);
        BaseDeDatos.Pelicula.load(content);
        estado.setStyle("" +
                "-fx-background-color:  #8cff66;" +
                "-fx-text-fill: #000000;");
        estado.setText(nombre.getText() + " agregada exitosamente.");
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
                estado.setText("Complete la información de la pelicula");
            });
        }).start();
    }

    @FXML
    void salida() {
        agPelicula.hide();
    }

    private void limpiar() {
        nombre.setText("");
        autor.setText("");
        genero.setText("");
        portada.setText("Click aqui para seleccionar...");
        rate.setRating(2.5);
        portadaSelec = false;
        porName = "";
    }
}
