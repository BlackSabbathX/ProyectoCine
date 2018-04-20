package BaseDeDatos;

public class Actual {

    private static Cliente cliente;
    private static Funcion funcion;
    private static Pelicula pelicula;
    private static Usuario usuario;

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente _cliente) {
        cliente = _cliente;
    }

    public static Funcion getFuncion() {
        return funcion;
    }

    public static void setFuncion(Funcion _funcion) {
        funcion = _funcion;
    }

    public static Pelicula getPelicula() {
        return pelicula;
    }

    public static void setPelicula(Pelicula _pelicula) {
        pelicula = _pelicula;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario _usuario) {
        usuario = _usuario;
    }
}
