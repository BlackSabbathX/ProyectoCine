package Estructuras;

public class Fecha {
    private int d;
    private int m;
    private int a;

    public Fecha(int _d, int _m, int _a) {
        d = _d;
        m = _m;
        a = _a;
    }

    public boolean esMayor(Fecha fecha) {
        return ((a > fecha.a) || (a == fecha.a && m > fecha.m) || (a == fecha.a && m == fecha.m && d > fecha.d));
    }

    public boolean igualQue(Fecha fecha) {
        return (a == fecha.a && m == fecha.m && d == fecha.d);
    }

    @Override
    public String toString() {
        return d + "/" + m + "/" + a;
    }

    public static Fecha fromString(String f) {
        String[] t = f.split("/");
        return new Fecha(Integer.parseInt(t[0]), Integer.parseInt(t[1]), Integer.parseInt(t[2]));
    }
}