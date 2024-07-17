package cz.upce.fei.boop.pujcovna.GUI;

import cz.upce.fei.boop.pujcovna.data.Anime;
import cz.upce.fei.boop.pujcovna.data.Films;
import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.PG;
import cz.upce.fei.boop.pujcovna.data.TypMovie;
import static cz.upce.fei.boop.pujcovna.data.TypMovie.ANIME;
import static cz.upce.fei.boop.pujcovna.data.TypMovie.FILM;
import static cz.upce.fei.boop.pujcovna.data.TypMovie.SERIAL;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DialogNovy {
    private Dialog<Movie> dialog;
    private GridPane gridPane;
    private ComboBox<String> cbTypMovie;
    private ComboBox<String> cbPG;
    private TextField tfNazev = new TextField("Uvedte nazev");
    private TextField tfDivaky = new TextField("0");
    private Spinner<Integer> hodnoceni;

    private Movie result;
    
    public DialogNovy() {
        dialog = new Dialog<>();
        dialog.setTitle("New movie");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        nastavCbTypProstredku();

        gridPane = new GridPane();
        gridPane.setMinHeight(300);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        cbTypMovie.getSelectionModel().clearSelection();
        ziskejMovie();
        nastavGrid();
        
        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
    }
    
     public static DialogNovy getDialogNovy() {
        return new DialogNovy();
    }

    private void nastavCbTypProstredku() {
        cbTypMovie = new ComboBox<>();
        for (TypMovie value : TypMovie.values()) {
            cbTypMovie.getItems().add(value.nazev());
        }
        cbTypMovie.getSelectionModel().selectFirst();
    }

      private void nastavGrid() {
        gridPane.add(new Label("Typ Movie:"), 0, 0);
        gridPane.add(cbTypMovie, 1, 0);
        gridPane.add(new Label("Nazev: "), 0, 1);
        gridPane.add(tfNazev, 1, 1);
        gridPane.add(new Label("Pocet Divaku: "), 0, 2);
        gridPane.add(tfDivaky, 1, 2);
        
        cbPG = new ComboBox();
        for(PG value: PG.values()){
            cbPG.getItems().add(value.nazev());
        } 
        
        hodnoceni = new Spinner<>();
        SpinnerValueFactory svcPocet = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 1);
        hodnoceni.setValueFactory(svcPocet);
        cbPG.getSelectionModel().selectFirst();
        gridPane.add(new Label("PG: "), 0, 3);
        gridPane.add(cbPG, 1, 3);
        gridPane.add(new Label("Hodnoceni:"), 0, 4);
        gridPane.add(hodnoceni, 1, 4);
    }
    
    private void poNastaveniTypu() {
   
    }

    private void ziskejMovie() {
      dialog.setResultConverter(buttonType -> {
        if (buttonType == ButtonType.OK) {
            String nazev = tfNazev.getText();
            float divaky;
            try {
                divaky = Float.parseFloat(tfDivaky.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Chyba");
                alert.setHeaderText("Neplatný vstup pro pocet divaku");
                alert.setContentText("Prosím, zadejte platné číslo pro pocet divaku");
                alert.showAndWait();
                return null;
            }
            TypMovie typ = TypMovie.valueOf(cbTypMovie.getValue().toUpperCase());
            switch (typ) {
                case FILM:   
                    PG pgFilm = null;
                String selectedPGFilm = cbPG.getValue();
                if (selectedPGFilm != null) {
                    for (PG pgValue : PG.values()) {
                        if (pgValue.nazev().equals(selectedPGFilm)) {
                            pgFilm = pgValue;
                            break;
                        }
                    }
                }
                if (pgFilm == null) {
                    showErrorDialog("Prosím, vyberte PG");    
                }
                    int _hodnoceni = hodnoceni.getValue();
                    result = new Films(nazev, _hodnoceni, (int) divaky);
                    break;
                    
                case ANIME:
                PG pgAnime = null;
                String selectedPGAnime = cbPG.getValue();
                if (selectedPGAnime != null) {
                    for (PG pgValue : PG.values()) {
                        if (pgValue.nazev().equals(selectedPGAnime)) {
                            pgAnime = pgValue;
                            break;
                        }
                    }
                }
                if (pgAnime == null) {
                    showErrorDialog("Prosím, vyberte PG");    
                }
                    int _hodnoceniAnime = hodnoceni.getValue();
                    result = new Anime(nazev, _hodnoceniAnime, (int) divaky);
                    break;
                     
                case SERIAL:
                PG pgSerial = null;
                String selectedPGSerial = cbPG.getValue();
                if (selectedPGSerial != null) {
                    for (PG pgValue : PG.values()) {
                        if (pgValue.nazev().equals(selectedPGSerial)) {
                            pgSerial = pgValue;
                            break;
                        }
                    }
                }
                if (pgSerial == null) {
                    showErrorDialog("Prosím, vyberte PG");    
                }
                    int _hodnoceniSerial = hodnoceni.getValue();
                    result = new Films(nazev, _hodnoceniSerial, (int) divaky);
                    break;      
            }   
            return result;
        } else {
            return null;
        }
    });
    }  

    private void showErrorDialog(String errorMessage) {
     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
    
    
     public Movie dejResult() {
        return result;
    }
}
