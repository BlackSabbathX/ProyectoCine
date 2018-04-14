package BaseDeDatos;

//import Estructura.Lista;
//import Estructura.Separator;
//import Estructura.TipoUsuario;

import Estructuras.Lista;
import Estructuras.Separator;
import Estructuras.TipoUsuario;
import Ventana.Dialog;
import javafx.scene.layout.StackPane;

import java.io.*;

public class Usuario implements Comparable<Usuario> {

    private static final String DBPATH = "Usuario.txt";
    private static final File DBFILE = new File(DBPATH);
    private static Lista<Usuario> usuarios;

    public static void init(StackPane content) {
        usuarios = new Lista<>();
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al crear el archivo de usuarios.", "Aceptar");
            }
        }
    }

    public static int nAdministradores() {
        int n = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo() == TipoUsuario.Administrador) {
                n++;
            }
        }
        return n;
    }

    public static int nUsuarios() {
        int n = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getTipo() == TipoUsuario.Usuario) {
                n++;
            }
        }
        return n;
    }

    public static void load(StackPane content) {
        try {
            usuarios.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                String _usuario = registro[0];
                String _contrasena = registro[1];
                TipoUsuario _tipo = TipoUsuario.fromString(registro[2]);
                add(_usuario, _contrasena, _tipo);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los usuarios.", "Aceptar");
        } catch (NullPointerException error) {
            PrintWriter esc;
            try {
                esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los usuarios.", "Aceptar");
            }
        }
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            usuarios.forEach(usuario -> escritor.write(usuario.getUsuario() + Separator.A
                    + usuario.getContrasena() + Separator.A
                    + usuario.getTipo().toString() + Separator.A
                    + "\n")
            );
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al grabar la base de datos de los usuarios.", "Aceptar");
        }
    }

    public static void add(String _usuarioStr, String _contrasena, TipoUsuario _tipo) {
        Usuario _usuario = new Usuario(_usuarioStr, _contrasena, _tipo);
        usuarios.insertar(_usuario);
    }

    public static boolean exists(String _usuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(_usuario)) {
                usuarios.reset();
                return true;
            }
        }
        return false;
    }

    public static Usuario logear(String _usuario, String _contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(_usuario)) {
                if (usuario.contrasenaCorrecta(_contrasena)) {
                    usuarios.reset();
                    return usuario;
                } else {
                    usuarios.reset();
                    break;
                }
            }
        }
        return null;
    }

    private Usuario(String _usuario, String _contrasena, TipoUsuario _tipo) {
        usuario = _usuario;
        contrasena = _contrasena;
        tipo = _tipo;
    }

    private boolean contrasenaCorrecta(String _contrasena) {
        return (contrasena.equals(_contrasena.trim()));
    }

    public void setContrasena(String nueva, String actual) {
        if (contrasenaCorrecta(actual)) {
            contrasena = nueva;
        }
    }

    private String getUsuario() {
        return usuario;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    private String getContrasena() {
        return contrasena;
    }

    @Override
    public int compareTo(Usuario o) {
        return usuario.compareTo(o.usuario);
    }

    private final String usuario;
    private final TipoUsuario tipo;
    private String contrasena;
}
