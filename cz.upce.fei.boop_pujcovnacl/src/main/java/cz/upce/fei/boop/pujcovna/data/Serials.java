package cz.upce.fei.boop.pujcovna.data;

public class Serials extends Movie {
    
    public Serials(String nazev, int hodnoceni, int divaky) {
        super(TypMovie.SERIAL, nazev, hodnoceni, PG.TEEN, divaky);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
}
