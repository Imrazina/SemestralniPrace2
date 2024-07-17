package cz.upce.fei.boop.pujcovna.perzistence;

import cz.upce.fei.boop.pujcovna.command.Command;
import cz.upce.fei.boop.pujcovna.data.Anime;
import cz.upce.fei.boop.pujcovna.data.Films;
import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.Serials;
import cz.upce.fei.boop.pujcovna.kolekce.KolekceException;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import cz.upce.fei.boop.pujcovna.spravce.SpravaMovie;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;


public class Perzistence {
    private static final SpojovySeznam<Movie> instance = SpravaMovie.getInstance();
    private static final Iterator<Movie> itr = instance.iterator();
     public static final SpravaMovie sprava = new SpravaMovie();
    
    
    public static void nacti(String nazev) {
        try ( ObjectInputStream obj = new ObjectInputStream(new FileInputStream(nazev))) {
            instance.zrus();
            int i = obj.readInt();
            for (int k = 0; k < i; k++) {
                Movie movie = (Movie) obj.readObject();
                instance.vlozPosledni(movie);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void uloz(String nazev) {
        try(ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(nazev))){
            obj.writeInt(instance.size());
            for (Movie movie : instance) {
                obj.writeObject(movie);
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void nactiText(String nazevSouboru) throws KolekceException {
        BufferedReader reader = null;
        instance.zrus();
        try {
            reader = new BufferedReader(new FileReader(nazevSouboru));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(", ");               
                String nazev = row[2].split("=")[1];
                int hodnoceni = Integer.parseInt(row[3].split("=")[1]);
                int divaky = Integer.parseInt(row[5].split("=")[1]);
                switch (row[0]) {
                    case "ANIME" -> {
                        instance.vlozPosledni(new Anime(nazev, hodnoceni, divaky));
                    }
                    case "FILM" -> {
                        instance.vlozPosledni(new Films(nazev, hodnoceni, divaky));
                    }
                    case "SERIAL" -> {
                        instance.vlozPosledni(new Serials(nazev, hodnoceni, divaky));
                    }
                }
            }
            sprava.vypis(System.out::println);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void ulozText(String nazevSouboru) {
        try {
            FileWriter writer = new FileWriter(nazevSouboru);
            while (itr.hasNext()) {
                writer.write(itr.next().toString() + "\n");
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
