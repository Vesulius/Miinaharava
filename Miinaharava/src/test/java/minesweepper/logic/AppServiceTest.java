package minesweepper.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AppServiceTest {
    
    public AppService service;
    
    @Before
    public void setup() throws ClassNotFoundException {
        this.service = new AppService();
    }
    
    @Test
    public void createdAppService() {
        assertTrue(this.service != null);
    }
    
    @Test
    public void mineCouterCanCount() {
        assertEquals(10, this.service.mineCounter(10, 10));
    }
    
    @Test
    public void canRegisterGame() throws ClassNotFoundException {
        this.service.newGame(1, 1, "1234");
        this.service.endGame(100);
        assertEquals("1234", this.service.getRuns().get(0)[0]);
    }
    
}
