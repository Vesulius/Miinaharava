package ui;
import minesweepper.BoardGenerator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TileTest {
    
    public Tile tile;
    public GameBoard gameBoard;
    
    @Before
    public void setup() {
        System.out.println("toimi");
        this.gameBoard = new GameBoard(10, 10, 10, new Graphix());
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
