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


/**
 * This class is in charge ui. It will also communicate with logic classes.
 * 
 */

public class AppUi extends Application {
    
    private Stage stage;
    private AppService service;
    private GameTimer timer;
    private BoardGenerator generator;
    
    public AppUi() throws ClassNotFoundException {
        this.service = new AppService();
        this.generator = new BoardGenerator(this);
    }

    /**
     * This method will set new Stage and its settings
     * 
     * @param stage that will be set
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("MINESWEEPPER");
        this.stage.setResizable(false);
        this.selectGame();
        this.stage.show();
    }
    
    /**
     * This method sets SelectScreen scene as stages scene
     */
    public void selectGame() {
        this.setScene(new SelectScreen(this).getScene());
    }    
    
    /**
     * This method sets BoardScreen scene as stages scene and orders AppService to start a new game
     * 
     * @param height new gameboards height 
     * @param width new gameboards width
     * @param username the username that player wrote
     */
    public void newGame(int height, int width, String username) {
        this.service.newGame(height, width, username);
        
        GridPane grid = this.generator.generateBoard(width, height, this.service.getMines());
        Text text = new Text("1");
        text.setFont(Font.font(22));
                
        this.timer = new GameTimer(text);
        this.timer.startTimer();
        
        this.setScene(new BoardScreen(text, grid).getScene());
    }

    /**
     *
     * @param win
     * @throws ClassNotFoundException
     */
    public void endGame(boolean win) throws ClassNotFoundException {
        this.timer.endTimer();
        this.setScene(new ResultsScreen(this, win, this.service.getRuns()).getScene());
    }
    
    private void setScene(Scene scene) {
        if (this.stage != null) {
            this.stage.setScene(scene);
        } else {
            System.out.println("Stage not set.");
        }
    }
    
    /**
     * This method asks AppService if player has won the game
     * if game is won it will tell AppService to record the run
     * 
     * @throws ClassNotFoundException will be thrown incase player won the game but database can't connect to database
     */
    public void checkVictory() throws ClassNotFoundException {
        if (this.service.checkVictoryCounter()) {
            this.service.endGame(this.timer.getTime());
            this.endGame(true);
        }
    }

    /**
     * This method will launch the apps ui
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
