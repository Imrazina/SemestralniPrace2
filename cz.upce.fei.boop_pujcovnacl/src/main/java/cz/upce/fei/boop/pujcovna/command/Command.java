package cz.upce.fei.boop.pujcovna.command;
import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.MovieKlic;
import cz.upce.fei.boop.pujcovna.data.TypMovie;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.perzistence.Perzistence;
import cz.upce.fei.boop.pujcovna.spravce.SpravaMovie;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Command {
     private static Comparator<Movie> nazevMovie
            = (o1, o2) -> o1.getNazev().compareTo(o2.getNazev());
    public static final SpravaMovie sprava = new SpravaMovie();

    
    public static String getChoise(){
       
        System.out.println("Choose the number from menu");
        Scanner input = new Scanner(System.in);    
        String choise;
        
        while(true){
        choise = input.nextLine();
        if(     choise.equals("help")
                || choise.equals("h")
                || choise.equals("novy")
                || choise.equals("no")
                || choise.equals("najdi")
                || choise.equals("na")
                || choise.equals("n")
                || choise.equals("odeber")
                || choise.equals("od")
                || choise.equals("dej")
                || choise.equals("edituj")
                || choise.equals("edit")
                || choise.equals("vyjmi")
                || choise.equals("prvni")
                || choise.equals("pr")
                || choise.equals("dalsi")
                || choise.equals("da")
                || choise.equals("posledni")
                || choise.equals("po")
                || choise.equals("pocet")
                || choise.equals("obnov")
                || choise.equals("zalohuj")
                || choise.equals("vypis")
                || choise.equals("nactitext")
                || choise.equals("nt")
                || choise.equals("uloztext")
                || choise.equals("ut")
                || choise.equals("generuj")
                || choise.equals("g")
                || choise.equals("zrus")
                || choise.equals("exit")
           ){
        break;
        } else{
            System.out.println("Choose the other command");
        }
        }
    return choise;
    }
    
    public static void showCommands(){
        System.out.println("---------------------");
        System.out.println("help, h     - výpis příkazů");
        System.out.println("novy,no     - vytvoř novou instanci a vlož data za aktuální prvek");
        System.out.println("najdi,na,n  - najdi v seznamu data podle hodnoty nějakém atributu");
        System.out.println("odeber,od   - odeber data ze seznamu podle nějaké hodnoty atributu ");
        System.out.println("dej         - zobraz aktuální data v seznamu");
        System.out.println("edituj,edit - edituj aktuální data v seznamu");
        System.out.println("vyjmi       - vyjmi aktuální data ze seznamu");
        System.out.println("prvni,pr    - nastav jako aktuální první data v seznamu");
        System.out.println("dalsi,da    - přejdi na další data");
        System.out.println("posledni,po - přejdi na poslední data");
        System.out.println("pocet       - zobraz počet položek v seznamu");
        System.out.println("obnov       - obnov seznam data z binárního souboru");
        System.out.println("zalohuj     - zálohuj seznam dat do binárního souboru");
        System.out.println("vypis       - zobraz seznam dat");
        System.out.println("nactitext,nt- načti seznam data z textového souboru");
        System.out.println("uloztext,ut - ulož seznam data do textového souboru");
        System.out.println("generuj,g   - generuj náhodně data pro testování");
        System.out.println("zrus        - zruš všechny data v seznamu");
        System.out.println("exit        - ukončení programu");
    }
    
  
    
    public static void ShowMenu() throws KolekceException{
    Scanner sc = new Scanner(System.in);
    String choise;
    boolean exit = false;
    sprava.nastavKomparator(nazevMovie);
    showCommands();
    while(!exit){
    choise = getChoise();
    
            switch (choise) {
                case "help", "h":
                    showCommands();
                    break;

                case "exit":
                    System.out.println("Konec programu");
                    exit = true;
                    break;

                case "novy", "no":
                        Movie movie = null;
                        System.out.println("Vyberte cislo: \n"
                                + "1. Anime\n"
                                + "2. Film\n"
                                + "3. Serial");
                        int comanda = sc.nextInt();
                            System.out.println("Zadejte nazev movie: ");
                            String nazevMovie = sc.next();
                            System.out.println("Zadejte pocet divaku: ");
                            int pocetDivaku = sc.nextInt();
                            System.out.println("Zadejte hodnoceni mezi <0,10>: ");
                            int hodnoceni = sc.nextInt();
                            sprava.novy(nazevMovie, pocetDivaku, hodnoceni, comanda);
                            
                    break;

                case "najdi", "na", "n":
                    
                    System.out.println("Zadejte nazev podle ktereho budeme hleadat");
                    nazevMovie = Keyboard.getStringItem("");
                    System.out.println(sprava.najdi(new MovieKlic(nazevMovie)));
                   break;

                case "odeber", "od":
//                    System.out.println("Zadejte atribut daty pro smazeny");
//                    int hledat = sc.nextInt();
                    
                    System.out.println("Data " + sprava.odeber()
                            + " je odebrana");
                    break;

                case "dej":
                sprava.dej();
                break;
                
                case "edituj", "edit":
                    Movie dejMovie = sprava.dej();
                    String nameMovie = dejMovie.getNazev();
                    TypMovie typ = dejMovie.getTypMovie();
                    int pocetDivaky = dejMovie.getDivaky();
                    int hodnoceniMovie = dejMovie.getHodnoceni();
                        try {
                            System.out.println(dejMovie.toString());
                            System.out.println("Zadejte číslo atributů, které chcete editovat: ");
                            List<String> listAtributu = Arrays.asList("1 - nazev movie", "2 - typ movie", "3 - pocet divaku", "4 -hodnoceni");
                            listAtributu.forEach(System.out::println);
                            int cisloAtr = sc.nextInt();
                            switch (cisloAtr) {
                                case 1 -> {
                                    System.out.println("Zadejte nazev movie: ");
                                    String nazev = sc.next();
                                    dejMovie.setNazev(nazev);
                                }
                                case 2 -> {
                                    System.out.println("Zadejte cislo typu: \n 1 - anime,\n 2 - film,\n 3 - serial");
                                    String typy = sc.next();
                                    switch (typy) {
                                        case "1" -> dejMovie.setTypMovie(TypMovie.ANIME);
                                        case "2" -> dejMovie.setTypMovie(TypMovie.FILM);
                                        case "3" -> dejMovie.setTypMovie(TypMovie.SERIAL);                                       
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Zadejte pocet : ");
                                    int pocet = sc.nextInt();
                                    dejMovie.setDivaky(pocet);
                                }
                                case 4 -> {
                                    System.out.println("Zadejte hodnoceni: ");
                                    int hodnoceniMov = sc.nextInt();
                                    if(hodnoceniMov > 0 && hodnoceniMov<=10){
                                    dejMovie.setHodnoceni(hodnoceniMov);} else { System.out.println("Musíte si vybrat mezi 0 a 10");}
                                }
                                default ->
                                    System.out.println("Musíte si vybrat mezi 0 a 3");
                            }
                        } catch (NullPointerException e) {
                            System.out.println("Aktualni neni nastaven");
                        }
                    break;
                    
                case "vyjmi":
                     sprava.vyjmi();
                    break;

                case "prvni", "pr":
                       sprava.prvni();
                    break;
                    
                case "dalsi", "da":
                    sprava.dalsi();
                    break;
                
                case "posledni", "po":
                    sprava.posledeni();
                    break;
                    
                case "pocet":
                    System.out.println("Pocet dat: " + sprava.dejPocet());
                    break;
                    
                case "obnov":
                    Perzistence.nacti("binarniSoubor");
                    break;
                    
                case "zalohuj":
                     System.out.println("Zadejte nazev souboru");
                     String nazev = sc.next();
                     sprava.zalohuj(nazev);
                    break;
                    
                case "vypis":
                    sprava.vypis(System.out::println);
                    break;
                    
                case "nactitext", "nt":
                    System.out.println("Zadejte nazev souboru");
                    String nazevSouboru = sc.next();
                    sprava.nactiText(nazevSouboru);
                    break;
                    
                case "uloztext", "ut":
                    System.out.println("Zadejte nazev souboru");
                    String nazevSouboruProUlozeni = sc.next();
                    sprava.ulozText(nazevSouboruProUlozeni);
                    break;
                    
                case "generuj", "g":
                    System.out.println("Zadejte pocet generaci");
                        int genPocet = sc.nextInt();
                        int temp = 0;
                        temp+=genPocet;
                        if (sprava.dejPocet() <= 45 && temp >= 0 && temp <= 45 && genPocet >= 0) sprava.generuj(genPocet);
                        else {
                            System.out.println("Pocet generaci je vetsi nez 45");
                        }
                    break;

                    
                case "zrus", "z":
                    sprava.zrus();
                        System.out.println("Seznam byl odstranen");
                    break;
            }
        }
    }

    
}
