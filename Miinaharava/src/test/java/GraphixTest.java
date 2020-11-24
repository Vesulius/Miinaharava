/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.Scene;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphixTest {
    
    Graphix graphix;
    Scene scene;
    
    @Before
    public void setup() {
        this.graphix = new Graphix();
        this.scene = this.graphix.boardSceneCreator();
    }
    
    @Test
    public void createdGraphix() {
        assertTrue(this.graphix != null);
    }
    
    @Test
    public void createdTiles() {
        assertTrue(this.graphix.tiles[1][1] != null);
    }
    
    @Test
    public void tilesAreInvisible() {
        assertTrue(!this.graphix.tiles[1][1].text.isVisible());
    }
    
    @Test
    public void tilesAreRevealed() {
        this.graphix.tiles[1][1].reveal();
        assertTrue(this.graphix.tiles[1][1].text.isVisible());
    }

}
