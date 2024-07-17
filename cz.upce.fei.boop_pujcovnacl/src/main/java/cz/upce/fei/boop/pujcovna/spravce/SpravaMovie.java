package cz.upce.fei.boop.pujcovna.spravce;

import cz.upce.fei.boop.pujcovna.data.Anime;
import cz.upce.fei.boop.pujcovna.data.Films;
import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.Serials;
import cz.upce.fei.boop.pujcovna.generator.Generator;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import cz.upce.fei.boop.pujcovna.perzistence.Perzistence;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpravaMovie implements Ovladani {
    
    private Comparator<? super Movie> comparator;
    

    private static final SpojovySeznam<Movie> instance = new SpojovySeznam<>();

    public static SpojovySeznam<Movie> getInstance() {
        return instance;
    }
    
       @Override
    public void nastavKomparator(Comparator<? super Movie> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }


    @Override
    public void novy(String nazevMovie, int pocetDivaku, int hodnoceni, int index) {

        Movie movie = null;
        switch (index) {
            case 1 ->
                movie = new Anime(nazevMovie, pocetDivaku, hodnoceni);
            case 2 ->
                movie = new Films(nazevMovie, pocetDivaku, hodnoceni);
            case 3 ->
                movie = new Serials(nazevMovie, pocetDivaku, hodnoceni);
        }
        try {
            if (instance.jePrazdny()) {
                instance.vlozPrvni(movie);
                instance.vlozZaAktualni(movie);
            } else {
                instance.nastavPosledni();
                instance.vlozZaAktualni(movie);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Movie odeber() {
        
        try {
           return instance.odeberAktualni();
        } catch (Exception e) {
            e = new OvladaniException("Aktualni neni nastaven");
            return null;
        }
    }

    @Override
    public void prvni() {
        try {
            instance.nastavPrvni();
            instance.dejAktualni();
        } catch (Exception e) {
            e = new OvladaniException("Aktualni neni nastaven");
        }
    }

    @Override
    public void posledeni() {
        try {
            instance.nastavPosledni();
            instance.dejAktualni();
        } catch (Exception e) {
            e = new OvladaniException("Aktualni neni nastaven");
        }
    }
    

    public void prejdiNaPredchoziPolozku() {
        try {
            boolean najden = false;
            Movie data = instance.dejAktualni();
            instance.nastavPrvni();
            if (!instance.dejPrvni().equals(data)) {
                while (!najden) {
                    if (instance.dejZaAktualnim().equals(data)) {
                        najden = true;
                    } else {
                        instance.dalsi();
                    }
                }
            }
        } catch (Exception ex) {
            ex = new OvladaniException("Aktualni neni nastaven");
        }
    }

    public void vlozPolozku(Movie data) throws NullPointerException {
        instance.vlozPosledni(data);
    }

    @Override
    public void dalsi() {
        try {
            instance.dalsi();
            instance.dejAktualni();
        } catch (Exception e) {
             e = new OvladaniException("Aktualni neni nastaven");
        }
    }

    @Override
    public Movie dej() {
        try {
            return instance.dejAktualni();
        } catch (Exception e) {
            e = new OvladaniException("Aktualni neni nastaven");
            return null;
        }
    }

    @Override
    public void generuj(int pocet) {

        Generator.generateMovies(pocet, instance);
        try {
            instance.nastavPosledni();
        } catch (Exception ex) {
            ex = new OvladaniException("Nejprve musite vytvorit instanci");
        }
    }

    @Override
    public void vyjmi() {
        try {
            instance.odeberAktualni();
        } catch (Exception e) {
            throw new RuntimeException("Aktualni neni nastaven");
        }
    }

    @Override
    public int dejPocet() {
        return instance.size();
    }

    @Override
    public void obnov(String nazev) {
        Perzistence.nacti(nazev);
    }

    @Override
    public void zalohuj(String nazev) {
        Perzistence.uloz(nazev);
    }

    @Override
    public void vypis(Consumer<Movie> writer) {
        stream().forEach(writer);
    }

    @Override
    public void nactiText(String nazev) {
        try {
            Perzistence.nactiText(nazev);
        } catch (Exception ex) {
             ex = new OvladaniException("Nejprve musite vytvorit instanci");
        }
    }

    @Override
    public void ulozText(String nazev) {
        Perzistence.ulozText(nazev);
    }

    @Override
    public void zrus() {
        instance.zrus();
    }

    @Override
    public void edituj(Function<Movie, Movie> editor) {
        try {
            editor.apply(instance.dejAktualni());
        } catch (Exception ex) {
            ex = new OvladaniException("Nejprve musite vytvorit instanci");
        }

    }

    @Override
    public Iterator<Movie> iterator() {
        return instance.iterator();
    }

    @Override
    public Movie najdi(Movie klic) {
        try {
            return stream().filter((movie) -> comparator.compare(movie, klic) == 0)
                    .findFirst().get();
        } catch (Exception ex) {
            ex = new OvladaniException("Zadna polozka");
            return null;
        }
    }

}
