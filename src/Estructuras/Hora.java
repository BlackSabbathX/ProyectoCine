package Estructuras;

public class Hora {
    private int minuto;
    private int hora;

    public Hora(int _hora, int _minuto) {
        minuto = _minuto;
        hora = _hora;
    }

    public boolean esMayor(Hora h) {
        return ((hora > h.hora) || (hora == h.hora && minuto > h.minuto));
    }

    public boolean igualQue(Hora h) {
        return (hora == h.hora && minuto == h.minuto);
    }

    @Override
    public String toString() {
        String h = String.valueOf(hora);
        String m = String.valueOf(minuto);
        if (hora < 10) h = "0" + h;
        if (minuto < 10) m = "0" + m;
        return h + ":" + m;
    }

    public static Hora fromString(String h) {
        String[] t = h.split(":");
        return new Hora(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
    }
}
