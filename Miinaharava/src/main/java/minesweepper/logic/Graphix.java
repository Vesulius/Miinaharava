package minesweepper.logic;

import javafx.application.Application;
import javafx.stage.Stage;
import minesweepper.ui.*;

public class Graphix extends Application {
    
    private SelectScreen selectScreen;
    private ResultsScreen resultsScreen;
    private BoardScreen boardScreen;
    private Stage stage;

    public Graphix() {
        this.selectScreen = new SelectScreen(this);
        this.resultsScreen = new ResultsScreen(this);
        // this.boardScreen = new BoardScreen(this);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.selectGame();
        this.stage.show();
    }
    
    public void selectGame() {
        this.stage.setScene(new SelectScreen(this).getScene());
    }    
    
    public void newGame(int height, int width) {
        int mines = (height + width) / 2;
        System.out.println(mines);
        this.stage.setScene(new BoardScreen(height, width, mines, this).getScene());
    }

    public void endGame(boolean loss) {
        this.stage.setScene(new ResultsScreen(this).resultSceneCreator(loss));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
