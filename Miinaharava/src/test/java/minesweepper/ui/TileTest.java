package minesweepper.ui;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import minesweepper.logic.Graphix;


public class TileTest {
    
    public Tile tile;
    public BoardScreen gameBoard;
    
    @Before
    public void setup() {
        this.gameBoard = new BoardScreen(10, 10, 10, new Graphix());
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
