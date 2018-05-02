package Estructuras;

public enum Hora {
    nueveA(9),
    once(11),
    una(13),
    tres(15),
    cinco(17),
    siete(19),
    nueveP(21);

    private int hora;

    Hora(int _hora) {
        hora = _hora;
    }

    public static Hora fromString(String h) {
        int n = Integer.parseInt(h.split(":")[0]);
        for (Hora ho : Hora.values()) {
            if (ho.hora == n) return ho;
        }
        return null;
    }

    public boolean mayorQue(Hora h) {
        return hora > h.hora;
    }

    public boolean igualQue(Hora h) {
        return hora == h.hora;
    }

    @Override
    public String toString() {
        String h = String.valueOf(hora);
        if (hora < 10) h = "0" + h;
        return h + ":00 ";
    }
}
