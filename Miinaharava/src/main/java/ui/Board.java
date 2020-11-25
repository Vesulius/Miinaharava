
package ui;
        
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import minesweepper.BoardGenerator;
        
public class Board {
    
    public Tile[][] tiles;
    public Graphix graphix;

    public Board(Tile[][] tiles, Graphix graphix) {
        this.tiles = tiles;
        this.graphix = graphix;
    }

    
    public Scene boardSceneCreator() {
        Pane root = new Pane();
        root.setPrefSize(500, 500);

        BoardGenerator boardGenerator = new BoardGenerator();
        int[][] board = boardGenerator.generateBoard(10, 10, 10);

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                Tile tile = new Tile(y, x, board[x][y], this.graphix);
                tiles[y][x] = tile;
                root.getChildren().add(tile);
            }
        }

        return new Scene(root);
    }
}
    



