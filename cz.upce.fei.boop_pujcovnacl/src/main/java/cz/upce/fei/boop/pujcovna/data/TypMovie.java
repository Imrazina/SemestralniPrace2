
package cz.upce.fei.boop.pujcovna.data;


public enum TypMovie {
    FILM("Film"),
    SERIAL("Serial"),
    ANIME("Anime");
          
    
    private final String nazev;
    
     private TypMovie(String nazev) {
        this.nazev = nazev;
    }
   
    public String nazev(){
    return nazev;
    }
}
