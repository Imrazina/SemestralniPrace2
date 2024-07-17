
package cz.upce.fei.boop.pujcovna.data;

public class Anime extends Movie {
    public Anime(String nazev, int hodnoceni, int divaky) {
        super(TypMovie.ANIME, nazev, hodnoceni, PG.CHILD, divaky);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
