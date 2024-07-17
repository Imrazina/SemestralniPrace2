package cz.upce.fei.boop.pujcovna.generator;

import cz.upce.fei.boop.pujcovna.command.Command;
import cz.upce.fei.boop.pujcovna.data.Anime;
import cz.upce.fei.boop.pujcovna.data.Films;
import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.Serials;
import cz.upce.fei.boop.pujcovna.kolekce.SpojovySeznam;
import cz.upce.fei.boop.pujcovna.spravce.SpravaMovie;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Generator {

    private static final Random random = new Random();
    private static final SpojovySeznam<Movie> instance = SpravaMovie.getInstance();
    private static final Set<String> nazevMovie = new HashSet<>();

    public static Films generateRandomFilm() {
        String randomNazev = generateRandomNazev();
        int randomHodnoceni = random.nextInt(11);
        int randomDivaky = random.nextInt(10000);

        return new Films(randomNazev, randomHodnoceni, randomDivaky);
    }

    public static Anime generateRandomAnime() {
        String randomNazev = generateRandomNazev();
        int randomHodnoceni = random.nextInt(11);
        int randomDivaky = random.nextInt(10000);

        return new Anime(randomNazev, randomHodnoceni, randomDivaky);
    }

    public static Serials generateRandomSerial() {
        String randomNazev = generateRandomNazev();
        int randomHodnoceni = random.nextInt(11);
        int randomDivaky = random.nextInt(10000);

        return new Serials(randomNazev, randomHodnoceni, randomDivaky);
    }

    private static String generateRandomNazev() {
        String[] words = {"The", "Great", "Adventure", "Journey", "Fantastic", "Mystery", "Wonder", "Brave", "Epic", "Legend"};
        String[] genres = {"Action", "Comedy", "Drama", "Horror", "Sci-Fi", "Romance", "Thriller", "Fantasy"};

        String randomWord = words[random.nextInt(words.length)];
        String randomGenre = genres[random.nextInt(genres.length)];

        return randomWord + " " + randomGenre;
    }

    public static void generateMovies(int count, SpojovySeznam<Movie> instance) {
        for (int i = 0; i < count; i++) {
            Films randomFilm = Generator.generateRandomFilm();
            instance.vlozPosledni(randomFilm);
        }
        for (int i = 0; i < count; i++) {
            Anime randomAnime = Generator.generateRandomAnime();
            instance.vlozPosledni(randomAnime);
        }
        for (int i = 0; i < count; i++) {
            Serials randomSerial = Generator.generateRandomSerial();
            instance.vlozPosledni(randomSerial);
        }
        System.out.println("Schvaleno");
    }
}
