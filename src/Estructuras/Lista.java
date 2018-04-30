package Estructuras;

import java.util.Iterator;

public class Lista<T extends Comparable<T>> implements Iterable<T> {

    private int count;
    private Nodo<T> inicio;
    private Nodo<T> fin;

    public Lista() {
        this.count = 0;
        this.inicio = null;
        this.fin = null;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void add(T dato) {
        switch (count) {
            case 0:
                inicio = new Nodo<>(dato);
                fin = inicio;
                break;
            default:
                Nodo<T> temp = inicio;
                while (temp != null) {
                    int cmp = temp.dato.compareTo(dato);
                    if (cmp > 0) {
                        insert(temp.ant, new Nodo<>(dato), temp);
                        break;
                    } else if (cmp == 0) {
                        insert(temp, new Nodo<>(dato), temp.sig);
                        break;
                    }
                    temp = temp.sig;
                }
                if (temp == null) {
                    insert(fin, new Nodo<>(dato), fin.sig);
                }
        }
        count++;
    }

    private void insert(Nodo<T> nodoa, Nodo<T> nodon, Nodo<T> nodod) {
        nodon.ant = nodoa;
        nodon.sig = nodod;
        if (nodoa != null) nodoa.sig = nodon;
        if (nodod != null) nodod.ant = nodon;
        if (nodoa == fin) fin = nodon;
        if (nodod == inicio) inicio = nodon;
    }

    public T get(int i) {
        Nodo<T> temp = inicio;
        int j = 0;
        while (temp != null) {
            if (j == i) {
                return temp.dato;
            }
            j++;
            temp = temp.sig;
        }
        return null;
    }

    public void removeAt(int i) {
        Nodo<T> temp = inicio;
        if (count == 0) return;
        int j = 0;
        while (temp != null) {
            if (j == i) {
                if (temp.ant == null && temp.sig == null) {
                    inicio = null;
                    fin = null;
                } else if (temp == inicio) {
                    inicio = temp.sig;
                    temp.sig.ant = null;
                } else if (temp == fin) {
                    fin = temp.ant;
                    temp.ant.sig = null;
                } else {
                    temp.ant.sig = temp.sig;
                    temp.sig.ant = temp.ant;
                }
                count--;
                break;
            }
            j++;
            temp = temp.sig;
        }
    }

    public void clear() {
        fin = null;
        inicio = null;
        count = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class Nodo<Y> {

        private Nodo<Y> ant;
        private Nodo<Y> sig;
        private Y dato;

        private Nodo(Nodo<Y> ant, Y dato, Nodo<Y> sig) {
            this.ant = ant;
            this.dato = dato;
            this.sig = sig;
        }

        private Nodo(Nodo<Y> ant, Y dato) {
            this.ant = ant;
            this.dato = dato;
            this.sig = null;
        }

        private Nodo(Y dato, Nodo<Y> sig) {
            this.ant = null;
            this.dato = dato;
            this.sig = sig;
        }

        private Nodo(Y dato) {
            this.ant = null;
            this.dato = dato;
            this.sig = null;
        }

    }

    private class ListIterator implements Iterator<T> {

        private Nodo<T> temp;
        private int pos;

        private ListIterator() {
            temp = inicio;
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            T dato;
            if (pos == 0) {
                dato = temp.dato;
            } else {
                temp = temp.sig;
                dato = temp.dato;
            }
            pos++;
            return dato;
        }
    }
}
