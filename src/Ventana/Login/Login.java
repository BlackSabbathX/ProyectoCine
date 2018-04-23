package Ventana.Login;

import BaseDeDatos.Actual;
import BaseDeDatos.Cliente;
import BaseDeDatos.Usuario;
import Estructuras.TipoUsuario;
import Ventana.Dialog;
import Ventana.DraggedScene;
import Ventana.PrincipalAdministrador.PrincipalAdministrador;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import Ventana.Registro.Registro;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable, DraggedScene {

    public static Stage login;
    public static Login controlador;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private JFXCheckBox recordar;
    @FXML
    private StackPane content;
    @FXML
    private AnchorPane pane;

    public static void toogleVisible() {
        if (login.isShowing()) {
            login.hide();
        } else {
            login.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.onDraggedScene(pane);
        usuario.focusedProperty().addListener(observable -> usuario.setUnFocusColor(Color.BLACK));
        contrasena.focusedProperty().addListener(observable -> contrasena.setUnFocusColor(Color.BLACK));
    }

    @FXML
    public void cerrarClick() {
        toogleVisible();
    }

    @FXML
    public void registrarClick() {
        Registro.toogleVisible();
        toogleVisible();
    }

    @FXML
    public void iniciarSesion() {
        String u = usuario.getText().trim();
        String c = contrasena.getText().trim();
        if (u.equals("")) {
            usuario.setUnFocusColor(Color.RED);
            return;
        }
        if (c.equals("")) {
            contrasena.setUnFocusColor(Color.RED);
            return;
        }
        Usuario _usuario = Usuario.logear(u, c);
        if (_usuario == null) {
            Dialog.showSimpleDialog(content, "Error", "Usuario o contraseña incorrectos", "Aceptar");
            return;
        }
        if (!recordar.isSelected()) {
            usuario.setText("");
        }
        contrasena.setText("");
        toogleVisible();
        if (_usuario.getTipo() == TipoUsuario.Usuario) {
            Actual.setUsuario(_usuario);
            Actual.setCliente(Cliente.getClienteAt(Cliente.indexOf(u)));
            PrincipalUsuario.toogleVisible();
        } else {
            Actual.setUsuario(_usuario);
            PrincipalAdministrador.toogleVisible();
        }
    }

}
