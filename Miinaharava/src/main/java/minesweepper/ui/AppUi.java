package minesweepper.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import minesweepper.logic.AppService;
import minesweepper.logic.BoardGenerator;
import minesweepper.logic.GameTimer;


public class AppUi extends Application {
    
    private Stage stage;
    private AppService service;
    private GameTimer timer;
    private BoardGenerator generator;
    
    public AppUi() throws ClassNotFoundException {
        this.service = new AppService();
        this.generator = new BoardGenerator(this);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("MINESWEEPPER");
        this.stage.setResizable(false);
        this.selectGame();
        this.stage.show();
    }
    
    public void selectGame() {
        this.setScene(new SelectScreen(this).getScene());
    }    
    
    public void newGame(int height, int width, String username) {
        this.service.newGame(height, width, username);
        
        GridPane grid = this.generator.generateBoard(width, height, this.service.getMines());
        Text text = new Text("1");
        text.setFont(Font.font(22));
                
        this.timer = new GameTimer(text);
        this.timer.startTimer();
        
        this.setScene(new BoardScreen(height, width, this.service.getMines(), text, grid).getScene());
    }

    public void endGame(boolean win) throws ClassNotFoundException {
        this.timer.endTimer();
        this.setScene(new ResultsScreen(this, win, this.service.getRuns()).getScene());
    }
    
    public void setScene(Scene scene) {
        if (this.stage != null) {
            this.stage.setScene(scene);
        } else {
            System.out.println("Stage not set.");
        }
    }
    
    public void checkVictory() throws ClassNotFoundException {
        if (this.service.checkVictoryCounter()) {
            this.service.endGame(this.timer.getTime());
            this.endGame(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
