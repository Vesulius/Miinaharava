package minesweepper.logic;

import javafx.application.Application;
import javafx.stage.Stage;

import minesweepper.data.*;
import minesweepper.ui.*;

public class App extends Application {
    
    private Stage stage;
    private Database database;
    private RunRegister runRegister;
    
    private long time;
    private int mines;
    private String username;
    
    public App() throws ClassNotFoundException {
        this.database = new Database();
        this.database.initialize();
        this.runRegister = new RunRegister(this.database);
        // this.runRegister.testRuns();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
        this.selectGame();
        this.stage.show();
    }
    
    public void selectGame() {
        this.stage.setScene(new SelectScreen(this).getScene());
    }    
    
    public void newGame(int height, int width, String username) {
        this.username = username;
        this.mines = height * width / 10;
        this.time = System.nanoTime();
        this.stage.setScene(new BoardScreen(height, width, this.mines, this).getScene());
    }

    public void endGame(boolean loss) throws ClassNotFoundException {
        long runTime = (long) ((System.nanoTime() - this.time) / 1e9);
        int score = (int) (mines * 100000 / (runTime + 1));
        
        if (!loss) {
            this.runRegister.addRun(this.username, score, runTime);
        }
        this.stage.setScene(new ResultsScreen(this, loss, this.runRegister.getRuns()).getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
