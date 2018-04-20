package BaseDeDatos;

import Estructuras.Lista;
import Estructuras.Separator;
import Ventana.Dialog;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.Random;

public class Reserva implements Comparable<Reserva> {

    private static final String DBPATH = "Reserva.txt";
    private static final File DBFILE = new File(DBPATH);
    private static Lista<Reserva> reservas;
    private static int _pos;
    private final int id;
    private final int pos;
    private Funcion funcion;
    private Cliente cliente;

    public Reserva(int _id, int _pos, Funcion _funcion, Cliente _cliente) {
        id = _id;
        pos = _pos;
        funcion = _funcion;
        cliente = _cliente;
    }

    public static Lista<Reserva> getReservas() {
        return reservas;
    }

    public static void init(StackPane content) {
        reservas = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las reservas.", "Aceptar");
            }
        }
    }

    public static int generateId() {
        Random random = new Random();
        int _id;
        boolean isIn;
        do {
            _id = random.nextInt(100000);
            isIn = false;
            for (Reserva reserva : reservas) {
                if (reserva.getId() == _id) {
                    isIn = true;
                    reservas.reset();
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    public static void load(StackPane content) {
        try {
            reservas.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                Funcion _funcion = Funcion.getFuncionAt(Funcion.indexOf(Integer.parseInt(registro[1])));
                Cliente _cliente = Cliente.getClienteAt(Cliente.indexOf(registro[2]));
                add(_id, _funcion, _cliente);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las reservas.", "Aceptar");
        } catch (NullPointerException error) {
            try {
                PrintWriter esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las reservas.", "Aceptar");
            }
        }
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            reservas.forEach(c -> escritor.write(c.getId() + Separator.A
                    + c.getFuncion().getId() + Separator.A
                    + c.getCliente().getCedula() + Separator.A
                    + "\n"));
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al guardar la base de datos de los usuarios.", "Aceptar");
        }
    }

    public static void add(int _id, Funcion _funcion, Cliente _cliente) {
        reservas.insertarOrdenado(new Reserva(_id, _pos, _funcion, _cliente));
        _pos++;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < reservas.getItemCount()) {
            reservas.remove(i);
        }
    }

    public static int getItemCount() {
        return reservas.getItemCount();
    }

    public static Reserva getReservaAt(int i) {
        if (i >= 0 && i < reservas.getItemCount()) {
            return reservas.get(i);
        }
        return null;
    }

    public static int indexOf(int _id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == _id) {
                int p = reserva.getPos();
                reservas.reset();
                return p;
            }
        }
        return -1;
    }

    public int getId() {
        return id;
    }

    public int getPos() {
        return pos;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion _funcion) {
        funcion = _funcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente _cliente) {
        cliente = _cliente;
    }

    @Override
    public int compareTo(Reserva o) {
        int cmp = funcion.compareTo(o.funcion);
        if (cmp == 0) cmp = Integer.compare(id, o.id);
        else return cmp;
        if (cmp == 0) cmp = 1;
        return cmp;
    }
}
