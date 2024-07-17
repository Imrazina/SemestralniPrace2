package cz.upce.fei.boop.pujcovna.spravce;

import cz.upce.fei.boop.pujcovna.data.Movie;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

interface Ovladani extends Iterable<Movie>{
    void novy(String nazevMovie, int pocetDivaku, int hodnoceni, int index);
    
    void nastavKomparator(Comparator<? super Movie> comparator);

    Movie najdi(Movie klic);

    Movie odeber();

    void prvni();

    void posledeni();

    void dalsi();

    Movie dej();

    void generuj(int pocet);

    void edituj(Function<Movie, Movie> editor);

    void vyjmi();

    int dejPocet();

    void obnov(String nazev) ;

    void zalohuj(String nazev);

    void vypis(Consumer<Movie> writer);

    void nactiText(String nazev);

    void ulozText(String nazev);

    void zrus();
    
    default Stream<Movie> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
