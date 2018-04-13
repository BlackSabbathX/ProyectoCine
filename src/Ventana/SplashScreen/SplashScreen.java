package Ventana.SplashScreen;

import BaseDeDatos.*;
import Ventana.Login.Login;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreen implements Initializable {

    public static Stage splashScreen;
    public static SplashScreen controlador;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (splashScreen.isShowing()) {
            splashScreen.hide();
        } else {
            splashScreen.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void startApp() {
        Pelicula.init(content);
        Sala.init(content);
        Cliente.init(content);
        Funcion.init(content);
        Reserva.init(content);
        Usuario.init(content);
        Pelicula.load(content);
        Sala.load(content);
        Cliente.load(content);
        Funcion.load(content);
        Reserva.load(content);
        Usuario.load(content);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                Platform.runLater(() -> {
                    Login.toogleVisible();
                    toogleVisible();
                });
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

}
