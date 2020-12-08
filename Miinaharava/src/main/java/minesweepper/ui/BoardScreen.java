
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
    
    public BoardScreen(int heigth, int weidth, int mines, Graphix graphix) {
        this.tiles = new Tile[heigth][weidth];
        this.victoryCount = weidth * heigth - mines;
        this.minesCount = mines;
        this.graphix = graphix;
        this.boardGenerator = new BoardGenerator(this);
        
        GridPane grid = new GridPane();
                
        Tile[][] tiles = this.boardGenerator.generateBoard(heigth, weidth, this.minesCount);
        
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[0].length; x++) {
                grid.add(tiles[y][x], y, x);
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
    



