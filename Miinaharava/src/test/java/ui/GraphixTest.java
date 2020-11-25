package ui;

import javafx.scene.Scene;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphixTest {
    
    Graphix graphix;
    Scene scene;
    
    @Before
    public void setup() {
        this.graphix = new Graphix();
        this.scene = this.graphix.board.boardSceneCreator();
    }
    
    @Test
    public void createdGraphix() {
        assertTrue(this.graphix != null);
    }
    
    @Test
    public void createdTiles() {
        assertTrue(this.graphix.board.tiles[1][1] != null);
    }
    
    @Test
    public void tilesAreInvisible() {
        assertTrue(!this.graphix.board.tiles[1][1].text.isVisible());
    }
    
    @Test
    public void tilesAreRevealed() {
        this.graphix.board.tiles[1][1].reveal();
        assertTrue(this.graphix.board.tiles[1][1].text.isVisible());
    }

}
