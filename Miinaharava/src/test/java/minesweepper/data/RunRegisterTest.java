package minesweepper.data;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RunRegisterTest {
    
    public RunRegister register;
    
    @Before
    public void setup() throws ClassNotFoundException {
        Database db = new Database();
        db.initialize();
        this.register = new RunRegister(db);
    }
    
    @Test
    public void createdRunRegister() {
        assertTrue(this.register != null);
    }
    
    @Test
    public void canInsertRuns() throws ClassNotFoundException {
        this.register.testRuns();
        assertTrue(this.register.getRuns() != null);
    }
}
