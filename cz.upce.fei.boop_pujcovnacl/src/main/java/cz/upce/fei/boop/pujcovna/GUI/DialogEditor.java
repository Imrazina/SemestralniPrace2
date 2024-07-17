package cz.upce.fei.boop.pujcovna.GUI;

import cz.upce.fei.boop.pujcovna.data.Anime;
import cz.upce.fei.boop.pujcovna.data.Films;
import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.PG;
import cz.upce.fei.boop.pujcovna.data.Serials;
import cz.upce.fei.boop.pujcovna.data.TypMovie;
import static cz.upce.fei.boop.pujcovna.data.TypMovie.ANIME;
import static cz.upce.fei.boop.pujcovna.data.TypMovie.FILM;
import static cz.upce.fei.boop.pujcovna.data.TypMovie.SERIAL;
import java.io.Serial;
import java.util.function.Function;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DialogEditor implements Function<Movie, Movie> {

    
    private Dialog<Movie> dialog;
    private Movie movie;

    private GridPane gridPane;
    private ComboBox<String> BoxPG;
    private TextField tfNazev;
    private TextField tfPocetDivaku;
    private Spinner<Integer> spinnerHodnoceni;

    private static DialogEditor seznam = new DialogEditor();

        public static DialogEditor getDialogEditor() {
        return seznam;
    }

    @Override
    public Movie apply(Movie t) {
        movie = t;
        dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        gridPane = new GridPane();
        gridPane.setMinHeight(200);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));
        nastavDialog();
        edituj();
        
        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
        
        return movie;
    }

    private void nastavDialog() {
                switch (movie.getTypMovie()) {
            case FILM:
               
                nastavGridFilm();
                break;
            case ANIME:
            
                nastavGridAnime();
                break;
            case SERIAL:
               
                nastavGridSerial();
                break;
        }
    

    }

    private void nastavGridFilm() {
        Films film = (Films) movie;
        BoxPG = new ComboBox();
        for(PG value: PG.values()){
            BoxPG.getItems().add(value.nazev());
        }
        
        BoxPG.getSelectionModel().select(film.getPG().nazev());
        spinnerHodnoceni = new Spinner<>();
        SpinnerValueFactory svcHodnoceni = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, film.getHodnoceni());
        spinnerHodnoceni.setValueFactory(svcHodnoceni);
        
        tfNazev = new TextField(movie.getNazev());
        tfPocetDivaku = new TextField(String.valueOf(movie.getDivaky()));
        gridPane.add(new Label("Nazev: "), 0, 1);
        gridPane.add(tfNazev, 1, 1);
        gridPane.add(new Label("Pocet Divaku: "), 0, 2);
        gridPane.add(tfPocetDivaku, 1, 2);
        
        gridPane.add(new Label("PG: "), 0, 3);
        gridPane.add(BoxPG, 1, 3);
    
        gridPane.add(new Label("Hodnoceni: "), 0, 4);
        gridPane.add(spinnerHodnoceni, 1, 4);
    
    }

    private void nastavGridAnime() {
    
        Anime anime = (Anime) movie;
        BoxPG = new ComboBox();
        for(PG value: PG.values()){
            BoxPG.getItems().add(value.nazev());
        }
        
        BoxPG.getSelectionModel().select(anime.getPG().nazev());
        spinnerHodnoceni = new Spinner<>();
        SpinnerValueFactory svcHodnoceni = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, anime.getHodnoceni());
        spinnerHodnoceni.setValueFactory(svcHodnoceni);
        
        tfNazev = new TextField(movie.getNazev());
        tfPocetDivaku = new TextField(String.valueOf(movie.getDivaky()));
        gridPane.add(new Label("Nazev: "), 0, 1);
        gridPane.add(tfNazev, 1, 1);
        gridPane.add(new Label("Pocet Divaku: "), 0, 2);
        gridPane.add(tfPocetDivaku, 1, 2);
        
        gridPane.add(new Label("PG: "), 0, 3);
        gridPane.add(BoxPG, 1, 3);
    
        gridPane.add(new Label("Hodnoceni: "), 0, 4);
        gridPane.add(spinnerHodnoceni, 1, 4);
    
    
    }

    private void nastavGridSerial() {
   
        Serials serial = (Serials) movie;
        BoxPG = new ComboBox();
        for(PG value: PG.values()){
            BoxPG.getItems().add(value.nazev());
        }
        
        BoxPG.getSelectionModel().select(serial.getPG().nazev());
        spinnerHodnoceni = new Spinner<>();
        SpinnerValueFactory svcHodnoceni = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, serial.getHodnoceni());
        spinnerHodnoceni.setValueFactory(svcHodnoceni);
        
        tfNazev = new TextField(movie.getNazev());
        tfPocetDivaku = new TextField(String.valueOf(movie.getDivaky()));
        gridPane.add(new Label("Nazev: "), 0, 1);
        gridPane.add(tfNazev, 1, 1);
        gridPane.add(new Label("Pocet Divaku: "), 0, 2);
        gridPane.add(tfPocetDivaku, 1, 2);
        
        gridPane.add(new Label("PG: "), 0, 3);
        gridPane.add(BoxPG, 1, 3);
    
        gridPane.add(new Label("Hodnoceni: "), 0, 4);
        gridPane.add(spinnerHodnoceni, 1, 4);
    
    }
    
        private void edituj() {
       dialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event -> {
           switch(movie.getTypMovie()){
               case FILM:
                PG pgFilm = null;
                String selectedPGFilm = BoxPG.getValue();
                if (selectedPGFilm != null) {
                    for (PG pgValue : PG.values()) {
                        if (pgValue.nazev().equals(selectedPGFilm)) {
                            pgFilm = pgValue;
                            break;
                        }
                    }
                }
                if (pgFilm == null) {
                    // Обработка случая, когда не выбрано значение PG
                    showErrorDialog("Prosím, vyberte PG");
                    event.consume();
                    return;
                }
                    int _hodnoceni = spinnerHodnoceni.getValue();
                    Films film = (Films) movie;
                    String nazev = tfNazev.getText();
                    double divaky = Double.parseDouble(tfPocetDivaku.getText());
                    film.setNazev(nazev);
                    film.setPg(pgFilm);
                    film.setHodnoceni(_hodnoceni);
                    film.setDivaky((int) divaky);
                    break;
                    
               case ANIME:
                PG pgAnime = null;
                String selectedPGAnime = BoxPG.getValue();
                if (selectedPGAnime != null) {
                    for (PG pgValue : PG.values()) {
                        if (pgValue.nazev().equals(selectedPGAnime)) {
                            pgAnime = pgValue;
                            break;
                        }
                    }
                }
                if (pgAnime == null) {
                    // Обработка случая, когда не выбрано значение PG
                    showErrorDialog("Prosím, vyberte PG");
                    event.consume();
                    return;
                }
                    int _hodnoceniAnime = spinnerHodnoceni.getValue();
                    Anime anime = (Anime) movie;
                    String nazevAnime = tfNazev.getText();
                    double divakyAnime = Double.parseDouble(tfPocetDivaku.getText());
                    anime.setNazev(nazevAnime);
                    anime.setPg(pgAnime);
                    anime.setHodnoceni(_hodnoceniAnime);
                    anime.setDivaky((int) divakyAnime);
                   break;
               case SERIAL:
                    PG pgSerial = null;
                String selectedPGSerial = BoxPG.getValue();
                if (selectedPGSerial != null) {
                    for (PG pgValue : PG.values()) {
                        if (pgValue.nazev().equals(selectedPGSerial)) {
                            pgSerial = pgValue;
                            break;
                        }
                    }
                }
                if (pgSerial == null) {
                    // Обработка случая, когда не выбрано значение PG
                    showErrorDialog("Prosím, vyberte PG");
                    event.consume();
                    return;
                }
                    int _hodnoceniSerial = spinnerHodnoceni.getValue();
                    Serials serial = (Serials) movie;
                    String nazevSerial = tfNazev.getText();
                    double divakySerial = Double.parseDouble(tfPocetDivaku.getText());
                    serial.setNazev(nazevSerial);
                    serial.setPg(pgSerial);
                    serial.setHodnoceni(_hodnoceniSerial);
                    serial.setDivaky((int) divakySerial);
                   break;
           }
    });}

    private void showErrorDialog(String errorMessage) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

}
