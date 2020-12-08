package minesweepper.ui;

import javafx.scene.Scene;
import minesweepper.logic.Graphix;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SelectScreenTest {
    
    public SelectScreen selectScreen;
    
    @Before
    public void setup() {
        this.selectScreen = new SelectScreen(new Graphix());
    }
    
    @Test
    public void selectScreenCreated() {
        assertTrue(this.selectScreen != null);
    }
    
    @Test
    public void canGetScene() {
        assertTrue(this.selectScreen.getScene() instanceof Scene);
    }
   
}
