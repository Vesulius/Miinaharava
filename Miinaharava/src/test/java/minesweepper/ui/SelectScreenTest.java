package minesweepper.ui;

import javafx.scene.Scene;
import minesweepper.logic.App;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SelectScreenTest {
    
    public SelectScreen selectScreen;
    
    @Before
    public void setup() throws ClassNotFoundException {
        this.selectScreen = new SelectScreen(new App());
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
