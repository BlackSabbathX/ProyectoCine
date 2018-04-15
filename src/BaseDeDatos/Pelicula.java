package BaseDeDatos;

import Estructuras.Lista;
import Estructuras.Separator;
import Ventana.Dialog;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.Random;

public class Pelicula implements Comparable<Pelicula> {

    private static final String DBPATH = "Pelicula.txt";
    private static final File DBFILE = new File(DBPATH);
    private static Lista<Pelicula> peliculas;
    private static int _pos;

    public static Lista<Pelicula> getPeliculas() {
        return peliculas;
    }

    public static void init(StackPane content) {
        peliculas = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las peliculas.", "Aceptar");
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
            for (Pelicula pelicula : peliculas) {
                if (pelicula.getId() == _id) {
                    isIn = true;
                    peliculas.reset();
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    public static void load(StackPane content) {
        try {
            peliculas.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int id = Integer.parseInt(registro[0]);
                String _nombre = registro[1];
                String _autor = registro[2];
                add(id, _nombre, _autor);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las peliculas.", "Aceptar");
        } catch (NullPointerException error) {
            try {
                PrintWriter esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las peliculas.", "Aceptar");
            }
        }
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            peliculas.forEach(c -> escritor.write(c.getId() + Separator.A
                    + c.getNombre() + Separator.A
                    + c.getAutor() + Separator.A
                    + "\n"));
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al guardar la base de datos de las peliculas.", "Aceptar");
        }
    }

    public static void add(int _id, String _nombre, String autor) {
        peliculas.insertarOrdenado(new Pelicula(_id, _pos, _nombre, autor));
        _pos++;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < peliculas.getItemCount()) {
            peliculas.remove(i);
        }
    }

    public static int getItemCount() {
        return peliculas.getItemCount();
    }

    public static Pelicula getPeliculaAt(int i) {
        if (i >= 0 && i < peliculas.getItemCount()) {
            return peliculas.get(i);
        }
        return null;
    }

    public static int indexOf(String _nombre) {
        int i = 0;
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getNombre().equals(_nombre)) {
                peliculas.reset();
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(int _id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == _id) {
                int p = pelicula.getPos();
                peliculas.reset();
                return p;
            }
        }
        return -1;
    }

    private final int id;
    private final int pos;
    private String nombre;
    private String autor;

    public Pelicula(int _id, int _pos, String _nombre, String _autor) {
        id = _id;
        pos = _pos;
        nombre = _nombre;
        autor = _autor;
    }

    public int getPos() {
        return pos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String _autor) {
        autor = _autor;
    }

    @Override
    public int compareTo(Pelicula o) {
        int cmp = nombre.toLowerCase().compareTo(o.nombre.toLowerCase());
        if (cmp == 0) cmp = autor.toLowerCase().compareTo(o.autor.toLowerCase());
        else return cmp;
        if (cmp == 0) cmp = Integer.compare(id, o.id);
        else return cmp;
        if (cmp == 0) cmp = 1;
        return cmp;
    }
}
