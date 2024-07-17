package cz.upce.fei.boop.pujcovna.data;


public class MovieKlic extends Movie{
    
    public MovieKlic(TypMovie typMovie, String nazev, int hodnoceni, PG pg, int divaky) {
        super(typMovie, nazev, hodnoceni, pg, divaky);
    }
    
    public MovieKlic(String nazev) {
        super(nazev);
    }
}
