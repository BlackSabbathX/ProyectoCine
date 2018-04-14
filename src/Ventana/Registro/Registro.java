package Ventana.Registro;

import BaseDeDatos.Usuario;
import Estructuras.TipoUsuario;
import Ventana.Dialog;
import Ventana.Login.Login;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Registro implements Initializable {

    public static Stage registro;
    public static Registro controlador;
    @FXML
    public JFXTextField nombre;
    @FXML
    private JFXTextField usuario;
    @FXML
    private JFXPasswordField contrasena;
    @FXML
    private JFXPasswordField contrasena1;
    @FXML
    private StackPane content;
    private boolean pass, user, name;

    public static void toogleVisible() {
        if (registro.isShowing()) {
            registro.hide();
        } else {
            registro.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pass = false;
        user = false;
        name = false;
        verificar();
    }

    private void verificar() {
        usuario.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String u = usuario.getText().trim();
                if (Usuario.usuarioExists(u)) {
                    Dialog.showSimpleDialog(content, "Error", "El documento ingresado ya ha sido registrado.", "Aceptar");
                    usuario.setUnFocusColor(Color.RED);
                    user = false;
                    return;
                } else {
                    usuario.setUnFocusColor(Color.WHITE);
                }
                if (u.equals("")) {
                    user = false;
                    usuario.setUnFocusColor(Color.RED);
                    return;
                } else {
                    usuario.setUnFocusColor(Color.WHITE);
                }
                user = true;
            }
        });
        contrasena.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String c = contrasena.getText().trim();
                String c1 = contrasena1.getText().trim();
                if (c.equals("")) {
                    pass = false;
                    contrasena.setUnFocusColor(Color.RED);
                    return;
                } else {
                    contrasena.setUnFocusColor(Color.WHITE);
                }
                if (c1.equals("")) {
                    pass = false;
                    return;
                }
                if (c.equals(c1)) {
                    contrasena.setUnFocusColor(Color.WHITE);
                    contrasena1.setUnFocusColor(Color.WHITE);
                } else {
                    contrasena.setUnFocusColor(Color.RED);
                    contrasena1.setUnFocusColor(Color.RED);
                    pass = false;
                    return;
                }
                pass = true;
            }
        });
        contrasena1.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String c = contrasena.getText().trim();
                String c1 = contrasena1.getText().trim();
                if (c1.equals("")) {
                    pass = false;
                    contrasena1.setUnFocusColor(Paint.valueOf("red"));
                    return;
                } else {
                    contrasena1.setUnFocusColor(Paint.valueOf("white"));
                }
                if (c.equals("")) {
                    pass = false;
                    return;
                }
                if (c.equals(c1)) {
                    contrasena1.setUnFocusColor(Paint.valueOf("white"));
                    contrasena.setUnFocusColor(Paint.valueOf("white"));
                } else {
                    contrasena1.setUnFocusColor(Paint.valueOf("red"));
                    contrasena.setUnFocusColor(Paint.valueOf("red"));
                    pass = false;
                    return;
                }
                pass = true;
            }
        });
        nombre.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String n = nombre.getText().trim();
                if (n.equals("")) {
                    name = false;
                    nombre.setUnFocusColor(Color.RED);
                    return;
                } else {
                    nombre.setUnFocusColor(Color.WHITE);
                }
                name = true;
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
        if (pass && user && name) {
            String u = usuario.getText().trim();
            Usuario.add(u, c, TipoUsuario.Usuario);
            Usuario.save(content);
            cleanForm();
            Dialog.showSimpleDialog(content, "Correcto", "Usuario creado exitosamente.", "Aceptar");
        } else {
            if (!c.equals(c1)) {
                Dialog.showSimpleDialog(content, "Corrija", "Las contraseñas digitadas no son iguales.", "Aceptar");
                return;
            }
            Dialog.showSimpleDialog(content, "Complete", "Formulario de registro incompleto.", "Aceptar");
        }
    }

    private void cleanForm() {
        contrasena.setText("");
        contrasena1.setText("");
    }

}
