package Ventana.Registro;

import BaseDeDatos.Cliente;
import BaseDeDatos.Usuario;
import Estructuras.TipoUsuario;
import Ventana.Dialog;
import Ventana.Login.Login;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Registro implements Initializable {

    public static Stage registro;
    public static Registro controlador;
    @FXML
    private JFXCheckBox admin;
    @FXML
    private JFXTextField nombre;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private JFXPasswordField contrasena1;
    @FXML
    private StackPane content;

    public static void toogleVisible() {
        if (registro.isShowing()) {
            registro.hide();
        } else {
            registro.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        admin.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue) {
                nombre.setPromptText("Codigo de verificación");
                nombre.setText("");
                usuario.setPromptText("Digite un usuario");
            } else {
                nombre.setPromptText("Nombre");
                nombre.setText("");
                usuario.setPromptText("No. de identificación");
            }
        }));
        verificar();
    }

    private void verificar() {
        usuario.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                usuario.setUnFocusColor(Color.BLACK);
            }
        });
        contrasena.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                contrasena.setUnFocusColor(Color.BLACK);
            }
        });
        contrasena1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                contrasena1.setUnFocusColor(Color.BLACK);
            }
        });
        nombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                nombre.setUnFocusColor(Color.BLACK);
            }
        });
    }

    @FXML
    public void cerrarClick() {
        toogleVisible();
    }

    @FXML
    public void volverClick() {
        Login.toogleVisible();
        toogleVisible();
    }

    @FXML
    public void registrarClick() {
        String c = contrasena.getText().trim();
        String c1 = contrasena1.getText().trim();
        String u = usuario.getText().trim();
        String n = nombre.getText().trim();
        if (Usuario.exists(u)) {
            usuario.setUnFocusColor(Color.RED);
            Dialog.showSimpleDialog(content, "Error", "El documento o usuario ingresado ya ha sido registrado.", "Aceptar");
            return;
        }
        if (c.equals("")) {
            contrasena.setUnFocusColor(Color.RED);
            return;
        }
        if (c1.equals("")) {
            contrasena1.setUnFocusColor(Color.RED);
            return;
        }
        if (!c.equals(c1)) {
            contrasena.setUnFocusColor(Color.RED);
            contrasena1.setUnFocusColor(Color.RED);
            Dialog.showSimpleDialog(content, "Corrija", "Las contraseñas ingresadas no coinciden.", "Aceptar");
            return;
        }
        if (n.equals("")) {
            nombre.setUnFocusColor(Color.RED);
            return;
        }
        if (admin.isSelected()) {
            Usuario.add(u, c, TipoUsuario.Administrador);
            Usuario.save(content);
        } else {
            if (!Cliente.exists(u)) Cliente.add(u, n);
            Usuario.add(u, c, TipoUsuario.Usuario);
        }
        cleanForm();
        Dialog.showSimpleDialog(content, "Correcto", "Usuario creado exitosamente.", "Aceptar");
    }

    private void cleanForm() {
        contrasena.setText("");
        contrasena1.setText("");
        nombre.setText("");
    }

}
