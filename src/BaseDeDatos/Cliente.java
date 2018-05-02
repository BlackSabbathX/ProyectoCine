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
    private final String cedula;
    private final int pos;
    private String nombre;

    public Cliente(String _cedula, int _pos, String _nombre) {
        cedula = _cedula;
        nombre = _nombre;
        pos = _pos;
    }

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
            try {
                PrintWriter esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de los clientes.", "Aceptar");
            }
        }
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            clientes.forEach(c -> escritor.write(c.getCedula() + Separator.A
                    + c.getNombre() + Separator.A
                    + "\n"));
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al guardar la base de datos de los clientes.", "Aceptar");
        }
    }

    public static void add(String _cedula, String _nombre) {
        clientes.add(new Cliente(_cedula, _pos, _nombre));
        _pos++;
    }

    public static boolean exists(String _cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(_cedula)) {
                return true;
            }
        }
        return false;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < clientes.size()) {
            Reserva.removeByCliente(Cliente.getClienteAt(i));
            Usuario.remove(Cliente.getClienteAt(i).getCedula());
            clientes.removeAt(i);
        }
    }

    public static int getItemCount() {
        return clientes.size();
    }

    public static Cliente getClienteAt(int i) {
        if (i >= 0 && i < clientes.size()) {
            return clientes.get(i);
        }
        return null;
    }

    public static int indexOf(String _cedula) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(_cedula)) {
                int p = cliente.getPos();
                return p;
            }
        }
        return -1;
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
