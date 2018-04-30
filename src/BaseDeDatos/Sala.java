package BaseDeDatos;

import Estructuras.Lista;
import Estructuras.Separator;
import Ventana.Dialog;
import javafx.scene.layout.StackPane;

import java.io.*;
import java.util.Random;

public class Sala implements Comparable<Sala> {

    private static final String DBPATH = "Sala.txt";
    private static final File DBFILE = new File(DBPATH);
    private static Lista<Sala> salas;
    private static int _pos;
    private final int id;
    private final int pos;
    private String sala;

    public Sala(int _id, int _pos, String _sala) {
        id = _id;
        pos = _pos;
        sala = _sala;
    }

    public static Lista<Sala> getSalas() {
        return salas;
    }

    public static void init(StackPane content) {
        salas = new Lista<>();
        _pos = 0;
        if (!DBFILE.exists()) {
            try {
                DBFILE.createNewFile();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las salas.", "Aceptar");
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
            for (Sala sala : salas) {
                if (sala.getId() == _id) {
                    isIn = true;
                    break;
                }
            }
        } while (isIn);
        return _id;
    }

    public static void load(StackPane content) {
        try {
            salas.clear();
            BufferedReader lector = new BufferedReader(new FileReader(DBFILE));
            String linea = lector.readLine().trim();
            _pos = 0;
            while (linea != null && !linea.equals("")) {
                String[] registro = linea.split(Separator.A);
                int _id = Integer.parseInt(registro[0]);
                String _sala = registro[1];
                add(_id, _sala);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las salas.", "Aceptar");
        } catch (NullPointerException error) {
            try {
                PrintWriter esc = new PrintWriter(new FileWriter(DBFILE));
                esc.write(" ");
                esc.close();
            } catch (IOException ex) {
                Dialog.showSimpleDialog(content, "Error", "Error al cargar la base de datos de las salas.", "Aceptar");
            }
        }
    }

    public static void save(StackPane content) {
        try {
            PrintWriter escritor = new PrintWriter(new FileWriter(DBFILE));
            salas.forEach(c -> escritor.write(c.getId() + Separator.A
                    + c.getSala() + Separator.A
                    + "\n"));
            escritor.close();
        } catch (IOException error) {
            Dialog.showSimpleDialog(content, "Error", "Error al guardar la base de datos de las salas.", "Aceptar");
        }
    }

    public static void add(int _id, String _sala) {
        salas.add(new Sala(_id, _pos, _sala));
        _pos++;
    }

    public static void removeAt(int i) {
        if (i >= 0 && i < salas.size()) {
            salas.removeAt(i);
        }
    }

    public static int getItemCount() {
        return salas.size();
    }

    public static Sala getSalaAt(int i) {
        if (i >= 0 && i < salas.size()) {
            return salas.get(i);
        }
        return null;
    }

    public static int indexOf(String _sala) {
        int i = 0;
        for (Sala sala : salas) {
            if (sala.getSala().equals(_sala)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(int _id) {
        for (Sala sala : salas) {
            if (sala.getId() == _id) {
                int p = sala.getPos();
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

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public int compareTo(Sala o) {
        int cmp = sala.toLowerCase().compareTo(o.sala.toLowerCase());
        if (cmp == 0) cmp = Integer.compare(id, o.id);
        else return cmp;
        if (cmp == 0) cmp = 1;
        return cmp;
    }
}
