package minesweepper.ui;


import javafx.scene.Scene;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweepper.logic.Graphix;

public class GameBoardTest {
    
    public BoardScreen board;
    public Scene scene;
    
    @Before
    public void setup() {
        this.board = new BoardScreen(10, 10, 10, new Graphix());
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