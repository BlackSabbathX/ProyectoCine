package Estructuras;


public enum Dia {
    lunes(1) {
        @Override
        public String toString() {
            return "Lunes";
        }
    },
    martes(2) {
        @Override
        public String toString() {
            return "Martes";
        }
    },
    miercoles(3) {
        @Override
        public String toString() {
            return "Miércoles";
        }
    },
    jueves(4) {
        @Override
        public String toString() {
            return "Jueves";
        }
    },
    viernes(5) {
        @Override
        public String toString() {
            return "Viernes";
        }
    },
    sabado(6) {
        @Override
        public String toString() {
            return "Sábado";
        }
    },
    domingo(7) {
        @Override
        public String toString() {
            return "Domingo";
        }
    };

    private int numDia;

    Dia(int i) {
        numDia = i;
    }

    private int getNumDia() {
        return numDia;
    }

    public boolean mayorQue(Dia dia) {
        return numDia > dia.numDia;
    }

    public static Dia fromString(String text) {
        for (Dia dia : Dia.values()) {
            if (dia.toString().toLowerCase().equals(text.toLowerCase())) return dia;
        }
        return null;
    }
}
