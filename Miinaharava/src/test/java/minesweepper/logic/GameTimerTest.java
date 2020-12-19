package minesweepper.logic;

import java.util.concurrent.TimeUnit;
import javafx.scene.text.Text;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTimerTest {

    public GameTimer timer;
    public Text text;
    
    @Before
    public void setup() {
        this.text = new Text("0");
        this.timer = new GameTimer(this.text);
    }
    
    @Test
    public void createdGameTimer() {
        assertTrue(this.timer != null);
    }
    
    @Test
    public void canTime() throws InterruptedException {
        this.timer.startTimer();
        TimeUnit.SECONDS.sleep(1);
        assertTrue(this.timer.getTime() > 0.5);
    }
}
