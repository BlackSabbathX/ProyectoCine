package Estructuras;

public class DateTime {

    private Fecha fecha;
    private Dia dia;
    private Hora hora;

    public DateTime(Fecha _fecha, Dia _dia, Hora _hora) {
        fecha = _fecha;
        dia = _dia;
        hora = _hora;
    }

    public boolean esMayor(DateTime d) {
        return ((fecha.esMayor(d.fecha)) || (fecha.igualQue(d.fecha) && hora.esMayor(d.hora)));
    }

    public boolean igualQue(DateTime d) {
        return (fecha.igualQue(d.fecha) && dia == d.dia && hora.igualQue(d.hora));
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
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
        return fecha.toString() + Separator.B + dia.toString() + Separator.B + hora.toString();
    }

    public static DateTime fromString(String d) {
        String[] t = d.split(Separator.B);
        return new DateTime(Fecha.fromString(t[0]), Dia.fromString(t[1]), Hora.fromString(t[2]));
    }
}
