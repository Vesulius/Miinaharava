package ui;


import javafx.scene.Scene;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {
    
    public GameBoard board;
    public Scene scene;
    
    @Before
    public void setup() {
        this.board = new GameBoard(10, 10, 10, new Graphix());
        this.board.boardSceneCreator();
    }
    
    @Test
    public void createdGameBoard() {
        assertTrue(this.board != null);
    }
    
    @Test
    public void createdTiles() {
        assertTrue(this.board.tiles[1][1] != null);
    }
    
    @Test
    public void tilesAreInvisible() {
        assertTrue(!this.board.tiles[1][1].text.isVisible());
    }
    
    @Test
    public void tilesAreRevealed() {
        this.board.tiles[1][1].reveal();
        assertTrue(this.board.tiles[1][1].text.isVisible());
    }
}