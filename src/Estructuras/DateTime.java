package Estructuras;

public class DateTime {

    private Dia dia;
    private Hora hora;

    public DateTime(Dia _dia, Hora _hora) {
        dia = _dia;
        hora = _hora;
    }

    public static DateTime fromString(String d) {
        String[] t = d.split(Separator.B);
        return new DateTime(Dia.fromString(t[0]), Hora.fromString(t[1]));
    }

    public boolean mayorQue(DateTime d) {
        return (dia.mayorQue(d.dia) || (dia == d.dia && hora.mayorQue(d.hora)));
    }

    public boolean igualQue(DateTime d) {
        return hora == d.hora && dia == d.dia;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return dia.toString() + Separator.B + hora.toString();
    }
}
