
package minesweepper.ui;
        
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import minesweepper.logic.BoardGenerator;

        
public class BoardScreen {
    
    public Tile[][] tiles;
    private Scene scene;
    private AppUi app;
    private int victoryCount;
    private BoardGenerator boardGenerator;
    private static double interval;
    private static Text timerText;
    private GridPane grid;
    
    public BoardScreen(int heigth, int width, int mines, AppUi app) {
        this.victoryCount = width * heigth - mines;
        this.app = app;
        this.boardGenerator = new BoardGenerator(this);
        this.timerText = new Text("0");
        timerText.setFont(Font.font(22));
        this.grid = new GridPane();
        this.tiles = this.boardGenerator.generateBoard(heigth, width, mines);
        this.interval = 0.0;
        
        for (int y = 0; y < this.tiles.length; y++) {
            for (int x = 0; x < this.tiles[0].length; x++) {
                grid.add(this.tiles[y][x], y, x);
            }
        }
        
        HBox hbox = new HBox();
        hbox.setSpacing(150);
        hbox.getChildren().addAll(timerText);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(hbox, grid);
        
        this.startTimer();
        this.scene = new Scene(vbox);
        
    }   
    
    private void startTimer() {
        final Timer time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (interval > 100000) {
                    time.cancel();
                    time.purge();
                } else {
                    timerText.setText(String.valueOf(Math.ceil(interval * 10) / 10));
                    setInterval();
                }
            }
        }, 0, 100);
    }
    
    private static void setInterval() {
        interval += 0.1;
    }
    
    public void checkVictory(boolean loss) throws ClassNotFoundException {
        if (loss) {
            interval += 100000;
            this.app.endGame(true, Double.valueOf(timerText.getText()));
        }
        this.victoryCount--;
        if (this.victoryCount < 1) {
            interval += 100000;
            System.out.println("WIN!");
            this.app.endGame(false, Double.valueOf(timerText.getText()));
        } 
    }
    
    public Scene getScene() {
        return this.scene;
    }
}
    



