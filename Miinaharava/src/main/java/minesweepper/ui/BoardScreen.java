
package minesweepper.ui;
        
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import minesweepper.logic.BoardGenerator;
import minesweepper.logic.Graphix;

        
public class BoardScreen {
    
    public Tile[][] tiles;
    private Scene scene;
    private Graphix graphix;
    private int victoryCount;
    private int minesCount;
    private BoardGenerator boardGenerator;
    
    public BoardScreen(int heigth, int width, int mines, Graphix graphix) {
        this.tiles = new Tile[heigth][width];
        this.victoryCount = width * heigth - mines;
        this.minesCount = mines;
        this.graphix = graphix;
        this.boardGenerator = new BoardGenerator(this);
        
        GridPane grid = new GridPane();
                
        this.tiles = this.boardGenerator.generateBoard(heigth, width, this.minesCount);
        
        
        for (int y = 0; y < this.tiles.length; y++) {
            for (int x = 0; x < this.tiles[0].length; x++) {
                grid.add(this.tiles[y][x], y, x);
            }
        }

        this.scene = new Scene(grid);
    }
    
    public void checkVictory(boolean loss) {
        if (loss) {
            this.graphix.endGame(loss);
        }
        
        this.victoryCount--;
        if (this.victoryCount < 1) {
            System.out.println("WIN!");
            this.graphix.endGame(false);
        } 
    }
    
    public Scene getScene() {
        return this.scene;
    }
}
    



