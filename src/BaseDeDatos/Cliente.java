package BaseDeDatos;

import Estructuras.Lista;
import Estructuras.Separator;
import Ventana.Dialog;
import javafx.scene.layout.StackPane;

import java.io.*;

public class Cliente implements Comparable<Cliente> {

    private static final String DBPATH = "Cliente.txt";
    private static final File DBFILE = new File(DBPATH);
    private static Lista<Cliente> clientes;
    private static int _pos;

    public static Lista<Cliente> getClientes() {
        return clientes;
    }

    public static void init(StackPane content) {
        clientes = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los clientes.", "Aceptar");
            }
        }
    }

    public static void load(StackPane content) {
        try {
            clientes.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                String _cedula = registro[0];
                String _nombre = registro[1];
                add(_cedula, _nombre);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los clientes.", "Aceptar");
        } catch (NullPointerException error) {
            PrintWriter esc;
            try {
                esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los clientes.", "Aceptar");
            }
        }
    }

    public static void save() {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            clientes.forEach(c -> escritor.write(c.getCedula() + Separator.A
                    + c.getNombre() + Separator.A
                    + "\n"));
            escritor.close();
        } catch (IOException error) {

        }
    }

    public static void add(String _cedula, String _nombre) {
        clientes.insertarOrdenado(new Cliente(_cedula, _pos, _nombre));
        _pos++;
    }

    public static boolean exists(String _cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(_cedula)) {
                clientes.reset();
                return true;
            }
        }
        return false;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < clientes.getItemCount()) {
            clientes.remove(i);
        }
    }

    public static int getItemCount() {
        return clientes.getItemCount();
    }

    public static Cliente getClienteAt(int i) {
        if (i >= 0 && i < clientes.getItemCount()) {
            return clientes.get(i);
        }
        return null;
    }

    public static int indexOf(String _cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equalsIgnoreCase(_cedula)) {
                int p = cliente.getPos();
                clientes.reset();
                return p;
            }
        }
        return -1;
    }

    private String nombre;
    private String cedula;
    private final int pos;

    public Cliente(String _cedula, int _pos, String _nombre) {
        cedula = _cedula;
        nombre = _nombre;
        pos = _pos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String _cedula) {
        cedula = _cedula;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public int compareTo(Cliente o) {
        int cmp = nombre.toLowerCase().compareTo(o.nombre.toLowerCase());
        if (cmp == 0) cmp = cedula.toLowerCase().compareTo(o.cedula.toLowerCase());
        else return cmp;
        if (cmp == 0) cmp = 1;
        return cmp;
    }
}
