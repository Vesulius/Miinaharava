
package ui;
        
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import minesweepper.BoardGenerator;
        
public class GameBoard {
    
    public Tile[][] tiles;
    public int victoryCount;
    public int minesCount;
    public Graphix graphix;

    public GameBoard(int y, int x, int mines, Graphix graphix) {
        this.tiles = new Tile[y][x];
        this.victoryCount = y * x - mines;
        this.minesCount = mines;
        this.graphix = graphix;
    }

    
    public Scene boardSceneCreator() {
        GridPane grid = new GridPane();

        BoardGenerator boardGenerator = new BoardGenerator();
        int[][] board = boardGenerator.generateBoard(tiles.length, tiles[0].length, this.minesCount);
        
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                Tile tile = new Tile(board[x][y], this);
                tiles[y][x] = tile;
                grid.add(tile, y, x);
            }
        }
        
        // This part shoud be done in the boardgeneration
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (x - 1 >= 0 && y + 1 < tiles.length) {
                    tiles[y][x].neibourghs.add(tiles[y + 1][x - 1]);
                }
                if (y + 1 < tiles.length) {
                    tiles[y][x].neibourghs.add(tiles[y + 1][x]);
                }
                if (x + 1 < tiles[0].length && y + 1 < tiles.length) {
                    tiles[y][x].neibourghs.add(tiles[y + 1][x + 1]);
                }

                if (x - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y][x - 1]);
                }
                if (x + 1 < tiles[0].length) {
                    tiles[y][x].neibourghs.add(tiles[y][x + 1]);
                }

                if (x - 1 >= 0 && y - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y - 1][x - 1]);
                }
                if (y - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y - 1][x]);
                }
                if (x + 1 < tiles[0].length && y - 1 >= 0) {
                    tiles[y][x].neibourghs.add(tiles[y - 1][x + 1]);
                }
                
            }
        }

        return new Scene(grid);
    }
    
    public void checkVictory(boolean loss) {
        if (loss) {
            this.graphix.gameFinished(loss);
        }
        
        this.victoryCount--;
        if (this.victoryCount < 1) {
            System.out.println("WIN!");
            this.graphix.gameFinished(false);
        } 
    }
}
    



