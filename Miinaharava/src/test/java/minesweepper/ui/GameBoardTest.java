package minesweepper.ui;


import javafx.scene.Scene;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweepper.logic.App;

public class GameBoardTest {
    
    public BoardScreen board;
    public Scene scene;
    
    @Before
    public void setup() throws ClassNotFoundException {
        this.board = new BoardScreen(10, 10, 10, new App());
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
    public void tilesAreRevealed() throws ClassNotFoundException {
        this.board.tiles[1][1].reveal();
        assertTrue(this.board.tiles[1][1].text.isVisible());
    }
}