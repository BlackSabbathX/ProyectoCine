import Ventana.Login.Login;
import Ventana.Pago.Pago;
import Ventana.PeliculaReservas.PeliculaReserva;
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
        FXMLLoader loaderRE = new FXMLLoader(getClass().getResource("/Ventana/Registro/Registro.fxml"));
        FXMLLoader loaderPR = new FXMLLoader(getClass().getResource("/Ventana/PeliculaReservas/PeliculaReserva.fxml"));
        FXMLLoader loaderR = new FXMLLoader(getClass().getResource("/Ventana/Reserva/Reserva.fxml"));
        FXMLLoader loaderP = new FXMLLoader(getClass().getResource("/Ventana/Pago/Pago.fxml"));

        Parent rootLG = loaderLG.load();
        Parent rootPU = loaderPU.load();
        Parent rootRE = loaderRE.load();
        Parent rootPR = loaderPR.load();
        Parent rootR = loaderR.load();
        Parent rootP = loaderP.load();

        Scene sceneLG = new Scene(rootLG);
        Scene scenePU = new Scene(rootPU);
        Scene sceneRE = new Scene(rootRE);
        Scene scenePR = new Scene(rootPR);
        Scene sceneR = new Scene(rootR);
        Scene sceneP = new Scene(rootP);

        Login.login = new Stage(StageStyle.UNDECORATED);
        PrincipalUsuario.usuario = new Stage(StageStyle.UNDECORATED);
        Registro.registro = new Stage(StageStyle.UNDECORATED);
        PeliculaReserva.peliculaR = new Stage(StageStyle.UNDECORATED);
        Reserva.stage = new Stage(StageStyle.UNDECORATED);
        Pago.pago = new Stage(StageStyle.UNDECORATED);

        Login.login.setScene(sceneLG);
        PrincipalUsuario.usuario.setScene(scenePU);
        Registro.registro.setScene(sceneRE);
        PeliculaReserva.peliculaR.setScene(scenePR);
        Reserva.stage.setScene(sceneR);
        Pago.pago.setScene(sceneP);

        Login.controlador = loaderLG.getController();
        PrincipalUsuario.controlador = loaderPU.getController();
        Registro.controlador = loaderRE.getController();
        PeliculaReserva.controlador = loaderPR.getController();
        Reserva.controlador = loaderR.getController();
        Pago.controlador = loaderP.getController();

        SplashScreen.toogleVisible();
        SplashScreen.controlador.startApp();
    }

}
