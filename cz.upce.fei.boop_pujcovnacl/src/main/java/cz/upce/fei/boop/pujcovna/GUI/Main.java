package cz.upce.fei.boop.pujcovna.GUI;

import cz.upce.fei.boop.pujcovna.data.Movie;
import cz.upce.fei.boop.pujcovna.data.MovieKlic;
import cz.upce.fei.boop.pujcovna.data.TypMovie;
import cz.upce.fei.boop.pujcovna.spravce.SpravaMovie;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final BorderPane bp = new BorderPane();
    private ListView<Movie> movieView;
    private ComboBox<TypMovie> cbFiltr;
    private VBox verticalPanel;
    private HBox horizontalPanel;
    private final SpravaMovie spravce = new SpravaMovie();

    public static void main(String[] args) {
        Application.launch("");
    }

    @Override
    public void start(Stage stage) throws Exception {

        setUpMovieView();
        setUpRightPanel();
        setUpBottomPanel();
        closeApp(stage);

        Scene scene = new Scene(bp, 830, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void error(String msg) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("ERROR");
        error.setHeaderText(msg);
        error.showAndWait();
    }

    private void setUpMovieView() {
        movieView = new ListView<>();
        bp.setCenter(movieView);
        bp.setMaxSize(300, 300);
    }

    private void setUpRightPanel() {
        verticalPanel = new VBox();
        verticalPanel.setAlignment(Pos.CENTER);
        verticalPanel.setSpacing(15);
        verticalPanel.setPadding(new Insets(15));

        Label prochLbl = new Label("Prochazeni");

        Button prvniBtn = new Button("Prvni");
        prvniBtn.setOnAction(e -> {
            kontrolaPrazdnoty();
            spravce.prvni();
            movieView.getSelectionModel().select(spravce.dej());
        });

        Button dalsiBtn = new Button("Dalsi");
        dalsiBtn.setOnAction(e -> {
            kontrolaPrazdnoty();
            spravce.dalsi();
            movieView.getSelectionModel().select(spravce.dej());
        });

        Button predchoziBtn = new Button("Predchozi");
        predchoziBtn.setOnAction(e -> {
            kontrolaPrazdnoty();
            spravce.prejdiNaPredchoziPolozku();
            movieView.getSelectionModel().select(spravce.dej());
        });

        Button posledniBtn = new Button("Posledni");
        posledniBtn.setOnAction(e -> {
            kontrolaPrazdnoty();
            spravce.posledeni();
            movieView.getSelectionModel().select(spravce.dej());
        });

        Label prikazyLbl = new Label("Prikazy");

        Button editujBtn = new Button("Edituj");
        editujBtn.setOnAction(e -> {
            Function<Movie, Movie> editor = DialogEditor.getDialogEditor();
            spravce.edituj(editor);
            movieView.getItems().clear();
            vypis();
        });

        Button vijmiBtn = new Button("Vijmi");
        vijmiBtn.setOnAction(e -> {
            if (spravce.dej() != null) {
                spravce.vyjmi();
                movieView.getItems().remove(movieView.getSelectionModel().getSelectedItem());
            }
        });

        Button zobrazBtn = new Button("Zobraz");
        zobrazBtn.setOnAction(e -> {
            kontrolaPrazdnoty();
            if (movieView.getItems().isEmpty()) {
                vypis();
            }
        });

        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> {
            movieView.getItems().clear();
            if (!cbFiltr.getSelectionModel().isEmpty()) {
                cbFiltr.getSelectionModel().clearSelection();
            }
        });

        Button[] buttonsVert = {prvniBtn, dalsiBtn, predchoziBtn,
            posledniBtn, editujBtn, vijmiBtn, zobrazBtn, clearBtn};
        for (Button button : buttonsVert) {
            button.setMinSize(70, 30);
            button.setMaxSize(70, 30);
        }

        verticalPanel.getChildren().addAll(prochLbl, prvniBtn, dalsiBtn, predchoziBtn,
                posledniBtn, prikazyLbl, editujBtn, vijmiBtn, zobrazBtn, clearBtn);
        bp.setRight(verticalPanel);
    }

    private void setUpBottomPanel() {
        horizontalPanel = new HBox();
        horizontalPanel.setAlignment(Pos.CENTER);
        horizontalPanel.setSpacing(15);
        horizontalPanel.setMinWidth(115);
        horizontalPanel.setPadding(new Insets(15, 115, 15, 15));

        Button generujBtn = new Button("Generuj");
        generujBtn.setOnAction(e -> {
            generuj();
        });

        Button ulozBtn = new Button("Uloz");
        ulozBtn.setOnAction(e -> {
            spravce.ulozText("movie.txt");
        });

        Button nactiBtn = new Button("Nacti");
        nactiBtn.setOnAction(e -> {
            movieView.getItems().clear();
            spravce.nactiText("Movie.txt");
            vypis();

        });

        Button novyBtn = new Button("Novy");
        novyBtn.setOnAction(e -> {
            DialogNovy dialog = DialogNovy.getDialogNovy();
            Movie movie = dialog.dejResult();
            if (movie != null) {
                spravce.vlozPolozku(movie);
                movieView.getItems().add(movie);
            }

        });

        Label filtrLbl = new Label("Filtr");
        cbFiltr = new ComboBox<>();
        nastavFiltr(cbFiltr);

        Button najdiBtn = new Button("Najdi");
        najdiBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Uvedte id");
            dialog.setHeaderText("Uvedte id");
            dialog.setContentText("id movie: ");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String nazev = dialog.getEditor().getText().trim();
                System.out.println("nazev");
                Movie movie = spravce.najdi(new MovieKlic(nazev));
                if (movie != null) {
                    movieView.getSelectionModel().select(movie);
                } else {
                    error("Takove movie neexistuje");
                }
            }
        });

        Button zalohujBtn = new Button("Zalohuj");
        zalohujBtn.setOnAction(e -> {
            spravce.zalohuj("binarniSoubor");
        });

        Button obnovBtn = new Button("Obnov");
        obnovBtn.setOnAction(e -> {
            movieView.getItems().clear();
            spravce.nactiText("binarniSoubor");
            vypis();
        });

        Button zrusBtn = new Button("Zrus");
        zrusBtn.setOnAction(e -> {
            spravce.zrus();
            movieView.getItems().clear();
        });

        Button[] buttonsHor = {generujBtn, ulozBtn, nactiBtn, novyBtn, najdiBtn, zalohujBtn, obnovBtn, zrusBtn};

        for (Button button : buttonsHor) {
            button.setMaxSize(70, 30);
            button.setMinSize(70, 30);
        }

        horizontalPanel.getChildren().addAll(generujBtn, ulozBtn, nactiBtn, novyBtn,
                filtrLbl, najdiBtn, zalohujBtn, obnovBtn, zrusBtn);
        bp.setBottom(horizontalPanel);
    }

    private void nastavFiltr(ComboBox<TypMovie> cb) {
        cb.getItems().addAll(Arrays.asList(TypMovie.values()));

        cb.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            TypMovie selectedType = cb.getValue();
            movieView.getItems().clear();
            spravce.vypis(movie -> {
                if (movie.getTypMovie() == selectedType) {
                    movieView.getItems().add(movie);
                }
            });
        });
    }

    private void closeApp(Stage stage) {
        stage.setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Close confirmation");
            alert.setHeaderText("Zavrit aplikace?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stage.close();
            }
        });

    }

    private void kontrolaPrazdnoty() {
        if (spravce.dejPocet() == 0) {
            error("Seznam je prazdny");
        }
    }

    private void vypis() {
        spravce.vypis(movie -> movieView.getItems().add(movie));
    }

    private void generuj() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Uvedte pocet movie");
        dialog.setHeaderText("Uvedte pocet movie");
        dialog.setContentText("Uvedte pocet movie k generovani: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            int pocet = Integer.parseInt(dialog.getEditor().getText());
            movieView.getItems().clear();
            spravce.generuj(pocet);
            vypis();
        }

    }

}
