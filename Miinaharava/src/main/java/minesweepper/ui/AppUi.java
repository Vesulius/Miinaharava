package minesweepper.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import minesweepper.data.*;
import minesweepper.logic.AppService;


public class AppUi extends Application {
    
    private Stage stage;
    private AppService service;
    
    public AppUi() throws ClassNotFoundException {
        this.service = new AppService();
        // this.runRegister.testRuns();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setResizable(false);
        this.selectGame();
        this.stage.show();
    }
    
    public void selectGame() {
        this.setScene(new SelectScreen(this).getScene());
    }    
    
    public void newGame(int height, int width, String username) {
        this.service.setUsername(username);
        this.setScene(new BoardScreen(height, width, this.service.mineCounter(height, width), this).getScene());
    }

    public void endGame(boolean loss, double time) throws ClassNotFoundException {
        if (!loss) {
            this.service.endGame(time);
        }
        this.setScene(new ResultsScreen(this, loss, this.service.getRuns()).getScene());
    }
    
    public void setScene(Scene scene) {
        if (this.stage != null) {
            this.stage.setScene(scene);
        } else {
            System.out.println("Stage not set.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
