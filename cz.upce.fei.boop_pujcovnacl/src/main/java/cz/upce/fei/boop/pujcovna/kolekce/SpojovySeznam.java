
package cz.upce.fei.boop.pujcovna.kolekce;
 
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SpojovySeznam<E> implements Seznam<E> {

    public int size;
    private Prvek prvni;
    private Prvek aktualni;
    private Prvek posledni;

    public class Prvek<E> {
        public E data;
        public Prvek<E> next;
        public Prvek(E data, Prvek<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    
    

    @Override
    public void nastavPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        } else {
            aktualni = prvni;
        }
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        } else {
            aktualni = posledni;
        }
    }

    @Override
    public void dalsi() throws KolekceException {
        if (jePrazdny() || aktualni == null || aktualni == posledni) {
            throw new KolekceException("s");
        } else if (jeDalsi()) {
            aktualni = aktualni.next;
        }
    }

    @Override
    public boolean jeDalsi() {
        if (aktualni == null || jePrazdny()) {
            return false;
        } else{
            return (aktualni.next != null);
        }
    }

    @Override
    public void vlozPrvni(E data) {
        if (data == null) {
            throw new NullPointerException("");
        }
        prvni = new Prvek(data, prvni);
        if (size == 0) {
            posledni = prvni;
        }
        size++;
    }

    @Override
    public void vlozPosledni(E data) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (jePrazdny()) {
            vlozPrvni(data);
        } else {
            posledni.next = new Prvek(data, null);
            posledni = posledni.next;
            size++;
        }
    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
    Objects.requireNonNull(data);
    Prvek<E> prvek = new Prvek<>(data, null); 
    if (aktualni == null) {
//        aktualni = prvni; 
        aktualni = posledni; 
    } else if (!jeDalsi()) {
        aktualni.next = prvek;
        posledni = prvek;
    } else { 
        prvek.next = aktualni.next;
        aktualni.next = prvek;
    }
    size++;
}

    @Override
    public boolean jePrazdny() {
        return prvni == null || size == 0;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
//        aktualni = prvni;
        return (E) prvni.data;
    }

    @Override
    public E dejPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
//        aktualni = posledni;
        return (E) posledni.data;
    }

    @Override
    public E dejAktualni() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException();
        }
        return (E) aktualni.data;
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException();
        }
        if (aktualni.next == null) {
            throw new KolekceException();
        }
        return (E) aktualni.next.data;
    }

    @Override
    public E odeberPrvni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        E tempData = (E) prvni.data;
        if (aktualni == prvni) {
            aktualni = null;
        }
        prvni = prvni.next;
        size--;
        return tempData;
    }

    @Override
    public E odeberPosledni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        if (aktualni == posledni) {
            aktualni = null;
        }
        E tempData = (E) posledni.data;
        if (size == 1) {
            prvni = null;
            posledni = null;
        } else {
            Prvek<E> temp = prvni;

            while (temp.next != posledni && temp.next != null) {
                temp = temp.next;
            }

            temp.next = null;
            posledni = temp;
        }
        size--;
        return tempData;
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        if (jePrazdny()) {
            throw new KolekceException();
        }
        if (aktualni == prvni) {
            return odeberPrvni();
        }
        if (aktualni == posledni) {
            return odeberPosledni();
        }

        E tempData = (E) aktualni.data;
        Prvek<E> pomPointer = prvni;
        while (pomPointer.next != aktualni) {
            pomPointer = pomPointer.next;
        }
        pomPointer.next = aktualni.next;
        aktualni = null;
        size--;

        return tempData;
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        if (jePrazdny() || aktualni == null) {
            throw new KolekceException();
        }
        E tempData = null;
        if (aktualni.next != null) {
            tempData = (E) aktualni.next.data;
            aktualni.next = aktualni.next.next;
            size--;
        } else {
            throw new KolekceException();
        }
        return tempData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void zrus() {
        size = 0;
        prvni = posledni = aktualni = null;
    }

    @Override

        public Iterator<E> iterator() {
        return new Iterator<E>() {
        Prvek<E> aktualniIterator = null;

        @Override
        public boolean hasNext() {
            if (aktualniIterator == null) {
                return prvni != null;
            }
            return aktualniIterator.next != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                if (aktualniIterator == null) {
                    aktualniIterator = prvni;
                } else {
                    aktualniIterator = aktualniIterator.next;
                }
                return aktualniIterator.data;
            }
            throw new NoSuchElementException();
        }
    };
}
}

    

