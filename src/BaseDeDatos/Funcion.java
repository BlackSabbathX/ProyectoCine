package BaseDeDatos;

import Estructuras.DateTime;
import Estructuras.Lista;
import Estructuras.Separator;
import Ventana.Dialog;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.Random;

public class Funcion implements Comparable<Funcion> {

    private static final String DBPATH = "Funcion.txt";
    private static final File DBFILE = new File(DBPATH);
    private static Lista<Funcion> funciones;
    private static int _pos;
    private final int id;
    private final int pos;
    private DateTime tiempo;
    private Sala sala;
    private Pelicula pelicula;
    private float valor;
    private boolean[][] disponibles;

    public Funcion(int _id, int _pos, DateTime _tiempo, Sala _sala, Pelicula _pelicula, float _valor, boolean[][] _disponibles) {
        id = _id;
        pos = _pos;
        tiempo = _tiempo;
        sala = _sala;
        pelicula = _pelicula;
        valor = _valor;
        disponibles = _disponibles;
    }

    public static Lista<Funcion> getFunciones() {
        return funciones;
    }

    public static Lista<Funcion> getFunciones(Pelicula _pelicula) {
        Lista<Funcion> _funciones = new Lista<>();
        for (Funcion f : funciones) {
            if (f.getPelicula().equals(_pelicula)) {
                _funciones.insertarOrdenado(f);
            }
        }
        return _funciones;
    }

    public static void init(StackPane content) {
        funciones = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las funciones.", "Aceptar");
            }
        }
    }

    public static int generateId() {
        Random random = new Random();
        int _id;
        boolean isIn;
        do {
            _id = random.nextInt(10000);
            isIn = false;
            for (Funcion funcion : funciones) {
                if (funcion.getId() == _id) {
                    isIn = true;
                    funciones.reset();
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    public static void load(StackPane content) {
        try {
            funciones.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                DateTime _tiempo = DateTime.fromString(registro[1]);
                Sala _sala = Sala.getSalaAt(Sala.indexOf(Integer.parseInt(registro[2])));
                Pelicula _pelicula = Pelicula.getPeliculaAt(Pelicula.indexOf(Integer.parseInt(registro[3])));
                float _valor = Float.parseFloat(registro[4]);
                boolean[][] _disponibles = new boolean[10][10];
                String[] _d = registro[5].split(Separator.B);
                int it = 0;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        _disponibles[i][j] = Boolean.parseBoolean(_d[it]);
                        it++;
                    }
                }
                add(_id, _tiempo, _sala, _pelicula, _valor, _disponibles);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las funciones.", "Aceptar");
        } catch (NullPointerException error) {
            PrintWriter esc;
            try {
                esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las funciones.", "Aceptar");
            }
        }
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            funciones.forEach(c -> {
                StringBuilder _disp = new StringBuilder();
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        _disp.append(c.getDisponibles()[i][j]).append(Separator.B);
                    }
                }
                escritor.write(c.getId() + Separator.A
                        + c.getTiempo().toString() + Separator.A
                        + c.getSala().getId() + Separator.A
                        + c.getPelicula().getId() + Separator.A
                        + c.getValor() + Separator.A
                        + _disp + Separator.A
                        + "\n");
            });
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al guardar la base de datos de los usuarios.", "Aceptar");
        }
    }

    public static void add(int _id, DateTime _tiempo, Sala _sala, Pelicula _pelicula, float _valor, boolean[][] _disponibles) {
        funciones.insertarOrdenado(new Funcion(_id, _pos, _tiempo, _sala, _pelicula, _valor, _disponibles));
        _pos++;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < funciones.getItemCount()) {
            funciones.remove(i);
        }
    }

    public static int getItemCount() {
        return funciones.getItemCount();
    }

    public static Funcion getFuncionAt(int i) {
        if (i >= 0 && i < funciones.getItemCount()) {
            return funciones.get(i);
        }
        return null;
    }

    public static int indexOf(int _id) {
        int i = 0;
        for (Funcion funcion : funciones) {
            if (funcion.getId() == _id) {
                funciones.reset();
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getId() {
        return id;
    }

    public int getPos() {
        return pos;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula _pelicula) {
        pelicula = _pelicula;
    }

    public DateTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(DateTime _tiempo) {
        tiempo = _tiempo;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala _sala) {
        sala = _sala;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float _valor) {
        valor = _valor;
    }

    public boolean[][] getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(boolean[][] _disponibles) {
        disponibles = _disponibles;
    }

    public void setDisponibilidad(boolean _disponible, int i, int j) {
        disponibles[i][j] = _disponible;
    }

    @Override
    public int compareTo(Funcion o) {
        if (tiempo.mayorQue(o.tiempo)) return 1;
        if (tiempo.igualQue(o.tiempo)) return 0;
        else return -1;
    }
}
