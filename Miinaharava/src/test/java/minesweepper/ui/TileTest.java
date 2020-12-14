package minesweepper.ui;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweepper.logic.App;


public class TileTest {
    
    public Tile tile;
    public BoardScreen gameBoard;
    
    @Before
    public void setup() throws ClassNotFoundException {
        this.gameBoard = new BoardScreen(10, 10, 10, new App());
        this.tile = new Tile(1, this.gameBoard);
    }

    @Test
    public void tileCreated() {
        assertTrue(this.tile != null);
    }
   
    @Test
    public void tileTextIsHidden() {
        assertTrue(!this.tile.text.isVisible());
    }
}
