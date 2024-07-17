package cz.upce.fei.boop.pujcovna.data;

import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import cz.upce.fei.boop.pujcovna.spravce.SpravaMovie;
import java.io.Serializable;


public abstract class Movie implements Cloneable, 
        Serializable, Comparable<Movie> {
     private static final SpojovySeznam<Movie> instance = SpravaMovie.getInstance();
    
    private final int id = instance.size;
    private String nazev;
    private int hodnoceni;
    private PG pg;
    TypMovie typMovie;
    private int divaky;

    public Movie(TypMovie typMovie, String nazev, int hodnoceni, PG pg, int divaky) {
        this.typMovie = typMovie;
        this.nazev = nazev;
//        this.id = instance.size();
        this.hodnoceni = hodnoceni;
        this.pg = pg;
        this.divaky = divaky;
    }
    
    public Movie(String nazev) {
        this.nazev = nazev;
    }
    

    public int getId() {
        return id;
    }

    public String getNazev() {
        return nazev;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public PG getPG() {
        return pg;
    }

    public int getDivaky() {
        return divaky;
    }

    public TypMovie getTypMovie() {
        return typMovie;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setHodnoceni(int hodnoceni) {
        this.hodnoceni = hodnoceni;
    }

    public void setPg(PG pg) {
        this.pg = pg;
    }

    public void setDivaky(int divaky) {
        this.divaky = divaky;
    }

    public void setTypMovie(TypMovie typMovie) {
        this.typMovie = typMovie;
    }

    @Override
    public String toString() {
        return typMovie + ", id=" + id + ", nazev=" + nazev + ", hodnoceni=" + hodnoceni + ", PG=" + pg + ", divaky=" + divaky;
    }
    
    @Override
    public int compareTo(Movie m){
        return nazev.compareTo(m.nazev());
    }

    private String nazev() {
        return nazev;
    }
    
      @Override
    public Movie clone() throws CloneNotSupportedException {
        Movie novyProstredek = (Movie) super.clone();
        return novyProstredek;
    }
    
}
