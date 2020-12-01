package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Graphix extends Application {

    private final Stage stage;

    public Graphix() {
        this.stage = new Stage();
    }

    @Override
    public void start(Stage stage) {
        this.newGame();
        this.stage.show();
    }

    public void newGame() {
        this.stage.setScene(new GameBoard(10, 10, 10, this).boardSceneCreator());
    }

    public void gameFinished(boolean loss) {
        this.stage.setScene(new ResultsBoard(this).resultSceneCreator(loss));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
