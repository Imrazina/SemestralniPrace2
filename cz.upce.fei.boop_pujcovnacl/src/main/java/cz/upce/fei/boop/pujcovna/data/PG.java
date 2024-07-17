package cz.upce.fei.boop.pujcovna.data;


public enum PG {
    EDULT("18+"),
    TEEN("12+"),
    CHILD("6+");
          
    
    private final String nazev;
    
     private PG(String nazev) {
        this.nazev = nazev;
    }
   
    public String nazev(){
    return nazev;
    }
}
