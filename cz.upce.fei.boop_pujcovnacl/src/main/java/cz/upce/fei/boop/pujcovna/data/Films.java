package cz.upce.fei.boop.pujcovna.data;


public class Films extends Movie {
    public Films(String nazev, int hodnoceni, int divaky) {
        super(TypMovie.FILM, nazev, hodnoceni, PG.EDULT, divaky);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
