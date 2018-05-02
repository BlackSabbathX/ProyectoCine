import Ventana.Agregar.Cliente.Cliente;
import Ventana.Agregar.Funcion.Funcion;
import Ventana.Agregar.Pelicula.Pelicula;
import Ventana.Agregar.Sala.Sala;
import Ventana.Lista.Listar;
import Ventana.Login.Login;
import Ventana.Pago.Pago;
import Ventana.PeliculaReservas.PeliculaReserva;
import Ventana.PrincipalAdministrador.PrincipalAdministrador;
import Ventana.PrincipalUsuario.PrincipalUsuario;
import Ventana.Registro.Registro;
import Ventana.Reserva.Reserva;
import Ventana.SplashScreen.SplashScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loaderSS = new FXMLLoader(getClass().getResource("/Ventana/SplashScreen/SplashScreen.fxml"));

        Parent rootSS = loaderSS.load();
        Scene sceneSS = new Scene(rootSS);
        SplashScreen.splashScreen = new Stage(StageStyle.UNDECORATED);
        SplashScreen.splashScreen.setScene(sceneSS);
        SplashScreen.controlador = loaderSS.getController();

        FXMLLoader loaderLG = new FXMLLoader(getClass().getResource("/Ventana/Login/Login.fxml"));
        FXMLLoader loaderPU = new FXMLLoader(getClass().getResource("/Ventana/PrincipalUsuario/PrincipalUsuario.fxml"));
        FXMLLoader loaderPA = new FXMLLoader(getClass().getResource("/Ventana/PrincipalAdministrador/PrincipalAdministrador.fxml"));
        FXMLLoader loaderRE = new FXMLLoader(getClass().getResource("/Ventana/Registro/Registro.fxml"));
        FXMLLoader loaderPR = new FXMLLoader(getClass().getResource("/Ventana/PeliculaReservas/PeliculaReserva.fxml"));
        FXMLLoader loaderR = new FXMLLoader(getClass().getResource("/Ventana/Reserva/Reserva.fxml"));
        FXMLLoader loaderP = new FXMLLoader(getClass().getResource("/Ventana/Pago/Pago.fxml"));
        FXMLLoader loaderL = new FXMLLoader(getClass().getResource("/Ventana/Lista/Listar.fxml"));

        FXMLLoader loaderAgC = new FXMLLoader(getClass().getResource("/Ventana/Agregar/Cliente/Cliente.fxml"));
        FXMLLoader loaderAgF = new FXMLLoader(getClass().getResource("/Ventana/Agregar/Funcion/Funcion.fxml"));
        FXMLLoader loaderAgP = new FXMLLoader(getClass().getResource("/Ventana/Agregar/Pelicula/Pelicula.fxml"));
        FXMLLoader loaderAgS = new FXMLLoader(getClass().getResource("/Ventana/Agregar/Sala/Sala.fxml"));

        Parent rootLG = loaderLG.load();
        Parent rootPU = loaderPU.load();
        Parent rootPA = loaderPA.load();
        Parent rootRE = loaderRE.load();
        Parent rootPR = loaderPR.load();
        Parent rootR = loaderR.load();
        Parent rootP = loaderP.load();
        Parent rootL = loaderL.load();

        Parent rootAgC = loaderAgC.load();
        Parent rootAgF = loaderAgF.load();
        Parent rootAgP = loaderAgP.load();
        Parent rootAgS = loaderAgS.load();

        Scene sceneLG = new Scene(rootLG);
        Scene scenePU = new Scene(rootPU);
        Scene scenePA = new Scene(rootPA);
        Scene sceneRE = new Scene(rootRE);
        Scene scenePR = new Scene(rootPR);
        Scene sceneR = new Scene(rootR);
        Scene sceneP = new Scene(rootP);
        Scene sceneL = new Scene(rootL);

        Scene sceneAgC = new Scene(rootAgC);
        Scene sceneAgF = new Scene(rootAgF);
        Scene sceneAgP = new Scene(rootAgP);
        Scene sceneAgS = new Scene(rootAgS);

        Login.login = new Stage(StageStyle.UNDECORATED);
        PrincipalUsuario.usuario = new Stage(StageStyle.UNDECORATED);
        PrincipalAdministrador.administrador = new Stage(StageStyle.UNDECORATED);
        Registro.registro = new Stage(StageStyle.UNDECORATED);
        PeliculaReserva.peliculaR = new Stage(StageStyle.UNDECORATED);
        Reserva.reserva = new Stage(StageStyle.UNDECORATED);
        Pago.pago = new Stage(StageStyle.UNDECORATED);
        Listar.listar = new Stage(StageStyle.UNDECORATED);

        Cliente.agCliente = new Stage(StageStyle.UNDECORATED);
        Funcion.agFuncion = new Stage(StageStyle.UNDECORATED);
        Pelicula.agPelicula = new Stage(StageStyle.UNDECORATED);
        Sala.agSala = new Stage(StageStyle.UNDECORATED);

        Login.login.setScene(sceneLG);
        PrincipalUsuario.usuario.setScene(scenePU);
        PrincipalAdministrador.administrador.setScene(scenePA);
        Registro.registro.setScene(sceneRE);
        PeliculaReserva.peliculaR.setScene(scenePR);
        Reserva.reserva.setScene(sceneR);
        Pago.pago.setScene(sceneP);
        Listar.listar.setScene(sceneL);

        Cliente.agCliente.setScene(sceneAgC);
        Funcion.agFuncion.setScene(sceneAgF);
        Pelicula.agPelicula.setScene(sceneAgP);
        Sala.agSala.setScene(sceneAgS);

        Login.controlador = loaderLG.getController();
        PrincipalUsuario.controlador = loaderPU.getController();
        PrincipalAdministrador.controlador = loaderPA.getController();
        Registro.controlador = loaderRE.getController();
        PeliculaReserva.controlador = loaderPR.getController();
        Reserva.controlador = loaderR.getController();
        Pago.controlador = loaderP.getController();
        Listar.controlador = loaderL.getController();

        Cliente.controlador = loaderAgC.getController();
        Funcion.controlador = loaderAgF.getController();
        Pelicula.controlador = loaderAgP.getController();
        Sala.controlador = loaderAgS.getController();

        SplashScreen.toogleVisible();
        SplashScreen.controlador.startApp();
    }

}
